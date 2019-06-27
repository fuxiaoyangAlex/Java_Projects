<%@ page import="org.springframework.ui.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="../backgroundresources/images/bitbug_favicon.ico" />
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>推荐页面</title>
    <%@ include file="common.jsp"%>
    <style>
        a{
            color: grey;

        }
        a:hover{
            cursor:pointer;
            color:blue;
            text-decoration:none;
        }
    </style>
</head>
<body>
<form id="form" method="get" action="">
</form>
    <ul class="flow-default" id="questions"></ul>

    <script type="text/javascript">

        $(document).ready(function(){

            $('body').on("click",'.heart',function(){

                var A =$(this).attr("id");
                var B =A.split("like");
                // var C = parseInt($("#likeCount" + messageID).html());
                var C = parseInt($("#test1").text());
                $(this).css("background-position","");
                var D=$(this).attr("rel");

                if(D === 'like') {
                    $("#test1").html(C+1);
                    $(this).addClass("heartAnimation").attr("rel","unlike");
                }
                else{
                    $("#test1").html(C-1);
                    $(this).removeClass("heartAnimation").attr("rel","like");
                    $(this).css("background-position","left");
                }
            });

        });
            //加载最新的问题
            layui.use('flow', function(){
                var flow = layui.flow;

                flow.load({
                    elem: '#questions'  // 指定列表容器
                    // page 默认为1
                    , isAuto:true
                    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
                        // alert("page: -------Page------> " + page);
                        var lis = [];
                        $.get(getBaseUrl() + 'question/getNewestQuestionsDetails.do?pageindex='+ page, function(res){
                            layui.each(res, function(index, item){
                                // // ----------------------------------------------------------------------------------------
                                lis.push(
                                    '<div class="layui-card">' +
                                    '<div class="layui-card-header userHeadParentElement">' +
                                    '<img src="' + contextPath  + '/frontresources/' + item.headimg + '" class="userSmallHead"/>' +
                                    '<a class="userName" onclick="showUserInfoPage(' + String(item.userId) + ')">' + item.username + '</a>' +
                                    '<a class="userName" onclick="showQuestionDetail(' + item.questionId + ')">'+item.title + '</a>' +
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
                            console.log("RES Info ---------> " + res[1].totalnum);
                            next(lis.join(''), page < res[1].totalnum);
                            window.parent.resize(1);
                        });
                    }
                });
            });

        /**
         * 显示签名
         */
        function showSignature(signature) {
            if(isNullOrEmpty(signature)){
                return "";
            }
            if(signature.length < 15){
                return signature;
            }


                return signature.substr(0, 15) + " ... "

        }

        /**
         * 显示问题内容
         */
        // function showQuestionText(text) {
        //     if(isNullOrEmpty(text)){
        //         return "";
        //     }
        //
        //     // if(text.length < 85){
        //     //     return text;
        //     // }
        //
        //     return text.substr(0, 85) + " ... " + '<a href="#/" class="read-more" >查看全文 &raquo;</a>';
        // }

        /**
         * 显示问题回答数
         *
         * @param answersnum
         * @returns {string}
         */
        function showAnswersNum(answersnum) {
            if(undefined == answersnum || 0 == answersnum){
                return "添加回答";
            }

            return answersnum + " 条回答";
        }

        /**
         * 显示回答点赞数
         *
         * @param hotsatr
         * @returns {string}
         */
        function showAnswerStar(hotsatr) {
            if(undefined == hotsatr ||  0 == hotsatr){
                return "点赞";
            }

            return hotsatr + " 条点赞";
        }

        /**
         * 打开问题详情页面
         *
         *
         * @param str
         */
        function showQuestionDetail( str) {
            window.open(contextPath + "/jsp/question_detail.jsp?qid=" + str);
        }
    </script>


<!---------------------------第一个模态弹窗--------------------------------------------------->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加收藏
                    </h4>
                </div>
                <div class="modal-body" >
                    <div class="row pre-scrollable">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p>
                                    <b>读书笔记</b>
                                    <button type="button" class="btn btn-default" style="float:right">收藏</button>
                                </p>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p>
                                    <b>读书笔记</b>
                                    <button type="button" class="btn btn-default" style="float:right">收藏</button>
                                </p>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p>
                                    <b>读书笔记</b>
                                    <button type="button" class="btn btn-default" style="float:right">收藏</button>
                                </p>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p>
                                    <b>读书笔记</b>
                                    <button type="button" class="btn btn-default" style="float:right">收藏</button>
                                </p>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p>
                                    <b>读书笔记撒旦发射点发射点阿迪斯发啊士大夫</b>
                                    <button type="button" class="btn btn-default" style="float:right">收藏</button>
                                </p>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p>
                                    <b>读书笔记</b>
                                    <button type="button" class="btn btn-default" style="float:right">收藏</button>
                                </p>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p>
                                    <b>读书笔记</b>
                                    <button type="button" class="btn btn-default" style="float:right">收藏</button>
                                </p>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <!-- 按钮触发模态框 -->
                    <button class="btn btn-primary btn-lg"style="margin-right: 20px;text-align: center" data-toggle="modal" data-target="#myCollectionModal">
                        创建收藏夹
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>


<!---------------------------第二个模态弹窗--------------------------------------------------->

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myCollectionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:350px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                </div>
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group">
                            <input type="text" class="form-control" id="catagoryname" placeholder="收藏夹名称">
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">
                        确认创建
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

<!---------------------------第三个模态弹窗--------------------------------------------------->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myReportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 300px; margin-left: auto">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myReportLabel">
                    举报内容
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="请输入...">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    举报
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script>
    /**
    *  通过userId过去用户信息界面
    *  @param userId
    * */
    function showUserInfoPage(userId){
        alert(userId);
        $("#form").attr("action", getBaseUrl() + "/user/getUserInfo.do?userid=" + userId);
        $("#form").submit();
    }
</script>
</html>
