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
	<link rel="shortcut icon" href=" img/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/staticRes/js/html5shiv.js"></script>
<script type="text/javascript" src="/staticRes/js/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="plugin/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="plugin/Hui-iconfont/1.0.8/iconfont.min.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="plugin/pifu/pifu.css" />
<link rel="stylesheet" type="text/css" href="css/timeline.css" />
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
            <a class="logo navbar-logo-m visible-xs" href="cyberspace_index.jsp"></a>
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
						<a href="${pageContext.request.contextPath}/logoutServlet.do?umail=${user.umail}"onclick="" ><img class="" src="img/out.png" title=""><font color="blue">注销</font></a>
					</li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<!--导航条-->
<nav class="breadcrumb">
    <div class="container"><i class="Hui-iconfont">&#xe67f;</i> <a href="cyberspace_index.jsp" class="c-primary">首页</a> <span class="c-gray en">&gt;</span> <span class="c-gray">碎言碎语</span></div>
</nav>

<section class="container mt-20">
    <div class="container-fluid">
        <%--<div class="timeline">--%>
        	<%----%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%--<div class="cd-timeline-block">--%>
					    <%--<div class="cd-timeline-img cd-picture">--%>
					        <%--<img src="css/timeline/cd-icon-location.svg" alt="position">--%>
					    <%--</div>--%>
					    <%--<div class="cd-timeline-content">--%>
					        <%--<h4>测试测试</h4>--%>
					        <%--<p>Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。Lao王博客测试版本上线。。</p>--%>
					        <%--<a href="http://www.wfyvv.com" class="f-r"><input class="btn btn-success size-S" type="button" value="更多"></a>--%>
					        <%--<span class="cd-date">2017年1月01日</span>--%>
					    <%--</div>--%>
					<%--</div>--%>
					<%----%>
        <%--</div>--%>
    </div>

</section>
<footer class="footer mt-20">
	<div class="container-fluid" id="foot">
		<p>Copyright &copy; 2018 <a href="https://github.com/Eirckwang"><font color="#1e90ff">https://github.com/Eirckwang</font> </a><br>
			<a href="#" target="_blank">Niit Cyberspace 个人空间系统</a>
		</p>
	</div>
</footer>
<script type="text/javascript" src="plugin/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="plugin/layer/3.0/layer.js"></script>
<script type="text/javascript" src="plugin/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="plugin/pifu/pifu.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script> $(function(){ $(window).on("scroll",backToTopFun); backToTopFun(); }); </script>
<script>
    $(function () {
        //on scolling, show/animate timeline blocks when enter the viewport
        $(window).on('scroll', function () {
            $('.cd-timeline-block').each(function () {
                if ($(this).offset().top <= $(window).scrollTop() + $(window).height() * 0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden')) {
                    $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
                }
                if ($(window).scrollTop() - $(this).offset().top > 0) {
                    $(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden').removeClass('bounce-in');
                }
                
            });
           $('.cd-timeline-block').each(function(){
                if($(this).offset().top < $(window).scrollTop()+$(window).height()*0.75) {
                    $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden');
                }
            });
        });
    });

</script>
</body>
</html>
