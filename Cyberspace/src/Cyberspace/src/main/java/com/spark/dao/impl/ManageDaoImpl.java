package com.spark.dao.impl;

import com.spark.dao.ManageDao;
import com.spark.domain.*;
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
 * @date Date : 2018-12-12  8:14
 * @version： V1.0
 */
public class ManageDaoImpl implements ManageDao {


    //统一实例化
    private Article article = new Article();
    private QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
    @Override
    public int getAllCountUser() throws SQLException {
        //创建SQL语句
        String sql = "SELECT * FROM t_user";
        //执行SQL语句
        List<User> query = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        //返回查询的总数
        int q = query.size();
        return q;
    }

    @Override
    public int getAllCountArticle() throws SQLException {
        //创建sql语句
        String sql = "SELECT * FROM t_article";
        //执行sql语句
        List<Article> query = queryRunner.query(sql, new BeanListHandler<Article>(Article.class));
        //返回查询的总数
        int q = query.size();
        return q;
    }

    @Override
    public int getAllCountComment() throws SQLException {
        //创建sql语句
        String sql = "SELECT * FROM t_article_comment";
        //执行sql语句
        List<ArticleComment> query = queryRunner.query(sql, new BeanListHandler<ArticleComment>(ArticleComment.class));
        //返回查询的总数
        int q = query.size();
        return q;
    }

    @Override
    public int getAlllCountPhoto() throws SQLException {
        //创建sql语句
        String sql = "SELECT * FROM t_photo";
        //执行sql语句
        List<Photo> query = queryRunner.query(sql, new BeanListHandler<Photo>(Photo.class));
        //返回查询的总数
        int q = query.size();
        return q;
    }
}
