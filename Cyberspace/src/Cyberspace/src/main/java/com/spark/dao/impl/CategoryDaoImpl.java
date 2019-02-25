package com.spark.dao.impl;

import com.spark.dao.CategoryDao;
import com.spark.domain.Category;
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
 * @date Date : 2018-12-08  17:37
 * @version： V1.0
 */
public class CategoryDaoImpl implements CategoryDao {
    //CategoryDAO实现类

    private QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource()); //获取数据源
    @Override
    public String save(Category category) throws SQLException {
        System.out.println("add Category.......");
        //创建sql语句
        String sql = "INSERT INTO t_category(cname,cauthor) VALUES(?,?)";
        Object[] params = new Object[]{category.getCname(),category.getCauthor()};
        int update = queryRunner.update(sql, params);
        if (update > 0){
            return "添加分类成功！";
        }else{
            return "添加失败！";
        }
    }

    @Override
    public List<Category> getAllCategory(Category category) throws SQLException {
        System.out.println("findAll Category....");
        //创建sql语句
        String sql = "SELECT * FROM t_category WHERE cauthor = ?";
        List<Category> query = queryRunner.query(sql, new BeanListHandler<Category>(Category.class),category.getCauthor());
        return query;
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        System.out.println("update Category");
        //创建SQL语句
        String sql = "UPDATE t_category SET cname = ? WHERE  cid = ?";
        //获取Object[]
        Object[] params = new Object[]{category.getCname(),category.getCid()};
        //执行sql
        int update = queryRunner.update(sql, params);

        return update;
    }

    @Override
    public int deleteCategory(Category category) throws SQLException {
        System.out.println("delete Category");
        //创建SQL语句
        String sql = "DELETE FROM t_category WHERE cid = ? ";
        //执行sql
        int delete = queryRunner.update(sql, category.getCid());
        return delete;
    }
}
