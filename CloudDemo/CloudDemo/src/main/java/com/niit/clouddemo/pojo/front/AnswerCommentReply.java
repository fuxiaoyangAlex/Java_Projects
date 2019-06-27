package com.niit.clouddemo.pojo.front;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:38
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class AnswerCommentReply {

    private Integer answerCommentReplyId;
    private String content;
    private String timestamp;
    private Integer answerCommentId;
    private String userId;

    public Integer getAnswerCommentReplyId() {
        return answerCommentReplyId;
    }

    public void setAnswerCommentReplyId(Integer answerCommentReplyId) {
        this.answerCommentReplyId = answerCommentReplyId;
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

    public Integer getAnswerCommentId() {
        return answerCommentId;
    }

    public void setAnswerCommentId(Integer answerCommentId) {
        this.answerCommentId = answerCommentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
