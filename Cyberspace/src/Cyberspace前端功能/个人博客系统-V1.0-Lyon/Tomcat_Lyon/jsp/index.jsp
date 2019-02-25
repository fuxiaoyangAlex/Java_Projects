<!DOCTYPE html>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="blog.flowingsun.bean.Articlelist"%>
<%@ page import="blog.flowingsun.bean.Categary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
	List <Categary> categarys = (List)request.getAttribute("categarys");    //categarys数组元素保存一级分类名，String类型
  List <Categary> secondcats = (List)request.getAttribute("secondcats");  //secondcats数组元素保存二级分类Bean类型，Bean类型中保存了二级分类名。
%>


<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width;initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<link href="css/copy.css" rel="stylesheet">
		<!-- Custom styles for this template -->
		<link rel="stylesheet" href="css/animate.css">
		<link rel="stylesheet" href="css/site.min.css">
		<link  rel="stylesheet" href="css/headerwrap.css">
		<link rel="stylesheet" href="css/userLogin.css" type="text/css" media="all">
		<!-- Bootstrap core JavaScript================================================== -->
		<script src="js/jquery1.12.4.min.js"></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap3.3.7.min.js"></script>
		<!-- Placed at the end of the document so the pages load faster -->
    <title>阳光流淌的个人博客</title>
		<script src="css/copy.js"></script>
		<script>
    function imgLoaded(img) {
        var imgWrapper = img.parentNode;
        imgWrapper.className += imgWrapper.className? ' loaded' : 'loaded'
    }
    </script>
  </head>

<%@include file="navtop.jsp"%>



<div id="headerwrap">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<h1 class="animate-box" data-animate-effect="fadeIn">当你老了</h1>
					<h3>—赵照</h3>
				<h3 class="animate-box" data-animate-effect="fadeIn">当你老了    头发白了    睡意    昏沉<br>当你老了    走不动了    炉火旁打盹    回忆青春<br>
					多少人曾爱你青春欢畅的时辰<br>爱慕    你的美丽    假意或真心<br>只有一个人还爱你虔诚的灵魂<br>爱你    苍老的    脸上的皱纹</h3>
				<h3 class="animate-box" data-animate-effect="fadeIn">当我老了    眼眉低垂    灯火    昏黄    不定<br>
					风吹过来    你的消息    这就    是我    心里的歌<br>当我老了    我真希望    这首歌    是    唱给你的！<h3>
				<h5 class="animate-box" data-animate-effect="fadeIn"><b class="app-name">歌词改编自-叶芝《When You Are Old》</b></h5>
			</div>
		</div>
	</div>
</div>

<%@include file="homeImages.jsp"%>
<%@include file="footer.jsp"%>
