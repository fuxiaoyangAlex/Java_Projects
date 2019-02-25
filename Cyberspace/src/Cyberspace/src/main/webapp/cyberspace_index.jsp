<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spark.domain.Article" %>
<%@ page import="com.spark.service.ArticleService" %>
<%@ page import="com.spark.service.impl.ArticleServiceImpl" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
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
<meta name="keywords" content="个人博客">
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
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container cl">
            <a class="navbar-logo hidden-xs" href="cyberspace_index.jsp">
				<img class="logo" src="img/w_logo.png" alt="Cyberspace个人空间" />
            </a>
            <a class="logo navbar-logo-m visible-xs" href="cyberspace_index.jsp">Cyberspace</a>
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
						<a href="${pageContext.request.contextPath}/logoutServlet.do?umail=<%= request.getSession().getAttribute("umail")%>" onclick="" ><img class="" src="img/out.png" title=""><font color="blue">注销</font></a>
					</li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<!--- 将用户名以及相关信息存储在session域之中 以待之后的使用-->
<section class="container pt-20">
	<!--<div class="Huialert Huialert-info"><i class="Hui-iconfont">&#xe6a6;</i>成功状态提示</div>-->
  <!--left-->
  <div class="col-sm-9 col-md-9">
  	<!--滚动图-->
  	<div class="slider_main">
            <div class="slider">
                <div class="bd">
                    <ul>
                        <li><a href="#" target="_blank"><img src="img/w_baner02.jpg"></a></li>
                        <li><a href="#" target="_blank"><img src="img/w_banner03.jpg"></a> </li>
                        <li><a href="#" target="_blank"><img src="img/w_baner01.jpg"></a></li>
                    </ul>
                </div>
                <ol class="hd cl dots">
                    <li>1</li>
                    <li>2</li>
                </ol>
                <a class="slider-arrow prev" href="javascript:void(0)"></a>
                <a class="slider-arrow next" href="javascript:void(0)"></a>
            </div>
        </div>
  		
		<div class="mt-20 bg-fff box-shadow radius mb-5">
			<div class="tab-category">
				<a href=""><strong class="current">最新发布</strong></a>
			</div>
		</div>

	  <!-----------------------------测试------------------------------------------------>

      <%
          /*==================================================================*/
          //首页获取所有文章数据并存入request域中
          //===========================有条件的查询所有的文章数据findAllIndexArticle===========================
          ArticleService articleService = new ArticleServiceImpl();
          List<Article> indexPageArticle = articleService.getIndexPageArticle();
          request.setAttribute("indexPageArticle",indexPageArticle);

          /*==================================================================*/
          List<Article> articleList = (List<Article>) request.getAttribute("indexPageArticle");
        Collections.reverse(articleList);
          if (articleList != null){
//              System.out.println(articleList);
              System.out.println("获取成功！--------------------------");
          }else{
              System.out.println("获取失败------------------");
          }
      %>
	  <!-----------------------------测试------------------------------------------------>
	  <!-----------------------------内容------------------------------------------------>
		<div class="art_content">
            <!-----------------------------获取的文章内容------------------------------------------------>

            <ul class="index_arc">
                <c:forEach items="${indexPageArticle}" var="index">
                <li class="index_arc_item">
                    <a href="#" class="pic">
                        <img class="lazyload" data-original="${index.article_pic}" alt="demo" />
                    </a>
                    <h4 class="title">
                        <a href="cyberspace_article_detail.jsp?article_id=${index.article_id}" target="_blank">${index.article_title}</a>
                        <%--<a href="cyberspace_article_detail.jsp?article_id=${index.article_id}" target="_blank">${index.article_title}</a></h4>--%>
                    <%--<h4 class="title"><a href="cyberspace_article_detail.jsp&article_id=${index.article_id}" target="_blank">${index.article_title}</a></h4>--%>
                    <div class="date_hits">
                        <span>&nbsp;&nbsp;</span>
                        <span>${index.article_time}</span>
                        <span><a href="/article-lists/10.html">${index.article_author}</a></span>
                        <p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> ${index.read_count}° </p>
                        <p class="commonts"><i class="Hui-iconfont" title="评论次数">&#xe622;</i> <span class="cy_cmt_count">20</span></p>
                    </div>
                    <div class="desc">
                            ${index.article_desc}
                    </div>
                </li>
                </c:forEach>
            </ul>

            <!-----------------------------获取的文章内容------------------------------------------------>
            <!-----------------------------文章测试------------------------------------------------>
            <%--<ul class="index_arc">--%>
				<%--<li class="index_arc_item">--%>
					<%--<a href="#" class="pic">--%>
						<%--<img class="lazyload" data-original="temp/art.jpg" alt="应该选" />--%>
					<%--</a>--%>
					<%--<h4 class="title"><a href="article_detail.html">个人博客应该选择什么样的域名和域名后缀</a></h4>--%>
					<%--<div class="date_hits">--%>
						<%--<span>测试员</span>--%>
						<%--<span>2017-02-24</span>--%>
						<%--<span><a href="/article-lists/10.html">程序人生</a></span>--%>
						<%--<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i> 276° </p>--%>
						<%--<p class="commonts"><i class="Hui-iconfont" title="点击量">&#xe622;</i> <span class="cy_cmt_count">20</span></p>--%>
					<%--</div>--%>
					<%--<div class="desc">不论搭建什么样的网站，选择一个好的域名都是很有必要的，选择一个好的域名对网站的意义也是不言而喻的。每一个网站都有之对应的域名，就像人的名字一样。每个人都想自己有个好听的名字，网站也是一样。一个网站可以有多个域名，但是一个域名只能对应一个网站。&nbsp;一、域名要好记，方便输入&nbsp; &nbsp; &nbsp; &nbsp;域名本身的意义就是为了人们方便记忆才使用的，不然都用IP地址就好了。所以，网站域名一定要选择好记忆的。因为域名是</div>--%>
				<%--</li>--%>
			<%--</ul>--%>
            <!-----------------------------文章------------------------------------------------>
            <div class="text-c mb-20" id="moreBlog">
                <a class="btn  radius btn-block " href="javascript:;" onclick="moreBlog();">点击加载更多</a>
                <a class="btn  radius btn-block hidden" href="javascript:;">加载中……</a>
            </div>
		</div>
      <!-----------------------------内容------------------------------------------------>

  </div>
  
  <!--right-->
  <div class="col-sm-3 col-md-3">
  	
  	<!--站点声明-->
        <div class="panel panel-default mb-20">
            <div class="panel-body">
                <i class="Hui-iconfont" style="float: left;">&#xe62f;&nbsp;</i>
                <div class="slideTxtBox">
                    <div class="bd">
                        <ul>
                            <li><a href="javascript:void(0);"><font color="red">Cyberspace个人空间系统，欢迎访问！</font></a></li>
							<li><a href="javascript:void(0);"><font color="#1e90ff"> We are the world!</font></a></li>
							<li><a href="javascript:void(0);"><font color="blue">IDEA、MAVEN、TOMCAT、MYSQL、NAVICAT</font></a></li>
                            <li><a href="javascript:void(0);"><font color="#b8860b">JSP、Servlet、HTML、JS、CSS、SQL、JSTL、EL</font> </a></li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
  	<!--博主信息-->
        <div class="bg-fff box-shadow radius mb-20">
            <div class="tab-category">
                <a href=""><strong>博主信息</strong></a>
            </div>
            <div class="tab-category-item">
                <ul class="index_recd">
                    <li class="index_recd_item"><i class="Hui-iconfont">&#xe653;用户名称: ${user.uname}</i></li>

                    <li class="index_recd_item"><i class="Hui-iconfont">&#xe63b;</i>邮箱：<a href="mailto:${user.umail}">${user.umail}</a></li>
                    <li class="index_recd_item"><i class="Hui-iconfont">&#xe671;</i>定位：中国 &middot; 江苏</li>
                </ul>
            </div>
        </div>
   <!----------------------========================获取热门推荐和点击排行所需要的数据===========================================================--->
      <%
          //======================点击排行========================
          //调用Service 层
          try {
              List<Article> hotInfoArticle = articleService.getHotInfoArticle();
              if(hotInfoArticle != null){
                  System.out.println("热门数据获取成功！........................................");
                  System.out.println(hotInfoArticle);
                  //将获取的热门数据存入会话之中
                  session.setAttribute("pData",hotInfoArticle);
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
          //======================热门推荐========================
          try {
              List<Article> recommendArticle = articleService.getRecommendArticle();
              if(recommendArticle != null){
                  System.out.println("推荐数据获取成功！..........................................");
                  //将获取的推荐数据存入会话之中
                  session.setAttribute("rData",recommendArticle);
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }


      %>


   <!--热门推荐----------------------------------------------------------------------------------------------------------->
  	<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>热门推荐</strong></a>
			</div>
			<div class="tab-category-item">
				<ul class="index_recd">
                    <c:forEach items="${rData}" var="rData">
					<li>
                        <a href="cyberspace_article_detail.jsp?article_id=${rData.article_id}&ac_user=${user.uname}&ac_img=${user.uimg}" target="_blank">${rData.article_title}</a>
						<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i>${rData.read_count}° </p>
					</li>
                    </c:forEach>
				</ul>
			</div>
		</div>


		
	<!--点击排行----------------------------------------------------------------------------------------------------------->

        <div class="bg-fff box-shadow radius mb-20">
            <div class="tab-category">
                <a href=""><strong>点击排行</strong></a>
            </div>
            <div class="tab-category-item">
                <ul class="index_recd clickTop">
                    <c:forEach items="${pData}" var="pData">
                    <li>
                        <a href="cyberspace_article_detail.jsp?article_id=${pData.article_id}&ac_user=${user.uname}&ac_img=${user.uimg}" target="_blank">${pData.article_title}</a>
                        <%--<a href="cyberspace_article_detail.jsp?article_id=${pData.article_id}" target="_blank">${pData.article_title}</a>--%>
                        <p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i>${pData.read_count}° </p>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        
		<!--标签-->
		<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>标签云</strong></a>
			</div>
			<div class="tab-category-item">
				<div class="tags"> <a href="http://www.h-ui.net/">H-ui前端框架</a> <a href="http://www.h-ui.net/websafecolors.shtml">Web安全色</a> <a href="http://www.h-ui.net/Hui-4.4-Unslider.shtml">jQuery轮播插件</a> <a href="http://idc.likejianzhan.com/vhost/korea_hosting.php">韩国云虚拟主机</a> <a href="http://www.h-ui.net/bug.shtml">IEbug</a> <a href="http://www.h-ui.net/site.shtml">IT网址导航</a> <a href="http://www.h-ui.net/icon/index.shtml">网站常用小图标</a> <a href="http://www.h-ui.net/tools/jsformat.shtml">web工具箱</a> <a href="http://www.h-ui.net/bg/index.shtml">网站常用背景素材</a> <a href="http://www.h-ui.net/yuedu/chm.shtml">H-ui阅读</a> <a href="http://www.h-ui.net/easydialog-v2.0/index.html">弹出层插件</a> <a href="http://www.h-ui.net/SuperSlide2.1/demo.html">SuperSlide插件</a> <a href="http://www.h-ui.net/TouchSlide1.1/demo.html">TouchSlide</a></div>
			</div>
		</div>
		<!--友情链接-->
		<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>友情链接</strong></a>
			</div>
			<div class="tab-category-item">
				<span><i class="Hui-iconfont">&#xe6f1;</i><a href="#" class="btn-link">百度</a></span>
				<span><i class="Hui-iconfont">&#xe6f1;</i><a href="#" class="btn-link">淘宝</a></span>
				<span><i class="Hui-iconfont">&#xe6f1;</i><a href="#" class="btn-link">腾讯</a></span>
				<span><i class="Hui-iconfont">&#xe6f1;</i><a href="#" class="btn-link">慕课网</a></span>
				<span><i class="Hui-iconfont">&#xe6f1;</i><a href="#" class="btn-link">h-ui</a></span>
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
<script type="text/javascript" src="plugin/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="plugin/layer/3.0/layer.js"></script>
<script type="text/javascript" src="plugin/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="plugin/pifu/pifu.js"></script>  <!--控制皮肤-->
<script type="text/javascript" src="js/common.js"></script>
<script> $(function(){ $(window).on("scroll",backToTopFun); backToTopFun(); }); </script>
<script type="text/javascript" src="plugin/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>

<script>
$(function(){
//幻灯片
jQuery(".slider_main .slider").slide({mainCell: ".bd ul", titCell: ".hd li", trigger: "mouseover", effect: "leftLoop", autoPlay: true, delayTime: 700, interTime: 2000, pnLoop: true, titOnClassName: "active"})
//tips
jQuery(".slideTxtBox").slide({titCell: ".hd ul", mainCell: ".bd ul", autoPage: true, effect: "top", autoPlay: true});
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
