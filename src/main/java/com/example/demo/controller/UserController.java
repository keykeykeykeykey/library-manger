package com.example.demo.controller;

import com.example.demo.entity.RestResult;
import com.example.demo.entity.User;
import com.example.demo.service.BorrowRecordsService;
import com.example.demo.service.UserInfoService;
import com.example.demo.utils.MD5;
import com.example.demo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final ResultGenerator generator;

    private final UserInfoService userInfoService;

    private final BorrowRecordsService borrowRecordsService;

    @Autowired
    public UserController(UserService userService, ResultGenerator generator, UserInfoService userInfoService, BorrowRecordsService borrowRecordsService) {
        this.userService = userService;
        this.generator = generator;
        this.userInfoService = userInfoService;
        this.borrowRecordsService = borrowRecordsService;
    }

    /**
     * 匹配 /user/login 地址 ,限定POST方法
     * 。@NotNull 在字段前添加注解代表验证该字段，如果为空则报异常
     *
     * @return 登陆成功则返回相关信息，否则返回错误提示
     */
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResult login(@Valid @RequestBody User user) {
        String name = user.getName();
        String password = user.getPassword();
        password = MD5.md5Password(password);
        List<User> user1 = userService.findUserByName(name, password);
        if (user1.size() != 0) {
            //储存到session中
            return generator.getSuccessResult("登陆成功", user1);
        }
        return generator.getFailResult("用户名/密码错误");
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/base", method = RequestMethod.POST)
    public RestResult getBase(@Valid @RequestBody Map<String, Object> reqMap) {
        Integer id = Integer.valueOf(reqMap.get("id").toString());
        int a = borrowRecordsService.findById(reqMap.get("id").toString());
        Object data = userService.findNameById(reqMap.get("id").toString());
        return generator.getSuccessResult(String.valueOf(a), userInfoService.findById(id));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public RestResult change(@Valid @RequestBody Map<String, Object> reqMap) {
        String id = reqMap.get("id").toString();
        String phone = reqMap.get("phone").toString();
        String email = reqMap.get("email").toString();
        userInfoService.change(id, phone, email);
        return generator.getSuccessResult("修改成功", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    public RestResult changepwd(@Valid @RequestBody Map<String, Object> reqMap) {
        Integer id = Integer.valueOf(reqMap.get("id").toString());
        String password = reqMap.get("password").toString();
        String newPassword = reqMap.get("newPassword").toString();
        password = MD5.md5Password(password);
        newPassword = MD5.md5Password(newPassword);
        int a = userService.changepwd(id, password, newPassword);
        if (a == 1) {
            return generator.getSuccessResult("修改成功", null);
        } else {
            return generator.getSuccessResult("修改失败", null);
        }
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getborrow", method = RequestMethod.POST)
    public RestResult getAllBorrowById(@Valid @RequestBody Map<String, Object> reqMap) {
        String id = reqMap.get("id").toString();
        int status = Integer.parseInt(reqMap.get("status").toString());
        return generator.getResult("查询成功",borrowRecordsService.findContentById(Integer.valueOf(id),status),userInfoService.findById(Integer.valueOf(id)));
    }
}
