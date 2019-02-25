package com.spark.servlet;

import com.spark.dao.ArticleDao;
import com.spark.dao.impl.ArticleDaoImpl;
import com.spark.domain.ArticleComment;
import com.spark.service.ArticleCommentService;
import com.spark.service.ArticleService;
import com.spark.service.impl.ArticleCommentServiceImpl;
import com.spark.service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-11  10:12
 * @version： V1.0
 */
@WebServlet(name = "ArticleCommentServlet",urlPatterns = {"/articleCommentServlet.do"})
public class ArticleCommentServlet extends HttpServlet {
    private static final long serialVersionUID = -4269622061369215294L;

    //统一实例化
    private ArticleComment articleComment = new ArticleComment();
    private ArticleCommentService articleCommentService = new ArticleCommentServiceImpl();
    private ArticleDao articleDao = new ArticleDaoImpl();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String article_id_ = request.getParameter("article_id");//获取文章的id
        int article_id = Integer.parseInt(article_id_);
        String ac_user = request.getParameter("ac_user");//获取评论的用户
        String ac_img = request.getParameter("ac_img");//获取评论人的头像
        String ac_content = request.getParameter("ac_content");//获取评论的内容
        String ac_date = request.getParameter("ac_date");//获取评论的日期
        System.out.println("article_id: " + article_id);
        System.out.println("ac_img: " + ac_img);
        System.out.println("ac_user: " + ac_user);
        articleComment.setArticle_id(article_id);
        articleComment.setAc_user(ac_user);
        articleComment.setAc_img(ac_img);
        articleComment.setAc_content(ac_content);
        articleComment.setAc_date(ac_date);
        System.out.println("-----------------------------------");
//        System.out.println(articleComment);
        System.out.println("-----------------------------------");
//        System.out.println("-------》☆" + articleComment);
        try {
            articleCommentService.insertIntoArticleCommentInfo(articleComment);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("cyberspace_article_detail.jsp?article_id=" + article_id  + "&ac_img" + ac_img + "&ac_user" + ac_user).forward(request,response);
    }

}
