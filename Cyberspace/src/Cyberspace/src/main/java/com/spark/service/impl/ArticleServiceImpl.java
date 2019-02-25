package com.spark.service.impl;

import com.spark.dao.ArticleDao;
import com.spark.dao.impl.ArticleDaoImpl;
import com.spark.domain.Article;
import com.spark.domain.PageBean;
import com.spark.service.ArticleService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service.impl
 * @Description: TODO
 * @date Date : 2018-12-09  13:15
 * @version： V1.0
 */
public class ArticleServiceImpl implements ArticleService {

    //实例化DAO层
    ArticleDao articleDao = new ArticleDaoImpl();
    @Override
    public List<Article> findAllArticle(Article article) throws SQLException {
        List<Article> articles = (List<Article>) articleDao.findAllArticle(article);
        return articles;
    }

    @Override
    public PageBean getPageBean(Integer currentPage, String article_author) throws SQLException {
        PageBean pageBean = new PageBean();
        //设置当前页
        pageBean.setCurrentPage(currentPage);
        //获取多少条记录
        //从数据库中进行查询
        Long count = articleDao.getCount(article_author);
        System.out.println("count: " + count);
        pageBean.setTotalCount(count.intValue());

        //一页展示多少数据
        Integer pageCount = 5; // 15/5 = / | 16/5=3..1 = 4 向上取整
        //总页数
        double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);
        System.out.println("totalPage: " + totalPage);
        pageBean.setTotalPage((int)totalPage);
        //当前页查询的角标
        Integer index = (pageBean.getCurrentPage()-1) * pageCount;
        System.out.println("index: " + index);
        List<Article> list = (List<Article>) articleDao.getPageData(index,pageCount,article_author);
        System.out.println("------> " + list);
        pageBean.setArticleList(list);
        return pageBean;
    }

    @Override
    public void insertIntoArticle(Article article) throws SQLException {
        int i = articleDao.insertIntoArticle(article);
        if(i > 0){
            System.out.println("发布文章成功! Success.............");
        }else{
            System.out.println("发布文章失败! Failed..............");
        }
    }

    @Override
    public List<Article> getIndexPageArticle() throws SQLException {
        //获取从数据库中所有公有的文章
        List<Article> indexPageArticle = articleDao.getIndexPageArticle();
        if (indexPageArticle != null){
            System.out.println("查询所有公开的文章数据！........");
        }else{
            System.out.println("Game Over 1");
        }
        return indexPageArticle;
    }

    @Override
    public List<Article> getDetailArticle(Article article) throws SQLException {
        //调用DAO层处理业务逻辑
        List<Article> detailArticle = articleDao.getDetailArticle(article);
        if(detailArticle != null){
            System.out.println("获取文章详情页成功！................");
            return detailArticle;
        }else{
            System.out.println("获取文章详情页失败! ...............");
        }
        return null;
    }

    @Override
    public void insertIntoArticleReadCount(Article article) throws SQLException {
        //调用DAO层处理业务逻辑
        int i = articleDao.insertIntoArticleReadCount(article);
        if(i > 0){
            System.out.println("文章ID：" + article.getArticle_id() + "被阅读了!");
        }else{
            System.out.println("Nothing................................");
        }
    }

    @Override
    public List<Article> getHotInfoArticle() throws SQLException {
        //调用DAO层处理业务逻辑
        List<Article> hotInfoArticle = articleDao.getHotInfoArticle();
        if(hotInfoArticle != null){
            System.out.println("查取热门文章...............");
            return  hotInfoArticle;
        }else{
            System.out.println("查询失败!..........");
        }
        return null;
    }

    @Override
    public void likeArticle(Article article) throws SQLException {
        //调用DAO层处理业务逻辑
        int i = articleDao.likeArticle(article);
        System.out.println("-------> i" + i);
        System.out.println("点赞成功！------------------>");

    }

    @Override
    public void unlikeArticle(Article article) throws SQLException {
        //调用DAO层处理业务逻辑
        int i = articleDao.unlikeArtile(article);
        System.out.println("****************> i " + i);
        System.out.println("取消点赞成功！ ------------> ");
    }

    @Override
    public List<Article> getRecommendArticle() throws SQLException {
        //调用DAO层处理业务逻辑
        List<Article> recommandArticle = articleDao.getRecommandArticle();

        if(recommandArticle != null){
            System.out.println("查询推荐数据成功！");
//            System.out.println(recommandArticle);
        }else{
            System.out.println("-----------> " + "查询数据失败！");
        }
        return recommandArticle;
    }


}
