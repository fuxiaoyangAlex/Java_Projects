package com.niit.clouddemo.pojo.front;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 9:05
 * @description：POJO类
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class User implements Serializable {


    private static final long serialVersionUID = -4917509537361130427L;

    /**
     * 用户表主键ID
     * */
    private String userid;
    /**
     * 用户手机号码
     * */
    private String phone;
    /**
     * 用户名
     * */
    private String username;

    /**
     * 用户邮箱
     * */
    private String email;

    /**
     * 用户密码
     * */
    private String password;


    /**
     * 用户个性签名
     * */
    private String signature;
    /**
     * 用户头像地址
     * */
    private String headimg;
    /**
     * 用户性别
     * */
    private String gender;
    /**
     * 用户注册时间
     * */
    private Date createtime;
    /**
     * 用户更新时间
     * */
    private Date updatetime;
    /**
     * 是否被锁定 0:表示没有 1：表示锁定
     * */
    private Integer lockedflag;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getLockedflag() {
        return lockedflag;
    }

    public void setLockedflag(Integer lockedflag) {
        this.lockedflag = lockedflag;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", signature='" + signature + '\'' +
                ", headimg='" + headimg + '\'' +
                ", gender='" + gender + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", lockedflag=" + lockedflag +
                '}';
    }
}
