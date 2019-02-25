package com.spark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-11  18:58
 * @version： V1.0
 */
@WebServlet(name = "ArticleSecondServlet",urlPatterns = {"/articleSecondServlet.do"})
public class ArticleSecondServlet extends HttpServlet {
    private static final long serialVersionUID = 4275246014425673056L;

    //=============================================★获取用户评论文章的信息将其存储到数据库中==============================================================================//
    public void service(HttpServletRequest request, HttpServletResponse response){
        //        <!--用于评论
        //        评论所需要的属性：
        //        1.所评论文章的article_id
        //        2.所评论的用户ac_user（名称）
        //        3.所评论的用户ac_img（头像)
        //        4.所评论的内容ac_content(内容）
        //        5.所评论的日期ac_date（日期）
        //        -->
        Date date = new Date(System.currentTimeMillis());
        String article_id_ = request.getParameter("article_id");
        int article_id = Integer.parseInt(article_id_);                                         //1.
        String ac_user = request.getParameter("ac_user");                                   //2.
        String ac_img = request.getParameter("ac_img");                                    //3.
        String ac_content = request.getParameter("ac_content");                           //4.
        String ac_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);  //5.
        System.out.println("article_id: " + article_id);
        System.out.println("ac_user: " + ac_user);
        System.out.println("ac_img: " + ac_img);
        System.out.println("ac_content: " + ac_content);
        System.out.println("ac_date: " + ac_date);
    }
}
