package com.spark.dao.impl;

import com.spark.dao.ArticleCommentDao;
import com.spark.domain.ArticleComment;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao.impl
 * @Description: TODO
 * @date Date : 2018-12-11  9:34
 * @version： V1.0
 */
public class ArticleCommentDaoImpl implements ArticleCommentDao {

    //统一实例化
    private QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
    private ArticleComment articleComment;
    @Override
    public int insertIntoArticleCommentInfo(ArticleComment articleComment) throws SQLException {
        //创建sql语句
        String sql = "INSERT INTO t_article_comment(article_id,ac_user,ac_img,ac_content,ac_date) values(?,?,?,?,?)";
        //执行sql语句

        int update = queryRunner.update(sql, articleComment.getArticle_id(), articleComment.getAc_user(), articleComment.getAc_img(), articleComment.getAc_content(), articleComment.getAc_date());

        return update;
    }

    @Override
    public List<ArticleComment> getAllArticleCommentInfo(ArticleComment articleComment) throws SQLException {
        //创建sql语句
        String sql = "SELECT * FROM t_article_comment WHERE article_id = ?";
        //执行sql语句
        List<ArticleComment> query = queryRunner.query(sql, new BeanListHandler<ArticleComment>(ArticleComment.class),articleComment.getArticle_id());
        return query;
    }


}
