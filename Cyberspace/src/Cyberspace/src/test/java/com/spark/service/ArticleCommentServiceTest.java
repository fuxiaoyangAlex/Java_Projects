package com.spark.service;

import com.spark.domain.ArticleComment;
import com.spark.service.impl.ArticleCommentServiceImpl;
import com.spark.service.impl.ArticleServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-11  10:03
 * @version： V1.0
 */
public class ArticleCommentServiceTest {

    //统一实例化
    private ArticleCommentService articleCommentService = new ArticleCommentServiceImpl();
    private ArticleComment articleComment = new ArticleComment();
    @Test
    public void insertIntoArticleCommentInfo() throws SQLException {
        //模拟用户进行评论
        articleComment.setArticle_id(19);
        articleComment.setAc_content("网络爬虫按照系统结构和实现技术，大致可以分为以下几种类型：通用网络爬虫（General Purpose Web Crawler）、聚焦网络爬虫（Focused Web Crawler）、增量式网络爬虫（Incremental Web Crawler）、深层网络爬虫（Deep Web Crawler）。 实际的网络爬虫系统通常是几种爬虫技术相结合实现的");
        articleComment.setAc_date("2018-12-11 12:12");
        articleComment.setAc_user("一个奋斗的青年");
        articleComment.setAc_img("images/uimg/pic04.png");
        articleCommentService.insertIntoArticleCommentInfo(articleComment);
    }

    @Test
    public void getArticleCommentById() throws SQLException {
        //根据文章id对数据库进行检索获取此文章下的所有的用户评论
        articleComment.setArticle_id(19);
        //获取所有的评论数据
        List<ArticleComment> articleCommentById = articleCommentService.getArticleCommentById(articleComment);
        if(articleCommentById != null){
            System.out.println("获取数据成功！");
        }else{
            System.out.println("Fail---------------");
        }

    }
}