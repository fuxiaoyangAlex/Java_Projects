<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width;initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<!-- Bootstrap core JavaScript================================================== -->
			<script src="js/jquery1.12.4.min.js"></script>
			<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
			<script src="js/bootstrap3.3.7.min.js"></script>
		<!-- Placed at the end of the document so the pages load faster -->
    <title>阳光流淌的个人博客</title>
    <link href="css/copy.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/blog.css" rel="stylesheet">
    <script src="css/copy.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>
  </head>

	<body>
		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
					</button>
					<a class="navbar-brand" href="/Lyon/Admin" target="_blank">Lyon后台管理系统</a>
				</div>

				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理列表<span class="caret"></span></a>
								<ul class="dropdown-menu">
										 <li><a href="/Lyon/Admin_home_writeBlog" target="_blank">新增 文章</a></li>
										 <li><a href="/Lyon/Admin_home_editTags" target="_blank">标签管理</a></li>
										 <li><a href="#" target="_blank">文章属性管理</a></li>
										 <li><a href="/Lyon/Home" target="_blank">前台Home主页</a></li>
								</ul>
						</li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[0].getName()}<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<c:forEach items="${secondcats[0]}" var="categary" varStatus="st">
										 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}" target="_blank"><c:out value="${categary.getName()}"/></a></li>
								 </c:forEach>
								</ul>
						</li>
						<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[1].getName()}<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<c:forEach items="${secondcats[1]}" var="categary" varStatus="st">
										 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}" target="_blank"><c:out value="${categary.getName()}"/></a></li>
									</c:forEach>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[2].getName()}<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<c:forEach items="${secondcats[2]}" var="categary" varStatus="st">
									 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}" target="_blank"><c:out value="${categary.getName()}"/></a></li>
								</c:forEach>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[3].getName()}<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<c:forEach items="${secondcats[3]}" var="categary" varStatus="st">
									 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}" target="_blank"><c:out value="${categary.getName()}"/></a></li>
								</c:forEach>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[4].getName()}<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<c:forEach items="${secondcats[4]}" var="categary" varStatus="st">
									 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}" target="_blank"><c:out value="${categary.getName()}"/></a></li>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div><!--container-->
		</nav>
