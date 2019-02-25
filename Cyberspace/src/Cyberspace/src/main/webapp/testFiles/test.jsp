<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/30
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin-left: 400px">
    <h1 style="margin-left: 10px;">添加DVD信息：</h1>
    <br>
    <form  method="post"
           action="${pageContext.request.contextPath}/testServlet.do?method=add"
           enctype="multipart/form-data"
    >
        <!--             onsubmit=" return checkBox()"
         -->            <table class="gridtable">
        <tr>
            <td>编号(已自动分配不可修改):
                <div id="myDiv">
                </div>
            </td>
            <td width="300px"><input id="pr_dvdNum" type="text" name="pr_dvdNum"
                                     value="${nextNum }" disabled="disabled"
                                     style="border: 0px; font-size: 20px;">
            </td>
        </tr>
        <tr>
            <td>请输入DVD名字:</td>
            <td width="300px"><input id="dvdName" type="text"
                                     name="dvdName" style="border: 0px; font-size: 20px;"></td>
        </tr>
        <tr>
            <td>请输入DVD状态:</td>
            <td width="300px"><input id="dvdState" type="radio" checked
                                     value="未借出"    name="dvdState" style="border: 0px; font-size: 20px;">未借出
                <input id="dvdState" type="radio"
                       value="已借出"    name="dvdState" style="border: 0px; font-size: 20px;">已借出
            </td>
        </tr>
        <tr>
            <td>请传入DVD图片<br>必须是gif格式</td>
            <td><input type="file" name="file"
                       style="border: 0px; font-size: 20px;"
            /></td>
        </tr>
        <tr>
            <td>请输入DVD类型:</td>
            <td width="300px"><input id="dvdType" type="text"
                                     name="dvdType" style="border: 0px; font-size: 20px;">
                <input type="hidden" name="currentPage" value="${currentPage}">

            </td>
        </tr>
        <tr>

            <td colspan="2"><input type="submit" value="添加"
                                   style="width: 120px; height: 30px; background-color: blue; border: 0px; color: #fff5ee; font-size: 15px; margin-left: 180px;"/>
                <%--<a href="${pageContext.request.contextPath}/ProductServlet?method=toMainView&currentPage=${currentPage }" style="float: right;">返回主页</a></td>--%>
        </tr>
    </table>
        <input type="hidden" id="isEmpty" name="isEmpty" value="FULL">
    </form>
</div>

</body>
</html>
