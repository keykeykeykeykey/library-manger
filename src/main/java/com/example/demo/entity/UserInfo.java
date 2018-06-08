package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "密码不允许为空")
    private long userId;

    @NotNull(message = "院系不允许为空")
    private String departments;

    @NotNull(message = "专业不允许为空")
    private String major;

    private String phone;

    private String email;

    @NotNull(message = "可借最大数量不允许为空")
    private Integer maxNum;

    @NotNull(message = "可借期限不允许为空")
    private int time;

    @NotNull(message = "在借数量不允许为空")
    private String lendedNum;

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

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLendedNum() {
        return lendedNum;
    }

    public void setLendedNum(String lendedNum) {
        this.lendedNum = lendedNum;
    }
}
