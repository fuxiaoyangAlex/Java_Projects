<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
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
<script type="text/javascript" src="js.js"></script>
</head>

<%
	XbloUserBean validXbloUserBean = (XbloUserBean) session
			.getAttribute("validXbloUserBean");
%>

<body id="index">
<h1>欢迎你，<%=validXbloUserBean.getXbloUsername()%></h1>
<ul id="globalNav">
	<li><a href="<%=basePath%>/admin/siteInfo.jsp" target="frameBord">站点信息</a></li>
	<li><a href="<%=basePath%>/admin/article/addArticle.jsp"
		target="frameBord">发表文章</a></li>
	<li><a href="<%=basePath%>/admin/article/mgrArticle.jsp"
		target="frameBord">管理文章</a></li>
	<li><a href="<%=basePath%>/admin/articleType/mgrArticleType.jsp"
		target="frameBord">管理类别</a></li>
	<li><a href="<%=basePath%>/admin/xbloLink/mgrXbloLink.jsp"
		target="frameBord">管理链接</a></li>
	<li><a href="<%=basePath%>/admin/xbloUser/mgrXbloUser.jsp"
		target="frameBord">管理用户</a></li>
	<li><a href="ActionServlet?action=logout">退出</a></li>
</ul>
<iframe id="frameBord" name="frameBord" frameborder="0" width="100%"
	height="100%" src="<%=basePath%>/admin/siteInfo.jsp"></iframe>
</body>
</html>
