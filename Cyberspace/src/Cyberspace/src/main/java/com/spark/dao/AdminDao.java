package com.spark.dao;

import com.spark.domain.Admin;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-04  16:30
 * @version： V1.0
 */
public class AdminDao {
    public void registAdmin(String admin_name,String admin_realname,String admin_password,String admin_gender,String admin_age,String admin_phone,String admin_mail,String admin_qq) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource()); //加载数据源
        Date date = new Date(System.currentTimeMillis());
        String admin_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        String sql = "INSERT INTO t_admin(admin_name,admin_realname, admin_password, admin_gender, admin_age, admin_phone, admin_mail, admin_qq,admin_date) VALUES(?,?,?,?,?,?,?,?,?)";
        qr.update(sql,admin_name,admin_realname,admin_password, admin_gender, admin_age, admin_phone, admin_mail, admin_qq,admin_date);
    }

    public static List<Map<String, Object>> findAllAdmin() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource()); //加载数据源
        String sql = "SELECT * FROM t_admin";

        List<Map<String,Object>> list = qr.query(sql, new MapListHandler());

        return list;
    }

    public static List<Admin> findAllAdmin2() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "SELECT * FROM t_admin";
        //调用方法传递结果集的实现类BeanListHander
        //BenaListHandler(Class<T> type)
        List<Admin> list = qr.query(sql, new BeanListHandler<Admin>(Admin.class));
//        for(Admin ad : list){
//            System.out.println("____________" + ad);
//        }
        return list;
    }

    public int deleteAdmin(String admin_realname) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "DELETE FROM t_admin WHERE admin_realname = ?";
        qr.update(sql,admin_realname);
        return 0;
    }

//===================================**测试**===========================================================================//
//    public static void main(String [] args){
//        try {
//            List<Map<String,Object>> list = AdminDao.findAllAdmin();
//            for(Map<String,Object> map : list){
//                System.out.println("--> map: " + map );
//                for(String key : map.keySet()){
//                    System.out.println("--- : " + key + map.get(key) );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            List<Admin> list = AdminDao.findAllAdmin2();
//            for(Admin ad : list){
//                System.out.println("----------> " + ad);
//                System.out.println("------------> " + ad.getAdmin_name());
//                System.out.println("------------> " + ad.getAdmin_age());
//                System.out.println("------------> " + ad.getAdmin_date());
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
