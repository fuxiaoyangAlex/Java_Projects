package com.spark.dao;

import com.spark.domain.T_ochart;
import com.spark.domain.T_rchart;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.management.QueryEval;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-06  11:26
 * @version： V1.0
 */
public class LogDao {

    private QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());


//=================================================================================================================
    public void operationOnRecord(String o_way,String o_operation) throws UnknownHostException {
    //============================================================
    //写入操作日志
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
}

public void registOnRecord(String r_name,String r_status,String r_mail) throws UnknownHostException {
    //写入注册日志
    //============================================================
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
        queryRunner.update(sql2,r_name_,r_status_,r_mail_,date_,r_ip);
    } catch (SQLException e) {
        e.printStackTrace();
    }

}
//=====================================================================================================================


//======================================================================================================================
    //echarts中的站点访问量
    public int updateRchart(String sql) throws SQLException {
        int updateR = queryRunner.update(sql);
        return updateR;
    }
//======================================================================================================================
    //echarts中的用户活跃度
    public int updateOchart(String sql) throws SQLException {
        int updateO = queryRunner.update(sql);
        return updateO;
    }

//======================================================================================================================
    //下面的两个方法用于获取数据库中echarts所需要的数据
    /**
     *操作视图
     */
    public List<T_ochart> getAllOchart() throws SQLException {
        String sql = "SELECT * FROM t_ochart";
        List<T_ochart> query = queryRunner.query(sql, new BeanListHandler<T_ochart>(T_ochart.class));
        return query;
    }

    /***
     * 注册视图
     * */
    public List<T_rchart> getAllRchart() throws SQLException {
        String sql = "SELECT * FROM t_rchart";
        List<T_rchart> query = queryRunner.query(sql, new BeanListHandler<T_rchart>(T_rchart.class));
        return query;
    }
}
