package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "borrowRecords")
public class BorrowRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "用户ID不允许为空")
    private long userId;

    @NotNull(message = "图书ID不允许为空")
    private long bookInfoId;

    @NotNull(message = "书名不允许为空")
    private String bookName;

    @NotNull(message = "借书时间不允许为空")
    private Date borrowTime;

    @NotNull(message = "预还书时间不允许为空")
    private Date shouldTime;

    private Date returnTime;

    @NotNull(message = "借阅状态不允许为空")
    private Integer status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(long bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getShouldTime() {
        return shouldTime;
    }

    public void setShouldTime(Date shouldTime) {
        this.shouldTime = shouldTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
