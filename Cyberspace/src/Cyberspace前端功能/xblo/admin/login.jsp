<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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

<body>
<form action="ActionServlet?action=login" id="loginForm" method="post">
<h3>管理员登录</h3>
<label for="xbloUsername"><span>用户名：</span><input name="xbloUsername"
	type="text" /></label> <label for="xbloPassword"><span>密码：</span><input
	name="xbloPassword" type="password" /></label> <label><input name=""
	type="submit" class="bt" value="确定" /> </label></form>
</body>
</html>
