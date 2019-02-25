<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xblo.bean.*"%>
<%@ page import="org.xblo.dao.*"%>

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
	List<ArticleBean> articleList = (List<ArticleBean>) request
			.getAttribute("articleList");
	if (articleList == null || articleList.size() == 0) {
%>
<div class="post">
<h1 class="title">欢迎使用Xblo!</h1>
<p class="meta"><small>当前类别下博主还未发表文章</small></p>
<div class="entry">
<p>精彩即将来临...</p>
<p class="more"><a href="#">阅读全文&hellip;</a></p>
</div>
</div>
<%
	} else {
		int i = 0;
		ArticleCommentDao articleCommentDao = new ArticleCommentDao();
		XbloUserDao xbloUserDao = new XbloUserDao();
		ArticleTypeDao articleTypeDao = new ArticleTypeDao();

		String username = "";
		int commentCount = 0;
		while (i < articleList.size()) {
			ArticleBean articleBean = (ArticleBean) articleList.get(i);
			XbloUserBean xbloUserBean = xbloUserDao
					.queryByXbloUserId(articleBean.getCreateUserId());
			username = xbloUserBean.getXbloUsername();
			List<ArticleCommentBean> articleCommentList = articleCommentDao
					.queryByArticleId(articleBean.getArticleId());
			if (articleCommentList == null)
				commentCount = 0;
			else
				commentCount = articleCommentList.size();
			ArticleTypeBean articleTypeBean = articleTypeDao
					.query(articleBean.getArticleTypeId());
			String articleTypeName = null;
			if (articleTypeBean == null)
				articleTypeName = "未分类";
			else
				articleTypeName = articleTypeBean.getArticleTypeName();
%>
<div class="post">
<h2 class="title">[<%=articleTypeName%>]<%=articleBean.getArticleTitle()%></h2>
<p class="meta"><small><%=username%> 发布于 <%=articleBean.getCreateDate()%>
<b>&nbsp;|&nbsp;</b> 阅读 (<%=articleBean.getVisitCount()%>) <b>&nbsp;|&nbsp;</b>
<a
	href="ActionServlet?action=viewArticle&articleId=<%=articleBean.getArticleId()%>#FeedBack">评论
(<%=commentCount%>)</a></small></p>
<div class="entry">
<p><%=articleBean.getArticleSummary()%></p>
<p class="more"><a
	href="ActionServlet?action=viewArticle&articleId=<%=articleBean.getArticleId()%>">阅读全文&hellip;</a></p>
</div>
</div>
<%
	i++;
		}
	}
%>
</div>
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
