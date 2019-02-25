<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="blog.flowingsun.bean.Articlelist"%>
<%@ page import="blog.flowingsun.bean.Categary"%>
<%@ page import="blog.flowingsun.bean.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
	List <Categary> categarys = (List)request.getAttribute("categarys");    //categarys数组元素保存一级分类名，String类型
  List <Categary> secondcats = (List)request.getAttribute("secondcats");  //secondcats数组元素保存二级分类Bean类型，Bean类型中保存了二级分类名。
  User  user = (User)request.getSession().getAttribute("user");           //用于保存用户登录状态、用户Id、用户昵称。
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<link href="css/copy.css" rel="stylesheet" >
		<link href="css/blog.css" rel="stylesheet">	<!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/userLogin.css" type="text/css" media="all">
		<!-- Bootstrap core JavaScript================================================== -->
			<script src="js/jquery1.12.4.min.js"></script>
			<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
			<script src="js/bootstrap3.3.7.min.js"></script>
		<!-- Placed at the end of the document so the pages load faster -->
      <script src="css/copy.js"></script>
		    <title>阳光流淌的个人博客</title>
  </head>
  <body>

<script type="application/x-javascript">
var xmlhttp;
function validate(f){
		var re1 = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		var input1 = f.userid.value;
		if(/^\d+$/.test(input1)==true){
				if(!(/^\d{11,12}$/.test(input1))){
					alert("请输入11-12位的手机号！");
					f.userid.focus();
					return false;
				}
		}else{
			if(!(re1.test(input1))){
					alert("请输入正确格式的邮箱地址！");
					f.userid.focus();
					return false;
			}
		}
		if(!(/^[0-9a-zA-Z.]{6,16}$/.test(f.userpass.value))){
				alert("密码必须是6~16位！");
				f.userpass.focus();
				return false;
		}
		$('#loginDiv').css('display','none');
		$.ajax({
			cache:true,
			type:"POST",
			url:"foreuserLogin",
			data:$('#loginForm').serialize(),
			async: false,
			error:function(request){
          alert("💔登录失败：Connection error:"+request.error);
      },
			success:function(data) {
				  if(data=="success"){
						  alert("🙂登录成功！");
					}else{
						  alert(data);
					}

      }
    });
}
function validate2(f){
		var re2 = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		var input1 = f.username.value;
		var input2 = f.useremail.value;
		var input3 = f.userpass.value;
		var input4 = f.userphone.value;
		if(/^\d+$/.test(input4)==true){
				if(!(/^\d{11,12}$/.test(input4))){
					alert("请输入11-12位的手机号！");
					f.userphone.focus();
					return false;
				}
		}else{
			alert("请确保输入的手机号码为数字！");
		}
		if(!(re2.test(input2))){
				alert("请输入正确格式的邮箱地址！");
				f.useremail.focus();
				return false;
				}
		if(!(/^[0-9a-zA-Z.]{6,16}$/.test(input3))){
				alert("密码必须是6~16位！");
				f.userpass.focus();
				return false;
		}
		return true;
}
</script>

<script>
$(function(){
	  $('#navbar').on("mouseenter",'#searchspan',function(){
			  var father = $(this).parent('li');
			  $(this).remove();
				var newdiv = document.createElement('div');
				newdiv.setAttribute("class","searchDiv");
				newdiv.setAttribute("style","background-color: #fff;padding: 1px;");
        newdiv.innerHTML = '<form  method="post"  action="/Lyon/foresearchKeyword" style="display: block; padding: 0;margin: 0;">'+
				'<p class="search-row"><input type="search" name="keyWord" id="keyWord" placeholder="🔍"><button id="searchButton" onclick="return function()">搜索</button></p></form>';
				father.append(newdiv);
	  });
		$('#navbar').on("mouseleave",'.searchDiv',function(){
			  var thenode = $(this).parent('li');
				var input = $('#keyWord');
				if(input.val()==''){
					  $(this).remove();
					  thenode.append('<span id="searchspan" style="color:#00E5EE">搜🔍</span>');
				}
	  });
		$('#navbar').on("click",'#searchButton',function(){
        var input = $('#keyWord');
				if(input.val()==''){
					  alert("请输入🔍关键词！");
						return false;
				}
		});
		$('#navbar').on("click",'#loginbutton',function(){
			  var css = $('#loginDiv').css('display');
			  if(css=='block'){
					  $('#loginDiv').css('display','none');
            $(this).text("登录注册");
			  }else{
					  $('#loginDiv').css('display','block');
					  $(this).text("关闭×");
			}
		});

		$('#navbar').on("click",'#logoutbutton',function(){
			  var father = $('#logoutbutton').parent().parent();
				$.ajax({
					cache:true,
					type:"GET",
					url:"forecheckLogout",
					async: false,
					error:function(request){
		          alert("💔退出登录失败：Connection error:"+request.error);
		      },
					success:function(data) {
		          alert("🙂成功退出！");
							$('#userHeader').remove();
              $('#logoutbutton').remove();
							$(father).append("<li id='loginLi'>"+'<a href="javascript:void(0)" onclick="function()" id="loginbutton">登录注册</a></li>');
		      }
		    });
		});

});
</script>

  <nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
        </button>
        <a class="navbar-brand" href="/Lyon/Home" target="_blank">Lyon's Blog</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
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

        </ul>


        <ul class="nav navbar-nav navbar-right">
					<li style="margin-top:12px;" id="search">
						<span id="searchspan" style="color:#00E5EE">搜🔍</span>
				  </li>

          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[4].getName()}<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <c:forEach items="${secondcats[4]}" var="categary" varStatus="st">
                 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}" target="_blank"><c:out value="${categary.getName()}"/></a></li>
              </c:forEach>
            </ul>
          </li>
					<c:if test="${user==null}">
            <li id='loginLi'><a href="javascript:void(0)" onclick="function()" id="loginbutton">登录注册</a></li>
				  </c:if>
					<c:if test="${user!=null}">
            <li id='logoutLi'><a href="javascript:void(0)" onclick="function()" id="logoutbutton">退出登录</a></li>
					  <a id='userHeader'><img src="images/十一月的肖邦.jpg" height="40px" width="45px" style="margin-top:4px;"/></a>
				  </c:if>
        </ul>
      </div><!--/.nav-collapse -->
    </div><!--container-->
  </nav>

	<div id="loginDiv" style="position:absolute;z-index: 99;left:30%;width:50%;height:52%;display:none;padding:10px 30px 0px 30px;" class="container1 w3layouts agileits">
		<div class="login w3layouts agileits">
			<h2 style="color:#FFF;font-size:25px;text-align:center;margin-bottom:10px;">登 录</h2>
			<form action="" id="loginForm" method="post" onSubmit="return validate(this)">
				<input type="text" name="userid" placeholder="邮箱/手机号" required="">
				<input type="password" name="userpass" placeholder="密码" required="">
				<ul style="width: 100%;display:inline-block;float:left;margin-bottom:20px;">
					<li>
						<input type="checkbox" style="padding-left:30px;" value="">
						<span style="color:#FFF">记住我</span>
					</li>
				</ul>

			<div class="send-button w3layouts agileits">
					<input type="submit" value="登 录"><a href="#">忘记密码?</a>
				</form>
			</div>
			<div class="social-icons w3layouts agileits">
				<p>- 其他方式登录 -</p>
				<ul>
					<li class="qq"><a href="#">
					<span class="icons"><img src="images/tencent.jpeg" style="border-radius:6px;"/></span>
					<span class="text">QQ</span></a></li>
					<li class="weixin w3ls"><a href="#">
					<span class="icons"><img src="images/wechat.jpeg" style="border-radius:6px;"/></span>
					<span class="text">微信</span></a></li>
					<li class="weibo aits"><a href="#">
					<span class="icons"><img src="images/xlwb.jpeg" style="border-radius:6px;"/></span>
					<span class="text">微博</span></a></li>
					<div class="clear"> </div>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="register w3layouts agileits">
			<h2 style="color:#FFF;font-size:25px;text-align:center;">注 册</h2>
			<form action="/Lyon/foreuserRegister" method="post" onSubmit="return validate2(this)">
				<input type="text" name="username" placeholder="用户名">
				<input type="text" name="useremail" placeholder="邮箱">
				<input type="password" name="userpass" placeholder="密码">
				<input type="text" name="userphone" placeholder="手机号码">
			  <div class="send-button w3layouts agileits">
				<input type="submit" value="免费注册">
			</form>
			  </div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
