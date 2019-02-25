<%@ page import="com.spark.domain.User" %>
<%@ page import="com.spark.dao.GetAllUserInfoDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>CSS3个人资料菜单导航</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="../Bootstrap/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../Bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../Bootstrap/css/font-awesome.min.css">
    <link href="../Bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/reset.css" media="all"/>

    <script type="text/javascript" src="../js/prefixfree.min.js"></script>

    <style type="text/css">
        body {
            background-color: #d9d9d9;
        }

        a {
            color: #fff;
        }

        a:hover {
            color: #fff;
            text-decoration: none;
        }

        .demo {
            width: 960px;
            margin: 40px auto 0;
        }

        .menu-function {
            position: relative;
            background-image: -*-linear-gradient(top, #3d3d3d 50%, #373737 51%);
        }

        .menu-function li {
            float: left;
            height: 40px;
        }

        .menu-function li a {
            position: relative;
            display: block;
            padding: 0 20px 0 40px;
            line-height: 40px;
        }

        .menu-function li:not(:last-child) span {
            display: inline-block;
            padding: 0 3px;
            height: 14px;
            line-height: 14px;
            color: #fff;
            border-radius: 2px;
            box-shadow: 0 -1px 0 #6cb6e9, 0 1px 0 #24548e;
            background-image: -*-linear-gradient(top, #2b95e0, #2175d7);
        }

        .menu-function li:not(:last-child) a {
            line-height: 20px;
            border-left: 1px solid #222a30;
            border-top: 10px solid transparent;
            border-bottom: 10px solid transparent;
            transition: all .3s ease-out;
            transform-style: preserve-3d;
        }

        .menu-function li:last-child {
            float: right;
        }

        .menu-function li:first-child a {
            font-size: 0;
            padding-left: 25px;
        }

        .menu-function li:last-child img {
            width: 30px;
            height: 30px;
            margin-left: 2px;
            vertical-align: middle;
            box-shadow: 0 -1px 0 rgba(0, 0, 0, .2);
            border-radius: 2px;
        }

        .menu-function li:last-child span {
            color: #a3c8ea;
        }

        .menu-function li a:before, .drop-down button[type="button"]:before {
            position: absolute;
            left: 15px;
            font-family: 'icomoon';
            font-style: normal;
            speak: none;
            font-weight: normal;
            font-smoothing: antialiased;
            font-size: 18px;
            vertical-align: middle;
        }

        .menu-function li:nth-child(1) a:before {
            content: "\21";
        }

        .menu-function li:nth-child(2) a:before, .drop-down button[type="button"]:before {
            content: "\7d";
        }

        .menu-function li:nth-child(3) a:before {
            content: "\63";
        }

        .menu-function li:nth-child(4) a:before {
            content: "\e022";
        }

        .menu-function li:nth-child(5) a:before {
            content: "\38";
        }

        .menu-function li:not(:last-child) a:hover {
            color: #555;
            border-left: 1px solid transparent;
            background-color: #fff;
        }

        .menu-function li a:hover .drop-down {
            display: block;
            transform: rotateX(0deg);
        }

        .drop-down {
            position: absolute;
            top: 30px;
            left: 0;
            margin-left: -1px;
            width: 300px;
            padding: 20px;
            background-color: #fff;
            transition: all 0.3s ease-in;
            transform: rotateX(-90deg);
            backface-visibility: hidden;
        }

        .drop-down input[type="text"] {
            width: 100%;
            height: 30px;
            padding-left: 5px;
            border: 1px solid #ddd;
            border-radius: 2px;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2) inset;
            /*background-color: none;*/
            box-sizing: border-box;
            transition: all 0.3s ease-in;
        }

        .drop-down button[type="button"] {
            position: absolute;
            top: 22px;
            right: 22px;
            width: 24px;
            height: 26px;
            border: none;
            background-color: transparent;
        }

        .drop-down button[type="button"]:before {
            font-size: 14px;
            top: 6px;
            left: 6px;
            color: #747474;
        }

        input[type="text"]:focus {
            outline: 0 none;
            border-color: rgba(82, 168, 236, 0.8);
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1), 0 0 8px rgba(82, 168, 236, 0.6);
        }

        @font-face {
            font-family: 'icomoon';
            src: url('../fonts/icomoon.eot');
            src: url('../fonts/icomoon.eot?#iefix') format('embedded-opentype'),
            url('../fonts/icomoon.svg#icomoon') format('svg'),
            url('../fonts/icomoon.woff') format('woff'),
            url('../fonts/icomoon.ttf') format('truetype');
            font-weight: normal;
            font-style: normal;
        }
    </style>

</head>
<body>
<%
    String umail = request.getParameter("umail".trim());
    System.out.println("umail在personal_space.jsp中：" + umail);
    User user = GetAllUserInfoDao.getAllUser(umail);
    System.out.println("---User:" + user);
    pageContext.setAttribute("user",user);
%>
<section class="demo">
    <div class="page-menu-wrapper">
        <ul class="menu-function clearfix">
            <li><a href="../index.jsp" title="">首页</a></li>
            <li><a href="">Search</a> </li>
            <li><a href="" title="">留言板<span>10</span></a></li>
            <li><a href="personal_intro.jsp?umail=<%=user.getUmail()%>" title="">个人资料更改</a></li>
            <li><a href="#" title="">My Message<span>8</span></a></li>
            <li><a href="https://github.com/Eirckwang">🔥GitHub</a></li>
            <li><a href="#"></a></li>
            <li>
                <span>${user.uname}</span>
            <!--判断有没有用户的头像-->
            <c:if test="${empty user.uimg}">
                <img src="../images/testPic.jpg" width="80px" height="80px">
            </c:if>
            <!--有用户头像的存在-->
            <c:if test="${!empty user.uimg}">
                <img src="${user.uimg}" alt="用户头像" width="80px" height="80px"></a></li>
            <%System.out.println("Final Testing: " + user.getUimg());%>
            </c:if>
            <%--<!--判断有没有用户 session-->--%>
            <%--<c:if test="${empty user }">--%>
            <%--<li><a href="" title="">Howdy！--%>
                <%--<span>路人甲</span>--%>
                <%--<img src="images/testPic.jpg" alt="Eva."></a></li>--%>
            <%--</c:if>--%>
            <%--<!--有用户的存在-->--%>
            <%--<c:if test="${!empty user }">--%>
                <%--<li><a href="" title="">Howdy！--%>
                    <%--<span>${user.uname}</span>--%>
                    <%--<img src="images/testPic.jpg" alt="Eva."></a></li>--%>
            <%--</c:if>--%>
		</ul>
		<div align=" right">
			<a href="${pageContext.request.contextPath}/logoutServlet.do?umail=<%= user.getUmail()%>" style="color:lightseagreen" target="_parent">
            <span class="glyphicon glyphicon-new-window" style="color: rgb(47, 50, 255);"></span><font color="blue">Logout</font>
            </a>
        </div>
    </div>
</section>

</body>
</html>

