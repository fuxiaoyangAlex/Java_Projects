<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<script>
$(function(){
$('#navbar').on("click",'#loginbutton',function(){
    var css = $('#loginDiv').css('display');
    if(css=='block'){
        $('#loginDiv').css('display','none');
        $(this).text("登录注册");
    }else{
        $('#loginDiv').css('display','block');
        $(this).text("关闭×");
  }
})
});
</script>
  <body>
  <nav id="navbar" class="navbar navbar-default navbar-static-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
        </button>
        <a class="navbar-brand" href="/Lyon/Home" target="_blank">阳光流淌的个人主页</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[0].getName()}<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <c:forEach items="${secondcats[0]}" var="categary" varStatus="st">
                 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}"><c:out value="${categary.getName()}"/></a></li>
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
                 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}"><c:out value="${categary.getName()}"/></a></li>
              </c:forEach>
            </ul>
          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[3].getName()}<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <c:forEach items="${secondcats[3]}" var="categary" varStatus="st">
                 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}"><c:out value="${categary.getName()}"/></a></li>
              </c:forEach>
            </ul>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${categarys[4].getName()}<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <c:forEach items="${secondcats[4]}" var="categary" varStatus="st">
                 <li><a href="/Lyon/foreviewCatBlogs?Cid=${categary.getId()}"><c:out value="${categary.getName()}"/></a></li>
              </c:forEach>
            </ul>
          </li>
          <c:if test="${user==null}">
            <li id='loginLi'><a href="javascript:void(0)" onclick="function()" id="loginbutton">登录注册</a></li>
				  </c:if>
					<c:if test="${user!=null}">
            <li id='loginLi'><a href="/Lyon/forecheckLogout" target="_blank">退出登录</a></li>
					  <a><img src="images/十一月的肖邦.jpg" height="40px" width="45px" style="margin-top:4px;"/></a>
				  </c:if>
        </ul>
      </div><!--/.nav-collapse -->
    </div><!--container-->
  </nav>

  <div id="loginDiv" style="position:absolute;z-index: 99;left:25%;width:50%;height:48%;top:150px;display:none;padding:10px 30px 0px 30px;" class="container1 w3layouts agileits">
		<div class="login w3layouts agileits">
			<h2 style="color:#FFF;font-size:25px;text-align:center;margin-bottom:5px;">登 录</h2>
			<form action="" id="loginForm" method="post" onSubmit="return validate(this)">
				<input type="text" name="userid" placeholder="邮箱/手机号" required="">
				<input type="password" name="userpass" placeholder="密码" required="">
			<ul class="tick w3layouts agileits">
				<li>
					<input type="checkbox" id="brand1" value="">
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
