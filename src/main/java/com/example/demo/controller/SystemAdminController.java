package com.example.demo.controller;

import com.example.demo.entity.BookAdmin;
import com.example.demo.entity.RestResult;
import com.example.demo.entity.SystemAdmin;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.BookAdminService;
import com.example.demo.service.SystemAdminService;
import com.example.demo.service.UserInfoService;
import com.example.demo.utils.MD5;
import com.example.demo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/systemAdmin")
public class SystemAdminController {

    private final SystemAdminService systemAdminService;

    private final UserInfoService userInfoService;

    private final BookAdminService bookAdminService;

    private final ResultGenerator generator;

    @Autowired
    public SystemAdminController(SystemAdminService systemAdminService, UserInfoService userInfoService, BookAdminService bookAdminService, ResultGenerator generator) {
        this.systemAdminService = systemAdminService;
        this.userInfoService = userInfoService;
        this.bookAdminService = bookAdminService;
        this.generator = generator;
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
    public RestResult login(@Valid @RequestBody SystemAdmin user) {
        String name = user.getName();
        String password = user.getPassword();
        password = MD5.md5Password(password);
        return generator.getSuccessResult("登陆成功",systemAdminService.findUserByName(name, password));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RestResult addUser(@Valid @RequestBody Map<String, Object> reqMap) {
        String addUserLendNum = reqMap.get("addUserLendNum").toString();
        String addUserId = reqMap.get("addUserId").toString();
        String addUserDepartment = reqMap.get("addUserDepartment").toString();
        String addUserMajor = reqMap.get("addUserMajor").toString();
        String addUserPhone = reqMap.get("addUserPhone").toString();
        String addUserMail = reqMap.get("addUserMail").toString();
        int addUserMax = Integer.parseInt(reqMap.get("addUserMax").toString());
        int addUserTime = Integer.parseInt(reqMap.get("addUserTime").toString());
        UserInfo userInfo = new UserInfo();
        userInfo.setLendedNum(addUserLendNum);
        userInfo.setUserId(Long.parseLong(addUserId));
        userInfo.setDepartments(addUserDepartment);
        userInfo.setMajor(addUserMajor);
        userInfo.setPhone(addUserPhone);
        userInfo.setEmail(addUserMail);
        userInfo.setMaxNum(addUserMax);
        userInfo.setTime(addUserTime);
        int a = userInfoService.getLendNum(addUserLendNum);
        if (a == 0) {
            userInfoService.save(userInfo);
            return generator.getSuccessResult("新增成功", null);
        }
        return generator.getSuccessResult("0", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/sureUser", method = RequestMethod.POST)
    public RestResult sureUser(@Valid @RequestBody Map<String, Object> reqMap) {
        String lendNum = reqMap.get("lendNum").toString();
        return generator.getSuccessResult("查询成功", userInfoService.sureUser(lendNum));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public RestResult deleteUser(@Valid @RequestBody Map<String, Object> reqMap) {
        String lendNum = reqMap.get("lendNum").toString();
        int a = userInfoService.getLendNum(lendNum);
        if (a == 0) {
            return generator.getSuccessResult("0", null);
        }
        userInfoService.deleteUser(lendNum);
        return generator.getSuccessResult("1", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public RestResult updateUser(@Valid @RequestBody Map<String, Object> reqMap) {
        String changeUserLendNum = reqMap.get("changeUserLendNum").toString();
        long changeUserId = Long.parseLong(reqMap.get("changeUserId").toString());
        String changeUserDepartment = reqMap.get("changeUserDepartment").toString();
        String changeUserMajor = reqMap.get("changeUserMajor").toString();
        String changeUserPhone = reqMap.get("changeUserPhone").toString();
        String changeUserMail = reqMap.get("changeUserMail").toString();
        int changeUserMax = Integer.parseInt(reqMap.get("changeUserMax").toString());
        int changeUserTime = Integer.parseInt(reqMap.get("changeUserTime").toString());
        int a = userInfoService.getLendNum(changeUserLendNum);
        if (a == 0) {
            return generator.getSuccessResult("0", null);
        }
        userInfoService.updateUser(changeUserLendNum, changeUserId, changeUserDepartment, changeUserMajor, changeUserPhone, changeUserMail, changeUserMax, changeUserTime);
        return generator.getSuccessResult("1", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public RestResult addAdmin(@Valid @RequestBody Map<String, Object> reqMap) {
        String addAdminNum = reqMap.get("addAdminNum").toString();
        String addAdminName = reqMap.get("addAdminName").toString();
        String addAdminPassword = reqMap.get("addAdminPassword").toString();
        String addAdminPhone = reqMap.get("addAdminPhone").toString();
        String addAdminEmail = reqMap.get("addAdminEmail").toString();
        BookAdmin bookAdmin = new BookAdmin();
        bookAdmin.setId(Integer.valueOf(addAdminNum));
        bookAdmin.setName(addAdminName);
        bookAdmin.setPassword(MD5.md5Password(addAdminPassword));
        bookAdmin.setPhone(addAdminPhone);
        bookAdmin.setEmail(addAdminEmail);
        int a = bookAdminService.getIdById(Integer.valueOf(addAdminNum));
        if (a == 0) {
            bookAdminService.save(bookAdmin);
            return generator.getSuccessResult("新增成功", null);
        }
        return generator.getSuccessResult("0", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/sureAdmin", method = RequestMethod.POST)
    public RestResult sureAdmin(@Valid @RequestBody Map<String, Object> reqMap) {
        String adminNum = reqMap.get("adminNum").toString();
        return generator.getSuccessResult("查询成功",bookAdminService.sureAdmin(Integer.valueOf(adminNum)));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST)
    public RestResult deleteAdmin(@Valid @RequestBody Map<String, Object> reqMap) {
        String adminNum = reqMap.get("adminNum").toString();
        int a = bookAdminService.getIdById(Integer.valueOf(adminNum));
        if (a == 0) {
            return generator.getSuccessResult("0", null);
        }
        bookAdminService.deleteAdmin(Integer.valueOf(adminNum));
        return generator.getSuccessResult("1", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    public RestResult updateAdmin(@Valid @RequestBody Map<String, Object> reqMap) {
        String adminNum = reqMap.get("adminNum").toString();
        String adminName = reqMap.get("adminName").toString();
        String adminPhone = reqMap.get("adminPhone").toString();
        String adminEmail = reqMap.get("adminEmail").toString();
        int a = bookAdminService.getIdById(Integer.valueOf(adminNum));
        if (a == 0) {
            return generator.getSuccessResult("0", null);
        }
        bookAdminService.updateAdmin(Integer.valueOf(adminNum),adminName,adminPhone,adminEmail);
        return generator.getSuccessResult("1", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/searchUserInfo", method = RequestMethod.POST)
    public RestResult searchUserInfo(@Valid @RequestBody Map<String, Object> reqMap) {
        String keyword = reqMap.get("keyword").toString();
        return generator.getSuccessResult("0", userInfoService.searchUserInfo(keyword));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/searchAdminInfo", method = RequestMethod.POST)
    public RestResult searchAdminInfo(@Valid @RequestBody Map<String, Object> reqMap) {
        String keyword = reqMap.get("keyword").toString();
        return generator.getSuccessResult("0", bookAdminService.searchAdminInfo(keyword));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getSystem", method = RequestMethod.POST)
    public RestResult getSystem(@Valid @RequestBody Map<String, Object> reqMap) {
        String systemId = reqMap.get("systemId").toString();
        return generator.getSuccessResult("0", systemAdminService.getSystem(Long.parseLong(systemId)));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/updateSystemInfo", method = RequestMethod.POST)
    public RestResult updateSystemInfo(@Valid @RequestBody Map<String, Object> reqMap) {
        String systemId = reqMap.get("systemId").toString();
        String password = reqMap.get("password").toString();
        String newPassword = reqMap.get("newPassword").toString();
        String phone = reqMap.get("phone").toString();
        String email = reqMap.get("email").toString();
        String a = systemAdminService.getPassword(Long.parseLong(systemId));
        if(a.equals(MD5.md5Password(password))){
            systemAdminService.updateSystemInfo(Long.parseLong(systemId),MD5.md5Password(newPassword),phone,email);
            return generator.getSuccessResult("1",null);
        }
        else{
            return generator.getSuccessResult("0",null);
        }
    }
}
