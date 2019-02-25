package com.spark.service;

import com.spark.domain.Article;
import com.spark.domain.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-09  13:14
 * @version： V1.0
 */
public interface ArticleService {

    public List<Article> findAllArticle(Article article) throws SQLException;

    public PageBean getPageBean(Integer currentPage, String article_author) throws SQLException;

    public void insertIntoArticle(Article article) throws SQLException;

    public List<Article> getIndexPageArticle() throws SQLException;

    public List<Article> getDetailArticle(Article article) throws SQLException;

    public void insertIntoArticleReadCount(Article article) throws SQLException;

    public List<Article> getHotInfoArticle() throws SQLException;

    public void likeArticle(Article article) throws SQLException;

    public void unlikeArticle(Article article) throws SQLException;

    public List<Article> getRecommendArticle() throws SQLException;
}
