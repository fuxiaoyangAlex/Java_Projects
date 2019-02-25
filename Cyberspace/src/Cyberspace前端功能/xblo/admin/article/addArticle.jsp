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
	XbloUserBean validXbloUserBean = (XbloUserBean) session
			.getAttribute("validXbloUserBean");
%>

<body id="page">
<h2>发表文章</h2>
<form action="<%=basePath%>/ActionServlet?action=addArticle"
	method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<input type="hidden" name="createUserId"
		value="<%=validXbloUserBean.getXbloUserId()%>">
	</input>
	<tr>
		<th>标题：</th>
		<td>[<select name="articleTypeId">
			<%
				ArticleTypeDao articleTypeDao = new ArticleTypeDao();
				List<ArticleTypeBean> articleTypeList = articleTypeDao.queryAll();
				if (articleTypeList.size() > 0) {
					for (int i = 0; i < articleTypeList.size(); i++) {
						ArticleTypeBean articleTypeBean = (ArticleTypeBean) articleTypeList
								.get(i);
			%>
			<option value="<%=articleTypeBean.getArticleTypeId()%>"><%=articleTypeBean.getArticleTypeName()%></option>
			<%
				}
				}
			%>
		</select>]<input type="text" name="articleTitle" size="50"></input></td>
	</tr>
	<tr>
		<th>简介：</th>
		<td><textarea name="articleSummary" rows="5" cols="80"></textarea></td>
	</tr>
	<tr>
		<th>内容：</th>
		<td><textarea name="articleContent" rows="20" cols="80"></textarea></td>
	</tr>

	<tr>
		<th>&nbsp;</th>
		<td><input type="submit" class="bt" value="提交"></input></td>
	</tr>
</table>
</form>
</body>
</html>
