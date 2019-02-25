package com.spark.servlet;

import com.spark.dao.UimgDao;
import com.spark.domain.Article;
import com.spark.domain.PageBean;
import com.spark.service.ArticleService;
import com.spark.service.LogService;
import com.spark.service.UpdatePersonalInfoService;
import com.spark.service.impl.ArticleServiceImpl;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-09  13:17
 * @version： V1.0
 */
@WebServlet(name = "ArticleServlet",urlPatterns = {"/articleServlet.do"})
public class ArticleServlet extends BaseServlet {
    private static final long serialVersionUID = 2611426004319817438L;

    //实例化Service（ArticleService & LogService & Article）层（统一）
    private Article article = new Article();
    private ArticleService articleService = new ArticleServiceImpl();
    private LogService lg = new LogService();

/**
 * Functional description:
 *
 * 此处的查找所有文章的功能只是转发到cyberspace_article_manager.jsp中用于
 * 用户的文章统一查看和管理
 * @param:
 * @return: "cyberspace_article_manager.jsp"
 * @auther: wangzhuang2
 * @date: 2018/12/10 9:16
 */
    public String findAllArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        byte[] bytes = uname.getBytes("ISO-8859-1");
        String cauthor = new String(bytes,"UTF-8");
        System.out.println("findAllArticle ............" + cauthor);
        article.setArticle_author(cauthor);
        //查询所有Article的数据
        try {
            List<Article> allArticle = articleService.findAllArticle(article);
            if(allArticle != null){
                System.out.println("文章数据查询成功！");
                //将查询到的数据存储request域之中
                request.setAttribute("allArticle",allArticle);
                //====================================
                //写入用户活跃视图
                Date date_ = new Date(System.currentTimeMillis());
                String date = new SimpleDateFormat("HHmm").format(date_);
                lg.t_ochart(date);
                //====================================
                //返回指定页面的地址进行服务器内部的转发
                return "cyberspace_article_manager.jsp";
            }else{
                System.out.println("文章数据查询失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void deletedAricle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

//============================================✈文章管理分页数据==============================================================================//
    public String getPageData(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //1.获取参数-----------------uname&currentPage------------
        String uname = request.getParameter("uname");
        byte[] bytes = uname.getBytes("ISO-8859-1");
        String article_author = new String(bytes,"UTF-8");
        String currentPage = request.getParameter("currentPage");
        System.out.println("currentPage: " + currentPage);
        System.out.println("article_author: " + article_author);

        //调用服务层
        ArticleService articleService = new ArticleServiceImpl();
        //2.把页码给业务层 根据页码给我一个pageBean
        PageBean pageBean = null;
        try {
            pageBean = articleService.getPageBean(Integer.parseInt(currentPage),article_author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3.把pageBean写到域中
        request.setAttribute("pageBean",pageBean);
        //4.转发
        return "cyberspace_article_manager.jsp";
    }
//=============================================✈文章管理分页数据==============================================================================//





//=============================================🔉获取数据库中的数据将数据转发到主界面 cyberspace_article.jsp==============================================================================//
    /**
     * Functional description:
     * 根据传递过来的用户名获取用户的所有文章（
     * @param:
     * @return:
     * @auther: wangzhuang2
     * @date: 2018/12/10 10:28
     */
    public String getFinalAllArticle(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String uname = request.getParameter("uname");
        byte[] bytes = uname.getBytes("ISO-8859-1");
        String cauthor = new String(bytes,"UTF-8");
        System.out.println("findAllArticle ............" + cauthor);
        article.setArticle_author(cauthor);
        //查询所有Article的数据
        try {
            List<Article> allArticle = articleService.findAllArticle(article);
            if(allArticle != null){
                System.out.println("文章数据查询成功！");
                //将查询到的数据存储request域之中
                request.setAttribute("allArticle",allArticle);
                //====================================
                //写入用户活跃视图
                Date date_ = new Date(System.currentTimeMillis());
                String date = new SimpleDateFormat("HHmm").format(date_);
                lg.t_ochart(date);
                //====================================
                //返回指定页面的地址进行服务器内部的转发
                System.out.println("转发到cyberspace_article_demo.jsp界面");
                return "cyberspace_article_demo.jsp";
            }else{
                System.out.println("文章数据查询失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//=============================================🚎此功能为根据id和传过来的flag进行点赞👍逻辑的处理=============================================================================//
    public String praiseArticle(HttpServletRequest request, HttpServletResponse response){
        String article_id_ = request.getParameter("article_id");
        String flag = request.getParameter("flag");
        int article_id = Integer.parseInt(article_id_);
        System.out.println("article_id: ★" + article_id);
        article.setArticle_id(article_id);
        System.out.println("------★---------" + flag);
        if("like".equals(flag)){
            System.out.println("数据库为此点赞数量增加一个单位》》》》》》》》》");
            try {
                articleService.likeArticle(article);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if("unlike".equals(flag)){
            System.out.println("数据库为此点赞数量减少一个单位《《《《《《《《《");
            try {
                articleService.unlikeArticle(article);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "cyberspace_article_detail.jsp?article_id=" + article_id;
    }
//=============================================🔉获取数据库中的数据将数据转发到主界面 cyberspace_article.jsp==============================================================================//
//=============================================🚎此功能为根据id（来源：首页cyberspace_index.jsp&cyberspace_article_demo.jsp）==============================================================================//
/*此区域代码已经转移到具体的*.jsp页面*/
//    public String getDetailArticle(HttpServletRequest request, HttpServletResponse response){
//        String article_id_ = request.getParameter("article_id");
//        int article_id = Integer.parseInt(article_id_); //将字符串通过包装类转成int类型
//        article.setArticle_id(article_id);
//        //1.调用service层
//        try {
//            List<Article> detailArticle = articleService.getDetailArticle(article);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        //2.根据article_id 获取此article_id下所有的文章信息存储在List集合中
//        //3.将List集合转发（此转发）为重新打到文章详情页进行展示
//
//        return null;
//    }
//=============================================🚎此功能为根据id（来源：首页cyberspace_index.jsp&cyberspace_article_demo.jsp）==============================================================================//







}




/******************以下功能被重新转移到publicServlet.do模块下实现*************************************************************************************************/
/**======================添加文章==================================*/
//
//    public String addArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("I am comming ！............");
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        List<String> list = new ArrayList<String>();
//
//        String filename = UimgDao.getPhotoNewName(); //得到新的图片名称
//        System.out.println("得到的新的图片名称： " + filename);
//        //==========================================================================================================
//        //ServletContext是什么？ -----------> SerlvetContext是整个Web应用程序运行后的代表对象。（通过ServletConfig的
//        //getServletContext()方法取得）servletContext接口是Servlet中最大的一个接口，呈现了web应用的Servlet视图。ServletContext
//        //实例是通过 getServletContext()方法获得的，由于//HttpServlet继承GenericServlet的关系，GenericServlet类和HttpServlet
//        //类同时具有该方法。
//        //==========================================================================================================
//        ServletContext servletContext = request.getServletContext();
//        System.out.println("servletContext： " + servletContext);
//        //--->result
//        //org.apache.catalina.core.ApplicationContextFacade@6a17e56c
//        //org.apache.catalina.core.ApplicationContextFacade@298008a4
//        //org.apache.catalina.core.ApplicationContextFacade@289a91c4
//        //获取//数据库中存储格式:/webTest/imgs/***.jpg（理论上）
//        //第一步:获取页面上上传的图片资源
//        //@params: request：封装用户的请求 | servletContext 代表整个Web应用程序的对象
//        List<FileItem> myBlog = UimgDao.getRequsetFileItems(request,servletContext);
//        System.out.println("items: " + myBlog);
//        boolean isLoadToSQL =false;
//        for(FileItem my : myBlog ) {
//            if(!my.isFormField()){
//                //判断后缀名是否是jpg
//                if(UimgDao.isGif(my)) {
//                    isLoadToSQL = UimgDao.saveFile(my,filename);
//                }else {
//                    System.out.println("后缀格式有误，保存文件失败");
//                }
//            }else {
//                //获取表单中的非文件值表单中的空间name值
//                System.out.println("name值:  "+my.getFieldName());
//                //该name值空间中的value值
//                System.out.println(my.getString("UTF-8"));
//                list.add(my.getString("UTF-8"));
//            }
//        }
//
////        存在数据库里面的照片路径是在项目里的相对路径一般来说在项目中相对路径有request.getContextPath() + ... 开头，但经过实践在idea中获取
////        文件不需要项目名称直接通过二级父目录路径进行取得
////        String finalPhotoName= request.getContextPath()+"/images/uimg/"+filename;
//        Date date_ = new Date(System.currentTimeMillis());
//        String date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date_);
//        String date2 = new SimpleDateFormat("HHmm").format(date_);
//
//
//
//
//        String uname = request.getParameter("uname");
//        byte[] bytes = uname.getBytes("ISO-8859-1");
//        String article_author = new String(bytes,"UTF-8");    //======1.文章的作者 article_author
//        String article_time = date1;                                     //======2.文章的发布时间 article_time
//        System.out.println("****************************************");
//        for(String v : list){
//            System.out.println("v: " + v);
//
//        }
//        String [] info = new String[list.size()];
//        System.out.println("--------> " + info.length);
//        list.toArray(info);
//        String article_title = info[0];                                   //======3.文章的标题 article_title
//        String article_desc = info[1];                                    //======4.文章的摘要 article_desc
//        String article_sort = info[2];                                    //======5.文章的分类 article_sort
//        String article_pic = "images/uimg/"+filename;                     //======6.文章的图片 article_pic
//        int article_public = Integer.parseInt(info[3]);                   //======7.文章是否公开 article_public
//        String article_content = info[4];                                 //======8.文章的内容 article_content
//
//
//        System.out.println("article_author: " + article_author);
//        System.out.println("article_time: " + article_time);
//        System.out.println("article_title: " + article_title);
//        System.out.println("article_desc: " + article_desc);
//        System.out.println("article_sort: " + article_sort);
//        System.out.println("article_pic: " + article_pic);
//        System.out.println("article_public: " + article_public);
//        System.out.println("article_content: " + article_content);
//
//        article.setArticle_author(article_author);
//        article.setArticle_time(date1);
//        article.setArticle_title(article_title);
//        article.setArticle_desc(article_desc);
//        article.setArticle_sort(article_sort);
//        article.setArticle_pic(article_pic);
//        article.setIs_public(article_public);
//        article.setArticle_content(article_content);
//
//        try {
//            //存入数据库中
//            articleService.insertIntoArticle(article);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "cyberspace_article_manager.jsp";
//    }
