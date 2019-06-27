package com.niit.clouddemo.pojo.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/23 23:15
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class AnswerInfoWithUserInfo {


    private String content;
    private Timestamp createtime;
    private Integer hotstar;
    private String userid;
    private String username;
    private String signature;
    private String headimg;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Integer getHotstar() {
        return hotstar;
    }

    public void setHotstar(Integer hotstar) {
        this.hotstar = hotstar;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "AnswerInfoWithUserInfo{" +
                "content='" + content + '\'' +
                ", createtime=" + createtime +
                ", hotstar=" + hotstar +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", signature='" + signature + '\'' +
                ", headimg='" + headimg + '\'' +
                '}';
    }
}
