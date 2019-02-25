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
%>
<c:set var="bean" value="${pageBean}" scope="request" />
<c:set var="p" value="${bean.pagenum}" scope="request" />
<%@include file="header.jsp"%>


<script>
function confirmAct(){
	  if(confirm('【亲~点击确定，此篇文章将在地球上消失，您考虑清楚了么？！】☹️')){
			  return true;
		}
		return false;}
</script>
<script>
//var temp = Object.prototype.toString.apply(data);
//alert(temp);可以查看返回元素类型
$(function(){
	$('#pMid').change(function(){
		var select1 = $('#pMid');
		var select2 = $('#pCid');
		var options1 = $('#pMid option:selected');
		var options2 = $('#pCid option:selected');
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
						select2.append('<option value="0">全部</option>');
				}
			});
		}else{//else表示主分类选择了全部，则二级分类也相应地跳转为全部。
        select2.children().remove();
				select2.append('<option value="0">全部</option>');
		}
	});



});


</script>


		<div class="container">
		    <div class="row">
					<div class="col-md-12 blog-main">
		        <title>文章管理</title>
							<div class="listDataTableDiv">
								<table class="table table-striped table-bordered table-hover  table-condensed">
									<thead>
										<tr class="success">
											<th>文章Id</th>
											<th>文章标题(Title)</th>
											<th>主分类(Mid)</th>
											<th>二级分类(Cid)</th>
											<th>作者,创建日期</th>
											<th>编辑日期(EditDate)</th>
											<th>编辑(Edit)</th>
											<th>删除(Delete)</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${articles}" var="p" varStatus="st">
										  <tr>
											    <td>${p.getId()}</td>
											    <td><a href="/Lyon/foreviewSingleBlog?Id=${p.getId()}")} target="_blank">${p.getTitle()}</td>
											    <td>${p.getMidName()}</td>
											    <td><a href="/Lyon/foreviewCatBlogs?Cid=${p.getCid()}")} target="_blank">${p.getCidName()}</td>
											    <td>${p.getAuthor()},${p.getCreateDay()}</td>
											    <td>${p.getEditDay()}</td>
											    <td>
														<form action="Admin_home_editorBlog" method="post">
														  <button type="submit" id="reWrite" name="articleId" value="${p.getId()}" class="btn btn-primary">编辑</button>
                              <input type="hidden" name="originCid" value="${p.getCid()}">
															<input type="hidden" name="originMid" value="${p.getMid()}">
														</form>
													</td>
											    <td>
														<form action="Admin_home_deleteBlog" method="post">
														  <button type="submit" id="delete" name="deleteArticle" value="${p.getId()}" class="btn btn-danger"  onclick="return confirmAct()">删除</button>
													  </form>
													</td>
										  </tr>
                    </c:forEach>
									</tbody>
								  </table>
					     </div><!--listDataTableDiv-->




	<div class="pageDiv" >
		<script>
		$(function(){
			$("#submitpsize").on("click",function(){
				var psize = document.getElementById("psize").value;
				if(psize!=""){
					var pmid = document.getElementById("pMid").value;
	 			  var pcid = document.getElementById("pCid").value;
					document.getElementById("Mcategary").value=pmid;
					document.getElementById("Scategary").value=pcid;
					return psize;
				}else{
					alert("亲，请输入每页要显示的数目🙂");
					return false;
				}
			});
		});
		function Submitpcats() {
			var psize = ${bean.pagesize};
			document.getElementById("plength").value=psize;
			return psize;
		}
		$(function(){
			$(".disabled").on("click",function(){
					return false;
			});
		});
		</script>
		<nav aria-label="..."  style="text-align:right">
      <ul class="pagination">
	      <li class="${p == 1 ? "disabled":""}"> <a href="Admin_home_linkQuery?pagenum=1&pagesize=${bean.pagesize}&cid=${bean.cid}&mid=${bean.mid}"><span aria-hidden="true">&laquo;</span></a></li>
				<li class="${p-1==0 ? "disabled":""}"><a href="Admin_home_linkQuery?pagenum=${p-1}&pagesize=${bean.pagesize}&cid=${bean.cid}&mid=${bean.mid}" aria-label="Previous">‹</a></li>
	      <li class="active"><a>${p}</a></li>
				<c:forEach begin="1" end="4" varStatus="st">
					<c:if test="${p+st.count<=bean.pagecount}">
						<li><a href="Admin_home_linkQuery?pagenum=${p+st.count}&pagesize=${bean.pagesize}&cid=${bean.cid}&mid=${bean.mid}">${p+st.count}</a></li>
					</c:if>
				</c:forEach>
				<li class="${p+1>bean.pagecount? "disabled":""}"><a href="Admin_home_linkQuery?pagenum=${p+1}&pagesize=${bean.pagesize}&cid=${bean.cid}&mid=${bean.mid}" aria-label="Next">›</a></li>
				<li class="${p+1>bean.pagecount? "disabled":""}"><a href="Admin_home_linkQuery?pagenum=${bean.pagecount}&pagesize=${bean.pagesize}&cid=${bean.cid}&mid=${bean.mid}"><span aria-hidden="true">&raquo;</span></a></li>
      </ul>
   </nav>

   <form style="text-align:right" action="Admin_home_pageSizeQuery" method="post">
	   <label>第<span id="page">${p}</span>/${bean.pagecount}页(共${bean.total}篇),每页
	     <input id="psize" type="text" name="psize" maxlength="4" size="4"  placeholder="${bean.pagesize}"/>篇</label>
			 <input type="hidden"  id="Mcategary" name="Mcategary" >
			 <input type="hidden"  id="Scategary" name="Scategary" >
	     <button id="submitpsize"class="btn btn-success">确定</button>
	 </form>
</div><!--class="pageDiv" >-->




		<div id="categarySearch" class="panel panel-warning addDiv" style="width:400px;text-align:center;margin:0px auto;border-color: #faebcc;display: block;box-sizing:border-box;">
			<div class="panel-heading" style="font-size:18px;color: #9932CC;padding:0px;">按 分 类 查 找</div>
			<div class="panel-body" style="color: black;background-color:#F8F8FF;border:0px  transparent;padding:0px;">

						<table class="addTable" style="border:0;width:380px">
							<form method="post" action="Admin_home_categaryQuery" id="categaryQuery"  style="width:300px;">
							<tr>
								<td style="border-style:none;font-size:16px;">主分类</td>
								<td style="width:300px;border-style:none">
									 <select class="form-control" id="pMid" name="pMid">
										 <option value="0">全部</option>
										 <c:forEach items="${categarys}" var="q" varStatus="st">
											  <c:if test="${!(st.last)}">
													 <c:if test="${q.mid==bean.mid}">
		                           <option selected="value" value="${q.mid}">${q.name}</option>
													 </c:if>
													 <c:if test="${!(q.mid==bean.mid)}">
													     <option value="${q.mid}">${q.name}</option>
													 </c:if>
                        </c:if>
										 </c:forEach>
										</select>
								</td>
							</tr>
							<tr>
								<td style="border-style:none;font-size:16px;">二级分类</td>
								<td style="border-style:none">
									 <select class="form-control" id="pCid" name="pCid">
										 <option value="0">全部</option>
										 <c:forEach items="${secondcats}" var="cat" varStatus="st">
											 <c:if test="${!(st.last)}">
											  <c:forEach items="${cat}" var="p" varStatus="st">
													<c:if test="${p.id==bean.cid}">
														 <option selected="value" value="${p.id}">${p.name}</option>
												  </c:if>
												  <c:if test="${!(p.id==bean.cid)}">
														 <option value="${p.id}">${p.name}</option>
												  </c:if>
											  </c:forEach>
											 </c:if>
										 </c:forEach>
										</select>
								</td>
							</tr>
							<tr class="submitTR">
								<td style="border-style:none" colspan="2" align="center">
									<input type="hidden"  name="plength"  id="plength">
									<button  id="submitcatsQuery"type="submit" class="btn btn-success" onclick="return Submitpcats()" style="width:80px;font-size:18px;padding:0px;">确 定</button>
								</td>
							</tr>
							</form>
						</table>

				</div>
			</div>

</div><!-- /.blog-sidebar -->
</div><!--row-->
</div><!--container-->

<div id=footer style="background-color: #7373B9;color:#434343;text-align:center;padding:15px;margin-bottom:0px;bottom:0px;font-size:90%;border:0;clear:right;">
	   <p>© CopyRight 2018-2019 www.flowingsun.com Inc All Rights Reserved 皖ICP备17013223号 &copy;<br> Email: Flowingsun007@163.com</p>
</div>
</body>
<!-- //Body -->
</html>
