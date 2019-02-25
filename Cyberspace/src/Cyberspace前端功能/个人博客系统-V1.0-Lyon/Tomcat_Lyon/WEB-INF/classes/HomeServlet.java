package blog.flowingsun.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import blog.flowingsun.util.DateUtil;
import blog.flowingsun.bean.Articlelist;
import blog.flowingsun.bean.User;
import blog.flowingsun.bean.Comment;
import blog.flowingsun.bean.Recomment;



public class HomeServlet extends BaseForeServlet{

    public String foreHome(HttpServletRequest request,HttpServletResponse response){
        return "jsp/index.jsp";
    }

    public String LoginPage(HttpServletRequest request,HttpServletResponse response){
        return "jsp/userLogin.jsp";
    }

    public String userRegister(HttpServletRequest request,HttpServletResponse response){
        String username = new String(request.getParameter("username"));
        String useremail = request.getParameter("useremail");//接收用户注册的四项内容：用户名称、电话、emaill、密码
        String userpass = request.getParameter("userpass");
        String userphone = request.getParameter("userphone");
        List<String> info = new ArrayList<String>();    //保存所有返回信息

        User user = new User();                     //实例化VO
        user.setName(username);                     //传入用户注册的四项参数：姓名、电话、emaill、密码
        user.setEmail(useremail);
        user.setPassword(userpass);
        user.setTelephone(userphone);
        try{
          User usertoken = UserDAO.findLogin(user);
          if(usertoken.getId()!=0&&usertoken.getName()!=null){
              info.add("<p><font color='white'>亲爱的:"+usertoken.getName()+"</font></p>");
              info.add("<p><font color='white'>该邮箱或手机号<font color='red'>已注册过，</font>请勿重复注册！！</font></p>");
              request.setAttribute("info",info);                     //保存info信息
          }else{
              try{
                if(UserDAO.findRegister(user)==true){
                    info.add("<p><font color='white'>注册<font color='red'>成功</font>！</p>"+user.getName()+",感谢您的注册！</font></p>");
                }else{
                    info.add("<p><font color='white'>对不起，注册<font color='red'>失败，</font>请重新尝试！</font></p>");
                }
                request.setAttribute("info",info);                     //保存info信息
              }catch(Exception e){
                  e.printStackTrace();
                  info.add("<p>对不起，注册<font color='red'>失败，</font>请重新尝试！</p>");
                  request.setAttribute("info",info);                     //保存info信息
              }
          }
        }catch(Exception e){
        }
        return "jsp/userLogin.jsp";
    }

    public String userLogin(HttpServletRequest request,HttpServletResponse response){
        List<String> info = new ArrayList<String>();    //保存所有返回信息
        String userid = request.getParameter("userid");     //接收用户登录email内容
        String userpass = request.getParameter("userpass"); //接收userpass内容
        String rephone = "^[0-9]+";
        User user = new User();
        if(Pattern.matches(rephone,userid)){         //判断传入的userid值是邮箱还是手机号
              user.setTelephone(userid);             //根据不同情况设置具体的传入参数为邮箱号或手机号
        }else{user.setEmail(userid);
        }
        user.setPassword(userpass);                  //设置password

        try{
          //先用if语句查询用户邮箱和手机号，如果数据库中已存在，则用户已经注册过！否则，证明是新用户，else语句执行注册过程！
          User usertoken = UserDAO.findLogin(user);
          if(usertoken.getId()!=0&&usertoken.getName()!=null){
               info.add("<p><font color='white'>亲爱的:"+usertoken.getName()+"</font>登录成功！</p>");
               request.setAttribute("info",info);                     //保存info信息
               request.getSession().setAttribute("user", usertoken);
               return "%success";
           }else{
               info.add("<p><font color='white'>账号或密码错误<font color='red'>登录失败，</font>请再次尝试！</font></p>");
               request.setAttribute("info",info);                     //保存info信息
               return "%fail";
           }
        }catch(Exception e){
        }return "jsp/userLogin.jsp";
    }

    public String viewCatBlogs(HttpServletRequest request,HttpServletResponse response){
        int cid = Integer.parseInt(request.getParameter("Cid"));
        ArrayList<Articlelist> articles = new ArrayList<Articlelist>();
        ArrayList<String> allTags = new ArrayList<String>();
        articles = ArticleDAO.getCatsArticles(cid);
        allTags = ArticleDAO.getallTags();
        for(Articlelist article : articles){
            int id = article.getId();
            ArrayList<String> articleTags = new ArrayList<String>();
            articleTags = ArticleDAO.getarticleTags(id);
            int commentNum = CommentDAO.getcommentNum(id);
            int thankNum = CommentDAO.getthankNum(id);
            article.setarticleTags(articleTags);
            article.setcommentNum(commentNum);
            article.setthankNum(thankNum);
        }
        User user =(User) request.getSession().getAttribute("user");
        if(null!=user){
            request.setAttribute("user",user);
        }
        request.setAttribute("articles",articles);
        request.setAttribute("allTags",allTags);
        return "jsp/categaryArticles.jsp";
    }

    public String viewSingleBlog(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("Id"));
        Articlelist article = new Articlelist();
        ArrayList<String> articleTags = new ArrayList<String>();
        ArrayList<String> allTags = new ArrayList<String>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        article = ArticleDAO.getArticle(id);
        if(article.getTitle()==null){
            article.setTitle("亲，地球上没有这篇文章哦~囧！");
        }
        articleTags = ArticleDAO.getarticleTags(id);
        allTags = ArticleDAO.getallTags();
        comments = CommentDAO.getComments(id);
        article.setarticleTags(articleTags);
        for (Comment bean : comments){
            int mainCommentid = bean.getId();
            ArrayList<Recomment> recommentBean = new ArrayList<Recomment>();
            recommentBean = CommentDAO.getreComments(mainCommentid);
            bean.setrecommentsList(recommentBean);
        }
        //原始版本采用：int commentNum = CommentDAO.getcommentNum(id);
        int commentNum = comments.size();
        int thankNum = CommentDAO.getthankNum(id);
        User user =(User) request.getSession().getAttribute("user");
        if(null!=user){
            request.setAttribute("user",user);
        }
        request.setAttribute("allTags",allTags);
        request.setAttribute("commentNum",commentNum);
        request.setAttribute("thankNum",thankNum);
        request.setAttribute("article",article);
        request.setAttribute("comments",comments);
        return "jsp/singleArticle.jsp";
    }

    public String setblogComment(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getName();
        int userId = user.getId();
        int starNum = 0;
        int commentNum = 0;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date= new Date();
        String commentTime=sdf.format(date);
        String usrComment = request.getParameter("usrComment");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        commentNum = CommentDAO.setComment(userName,commentTime,usrComment,articleId,userId,starNum);
        if (commentNum!=0){//表示评论插入成功,且commentNum为插入成功后更新的评论数
            if(ArticleDAO.updatecommentNum(commentNum,articleId)==true){
                List<String> info = new ArrayList<String>();    //保存所有返回信息
                info.add("<p>亲爱的，评论<font color='red'>成功</font>！</p>");
                request.setAttribute("info",info);
                return "jsp/login.jsp";
            }
        }return "jsp/login.jsp";
    }

    public String setblogThank(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getName();
        int userId = user.getId();
        int thankNum = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String thankTime = sdf.format(date);
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        List<String> info = new ArrayList<String>();    //保存所有返回信息
        try{
            thankNum = CommentDAO.setThank(articleId,userId,userName,thankTime);
        }catch(Exception e){
            info.add("<p>Error!<font color='red'>details:</font>！</p>" + e);
        }
        if (thankNum!=0){//表示提交感谢成功,且thankNum为提交感谢成功后更新的感谢数
            if(ArticleDAO.updatethankNum(thankNum,articleId)==true){
                info.add("<p>Dear<font color='red'>succeed</font>！set blog thank!</p>");
                request.setAttribute("info",info);
                return "jsp/login.jsp";
            }
        }return "jsp/login.jsp";
    }

    public String setreComment(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getName();
        int userId = user.getId();
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int mainCommentid = Integer.parseInt(request.getParameter("mainUserid"));
        String reComment = request.getParameter("usrComment");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date= new Date();
        String reCommentDate=sdf.format(date);
        if (CommentDAO.setreComment(articleId,mainCommentid,userId,userName,reComment,reCommentDate)==true){
            List<String> info = new ArrayList<String>();    //保存所有返回信息
            info.add("<p>亲爱的，评论<font color='red'>成功</font>！</p>");
            request.setAttribute("info",info);
            return "jsp/login.jsp";
        }return "jsp/login.jsp";
    }

    public String checkLogin(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        if(null!=user){
            return "%success";
        }
        return "%fail";
    }

    public String checkLogout(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        if(null!=user){
            request.getSession().setAttribute("user", null);
            return "%success";
        }
        return "%fail";
    }

    public String addStar(HttpServletRequest request, HttpServletResponse response) {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        boolean flag = CommentDAO.addStar(commentId);
        if(flag==true){
            return "%success";
        }
        return "%fail";
    }

    public String delStar(HttpServletRequest request, HttpServletResponse response) {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        boolean flag = CommentDAO.delStar(commentId);
        if(flag==true){
            return "%success";
        }
        return "%fail";
    }

    public String searchKeyword(HttpServletRequest request,HttpServletResponse response){
        ArrayList<String> allTags = new ArrayList<String>();
        ArrayList<Articlelist> articles = new ArrayList<Articlelist>();
        String keyWord = request.getParameter("keyWord");
        articles = ArticleDAO.SearchKeyword(keyWord);
        for(Articlelist article : articles){
            int id = article.getId();
            ArrayList<String> articleTags = new ArrayList<String>();
            articleTags = ArticleDAO.getarticleTags(id);
            article.setarticleTags(articleTags);
        }
        User user =(User) request.getSession().getAttribute("user");
        if(null!=user){
            request.setAttribute("user",user);
        }
        allTags = ArticleDAO.getallTags();
        request.setAttribute("allTags",allTags);
        if(request.getAttribute("articles")!=null){
            request.removeAttribute("articles");
            request.setAttribute("articles",articles);
        }else{
            request.setAttribute("articles",articles);
        }
        return "jsp/categaryArticles.jsp";
    }

    public String getTagarticles(HttpServletRequest request,HttpServletResponse response){
        String tagName = request.getParameter("tagName");
        ArrayList<Integer> idList = new ArrayList<Integer>();
        ArrayList<String> allTags = new ArrayList<String>();
        ArrayList<Articlelist> articles = new ArrayList<Articlelist>();
        idList = ArticleDAO.getTagArticlesId(tagName);
        for (int id : idList){
            ArrayList<String> articleTags = new ArrayList<String>();
            Articlelist bean = new Articlelist();
            articleTags = ArticleDAO.getarticleTags(id);
            bean = ArticleDAO.getArticle(id);
            bean.setarticleTags(articleTags);
            articles.add(bean);
        }
        User user =(User) request.getSession().getAttribute("user");
        if(null!=user){
            request.setAttribute("user",user);
        }
        allTags = ArticleDAO.getallTags();
        request.setAttribute("allTags",allTags);
        request.setAttribute("articles",articles);
        return "jsp/categaryArticles.jsp";
    }
}
