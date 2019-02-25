<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<html>
  <head>
    <title>欢迎光临-www.flowingsun.com</title>
  </head>
  <body>
    <center>
      <h2>欢迎进入-www.flowingsun.com</h2><br>
    </center>

    <%
      request.setCharacterEncoding("UTF-8");
    %>
    <%
      List<String> info = (List<String>) request.getAttribute("info");    //取得属性
      if (info != null){
          Iterator<String> iter = info.iterator();                        //实例化Iterator
          while(iter.hasNext()){
    %>
               <h2><%=iter.next()%></h2><br>
    <%
          }
      }
    %>

  </center>
  </body>
</html>
