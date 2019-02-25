<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spark.domain.User" %>
<%@ page import="org.apache.commons.collections.bag.SynchronizedSortedBag" %>
<%@ page import="com.spark.dao.GetAllUserInfoDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath();  // /Cyberspace 工程名称
	pageContext.setAttribute("ctx", ctx);
	System.out.println("-------> " + ctx);
%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript" src="../Bootstrap/jquery/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="../Bootstrap/js/bootstrap.min.js"></script>
  <link href="../Bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
 
  <style type = "text/css">
  
	table.gridtable {
	font-family:verdana,arial,sans-serif;
	font-size:20px;
	color:#333333;
	border-width:1px;
	border-color:#666666;
	border-collapse: collapse;
	}
	table.gridtable th{
	border-width:1px;
	padding:25px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
	}
	table.gridtable td{
	border-width:1px;
	padding:20px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	}
    p{ 
        color: #ff6db3;
  		padding-top:3px;
        text-align:left; 
     }
    body{
    /*background-image:url("images/timg.gif");*/
    background-repeat:no-repeat;
    background-position:530px 0px;
   /*  background-attachment:scroll; */
    background-attachment:fixed;
    background-size:1000px 760px;
    text-align:center;   
    }
	#fileupload{
		display: none;
	}

	.item1_img{
		width: 80px;
		height: 60px;
	}
  </style>
<title>My info</title>

</head>
<script>
    window.alert("用户头像资源路径：" + ${user.uimg};
</script>


<body>
<!--
	对用户资料进行更新操作：
	更新数据：
	-> 用户头像（uimg 将图像放入文件夹中而将图片所在文件夹的地址存放在数据库中）：
	-> 用户真实姓名（urealname 更新用户真实姓名）
	-> 用户性别 （ugender 更新用户性别）
	-> 个性签名 （ubio 更新用户个性签名)
-->

<div class="container" align="center">
	<caption align="center"><span class="glyphicon glyphicon-hand-right"
								  style="color: rgb(136, 173, 188);"></span>个人资料
		<span class="glyphicon glyphicon-hand-left"
			  style="color: rgb(136, 173, 188);"></span></caption>
	<form >
		<table class="table table-striped">
			<tr>
			<td>用户头像:</td>
			<td>
			<!--判断有没有用户的头像-->
			<c:if test="${empty user.uimg}">
			<%System.out.println("----------》此用户没有头像");%>
			<img src="../images/testPic.jpg" width="80px" height="60px">
			</c:if>
			<!--有用户头像的存在-->
			<c:if test="${!empty user.uimg}">
			<img src="${user.uimg}" alt="用户头像" width="80px" height="60px"></a></li>
			</c:if>
			</td>
			</tr>
			<tr>
			<td>更改用户头像：<br>（必须是jpg格式）</td>
			<td>
			<span>摘要图片：</span>
			<img src="" id="imageview" class="item1_img" style="display: none;" >
			<label for="fileupload" id="label_file" class="btn btn-info">上传文件</label>
			<input type="file" name="uimg" id="fileupload"/>
			</td>
				<tr>
					<td>
						用户名：
					</td>1
					<td>
						<font color="blue">${user.uname}</font>
					</td>
				</tr>

			  	<tr>
					<td>
						<span class="glyphicon glyphicon-user"></span>真实姓名：
					</td>
					<td>
						<!--判断有没有用户的真实姓名-->
						<c:if test="${empty user.urealname}">
							<font color="blue">无</font>&nbsp;&nbsp;添加真实姓名：<input type="text" name="urealname">
						</c:if>
						<!--有用户真实姓名的存在-->
						<c:if test="${!empty user.urealname}">
							<font color="blue">${user.urealname}</font>&nbsp;&nbsp;添加真实姓名：<input type="text" name="urealname">
						</c:if>
						<%--<font color="blue">无</font>&nbsp;&nbsp;添加真实姓名：<input type="text" name="urealname">--%>
					</td>
				</tr>

			  	<tr>
				  <td>👬性别：</td>
				  <td>
					  <!--判断是否已经有用户性别的数据-->
					  <c:if test="${empty user.gender}">
						 用户性别： <font color="blue">***</font>&nbsp;&nbsp;添加：<input type="text" name="ugender">
					  </c:if>
					  <!--有用户性别数据的存在-->
					  <c:if test="${!empty user.gender}">
						 用户性别： <font color="blue">${user.gender}</font>&nbsp;&nbsp;更新：<input type="text" name="ugender">
					  </c:if>
				  </td>
			  	</tr>

			  	<tr>
				  <td>
					  <span class="glyphicon glyphicon-envelope"></span>邮箱：
				  </td>
				  <td>
					  <font color="blue">${user.umail}</font>
				  </td>
			  	</tr>

			  	<tr>
					<td>
						<span class="glyphicon glyphicon-tags"></span>个性签名:
					</td>
					<td>
						<!--判断有没有用户的真个性签名-->
						<c:if test="${empty user.ubio}">
							<font color="blue">无</font>&nbsp;&nbsp;更新个性签名：<input type="text" name="ubio">
						</c:if>
						<!--有用户个性签名的存在-->
						<c:if test="${!empty user.ubio}">
							<font color="blue">${user.ubio}</font>&nbsp;&nbsp;更新个性签名：<input type="text" name="ubio">
						</c:if>
					</td>
				</tr>

			  	<tr>
					<td>注册日期：</td>
					<td><font color="blue">${user.udate}</font></td>
				</tr>
		  </table>
		<button id="send" class="btn btn-success">保存</button>
		<input type="reset" class="btn btn-warning" value="重置"><br/>
		<span style="color: green;">${result}</span>
		</form>
</div>
<script>
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
    $('#imageview').width("60px");
    $('#imageview').height("40px");
    $('.update_pic').attr('style', 'margin-bottom: 80px;');
    }
    });

    //
    $("#send").click(function(){
    //提交表单
    $("#blog_form").submit();
    }
    );
</script>
</body>
</html>



