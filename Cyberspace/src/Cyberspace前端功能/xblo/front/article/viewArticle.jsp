<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xblo.bean.*"%>
<%@ page import="org.xblo.dao.*"%>
<%@ page import="java.util.Iterator"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--

Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Title      : Artificial
Version    : 1.0
Released   : 20070808
Description: A two-column, fixed-width with fluid header ideal for 1024x768 resolutions. Suitable for blogs and small websites.

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Xblo首页</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="<%=basePath%>/css/default.css" rel="stylesheet"
	type="text/css" media="screen" />
</head>
<body>

<!-- start header -->
<jsp:include page="/front/view/FrontHead.jsp" />
<!-- end header -->

<!-- start page -->
<div id="page"><!-- start content -->
<div id="content">
<%
	ArticleBean articleSingle = (ArticleBean) request
			.getAttribute("articleBean");
	XbloUserDao xbloUserDao = new XbloUserDao();
	ArticleCommentDao articleCommentDao = new ArticleCommentDao();
	String xbloUsername = "";
	int commentCount = 0;
	XbloUserBean xbloUserBean = xbloUserDao.queryByXbloUserId(articleSingle
			.getCreateUserId());
	xbloUsername = xbloUserBean.getXbloUsername();
	List<ArticleCommentBean> articleCommentList = articleCommentDao
			.queryByArticleId(articleSingle.getArticleId());
	commentCount = articleCommentList.size();
%>
<div class="post">
<h2 class="title"><%=articleSingle.getArticleTitle()%></h2>
<p class="meta"><small><a href="#"><%=xbloUsername%></a> 发布于 <%=articleSingle.getCreateDate()%>
<b>&nbsp;|&nbsp;</b> 阅读 (<%=articleSingle.getVisitCount()%>) <b>&nbsp;|&nbsp;</b>
<a href="#FeedBack">评论 (<%=commentCount%>)</a></small></p>
<div class="entry">
<p><%=articleSingle.getArticleContent()%></p>
</div>
</div>

<!-- 评论开始 -->
<div class="comment">
<%
	for (Iterator<ArticleCommentBean> iter = articleCommentList
			.iterator(); iter.hasNext();) {
		ArticleCommentBean articleCommentBean = (ArticleCommentBean) iter
				.next();
%>
<p class="title"><%=articleCommentBean.getArticleCommentUser()%>
发表于：<%=articleCommentBean.getArticleCommentDate()%></p>
<p><%=articleCommentBean.getCommentContent()%></p>
<%
	}
%> <a name="FeedBack"></a>
<form
	action="ActionServlet?action=comment&articleId=<%=articleSingle.getArticleId()%>"
	method="post">
姓名：
<input type="text" name="articleCommentUser" size="50" value="匿名用户"></input>
<br />
邮件：
<input type="text" name="articleCommentEmail" size="50"
	value="user@domain.com"></input>
<br />
内容：
<textarea name="articleCommentContent" rows="10" cols="55"></textarea>
<br />
<input type="submit" value="提交" style="width: 50"></input>
<input type="reset" value="重置" style="width: 50"></input>
</form>
</div>
<!-- 评论结束 --></div>




<!-- end content --> <!-- start sidebar --> <jsp:include
	page="/front/view/FrontLeft.jsp" /> <!-- end sidebar -->

<div style="clear: both;">&nbsp;</div>
</div>
<!-- end page -->

<!-- start footer -->
<jsp:include page="/front/view/FrontFoot.jsp" />
<!-- end footer -->

</body>
</html>
