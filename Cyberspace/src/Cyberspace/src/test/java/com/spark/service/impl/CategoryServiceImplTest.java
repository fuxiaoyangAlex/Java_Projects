package com.spark.service.impl;

import com.spark.domain.Category;
import com.spark.service.CategoryService;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service.impl
 * @Description: TODO
 * @date Date : 2018-12-09  16:15
 * @version： V1.0
 */
public class CategoryServiceImplTest {

    //统一实例化pojo类对象
    private Category category = new Category();
    //统一实例化Servlet层对象
    private CategoryService categoryService = new CategoryServiceImpl();
    @Test
    public void save() {
        //保存的条件
        //1.获取用户名 因此这里的用户名为外键测试的时候必须在数据库中先得到已有的用户名称
        //2.获取要保存分类的名号曾
        category.setCauthor("Erick王");
        category.setCname("程序设计");
        try {
            categoryService.save(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getAllCategory() {
        //getAll的条件
        //1.获取用户名
        //获取指定用户的所有的分类
    }

    @Test
    public void update() {
        //更新的条件
        //1.获取用户名 cauthor
        //2.获取要更新的cid（在表中唯一keyword）
        //3.要更新分类的名称cname
        category.setCname("Erick王");
        category.setCid(6);
        category.setCname("数据算法");
        try {
            categoryService.update(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {

        //删除条件
        //1.获取cid（只需要获取cid就可以了因为所有的用户分类都有唯一的cid
        category.setCid(7);
        try {
            categoryService.delete(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}