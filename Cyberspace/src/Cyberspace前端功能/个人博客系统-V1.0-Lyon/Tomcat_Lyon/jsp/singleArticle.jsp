<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp"%>
<%@ page import="blog.flowingsun.bean.Comment"%>
<%@ page import="blog.flowingsun.bean.Recomment"%>
<%
  request.setCharacterEncoding("UTF-8");
	Articlelist  article = (Articlelist)request.getAttribute("article");
	ArrayList <Comment> comments = (ArrayList)request.getAttribute("comments");
  ArrayList <String> allTags = (ArrayList)request.getAttribute("allTags");
  int commentNum = (int) request.getAttribute("commentNum");
  int thankNum = (int) request.getAttribute("thankNum");
%>
	<div class="container">
	  <div class="row">
	    <div class="col-sm-8 blog-main">
	        <!--文章主体全部在blog-post中 ，要添加新文章，直接copy此div模块即可-->
					<div class="blog-post">
						<a href="#"target="_blank"><h2 class="blog-post-title" style="font-family:STXingkai;font-size:35px;"><%=article.getTitle()%></h2></a>
					  <p class="blog-post-meta"><%=article.getCreateDay()%>,by—<a href="#"><%=article.getAuthor()%></a></p>
            <c:forEach items="${article.getarticleTags()}" var="tag">
                  <span class="label label-success">${tag}</span>&nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
					                            <%=article.getContent()%>

					</div><!-- /.blog-post -->
	        <%@include file="commentAera.jsp"%>
			</div><!-- /.blog-main -->

<%@include file="siderbar.jsp"%>
<%@include file="footer.jsp"%>
