<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/27
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>注册 - Cyberspace - </title>
    <link rel="stylesheet" type="text/css" href="style/register-login.css">
    <script type="text/javascript">
        function change(){
            var time = new Date().getTime();
            document.getElementById("imagecode").src="<%=request.getContextPath() %>/verificationCodeServlet.do?d="+time;
        }
    </script>
    <script src='js/login_particles.js' type="text/javascript"></script>
    <script src='js/login_background.js' type="text/javascript"></script>
    <script src='js/login_jquery.min.js' type="text/javascript"></script>
    <script src='js/login_layer.js' type="text/javascript"></script>
    <script src='js/login_index.js' type="text/javascript"></script>
</head>
<body>
<div id="box"></div>
<div class="cent-box register-box">
    <div class="cent-box-header">
        <h1 class="main-title hide">Cyberspace</h1>
        <h2 class="sub-title">生活 分享 精彩 - Cyberspace</h2>
    </div>

    <div class="cont-main clearfix">
        <div class="index-tab">
            <div class="index-slide-nav">
                <a href="user_login.jsp">登录</a>
                <a href="user_regist.jsp" class="active">注册</a>
                <div class="slide-bar slide-bar1"></div>
            </div>
        </div>
        <form action="registServlet.do" method="post" id="reg_form">
            <div class="login form">
                <div class="group">
                    <div class="group-ipt email">
                        <input type="email" name="umail" id="email" class="ipt" placeholder="邮箱地址" onfocus="DZYXonfocu()" onblur="DZYXonblus()" required>
                        <span id="verify_email"></span>

                    </div>
                    <div class="group-ipt user">
                        <input type="text" name="uname" id="userName" class="ipt" placeholder="选择一个用户名" onfocus="YHMonfocu" onblur="YHMonblus()"  required>
                        <span id="verify_user"></span>
                    </div>
                    <div class="group-ipt password">
                        <input type="password" name="upassword" id="password" class="ipt" placeholder="设置登录密码" onfocus="MMonfocu()" onblur="MMonblus()" required>
                        <span id="verify_password"></span>
                    </div>
                    <div class="group-ipt password1">
                        <input type="password" name="password1" id="confirmPassword" class="ipt" placeholder="重复密码" onfocus="QRMMonfocu()" onblur="QRMMonblus()" required>
                        <span id="verify_repassword"></span>
                    </div>
                    <div class="group-ipt verify">
                        <input type="text" name="checkcode" id="verify" class="ipt" placeholder="输入验证码" required>
                        <img alt="验证码" id="imagecode" class="imgcode" onClick="change()" src="<%=request.getContextPath()%>/verificationCodeServlet.do"/>
                    </div>
                </div>
            </div>
            <div class="button">
                <button  class="login-btn register-btn" id="button" onclick="CheckRegistData()">注册</button>
            </div>
        </form>
    </div>
</div>


<script>
    $('.imgcode').hover(function(){
        layer.tips("看不清？点击更换", '.verify', {
            time: 1000,
            tips: [2, "＃66FFFF"]
        })
    },function(){
        layer.closeAll('tips');
    });

    $(".login-btn").click(function(){
        var email = $("#email").val();
        var password = $("#password").val();
        var verify = $("#verify").val();


    });
    $("#remember-me").click(function(){
        var n = document.getElementById("remember-me").checked;
        if(n){
            $(".zt").show();
        }else{
            $(".zt").hide();
        }
    })
</script>
<!--==================================校验内容======================================================================-->
<!--
    JavaScript校验要求：
    1.每一项都必须填写内容或者做出选择，不能存在空项；+++++++++


    2.用户名：只能包含英文字母和下划线，长度为6-18个字符；++++++++


    3.密码：必须包含英文字母大小写和数字，长度不能少于6个字符；++++++
    4.确认密码：必须与密码相同；


    5.联系电话：确保手机号码的有效性；+++++++++
    6.电子邮箱：确保电子邮箱的有效性；+++++++++

    7.如果用户没有按照以上要求输入信息，则当光标离开该项时，在该项的右侧用红色字体给出相应的提示信息。++++++
-->


<!--==================================================电子邮箱校验=============================================-->
<script>
    var Flag = false;
    function DZYXonblus(){
        var email=document.getElementById("email");
        var re= /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        if(email.value===""){
            Flag = false;
            document.getElementById('verify_email').innerHTML="<font color=red>请输入电子邮箱</font>";
        }
        else if(!re.test(email.value)){
            Flag = false;
            document.getElementById("verify_email").innerHTML="<font color=red>邮箱格式不正确</font>";
        }
        else {
            Flag = true;
            document.getElementById('verify_email').innerText ="";
        }
    }
    function DZYXonfocu(){
        document.getElementById('verify_email').innerText ="";
    }



    <!--==================================================用户名校验=============================================-->
    //    用户名

    function YHMonblus(){
        var userName = document.getElementById("userName");
        var reN =/^\d{6,18}$/;
        // var re = /^[a-zA-Z_]{6,18}$/;
        if(userName.value===""){
            Flag = false;
            document.getElementById('verify_user').innerHTML="<font color=red>请输入用户名</font>";
        }
        else if(userName.value.length < 6 ||userName.value.length > 18){
            console.log(userName.value);
            Flag = false;
            document.getElementById('verify_user').innerHTML="<font color=red>格式错误,长度应为6-18个字符</font>";
        }
        // else if(!re.test(userName.value)){
        //     Flag = false;
        //     document.getElementById('verify_user').innerHTML="<font color=red>格式错误,只能包含英文字母和下划线</font>";
        // }
        else {
            Flag = true;
            document.getElementById('verify_user').innerText ="";
        }
    }
    function YHMonfocu(){
        document.getElementById('verify_user').innerText ="";
    }

    <!--==================================================密码校验=============================================-->
    //   密码
    function MMonblus(){
        var password=document.getElementById("password");
        var re = /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z]{6,}$/;
        // var reg=/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;
        if(password.value===""){
            Flag = false;
            document.getElementById('verify_password').innerHTML="<font color=red>请输入密码</font>";
        }
        else if(password.value.length < 6){
            Flag = false;
            document.getElementById('verify_password').innerHTML="<font color=red>格式错误,,密码长度至少为6位</font>";
        }

        else if(!re.test(password.value)){
            Flag = false;
            document.getElementById('verify_password').innerHTML="<font color=red>格式错误,必须包含英文字母大小写和数字</font>";
        }
        else {
            Flag = true;
            document.getElementById('verify_password').innerText ="";
        }
    }
    function MMonfocu(){
        document.getElementById('verify_password').innerText ="";
    }

    <!--==================================================重复密码校验=============================================-->
    //    确认密码
    function QRMMonblus(){
        var password=document.getElementById("password");
        var confirmPassword=document.getElementById("confirmPassword");
        if(confirmPassword.value===""){
            Flag = false;
            document.getElementById('verify_repassword').innerHTML="<font color=red>请输入确认密码</font>";
        }
        else if(password.value !== confirmPassword.value){
            Flag = false;
            document.getElementById('verify_repassword').innerHTML="<font color=red>两次密码输入不一致</font>";
        }
        else {
            Flag = true;
            document.getElementById('verify_repassword').innerText ="";
        }
    }
    function QRMMonfocu(){
        document.getElementById('verify_repassword').innerText ="";
    }
    function CheckRegistData(){
        if(Flag === true){
            // alert("检测录入表单数据正确！");
            //发送请求  form  获取form
            var form =  document.getElementById("reg_form");
            form.submit();//通过js提交表单 执行action
        }else{
            alert("检测录入注册数据错误！");
        }
    }
</script>
</body>
</html>
