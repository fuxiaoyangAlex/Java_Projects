package com.spark.dao;

import com.spark.domain.T_lmessage;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-06  11:05
 * @version： V1.0
 */
public class MessageDao {


    public int insertLmessage(String lmessage_user, String lmessage_pic, String lmessage_date, String lmessage_content,String uuid) throws SQLException {

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "INSERT INTO t_lmessage(lmessage_user,lmessage_pic,lmessage_date,lmessage_content,uuid) VALUES(?,?,?,?,?)";
        Object[] params = new Object[]{lmessage_user,lmessage_pic,lmessage_date,lmessage_content,uuid};
        int flag = qr.update(sql,params);
        return flag;
    }

    public  List<T_lmessage> findAllLMessage(String uuid) throws SQLException {
        //加载数据源
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        //创建sql语句
        String sql = "SELECT * FROM t_lmessage WHERE uuid = ?";
        List<T_lmessage> lList = qr.query(sql, new BeanListHandler<T_lmessage>(T_lmessage.class),uuid);
        return lList;
    }

//=====================================================测试==================================================================
    public static void main(String [] args){
        MessageDao md = new MessageDao();
        try {
            List<T_lmessage> allLMessage = md.findAllLMessage("b73b54a2-f42d-44da-a1d0-21bbbc59ea5b");
            for(T_lmessage ls : allLMessage){
                System.out.println("---> " + ls);
                System.out.println("lmessage_user: " + ls.getLmessage_user());
                System.out.println("lmessage_pic: " + ls.getLmessage_pic());
                System.out.println("lmessage_date: " + ls.getLmessage_date());
                System.out.println("===============Content===============");
                System.out.println("lmessage_content: " + ls.getLmessage_content());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
