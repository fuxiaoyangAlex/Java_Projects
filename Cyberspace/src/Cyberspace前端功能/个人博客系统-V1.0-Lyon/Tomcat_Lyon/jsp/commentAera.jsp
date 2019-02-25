<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<script>
$(function(){
    var xmlhttp;
    $(".linkandshare-mainarticle").on("click",".submitThank",function(){
        var thenode = $(this);
        var spannode = thenode.children("span");
        var thanknode = spannode.children("span");
        var originNum = parseInt(thanknode.text());
        $.get(
            "forecheckLogin",
            function(result){
                if("success"==result){
                  var url = "foresetblogThank";
                  var articleId = ${article.getId()};
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
                 thenode.css({"margin-right":"80px","color":"#337ab7"});
                 spannode.css("color","#EE2C2C");
                 thanknode.text(originNum+1);
               }
            }
          });
});
</script>

<script>
$(function(){
    var xmlhttp;
    $("#commentsubmit").click(function(){
      $.get(
          "forecheckLogin",
          function(result){
              if("success"==result){
                 var usrComment = $("#maincomment").val();
                 var articleId = ${article.getId()};
                 var url = "foresetblogComment";

                 xmlhttp = new XMLHttpRequest();
                 xmlhttp.onreadystatechange=createCommentArea; //响应函数
                 xmlhttp.open("POST",url,true);
                 xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
                 if (usrComment==""){
                   alert("亲😶，请输入评论内容!");
                 }else{xmlhttp.send("articleId="+articleId+"&usrComment="+usrComment);}
              }else{
                  alert("亲😶，请先登录后再来评论哦！");
              }
          }
      );return false;

    });
    function createCommentArea(){
       if (xmlhttp.readyState==4 && xmlhttp.status==200){
         var usrComment = document.getElementById('maincomment').value;
         var newdiv = document.createElement("div");
         var commentlist = document.getElementById("commentList");
         newdiv.className = 'commentItem';
         var date = new Date();
         var time = date.toLocaleTimeString();
         newdiv.innerHTML = '<div><a href="">${user.getName()}</a>：'+usrComment+'</div><span>'+time+'</span><div id="praise"><span class="praise" onclick="return function(this)"><img src="images/zan.png" id="praise-img" /></span><span id="praise-txt">0</span><span id="add-num"><em></em></span></div>';
         commentlist.appendChild(newdiv);
         return true;
       }
    }
  });
</script>
<script>
$(function(){
    var xmlhttp;
    $("div.commentItem").on("click",".joinComment",function(){
        var mother = $(this).parent().parent();
        $.get(
            "forecheckLogin",
            function(result){
                if("success"==result){
                    if ($("div.recommentItem").length > 0){
                        $("div.recommentItem").remove();
                    }else{
                        var newdiv = document.createElement("div");
                        newdiv.className = 'recommentItem';
                        newdiv.innerHTML = '<textarea style="height:50px;width:450px;" class="recomment" placeholder="说一句：" id="recomment"></textarea>  <button type="button" id="recommentSubmit"  class="recommentSubmit" style="background-color:#7D9EC0;color:#FFFFFF;margin-bottom:10px;">提交</button>';
                        $(mother).append(newdiv);
                    }
                }else{
                    alert("😶，请先登录后再参与讨论哦！");
                }return false;
            });
          });
});
</script>

<script>
$(function(){
    var xmlhttp;
    $(".starArea").on("click",".recommentSubmit",function(){
      var thisnode = $(this);
      var mother = $(this).parents(".commentArea");
      var mainUserid = $(this).parents(".starArea").children("#praise").children("#commentId").text();
      $.get(
          "forecheckLogin",
          function(result){
              if("success"==result){
                 var usrComment = $(".recomment").val();
                 var articleId = ${article.getId()};
                 var url = "foresetreComment";
                 if (usrComment==""){
                   alert("亲，讨论的内容为空😶！");
                 }else{
                   xmlhttp = new XMLHttpRequest();
                   xmlhttp.onreadystatechange=createreComment; //响应函数
                   xmlhttp.open("POST",url,true);
                   xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
                   xmlhttp.send("articleId="+articleId+"&mainUserid="+mainUserid+"&usrComment="+usrComment);}
              }else{
                  alert("亲，您还未登录，请先登录后再来讨论哦！");
              }
          }
      );
      function createreComment(){
         if (xmlhttp.readyState==4 && xmlhttp.status==200){
           var usrComment = $(".recomment").val();
           thisnode.remove();
           $(".recomment").remove();
           var date = new Date();
           var time = date.toLocaleTimeString();
           var newdiv = document.createElement("div");
           newdiv.className = 'reComment';
           newdiv.id = 'reComment';
           newdiv.style='background-color:#f4f4f4;width:450px;font-size:13px;margin:5px;padding:8px 19px;border:1px solid #dedede';
           newdiv.innerHTML = ' <a href="#">'+ '${user.getName()}于' + time + '说：</a>' + usrComment;
           mother.append(newdiv);

         }return false;
      }
    });
  });
</script>


<div class="linkandshare-mainarticle">
  <c:if test="${thankNum==0}">
    <a href="javascript:void(0)" onclick="function()" style="margin-right:80px;color:#919191;" class="submitThank"><span class="glyphicon glyphicon-heart"><span>${thankNum}</span></span>感谢</a>
  </c:if>
  <c:if test="${thankNum!=0}">
    <a href="javascript:void(0)" onclick="function()" style="margin-right:80px;" class="submitThank"><span class="glyphicon glyphicon-heart" style="color:#EE2C2C;"><span>${thankNum}</span></span>感谢</a>
  </c:if>
  <c:if test="${commentNum==0}">
    <a href="" style="margin-right:80px;color:#919191;"><span style=""><img src="images/mother2.png" height="16" width="16"></span>${commentNum}评论</a>
  </c:if>
  <c:if test="${commentNum!=0}">
    <a href="" style="margin-right:80px;"><span><img src="images/mother1.png" height="16" width="16"></span>${commentNum}评论</a>
  </c:if>
  <a href="" style="margin-right:80px;color:#919191;"><span class="glyphicon glyphicon-star">9.9K收藏</span></a>
  <a href=""  style="color:#919191;"><span class="glyphicon glyphicon-send">分享</span></a>
</div>



<div>
<div class="commentHeader" >
<h3><span class="f-ff2">留言区</span></h3>
</div>
<textarea style="height:100px;" class="writeComment" placeholder="亲，在此写下您的评论！" id="maincomment"></textarea>
<button type="button" id="commentsubmit"  class="btn btn-default btn-xs " style="background-color:#7D9EC0;color:#FFFFFF;width=5px;height=5px;float:right;">
<span class="glyphicon glyphicon-comment">评论
</button>
</div>


<script>
  /* @author:Romey
   * 动态点赞
   * 此效果包含css3，部分浏览器不兼容（如：IE10以下的版本）
  */
  $(function(f){
      $(".praise").click(function(){
        var praise_img = $(this).children("#praise-img");
        var text_box = $(this).siblings("#add-num");
        var praise_txt = $(this).siblings("#praise-txt");
        var num=parseInt(praise_txt.text());
        var commentId = $(this).siblings("#commentId").text();
        $.get(
            "forecheckLogin",
            function(result){
                if("success"==result){
                    if(praise_img.attr("src") == ("images/yizan.png")){
                        praise_img.parent().html("<img src='images/zan.png' id='praise-img' class='animation' />");
                        praise_txt.removeClass("hover");
                        text_box.show().html("<em class='add-animation'>-1</em>");
                        text_box.children(".add-animation").removeClass("hover");
                        num -=1;
                    $.get(
                        "foredelStar?commentId="+commentId,
                        function(result){
                            if("success"==result){
                                 praise_txt.text(num);
                            }else{
                                 num +=1;
                                 praise_txt.text(num);
                                 alert("亲，手速太快，服务器没反应过来");
                            }
                        }
                    );return false;
                    }else{
                        praise_img.parent().html("<img src='images/yizan.png' id='praise-img' class='animation' />");
                        praise_txt.addClass("hover");
                        text_box.show().html("<em class='add-animation'>+1</em>");
                        text_box.children(".add-animation").addClass("hover");
                        num +=1;
                        $.get(
                            "foreaddStar?commentId="+commentId,
                            function(result){
                                if("success"==result){
                                praise_txt.text(num);
                                }else{
                                    num -=1;
                                    praise_txt.text(num);
                                    alert("亲，手速太快，喝杯水休息下！");
                                }
                            }
                        );return false;
                    }
                }else{
                    alert("亲，先登录，后点赞，谢谢呲瓷！☺️");
                }
          });return false;
      });
});
</script>


<div id="commentList">
<div class="commentCounts" >
共<span class="j-flag">${commentNum}</span>条评论
</div>

<c:forEach items="${comments}" var="p">
    <div class="commentItem">
        <div class="userheadimg" style="float:left">
            <img src="http://p1.music.126.net/MfZw0nF4eSLnvZUSYf4WTA==/3426078262499341.jpg?param=50y50">
        </div>
        <div class="commentArea" id="commentArea" style="margin-left:60px;font-size:15px">
            <div class="mainComment" style="color:#333;font-family:Arial, Helvetica, sans-serif;"><a href="#">${p.getuserName()}</a>：${p.getComment()}</div>
            <div id="starArea" class="starArea"style="margin-bottom:10px;">
                <span>(${p.getcommentDay()})
                    <button type="button" id="joinComment"  class="joinComment" style="background-color:#7D9EC0;color:#FFFFFF;width=5px;height=5px;">
                      <span class="glyphicon glyphicon-headphones"></span>加入讨论
                    </button>
                  </span>
                <div id="praise">
                    <span class="praise"><span class="glyphicon glyphicon-thumbs-up" id="praise-img"></span></span>
                    <span id="praise-txt">${p.getstarNum()}</span>
                    <span id="add-num"><em></em></span>
                    <span id="commentId" style="display:none;">${p.getId()}</span>
                </div>
                <c:forEach items="${p.getrecommentsList()}" var="q">
                 <div class="reComment" id="reComment" style="background-color:#f4f4f4;width:450px;font-size:13px;margin:5px;padding:8px 19px;border:1px solid #dedede">
                     <a href="#">${q.getuserName()}于(${q.getreCommentDay()})说：</a>${q.getreComment()}
                 </div>
                </c:forEach>
            </div>
        </div>
    </div>
</c:forEach>




</div>
