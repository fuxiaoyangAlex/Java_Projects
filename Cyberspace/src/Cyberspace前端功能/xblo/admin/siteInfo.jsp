<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.xblo.bean.*"%>
<%@ page import="org.xblo.dao.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理界面</title>
<link href="<%=basePath%>/admin/style.css" rel="stylesheet"
	type="text/css" />
</head>

<%
	ArticleDao articleDao = new ArticleDao();
	List<ArticleBean> articleList = articleDao
			.queryByArticleTypeId(ArticleDao.ALLTYPE);
	ArticleTypeDao articleTypeDao = new ArticleTypeDao();
	List<ArticleTypeBean> articleTypeList = articleTypeDao.queryAll();

	List<ArticleBean> searchArticleList = (List<ArticleBean>) request
			.getAttribute("searchArticleList");
	int searchArticleCount = 0;
	if (searchArticleList == null)
		searchArticleCount = 0;
	else
		searchArticleCount = searchArticleList.size();
%>

<body id="page">
<h2>站点信息</h2>

<form action="<%=basePath%>/ActionServlet?action=searchArticle"
	method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<th>站点名称：</th>
		<td>Xblo</td>
	</tr>
	<tr>
		<th>文章数量：</th>
		<td><strong><%=articleList.size()%></strong>篇包含在<strong><%=articleTypeList.size()%></strong>个分类中</td>
	</tr>
	<tr>
		<th>搜索结果：</th>
		<td><%=searchArticleCount%>篇</td>
	</tr>
	<tr>
		<th>关键字：</th>
		<td><input type="text" name="keyword" /> <input type="submit"
			class="bt" value="搜索文章" /></td>
	</tr>

	<%
		if (searchArticleCount > 0) {
			for (Iterator<ArticleBean> iter = searchArticleList.iterator(); iter
					.hasNext();) {
				ArticleBean articleBean = (ArticleBean) iter.next();
	%>
	<tr>
		<th>标题：</th>
		<td><a
			href="ActionServlet?action=viewArticle&articleId=<%=articleBean.getArticleId()%>"><%=articleBean.getArticleTitle()%></a></td>
	</tr>
	<tr>
		<th>简介：</th>
		<td><%=articleBean.getArticleSummary()%></td>
	</tr>
	<%
		}
		}
	%>
</table>
</form>
</body>
</html>
