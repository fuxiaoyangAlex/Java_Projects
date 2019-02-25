<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList <Articlelist> articles = (ArrayList)request.getAttribute("articles");
	ArrayList <String> allTags = (ArrayList)request.getAttribute("allTags");
%>

<script>
$(function(){
		var xmlhttp;
		$(".blog-post").on("click","#submitThank",function(){
				var thisnode = $(this);
				var spannode = thisnode.children("span");
				var thanknode = spannode.children("span");
				var originNum = parseInt(thanknode.text());
				var articleId = thanknode.parents(".linkandshare-catarticle").children(".articleId").text();
				$.get(
						"forecheckLogin",
						function(result){
								if("success"==result){
									var url = "foresetblogThank";
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=setThank; //响应函数
									xmlhttp.open("POST",url,true);
									xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
									xmlhttp.send("articleId="+articleId);
								}else{
										alert("亲，请先登录后再点❤️哦！");
										return false;
								}
						});
						function setThank(){
							 if (xmlhttp.readyState==4 && xmlhttp.status==200){
								 thisnode.css({"margin-right":"50px","color":"#337ab7"});
								 spannode.css("color","#EE2C2C");
								 thanknode.text(originNum+1);
							 }
						}

					});
});
</script>

	<div class="container">
	  <div class="row">
	    <div class="col-sm-8 blog-main">
	        <!--文章主体全部在blog-post中 ，要添加新文章，直接copy此div模块即可-->
					<c:forEach items="${articles}" var="article" varStatus="st">
			 				<div class="blog-post">
			 						 <a href="/Lyon/foreviewSingleBlog?Id=${article.getId()}"target="_blank"><h2 class="blog-post-title"><strong style="font-family:Helvetica;color:#7171C6"><c:out value="${article.getTitle()}"/></strong></h2></a>
									 <div>
                     <c:forEach items="${article.getarticleTags()}" var="tag">
											  <a href="/Lyon/foregetTagarticles?tagName=${tag}" target="_blank"><span class="label label-success">${tag}</span>&nbsp;&nbsp;&nbsp;</a>
										 </c:forEach>
		 						   </div>
			 						 <p class="blog-post-meta"><c:out value="${article.getCreateDay()}"/>,by—<a href="#"><c:out value="${article.getAuthor()}"/></a></p>
			 						 <h4 style="font-family:Andale Mono;color:#4F4F4F"><c:out value="${article.getSummary()}"/></h4>
									 <div class="linkandshare-catarticle">
		 								  <span class="articleId" hidden>${article.getId()}</span>
		 								  <span class="articleTitle" hidden>${article.getTitle()}</span>
		 								  <c:if test="${article.getthankNum()==0}">
		 									    <a href="javascript:void(0)" id="submitThank" onclick="function()" style="margin-right:50px;color:#919191;"><span class="glyphicon glyphicon-heart" ><span>${article.getthankNum()}</span></span>感谢</a>
		 							    </c:if>
		 								  <c:if test="${article.getthankNum()!=0}">
		 									    <a href="javascript:void(0)" id="submitThank" onclick="function()" style="margin-right:50px;"><span class="glyphicon glyphicon-heart" style="color:#EE2C2C;"><span>${article.getthankNum()}</span></span>感谢</a>
		 							    </c:if>
		 								  <c:if test="${article.getcommentNum()==0}">
		 			                <a href="" style="margin-right:50px;color:#919191;"><span style=""><img src="images/mother2.png" height="16" width="16">${article.getcommentNum()}</span>评论</a>
		 							    </c:if>
		 								  <c:if test="${article.getcommentNum()!=0}">
		 			                <a href="" style="margin-right:50px;"><span><img src="images/mother1.png" height="16" width="16">${article.getcommentNum()}</span>评论</a>
		 							    </c:if>
		 								  <a href="" style="margin-right:50px;color:#919191;"><span class="glyphicon glyphicon-star"></span>9.9K收藏</a>
		 								  <a href="" style="color:#919191;"><span class="glyphicon glyphicon-send""></span>分享</a>
		 							 </div>
			 				 </div><!-- /.blog-post -->
			 		</c:forEach>
			</div><!-- /.blog-main -->

	<%@include file="siderbar.jsp"%>
	<%@include file="footer.jsp"%>
