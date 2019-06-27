package com.niit.clouddemo.pojo.front;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 9:12
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO: 分页查询分页信息封装POJO
 */
public class PageInfo {

    /**
     *  第几页
     * */
    private Integer pageIndex;
    /**
     *  每页显示数量
     * */
    private Integer pageSize;
    /**
     *  每页开始位置
     * */
    private Integer start;
    /**
     *  业务记录ID
     * */
    private Integer id;
    /**
     * 用户ID
     * */
    private Integer userid;

    /***
     * 获取最新的问题
     * */
    private List<Question> questionList;

    /**
     *  获取的每个问题对应最好得答案
     * */
    private Answer answer;

    public PageInfo(){

    }

    public PageInfo(Integer pageIndex, Integer pageSize, Integer start, Integer id, Integer userid, List<Question> questionList, Answer answer) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.start = start;
        this.id = id;
        this.userid = userid;
        this.questionList = questionList;
        this.answer = answer;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
