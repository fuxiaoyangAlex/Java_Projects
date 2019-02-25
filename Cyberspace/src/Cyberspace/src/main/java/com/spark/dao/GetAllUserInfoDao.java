package com.spark.dao;

import com.spark.domain.User;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-01  14:55
 * @version： V1.0
 */
public class GetAllUserInfoDao {

    public static User getAllUser(String umail){
        //连接
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        //创建sql语句
        String sql = "SELECT * FROM t_user WHERE umail = ?";
        User user = null;
        //执行查询
        try {
            user = qr.query(sql,new BeanHandler<User>(User.class),umail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回查询结果
        return user;
    }
}
