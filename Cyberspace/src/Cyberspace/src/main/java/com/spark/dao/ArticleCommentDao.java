package com.spark.dao;

import com.spark.domain.ArticleComment;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-11  9:33
 * @version： V1.0
 */
public interface ArticleCommentDao {

    public int insertIntoArticleCommentInfo(ArticleComment articleComment) throws SQLException; //用户评论操作
    public List<ArticleComment> getAllArticleCommentInfo(ArticleComment articleComment) throws SQLException; //传入文章的id用来获取此文章的所有评论信息

}
