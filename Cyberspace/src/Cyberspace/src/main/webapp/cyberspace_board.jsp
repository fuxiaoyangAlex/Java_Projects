<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<title>Cyberspace个人空间</title>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" href=" img/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/staticRes/js/html5shiv.js"></script>
<script type="text/javascript" src="/staticRes/js/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="plugin/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="plugin/Hui-iconfont/1.0.8/iconfont.min.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="plugin/pifu/pifu.css" />
<link rel="stylesheet" type="text/css" href="plugin/wangEditor/css/wangEditor.min.css">
<!--[if lt IE 9]>
<link href="/staticRes/lib/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } function showSide(){$('.navbar-nav').toggle();}</script>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container cl">
            <a class="navbar-logo hidden-xs" href="cyberspace_index.jsp">
                <img class="logo" src="img/w_logo.png" alt="Cyberspace个人空间" />
            </a>
            <a class="logo navbar-logo-m visible-xs" href="cyberspace_index.jsp">Lao王博客</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);" onclick="showSide();">&#xe667;</a>
            <nav class="nav navbar-nav nav-collapse w_menu" role="navigation">
                <ul class="cl">
                    <li class="active"> <a href="cyberspace_index.jsp" data-hover="首页">首页</a> </li>
                    <li> <a href="cyberspace_about.jsp" data-hover="关于我">关于我</a> </li>
                    <li><a href="${pageContext.request.contextPath}/articleServlet.do?action=getFinalAllArticle&uname=${user.uname}" data-hover="文章">文章</a></li>
                    <li> <a href="cyberspace_album.jsp" data-hover="相册">相册</a> </li>
                    <li> <a href="${pageContext.request.contextPath}/lMessageServlet.do?uuid=${user.uuid}" data-hover="留言板">留言板</a> </li>
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
                        <a href="${pageContext.request.contextPath}/logoutServlet.do?umail=<%= request.getSession().getAttribute("umail")%>"onclick="" ><img class="" src="img/out.png" title=""><font color="blue">注销</font></a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<br/>
<!--导航条-->
<nav class="breadcrumb">
    <div class="container"> <i class="Hui-iconfont">&#xe67f;</i> <a href="cyberspace_index.jsp" class="c-primary">首页</a> <span class="c-gray en">&gt;</span>  <span class="c-gray">留言板</span> </div>
</nav>


<!--=============================================留言板====================================================================-->
<section class="container">
    <div class="col-xs-12 col-md-10 col-md-offset-1 mt-20">
        <!--用于评论-->
        <script>
            console.log(${user.uuid}${user.uimg})
        </script>
        <form action="lmessageServlet.do" method="post" id="txtForm">
        <%--<form action="lmessageServlet.do" method="post" id="txtForm">--%>
        <div class="mt-20" id="ct">
            <div id="error" class="Huialert Huialert-danger hidden radius"><span>${error}</span></div>
            <input type="hidden" value="<c:out value='${user.uname}'/>" name="uname"/> <!--自己给自己留言（自己的姓名）-->
            <input type="hidden" value="<c:out value='${user.uuid}'/>" name="uuid"/> <!--此份留言所属的用户-->
            <input type="hidden" value="<c:out value='${user.uimg}'/>" name="lmessage_pic"/> <!--留言时的头像-->

            <%--<input id="xx"   value="<c:out value='${user.uuid}'/>"/>--%>
            <textarea id="textarea1" name="lmessage" style="height:200px;" placeholder="........."> </textarea>
            <div class="text-r mt-10">
                <button onclick="getPlainTxt()" class="btn btn-primary radius"> 发表评论</button>
            </div>
        </div>
        </form>
        <div class="line"></div>

<!--=============================================留言区域====================================================================-->
        <ul class="commentList mt-50">
<!--=============================================留言区域====================================================================-->
            <!--用用户的uuid查询用户所有的留言
                大留言板
                id
                lmessage_user
                lmessage_pic
                lmessage_date
                lmessage_content
            -->

            <!--------------------------------------大评论开始处----------------------------------------------------------------------------------------------->
            <!--------------------------------------大评论开始处----------------------------------------------------------------------------------------------->
            <%
                System.out.println(request.getSession().getAttribute("lList"));
            %>
            <c:forEach items="${lList}" var="vars">
                <form action="smessageInsertServlet.do" id="form1" method="post">
            <li class="item cl"> <a href="#"><i class="avatar size-L radius"><img alt="" src=${vars.lmessage_pic}></i></a>
                <div class="comment-main">
                    <header class="comment-header">
                        <div class="comment-meta"><a class="comment-author" href="#">${vars.lmessage_user}</a>
                            <time title="" datetime="2018-12-31T03:54:20" class="f-r">${vars.lmessage_date}</time>
                        </div>
                    </header>
                    <div class="comment-body">
                        ${vars.lmessage_content}
                    <!--------------------------------------小评论开始处----------------------------------------------------------------------------------------------->
                            <c:forEach items="${sList}" var="svars1">
                            <c:if test="${vars.id == svars1.id}">
                                <!--从此处开始为一个小评论！-->
                                <ul class="commentList">

                                    <li class="item cl"> <a href="#"><i class="avatar size-L radius"><img alt="" src="${svars1.smessage_userpic}"></i></a>
                                        <div class="comment-main">
                                            <header class="comment-header">
                                                <div class="comment-meta"><a class="comment-author" href="#">${svars1.smessage_username}</a>
                                                    <time title="time" datetime="2018-12-31T03:54:20" class="f-r">${svars1.smessage_userdate}}</time>
                                                </div>
                                            </header>
                                            <div class="comment-body">
                                                <p>${svars1.smessage_usercontent}</p>
                                            </div>
                                        </div>
                                    </li>

                            <!--------------------------------------小评论边界----------------------------------------------------------------------------------------------->
                                </ul>
                            </c:if>
                            </c:forEach>



                            <%--<input type="hidden" value="<c:out value='${user.uuid}'/>" name="uuid"/> <!--此份留言所属的用户-->--%>


            <!--------------------------------------大评论边界----------------------------------------------------------------------------------------------->
                    </div>
                </div>
            </li>
                <div align="right">
                    <br/>
                <input type="hidden" value="<c:out value='${user.uname}'/>" name="smessage_username"/> <!--（自己的姓名）-->
                <input type="hidden" value="<c:out value='${vars.id}'/>" name="id"/> <!--回复-->
                <input type="hidden" value="<c:out value='${user.uimg}'/>" name="smessage_userpic"/> <!--回复时的头像-->
                <input type="hidden" value="<c:out value='${user.uuid}'/> " name="uuid"/> <!--用户的UUID-->
                <textarea name="smessage_usercontent" cols="30" rows="2" style="resize:none;width:200px; height:40px" class="input-text radius size-M"></textarea>
                <button type="submit"  class="btn btn-success-outline radius">回复</button>
                </div>
                </form>
            </c:forEach>

            <!--========================================================第二组评论(用于测试)=========================================================-->
            <li class="item cl"> <a href="#"><i class="avatar size-L radius"><img alt="" src="http://q.qlogo.cn/qqapp/101388738/1CF8425D24660DB8C3EBB76C03D95F35/100"></i></a>
                <div class="comment-main">
                    <header class="comment-header">
                        <div class="comment-meta"><a class="comment-author" href="#">老王</a>
                            <time title="2014年8月31日 下午3:20" datetime="2014-08-31T03:54:20" class="f-r">2014-8-31 15:20</time>
                        </div>
                    </header>
                    <div class="comment-body">
                        你是猴子派来的救兵吗？

                        <ul class="commentList">
                            <li class="item cl"> <a href="#"><i class="avatar size-L radius"><img alt="" src=></i></a>
                                <div class="comment-main">
                                    <header class="comment-header">
                                        <div class="comment-meta"><a class="comment-author" href="#">老王</a>
                                            <time title="2014年8月31日 下午3:20" datetime="2014-08-31T03:54:20" class="f-r">2014-8-31 15:20</time>
                                        </div>
                                    </header>
                                    <div class="comment-body">
                                        <p> 是的</p>
                                    </div>
                                </div>
                            </li>
                            <li class="item cl"> <a href="#"><i class="avatar size-L radius"><img alt="" src="http://qzapp.qlogo.cn/qzapp/101388738/696C8A17B3383B88804BA92ECBAA5D81/100"></i></a>
                                <div class="comment-main">
                                    <header class="comment-header">
                                        <div class="comment-meta"><a class="comment-author" href="#">老王</a>
                                            <time title="2014年8月31日 下午3:20" datetime="2014-08-31T03:54:20" class="f-r">2014-8-31 15:20</time>
                                        </div>
                                    </header>
                                    <div class="comment-body">
                                        <p> +1</p>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <button class="hf f-r btn btn-default size-S mt-10" name="2">回复</button>

                    </div>
                </div>
            </li>
 <!--========================================================留言=========================================================-->
        </ul>
 <!--========================================================小回复表单=========================================================-->
<%--
    小回复的属性
    ：smessage_username 回复的用户
    ：smessage_userpic 回复的头像
    : smessage_usercontent 回复的内容
    : smessage_userdate 回复的时间
    ：id 所属的大留言板
    <form action="" method="get">
    <input type='text' name='gid'/>
    <input type='text' name='type'/>
    <input type="button" value="搜索" onClick="tpformsubmit()">
</form>
<script>
    function tpformsubmit(){
        var gid = $('input[name=gid]').val();
        var type = $('input[name=type]').val();
        url = '/index/web?style=tp&gid='+gid+'&type='+type;
        window.location.href = url;
    }
</script>

--%>
<!------------------------------------------------------------此处功能尚未完成！--------------------------------------------------->
        <%--<!--用于回复-->--%>
        <%--<div class="comment hidden mt-20">--%>
            <%--<div id="err2" class="Huialert Huialert-danger hidden radius">成功状态提示</div>--%>
            <%--<form action="smessageInsertServlet.do" method="post" id="sform">--%>
                <%--<input type="hidden" value="<c:out value='${user.uname}'/>" name="smessage_username"/> <!--自己给自己留言（自己的姓名）-->--%>
                <%--<input type="hidden" value="<c:out value='${vars.id}'/>" name="id"/> <!--此份留言所属的大留言板-->--%>
                <%--<input type="hidden" value="<c:out value='${user.uimg}'/>" name="smessage_userpic"/> <!--留言时的头像-->--%>
                <%--<textarea class="textarea" style="height:100px;" name="smessage_usercontent"> </textarea>--%>
            <%--<button onclick="submitData()" type="button" class="btn btn-primary radius mt-10">回复</button>--%>
            <%--</form>--%>
            <%--<a class="cancelReply f-r mt-10">取消回复</a>--%>
        <%--</div>--%>

    </div>
</section>

<%--=============================================留言板====================================================================--%>
<footer class="footer mt-20">
    <div class="container-fluid" id="foot">
        <pre>Copyright &copy; 2018 <a href="https://github.com/Eirckwang"><font color="#1e90ff">https://github.com/Eirckwang</font> </a><br>
            <a href="#" target="_blank">Niit Cyberspace 个人空间系统</a>
        </pre>
    </div>
</footer>
<script>
    function submitData() {
        var form = document.getElementById("sform");
        form.submit();
    }
</script>
<script type="text/javascript" src="plugin/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="plugin/layer/3.0/layer.js"></script>
<script type="text/javascript" src="plugin/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="plugin/pifu/pifu.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script> $(function(){ $(window).on("scroll",backToTopFun); backToTopFun(); }); </script>
<script type="text/javascript" src="plugin/wangEditor/js/wangEditor.min.js"></script>

<script type="text/javascript">
    $(function () {
        wangEditor.config.printLog = false;
        var editor1 = new wangEditor('textarea1');
        editor1.config.menus = ['insertcode', 'quote', 'bold', '|', 'img', 'emotion', '|', 'undo', 'fullscreen'];
        editor1.config.emotions = { 'default': { title: '表情', data: 'plugin/wangEditor/emotions1.data'}, 'default2': { title: '心情', data: 'plugin/wangEditor/emotions3.data'}, 'default3': { title: '顶一顶', data: 'plugin/wangEditor/emotions2.data'}};
        editor1.create();

        //show reply
        $(".hf").click(function () {
            pId = $(this).attr("name");
            $(this).parents(".commentList").find(".cancelReply").trigger("click");
            $(this).parent(".comment-body").append($(".comment").clone(true));
            $(this).parent(".comment-body").find(".comment").removeClass("hidden");
            $(this).hide();
        });
        //cancel reply
        $(".cancelReply").on('click',function () {
            $(this).parents(".comment-body").find(".hf").show();
            $(this).parents(".comment-body").find(".comment").remove();
        });
    });

</script>

</body>
</html>
