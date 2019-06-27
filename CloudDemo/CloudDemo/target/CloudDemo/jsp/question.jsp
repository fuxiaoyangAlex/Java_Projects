<%@ page import="com.niit.clouddemo.pojo.front.User" %><%--
  Created by IntelliJ IDEA.
  User: zhuyong
  Date: 2018/5/24  20:35
  Description:
        提问问题页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="../backgroundresources/images/bitbug_favicon.ico" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>提问问题</title>
    <%
        String contextPath = request.getContextPath();
        HttpSession s = request.getSession();
        User loginUserInfo = (User)s.getAttribute("loginUserInfo");
        String uid = loginUserInfo.getUserid();
    %>
    <%@ include file="common.jsp"%>
    <script href="<%=request.getContextPath() %>/frontresources/assets/plugins/layui/formSelects-v3.js" type="text/javascript"></script>
</head>
<body>
    <div class="layui-container container_location">
        <div class="layui-row layui-col-space10">
            <div  id="quetitle" class="layui-col-md8">
                我的问题
            </div>
        </div>

        <form id="form" class="layui-form">

            <div class="layui-row layui-col-space10 row_margin">
                <div  class="layui-col-md4">
                    <textarea id="title" style="font-size:35px;"  name="title"  placeholder="问题标题" class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-row layui-col-space10 row_margin2">
                <div class="layui-col-md10">
                    <select id="topic" xm-select="city" lay-search style="width: 100%;">
                        <option value="">添加话题</option>

                        <optgroup label="软件开发">
                            <option value="编程语言">编程语言</option>
                            <option value="轮子框架">轮子框架</option>
                            <option value="计算机科学">计算机科学</option>
                        </optgroup>
                        <optgroup label="生活">
                            <option value="学习生活">学习生活</option>
                            <option value="办公效率">办公效率</option>
                            <option value="娱乐影音">娱乐影音</option>
                            <option value="人生理财">人生理财</option>
                        </optgroup>
                        <optgroup label="科技">
                            <option value="宇宙航天">宇宙航天</option>
                            <option value="计算机硬件">计算机硬件</option>
                            <option value="计算机软件">计算机软件</option>
                        </optgroup>
                        <optgroup label="文学">
                            <option value="古典文学">古典文学</option>
                            <option value="现代诗歌">现代诗歌</option>
                        </optgroup>
                        <optgroup label="思想精神">
                            <option value="精神洗涤">精神洗涤</option>
                            <option value="奋斗人生">奋斗人生</option>
                            <option value="心灵鸡汤">心灵鸡汤</option>
                        </optgroup>

                    </select>
                </div>
            </div>

            <div class="layui-row layui-col-space10 row_margin2">
                <div class="layui-col-md8" style="text-align: left;padding-left:10px">
                    问题描述（可选）：
                </div>
            </div>

            <div class="layui-row layui-col-space10 row_margin2">
                <div class="layui-col-md8">
                    <textarea id="desc" name="desc" placeholder="问题描述" class="layui-textarea">

                    </textarea>
                </div>
            </div>

        </form>

        <div class="layui-row layui-col-space10 row_margin3">
            <div class="layui-col-md2 layui-col-md-offset2">
            </div>
            <div class="layui-col-md4" style="text-align: center">
                <button class="layui-btn layui-btn-normal" onclick="submit();">提交问题</button>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });

        //提交问题
        function submit() {
            var title = $("#title").val();  //问题标题

            console.log("问题标题：" + title);
            if(isNullOrEmpty(title)){
                layer.msg("标题不能为空");
                return false;
            }

            var tag = $("#topic").val();  //问题标签
            if(isNullOrEmpty(tag)){
                layer.msg("话题不能为空");
                return false;
            }
            console.log("问题标签：" + tag);
            var desc = $("#desc").val();   //问题描述
            console.log("问题描述: " + desc);

            var uid = '<%= uid%>';

            console.log("标题: " + title +"话题：" + tag + "问题描述：" + desc + "问题提出者uid: " + uid);
            $.ajax({
                url: getBaseUrl() + "question/addNewQuestion.do",
                type: "POST",
                data: JSON.stringify({"title":title, "desc":desc, "tag":tag, "uid":uid}),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if("添加新问题成功!" === data.message){
                        layer.msg('添加新问题成功!', {icon: 6});
                            setTimeout(function() {
                                window.parent.closeOpenPage();
                            },2000);

                    }
                    if("添加失败!" === data.message){
                        layer.msg('系统维护中！请稍后提问。', {icon: 5});
                        setTimeout(function() {
                            window.parent.closeOpenPage();
                        },1000);
                    }

                }
            });

        }
    </script>
</body>
</html>