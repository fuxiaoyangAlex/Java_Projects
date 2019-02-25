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
	ArticleTypeDao articleTypeDao = new ArticleTypeDao();
	List<ArticleTypeBean> articleTypeList = articleTypeDao.queryAll();
%>

<body id="page">
<h2>类别列表</h2>
<form action="<%=basePath%>/ActionServlet?action=addArticleType"
	method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>
		<th>类别：</th>
		<td><input type="text" name="articleTypeName" size="20"></input></td>
	</tr>
	<tr>
		<th>描述：</th>
		<td><input type="text" name="articleTypeDesc" size="50"></input></td>
	</tr>
	<tr>
		<th></th>
		<td><input type="submit" class="bt" value="新增类别" /></td>
	</tr>

	<%
		for (Iterator<ArticleTypeBean> iter = articleTypeList.iterator(); iter
				.hasNext();) {
			ArticleTypeBean articleTypeBean = (ArticleTypeBean) iter.next();
	%>

	<tr>
		<th>类别：</th>
		<td><%=articleTypeBean.getArticleTypeName()%></td>
	</tr>
	<tr>
		<th>描述：</th>
		<td><%=articleTypeBean.getArticleTypeDesc()%></td>
	</tr>
	<tr>
		<th>操作：</th>
		<td><a
			href="<%=basePath%>/ActionServlet?action=updArticleType&articleTypeId=<%=articleTypeBean.getArticleTypeId()%>">修改</a>
		| <a
			href="<%=basePath%>/ActionServlet?action=delArticleType&articleTypeId=<%=articleTypeBean.getArticleTypeId()%>">删除</a></td>
	</tr>
	<%
		}
	%>

</table>
</form>
</body>
</html>
