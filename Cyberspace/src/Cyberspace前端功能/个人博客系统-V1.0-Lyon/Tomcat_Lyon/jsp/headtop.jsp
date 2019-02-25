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
  User  user = (User)request.getSession().getAttribute("user");           //用于保存用户登录状态、用户Id、用户昵称。
%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<link href="css/copy.css" rel="stylesheet">
		<link href="css/blog.css" rel="stylesheet"><!-- Custom styles for this template -->
		<!-- Bootstrap core JavaScript================================================== -->
			<script src="js/jquery1.12.4.min.js"></script>
			<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
			<script src="js/bootstrap3.3.7.min.js"></script>
		<!-- Placed at the end of the document so the pages load faster -->
      <script src="css/copy.js"></script>
		    <title>阳光流淌的个人博客</title>
  </head>
