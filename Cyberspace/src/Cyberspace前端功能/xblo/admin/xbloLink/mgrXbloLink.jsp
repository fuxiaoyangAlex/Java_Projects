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
	XbloLinkDao xbloLinkDao = new XbloLinkDao();
	List<XbloLinkBean> xbloLinkList = xbloLinkDao.queryAll();
%>

<body id="page">
<h2>链接列表</h2>
<form action="<%=basePath%>/ActionServlet?action=addXbloLink"
	method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>
		<th>名称：</th>
		<td><input type="text" name="xbloLinkName" size="20"></input></td>
	</tr>
	<tr>
		<th>URL：</th>
		<td><input type="text" name="xbloLinkUrl" size="50"></input></td>
	</tr>
	<tr>
		<th></th>
		<td><input type="submit" class="bt" value="新增链接" /></td>
	</tr>

	<%
		for (Iterator<XbloLinkBean> iter = xbloLinkList.iterator(); iter
				.hasNext();) {
			XbloLinkBean xbloLinkBean = (XbloLinkBean) iter.next();
	%>

	<tr>
		<th>名称：</th>
		<td><%=xbloLinkBean.getXbloLinkName()%></td>
	</tr>
	<tr>
		<th>URL：</th>
		<td><%=xbloLinkBean.getXbloLinkUrl()%></td>
	</tr>
	<tr>
		<th>操作：</th>
		<td><a
			href="<%=basePath%>/ActionServlet?action=updXbloLink&xbloLinkId=<%=xbloLinkBean.getXbloLinkId()%>">修改</a>
		| <a
			href="<%=basePath%>/ActionServlet?action=delXbloLink&xbloLinkId=<%=xbloLinkBean.getXbloLinkId()%>">删除</a></td>
	</tr>
	<%
		}
	%>

</table>
</form>
</body>
</html>
