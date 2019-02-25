package com.spark.service;

import com.spark.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-08  17:34
 * @version： V1.0
 */
public interface CategoryService {
    public void save(Category category) throws SQLException;
    public List<Category> getAllCategory(Category category) throws SQLException;
    public void update(Category category) throws SQLException;
    public void delete(Category category) throws SQLException;
}
