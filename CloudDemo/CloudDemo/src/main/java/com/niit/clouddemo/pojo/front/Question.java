package com.niit.clouddemo.pojo.front;

import java.util.Date;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:03
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class Question {

    /**
     *  问题ID
     * */
    private String questionId;
    /**
     *  问题标题
     * */
    private String title;
    /**
     *  问题描述
     * */
    private String desc;
    /**
     *  问题创建时间
     * */
    private Date createtime;
    /**
     *  问题更新时间
     * */
    private Date updatetime;
    /**
     *  问题热门指数
     * */
    private Integer hotIndex;
    /**
     *  问题标签
     * */
    private String tag;
    //---------------------------------------------------------------
    /**
     *  所属用户
     * */
    private String userId;

    /**
     * 问题所提出的用户
     * */
    private User user;

    /**
     *  问题关联的最优回答
     * */
    private Answer answer;
    /**
     *  问题关联的回答
     * */
    private List<Answer> answerList;

    /**
     *  TODO: 分页参数 ---- 当前页数 🔽
     * */
    private int currPage;

    /**
     * TODO: 每页记录数 🔽
     */
    private int pageSize;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Integer getHotIndex() {
        return hotIndex;
    }

    public void setHotIndex(Integer hotIndex) {
        this.hotIndex = hotIndex;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
