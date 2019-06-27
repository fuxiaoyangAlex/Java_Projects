package com.niit.clouddemo.pojo.background;

import java.util.Date;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 20:50
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class Admin {

    private Integer adminid;
    private String  headimg;
    private String  password;
    private String name;
    private String email;
    private String phone;
    private String qq;
    private String address;
    private String createtime;
    private String updatetime;
    private String gender;
    private Integer adminflag;

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAdminflag() {
        return adminflag;
    }

    public void setAdminflag(Integer adminflag) {
        this.adminflag = adminflag;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", headimg='" + headimg + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", address='" + address + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", gender='" + gender + '\'' +
                ", adminflag=" + adminflag +
                '}';
    }
}
