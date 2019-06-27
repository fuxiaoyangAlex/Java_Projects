package com.niit.clouddemo.pojo.front;

import java.util.Date;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:41
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class Answer {

    /**
     *  回答ID
     * */
    private Integer answerId;
    /**
     *  回答内容
     * */
    private String content;
    /**
     *  回答时间
     * */
    private Date createtime;
    /**
     * 点赞👍数
     * */
    private Integer hotStar;
    //----------------------------------------------------
    /**
     *  回答关联的问题ID
     * */
    private String questionId;

    /**
     *  回答管理的用户ID
     * */
    private String userid;

    /**
     *  该回答用户
     * */
    private User user;

    public String getUserid() {
        return userid;
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getHotStar() {
        return hotStar;
    }

    public void setHotStar(Integer hotStar) {
        this.hotStar = hotStar;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", hotStar=" + hotStar +
                ", questionId='" + questionId + '\'' +
                ", userid='" + userid + '\'' +
                ", user=" + user +
                '}';
    }
}
