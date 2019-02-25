<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>博客-页尾</title>
<link href="<%=basePath%>/css/default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="footer">
	<p id="legal">&copy;2010 All Rights Reserved.</p>
	<p id="legal">Front Template by <a href="http://www.freecsstemplates.org/">freecsstemplates</a></p>
	<p id="links">Powered by <a href="http://blog.csdn.net/t0nsha/">t0nsha</a></p>
</div>
</body>
</html>