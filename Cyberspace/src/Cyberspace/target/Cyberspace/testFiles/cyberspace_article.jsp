<%@ page import="com.spark.domain.Article" %>
<%@ page import="java.util.List" %>
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
	<link rel="shortcut icon" href="../img/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="/staticRes/js/html5shiv.js"></script>
<script type="text/javascript" src="/staticRes/js/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../plugin/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../plugin/Hui-iconfont/1.0.8/iconfont.min.css" />
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../plugin/pifu/pifu.css" />
<!--[if lt IE 9]>
<link href="/staticRes/lib/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } function showSide(){$('.navbar-nav').toggle();}</script>
	<%
		String ctx = request.getContextPath();
		pageContext.setAttribute("ctx", ctx);
	%>
	<link rel="stylesheet" href="${ctx }/css/style_.css" type="text/css" />
	<link rel="stylesheet" href="${ctx }/css/amazeui.min.css" />
	<script src="${ctx }/js/jquery.min.js"></script>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container cl">
			<a class="navbar-logo hidden-xs" href="index.html">
				<img class="logo" src="../img/w_logo.png" alt="Cyberspace个人空间" />
			</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);" onclick="showSide();">&#xe667;</a>
			<nav class="nav navbar-nav nav-collapse w_menu" role="navigation">
				<ul class="cl">
					<li class="active"> <a href="../cyberspace_index.jsp" data-hover="首页">首页</a> </li>
					<li> <a href="../cyberspace_about.jsp" data-hover="关于我">关于我</a> </li>
					<li><a href="cyberspace_article.jsp" data-hover="文章">文章</a></li>
					<li> <a href="../cyberspace_album.jsp" data-hover="相册">相册</a> </li>
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
						<a href="${pageContext.request.contextPath}/logoutServlet.do?umail=${user.umail}"onclick="" ><img class="" src="../img/out.png" title=""><font color="blue">注销</font></a>
					</li>
				</ul>
			</nav>
        </div>
    </div>
</header>
<br/>
<!--导航条-->
<nav class="breadcrumb">
    <div class="container">
        <i class="Hui-iconfont">&#xe67f;</i><a href="#" class="c-primary">首页</a>
        <span class="c-gray en">&gt;</span> <a href="#" class="c-primary">我的文章</a>
    </div>
</nav>

<!--================================================测试================================================================--->
<%
	List<Article> allArticle = (List<Article>) request.getAttribute("allArticle");
	System.out.println("*****************************************************");
	if (allArticle != null){
	    System.out.println(allArticle);
	}else{
	    System.out.println("Nothing------------------------------------------------------------------------");
	}
%>
<!--================================================测试================================================================--->

<section class="container">
  <!--left-->
  <div class="col-sm-9 col-md-9 mt-20">
	  <!--article list-->
	  <ul class="index_arc">
		  <li class="index_arc_item">
			  <a href="#" class="pic">
				  <img class="lazyload" data-original="temp/art.jpg" alt="应该选" />
			  </a>
			  <h4 class="title"><a href="article_detail.html">个人博客应该选择什么样的域名和域名后缀</a></h4>
			  <div class="date_hits">
				  <span>老王</span>
				  <span>2017-02-24</span>
				  <span><a href="/article-lists/10.html">程序人生</a></span>
				  <p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> 276° </p>
				  <p class="commonts"><i class="Hui-iconfont" title="点击量">&#xe622;</i> <span class="cy_cmt_count">20</span></p>
			  </div>
			  <div class="desc">不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp; &nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是</div>
		  </li>

		  <li class="index_arc_item">
			  <a href="/article/4" class="pic">
				  <img class="lazyload" data-original="temp/art.jpg" alt="centos 6.5 nginx安装及配置" >
			  </a>
			  <h4 class="title"><a href="/article/4">centos 6.5 nginx安装及配置</a></h4>
			  <div class="date_hits">
				  <span>老王</span>
				  <span>2017-3-15</span>
				  <span>
	                <a href="/article?t=1">程序人生</a>
	            </span>
				  <p class="hits"><i class="Hui-iconfont" title="点击量"></i> 13° </p>
				  <p class="commonts"><i class="Hui-iconfont" title="评论"></i> <span class="cy_cmt_count">0</span></p>
			  </div>
			  <div class="desc">linux环境中nginx安装及配置简要概述。。。</div>
		  </li>

		  <li class="index_arc_item">
			  <a href="/article/4" class="pic">
				  <img class="lazyload" data-original="temp/art.jpg" alt="centos 6.5 nginx安装及配置" >
			  </a>
			  <h4 class="title"><a href="/article/4">centos 6.5 nginx安装及配置</a></h4>
			  <div class="date_hits">
				  <span>老王</span>
				  <span>2017-3-15</span>
				  <span>
	                <a href="/article?t=1">程序人生</a>
	            </span>
				  <p class="hits"><i class="Hui-iconfont" title="点击量"></i> 13° </p>
				  <p class="commonts"><i class="Hui-iconfont" title="评论"></i> <span class="cy_cmt_count">0</span></p>
			  </div>
			  <div class="desc">linux环境中nginx安装及配置简要概述。。。</div>
		  </li>

	  </ul>
  		<div class="text-c mb-20" id="moreBlog">
            <a class="btn  radius btn-block " href="javascript:;" onclick="moreBlog('${blogType.id}','${tag.name}');">点击加载更多</a>
            <a class="btn  radius btn-block hidden" href="javascript:;">加载中……</a>
        </div>

  </div>
  <!--left-->

  <!--right-->
  <div class="col-sm-3 col-md-3 mt-20">
  	
  	<!--导航-->
  	<div class="panel panel-primary mb-20">
		<div class="panel-body">
			<a href="${pageContext.request.contextPath}/articleServlet.do?action=getFinalAllArticle&uname=${user.uname}"><input class="btn btn-primary radius nav-btn" type="button" value="我的文章"></a>
			<a href="${pageContext.request.contextPath}/categoryServlet.do?action=getMyCategory&uname=${user.uname}"><input class="btn btn-primary radius nav-btn" type="button" value="发布文章"></a>
			<a href="${pageContext.request.contextPath}/articleServlet.do?action=getPageData&currentPage=1&uname=${user.uname}"><input class="btn btn-secondary-outline radius nav-btn" type="button" value="文章管理"></a>
			<a href="${pageContext.request.contextPath}/categoryServlet.do?action=getAllCategory&uname=${user.uname}"><input class="btn btn-secondary-outline radius nav-btn" type="button" value="分类管理"></a>
		</div>
		</div>
  	
  	<!--热门推荐-->
  	<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>热门推荐</strong></a>
			</div>
			<div class="tab-category-item">
				<ul class="index_recd">
					<li>
						<a href="#">阻止a标签href默认跳转事件</a>
						<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> 276° </p>
					</li>
					<li >
						<a href="#">PHP面试题汇总</a>
						<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> 276° </p>
					</li>
					<li >
						<a href="#">阻止a标签href默认跳转事件</a>
						<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> 276° </p>
					</li>
					<li >
						<a href="#">阻止a标签href默认跳转事件</a>
						<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> 276° </p>
					</li>
					<li >
						<a href="#">PHP面试题汇总</a>
						<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> 276° </p>
					</li>
				</ul>
			</div>
		</div>
	
<!--标签-->
		<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>标签</strong></a>
			</div>
			<div class="tab-category-item">
				<div class="tags"> <a href="https://github.com/"><FONT COLOR="#1e90ff" >GitHub</FONT></a> <a href="http://www.h-ui.net/websafecolors.shtml">Web安全色</a> <a href="http://www.h-ui.net/Hui-4.4-Unslider.shtml"><FONT COLOR="#7fff00">jQuery轮播插件</FONT></a> <a href="https://mvnrepository.com/">Maven</a> <a href="https://en.wikipedia.org/w/index.php?title=Main_Page&gettingStartedReturn=true">维基百科</a> <a href="http://www.h-ui.net/site.shtml">IT网址导航</a> <a href="http://www.h-ui.net/icon/index.shtml">网站常用小图标</a> <a href="http://www.h-ui.net/tools/jsformat.shtml">web工具箱</a> <a href="http://www.h-ui.net/bg/index.shtml">网站常用背景素材</a> <a href="http://www.h-ui.net/yuedu/chm.shtml">H-ui阅读</a> <a href="http://www.h-ui.net/easydialog-v2.0/index.html">弹出层插件</a> <a href="https://www.oracle.com/technetwork/cn/java/javaee/tech/index.html"><FONT COLOR="#7fff00">Oracle</FONT></a> <a href="http://www.runoob.com/">RUNOOB.COM</a></div>

			</div>
		</div>
  </div>
  
</section>


<footer class="footer mt-20">
	<div class="container-fluid" id="foot">
		<p>Copyright &copy; 2018 <a href="https://github.com/Eirckwang"><font color="#1e90ff">https://github.com/Eirckwang</font> </a><br>
			<a href="#" target="_blank">Niit Cyberspace 个人空间系统</a>
		</p>
	</div>
</footer>
<script type="text/javascript" src="../plugin/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../plugin/layer/3.0/layer.js"></script>
<script type="text/javascript" src="../plugin/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../plugin/pifu/pifu.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script> $(function(){ $(window).on("scroll",backToTopFun); backToTopFun(); }); </script>
<script>
$(function(){
//标签
	$(".tags a").each(function(){
		var x = 9;
		var y = 0;
		var rand = parseInt(Math.random() * (x - y + 1) + y);
		$(this).addClass("tags"+rand)
	});
	
	$("img.lazyload").lazyload({failurelimit : 3});
});

</script> 
</body>
</html>
