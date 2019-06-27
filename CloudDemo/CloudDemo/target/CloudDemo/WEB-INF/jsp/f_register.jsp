<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户注册页面</title>

    <%@ include file="../../jsp/common.jsp"%>

    <style type="text/css">
        .layui-col-space10{
            margin-top: 5px;
        }

        body{
            background:url(<%=request.getContextPath() %>/frontresources/assets/images/webimages/bg_idev.png)  no-repeat center center;
            background-size:cover;
            background-attachment:fixed;
            background-color:#CCCCCC;
        }
    </style>
</head>
<body>
    <div id="register" class="layui-container">
        <div class="layui-row layui-col-space10">
            <div class="layui-col-md2 layui-col-md-offset2">
<%--                <img src="<%=request.getContextPath() %>/assets/images/symbol/logo_clouddemo.png"/>--%>

            </div>
            <div  id="solgan" class="layui-col-md4">
                <img src="<%=request.getContextPath() %>/frontresources/assets/images/webimages/logo_clouddemo.png"/>

            </div>
        </div>
<%--        <img src="<%=request.getContextPath() %>/assets/images/symbol/logo_clouddemo.png"/>--%>
        <div id="recont" class="layui-row layui-col-space10">
            <div class="layui-col-md2 layui-col-md-offset2">
                <p class="label">用户名</p>
            </div>
            <div class="layui-col-md4">
                <input type="text" id="username" name="username" placeholder="请设置用户名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-row layui-col-space10">
            <div class="layui-col-md2 layui-col-md-offset2">
                <p class="label">密&nbsp;&nbsp;&nbsp;码</p>
            </div>
            <div class="layui-col-md4">
                <input type="password" id="password" name="password" placeholder="请设置您的密码" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-row layui-col-space10">
            <div class="layui-col-md2 layui-col-md-offset2">
                <p class="label">手机号</p>
            </div>
            <div class="layui-col-md4">
                <input type="text" id="phone" name="phone" placeholder="请输入你的手机号" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-row layui-col-space10">
            <div class="layui-col-md2 layui-col-md-offset2">
                <p class="label">验证码</p>
            </div>
            <div class="layui-col-md2">
                <input type="text" id="vercode" name="vercode" placeholder="请输入验证码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-col-md2">
                <button id="btncode" class="layui-btn layui-btn-primary" onclick="getVercode();">获取验证码</button>
            </div>
        </div>

        <div class="layui-row layui-col-space10">
            <div class="layui-col-md2 layui-col-md-offset2">
            </div>
            <div class="layui-col-md4">
                <button class="layui-btn layui-btn-fluid  layui-btn-normal" onclick="register();">注册</button>
            </div>
        </div>


        <div class="layui-row layui-col-space10">
            <div class="layui-col-md2 layui-col-md-offset2">
            </div>
            <div class="layui-col-md4">
                <div class="layui-row">
                    <div class="layui-col-md6">
                        <p class="label2">已有账号？</p>
                </div>
                    <div class="layui-col-md6">
                        <button onclick="toLoginPage();" class="layui-btn layui-btn-primary">登录</button>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <form id="form" method="post" action="">
    </form>

    <script type="text/javascript">
        //获取验证码
        function getVercode(){
            var phone = $("#phone").val();
            if(isNullOrEmpty(phone) || !isPhoneValidate(phone)){
                layer.msg("请正确填写手机号");
                return;
            }


            $.ajax({
                url:  getBaseUrl() + "register/getAuthCode.do?phone=" + phone,
                type: "GET",
                dataType: "json",
                success:function (data) {
                    if(1 != data.code){
                        layer.msg(data.message);
                    }
                },
                complete:function () {
                    $("#btncode").text("重新发送");
                },
            });
        }

        //用户注册
        function register() {
            //用户名验证
            var username = $("#username").val();
            if(isNullOrEmpty(username) || !validateUsername(username)){
                layer.msg("用户名2到10位汉字或英文字母组成");
                return;
            }

            //密码验证
            var password = $("#password").val();
            if(isNullOrEmpty(password) || !validatePassword(password)){
                layer.msg("密码6到20位非重复数字、字母组成");
                return;
            }

            //手机号验证
            var phone = $("#phone").val();
            if(isNullOrEmpty(phone) || !isPhoneValidate(phone)){
                layer.msg("请正确填写手机号");
                return;
            }

            //验证码格式验证
            var vercode = $("#vercode").val();
            if(isNullOrEmpty(vercode) || !isVercodeValidate(vercode)){
                layer.msg("请正确填写验证码");
                return;
            }

            alert("---------->" + "username: " + username + "password：" + password + "phone: " + phone + "vercode: " + vercode);

            $.ajax({
                // getBaseUrl() + "register/getAuthCode.do?phone=" + phone,
                url: getBaseUrl() + "register/registerValidate.do",
                type: "POST",
                data: JSON.stringify(
                    {
                        username:username,
                        password:password,
                        phone:phone,
                        vercode:vercode
                    }
                    ),
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success:function (data) {
                    if("手机号已经注册过!" === data.message){
                        layer.msg(data.message);
                    }else if("注册失败!"){
                        layer.msg(data.message);
                    }else{
                        layer.msg(data.message);
                        setTimeout(function() {
                            $("#form").attr("action", getBaseUrl() + "login/getLoginPage.do");
                            $("#form").submit();
                        },2000);
                    }



                }
            });
        }
    </script>
</body>
</html>
