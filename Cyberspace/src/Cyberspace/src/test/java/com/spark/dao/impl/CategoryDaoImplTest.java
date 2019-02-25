package com.spark.dao.impl;

import com.spark.domain.Category;
import com.spark.service.CategoryService;
import com.spark.service.impl.CategoryServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao.impl
 * @Description: TODO
 * @date Date : 2018-12-08  19:47
 * @version： V1.0
 */
public class CategoryDaoImplTest {

//    @Test
//    public void save() {
//        Category category = new Category();
//        category.setParentid(3);
//        category.setCname("jQuery");
//        CategoryService categoryService = new CategoryServiceImpl();
//        try {
//            categoryService.save(category);
//            System.out.println("添加成功..........");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void getAllCategory() {
        Category category = new Category();
        category.setCauthor("Java编程语言");
        CategoryService categoryService = new CategoryServiceImpl();
        try {
            List<Category> allCategory = categoryService.getAllCategory(category);
            if (allCategory != null){
                System.out.println("查询成功！");
                for(Category c : allCategory){
                    System.out.println("---------> " + c);
                }
            }else{
                System.out.println("查询失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}