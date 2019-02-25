<%@ page import="com.spark.domain.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spark.service.ArticleService" %>
<%@ page import="com.spark.service.impl.ArticleServiceImpl" %>
<%@ page import="com.spark.domain.Article" %>
<%@ page import="java.sql.SQLException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<style>
		#modal_content2{
			padding: 30px 20px;
			width: 400px;
			height: 200px;
			background: white;
			position: fixed;
			left: 50%;
			top: 50%;
			margin-left: -200px;
			margin-top: -100px;
			display: none;
		}
		#close2{
			position: absolute;
			right: 10px;
			top: 10px;
		}
	</style>
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
	<link rel="stylesheet" href="js/pageStyle.css">
	<script src="js/jquery.min.js"></script>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container cl">
			<a class="navbar-logo hidden-xs" href="#">
				<img class="logo" src="img/w_logo.png" alt="Cyberspace个人空间" />
			</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);" onclick="showSide();">&#xe667;</a>
			<nav class="nav navbar-nav nav-collapse w_menu" role="navigation">
				<ul class="cl">
					<li class="active"> <a href="cyberspace_index.jsp" data-hover="首页">首页</a> </li>
					<li> <a href="cyberspace_about.jsp" data-hover="关于我">关于我</a> </li>
					<li><a href="testFiles/cyberspace_article.jsp" data-hover="我的文章">我的文章</a></li>
					<li> <a href="cyberspace_album.jsp" data-hover="我的相册">我的相册</a> </li>
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
<br/>
<!--导航条-->
<nav class="breadcrumb">
    <div class="container">
        <i class="Hui-iconfont">&#xe67f;</i><a href="#" class="c-primary">首页</a>
        <span class="c-gray en">&gt;</span> <a href="#" class="c-primary">我的文章</a>
        <span class="c-gray en">&gt;</span> <span class="c-gray"><i class="Hui-iconfont">&#xe681;</i> 分类管理</span>
    </div>
</nav>
<section class="container">
  <!--left-->
  <div class="col-sm-9 col-md-9 mt-20">
<!--------------------------------------------------------------------------------------------->
					<div class="main_top">
						<div class="am-cf am-padding am-padding-bottom-0">
							<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong></div>
						</div>
						<hr>
						<div class="am-g">
							<div class="am-u-sm-12 am-u-md-6">
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<button id="add" class="btn btn-secondary-outline radius">
											<i class="Hui-iconfont">&#xe610;</i> 添加分类</button>
									</div>
								</div>
							</div>
						</div>
					<%
						List<Category> list = (List<Category>) request.getAttribute("allCategory");
						System.out.println("----> " + list);
					%>

					<div id="account_List">
						<table class="table table-border table-bordered table-striped">
						<tr>
							<td>序号</td>
							<td>分类名称</td>
							<td>修改分类</td>
							<td>删除分类</td>
						</tr>
						<!--取数据JSTL-->
<!----------------------------------------------------------------------------------------------------------------------->
						<c:forEach var="acg" items="${allCategory}" varStatus="status">
						<tr>
							<td><c:out value="${status.count}"/> </td>
							<td><c:out value="${acg.cname}"/> </td>
							<!--这里使用class允许重名，如果是id的话每个都是一样的-->
							<td><a href="#" class="updatebtn" data-id="${acg.cid}"><img class="img_icon" src="images/edit_icon.png" alt=""></a></td>
							<td><a href="#" class="deletebtn" data-id="${acg.cid}"><img class="img_icon" src="images/delete_icon.png" alt=""></a></td>
						</tr>
						</c:forEach>
						</table>
					</div>
					</div>
	  <!----------------------------------------------------------------------------------------------------------------->
					<div id="modal_view"></div>
					<div id="modal_content">
						<div id="close"><img src="images/delete_icon.png" alt=""></div>
						<div class="edit_content">

							<div class="item1">
								<div>
									<span>添加分类</span>
								</div>
							</div>
							<div class="item1">
								<div>
									<span>分类名称:</span>
									<input type="text" class="am-form-field" id="cname">&nbsp;&nbsp;
									<button class="am-btn am-btn-default" type="button" id="addcategory">添加</button>
								</div>
							</div>
						</div>
					</div>
<!---------------------------------------------------------------------------------------------------------------------->
<%--<script>--%>
	<%--$("#add").click(function () {--%>
		<%--alert("Hello World!")--%>
    <%--})--%>
<%--</script>--%>

<%--<!---------------------------------------------------------------------------------------------------------------------->--%>
	  				<div id="modal_content2" style="height: 150px; display: none">
						<div id="close2"><img src="images/delete_icon.png" alt=""></div>

						<div class="item1">
							<div>
								<span>修改分类：</span><br/>
								<input type="hidden" id="cid2">
								分类名称: <input type="text" class="am-form-field" id="cname2">
								<button class="am-btn am-btn-default" type="button" id="updatebtn">修改</button>
							</div>
						</div>
					</div>
<%--<!---------------------------------------------------------------------------------------------------------------------------->--%>
					<script>
						var cid;
                        $(function () {
                            $('#add').click(function () {
                                $("#modal_view").fadeIn();
                                $("#modal_content").fadeIn();
                            });

                            $("#close").click(function () {
                                $("#modal_view").fadeOut();
                                $("#modal_content").fadeOut();
                            });
                            /*监听添加按钮的点击**/
							$("#addcategory").click(function () {
								/**获取文本框的内容*/
                                var cname = $("#cname").val();
								alert(cname);
								//alert(parentid + cname);// 验证
								/**发送请求*/
								$(window).attr('location','${pageContext.request.contextPath}/categoryServlet.do?action=addCategory&uname=${user.uname}' + '&cname=' + cname);
                            });
							/*监听修改按钮的点击**/
							$(".updatebtn").click(function () {
								// alert("a");
								//知道当前点的是那一条的数据
								 cid = $(this).data("id");
								alert(cid);
								/**
								 * 取出对应的id的数据，到数据库当中查询当前记录
								 * 返回给页面
								 * 展示到页面中
								 ***/


								//把修改的界面弹出来
                                $("#modal_view").fadeIn();
                                $("#modal_content2").fadeIn();
                            });
							$("#updatebtn").click(function () {
                                var parentid2 = $("#parentid2").val();
                                var  cname2 = $("#cname2").val();
								alert(cid + parentid2 + cname2);
                                /**发送请求*/
                                $(window).attr('location','${pageContext.request.contextPath}/categoryServlet.do?action=updateCategory&uname=${user.uname}' + '&cname=' + cname2 + '&cid=' + cid);
                            });
								$("#close2").click(function () {
									$("#modal_view").fadeOut();
									$("#modal_content2").fadeOut();
								});
								<!--舰艇删除按钮-->
                            // $(".updatebtn").click(function () {
							$(".deletebtn").click(function () {
								cid = $(this).data("id");
								alert(cid);
								$(window).attr('location','${pageContext.request.contextPath}/categoryServlet.do?action=deleteCategory&uname=${user.uname}&cid=' + cid);
                            })
                        });
					</script>
<!--------------------------------------------------------------------------------------------->
  </div>
  
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

	  <%
		  ArticleService articleService = new ArticleServiceImpl();
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
<script type="text/javascript" src="plugin/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="plugin/layer/3.0/layer.js"></script>
<script type="text/javascript" src="plugin/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="plugin/pifu/pifu.js"></script>
<script type="text/javascript" src="js/common.js"></script>
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
