<%@ page import="java.net.InetAddress" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.spark.service.ManageService" %>
<%@ page import="com.spark.service.impl.ManageImpl" %>
<%@ page import="com.spark.domain.Count" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/style.css"/>
        	<link rel="stylesheet" href="assets/css/ace.min.css" />
        <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
        <link href="assets/css/codemirror.css" rel="stylesheet">
		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="assets/js/ace-extra.min.js"></script>
		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
        		<!--[if !IE]> -->
		<script src="assets/js/jquery.min.js"></script>
		<!-- <![endif]-->
        <script src="assets/js/bootstrap.min.js"></script>            
       <title></title>
</head>
<!--&lt;!&ndash; 为 ECharts 准备一个具备大小（宽高）的 DOM &ndash;&gt;-->
<!--<div id="main" style="width: 600px;height:400px;"></div>-->
<body>
<div class="page-content clearfix">
 <div class="alert alert-block alert-success">
  <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>


           <%
             InetAddress ia=null;
             ia = InetAddress.getLocalHost();
             String addr = request.getRemoteAddr();
             String localname=ia.getHostName();
             String localip=ia.getHostAddress();
             Date date = new Date(System.currentTimeMillis());
             String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ss").format(date);
             System.out.println("本机名称是："+ localname);
             System.out.println("本机的ip是 ："+localip);
           %>



  <i class="icon-ok green"></i>欢迎使用<strong class="green">Cyberspace后台管理系统<small>(version 1.0.1)</small></strong>,你本次登录时间为<%= date_%>，登录IP:<%=localip%>
 </div>
    <!--===============================================================================-->
    <jsp:include page="bar1.jsp"/>
    <!--===============================================================================-->
    <%--<div id="container" style="width: 600px;height:400px;"></div>--%>
    <!--==============获取需要的数据=================================================================-->
    <%
        ManageService manageService = new ManageImpl();
        Count count = new Count();
        try{
            int allCountArticle = manageService.getAllCountArticle();
            int allCountComment = manageService.getAllCountComment();
            int allCountUser = manageService.getAllCountUser();
            int allCountPhoto = manageService.getAlllCountPhoto();
            count.setAllCountArticle(allCountArticle);
            count.setAllCountComment(allCountComment);
            count.setAllCountUser(allCountUser);
            count.setAlllCountPhoto(allCountPhoto);
            pageContext.setAttribute("data",count);
        }catch(Exception e){e.printStackTrace();}
    %>
    <!--===============================================================================-->
 <div class="state-overview clearfix">
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                      <a href="#" title="商城会员">
                          <div class="symbol terques">
                             <i class="icon-user"></i>
                          </div>
                          <div class="value">
                              <h1>${data.allCountUser}</h1>
                              <p>Cyberspace用户</p>
                          </div>
                          </a>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol red">
                              <i class="icon-book"></i>
                          </div>
                          <div class="value">
                              <h1>${data.allCountArticle}</h1>
                              <p>文章（日志）记录</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol yellow">
                              <i class="icon-comment"></i>
                          </div>
                          <div class="value">
                              <h1>${data.allCountComment}</h1>
                              <p>评论记录</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol blue">
                              <i class="icon-picture"></i>
                          </div>
                          <div class="value">
                              <h1>${data.alllCountPhoto}</h1>
                              <p>相册记录</p>
                          </div>
                      </section>
                  </div>
              </div>
             <!--实时交易记录-->


    <div class="clearfix">
              <div class="Order_Statistics ">
          <div class="title_name"><b>网站注册日志统计表</b></div>
           <table class="table table-bordered ">
               <thead>
               <tr>
                   <th>ID</th>
                   <th>用户名</th>
                   <th>注册身份</th>
                   <th>邮箱</th>
                   <th>注册时间</th>
               </tr>
               </thead>
               <tbody>

               <tr>
                   <td>01</td>
                   <td>Bangalore</td>
                   <td>管理员</td>
                   <td>Wang.zhuang@outlook.com</td>
                   <td>2018-12-01 12:14.28</td>
               </tr>
               <tr>
                   <td>02</td>
                   <td>Hiro</td>
                   <td>用户</td>
                   <td>12424515@qq.com</td>
                   <td>2018-12-01 12:14.28</td>
               </tr>
               <tr>
                   <td>03</td>
                   <td>Hiro</td>
                   <td>用户</td>
                   <td>12424515@qq.com</td>
                   <td>2018-12-01 12:14.28</td>
               </tr>
               <tr>
                   <td>04</td>
                   <td>Hiro</td>
                   <td>用户</td>
                   <td>12424515@qq.com</td>
                   <td>2018-12-01 12:14.28</td>
               </tr>
               <tr>
                   <td>05 </td>
                   <td>Bangalore</td>
                   <td>管理员</td>
                   <td>Wang.zhuang@outlook.com</td>
                   <td>2018-12-01 12:14.28</td>
               </tr>
                </tbody>
          </table>
         </div>
         <div class="Order_Statistics">
          <div class="title_name"><b>网站用户操作日志统计表</b></div>
           <table class="table table-bordered">
               <thead>
               <tr>
                   <th>ID</th>
                   <th>用户名</th>
                   <th>邮箱</th>
                   <th>操作</th>
                   <th>操作时间</th>
               </tr>
               </thead>
               <tbody>
                    <tr>
                        <td>01</td>
                        <td>Erick</td>
                        <td>42874asdf@163.com</td>
                        <td>用户登录</td>
                        <td>2018-05-06 12:02:03</td>
                    </tr>
                    <tr>
                        <td>02</td>
                        <td>SunshineisBright</td>
                        <td>Wang.zhuang@outlook.com</td>
                        <td>用户添加一篇日志：《JSP程序设计》</td>
                        <td>2018-05-06 12:02:03</td>
                    </tr>
                    <tr>
                        <td>03</td>
                        <td>SunshineisBright</td>
                        <td>Wang.zhuang@outlook.com</td>
                        <td>添加了一篇文章: 《玩转Python》</td>
                        <td>2018-05-06 12:02:03</td>
                    </tr>
                    <tr>
                        <td>04</td>
                        <td>SunshineisBright</td>
                        <td>Wang.zhuang@outlook.com</td>
                        <td>用户添加一篇日志：《C Primer Plus》</td>
                        <td>2018-05-06 12:02:03</td>
                    </tr>
                    <tr>
                        <td>05</td>
                        <td>SunshineisBright</td>
                        <td>Wang.zhuang@outlook.com</td>
                        <td>此用户更新了相册</td>
                        <td>2018-05-06 12:02:03</td>
                    </tr>
               </tbody>
          </table>
         </div> 
             <!--<div class="t_Record">
               <div id="main" style="height:300px; overflow:hidden; width:100%; overflow:auto" ></div>     
              </div> -->
         <div class="news_style">
          <div class="title_name">最新消息</div>
          <ul class="list">
           <li><i class="icon-bell red"></i><a href="#">用户名为“王壮2”刚才注册了管理员时间是:2018-12-01 00:00:00</a></li>
           <li><i class="icon-bell red"></i><a href="#">网站用户突破<span>52</span>个人</a></li>
           <li><i class="icon-bell red"></i><a href="#">论坛共有<span>32</span>篇文章被发布</a></li>
          </ul>
         </div> 
         </div>
 <!--记录-->
 <div class="clearfix">
  <div class="home_btn">
     <div>
     <a href="Category_Manage.html"  title="文章分类" class="btn  btn-primary btn-sm no-radius">
     <i class="bigger-200"><img src="images/icon-cpgl.png" /></i>
     <h5 class="margin-top">文章分类</h5>
     </a>
     <a href="admin_info.jsp" title="个人信息" class="btn  btn-success btn-sm no-radius">
     <i class="bigger-200"><img src="images/icon-grxx.png" /></i>
     <h5 class="margin-top">个人信息</h5>
     </a>
     <a href="Systems.html"  title="系统设置" class="btn  btn-info btn-sm no-radius">
     <i class="bigger-200"><img src="images/xtsz.png" /></i>
     <h5 class="margin-top">系统设置</h5>
     </a>
     <a href="picture-add.html"  title="添加广告" class="btn  btn-pink btn-sm no-radius">
     <i class="bigger-200"><img src="images/icon-ad.png" /></i>
     <h5 class="margin-top">添加广告</h5>
     </a>
      <a href="article_add.html"  title="添加文章" class="btn  btn-info btn-sm no-radius">
     <i class="bigger-200"><img src="images/icon-addwz.png" /></i>
     <h5 class="margin-top">添加文章</h5>
     </a>
     </div>
  </div>
 
 </div>
   
     </div>
</body>
</html>
<script type="text/javascript">
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.no-radius').on('click', function(){
	var cname = $(this).attr("title");
	var chref = $(this).attr("href");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
	parent.$('#parentIframe').css("display","inline-block");
	parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);
	
});
     $(document).ready(function(){
		 
		  $(".t_Record").width($(window).width()-640);
		  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
		 $(".t_Record").width($(window).width()-640);
		});
 });
	 
	 
 </script>   