<!DOCTYPE html>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="blog.flowingsun.bean.Articlelist"%>
<%@ page import="blog.flowingsun.bean.Categary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
	List <Categary> categarys = (List)request.getAttribute("categarys");    //categarys数组元素保存一级分类名，String类型
  List <Categary> secondcats = (List)request.getAttribute("secondcats");  //secondcats数组元素保存二级分类Bean类型，Bean类型中保存了二级分类名。
  Articlelist  article = (Articlelist)request.getAttribute("article");
%>
<%@include file="header.jsp"%>
<%
	List<String> info = (List<String>) request.getAttribute("info");    //取得属性
	if (info != null){
			Iterator<String> iter = info.iterator();                        //实例化Iterator
			while(iter.hasNext()){
%>
					 <h3><%=iter.next()%></h3><br>
<%
			}
	}
%>

<script type="text/javascript">
function resubmitCheck(){
	  var a = $('#title').val();
		var b = $('#subtitle').val();
		var taglist = [];
		var tags = document.getElementsByClassName("anewTag");
		for(var i = 0; i < tags.length; i++){
			  taglist.push(tags[i].getAttribute('title'));
		}
		if ((a=='')||(b=='')||(taglist.length==0)){
			  alert("文章标题、摘要、标签均不能为空！！！");
				return false;
		}else{
			alert("您创建了"+taglist.length+"个标签："+taglist.join());
			var resubmitForm = document.getElementById("resubmitForm");
			var bloghtml = UE.getEditor('editor').getContent();
			var tagdiv = document.createElement("input");
			tagdiv.setAttribute("type", "text");
			tagdiv.setAttribute("name", "tags");
			tagdiv.setAttribute("value", taglist.join());
			if (bloghtml==''){
					if(confirm('请填写文章内容！【取消】将提交空文章！！！')) {
							return false;
					}else{
							document.getElementById("resubmitBlog").value=bloghtml;
							resubmitForm.appendChild(tagdiv);
					}
			}else{
					document.getElementById("resubmitBlog").value=bloghtml;
					resubmitForm.appendChild(tagdiv);
			}
		}
}
</script>

<script type="text/javascript">
$(function addTag(){
	$("#addnewTag").click(function(){
		var tagName = $('#inputnewTag').val();
		if (tagName==''){
				alert("请输入标签内容！");
				return false;
		}else{
			  $('#inputnewTag').val('');
				var newTag = document.createElement("a");
				var farther = document.getElementById('tagDiv');
				newTag.setAttribute("class", "anewTag");
				newTag.setAttribute("title", tagName);
				newTag.innerHTML = '<span class="label label-default" style="color:#98F5FF;font-family:STKaiti;font-size:13px;margin-right:6px;padding:4px 0px 4px 2px;">'+tagName+'<span id="closeTag" style="top:0px;padding:0px" class="glyphicon glyphicon-remove"></span></span>';
				farther.appendChild(newTag);
		}
	});
})
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#inputnewTag").keyup(function(e){
      if(e.keyCode == 13){
				  $("#addnewTag").click();
			}
	});
	$('#tagDiv').on("mouseenter",".anewTag",function(){
			var a = $(this).children('span');
			var b = a.children('span');
			a.css("background-color","#FFFFFF");
			a.css("color","#7373B9");
			b.css("color","#FF4500");
	});
	$('#tagDiv').on("mouseleave",".anewTag",function(){
			var a = $(this).children('span');
			var b = a.children('span');
			a.css("background-color","");
			a.css({"color":"#98F5FF","font-family":"STKaiti","margin-right":"6px","padding":"6px 0px 6px 2px"});
			b.css("color","");
			b.css("top","0px");
	});
	$('#tagDiv').on("click","#closeTag",function(){
			var mother = $(this).parents('.anewTag');
			mother.remove();
	});
});
</script>

	<div class="container">
	  <div class="row">
	    <div class="col-sm-8 blog-main">
	        <!--文章主体全部在blog-post中 ，要添加新文章，直接copy此div模块即可-->
					<div class="blog-post">
						<a href="#"target="_blank"><h2 class="blog-post-title"><strong><%=article.getTitle()%></strong></h2></a>
					  <p class="blog-post-meta"><%=article.getCreateDay()%><a href="#">by-<%=article.getAuthor()%></a></p>
					 <div id="content">
						  <%=article.getContent()%>
					 </div>

					</div><!-- /.blog-post -->
			</div><!-- /.blog-main -->
	</div><!-- /.row -->
</div><!-- /.container -->



<div class="container">
	<div class="row">
		 <div>
			<form action="/Lyon/Admin_home_rewriteSubmit"  id="resubmitForm" method="post" class="form-horizontal" >
			  <div class="form-group">
			    <label style="font-size:18px;">在这里写下文章标题和摘要</label>
			    <input type="text"  name="title" class="form-control" value="${article.getTitle()}" style="background-color:#E0EEEE">
				</div>
				<div class="form-group">
			    <input type="text"  name="subtitle" class="form-control"  value="${article.getSummary()}" style="background-color:#E0EEEE;">
        </div>
				<div class="form-group" style="width:400px;float:right">
         <label>选择文章的二级分类</label>
				 <select class="form-control"  name="secondcats"  style="background-color:#E6E6FA">
					 <c:forEach items="${secondcats}" var="cat" varStatus="st">
						 <c:if test="${!(st.last)}">
							<c:forEach items="${cat}" var="p" varStatus="st">
								<c:if test="${article.getCid()==p.id}">
								<option value="${p.id}" selected = "selected">${p.name}</option>
								</c:if>
								<c:if test="${!(article.getCid()==p.id)}">
								<option value="${p.id}">${p.name}</option>
								</c:if>
							</c:forEach>
						 </c:if>
					 </c:forEach>
						 </select>
				</div>
			  <div class="form-group" style="width:400px">
					<label>请选择文章主分类</label>
					<select name="maincategary"  class="form-control" value="${article.getMid()}" style="background-color:#B9D3EE" >
						<c:forEach items="${categarys}" var="p" varStatus="st">
							 <c:if test="${!(st.last)}">
								 <c:if test="${article.getMid()==p.mid}">
 								    <option value="${p.mid}" selected = "selected">${p.name}</option>
 								 </c:if>
								 <c:if test="${!(article.getMid()==p.mid)}">
								    <option value="${p.mid}">${p.name}</option>
								 </c:if>
							 </c:if>
						</c:forEach>
					</select>
			  </div>
				<div class="form-group">
				    <input type="hidden"  name="articleId" value="${article.getId()}">
				    <input type="hidden" name="author" value="${article.getAuthor()}">
				    <input type="hidden" name="originCid" value="${article.getCid()}">
				    <input type="hidden" name="originMid" value="${article.getMid()}">
						<button type="submit" class="btn btn-danger btn-lg" style="float:right;" id="resubmitBlog" name="resubmitBlog"  onclick="return resubmitCheck()">提交文章</button>
				    <label class="col-sm-6 control-label" style="font-size:18px;float:right">🙂点我提交→</label>
				</div>
			</form>
			<div class="form-group" id="tagDiv">
				  <label class="col-sm-4 control-label" style="font-size:16px;width:90px;padding:0px;">文章标签</label>
					<a href="javascript:void(0);" id="addnewTag" class="btn btn-primary" name="addnewTag" onclick="addTag()">新增</a>
					<div class="col-xs-4" style="margin-left:-10px;">
				    <input type="text"  name="tags"  class="form-control" id="inputnewTag"  placeholder="Tags" style="background-color:#E0EEEE;">
					</div>
					<c:forEach items="${article.getarticleTags()}" var="tag" varStatus="st">
						  <a class="anewTag" title="${tag}"><span class="label label-default" style="color: rgb(152, 245, 255); font-family: STKaiti; font-size: 13px; margin-right: 6px; padding: 6px 0px 6px 2px;">${tag}<span id="closeTag" style="top: 0px; padding: 0px;" class="glyphicon glyphicon-remove"></span></span></a>
					</c:forEach>
			</div>
   </div>



	 		<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
			<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>



<div id="btns">
	<div>

				<button  onclick="getContent()" class="btn btn-success">点击查看HTML</button>
				<button onclick="setContent()"class="btn btn-primary">写入内容</button>
				<button onclick="setContent(true)"class="btn btn-primary">追加内容</button>
				<button onclick="getContentTxt()"class="btn btn-primary">获得纯文本</button>
				<button onclick="getPlainTxt()"class="btn btn-primary">获得带格式的纯文本</button>
				<button onclick="hasContent()"class="btn btn-primary">判断是否有内容</button>
			  <button onclick="setFocus()"class="btn btn-primary">使编辑器获得焦点</button>

	</div>
	<div>
		  <button onmousedown="isFocus(event)"class="btn btn-primary">编辑器是否获得焦点</button>
			<button onmousedown="setblur(event)"class="btn btn-primary" >编辑器失去焦点</button>
			<button onclick="insertHtml()"class="btn btn-primary">插入给定的内容</button>
			<button id="enable" onclick="setEnabled()"class="btn btn-primary">可以编辑</button>
			<button onclick="setDisabled()"class="btn btn-primary">不可编辑</button>
			<button onclick=" UE.getEditor('editor').setHide()"class="btn btn-info">隐藏编辑器</button>
			<button onclick=" UE.getEditor('editor').setShow()"class="btn btn-success">显示编辑器</button>

	</div>
	<div>
		<button onclick="getText()"class="btn btn-primary">获得当前选中的文本</button>
		<button onclick=" UE.getEditor('editor').setHeight(300)"class="btn btn-primary">设置高度为300</button>
	  <button onclick="getLocalData()"class="btn btn-primary" >获取草稿箱内容</button>
	  <button onclick="clearLocalData()"class="btn btn-warning" >清空草稿箱</button>
	  <button onclick="createEditor()"class="btn btn-primary">创建编辑器</button>
	  <button onclick="deleteEditor()"class="btn btn-danger">删除编辑器</button>
  </div>
</div><!-- <div id="btns"> -->


<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');

	function isFocus(e){
			alert(UE.getEditor('editor').isFocus());
			UE.dom.domUtils.preventDefault(e)
	}
	function setblur(e){
			UE.getEditor('editor').blur();
			UE.dom.domUtils.preventDefault(e)
	}
	function insertHtml() {
			var value = prompt('插入html代码', '');
			UE.getEditor('editor').execCommand('insertHtml', value)
	}
	function createEditor() {
			enableBtn();
			UE.getEditor('editor');
	}
	function getAllHtml() {
			alert(UE.getEditor('editor').getAllHtml())
	}
	function getContent() {
		  var arr=[];
		  var bloghtml = UE.getEditor('editor').getContent();
		  arr.push("文章页面的Html代码如下：");
		  arr.push(bloghtml);
		  alert(arr.join("\n"));
		  return true;

	}
	function getPlainTxt() {
			var arr = [];
			arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getPlainTxt());
			alert(arr.join('\n'))
	}
	function setContent(isAppendTo) {
			var arr = [];
			arr.push("点击确定,将文章内容全部填入编辑器！");
			var content = document.getElementById("content").innerHTML;
			UE.getEditor('editor').setContent(content, isAppendTo);
			alert(arr.join("\n"));
	}
	function setDisabled() {
			UE.getEditor('editor').setDisabled('fullscreen');
			disableBtn("enable");
	}

	function setEnabled() {
			UE.getEditor('editor').setEnabled();
			enableBtn();
	}

	function getText() {
			//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
			var range = UE.getEditor('editor').selection.getRange();
			range.select();
			var txt = UE.getEditor('editor').selection.getText();
			alert(txt)
	}

	function getContentTxt() {
			var arr = [];
			arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
			arr.push("编辑器的纯文本内容为：");
			arr.push(UE.getEditor('editor').getContentTxt());
			alert(arr.join("\n"));
	}
	function hasContent() {
			var arr = [];
			arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
			arr.push("判断结果为：");
			arr.push(UE.getEditor('editor').hasContents());
			alert(arr.join("\n"));
	}
	function setFocus() {
			UE.getEditor('editor').focus();
	}
	function deleteEditor() {
			disableBtn();
			UE.getEditor('editor').destroy();
	}
	function disableBtn(str) {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
					if (btn.id == str) {
							UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
					} else {
							btn.setAttribute("disabled", "true");
					}
			}
	}
	function enableBtn() {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
					UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
			}
	}

	function getLocalData () {
			alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
	}

	function clearLocalData () {
			UE.getEditor('editor').execCommand( "clearlocaldata" );
			alert("已清空草稿箱")
	}
</script>

</div><!--<div class="container">-->
</div><!--<div class="row">-->


</body>
</html>
