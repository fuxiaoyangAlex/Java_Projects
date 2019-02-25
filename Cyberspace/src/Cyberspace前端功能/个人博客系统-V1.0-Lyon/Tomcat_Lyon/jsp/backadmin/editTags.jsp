<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="blog.flowingsun.bean.Articlelist"%>
<%@ page import="blog.flowingsun.bean.Categary"%>
<%@ page import="blog.flowingsun.bean.Pagebean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
	List <Categary> categarys = (List)request.getAttribute("categarys");    //categarys数组元素保存一级分类名，String类型
  List <Categary> secondcats = (List)request.getAttribute("secondcats");  //secondcats数组元素保存二级分类Bean类型，Bean类型中保存了二级分类名。
	ArrayList <Articlelist> articles = (ArrayList)request.getAttribute("articles");
	ArrayList <String> allTags = (ArrayList)request.getAttribute("allTags");
%>
<%@include file="header.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$('.listDataTableDiv').on("mouseenter","#tag",function(){
			var a = $(this);
			var b = a.children('span');
			a.css("background-color","#FFFFFF");
			a.css("color","#7373B9");
			b.css("color","#FF4500");
			b.css("display","");
	});
	$('.listDataTableDiv').on("mouseleave","#tag",function(){
			var a = $(this);
			var b = a.children('span');
			a.css("background-color","");
			a.css({"color":"#98F5FF","font-family":"STKaiti","margin-right":"6px","padding":"6px 0px 6px 2px"});
			b.css("color","");
			b.css("top","0px");
			b.css("display","none");
	});
	$('.listDataTableDiv').on("click","#tag",function(){
		var thenode = $(this);
		var tagName = $(this).text();
		var articleId = $(this).parents('tr').attr('title');
		var addbutton = $(this).parents('#tagTD').children('#addnewTag');
		if(addbutton.text()!='提交'){
			if(confirm('【亲~点击确定，删除此标签哦！☹️')){
				$.ajax({
					cache:true,
					type:"POST",
					url:"Admin_home_delOneArticletag",
					async: false,
					data:{
							'articleId':articleId,
							'tagName':tagName
					},
					dataType:'text',
					error:function(request){
							alert("💔标签删除失败：Connection error:"+request.error);
					},
					success:function(asd) {
							if(asd=="success"){
									$(thenode).remove();
									alert("🙂，标签删除成功");
							}else{
									alert("😔，标签删除失败,请刷新页面查看");
							}
					}
				});
			}
		}else{
			  $(thenode).remove();
		}

		return false;
	});
	$('.listDataTableDiv').on("click","#addnewTag",function(){
		var inputBox = $(this).parents('#tagTD').children('.inputnewTag');
		var farther = $(this).parents('#tagTD');
		var articleId = farther.parents('tr').attr('title');
		var tagName = inputBox.val();
		if(inputBox.css('display')=='none'){
			  inputBox.css('display','block');
				$(this).text('提交');
		}else{
			  var tags = $(this).parents('#tagTD').children('.anewTag').children('span');
				if(tags.length==0){
					  alert('😲您尚未创建任何标签！');
				}else{
					if(confirm('🙂确定提交更改标签么？')){
							inputBox.css('display','none');
							$(this).text('新增');
							var tagList = new Array();
							$.each(tags,function(){
									tagList.push($(this).text());
							});
							$.ajax({
								cache:true,
								type:"POST",
								url:"Admin_home_editArticleTags",
								async: false,
								data:{
										'articleId':articleId,
										'tagList':tagList.join()
								},
								dataType:'text',
								error:function(request){
										alert("💔Ajax网络连接出了问题：Connection error:"+request.error);
								},
								success:function(resp) {
										if(resp=="success"){
												alert("😊更改成功，标签如下：\n"+tagList.join());
										}else{
												alert("😔，标签更改失败,请刷新页面查看");
										}
								}
							});
							return false;
					}
				}
			}
	});
	$(".inputnewTag").keyup(function(e){
      if(e.keyCode == 13){
				  var inputBox = $(this);
				  var tagName = $(this).val();
					var farther = $(this).parents('#tagTD');
				  var addButton = farther.children('#addnewTag');
					if(tagName==''){
						  alert("请输入标签内容！");
						  return false;
					}else{
						  $(inputBox).val('');
							var newTag = document.createElement("a");
						  newTag.setAttribute("class", "anewTag");
						  newTag.setAttribute("title", tagName);
						  newTag.innerHTML = '<span id="tag" class="label label-default" style="color:#98F5FF;margin:3px;">'+tagName+'<span id="closeTag" style="top: 0px; padding: 0px;display:none;" class="glyphicon glyphicon-remove"></span></span>';
						  farther.append(newTag);
					}
			}
	});
});
</script>



<div class="container">
		<div class="row">
			<div class="col-md-10 blog-main" style="margin:0px;padding:0px;">
				<title>文章管理</title>
					<div class="listDataTableDiv">

						<table class="table table-striped table-bordered table-hover  table-condensed">
							<thead>
								<tr class="success">
									<th>文章Id-标题-分类</th>
									<th style="width:400px;">标签(批量更改)</th>
									<th>评论</th>
									<th>感谢</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${articles}" var="p" varStatus="st">
									<tr title="${p.getId()}">
											<td><span>${p.getId()}</span>:<a href="/Lyon/foreviewSingleBlog?Id=${p.getId()}")} target="_blank">${p.getTitle()} — </a>
											    <span title="${p.getMid()}" class="articleMid">${p.getMidName()},</span>
													<span title="${p.getCid()}" class="articleCid">${p.getCidName()}</span>
											</td>
											<td id="tagTD">
												<a href="javascript:void(0);" id="addnewTag" style="width:60px;" class="btn btn-primary" name="addnewTag" onclick="function()">新增</a>
												<input type="text"  name="tags"  class="inputnewTag"  placeholder="按回车新增" style="display:none;width:110px;background-color:#E0EEEE;float:left;margin-top:5px;">
											  <c:forEach items="${p.getarticleTags()}" var="tag">
					                  <a class="anewTag"><span id='tag' class="label label-default" style="color:#98F5FF;margin:3px;">${tag}<span id="closeTag" style="top: 0px; padding: 0px;display:none;" class="glyphicon glyphicon-remove"></span></span></a>
					              </c:forEach>
											</td>
											<td>${p.getcommentNum()}</td>
											<td>${p.getthankNum()}</td>
									</tr>
								</c:forEach>
							</tbody>
							</table>
					 </div><!--listDataTableDiv-->
					 </div>

					 <div class="col-sm-2 col-sm-offset-1 blog-sidebar" style="margin:0px;">
		 	      <div class="sidebar-module">
		 	        <h4 style="font-size:35px;color:#9F79EE;"><strong>Tags</strong></h4>
		 	          <ol class="list-unstyled">
		 							<c:forEach items="${allTags}" var="atag">
		 								<li style="font-family:STKaiti;font-size:18px;margin:8px;"><a href="/Lyon/foregetTagarticles?tagName=${atag}" target="_blank"><span style="color:#98F5FF;" class="label label-default">${atag}</span></a></li>
		 							</c:forEach>
		 	          </ol>
		 	      </div>
		 	    </div><!-- /.blog-sidebar -->



		</div>

	</div>





<div id=footer style="background-color: #7373B9;color:#434343;text-align:center;padding:15px;margin-bottom:0px;bottom:0px;font-size:90%;border:0;clear:right;">
	   <p>© CopyRight 2018-2019 www.flowingsun.com Inc All Rights Reserved 皖ICP备17013223号 &copy;<br> Email: Flowingsun007@163.com</p>
</div>
</body>
<!-- //Body -->
</html>
