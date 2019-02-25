package org.xblo.bean;

public class ArticleCommentBean {
	private int articleCommentId = -1;
	private int articleId = -1;
	private String articleCommentDate = "";
	private String articleCommentUser = "";
	private String articleCommentEmail = ""; // 不显示，仅用来发送回复通知
	private String articleCommentContent = "";

	public void setArticleCommentId(int articleCommentId) {
		this.articleCommentId = articleCommentId;
	}

	public int getArticleCommentId() {
		return articleCommentId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setCommentContent(String articleCommentContent) {
		this.articleCommentContent = articleCommentContent;
	}

	public String getCommentContent() {
		return articleCommentContent;
	}

	public void setArticleCommentDate(String articleCommentDate) {
		this.articleCommentDate = articleCommentDate;
	}

	public String getArticleCommentDate() {
		return articleCommentDate;
	}

	public void setArticleCommentUser(String articleCommentUser) {
		this.articleCommentUser = articleCommentUser;
	}

	public String getArticleCommentUser() {
		return articleCommentUser;
	}

	public void setArticleCommentEmail(String articleCommentEmail) {
		this.articleCommentEmail = articleCommentEmail;
	}

	public String getArticleCommentEmail() {
		return articleCommentEmail;
	}

}
