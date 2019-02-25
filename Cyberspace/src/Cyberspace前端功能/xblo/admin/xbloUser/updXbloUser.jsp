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
<script type="text/javascript" src="<%=basePath%>/admin/js.js"></script>
</head>

<%
	XbloUserBean xbloUserBean = (XbloUserBean) request
			.getAttribute("xbloUserBean");
	XbloUserBean validXbloUserBean = (XbloUserBean) session
			.getAttribute("validXbloUserBean");
%>

<body id="page">
<h2>修改密码</h2>
<form action="<%=basePath%>/ActionServlet?action=updXbloUser"
	method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<input type="hidden" name="updXbloUser" value="Y">
	</input>
	<input type="hidden" name="xbloUserId"
		value="<%=xbloUserBean.getXbloUserId()%>">
	</input>
	<tr>
		<th>用户名：</th>
		<td><input type="text" name="xbloUsername" size="50"
			readonly="readonly" value="<%=xbloUserBean.getXbloUsername()%>"></input></td>
	</tr>
	<tr>
		<th>新密码：</th>
		<td><input type="password" id="xbloPassword1" name="xbloPassword"
			size="50"></input></td>
	</tr>
	<tr>
		<th>重复新密码：</th>
		<td><input type="password" id="xbloPassword2" name="xbloPassword"
			size="50"></input></td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td><input type="submit" class="bt" value="提交"
			onclick="return chkPwdEql()"></input></td>
	</tr>
</table>
</form>
</body>
</html>
