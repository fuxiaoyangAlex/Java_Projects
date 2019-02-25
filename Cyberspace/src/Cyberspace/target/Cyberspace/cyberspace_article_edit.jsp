<%@ page import="com.spark.domain.Article" %>
<%@ page import="com.spark.domain.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spark.service.ArticleService" %>
<%@ page import="com.spark.service.impl.ArticleServiceImpl" %>
<%@ page import="java.sql.SQLException" %>
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
	<!--umeditor-->
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/umedit/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/umedit/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/umedit/lang/zh-cn/zh-cn.js"></script>
	<!--umeditor-->
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
        <span class="c-gray en">&gt;</span> <span class="c-gray"><i class="Hui-iconfont">&#xe623;</i>发布文章</span>
    </div>
</nav>

<section class="container">
  <!--left-->
  <div class="col-sm-9 col-md-9 mt-20">

  		<!---------------测试----------------------------------------------------------------------------->
	  <%
		  List<Category> myCategory = (List<Category>) request.getAttribute("myCategory");
		  if(myCategory != null){
		      System.out.println("myCategory: " + myCategory.size());
		  }else{
		      System.out.println("No No No.........");
		  }
	  %>


  		<!--------------------------------------------------------------------------------------------->
  	<!--article list-->
			<ul class="index_arc">
				<!---------------------------------------------------------------------------------------------->
				<div class="main_top">
					<div class="am-cf am-padding am-padding-bottom-0">
						<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">编辑文章
						</strong><small></small></div>
					</div>
					<hr>
<!----------====================================提交表单blog_form===enctype="multipart/form-data" =======================================--------->
					<form id="blog_form" action="${pageContext.request.contextPath}/publicArticleServlet.do" method="post" enctype="multipart/form-data">
						<input type="hidden" value="${user.uname}" name="uname">
						<div class="edit_content">
							<div class="item1">
								<div>
									<span>文章标题：</span>
									<input type="text" class="am-form-field" name="article_title" style="width: 300px"><br/>
									<span>文章概要：</span>
									<input type="text" class="am-form-field" name="article_desc"  style="width: 300px"><br/>
								</div>
							</div>




							<div class="item1">
								<span>所属分类：</span>
								<select id="category_select" name="myCategory" style="width: 300px">&nbsp;&nbsp;
									<c:forEach items="${myCategory}" varStatus="status" var="myCategory">
									　<option value="${myCategory.cname}"><c:out value="${myCategory.cname}"/></option>
									</c:forEach>
								</select>
							</div>
							<div >
								<!--1是不公开 0是公开-->
								<span>是否公开:&nbsp;&nbsp;</span>
								公开<input type="radio" name="myPublic" value="0">
								不公开<input type="radio" name="myPublic" value="1">
							</div>

							<div class="item1 update_pic" >
								<span>摘要图片：</span>
								<img src="" alt="图片" id="imageview" class="item1_img">
								<label for="fileupload" id="label_file">上传文件</label>
								<input type="file" name="upload" id="fileupload"/>
							</div>
							<!-----用于集成umeditor---->
							<div>
								<p>
									　　　　　　　
								</p>
							</div>
							<div id="editor" name="article_content" style="width:800px;height:400px;"></div>
							<!-----用于集成umeditor---->
							<button class="am-btn am-btn-default" type="button" id="send" style="margin-top: 10px;">
								发布</button>
						</div>
					</form>

				</div>

				<!---------------------------------------------------------------------------------------------->
			</ul>
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
				  pageContext.setAttribute("rData",recommendArticle);
			  }
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  %>
  	<!--热门推荐-->
  	<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>热门推荐</strong></a>
			</div>
			<div class="tab-category-item">
				<ul class="index_recd">
					<c:forEach items="${rData}" var="rData">
						<li>
							<a href="cyberspace_article_detail.jsp?article_id=${rData.article_id}" target="_blank">${rData.article_title}</a>
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
		<pre>Copyright &copy; 2018 <a href="https://github.com/Eirckwang"><font color="#1e90ff">https://github.com/Eirckwang</font> </a><br><a href="#" target="_blank">Niit Cyberspace 个人空间系统</a></pre>
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

	$(".tags a").each(function(){
		var x = 9;
		var y = 0;
		var rand = parseInt(Math.random() * (x - y + 1) + y);
		$(this).addClass("tags"+rand)
	});
	
	$("img.lazyload").lazyload({failurelimit : 3});
});
/***************************************************************************************************************/
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
        $('#imageview').width("80px");
        $('#imageview').height("200px");
        $('.update_pic').attr('style', 'margin-bottom: 80px;');
    }
	});
/*************************************************************************************************************/
<!--初始化umeditor富文本编辑器-->
//在js当中初始化富文本编辑器
var ue = UE.getEditor('editor');
<!--初始化umeditor富文本编辑器-->
//标签
//回调
$("#send").click(function () {

    alert("即将提交表单！");

    //设置文本描述
    //获取正文
	// var text = ue.getContent();
	// text = text.substring(0,150) + ".......................";
	// alert(text);
	// //设置描述
	// $("#article_desc").val(text);
    var form = $("#blog_form");
	//提交表单
	form.submit();
});
/*************************************************************************************************************/
</script> 
</body>
</html>
