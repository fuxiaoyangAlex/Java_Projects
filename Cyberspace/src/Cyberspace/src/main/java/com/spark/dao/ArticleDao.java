package com.spark.dao;

import com.spark.domain.Article;
import com.spark.domain.ArticleComment;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-09  13:06
 * @version： V1.0
 */
public interface ArticleDao {

    public List<Article> findAllArticle(Article article) throws SQLException; //查出所有的文章


    public Long getCount(String article_author) throws SQLException;

    public List<Article> getPageData(Integer index, Integer pageCount, String article_author) throws SQLException;


    public int insertIntoArticle(Article article) throws SQLException;

    public List<Article> getIndexPageArticle() throws SQLException;

    public List<Article> getDetailArticle(Article article) throws SQLException;

    public int insertIntoArticleReadCount(Article article) throws SQLException;

    public List<Article> getHotInfoArticle() throws SQLException;

    public int likeArticle(Article article) throws SQLException;

    public int unlikeArtile(Article article) throws SQLException;

    public List<Article> getRecommandArticle() throws SQLException;
}
