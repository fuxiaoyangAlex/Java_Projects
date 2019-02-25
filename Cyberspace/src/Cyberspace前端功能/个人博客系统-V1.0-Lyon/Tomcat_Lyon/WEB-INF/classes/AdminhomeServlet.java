package blog.flowingsun.servlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import blog.flowingsun.bean.Articlelist;
import blog.flowingsun.bean.Comment;
import blog.flowingsun.bean.Pagebean;
import blog.flowingsun.bean.User;

public class AdminhomeServlet extends BaseBackServlet{

    public String adminHome(HttpServletRequest request,HttpServletResponse response){
      User user =(User) request.getSession().getAttribute("user");
      if(null==user){
          List<String> info = new ArrayList<String>();
          info.add("<p><font color='black'>请<font color='red'>登录</font>管理员账号！</font></p>");
          request.setAttribute("info",info);
          return "jsp/userLogin.jsp";
      }
      Pagebean pagebean = new Pagebean();
      int pagenum = 1;
      int pagesize = 8;
      int total = ArticleDAO.getArticlesnum();
      int pagecount = (total%pagesize ==0)? (total/pagesize) : (total/pagesize+1);
      int queryindex = (pagenum-1)*pagesize;                    //查询索引起始数
      int endindex = queryindex+pagesize;
      if (endindex > pagecount){endindex = pagecount;}
      pagebean.setPagesize(pagesize);
      pagebean.setPagenum(pagenum);
      pagebean.setTotal(total);
      pagebean.setPagecount(pagecount);
      pagebean.setQueryindex(queryindex);
      pagebean.setEndindex(endindex);
      request.setAttribute("pageBean",pagebean);
      ArrayList articles = ArticleDAO.getPartArticles(queryindex,pagesize);//articles列表保存了所有文章的bean对象
      request.setAttribute("articles",articles);
     return "jsp/backadmin/adminIndex.jsp";
    }

    public String writeBlog(HttpServletRequest request,HttpServletResponse response){
        return "jsp/backadmin/writeBlog.jsp";
    }

    public String submitBlog(HttpServletRequest request,HttpServletResponse response){
      Articlelist articlebean = new Articlelist();
      String tags = new String(request.getParameter("tags"));
      String[] taglist = tags.split(",");
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
      Date date = new Date();
      String createTime = sdf.format(date);
      String author = new String("阳光流淌007");
      String title = new String(request.getParameter("title"));
      String subtitle = new String(request.getParameter("subtitle"));
      String content = new String(request.getParameter("submitBlog"));
      String cat1 = request.getParameter("maincategary");
      String cat2 = request.getParameter("secondcats");
      int mid = Integer.parseInt(cat1);
      int cid = Integer.parseInt(cat2);
      articlebean.setAuthor(author);
      articlebean.setTitle(title);
      articlebean.setSummary(subtitle);
      articlebean.setContent(content);
      articlebean.setCreateDay(createTime);
      articlebean.setEditDay(createTime);
      articlebean.setCid(cid);
      articlebean.setMid(mid);
      List<String> info = new ArrayList<String>();                      //保存所有返回信息
      try{
          boolean flag2 = ArticleDAO.setArticle(articlebean);
          if(flag2==true){
              info.add("<p><font color='black'>文章提交<font color='red'>成功，</font>Congratulations!</font></p>"+tags);
              int articleid = ArticleDAO.getarticleId(title,cid,mid);
              for (String tag : taglist){
                  //tagid==0表示未查到相应名称的tag（即此标签为新命名标签），则先创建标签，再将其插入tagRelations表。
                  //tagid!=0表示之前就存在此名称的tag，直接插入tagRelations表即可！
                  int tagid = ArticleDAO.gettagName(tag);
                  if (tagid==0){
                      if(ArticleDAO.setTag(tag,createTime)){  //标签新建完成
                          tagid = ArticleDAO.gettagName(tag);
                          info.add("🙂恭喜，你创造了一个新标签：【"+tag+"】");
                      }
                  }
                  if(tagid!=0&&ArticleDAO.settagRelation(articleid,tag,tagid,createTime)==true){
                      info.add("🙂标签:【"+tag+"】已成功插入文章！");
                  }else{
                      info.add("😔标签:【"+tag+"】插入失败！！！");
                  }
              }
              info.add("</p><p>标题："+title+"</p><p>摘要："+subtitle+"</p><p>作者：【"+author+"】</p><p>创建时间:【"+createTime+"】</p><p>文章主分类：【"+cat1+"】二级分类：【"+cat2+"】</p>");
              request.setAttribute("info",info);                     //保存info信息
          }else{
              info.add("<p><font color='black'>文章提交<font color='red'>失败，</font>AdminhomeServlet.java下submitBlog方法中的ArticleDAO.setArticle(articlebean)出了点问题！！！</font></p>");
              request.setAttribute("info",info);                     //保存info信息
          }
      }catch(Exception e){
        e.printStackTrace();
      }return "jsp/login.jsp";
    }

    public String deleteBlog(HttpServletRequest request,HttpServletResponse response){
      if(request.getParameter("deleteArticle")!=null){
          String id = request.getParameter("deleteArticle");
          int aId = Integer.valueOf(id);
          List<String> info = new ArrayList<String>();
          ArrayList<String> tags = ArticleDAO.getarticleTags(aId);
          try{
              for (String tag : tags){
                  int tagId = ArticleDAO.gettagName(tag);
                  int relationNum = ArticleDAO.getRelationNum(tagId);
                  if(ArticleDAO.deltagRelation(tag,tagId)){
                      info.add("<p>标签:【"+tag+"】已删除<font color='red'>成功！</font></p>");
                  }
                  if(relationNum==1&&ArticleDAO.deleteTag(tagId)){
                      info.add("<p>标签：【"+tag+"】已从数据库删除！<font color='red'>成功！</font></p>");
                  }
              }
              if(ArticleDAO.deleteThank(aId)){
                  info.add("<p>文章【点赞】信息删除<font color='red'>成功！</font></p>");
              }
              if(ArticleDAO.delRecomment(aId)){
                  info.add("<p>文章【讨论区】内容删除<font color='red'>成功！</font></p>");
              }
              if(ArticleDAO.delComment(aId)){
                  info.add("<p>文章【评论】内容删除<font color='red'>成功！</font></p>");
              }
              if(ArticleDAO.DeleteArticle(aId)){
                  info.add("<p>文章删除<font color='red'>成功！</font>Congratulations!</p>");
                  request.setAttribute("info",info);                     //保存info信息
              }
          }catch(Exception ex){
            ex.printStackTrace();
          }
      }return "jsp/login.jsp";
    }

    public String editorBlog(HttpServletRequest request,HttpServletResponse response){
      try{
          int id = Integer.parseInt(request.getParameter("articleId"));
          Articlelist article = ArticleDAO.getArticle(id);
          ArrayList<String> articleTags = ArticleDAO.getarticleTags(id);
          article.setarticleTags(articleTags);
          request.setAttribute("article",article);
      }catch(Exception e){
      }return "jsp/backadmin/reWriteBlog.jsp";
    }

    public String rewriteSubmit(HttpServletRequest request,HttpServletResponse response){
      Articlelist articlebean = new Articlelist();
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
      Date date= new Date();
      String createTime=sdf.format(date);
      String a = request.getParameter("articleId");
      String b = request.getParameter("originCid");
      String c = request.getParameter("originMid");
      int id = Integer.parseInt(a);
      int originCid = Integer.parseInt(b);
      int originMid = Integer.parseInt(c);
      ArrayList<String> articleTags = ArticleDAO.getarticleTags(id);//articleTags保存的是id文章下所有的标签名
      String tags = new String(request.getParameter("tags"));       //tags是编辑过后所有提交的标签名
      String[] taglist = tags.split(",");
      String author = new String(request.getParameter("author"));
      String title = new String(request.getParameter("title"));
      String subtitle = new String(request.getParameter("subtitle"));
      String content = new String(request.getParameter("resubmitBlog"));
      String cat1 = request.getParameter("maincategary");
      String cat2 = request.getParameter("secondcats");
      int mid = Integer.parseInt(cat1);
      int cid = Integer.parseInt(cat2);
      articlebean.setAuthor(author);
      articlebean.setTitle(title);
      articlebean.setSummary(subtitle);
      articlebean.setContent(content);
      articlebean.setEditDay(createTime);
      articlebean.setCid(cid);
      articlebean.setMid(mid);
      List<String> info = new ArrayList<String>();                       //保存所有返回信息
        try{ //try执行的是检测文章分类有没有经过修改。如果没有修改，则对文章进行更新；否则，重新插入一篇新文章！
          if(mid==originMid&&cid==originCid){
                 articlebean.setId(id);
                 boolean flag = ArticleDAO.reSetArticle(articlebean);
                 if(flag==true){
                   info.add("<p><font color='black'>文章更改<font color='red'>成功，</font>Congratulations!</font></p>");

                   for (String originTag : articleTags){
                       int isDelete = 1;//用isDelete来标记是否需要删除标签的操作，=1则删除此条标签
                       for (String tag : taglist){
                           if(tag.equals(originTag)){
                               isDelete -= 1;
                           }
                       }
                       if(isDelete==1){//删除此条文章下的此条标签，如果此标签为该文章独有，则在标签库中也删除该标签
                           int tagId = ArticleDAO.gettagName(originTag);
                           int relationNum = ArticleDAO.getRelationNum(tagId);
                           if(relationNum==1){//此处deleteOneTag和deltagRelation功能有重叠，前者强调删除某文章中的单个标签关系；后者可以删除所有此标签名下的标签关系。不过在情况relationNum==1时，二者功能相同。
                               if(ArticleDAO.deleteTag(tagId)&&ArticleDAO.deltagRelation(originTag,tagId)){
                                   info.add("🙂原标签:【"+originTag+"】已销毁并成功从文章删除！");
                               }
                           }else{
                               if(ArticleDAO.deleteOneTag(id,originTag)){
                                   info.add("🙂原标签:【"+originTag+"】已成功从文章删除！");
                               }
                           }
                       }

                   }
                   for (String tag : taglist){
                       int istagExist = 0;
                       for (String originTag : articleTags){//for循环检测原文章中是否存在此标签tag
                           if(tag.equals(originTag)){
                               istagExist = 1;
                           }
                       }
                       if (istagExist == 1){          //flag=1表示原文章存在此条标签，那么不需要操作。
                           info.add("😔文章已经存在标签:【"+tag+"】，请勿重复插入！");
                       }else{                  //else表示原文章中不存在此条标签，那么此标签需要插入。
                           int tagid = ArticleDAO.gettagName(tag);
                           if (tagid==0){      //tagid==0表示标签库中没找到此条tag标签，即此条标签为新创建的，需要同时插入到articleTags表和tagRelations表。
                               if(ArticleDAO.setTag(tag,createTime)){  //标签新建完成（已插入articleTags表）
                                   tagid = ArticleDAO.gettagName(tag);       //取新创建的tagid，插入tagRelations表中
                                   if(tagid!=0&&ArticleDAO.settagRelation(id,tag,tagid,createTime)){
                                       info.add("🙂恭喜，你创造了一个全新的标签:【"+tag+"】，标签已成功插入文章！");
                                   }
                               }else{
                                   info.add("😔，你创造了一个全新的标签:【"+tag+"】，但是标签插入失败！");
                               }
                           }else{              //tagid！=0表示标签库中已经有同名的标签，那么无需新建标签，只需要插入TagRelations表即可！
                               if(ArticleDAO.settagRelation(id,tag,tagid,createTime)){
                                   info.add("🙂标签:【"+tag+"】已成功插入文章！");
                               }else{
                                   info.add("😔标签:【"+tag+"】插入文章失败！");
                               }
                           }
                       }
                   }
                   info.add("<p>标题："+title+"</p><p>摘要："+subtitle+"</p><p>作者："+author+"</p><p>修改时间：【"+createTime+"】</p><p>文章主分类：【"+cat1+"】二级分类：【"+cat2+"】</p>");
                 }else{
                   info.add("<p><font color='black'>文章更改<font color='red'>失败，</font>AdminhomeServlet.java下rewriteSubmit()方法中的ArticleDAO.reSetArticle()出了问题！！！</font></p>");
                 }
                 request.setAttribute("info",info);
           }else{//否，则表示用户修改了文章的分类，则将编辑好的文章插入新的分类中！（即在新的分类下新增一篇文章）
                 articlebean.setCreateDay(createTime);
                 boolean flag = ArticleDAO.DeleteArticle(id);
                 if(flag){
                     info.add("<p>亲，由于文章分类更改，原分类下的文章已经删除，编辑好的文章已放入新的分类中！</p>");
                 }
                 boolean flag2 = ArticleDAO.setArticle(articlebean);
                 if(flag2){
                     info.add("<p><font color='black'>亲，您的文章提交<font color='red'>成功，</font>Congratulations!</font></p>");
                     info.add("<p>标题："+title+"</p><p>摘要："+subtitle+"</p><p>作者：【"+author+"】</p><p>创建时间：【"+createTime+"】</p><p>文章主分类：【"+cat1+"】二级分类：【"+cat2+"】</p>");
                 }else{
                     info.add("<p><font color='black'>对不起，文章提交<font color='red'>失败，</font>问题出在哪儿呢？！</font></p>");
                 }
                 request.setAttribute("info",info);                     //保存info信息
           }
        }catch(Exception f){
          f.printStackTrace();
        }return "jsp/login.jsp";
    }

    public String linkQuery(HttpServletRequest request,HttpServletResponse response){
      if (request.getParameter("pagesize")!=null&&request.getParameter("pagenum")!=null){//【表示是通过href链接访问的】
          Pagebean pagebean = new Pagebean();
          String c = request.getParameter("cid");
          String n = request.getParameter("pagenum");
          String s = request.getParameter("pagesize");
          String m = request.getParameter("mid");
          int mid = Integer.valueOf(m);
          int pagenum = Integer.valueOf(n);
          int pagesize = Integer.valueOf(s);
          int cid = Integer.valueOf(c);
          int total;
          int pagecount;
          int queryindex = (pagenum-1)*pagesize;                    //查询索引起始数
          int endindex = queryindex+pagesize;
          ArrayList articles = null;
          try{
            if(mid!=0){
              if(cid!=0){//表示之前提交过分类查询，此次按照cid、mid和pagenum、pagesize进行查询。
                articles = ArticleDAO.getcategaryArticles(cid,mid,queryindex,pagesize);//articles列表保存了所有文章的bean对象
                total = ArticleDAO.getArticlenum(cid);
              }else{ //表示mid==0，cid！=0，查询特定主分类下所有文章
                total = ArticleDAO.getMainArticlenum(mid);
                articles = ArticleDAO.getMaincatArticles(mid,queryindex,pagesize);//articles列表保存了所有文章的bean对象
              }
            }else{//主分类为0，表示查询所有文章或者之前没有提交过分类查询，此次只按照pagenum和pagesize进行常规查询。
              articles = ArticleDAO.getPartArticles(queryindex,pagesize);//articles列表保存了所有文章的bean对象
              total = ArticleDAO.getArticlesnum();
            }
            request.setAttribute("articles",articles);
            pagecount = (total%pagesize ==0)? (total/pagesize) : (total/pagesize+1);
            if (endindex > pagecount){endindex = pagecount;}
            pagebean.setPagesize(pagesize);
            pagebean.setPagenum(pagenum);
            pagebean.setMid(mid);
            pagebean.setCid(cid);
            pagebean.setTotal(total);
            pagebean.setPagecount(pagecount);
            pagebean.setQueryindex(queryindex);
            pagebean.setEndindex(endindex);
            request.setAttribute("pageBean",pagebean);
          }catch(Exception f){
            f.printStackTrace();
          }
      }return "jsp/backadmin/adminIndex.jsp";
    }  //if不成立，表示此次查询非正常【href】链接查询，那么就包括了三种可能：1，首次查询；2.点击分类查询按钮查询；3，点击页面大小按钮查询

    public String categaryQuery(HttpServletRequest request,HttpServletResponse response){
      if(request.getParameter("pMid")!=null&&request.getParameter("pCid")!=null){
        Pagebean pagebean = new Pagebean();
        String m = request.getParameter("pMid");
        String c = request.getParameter("pCid");
        String s = request.getParameter("plength");
        int mid = Integer.valueOf(m);
        int cid = Integer.valueOf(c);
        int pagesize = Integer.valueOf(s);
        if(pagesize==0){
            pagesize = 8;
        }
        try{//mid对应主分类(0表示全部)；cid对应二级分类（0表示全部）
          int pagenum = 1;
          int queryindex = (pagenum-1)*pagesize; //查询索引起始数
          int endindex = queryindex+pagesize;
          int total=0;
          int pagecount;
          ArrayList articles = null;
          if(mid==0){
            if(cid==0){//mid和cid同时为0，表示查询的是全部文章，则返回adminHome()方法。
              return adminHome(request,response);
            }
          }else{
            if(cid==0){//mid!=0,cid==0表示选择了特定主分类下全部二级分类的文章
              total = ArticleDAO.getMainArticlenum(mid);
              articles = ArticleDAO.getMaincatArticles(mid,queryindex,pagesize);//articles列表保存了所有文章的bean对象
            }else{//mid!=0,cid!=0表示选择了特定的主分类和二级分类，则执行按类别查询,初始化agenum=1表示从第一页开始，pagesize默认从页面获取！
              total = ArticleDAO.getArticlenum(cid);
              articles = ArticleDAO.getcategaryArticles(cid,mid,queryindex,pagesize);//articles列表保存了所有文章的bean对象
            }
          }
          request.setAttribute("articles",articles);
          pagecount = (total%pagesize ==0)? (total/pagesize) : (total/pagesize+1);
          pagebean.setPagesize(pagesize);
          pagebean.setPagenum(pagenum);
          pagebean.setMid(mid);
          pagebean.setCid(cid);
          pagebean.setTotal(total);
          pagebean.setPagecount(pagecount);
          pagebean.setQueryindex(queryindex);
          pagebean.setEndindex(endindex);
          request.setAttribute("pageBean",pagebean);
        }catch(Exception e){
            e.printStackTrace();
        }
      }return "jsp/backadmin/adminIndex.jsp";
    } //if不成立，表示此次查询非【提交分类按钮查询】，则剩下的可能是1，提交‘每页显示XX条’按钮进行查询；2，首次无条件查询。

    public String pageSizeQuery(HttpServletRequest request,HttpServletResponse response){
      if (request.getParameter("psize")!=null){
          //如果获取到此参数,证明提交了‘每页显示XX条’按钮，则一同提交的参数还有下面类别选择中的mid、cid。pagenum重置为1，从新开始。
          //此处分两种情况：1.分类按钮之前未设置值，则进行pagenum和pagesize两参数查询；2.分类按钮设置过分类，则进行4参数查询
          try{
            Pagebean pagebean = new Pagebean();
            ArrayList articles =null;
            String mcat = request.getParameter("Mcategary");
            int mid = Integer.valueOf(mcat);
            String scat = request.getParameter("Scategary");
            int cid = Integer.valueOf(scat);
            int pagenum = 1;
            String s = request.getParameter("psize");
            int pagesize = Integer.valueOf(s);
            int queryindex = (pagenum-1)*pagesize;  //查询索引起始数
            int endindex = queryindex+pagesize;
            int total;
            int pagecount;
            if(mid==0){//主分类为0则表示查询全部文章，则只需要2个查询参数
                articles = ArticleDAO.getPartArticles(queryindex,pagesize);//articles列表保存了所有文章的bean对象
                total = ArticleDAO.getArticlesnum();
            }else{
              if(cid==0){//mid!=0,cid==0表示选择了特定主分类下全部二级分类的文章，用getMaincatArticles()方法
                total = ArticleDAO.getMainArticlenum(mid);
                articles = ArticleDAO.getMaincatArticles(mid,queryindex,pagesize);//articles列表保存了所有文章的bean对象
              }else{    //mid和cid都！=0表示特定分类下的查询，需要进行4个参数查询
                articles = ArticleDAO.getcategaryArticles(cid,mid,queryindex,pagesize);//articles列表保存了所有文章的bean对象
                total = ArticleDAO.getArticlenum(cid);
              }
            }
            request.setAttribute("articles",articles);
            pagecount = (total%pagesize ==0)? (total/pagesize) : (total/pagesize+1);
            if (endindex > pagecount){endindex = pagecount;}
            pagebean.setPagesize(pagesize);
            pagebean.setPagenum(pagenum);
            pagebean.setMid(mid);
            pagebean.setCid(cid);
            pagebean.setTotal(total);
            pagebean.setPagecount(pagecount);
            pagebean.setQueryindex(queryindex);
            pagebean.setEndindex(endindex);
            request.setAttribute("pageBean",pagebean);
          }catch(Exception f){
              //else表示未提交过按分类查询、故此时就执行按pagenum和pagesize两参数查询。
              f.printStackTrace();
          }
        } return "jsp/backadmin/adminIndex.jsp";
    }//以上三种情况都未出现，则表明，此次是第一次访问，各个参数都按初始化操作执行！

    public String queryCategary(HttpServletRequest request,HttpServletResponse response){
      Map<Integer,String>  catsMap = new HashMap<Integer,String>();
      int Mid = Integer.parseInt(request.getParameter("Mid"));
      String resultStr = "";
      try{
          catsMap = ArticleDAO.queryCategary(Mid);
          for(Entry<Integer,String> entry:catsMap.entrySet()){
              String item = (String.valueOf(entry.getKey()) + ":" + (String)entry.getValue() + ";");
              resultStr += item;
          }
      }catch(Exception e){
      }return "%" + resultStr.substring(0,resultStr.length()-1);
    }

    public String editTags(HttpServletRequest request,HttpServletResponse response){
      //String mid = request.getParameter("Mid");
      //int Mid = Integer.parseInt(mid);
      ArrayList<String> allTags = new ArrayList<String>();
      allTags = ArticleDAO.getallTags();
      request.setAttribute("allTags",allTags);
      try{
        User user =(User) request.getSession().getAttribute("user");
        if(null==user){
            List<String> info = new ArrayList<String>();
            info.add("<p><font color='black'>请<font color='red'>登录</font>管理员账号！</font></p>");
            request.setAttribute("info",info);
            return "jsp/userLogin.jsp";
        }
        int Mid = 5;
        Map<Integer,String>  CidMap = new HashMap<Integer,String>();
        CidMap = ArticleDAO.getallCid(Mid);
        ArrayList<Articlelist> allarticles = new ArrayList<Articlelist>();
        for(Entry<Integer,String> entry:CidMap.entrySet()){
            int Cid = entry.getKey();
            String cidName = entry.getValue();
            ArrayList<Articlelist> articles = new ArrayList<Articlelist>();
            articles = ArticleDAO.getCatsArticleAttrs(Cid);
            for(Articlelist article : articles){
                int id = article.getId();
                ArrayList<String> articleTags = new ArrayList<String>();
                articleTags = ArticleDAO.getarticleTags(id);
                int commentNum = CommentDAO.getcommentNum(id);
                int thankNum = CommentDAO.getthankNum(id);
                article.setMid(Mid);
                article.setCid(Cid);
                article.setCidName(cidName);
                article.setMidName("生活杂谈");
                article.setthankNum(thankNum);
                article.setcommentNum(commentNum);
                article.setarticleTags(articleTags);
            }
            allarticles.addAll(articles);
        }
        request.setAttribute("articles",allarticles);
      }catch(Exception e){
      }
     return "jsp/backadmin/editTags.jsp";
    }

    public String delOneArticletag(HttpServletRequest request,HttpServletResponse response){
      try{
          int articleId = Integer.parseInt(request.getParameter("articleId"));
          String tagName = request.getParameter("tagName");


          int tagId = ArticleDAO.gettagName(tagName);
          int relationNum = ArticleDAO.getRelationNum(tagId);
          if(relationNum==1){//此处deleteOneTag和deltagRelation功能有重叠，前者强调删除某文章中的单个标签关系；后者可以删除所有此标签名下的标签关系。不过在情况relationNum==1时，二者功能相同。
              if(ArticleDAO.deleteTag(tagId)&&ArticleDAO.deltagRelation(tagName,tagId)){
                  return "%success";
              }
          }else{
              if(ArticleDAO.deleteOneTag(articleId,tagName)){
                  return "%success";
              }
          }
          return "%failed";
      }catch(Exception e){
      }return "jsp/userLogin.jsp";
    }

    public String editArticleTags(HttpServletRequest request,HttpServletResponse response){
      try{
          int id = Integer.parseInt(request.getParameter("articleId"));
          String[] tagList = request.getParameter("tagList").split(",");
          ArrayList<String> articleTags = ArticleDAO.getarticleTags(id);
          List<String> info = new ArrayList<String>();
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
          Date date= new Date();
          String createTime=sdf.format(date);
          for (String originTag : articleTags){
              int isDelete = 1;//用isDelete来标记是否需要删除标签的操作，=1则删除此条标签
              for (String tag : tagList){
                  if(tag.equals(originTag)){
                      isDelete -= 1;
                  }
              }
              if(isDelete==1){//删除此条文章下的此条标签，如果此标签为该文章独有，则在标签库中也删除该标签
                  int tagId = ArticleDAO.gettagName(originTag);
                  int relationNum = ArticleDAO.getRelationNum(tagId);
                  if(relationNum==1){//此处deleteOneTag和deltagRelation功能有重叠，前者强调删除某文章中的单个标签关系；后者可以删除所有此标签名下的标签关系。不过在情况relationNum==1时，二者功能相同。
                      if(ArticleDAO.deleteTag(tagId)&&ArticleDAO.deltagRelation(originTag,tagId)){
                          info.add("🙂原标签:【"+originTag+"】已销毁并成功从文章删除！");
                      }
                  }else{
                      if(ArticleDAO.deleteOneTag(id,originTag)){
                          info.add("🙂原标签:【"+originTag+"】已成功从文章删除！");
                      }
                  }
              }
          }

          for (String tag : tagList){
              int istagExist = 0;
              for (String originTag : articleTags){//for循环检测原文章中是否存在此标签tag
                  if(tag.equals(originTag)){
                      istagExist = 1;
                  }
              }
              if (istagExist == 1){          //flag=1表示原文章存在此条标签，那么不需要操作。
                  info.add("😔文章已经存在标签:【"+tag+"】，请勿重复插入！");
              }else{                  //else表示原文章中不存在此条标签，那么此标签需要插入。
                  int tagid = ArticleDAO.gettagName(tag);
                  if (tagid==0){      //tagid==0表示标签库中没找到此条tag标签，即此条标签为新创建的，需要同时插入到articleTags表和tagRelations表。
                      if(ArticleDAO.setTag(tag,createTime)){  //标签新建完成（已插入articleTags表）
                          tagid = ArticleDAO.gettagName(tag);       //取新创建的tagid，插入tagRelations表中
                          if(tagid!=0&&ArticleDAO.settagRelation(id,tag,tagid,createTime)){
                              info.add("🙂恭喜，你创造了一个全新的标签:【"+tag+"】，标签已成功插入文章！");
                          }
                      }else{
                          info.add("😔，你创造了一个全新的标签:【"+tag+"】，但是标签插入失败！");
                      }
                  }else{              //tagid！=0表示标签库中已经有同名的标签，那么无需新建标签，只需要插入TagRelations表即可！
                      if(ArticleDAO.settagRelation(id,tag,tagid,createTime)){
                          info.add("🙂标签:【"+tag+"】已成功插入文章！");
                      }else{
                          info.add("😔标签:【"+tag+"】插入文章失败！");
                      }
                  }
              }
          }
          return "%success";
      }catch(Exception e){
      }return "%failed";
    }
}
