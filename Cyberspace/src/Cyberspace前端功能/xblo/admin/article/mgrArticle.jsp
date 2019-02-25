<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xblo.bean.*"%>
<%@ page import="org.xblo.dao.*"%>
<%@ page import="java.util.Iterator"%>

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
%>

<body id="page">
<h2>文章列表</h2>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<%
		for (Iterator<ArticleBean> iter = articleList.iterator(); iter
				.hasNext();) {
			ArticleBean articleBean = (ArticleBean) iter.next();
			ArticleTypeBean articleTypeBean = articleTypeDao
					.query(articleBean.getArticleTypeId());
			String articleTypeName = null;
			if (articleTypeBean == null)
				articleTypeName = "未分类";
			else
				articleTypeName=articleTypeBean.getArticleTypeName();
	%>

	<tr>
		<th>标题：</th>
		<td>[<%=articleTypeName%>]<%=articleBean.getArticleTitle()%></td>
	</tr>
	<tr>
		<th>简介：</th>
		<td><textarea readonly="readonly"><%=articleBean.getArticleSummary()%></textarea></td>
	</tr>
	<tr>
		<th>操作：</th>
		<td><a
			href="<%=basePath%>/ActionServlet?action=viewArticle&articleId=<%=articleBean.getArticleId()%>">查看</a>
		| <a
			href="<%=basePath%>/ActionServlet?action=updArticle&articleId=<%=articleBean.getArticleId()%>">修改</a>
		| <a
			href="<%=basePath%>/ActionServlet?action=delArticle&articleId=<%=articleBean.getArticleId()%>">删除</a></td>

	</tr>
	<%
		}
	%>

</table>
</body>
</html>
