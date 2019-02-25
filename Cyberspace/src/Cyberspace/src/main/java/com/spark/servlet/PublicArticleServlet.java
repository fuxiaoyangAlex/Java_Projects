package com.spark.servlet;

import com.spark.dao.UimgDao;
import com.spark.domain.Article;
import com.spark.service.ArticleService;
import com.spark.service.LogService;
import com.spark.service.impl.ArticleServiceImpl;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-10  9:52
 * @version： V1.0
 */
@WebServlet(name = "PublicArticleServlet", urlPatterns = {"/publicArticleServlet.do"})
@MultipartConfig
public class PublicArticleServlet extends HttpServlet {
    private static final long serialVersionUID = -5550865093931948163L;

    //实例化Service（ArticleService & LogService & Article）层（统一）
    private Article article = new Article();
    private ArticleService articleService = new ArticleServiceImpl();
    private LogService lg = new LogService();
    private static String uname = null;

    /**============================================添加文章=============================================================*/
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I am comming ！............");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        List<String> list = new ArrayList<String>();

        String filename = UimgDao.getPhotoNewName(); //得到新的图片名称
        System.out.println("得到的新的图片名称： " + filename);
        //==========================================================================================================
        //ServletContext是什么？ -----------> SerlvetContext是整个Web应用程序运行后的代表对象。（通过ServletConfig的
        //getServletContext()方法取得）servletContext接口是Servlet中最大的一个接口，呈现了web应用的Servlet视图。ServletContext
        //实例是通过 getServletContext()方法获得的，由于//HttpServlet继承GenericServlet的关系，GenericServlet类和HttpServlet
        //类同时具有该方法。
        //==========================================================================================================
        ServletContext servletContext = request.getServletContext();
        System.out.println("servletContext： " + servletContext);
        //--->result
        //org.apache.catalina.core.ApplicationContextFacade@6a17e56c
        //org.apache.catalina.core.ApplicationContextFacade@298008a4
        //org.apache.catalina.core.ApplicationContextFacade@289a91c4
        //获取//数据库中存储格式:/webTest/imgs/***.jpg（理论上）
        //第一步:获取页面上上传的图片资源
        //@params: request：封装用户的请求 | servletContext 代表整个Web应用程序的对象
        List<FileItem> myBlog = UimgDao.getRequsetFileItems(request,servletContext);
        System.out.println("items: " + myBlog);
        boolean isLoadToSQL =false;
        for(FileItem my : myBlog ) {
            if(!my.isFormField()){
                //判断后缀名是否是jpg
                if(UimgDao.isGif(my)) {
                    isLoadToSQL = UimgDao.saveFile(my,filename);
                }else {
                    System.out.println("后缀格式有误，保存文件失败");
                }
            }else {
                //获取表单中的非文件值表单中的空间name值
                System.out.println("name值:  "+my.getFieldName());
                //该name值空间中的value值
                System.out.println(my.getString("UTF-8"));
                list.add(my.getString("UTF-8"));
            }
        }

//        存在数据库里面的照片路径是在项目里的相对路径一般来说在项目中相对路径有request.getContextPath() + ... 开头，但经过实践在idea中获取
//        文件不需要项目名称直接通过二级父目录路径进行取得
//        String finalPhotoName= request.getContextPath()+"/images/uimg/"+filename;
        Date date_ = new Date(System.currentTimeMillis());
        String date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date_);
        String date2 = new SimpleDateFormat("HHmm").format(date_);

        String article_time = date1;                                     //======2.文章的发布时间 article_time
        System.out.println("****************************************");
        for(String v : list){
            System.out.println("v: " + v);

        }
        String [] info = new String[list.size()];
        System.out.println("--------> " + info.length);
        list.toArray(info);
        String article_author = info[0];                                   //======1.文章的作者 article_author
        String article_title = info[1];                                   //======3.文章的标题 article_title
        String article_desc = info[2];                                    //======4.文章的摘要 article_desc
        String article_sort = info[3];                                    //======5.文章的分类 article_sort
        String article_pic = "images/uimg/"+filename;                     //======6.文章的图片 article_pic
        int article_public = Integer.parseInt(info[4]);                   //======7.文章是否公开 article_public
        String article_content = info[5];                                 //======8.文章的内容 article_content


        System.out.println("article_author: " + article_author);
        System.out.println("article_time: " + article_time);
        System.out.println("article_title: " + article_title);
        System.out.println("article_desc: " + article_desc);
        System.out.println("article_sort: " + article_sort);
        System.out.println("article_pic: " + article_pic);
        System.out.println("article_public: " + article_public);
        System.out.println("article_content: " + article_content);

        article.setArticle_author(article_author);
        article.setArticle_time(date1);
        article.setArticle_title(article_title);
        article.setArticle_desc(article_desc);
        article.setArticle_sort(article_sort);
        article.setArticle_pic(article_pic);
        article.setIs_public(article_public);
        article.setArticle_content(article_content);


         uname = article_author;
        System.out.println("uname: ---> " + uname);

        try {
            //发布的文章数据存入数据库中
            articleService.insertIntoArticle(article);
            //转发返回到我的文章界面
            System.out.println("uname: " + uname);
            response.sendRedirect("articleServlet.do?action=getFinalAllArticle&uname=" + uname);
//            articleServlet.do?action=getFinalAllArticle&uname=${user.uname}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //转发（间接返回主界面
    }
/**============================================添加文章=============================================================*/

}
