<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/27
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .code-box{
            padding: 0 10px;
            width: px;
            height: 40px;
            color: #fff;
            text-shadow: 1px 1px 1px black;
            background: rgba(0, 0, 0, 0.16);
            border: 0;
            border-radius: 5px;
            outline: none;
            box-shadow: inset 0 1px 4px rgba(0, 0, 0, 0.3), 0 1px rgba(255, 255, 255, 0.06);
        }
        .code-box{
            position: relative;
        }
        .code-box p,
        .code-box span{
            display:block;
            position: absolute;
            left: 0;
            height: 40px;
            text-align: center;
            line-height: 40px;
            border-radius: 5px;
        }
        .code-box span{

            background-color:#fff;
            font-family: "宋体";
            font-size: 16px;
            cursor: pointer;
        }
        .code-box p{
            width: 0;
            background-color: #FFFF99;
            overflow: hidden;
            text-indent: -20px;
            transition: background 1s ease-in;
        }
        .code-box .code-input{
            display: none;
        }
    </style>
    <meta charset="utf-8">
    <title>登录- CyberSpace我的个人空间</title>
    <link rel="stylesheet" type="text/css" href="../style/register_login.css"/>
    <%--<link rel="stylesheet" type="text/css" href="css/slide_user.css">--%>
</head>
<body>
<div id="box"></div>
<div class="cent-box">
    <div class="cent-box-header">
        <h1 class="main-title hide">Cyberspace</h1>
        <h2 class="sub-title">生活 分享 精彩 - My Hub</h2>
    </div>

    <div class="cont-main clearfix">
        <div class="index-tab">
            <div class="index-slide-nav">
                <a href="user_login_1.jsp" class="active">登录</a>
                <a href="../user_regist.jsp">注册</a>
                <div class="slide-bar"></div>
            </div>
        </div>

        <form action="loginServlet.do" method="post" id="login_form">
            <!--如果登录失败将登录失败的信息进行回显-->
            <span style="color: red;">${error}</span>
            <div class="login form">
                <div class="group">
                    <div class="group-ipt email">
                        <input type="text" name="umail" id="umail" class="ipt" placeholder="邮箱地址" required>
                    </div>
                    <div class="group-ipt password">
                        <input type="password" name="upassword" id="upassword" class="ipt" placeholder="输入您的登录密码" required>
                    </div>
                    <div class="group-ipt verify">
                        <div class="code-box" id="code-box">
                            <input type="text" name="code" class="code-input" />
                            <p></p>
                            <span>>>></span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="btn">
                <button type="submit" class="login-btn register-btn" id="button" onclick="">登录</button>
            </div>

            <div class="remember clearfix">
                <label class="remember-me"><span class="icon"><span class="zt"></span></span><input type="checkbox" name="remember-me" id="remember-me" class="remember-mecheck" checked>记住我</label>
                <label class="forgot-password">
                    <a href="../user_regist.jsp">忘记密码？</a>
                </label>
            </div>
        </form>
    </div>

</div>
<script src='../js/login_particles.js' type="text/javascript"></script>
<script src='../js/login_background.js' type="text/javascript"></script>
<script src='../js/login_jquery.min.js' type="text/javascript"></script>
<script src='../js/login_layer.js' type="text/javascript"></script>
<script src='../js/login_index.js' type="text/javascript"></script>
<script src="../js/slide_login.js" type="text/javascript"></script>
<!--*********************************************************-->

    <script>

        window.addEventListener('load',function(){

            //code是后台传入的验证字符串
            var code = "jsaidaisd656",
                codeFn = new moveCode(code);

            //获取当前的code值
            //console.log(codeFn.getCode());

            //改变code值
            //code = '46asd546as5';
            //codeFn.setCode(code);

            //重置为初始状态
            //codeFn.resetCode();
        });
    </script>
<!--*********************************************************-->
<script>
    function CheckLoginData(){
            //发送请求  form  获取form
            var form =  document.getElementById("login_form");
            form.submit();//通过js提交表单 执行action
    }
</script>
</body>
</html>
