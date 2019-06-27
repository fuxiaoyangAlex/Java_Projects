<%@ page import="com.niit.clouddemo.pojo.front.User" %><%--
  Created by IntelliJ IDEA.
  User: zhuyong
  Date: 2018/5/27 13:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--引入Layui样式--%>
        <link rel="shortcut icon" href="../backgroundresources/images/bitbug_favicon.ico" />
        <link rel="stylesheet" href="<%=request.getContextPath() %>/frontresources/assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/frontresources/assets/plugins/jQuery/jquery-3.3.1.min.js"></script>
        <%--引入Layui脚本--%>
        <script src="<%=request.getContextPath() %>/frontresources/assets/plugins/layui/layui.all.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>问题详情页面</title>
    <style type="text/css">
        #hotindexfont{
            font-family: "STXingkai"
        }

         a{
             color: grey;

         }
        a:hover{
            cursor:pointer;
            color:blue;
            text-decoration:none;
        }

    </style>
    <%
        String contextPath = request.getContextPath();
        HttpSession s = request.getSession();
        User    loginUserInfo = (User)s.getAttribute("loginUserInfo");
        System.out.println(loginUserInfo + loginUserInfo.getUsername() + "JSPdsafdaf");

    %>

    <script type="text/javascript">
        $(function(){
            var qid = GetQueryString("qid");
            $.ajax({
                url:  getBaseUrl() + "question/getDetailQuestionInfo.do",
                type: "POST",
                contentType:"application/json",
                data: JSON.stringify({"qid":qid}),
                success: function (data) {
                        $("#questionTitle").append("<p><b>" + data.title + "</b>"+"<i class=\"layui-icon  layui-icon-fire item-margin\"  style=\" color: #DC143C;\"></i><span id=\"hotindexfont\"> "+data.hotindex + "</span></p>" );
                        $("#questionText").append("<p>" + data.desc + "</p>");
                        $("#questionusername").append("<b>" + data.username + "</b>");
                        $("#questionusersignature").text(data.signature);
                }
            });



            //加载最新的问题
            layui.use('flow', function(){
                var flow = layui.flow;

                flow.load({
                    elem: '#answerinfowithuser'  // 指定列表容器
                    // page 默认为1
                    , isAuto:true
                    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
                        // alert("page: -------Page------> " + page);
                        var lis = [];
                        $.ajax({
                            url:  getBaseUrl() + "answer/getAnswerInfoandUserInfoByQID.do",
                            type: "POST",
                            contentType:"application/json",
                            data: JSON.stringify({"qid":qid}),
                            success: function (data) {
                                layui.each(data, function(index, item){

                                    // // ----------------------------------------------------------------------------------------
                                    lis.push(
                                        '<div class="layui-card">' +
                                        '<div class="layui-card-header userHeadParentElement">' +
                                        '<img src="' + contextPath  + '/frontresources/' + item.headimg + '" class="userSmallHead"/>' +
                                        '<a class="userName" onclick="showUserInfoPage(' + String(item.userId) + ')">' + item.username + '</a>' +
                                        '<a class="userSignature">' + showSignature(item.signature) + '</a>' +
                                        '</div>' +
                                        '<div class="layui-card-body">' +

                                        '<div class="layui-row questionDetailText">' +
                                        item.content +
                                        '</div>' +
                                        '<div class="layui-row questionAction">' +

                                        '<i class="layui-icon  item-margin"></i>&nbsp;&nbsp;'+
                                        '<div class="likeCount" id="likeCount1">'+'<p id="test1">'+item.hotstar+'</p>'+ '</div>' +

                                        '<a  data-toggle="modal"  data-target="#myCategoryModal"><i class="layui-icon layui-icon-rate-solid item-margin"></i>&nbsp;&nbsp;收藏&nbsp;&nbsp;&nbsp;&nbsp;'+'</a>' +
                                        '<i class="iconfont icon-pinglun"></i>&nbsp;&nbsp;' + showAnswersNum(item.answerId) +
                                        '<a  data-toggle="modal"  data-target="#myReportModal"><i class="layui-icon layui-icon-flag item-margin"></i>&nbsp;&nbsp;举报'+'</a>'  +
                                        '<li class="heart" id="like1" rel="like"></li>'+
                                        '</div>' +
                                        '</div>' +
                                        '</div>'
                                    );
                                    // ----------------------------------------------------------------------------------------
                                });
                                // TODO: res[1].totalnum 为Ajax返回的总页数
                                // 执行下一页渲染，第二参数为：“满足”加载更多的条件，即后面仍有分页
                                // TOTAL_PAGE_NUM为Ajax返回的总页数，只有当前页小于总页数的情况下，才会
                                // 继续出现加载更多
                                next(lis.join(''), page < 100);

                            }
                        });

                    }
                });
            });



        });


        function GetQueryString(name)
        {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null){
                return unescape(decodeURI(r[2]));
                //return unescape(r[2]);
            }

            return null;
        }
    </script>
    <%@ include file="common.jsp"%>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/frontresources/assets/css/index.css"/>
</head>
<body>

    <div class="layui-card" style="padding-top: 20px">
        <div class="layui-container">
            <div id="header" class="layui-row layui-col-space30">
                <%--左侧内容区--%>
                <div class="layui-col-md8">
                    <div class="layui-row layui-col-space10">
                        <%--标志--%>
                        <div class="layui-col-md2">
                            <img src="<%=request.getContextPath()%>/frontresources/assets/images/webimages/yunzhi.png">
                        </div>
                        <%--首页--%>
                        <div class="layui-col-md2">
                            <button class="layui-btn layui-btn-primary" onclick="loadIndex();">首页</button>
                        </div>
                        <%--搜索--%>
                        <div class="layui-col-md6">
                            <input type="text" name="title" placeholder="请输入搜索内容" autocomplete="off" class="layui-input">
                        </div>
                        <%--提问--%>
                        <div class="layui-col-md2">
                            <button class="layui-btn layui-btn-normal" onclick="addQuestion();">提问</button>
                        </div>
                    </div>
                </div>

                <%--右侧内容区--%>
                <div class="layui-col-md4">
                    <%--个人信息--%>
                    <div id="userinfo"  class="layui-row layui-col-space20">
                        <div class="layui-col-md2">
                            <img id="userHeadImg" src="<%=request.getContextPath()%>/frontresources/<%= loginUserInfo.getHeadimg()%>" name="file" class="userMiddleHead" onclick="showUserPage();"/>
                        </div>

                        <div class="layui-col-md10">
                            <div  class="layui-row">
                                <div class="layui-col-md10" onclick="showUserPage();">
                                    <div class="layui-card">
                                        <div class="layui-card-header" style="height: 20px;line-height: 20px;">
                                            <p id="username" class="indexUserName"><%= loginUserInfo.getUsername()%></p>
                                        </div>
                                        <div class="layui-card-body" style="padding-top:5px;padding-bottom: 0; line-height: 20px;">
                                            <p id="signature" class="indexUserSignature"><%= loginUserInfo.getSignature()%></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-col-md2">
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
            <div id="questionAnswers" class="layui-col-md8">
                <%-- TODO 问题详情--%>
                <div class="layui-card">
                    <div id="questionTitle" class="layui-card-header questitle">
                        <!-- 问题的标题-->


                    </div>
                    <div class="layui-card-body " style="line-height: 20px;">
                        <div id="questionuserinfo">
                            <i class="layui-icon layui-icon-username"></i><a href="#" ><span id="questionusername"></span></a>
                                <span id="questionusersignature"></span><br/>
                                &nbsp;
                        </div>
                        <div id="questionText"  class="layui-row">
                            <p></p>
                            <!-- 问题的具体内容以及所提问用户的信息-->
                        </div>

                        <div class="layui-row questionAction">
                            <button onclick="attentionQuestion();" class="layui-btn layui-btn-normal layui-btn-sm" style="padding-left: 10px;padding-right: 10px" >
                                <i class="layui-icon layui-icon-add-1"></i>关注问题
                            </button>
                            <i class="iconfont icon-pinglun" style="margin-left: 30px"></i>
                                &nbsp;<p id="answersNum"></p>
                            <i class="layui-icon layui-icon-share item-margin"></i>
                                &nbsp;分享
                        </div>
                    </div>
                </div>

                    <input id="temp" type="text" value="<%=loginUserInfo.getUserid()%>" style="display: none;">
                    <script type="text/javascript">
                        function attentionQuestion() {
                            $(function(){
                                var temp = "<%=loginUserInfo.getUserid()%>";
                                    $.ajax({
                                        url: getBaseUrl() + "attention/addAttentionQuestion.do",
                                        type: "POST",
                                        data: JSON.stringify({"userid":temp, "questionid":qid}),
                                        contentType:"application/json",
                                        dataType: "json",
                                        success: function (data) {
                                            if("关注成功!" === data.message){
                                                layer.alert('关注成功!', {icon: 6});
                                            }
                                            if("已经关注了!" === data.message){
                                                layer.msg('已经关注了!', function(){
                                                    //关闭后的操作
                                                });
                                            }

                                        }
                                    });

                            });


                        }
                    </script>
                <%--问题回答内容区：动态展示问题相关的回答或者显示回答问题编辑界面--%>
                <div class="layui-collapse" style="margin-bottom: 30px;">
                    <div class="layui-colla-item">
                        <h2 class="layui-colla-title" style="background-color: white">写回答</h2>
                        <div class="layui-colla-content layui-show" style="padding: 0">
                            <div class="layui-card-body" style="padding: 0">
                                <!-- 问题回答内容编辑区域 -->
                                <script id="container" name="content" type="text/plain"></script>
                                <div class="layui-row" style="text-align: right">
                                    <button class="layui-btn  layui-btn-normal layui-btn-sm" style="margin-top: 20px;" onclick="addAnswerInfo();">
                                        提交
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--问题所有回答--%>
                <ul class="flow-default" id="allAnswersAndUserInfo">
                    <div class="row pre-scrollable" id="allanswerinfowithuser">



                        <div class="panel panel-default">
                            <ul class="flow-default" id="answerinfowithuser">

                            </ul>
                        </div>
                    </div>

                </ul>


            </div>
            <div class="layui-col-md4">
                <%--个人提醒--%>
                <div id="usertips"  class="layui-row">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-primary">
                            <i class="layui-icon layui-icon-release" style="font-size: 25px; color: #1E9FFF;"></i>&nbsp;&nbsp;消息
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
                        相关话题
                    </div>
                </div>

                <div class="layui-row">
                    <%--相关问题--%>
                    <div class="layui-card" style="height: 400px;margin-top: 30px">
                        相关问题
                        <%--<table class="layui-hide" id="relativeQuestions"></table>--%>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <form id="form" method="post" action="">
        <input id="qid" name="qid" style="display: none"/>
    </form>

    <script type="text/javascript">
        //问题ID
        var qid = "<%=request.getParameter("qid")%>";
        $("#qid").val(qid);
    </script>

    <%--引入UEditor相关CSS/JS--%>
    <link href="<%=request.getContextPath() %>/frontresources/assets/plugins/ueditor/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/frontresources/assets/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/frontresources/assets/plugins/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/frontresources/assets/plugins/ueditor/ueditor.parse.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/frontresources/assets/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>

    <%--引入逻辑处理JS--%>
    <script src="<%=request.getContextPath() %>/frontresources/assets/js/question_detail.js"></script>


    <!----------------------------------回答---------------------------------------------------------------------------->

    <script type="text/javascript">
        var ue = UE.getEditor('container');
        /**
         * 提交回答
         */
        function addAnswerInfo() {



            var content = UE.getEditor('container').getContentTxt(); // 回答内容
            console.log("内容：" + content);
            if(isNullOrEmpty(content)){
                layer.tips('回答内容为空！', '#container', {
                    tips: [1, '#3595CC'],
                    time: 4000
                });
                return;
            }

            $(function () {
                layer.msg('确定提交回答？', {
                    time: 0 //不自动关闭
                    ,btn: ['是', '再想想']
                    ,yes: function(index){
                        layer.close(index);
                        var questionid = qid; // 回答问题qid
                        var userid = '<%= loginUserInfo.getUserid()%>'; // 用户uid
                        console.log("回答内容：" + content + "回答问题qid: " + questionid + "回答用户uid: " + userid);
                        $.ajax({
                            url:  getBaseUrl() + "answer/addAnswer.do",
                            type: "POST",
                            data:JSON.stringify({"questionid": questionid, "userid": userid, "content": content}),
                            contentType:"application/json",
                            dataType: "json",
                            success:function (data) {
                                if("提交成功!" === data.message ){

                                    $(function(){
                                        //加载最新的问题
                                        layui.use('flow', function(){
                                            var flow = layui.flow;

                                            flow.load({
                                                elem: '#answerinfowithuser'  // 指定列表容器
                                                // page 默认为1
                                                , isAuto:true
                                                ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
                                                    // alert("page: -------Page------> " + page);
                                                    var lis = [];
                                                    $.ajax({
                                                        url:  getBaseUrl() + "answer/getAnswerInfoandUserInfoByQID.do",
                                                        type: "POST",
                                                        contentType:"application/json",
                                                        data: JSON.stringify({"qid":qid}),
                                                        success: function (data) {
                                                            layui.each(data, function(index, item){

                                                                // // ----------------------------------------------------------------------------------------
                                                                lis.push(
                                                                    '<div class="layui-card">' +
                                                                    '<div class="layui-card-header userHeadParentElement">' +
                                                                    '<img src="' + contextPath  + '/frontresources/' + item.headimg + '" class="userSmallHead"/>' +
                                                                    '<a class="userName" onclick="showUserInfoPage(' + String(item.userId) + ')">' + item.username + '</a>' +
                                                                    '<a class="userSignature">' + showSignature(item.signature) + '</a>' +
                                                                    '</div>' +
                                                                    '<div class="layui-card-body">' +

                                                                    '<div class="layui-row questionDetailText">' +
                                                                    item.content +
                                                                    '</div>' +
                                                                    '<div class="layui-row questionAction">' +

                                                                    '<i class="layui-icon  item-margin"></i>&nbsp;&nbsp;'+
                                                                    '<div class="likeCount" id="likeCount1">'+'<p id="test1">'+item.hotstar+'</p>'+ '</div>' +

                                                                    '<a  data-toggle="modal"  data-target="#myCategoryModal"><i class="layui-icon layui-icon-rate-solid item-margin"></i>&nbsp;&nbsp;收藏&nbsp;&nbsp;&nbsp;&nbsp;'+'</a>' +
                                                                    '<i class="iconfont icon-pinglun"></i>&nbsp;&nbsp;' + showAnswersNum(item.answerId) +
                                                                    '<a  data-toggle="modal"  data-target="#myReportModal"><i class="layui-icon layui-icon-flag item-margin"></i>&nbsp;&nbsp;举报'+'</a>'  +
                                                                    '<li class="heart" id="like1" rel="like"></li>'+
                                                                    '</div>' +
                                                                    '</div>' +
                                                                    '</div>'
                                                                );
                                                                // ----------------------------------------------------------------------------------------
                                                            });
                                                            // TODO: res[1].totalnum 为Ajax返回的总页数
                                                            // 执行下一页渲染，第二参数为：“满足”加载更多的条件，即后面仍有分页
                                                            // TOTAL_PAGE_NUM为Ajax返回的总页数，只有当前页小于总页数的情况下，才会
                                                            // 继续出现加载更多
                                                            next(lis.join(''), page < 100);

                                                        }
                                                    });

                                                }
                                            });
                                        });
                                    });
                                    layer.alert('提交成功!', {
                                        icon: 1,
                                        skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
                                    })
                                }
                            }
                        });

                    }
                });

            });
        }

    </script>
    <!----------------------------------回答---------------------------------------------------------------------------->
</body>
</html>

