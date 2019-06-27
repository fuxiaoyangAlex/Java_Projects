<%@ page import="com.niit.clouddemo.pojo.front.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="./backgroundresources/images/bitbug_favicon.ico" />
    <%
        String contextPath = request.getContextPath();
        HttpSession s = request.getSession();
        User loginUserInfo = (User)s.getAttribute("loginUserInfo");
        String uid = loginUserInfo.getUserid();
    %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>CloudDemo首页</title>

    <%--引入公共JSP页面--%>
    <%@ include file="jsp/common.jsp"%>
    <%--引入首页样式--%>
    <link rel="stylesheet" href="<%=contextPath%>/frontresources/assets/css/index.css"/>

</head>
<body>

<%--<div class="heart" id="like1" rel="like"></div> <div class="likeCount" id="likeCount1">14</div>--%>

<%--<script type="text/javascript">--%>
<%--    $(document).ready(function(){--%>

<%--        $('body').on("click",'.heart',function(){--%>

<%--            var A=$(this).attr("id");--%>
<%--            var B=A.split("like");--%>
<%--            var messageID=B[1];--%>
<%--            var C=parseInt($("#likeCount" + messageID).html());--%>
<%--            $(this).css("background-position","")--%>
<%--            var D=$(this).attr("rel");--%>

<%--            if(D === 'like') {--%>
<%--                $("#likeCount"+messageID).html(C+1);--%>
<%--                $(this).addClass("heartAnimation").attr("rel","unlike");--%>
<%--            }--%>
<%--            else{--%>
<%--                $("#likeCount"+messageID).html(C-1);--%>
<%--                $(this).removeClass("heartAnimation").attr("rel","like");--%>
<%--                $(this).css("background-position","left");--%>
<%--            }--%>
<%--        });--%>

<%--    });--%>
<%--</script>--%>
<div class="layui-card" style="padding-top: 20px">
    <div class="layui-container">
        <div id="header" class="layui-row layui-col-space30">
            <%--左侧内容区--%>
            <div class="layui-col-md8">
                <div class="layui-row layui-col-space10">
                    <%--标志--%>
                    <div class="layui-col-md2">
                        <img src="<%=contextPath%>/frontresources/assets/images/webimages/yunzhi.png" />
                    </div>
                    <%--首页--%>
                    <div class="layui-col-md2">
                        <button class="layui-btn layui-btn-primary" onclick="loadIndex();">首页</button>
                    </div>
                    <%--搜索--%>
                    <div class="layui-col-md5">
                        <input type="text" id="words" name="words" placeholder="请输入搜索内容" autocomplete="off" class="layui-input">
                    </div>
                    <%--提问--%>
                    <div class="layui-col-md3">
                        <button class="layui-btn layui-btn-primary" onclick="search();">搜索</button>
                        <button class="layui-btn layui-btn-normal" onclick="addQuestion(<%= uid%>);">提问</button>
                    </div>
                </div>
            </div>

            <%--右侧内容区--%>
            <div class="layui-col-md4">
                <%--个人信息--%>
                <div id="userinfo"  class="layui-row layui-col-space20">
                    <div class="layui-col-md2">
                        <img id="userHeadImg" src="<%=contextPath%>/frontresources/<%=loginUserInfo.getHeadimg()%>" class="userMiddleHead" onclick="showUserPage();"/>
                    </div>

                    <div class="layui-col-md10">
                        <div  class="layui-row">
                            <div class="layui-col-md10" onclick="showUserPage();">
                                <div class="layui-card">
                                    <div class="layui-card-header" style="height: 20px;line-height: 20px;">
                                        <p id="username" class="indexUserName"><%=loginUserInfo.getUsername()%></p>
                                    </div>
                                    <div class="layui-card-body" style="padding-top:5px;padding-bottom: 0; line-height: 20px; color: #007DDB">
                                        <p id="signature" class="indexUserSignature"><%=loginUserInfo.getSignature()%></p>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-md2" >
                                <button class="layui-btn layui-btn-primary layui-btn-sm" style="margin-top: 20px" onclick="logout();">退出</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="layui-container">
    <div  id="center" class="layui-row layui-col-space30">
        <div class="layui-col-md8">
            <%--导航选项卡--%>
            <div id="navigation"  class="layui-row layui-col-space10">
                <div class="layui-tab layui-tab-brief" lay-filter="navigations">
                    <ul class="layui-tab-title  navtab">
                        <li lay-id="newest" class="layui-this">
                            <i class="layui-icon layui-icon-star"></i> &nbsp;最新
                        </li>
                        <li lay-id="attention" >
                            <i class="layui-icon layui-icon-user"></i>&nbsp;关注
                        </li>
                        <li lay-id="recommendation " >
                            <i class="layui-icon layui-icon-fire"></i>&nbsp;推荐
                        </li>
                        <li lay-id="discovery" >
                            <i class="layui-icon layui-icon-find-fill"></i>&nbsp;发现
                        </li>
                        <li lay-id="answer" >
                            <i class="layui-icon layui-icon-edit"></i>&nbsp;回答
                        </li>
                    </ul>
                    <div class="layui-tab-content" style="padding: 20px 0 0 0">
                        <!-- 🔴最新内容 -->
                        <div class="layui-tab-item">
                            <%--加载问题相关信息--%>
                            <iframe id="newestQuestionsFrame" src="jsp/newest_questions.jsp"></iframe>
                        </div>
                        <!-- 🔴关注内容 -->
                        <div class="layui-tab-item">内容2</div>
                        <!-- 🔴热门（推荐）内容 -->
                        <div class="layui-tab-item layui-show">
                            <iframe id="hotQuestionsFrame" src="jsp/hot_questions.jsp"></iframe>
                        </div>
                        <!-- 🔴提供回答  -->
                        <div class="layui-tab-item">内容3</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md4">
            <%--个人提醒--%>
            <div id="usertips"  class="layui-row">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-primary">
                        <i class="layui-icon layui-icon-release" style="font-size: 25px; color: #1E9FFF;"></i>&nbsp;&nbsp;消息
                        <span class="layui-badge-dot layui-bg-blue"></span>
                    </button>
                    <button class="layui-btn layui-btn-primary">
                        <i class="layui-icon layui-icon-face-surprised" style="font-size: 25px; color: #1E9FFF;"></i>&nbsp;&nbsp;问题
                    </button>
                    <button class="layui-btn layui-btn-primary">
                        <i class="layui-icon layui-icon-edit" style="font-size: 25px; color: #1E9FFF;"></i>&nbsp;&nbsp;回答
                    </button>
                </div>
            </div>

            <div class="layui-row">
                <div class="layui-card" style="height: 200px;width: 100%">
                    <table class="layui-table">
                        <tbody>
                        <tr>
                            <td>
                                <i class="layui-icon layui-icon-rate-solid" style="font-size: 25px; color: #77839c;"></i>
                                &nbsp;&nbsp;我的收藏
                            </td>
                            <td class="td_text">
                                <span class="layui-badge layui-bg-gray">1</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i class="layui-icon layui-icon-face-smile-b" style="font-size: 25px; color: #77839c;"></i>
                                &nbsp;&nbsp;我的关注
                            </td>
                            <td class="td_text">
                                <span class="layui-badge layui-bg-gray">1</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i class="layui-icon layui-icon-praise" style="font-size: 25px; color: #77839c;"></i>
                                &nbsp;&nbsp;我的点赞
                            </td>
                            <td class="td_text">
                                <span class="layui-badge layui-bg-gray">1</span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <!-- 条目中可以是任意内容，如：<img src=""> -->
                <div class="layui-carousel" id="test1">
                    <div carousel-item>
                        <div><img src="<%=contextPath%>/frontresources/assets/images/advertiseimages/ad09.jpg" alt=""/></div>
                        <div><img src="<%=contextPath%>/frontresources/assets/images/advertiseimages/ad07.jpg" alt=""/></div>
                        <div><img src="<%=contextPath%>/frontresources/assets/images/advertiseimages/ad08.png"   alt=""/></div>
                        <div><img src="<%=contextPath%>/frontresources/assets/images/advertiseimages/ad06.jpg"  alt=""/></div>
                        <div><img src="<%=contextPath%>/frontresources/assets/images/advertiseimages/ad02.png" width="120%"  alt=""/></div>
                        <div><img src="<%=contextPath%>/frontresources/assets/images/advertiseimages/ad08.jpg" width="120%"  alt=""/></div>
                    </div>
                </div>
                <!-- 条目中可以是任意内容，如：<img src=""> -->


                <script>
                    layui.use('carousel', function(){
                        var carousel = layui.carousel;
                        //建造实例
                        carousel.render({
                            elem: '#test1'
                            ,width: '100%' //设置容器宽度
                            ,arrow: 'always' //始终显示箭头
                            ,anim: 'updown' //切换动画方式
                        });
                    });
                </script>
            </div>

            </div>

        </div>

    </div>
</div>

<form id="form" method="post" action="">
</form>

<%--引入逻辑处理JS--%>
<script src="<%=request.getContextPath() %>/frontresources/assets/js/index.js"></script>


</body>
</html>
