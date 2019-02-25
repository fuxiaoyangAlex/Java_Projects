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
function submitCheck(){
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
			var submitForm = document.getElementById("submitForm");
			var bloghtml = UE.getEditor('editor').getContent();
			var tagdiv = document.createElement("input");
			tagdiv.setAttribute("type", "text");
			tagdiv.setAttribute("name", "tags");
			tagdiv.setAttribute("value", taglist.join());
			if (bloghtml==''){
					if(confirm('请填写文章内容！【取消】将提交空文章！！！')) {
							return false;
					}else{
							document.getElementById("submitBlog").value=bloghtml;
							submitForm.appendChild(tagdiv);
					}
			}else{
					document.getElementById("submitBlog").value=bloghtml;
					submitForm.appendChild(tagdiv);
			}
		}
}
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

	$('#maincategary').change(function(){
		var select1 = $('#maincategary');
		var select2 = $('#secondcats');
		var options1 = $('#maincategary option:selected');
		var options2 = $('#secondcats option:selected');
		if(options1.val()!=0){
			$.ajax({
				cache:true,
				type:"POST",
				url:"Admin_home_queryCategary",
				async: false,
				data:{
						'Mid':options1.val()
				},
				dataType:'text',
				error:function(request){
						alert("💔登录失败：Connection error:"+request.error);
				},
				success:function(data) {
						select2.children().remove();
						var result = data.split(";");
						for(var i=0;i<result.length;i++){
								var alist = result[i].split(":");
								var Cid = alist[0];
								var cidName = alist[1];
								select2.append('<option value="'+Cid+'">'+cidName+'</option>');
						}
				}
			});
		}
	});
});
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

<div class="container">
	<div class="row">

		 <div>
			<form action="/Lyon/Admin_home_submitBlog"  id="submitForm" method="post"  class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label" style="font-size:20px;">文章标题</label>
			    <div class="col-sm-10">
			      <input type="text"  name="title" id="title" class="form-control" placeholder="标题Article Title" style="background-color:#E0EEEE">
				  </div>
			  </div>
				<div class="form-group form-group-sm">
					<label class="col-sm-2 control-label" style="font-size:20px;">文章摘要</label>
					<div class="col-sm-10">
						<textarea type="text"  name="subtitle" id="subtitle" class="form-control"  placeholder="摘要Abstract" style="background-color:#E0EEEE;"></textarea>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label" style="font-size:16px;">一级分类</label>
					<div class="col-sm-4">
					<select name="maincategary"  class="form-control" id="maincategary" style="background-color:#B9D3EE">
						<c:forEach items="${categarys}" var="p" varStatus="st">
							 <c:if test="${!(st.last)}">
								<option value="${p.mid}">${p.name}</option>
							 </c:if>
						</c:forEach>
					</select>
				</div>
        <label class="col-sm-2 control-label" style="font-size:16px;">二级分类</label>
				<div class="col-sm-4">
				<select class="form-control"  name="secondcats" id="secondcats" placeholder="Abstract" style="background-color:#E6E6FA">
					<c:forEach items="${secondcats}" var="cat" varStatus="st">
						<c:if test="${!(st.last)}">
						 <c:forEach items="${cat}" var="p" varStatus="st">
							 <option value="${p.id}">${p.name}</option>
						 </c:forEach>
						</c:if>
					</c:forEach>
				</select>
			 </div>
      </div>

			<div class="form-group">
				<button type="submit" class="btn btn-danger btn-lg" style="float:right;" id="submitBlog" name="submitBlog"  onclick="return submitCheck()">提交文章</button>
				<label class="col-sm-6 control-label" style="font-size:18px;float:right">🙂点我提交→</label>
      </div>
			</form>
			<div class="form-group" id="tagDiv">
				  <label class="col-sm-4 control-label" style="font-size:16px;width:90px;margin-left:72px;padding:0px;">文章标签</label>
					<a href="javascript:void(0);" id="addnewTag" class="btn btn-primary" name="addnewTag" onclick="addTag()">新增</a>
					<div class="col-xs-4" style="margin-left:-10px;">
				    <input type="text"  name="tags"  class="form-control" id="inputnewTag"  placeholder="Tags" style="background-color:#E0EEEE;">
					</div>

			</div>
   </div>



	 		<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
	 		<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>


<div id="btns">
	<div>
				<button onclick="getContentTxt()"class="btn btn-primary">获取文字</button>
				<button onclick="getPlainTxt()"class="btn btn-primary">获取格式化文字</button>
				<button onclick="getText()"class="btn btn-primary">获得当前选中的文本</button>
	</div>
	<div>
		<button   onclick="getContent()"class="btn btn-info">查看页面html</button>
		<button onclick=" UE.getEditor('editor').setHeight(300)"class="btn btn-warning">设置高度为300</button>
		<button onclick="getLocalData()"class="btn btn-primary" >获取草稿箱内容</button>
	  <button onclick="clearLocalData()"class="btn btn-danger" >清空草稿箱</button>
  </div>
	<div>
		  <button onclick=" UE.getEditor('editor').setShow()"class="btn btn-success">显示编辑器</button>
			<button onclick=" UE.getEditor('editor').setHide()"class="btn btn-warning">隐藏编辑器</button>
			<button id="enable" onclick="setEnabled()"class="btn btn-primary">可以编辑</button>
			<button onclick="setDisabled()"class="btn btn-primary">不可编辑</button>
			<button onclick="createEditor()"class="btn btn-primary">创建编辑器</button>
	    <button onclick="deleteEditor()"class="btn btn-danger">删除编辑器</button>
	</div>
</div><!-- <div id="btns"> -->


<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');


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
			arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
			UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
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
