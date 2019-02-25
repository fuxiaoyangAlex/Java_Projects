<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>博客-页头</title>
<link href="<%=basePath%>/css/default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<!-- start header -->
<div id="feeds"><a href="#" id="entries-rss">Entries (RSS)</a> <b>&nbsp;|&nbsp;</b> <a href="#" id="comments-rss">Comments (RSS)</a></div>
<hr />
<div id="logo">
	<h1><a href="index.jsp">Xblo</a></h1>
	<p><a href="http://blog.csdn.net/t0nsha" target=_blank>珍惜每一天</a></p>
</div>
<hr />
<!-- end header -->
<!-- start menu -->
<div id="menu">
	<ul>
		<li class="active"><a href="index.jsp">首页</a></li>
		<li><a href="ActionServlet?action=login">管理</a></li>
	</ul>
</div>
<!-- end menu -->
</body>
</html>