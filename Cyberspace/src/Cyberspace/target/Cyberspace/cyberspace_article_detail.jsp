<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spark.service.ArticleService" %>
<%@ page import="com.spark.service.impl.ArticleServiceImpl" %>
<%@ page import="com.spark.domain.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.spark.domain.ArticleComment" %>
<%@ page import="com.spark.service.ArticleCommentService" %>
<%@ page import="com.spark.service.impl.ArticleCommentServiceImpl" %>
<%@ page import="java.util.Collections" %>
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
<link rel="stylesheet" type="text/css" href="plugin/wangEditor/css/wangEditor.min.css">
<!--[if lt IE 9]>
<link href="/staticRes/lib/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } function showSide(){$('.navbar-nav').toggle();}</script>
<link rel="stylesheet" type="text/css" href="css/praise_style.css" />
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container cl">
			<a class="navbar-logo hidden-xs" href="index.html">
				<img class="logo" src="img/w_logo.png" alt="Cyberspace个人空间" />
			</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:void(0);" onclick="showSide();">&#xe667;</a>
			<nav class="nav navbar-nav nav-collapse w_menu" role="navigation">
			</nav>
            <nav class="navbar-nav navbar-userbar hidden-xs hidden-sm " style="top: 0;">
            </nav>
        </div>
    </div>
</header>

<!--===========================================获取article_id来自（cyberspace_index.jsp 或者 cyberspace_article_demo.jsp===============================================================-->
		<%
			String article_id_ = request.getParameter("article_id");
			int article_id = 0;
			if(article_id_ != null){
			    System.out.println("article_id_: -------> " + article_id_  );
			     article_id = Integer.parseInt(article_id_);
				//实例化Article
				Article article = new Article();
				article.setArticle_id(article_id);
				pageContext.setAttribute("article_id",article_id);
				//调用Service层
				ArticleService articleService = new ArticleServiceImpl();
				List<Article> detailArticle = null;
				try {
					detailArticle = articleService.getDetailArticle(article);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//获取此条数据
				Article articleList = detailArticle.get(0);
				System.out.println("-------------------------------------------");
//				System.out.println(articleList);
				System.out.println("-------------------------------------------");

				//将获取的数据存入pageContext域中
				pageContext.setAttribute("index",articleList);
				/**------------------让文章的阅读次数自动增加1---------------------------------------------*/
				try {
					articleService.insertIntoArticleReadCount(article);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				/**------------------------------------------------------------------------------------*/

			}else{
			    System.out.println("获取参数失败！/////////////////////////");
			}
		%>

<!--===========================================获取article_id来自（cyberspace_index.jsp 或者 cyberspace_article_demo.jsp===============================================================-->

<!--导航条-->
<nav class="breadcrumb">
  <div class="container"> <i class="Hui-iconfont">&#xe67f;</i> <a href="cyberspace_index.jsp" class="c-primary">首页</a> <span class="c-gray en">&gt;</span>  <span class="c-gray">文章</span> <span class="c-gray en">&gt;</span>  <span class="c-gray">${index.article_title}</span></div>
</nav>

<section class="container pt-20">
	
	<div class="row w_main_row">
				
				
				<div class="col-lg-9 col-md-9 w_main_left">
					<div class="panel panel-default  mb-20">
						<div class="panel-body pt-10 pb-10">
							<h2 class="c_titile">${index.article_title}</h2>
							<p class="box_c"><span class="d_time">发布时间: <font color="#1e90ff">${index.article_time}</font></span><span>编辑：<a href="#">${index.article_author}</a></span><span>阅读（<font color="#1e90ff">${index.read_count}</font>）</span></p>
							<ul class="infos">
								${index.article_content}
							</ul>
															
							<div class="keybq">
						    	<p><span>关键字</span>：<a class="label label-default">个人博客</a><a class="label label-default">Cyberspace</a><a class="label label-default">软件1731</a></p>

							</div>
							<div class="heart " id="like1" rel="like"></div><div class="likeCount" id="likeCount1">${index.praise_count}</div>
						</div>
					</div>
					<!--------------------------------------------------------------------------------------------------------->

					<div class="panel panel-default  mb-20">
						<div class="tab-category">
                <a href=""><strong>评论区</strong></a>
            </div>
						<!--------------JSP内部直接获取属于该文章的所有数据----------------->
						<%
							ArticleCommentService articleCommentService = new ArticleCommentServiceImpl();
							ArticleComment articleComment = new ArticleComment();
							articleComment.setArticle_id(article_id);
							pageContext.setAttribute("article_id",article_id);
							System.out.println("article_id: " + article_id);
							try {
								List<ArticleComment> articleCommentById = articleCommentService.getArticleCommentById(articleComment);
								if (articleCommentById != null){
								    System.out.println("此片文章的评论查询成功！...........................");
								    //对数据进行反转
									Collections.reverse(articleCommentById);
//									System.out.println(articleCommentById);
									//将数据存入pageContext域中
									pageContext.setAttribute("AC",articleCommentById);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						%>


						<div class="panel-body">
							<div class="panel-body" style="margin: 0 3%;">
                    <div class="mb-20">
                    	<ul class="commentList">
							<!--------------此篇文章具体评论的显示------------------------------>
							<c:forEach items="${AC}" var="comment">
                            <li class="item cl"> <a href="#"><i class="avatar size-L radius"><img alt="" src="${comment.ac_img}"></i></a>
                                <div class="comment-main">
                                    <header class="comment-header">
                                        <div class="comment-meta"><a class="comment-author" href="#">${comment.ac_user}</a>
                                            <time title="" datetime="2018-12-31T03:54:20" class="f-r">${comment.ac_date}</time>
                                        </div>
                                    </header>
                                    <div class="comment-body">
                                       <p>${comment.ac_content}</p>
									</div>
                                </div>
                            </li>
							</c:forEach>
                        </ul>
    
                    </div>
<!-----------------------------------------------------评论区域--------------------------------------------------------------->
                    <div class="line"></div>
                    <!--用于评论
                    	评论所需要的属性：
                    	1.所评论文章的article_id
                    	2.所评论的用户ac_user（名称）
                    	3.所评论的用户ac_img（头像)
                    	3.所评论的内容ac_content(内容）
                    	4.所评论的日期ac_date（日期）
                    -->
					<%
						String ac_user = request.getParameter("ac_user");
						String ac_img = request.getParameter("ac_img");
						pageContext.setAttribute("ac_user",ac_user);
						pageContext.setAttribute("ac_img",ac_img);
					%>

                    <div class="mt-20" id="ct">
                        <form action="articleCommentServlet.do" method="post" >
                        <div id="err" class="Huialert Huialert-danger hidden radius">成功状态提示</div>
                        <textarea id="textarea1" name="ac_content" style="height:200px;" placeholder="看完不留一发？">
                        </textarea>
                            <input type="hidden" name="article_id" value="${article_id}"/>
                            <input type="hidden" name="ac_user" value="${ac_user}"/>
                            <input type="hidden" name="ac_img" value="${ac_img}"/>
                        <div class="text-r mt-10">
                            <input type="submit" class="btn btn-primary radius" value="发表评论">
                        </div>
                        </form>
                    </div>
                   <hr color="red"/>
                </div>
						</div>
					</div>
				</div>
<!---------------------------------------以下为界面推荐的内容（包括当前jsp页面数据的获取和展示）--------------------------------------------------------------------------------------->
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
				<div class="col-lg-3 col-md-3">
					<!--热门推荐-->
  	<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>热门推荐</strong></a>
			</div>
			<div class="tab-category-item">
				<ul class="index_recd">
					<c:forEach items="${rData}" var="rData">
						<li>
							<a href="cyberspace_article_detail.jsp?article_id=${rData.article_id}&ac_user=${user.uname}&ac_img=${user.uimg}" target="_blank">${rData.article_title}</a>
							<%--<a href="cyberspace_article_detail.jsp?article_id=${rData.article_id}" target="_blank">${rData.article_title}</a>--%>
							<p class="hits"><i class="Hui-iconfont" title="点击量">&#xe6c1;</i>${rData.read_count}° </p>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
						
					<!--图片-->
		<div class="bg-fff box-shadow radius mb-20">
			<div class="tab-category">
				<a href=""><strong>南京工业职业技术学院软件1731班</strong></a>
			</div>
			<div class="tab-category-item">
				<img src="images/myClass.jpg" class="img-responsive lazyload" alt="响应式图片">
			</div>
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
<script type="text/javascript" src="plugin/wangEditor/js/wangEditor.min.js"></script>
<script type="text/javascript">
    $(function () {
    		$("img.lazyload").lazyload({failurelimit : 3});
    	
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
    $(document).ready(function(){

        $('body').on("click",'.heart',function(){

            var A=$(this).attr("id");
            var B=A.split("like");
            var messageID=B[1];
            var C=parseInt($("#likeCount"+messageID).html());
            $(this).css("background-position","")
            var D=$(this).attr("rel");

            if(D === 'like') {
                $("#likeCount"+messageID).html(C+1);
                $(this).addClass("heartAnimation").attr("rel","unlike");
                $(window).attr('location','${pageContext.request.contextPath}/articleServlet.do?action=praiseArticle&article_id=' + ${article_id} + '&flag=like');

            }
            else{
                $("#likeCount"+messageID).html(C-1);
                $(this).removeClass("heartAnimation").attr("rel","like");
                $(this).css("background-position","left");
                $(window).attr('location','${pageContext.request.contextPath}/articleServlet.do?action=praiseArticle&article_id=' + ${article_id} + '&flag=unlike');
            }
        });

    });
</script>
</body>
</html>
