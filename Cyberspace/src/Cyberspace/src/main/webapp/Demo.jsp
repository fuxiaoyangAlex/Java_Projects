<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/08
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/testServlet.do?action=add">添加</a>
<a href="${pageContext.request.contextPath}/testServlet.do?action=del">删除</a>
<a href="${pageContext.request.contextPath}/testServlet.do?action=update">更新</a>
</body>
</html>
