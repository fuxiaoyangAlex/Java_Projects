package com.niit.clouddemo.pojo.front;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:18
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class ArticleCommentReply {

    private Integer articleCommentReplyId;
    private String content;
    private String timestamp;
    private Integer articleCommentId;
    private String userId;

    public Integer getArticleCommentReplyId() {
        return articleCommentReplyId;
    }

    public void setArticleCommentReplyId(Integer articleCommentReplyId) {
        this.articleCommentReplyId = articleCommentReplyId;
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

    public Integer getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Integer articleCommentId) {
        this.articleCommentId = articleCommentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
