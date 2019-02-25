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
	XbloUserDao xbloUserDao = new XbloUserDao();
	List<XbloUserBean> xbloUserList = xbloUserDao.queryAll();
%>

<body id="page">
<h2>链接列表</h2>
<form action="<%=basePath%>/ActionServlet?action=addXbloUser"
	method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>
		<th>用户名：</th>
		<td><input type="text" name="xbloUsername" size="20"></input></td>
	</tr>
	<tr>
		<th>密码：</th>
		<td><input type="text" name="xbloPassword" size="50"></input></td>
	</tr>
	<tr>
		<th></th>
		<td><input type="submit" class="bt" value="新增用户" /></td>
	</tr>

	<%
		for (Iterator<XbloUserBean> iter = xbloUserList.iterator(); iter
				.hasNext();) {
			XbloUserBean xbloUserBean = (XbloUserBean) iter.next();
	%>

	<tr>
		<th>用户名：</th>
		<td><%=xbloUserBean.getXbloUsername()%></td>
	</tr>
	<tr>
		<th>操作：</th>
		<td><a
			href="<%=basePath%>/ActionServlet?action=updXbloUser&xbloUserId=<%=xbloUserBean.getXbloUserId()%>">修改密码</a>
		| <a
			href="<%=basePath%>/ActionServlet?action=delXbloUser&xbloUserId=<%=xbloUserBean.getXbloUserId()%>">删除用户</a></td>
	</tr>
	<%
		}
	%>

</table>
</form>
</body>
</html>
