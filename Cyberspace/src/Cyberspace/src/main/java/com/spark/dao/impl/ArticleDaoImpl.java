package com.spark.dao.impl;

import com.spark.dao.ArticleDao;
import com.spark.domain.Article;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao.impl
 * @Description: TODO
 * @date Date : 2018-12-09  13:07
 * @version： V1.0
 */
public class ArticleDaoImpl implements ArticleDao {
    private QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource()); //加载数据源

    @Override
    public List<Article> findAllArticle(Article article) throws SQLException {
        //创建SQL语句
        String sql = "SELECT * FROM t_article WHERE article_author = ? ";
        //执行SQL语句
        List<Article> list = queryRunner.query(sql, new BeanListHandler<Article>(Article.class),article.getArticle_author());
        return list;
    }

    @Override
    public Long getCount(String article_author) throws SQLException {
        String sql = "SELECT count(*) FROM t_article WHERE article_author = ?";
        Long count = (Long)queryRunner.query(sql, new ScalarHandler<>(), article_author);
        return count;
    }

    @Override
    public List<Article> getPageData(Integer index, Integer pageCount, String article_author) throws SQLException {
        String sql = "SELECT * FROM t_article WHERE article_author = ? LIMIT ? , ? ";
        List<Article> articleList = queryRunner.query(sql, new BeanListHandler<Article>(Article.class),article_author,index,pageCount);
        return articleList;
    }

    @Override
    public int insertIntoArticle(Article article) throws SQLException {
        //创建sql语句
        String sql = "INSERT INTO t_article(article_author,article_title,article_content,article_time,article_pic,article_desc,article_sort,is_public) VALUES(?,?,?,?,?,?,?,?)";
        //执行sql语句
        Object [] objects = new Object[]{article.getArticle_author(),article.getArticle_title(),article.getArticle_content(),
        article.getArticle_time(),article.getArticle_pic(),article.getArticle_desc(),article.getArticle_sort(),article.getIs_public()};
        int update = queryRunner.update(sql, objects);
        return update;
    }

    @Override
    public List<Article> getIndexPageArticle() throws SQLException {
        //创建SQL语句
        String sql = "SELECT * FROM t_article WHERE is_public = ?";
        int tag = 0;
        //执行SQL语句
        List<Article> articles = queryRunner.query(sql,new BeanListHandler<Article>(Article.class),tag);
        return articles;
    }

    @Override
    public List<Article> getDetailArticle(Article article) throws SQLException {
        //创建sql语句
        String sql = "SELECT * FROM t_article WHERE article_id = ?";
        //执行sql语句
        List<Article> articles = queryRunner.query(sql,new BeanListHandler<Article>(Article.class),article.getArticle_id());
        return  articles;
    }

    public int insertIntoArticleReadCount(Article article) throws SQLException {
        //创建sql语句
        String sql = "UPDATE t_article SET read_count  = read_count + 1 WHERE article_id = ? ";
        //执行sql语句
        int update = queryRunner.update(sql, article.getArticle_id());
        return update;
    }

    /**
     * 获取热门文章以文章的点击量为标准
     * */
    @Override
    public List<Article> getHotInfoArticle() throws SQLException {
        //创建sql语句
        String sql = "SELECT * from t_article order by read_count desc LIMIT 6";
        //执行sql语句
        List<Article> query = queryRunner.query(sql, new BeanListHandler<Article>(Article.class));
        return query;
    }

    @Override
    public int likeArticle(Article article) throws SQLException {
        //创建SQL语句
        String sql = "UPDATE t_article SET praise_count = praise_count + 1 WHERE article_id = ?";
        //执行sql语句
        int update = queryRunner.update(sql, article.getArticle_id());
        return update;
    }

    @Override
    public int unlikeArtile(Article article) throws SQLException {
        //创建SQL语句
        String sql = "UPDATE t_article SET praise_count = praise_count - 1 WHERE article_id = ?";
        //执行SQL语句
        int update = queryRunner.update(sql, article.getArticle_id());
        return update;
    }

    @Override
    public List<Article> getRecommandArticle() throws SQLException {
        //创建SQL语句
        String sql = "SELECT * FROM t_article ORDER BY praise_count DESC LIMIT 5";
        //执行sql语句
        List<Article> query = queryRunner.query(sql, new BeanListHandler<Article>(Article.class));
        return query;
    }


}
