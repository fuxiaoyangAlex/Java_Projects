package com.spark.service;

import com.spark.domain.ArticleComment;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-11  9:31
 * @version： V1.0
 */
public interface ArticleCommentService {

    public void insertIntoArticleCommentInfo(ArticleComment articleComment) throws SQLException;
    public List<ArticleComment> getArticleCommentById(ArticleComment articleComment) throws SQLException;
}
