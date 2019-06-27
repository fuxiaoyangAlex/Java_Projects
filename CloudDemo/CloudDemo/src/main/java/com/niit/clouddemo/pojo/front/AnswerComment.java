package com.niit.clouddemo.pojo.front;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:39
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class AnswerComment {

    private Integer answerCommentId;
    private String content;
    private String timestamp;
    private String userId;
    private Integer answerId;

    public Integer getAnswerCommentId() {
        return answerCommentId;
    }

    public void setAnswerCommentId(Integer answerCommentId) {
        this.answerCommentId = answerCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
}
