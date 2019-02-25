package com.spark.service;

import com.spark.dao.ArticleDao;
import com.spark.dao.impl.ArticleDaoImpl;
import com.spark.domain.Article;
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
 * @date Date : 2018-12-11  14:21
 * @version： V1.0
 */
public class ArticleServiceTest {

    //统一实例化
    private Article article = new Article();
    private ArticleService articleService = new ArticleServiceImpl();
    private ArticleDao articleDao = new ArticleDaoImpl();

    @Test
    public void getRecommendArticle(){
        try {
            List<Article> recommandArticle = articleDao.getRecommandArticle();
            if(recommandArticle != null){
                System.out.println("★Size: " + recommandArticle.size());
                System.out.println("查询成功:................");
            }
        } catch (SQLException e) {
            System.out.println("查询失败:................");
            e.printStackTrace();
        }
    }


    @Test
    public void likeArticle() {
        article.setArticle_id(4);
        try {
            articleService.likeArticle(article);
            System.out.println("点赞成功👍");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unlikeArticle() {
        article.setArticle_id(4);
        try {
            articleService.unlikeArticle(article);
            System.out.println("取消点赞");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}