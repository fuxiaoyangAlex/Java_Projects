package com.niit.clouddemo.pojo.front;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:35
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class ArticleComment {

    private Integer articleCommentId;
    private String content;
    private String timestamp;
    private String articleId;
    private String userId;

    public Integer getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Integer articleCommentId) {
        this.articleCommentId = articleCommentId;
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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
