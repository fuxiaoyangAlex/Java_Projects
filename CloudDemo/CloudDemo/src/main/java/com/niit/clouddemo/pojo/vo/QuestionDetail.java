package com.niit.clouddemo.pojo.vo;

import java.util.Date;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/23 21:48
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class QuestionDetail {

    private String questionid;
    private String title;
    private String desc;
    private Date createtime;
    private Integer hotindex;
    private String tag;
    private String userid;
    private String username;
    private String signature;
    private String headimg;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getHotindex() {
        return hotindex;
    }

    public void setHotindex(Integer hotindex) {
        this.hotindex = hotindex;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
        return "QuestionDetail{" +
                "questionid='" + questionid + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", createtime=" + createtime +
                ", hotindex=" + hotindex +
                ", tag='" + tag + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", signature='" + signature + '\'' +
                ", headimg='" + headimg + '\'' +
                '}';
    }
}

//mysql> SELECT q.question_id,q.title,q.createtime,q.hotindex,q.tag
//        -> ,u.user_id,u.phone,u.username,u.signature,u.headimg
//        -> FROM cd_question q, cd_user u
//        -> WHERE q.user_id = u.user_id
//        -> AND q.question_id LIKE CONCAT ('%' ,'17528602152962','%');