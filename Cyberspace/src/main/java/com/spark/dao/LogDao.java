package com.spark.dao;

import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-06  11:26
 * @version： V1.0
 */
public class LogDao {
public static void main(String [] args){
    LogDao ld = new LogDao();
    try {
//        ld.operationOnRecord("Wang.zhuang@outlook.com","更新了一篇文章");
        ld.registOnRecord("王壮","管理员","Wang.zhuang@outlook.com");
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
}
public void operationOnRecord(String o_way,String o_operation) throws UnknownHostException {
    //============================================================
    //写入操作日志
    QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
    String sql2 = "INSERT INTO t_operationrecord(o_way,o_operation,o_date,o_ip) VALUES(?,?,?,?)";
    String o_operation_ = o_operation;  // 人员操作
    Date date = new Date(System.currentTimeMillis());
    String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date); // 人员操作日期
    String o_way_ = o_way; //特定操作特定描述
    InetAddress ia = null;
    ia = InetAddress.getLocalHost();
    String r_ip = ia.getHostAddress(); //人员操作IP地址
    try {
        queryRunner.update(sql2,o_way_,o_operation_,date_,r_ip);
    } catch (
            SQLException e) {
        e.printStackTrace();
    }
    //============================================================
}

public void registOnRecord(String r_name,String r_status,String r_mail) throws UnknownHostException {
    //写入注册日志
    //============================================================
    QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
    String sql2 = "INSERT INTO t_registrecord(r_name,r_status,r_mail,r_date,r_ip) VALUES(?,?,?,?,?)";
    String r_name_ = r_name; //人员姓名
    String r_status_ = r_status; //人员身份
    String r_mail_ = r_mail ; //人员邮箱
    InetAddress ia = null;
    ia = InetAddress.getLocalHost();
    String r_ip = ia.getHostAddress(); //操作IP
    Date date = new Date(System.currentTimeMillis());
    String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

    try {
        qr.update(sql2,r_name_,r_status_,r_mail_,date_,r_ip);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    //============================================================
}

}
