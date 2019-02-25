package com.spark.service.impl;

import com.spark.dao.ArticleCommentDao;
import com.spark.dao.ArticleDao;
import com.spark.dao.impl.ArticleCommentDaoImpl;
import com.spark.dao.impl.ArticleDaoImpl;
import com.spark.domain.ArticleComment;
import com.spark.service.ArticleCommentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service.impl
 * @Description: TODO
 * @date Date : 2018-12-11  9:32
 * @version： V1.0
 */
public class ArticleCommentServiceImpl implements ArticleCommentService {
   //统一实例化
    private ArticleComment articleComment = new ArticleComment();
    private ArticleCommentDao articleCommentDao = new ArticleCommentDaoImpl();
    @Override
    public void insertIntoArticleCommentInfo(ArticleComment articleComment) throws SQLException {
        int i = articleCommentDao.insertIntoArticleCommentInfo(articleComment);
        if(i > 0){
            System.out.println("此用户评论成功！.....................");
        }else{
            System.out.println("评论失败.........................");
        }

    }

    @Override
    public List<ArticleComment> getArticleCommentById(ArticleComment articleComment) throws SQLException {
        //调用dao层
        List<ArticleComment> allArticleCommentInfo = articleCommentDao.getAllArticleCommentInfo(articleComment);
        if(allArticleCommentInfo != null){
            System.out.println("--------------文章评论查询成功------------------------");
//            System.out.println(allArticleCommentInfo);
            System.out.println("--------------文章评论查询成功------------------------");
        }else{
            System.out.println("---------------文章评论查询失败-----------------------");
        }
        return allArticleCommentInfo;
    }
}
