<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图片的上传</title>
</head>
<body style="text-align:center">
	<!-- 上传文件 必须是Post、加entype -->
	<form action="UploadServlet" method="post";
		enctype="multipart/form-data">
		图片编号:<input name="sno" /><br>
		      图片名字:<input name="sname" /><br>
		   图片属于:<input name="use_id"> <br>
		上传照片:<input type="file" name="spicture" /><br>
		<input type="submit" value="上传" />
	</form>
	<!-- 	<a href="DownUploadServlet?filename=1.jpg" style="color:red">图片</a> -->
</body>
</html>