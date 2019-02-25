<%@ page import="java.net.InetAddress" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/01
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<img src="file://D:/我的下载/安装包/2.png"  width="112" height="112">--%>
<hr/>
<marquee behavior="alternate">我来回滚动</marquee>
<marquee behavior="scroll">我单方向循环滚动</marquee>
<marquee behavior="scroll" direction="up" height="30">我改单方向向上循环滚动</marquee>
<marquee behavior="slide">我只滚动一次</marquee>
<marquee behavior="slide" direction="up">我改向上只滚动一次了</marquee>
<MARQUEE DIRECTION=RIGHT BEHAVIOR=SCROLL SCROLLAMOUNT=10 SCROLLDELAY=200>这是一个滚动字幕。</MARQUEE>
<%--direction 表示滚动的方向，值可以是left，right，up，down，默认为left--%>
<%--behavior 表示滚动的方式，值可以是scroll(连续滚动)slide(滑动一次)alternate(来回滚动)--%>
<%--loop 表示循环的次数，值是正整数，默认为无限循环--%>
<%--scrollamount 表示运动速度，值是正整数，默认为6--%>
<%--scrolldelay 表示停顿时间，值是正整数，默认为0，单位是毫秒--%>
<%--align 表示元素的垂直对齐方式，值可以是top，middle，bottom，默认为middle--%>
<%--bgcolor 表示运动区域的背景色，值是16进制的RGB颜色，默认为白色--%>
<%--height、width 表示运动区域的高度和宽度，值是正整数(单位是像素)或百分数，默认width=100% height为标签内元素的高度。--%>
<%--hspace、vspace 表示元素到区域边界的水平距离和垂直距离，值是正整数，单位是像素。--%>
<%--onmouseover=this.stop() onmouseout=this.start() 表示当鼠标以上区域的时候滚动停止，当鼠标移开的时候又继续滚动。--%>
<hr/>

<marquee behavior="scroll" direction="down" height="100" width="200" onmouseover="this.stop()" onmouseout="this.start()">
    <img src="../images/logo.jpg" alt="logo">
    <br/>　　　　　　　　　　　　　　
    <br/>　　　　　　　
    <br/>　　　　　　　
    <img src="../images/logo1.png" align="logo1">
</marquee>
<hr/>
<p>
    <font color="red">
        <%
            InetAddress ia=null;
            ia = InetAddress.getLocalHost();
            String addr = request.getRemoteAddr();
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            System.out.println("本机名称是："+ localname);
            System.out.println("本机的ip是 ："+localip);
        %>
        <%= localip%>
    </font>
</p>
</body>
</html>
