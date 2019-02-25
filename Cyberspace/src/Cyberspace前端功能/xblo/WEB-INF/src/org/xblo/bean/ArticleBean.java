package org.xblo.bean;

public class ArticleBean {

	// DB字段
	private int articleId = -1; // 文章ID
	private int articleTypeId = -1; // 类型ID
	private String articleTitle = ""; // 文章标题
	private String articleSummary = ""; // 文章摘要
	private String articleContent = ""; // 文章内容
	private String createDate = ""; // 创建时间
	private int visitCount = 0; // 访问次数
	private int createUserId = -1; // 用户ID

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleTypeId(int articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public int getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleSummary(String articleSummary) {
		this.articleSummary = articleSummary;
	}

	public String getArticleSummary() {
		return articleSummary;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public int getCreateUserId() {
		return createUserId;
	}

}
