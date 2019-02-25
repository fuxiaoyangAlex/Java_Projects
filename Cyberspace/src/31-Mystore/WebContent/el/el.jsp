<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	pageContext.setAttribute("name", "pageContextValue");
	request.setAttribute("name", "requestValue");
	session.setAttribute("name", "sessionValue");
	application.setAttribute("name", "applicationValue");
%>

${pageScope.name }
<br/>
${requestScope.name }
<br/>
${sessionScope.name }
<br/>
${applicationScope.name }

<h1>-------------</h1>
<!-- 范围 ：从小到大  找到之后， 就不会再去找了  -->
${name }

<h1>-------</h1>
${1+1 }
${empty name }


</body>
</html>