<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Cyberspace个人空间</title>

<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="description" content="Cyberspace个人空间系统">

    <link rel="shortcut icon" href=" img/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/staticRes/js/html5shiv.js"></script>
<script type="text/javascript" src="/staticRes/js/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="plugin/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="plugin/Hui-iconfont/1.0.8/iconfont.min.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="plugin/pifu/pifu.css" />
<!--[if lt IE 9]>
<link href="/staticRes/lib/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } function showSide(){$('.navbar-nav').toggle();}</script>
    <script type="text/javascript" src="Bootstrap/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
    <link href="Bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">

    <style type = "text/css">

        table.gridtable {
            font-family:verdana,arial,sans-serif;
            font-size:20px;
            color:#333333;
            border-width:1px;
            border-color:#666666;
            border-collapse: collapse;
        }
        table.gridtable th{
            border-width:1px;
            padding:25px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table.gridtable td{
            border-width:1px;
            padding:20px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
        p{
            color: #ff6db3;
            padding-top:3px;
            text-align:left;
        }
        body{
            /*background-image:url("images/timg.gif");*/
            background-repeat:no-repeat;
            background-position:530px 0px;
            /*  background-attachment:scroll; */
            background-attachment:fixed;
            background-size:1000px 760px;
            text-align:center;
        }
        #fileupload{
            display: none;
        }

        .item1_img{
            width: 80px;
            height: 60px;
        }
    </style>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <a class="navbar-logo hidden-xs" href="cyberspace_index.jsp">
            <img class="logo" src="img/w_logo.png" alt="Cyberspace个人空间" />
        </a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);" onclick="showSide();">&#xe667;</a>
            <nav class="nav navbar-nav nav-collapse w_menu" role="navigation">
                <ul class="cl">
                    <li class="active"> <a href="cyberspace_index.jsp" data-hover="首页">首页</a> </li>
                    <li> <a href="cyberspace_about.jsp" data-hover="关于我">关于我</a> </li>
                    <li><a href="cyberspace_article.jsp" data-hover="我的文章">我的文章</a></li>
                    <li> <a href="cyberspace_mood.jsp" data-hover="我的相册">我的相册</a> </li>
                    <li> <a href="cyberspace_board.jsp" data-hover="留言板">留言板</a> </li>
                </ul>
            </nav>
            <nav class="navbar-nav navbar-userbar hidden-xs hidden-sm " style="top: 0;">
                <ul class="cl">
                    <li>
                        <span><font color="#1e90ff">${user.uname}</font> </span>
                        <!--判断有没有用户的头像-->
                        <c:if test="${empty user.uimg}">
                            <img src="" alt="无" width="28" height="20">
                        </c:if>
                        <!--有用户头像的存在-->
                        <c:if test="${!empty user.uimg}">
                            <img src="${user.uimg}" alt="用户头像" width="28" height="20"></a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/logoutServlet.do?umail=${user.umail}"onclick="" ><img class="" src="img/out.png" title=""><font color="blue">注销</font></a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<!--导航条-->
<nav class="breadcrumb">
    <div class="container"> <i class="Hui-iconfont">&#xe67f;</i> <a href="cyberspace_index.jsp" class="c-primary">首页</a> <span class="c-gray en">&gt;</span>  <span class="c-gray">关于</span> </div>
</nav>

<section class="container">
    <div class="container-fluid">
        <!--
            对用户资料进行更新操作：
            更新数据：
            -> 用户头像（uimg 将图像放入文件夹中而将图片所在文件夹的地址存放在数据库中）：
            -> 用户真实姓名（urealname 更新用户真实姓名）
            -> 用户性别 （ugender 更新用户性别）
            -> 个性签名 （ubio 更新用户个性签名)
        -->
        <div class="container" align="center">
            <form action="${pageContext.request.contextPath}/uimgServlet.do?umail=${user.umail}" method="post" enctype="multipart/form-data" >
                <caption align="center"><span class="glyphicon glyphicon-hand-right"
                                              style="color: rgb(136, 173, 188);"></span>个人资料
                    <span class="glyphicon glyphicon-hand-left"
                          style="color: rgb(136, 173, 188);"></span>
                </caption>
                <table class="table table-striped">
                    <tr>
                        <td>用户头像:</td>
                        <td>
                            <!--判断有没有用户的头像-->
                            <c:if test="${empty user.uimg}">
                                <%System.out.println("----------》此用户没有头像");%>
                                <img src="images/testPic.jpg" width="80px" height="60px">
                            </c:if>
                            <!--有用户头像的存在-->
                            <c:if test="${!empty user.uimg}">
                                <img src="${user.uimg}" alt="用户头像" width="80px" height="60px"></a></li>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>更改用户头像：<br>（必须是jpg格式）</td>
                        <td>
                            <span>摘要图片：</span>
                            <img src="" id="imageview" class="item1_img" style="display: none;" >
                            <label for="fileupload" id="label_file" class="btn btn-info">上传文件</label>
                            <input type="file" name="uimg" id="fileupload"/>
                        </td>
                    <tr>
                        <td>
                            用户名：
                        </td>1
                        <td>
                            <font color="blue">${user.uname}</font>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-user"></span>真实姓名：
                        </td>
                        <td>
                            <!--判断有没有用户的真实姓名-->
                            <c:if test="${empty user.urealname}">
                                <font color="blue">无</font>&nbsp;&nbsp;添加真实姓名：<input type="text" name="urealname">
                            </c:if>
                            <!--有用户真实姓名的存在-->
                            <c:if test="${!empty user.urealname}">
                                <font color="blue">${user.urealname}</font>&nbsp;&nbsp;添加真实姓名：<input type="text" name="urealname">
                            </c:if>
                            <%--<font color="blue">无</font>&nbsp;&nbsp;添加真实姓名：<input type="text" name="urealname">--%>
                        </td>
                    </tr>

                    <tr>
                        <td>👬性别：</td>
                        <td>
                            <!--判断是否已经有用户性别的数据-->
                            <c:if test="${empty user.ugender}">
                                用户性别： <font color="blue">***</font>&nbsp;&nbsp;添加：<input type="text" name="ugender">
                            </c:if>
                            <!--有用户性别数据的存在-->
                            <c:if test="${!empty user.ugender}">
                                用户性别： <font color="blue">${user.ugender}</font>&nbsp;&nbsp;更新：<input type="text" name="ugender">
                            </c:if>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-envelope"></span>邮箱：
                        </td>
                        <td>
                            <font color="blue">${user.umail}</font>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <span class="glyphicon glyphicon-tags"></span>个性签名:
                        </td>
                        <td>
                            <!--判断有没有用户的真个性签名-->
                            <c:if test="${empty user.ubio}">
                                <font color="blue">无</font>&nbsp;&nbsp;更新个性签名：<input type="text" name="ubio">
                            </c:if>
                            <!--有用户个性签名的存在-->
                            <c:if test="${!empty user.ubio}">
                                <font color="blue">${user.ubio}</font>&nbsp;&nbsp;更新个性签名：<input type="text" name="ubio">
                            </c:if>
                        </td>
                    </tr>

                    <tr>
                        <td>注册日期：</td>
                        <td><font color="blue">${user.udate}</font></td>
                    </tr>
                </table>
                <button id="send" class="btn btn-success">保存</button>
                <input type="reset" class="btn btn-warning" value="重置"><br/>
                <span style="color: green;">${result}</span>
            </form>
        </div>
    </div>
</section>
<footer class="footer mt-20">
    <div class="container-fluid" id="foot">
        <pre>Copyright &copy; 2018 <a href="https://github.com/Eirckwang"><font color="#1e90ff">https://github.com/Eirckwang</font> </a><br>
            <a href="#" target="_blank">Niit Cyberspace 个人空间系统</a>
        </pre>
    </div>
</footer>
<script type="text/javascript" src="plugin/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="plugin/layer/3.0/layer.js"></script>
<script type="text/javascript" src="plugin/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="plugin/pifu/pifu.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script> $(function(){ $(window).on("scroll",backToTopFun); backToTopFun(); }); </script>
<script>
    /*原理是把本地图片路径："D(盘符):/image/..."转为"http://..."格式路径来进行显示图片*/
    $("#fileupload").change(function() {
        var $file = $(this);
        var objUrl = $file[0].files[0];
        //获得一个http格式的url路径:mozilla(firefox)||webkit or chrome
        var windowURL = window.URL || window.webkitURL;
        //createObjectURL创建一个指向该参数对象(图片)的URL
        var dataURL;
        dataURL = windowURL.createObjectURL(objUrl);
        $("#imageview").attr("src",dataURL);
        console.log($('#imageview').attr('style'));

        if($('#imageview').attr('style') === 'display: none;'){
            $('#imageview').attr('style','inline');
            $('#imageview').width("60px");
            $('#imageview').height("40px");
            $('.update_pic').attr('style', 'margin-bottom: 80px;');
        }
    });

    //
    $("#send").click(function(){
        //提交表单
        $("#blog_form").submit();
    }
        );
</script>
</body>
</html>
