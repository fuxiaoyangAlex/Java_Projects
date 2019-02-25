package com.spark.dao.impl;

import com.spark.domain.Article;
import com.spark.service.ArticleService;
import com.spark.service.impl.ArticleServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao.impl
 * @Description: TODO
 * @date Date : 2018-12-09  13:11
 * @version： V1.0
 */
public class ArticleDaoImplTest {

    //实例化Service层
    private Article article = new Article();
    @Test
    public void findAllArticle() throws SQLException {

        article.setArticle_author("Java编程语言");

        ArticleService articleService = new ArticleServiceImpl();
        List<Article> allArticle = articleService.findAllArticle(article);
        for(Article article : allArticle){
            System.out.println("----------> " + allArticle);
        }
    }

    @Test
    public void getIndexPageArticle(){
        ArticleService articleService = new ArticleServiceImpl();
        try {
            List<Article> articles = articleService.getIndexPageArticle();
            System.out.println("----------------------------------------------");
            System.out.println(articles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}