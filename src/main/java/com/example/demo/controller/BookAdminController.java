package com.example.demo.controller;

import com.example.demo.entity.BookAdmin;
import com.example.demo.entity.BookInfo;
import com.example.demo.entity.BorrowRecords;
import com.example.demo.entity.RestResult;
import com.example.demo.service.BookAdminService;
import com.example.demo.service.BookInfoService;
import com.example.demo.service.BorrowRecordsService;
import com.example.demo.service.UserInfoService;
import com.example.demo.utils.MD5;
import com.example.demo.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/bookAdmin")
public class BookAdminController {

    private final BookAdminService bookAdminService;

    private final UserInfoService userInfoService;

    private final BookInfoService bookInfoService;

    private final BorrowRecordsService borrowRecordsService;

    private final ResultGenerator generator;

    @Autowired
    public BookAdminController(BookAdminService bookAdminService, UserInfoService userInfoService, BookInfoService bookInfoService, BorrowRecordsService borrowRecordsService, ResultGenerator generator) {
        this.bookAdminService = bookAdminService;
        this.userInfoService = userInfoService;
        this.bookInfoService = bookInfoService;
        this.borrowRecordsService = borrowRecordsService;
        this.generator = generator;
    }

    /**
     * 匹配 /bookAdmin/login 地址 ,限定POST方法
     * 。@NotNull 在字段前添加注解代表验证该字段，如果为空则报异常
     *
     * @return 登陆成功则返回相关信息，否则返回错误提示
     */
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResult login(@Valid @RequestBody BookAdmin bookAdmin) {
        String name = bookAdmin.getName();
        String password = bookAdmin.getPassword();
        password = MD5.md5Password(password);
        return generator.getSuccessResult("登陆成功", bookAdminService.findUserByName(name, password));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/userBorrowBookSure", method = RequestMethod.POST)
    public RestResult getAllBorrowById(@Valid @RequestBody Map<String, Object> reqMap) {
        String userBorrowNum = reqMap.get("userBorrowNum").toString();
        String userBorrowBookNum = reqMap.get("userBorrowBookNum").toString();
        String userId = userInfoService.getUserId(userBorrowNum);
        int bookNum = bookInfoService.getBookIdById(Integer.valueOf(userBorrowBookNum));
        if (bookNum == 0 || userId == null) {
            return generator.getSuccessResult("图书不存在或者用户不存在", null);
        }
        int status = bookInfoService.getBookStstusById(Integer.valueOf(userBorrowBookNum));
        if (status == 1) {
            return generator.getResult("图书已被借阅", null, null);
        }
        return generator.getResult("验证成功", borrowRecordsService.findContentByIdAndSatus(Integer.valueOf(userId)), userInfoService.findById(Integer.valueOf(userId)));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/sureBorrow", method = RequestMethod.POST)
    public RestResult borrow(@Valid @RequestBody Map<String, Object> reqMap) {
        String userBorrowNum = reqMap.get("userBorrowNum").toString();
        String userBorrowBookNum = reqMap.get("userBorrowBookNum").toString();
        int userTime = Integer.parseInt(reqMap.get("userTime").toString());
        String userId = userInfoService.getUserId(userBorrowNum);
        String bookName = bookInfoService.getBookNameById(Integer.valueOf(userBorrowBookNum));
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, userTime);//计算30天后的时间
        BorrowRecords borrowRecords = new BorrowRecords();
        borrowRecords.setBookInfoId(Long.parseLong(userBorrowBookNum));
        borrowRecords.setBookName(bookName);
        borrowRecords.setBorrowTime(date);
        borrowRecords.setShouldTime(c.getTime());
        borrowRecords.setStatus(1);
        borrowRecords.setUserId(Long.parseLong(userId));
        borrowRecordsService.save(borrowRecords);
        bookInfoService.updateStatusById(Integer.valueOf(userBorrowBookNum), 1);
        return generator.getSuccessResult("借阅成功", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/returnBook", method = RequestMethod.POST)
    public RestResult returnBook(@Valid @RequestBody Map<String, Object> reqMap) {
        String userBorrowBookNum = reqMap.get("returnBookNum").toString();
        int a = bookInfoService.getBookStstusById(Integer.valueOf(userBorrowBookNum));
        bookInfoService.updateStatusById(Integer.valueOf(userBorrowBookNum), 0);
        Date date = new Date();
        borrowRecordsService.updateStatusByBookId(Integer.valueOf(userBorrowBookNum),date);
        if (a == 0) {
            return generator.getSuccessResult("1", null);
        }
        return generator.getSuccessResult("0", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public RestResult addBook(@Valid @RequestBody Map<String, Object> reqMap) throws Exception{
        String addBookNum = reqMap.get("addBookNum").toString();
        String addBookName = reqMap.get("addBookName").toString();
        String addBookAuthor = reqMap.get("addBookAuthor").toString();
        String addBookTranslator = reqMap.get("addBookTranslator").toString();
        float addBookPrice = Float.parseFloat(reqMap.get("addBookPrice").toString());
        String addBookISBN = reqMap.get("addBookISBN").toString();
        String addBookPublish = reqMap.get("addBookPublish").toString();
        String date = reqMap.get("addPublishTime").toString();
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date addPublishTime = format.parse(date );
        Date curdate = new Date();
        String addBookMen = reqMap.get("addBookMen").toString();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(Integer.valueOf(addBookNum));
        bookInfo.setBookName(addBookName);
        bookInfo.setAuthor(addBookAuthor);
        bookInfo.setTranslator(addBookTranslator);
        bookInfo.setPrice(addBookPrice);
        bookInfo.setISBNCode(addBookISBN);
        bookInfo.setPublishCompany(addBookPublish);
        bookInfo.setComeUpTime(addPublishTime);
        bookInfo.setEnteringMen(addBookMen);
        bookInfo.setEnteringDate(curdate);
        bookInfo.setStatus(0);
        int a = bookInfoService.getBookIdById(Integer.valueOf(addBookNum));
        if(a!=0){
            return generator.getSuccessResult("1", null);
        }
        bookInfoService.save(bookInfo);
        return generator.getSuccessResult("0", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/sureBook", method = RequestMethod.POST)
    public RestResult sureBook(@Valid @RequestBody Map<String, Object> reqMap) {
        String deleteBookNum = reqMap.get("deleteBookNum").toString();
        return generator.getSuccessResult("验证成功", bookInfoService.getBookById(Integer.valueOf(deleteBookNum)));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public RestResult deleteBook(@Valid @RequestBody Map<String, Object> reqMap) {
        String deleteBookNum = reqMap.get("deleteBookNum").toString();
        int a = bookInfoService.getBookIdById(Integer.valueOf(deleteBookNum));
        if(a==0){
            return generator.getSuccessResult("1", null);
        }
        bookInfoService.deleteBookById(Integer.valueOf(deleteBookNum));
        return generator.getSuccessResult("0", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public RestResult updateBook(@Valid @RequestBody Map<String, Object> reqMap) throws Exception{
        String changeBookNum = reqMap.get("changeBookNum").toString();
        String changeBookName = reqMap.get("changeBookName").toString();
        String changeBookAuthor = reqMap.get("changeBookAuthor").toString();
        String changeBookTranslator = reqMap.get("changeBookTranslator").toString();
        float changeBookPrice = Float.parseFloat(reqMap.get("changeBookPrice").toString());
        String changeBookISBN = reqMap.get("changeBookISBN").toString();
        String changeBookPublish = reqMap.get("changeBookPublish").toString();
        String date = reqMap.get("changeBookPublishTime").toString();
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date changeBookPublishTime = format.parse(date );
        String changeBookEnterMen = reqMap.get("changeBookEnterMen").toString();
        int a = bookInfoService.getBookIdById(Integer.valueOf(changeBookNum));
        if(a!=0){
            bookInfoService.updateBookById(Integer.valueOf(changeBookNum),changeBookName,changeBookAuthor,changeBookTranslator,changeBookPrice,changeBookISBN,changeBookPublish,changeBookPublishTime,changeBookEnterMen);
            return generator.getSuccessResult("1", null);
        }
        return generator.getSuccessResult("0", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/searchRecords", method = RequestMethod.POST)
    public RestResult searchRecords(@Valid @RequestBody Map<String, Object> reqMap) {
        String keyword = reqMap.get("keyword").toString();
        return generator.getSuccessResult("0", borrowRecordsService.searchRecords(keyword));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/searchBookInfo", method = RequestMethod.POST)
    public RestResult searchBookInfo(@Valid @RequestBody Map<String, Object> reqMap) {
        String keyword = reqMap.get("keyword").toString();
        return generator.getSuccessResult("0", bookInfoService.searchBookInfo(keyword));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getAdmin", method = RequestMethod.POST)
    public RestResult getAdminById(@Valid @RequestBody Map<String, Object> reqMap) {
        String adminId = reqMap.get("adminId").toString();
        return generator.getSuccessResult("0", bookAdminService.getAdminById(Integer.valueOf(adminId)));
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public RestResult updateInfo(@Valid @RequestBody Map<String, Object> reqMap) {
        String adminId = reqMap.get("adminId").toString();
        String password = reqMap.get("password").toString();
        String newPassword = reqMap.get("newPassword").toString();
        String phone = reqMap.get("phone").toString();
        String email = reqMap.get("email").toString();
        String a = bookAdminService.getPasswordById(Integer.valueOf(adminId));
        if(a.equals(MD5.md5Password(password))){
            bookAdminService.updateInfo(Integer.valueOf(adminId),MD5.md5Password(newPassword),phone,email);
            return generator.getSuccessResult("1",null);
        }
        else{
            return generator.getSuccessResult("0",null);
        }
    }

    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/searchBookInfoAndStatus", method = RequestMethod.POST)
    public RestResult searchBookInfoAndStatus(@Valid @RequestBody Map<String, Object> reqMap) {
        String keyword = reqMap.get("keyword").toString();
        return generator.getSuccessResult("0", bookInfoService.searchBookInfoAndStatus(keyword));
    }
}
