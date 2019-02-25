package com.spark.dao;

import com.spark.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-08  17:37
 * @version： V1.0
 */
public interface CategoryDao {
    public String save(Category category) throws SQLException;

    public List<Category> getAllCategory(Category category) throws SQLException;

    public int updateCategory(Category category) throws SQLException;


    public int deleteCategory(Category category) throws SQLException;
}
