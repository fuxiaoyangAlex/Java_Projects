package com.spark.service.impl;

import com.spark.dao.CategoryDao;
import com.spark.dao.impl.CategoryDaoImpl;
import com.spark.domain.Category;
import com.spark.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service.impl
 * @Description: TODO
 * @date Date : 2018-12-08  17:35
 * @version： V1.0
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public void save(Category category) throws SQLException {
        //调用DAO层
        System.out.println("调用service添加分类");
        String save = categoryDao.save(category);
        if(save.equals("添加分类成功！")){
            System.out.println("添加分类成功！");
        }else{
            System.out.println("分类添加失败！");
        }
    }

    @Override
    public List<Category> getAllCategory(Category category) throws SQLException {
        //调用Dao层查询所有分类
       List<Category> list =  categoryDao.getAllCategory(category);
        return list;
    }

    @Override
    public void update(Category category) throws SQLException {
        //调用Dao层更新指定数据
        int update = categoryDao.updateCategory(category);
        if(update > 0){
            System.out.println("更新数据成功！");
        }else{
            System.out.println("更新数据失败！");
        }
    }

    @Override
    public void delete(Category category) throws SQLException {
        //调用DAO层删除指定数据
        int delete = categoryDao.deleteCategory(category);
        if(delete > 0){
            System.out.println("删除数据成功！");
        }else{
            System.out.println("删除数据失败!");
        }
    }


//    public static void main(String [] args){
//        Category ca = new Category();
//        ca.setParentid(3);
//        ca.setCname("Bootstrap");
//        CategoryServiceImpl ci = new CategoryServiceImpl();
//        try {
//            ci.save(ca);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
