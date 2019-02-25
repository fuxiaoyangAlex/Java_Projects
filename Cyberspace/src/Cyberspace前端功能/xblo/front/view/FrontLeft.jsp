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
	ArticleDao articleDao = new ArticleDao();
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>博客-侧栏</title>
<link href="<%=basePath%>/css/default.css" rel="stylesheet"
	type="text/css" media="screen" />
</head>
<body>
<div id="sidebar">
<ul>
	<li id="search">
	<h2>搜索</h2>
	<!-- Google站内搜索开始 -->
	<form method=get action="http://www.google.com/search">
	<fieldset><input type=text name=q> <input
		type=submit name=btnG value="Google"> <input type=hidden
		name=ie value=UTF-8> <input type=hidden name=oe value=UTF-8>
	<input type=hidden name=hl value=zh-CN> <input type=hidden
		name=domains value="<%=basePath%>"> <input type=hidden
		name=sitesearch value="<%=basePath%>"></fieldset>
	</form>
	<!-- Google 站内搜索结束 --></li>
	<li>
	<h2>排行</h2>
	<ul>
		<%
			List<ArticleBean> articleListTop = articleDao
					.queryTopOnVisitCount(10);
			if (articleListTop.size() > 0) {
				for (int i = 0; i < articleListTop.size(); i++) {
					ArticleBean articleBean = (ArticleBean) articleListTop
							.get(i);
		%>
		<li><a
			href="ActionServlet?action=viewArticle&articleId=<%=articleBean.getArticleId()%>"><%=articleBean.getArticleTitle()%></a>(<%=articleBean.getVisitCount()%>)</li>
		<%
			}
			}
		%>
	</ul>
	</li>
	<li>
	<h2>分类</h2>
	<ul>
		<%
			ArticleTypeDao articleTypeDao = new ArticleTypeDao();
			List<ArticleTypeBean> articleTypeList = articleTypeDao.queryAll();
			if (articleTypeList.size() > 0) {
				for (int i = 0; i < articleTypeList.size(); i++) {
					ArticleTypeBean articleTypeBean = (ArticleTypeBean) articleTypeList
							.get(i);
					List<ArticleBean> articleList = articleDao
							.queryByArticleTypeId(articleTypeBean
									.getArticleTypeId());
		%>
		<li><a
			href="IndexServlet?articleTypeId=<%=articleTypeBean.getArticleTypeId()%>"><%=articleTypeBean.getArticleTypeName()%></a>(<%=articleList.size()%>)</li>
		<%
			}
			}
		%>
	</ul>
	</li>
	<li>
	<h2>链接</h2>
	<ul>
		<%
			XbloLinkDao xbloLinkDao = new XbloLinkDao();
			List<XbloLinkBean> xbloLinkList = xbloLinkDao.queryAll();
			if (xbloLinkList.size() > 0) {
				for (int i = 0; i < xbloLinkList.size(); i++) {
					XbloLinkBean xbloLinkBean = (XbloLinkBean) xbloLinkList
							.get(i);
		%>
		<li><a href="http://<%=xbloLinkBean.getXbloLinkUrl()%>"
			target=_blank><%=xbloLinkBean.getXbloLinkName()%></a></li>
		<%
			}
			}
		%>
	</ul>
	</li>
</ul>
</div>
</body>
</html>