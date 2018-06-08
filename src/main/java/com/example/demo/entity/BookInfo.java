package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "bookinfo")
public class BookInfo {
    @Id
    private Integer id;

    @NotNull(message = "书名不允许为空")
    private String bookName;

    @NotNull(message = "作者不允许为空")
    private String author;

    private String translator;

    @NotNull(message = "价格不允许为空")
    private Float price;

    @NotNull(message = "ISBN不允许为空")
    private String ISBNCode;

    @NotNull(message = "出版日期不允许为空")
    private Date comeUpTime;

    @NotNull(message = "出版社不允许为空")
    private String publishCompany;

    @NotNull(message = "图书状态不允许为空")
    private Integer status;

    @NotNull(message = "入库者不允许为空")
    private String enteringMen;

    @NotNull(message = "入库日期不允许为空")
    private Date enteringDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getISBNCode() {
        return ISBNCode;
    }

    public void setISBNCode(String ISBNCode) {
        this.ISBNCode = ISBNCode;
    }

    public Date getComeUpTime() {
        return comeUpTime;
    }

    public void setComeUpTime(Date comeUpTime) {
        this.comeUpTime = comeUpTime;
    }

    public String getPublishCompany() {
        return publishCompany;
    }

    public void setPublishCompany(String publishCompany) {
        this.publishCompany = publishCompany;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEnteringMen() {
        return enteringMen;
    }

    public void setEnteringMen(String enteringMen) {
        this.enteringMen = enteringMen;
    }

    public Date getEnteringDate() {
        return enteringDate;
    }

    public void setEnteringDate(Date enteringDate) {
        this.enteringDate = enteringDate;
    }
}
