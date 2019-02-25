package com.spark.dao;

import com.spark.domain.Admin;
import com.spark.domain.User;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Author:Wangzhuang2
 * @Description:
 * /到数据库当中查询有没有所登录的用户或所登陆的管理员账号
 * @Date:2018/11/29
 */
public class CheckDao {

    /**
     * Functional description:
     * 对数据库中的用户进行查询，如果已经有了某一个用户的存在则回显为“已存在此用户”
     * @param: umail upassword
     * @return:User
     * @auther: wangzhuang2
     * @date: 2018/12/02 17:36
     */
    public User checkUser(String umail,String upassword) throws SQLException {
        //1.连接
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        //2.创建SQL语句
        String sql = "select * from t_user where umail=? and upassword=?";
        User user = null;
        //3.执行查询
        user = qr.query(sql, new BeanHandler<User>(User.class),umail,upassword);
        //4.返回查询的结果
        return user;
    }

    /**
     * Functional description:
     * 到数据库中根据admin_name查询管理员若此管理员名称存在则回显此管理员已经存在
     * @param: admin_name admin_password
     * @return: Admin
     * @auther: wangzhuang2
     * @date: 2018/12/02 17:36
     */
    public Admin checkAdmin(String admin_name,String admin_password) throws SQLException {

        //1.连接
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        //2.查询 创建SQL语句
        String sql = "SELECT * FROM t_admin WHERE admin_name = ? and admin_password = ?";
        //3.执行查询
        Admin admin = null;
        admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),admin_name,admin_password);
        return admin;
    }

}
