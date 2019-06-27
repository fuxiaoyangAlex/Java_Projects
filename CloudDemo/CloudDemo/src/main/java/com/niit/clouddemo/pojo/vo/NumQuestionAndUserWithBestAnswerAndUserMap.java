package com.niit.clouddemo.pojo.vo;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 21:18
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

public class NumQuestionAndUserWithBestAnswerAndUserMap {

    private String questionId;
    private String title;
    private Integer answerId;
    private String content;
    private Integer hotstar;
    private String username;
    private String userId;
    private String headimg;
    private String signature;



    /**
     *  总页数
     * */


    private Integer totalnum;

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHotstar() {
        return hotstar;
    }

    public void setHotstar(Integer hotstar) {
        this.hotstar = hotstar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "NumQuestionAndUserWithBestAnswerAndUserMap{" +
                "questionId='" + questionId + '\'' +
                ", title='" + title + '\'' +
                ", answerId=" + answerId +
                ", content='" + content + '\'' +
                ", hotstar=" + hotstar +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", headimg='" + headimg + '\'' +
                ", signature='" + signature + '\'' +
                ", totalnum=" + totalnum +
                '}';
    }
}
