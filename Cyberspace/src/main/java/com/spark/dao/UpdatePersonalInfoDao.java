package com.spark.dao;

import com.spark.domain.User;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 *  * 	对用户资料进行更新操作：
 *  * 	更新数据：
 *  * 	-> 用户头像（uimg 将图像放入服务器中而将图片所在服务器的地址存放在数据库中）：
 *  * 	-> 用户真实姓名（urealname 更新用户真实姓名）
 *  * 	-> 用户性别 （ugender 更新用户性别）
 *  * 	-> 个性签名 （ubio 更新用户个性签名)
 * @date Date : 2018-12-01  0:13
 * @version： V1.0
 */
public class UpdatePersonalInfoDao {

    public static int updatePersonaInfo(String uimg,String urealname,String ugender,String ubio, String umail) throws SQLException {
        //1.连接
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        //2.创建SQL语句
        String sql = "UPDATE t_user SET uimg = ? , urealname = ? , ugender = ? , ubio = ? WHERE umail = ?";
        Object[] params = {uimg,urealname,ugender,ubio,umail};
        //3.执行sql语句
        int result = qr.update(sql,params);
        //4.返回查询的结果
        return result;
    }
}
