<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<html>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript" src="Bootstrap/jquery/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
  <link href="Bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
 
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
    background-image:url("images/timg.gif");
    background-repeat:no-repeat;
    background-position:530px 0px;
   /*  background-attachment:scroll; */
    background-attachment:fixed;
    background-size:1000px 760px;
    text-align:center;   
    } 
  </style>
<title>个人简历表</title>
</head>
<body><font size = 5>
<div class="container">
      <table class="gridtable">
   			 <tr>
   			 <th colspan="1" align="center"><p style="color:">
   			 <span class="glyphicon glyphicon-hand-right" 
   			 style="color: rgb(136, 173, 188);"></span>个人信息表 
   			
   			 <span class="glyphicon glyphicon-hand-left" 
   			 style="color: rgb(136, 173, 188);"></span></p></td>
   			 </tr>
    	<tr>
     		<td align="left"><p><span class="glyphicon glyphicon-search"></span>姓名: WZ</p></td>
     	</tr>
     	<tr>	  	  
	 		<th align="left"><p><span class="glyphicon glyphicon-star" ></span>性别: 男</p></td>
	  </tr>  
	  <tr>
     	 <td align="center"><p><span class="glyphicon glyphicon-envelope"></span>邮箱:1713355078@qq.com</p></td>
     </tr>
     <tr>	 
	  	 <th align="center"><p><span class="glyphicon glyphicon-tags"></span>个性签名:吃好，喝好。</p></td>
	 </tr>
	 <tr>
	  <td align="center"><p><span class="glyphicon glyphicon-user"></span>真实姓名:王壮</p></td>
	</tr>
	  </table>
     <!-- <p>Font icon on a button:
        <button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-font"></span> Font
        </button>
      </p> -->   	 <p> 
 			<span class="glyphicon glyphicon-send"></span>
 			<button type="button"  class="btn btn-success">王壮壮按钮</button>
 		</p>
 	 </div> 	
 	</font>
</body>
</html>



