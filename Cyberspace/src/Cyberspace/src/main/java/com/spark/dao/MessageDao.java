package com.spark.dao;

import com.spark.domain.T_lmessage;
import com.spark.domain.T_smessage;
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
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

    //------------------------------------------留言部分-----------------------------------------------------------------
    public int insertLmessage(String lmessage_user, String lmessage_pic, String lmessage_date, String lmessage_content,String uuid) throws SQLException {

        String sql = "INSERT INTO t_lmessage(lmessage_user,lmessage_pic,lmessage_date,lmessage_content,uuid) VALUES(?,?,?,?,?)";
        Object[] params = new Object[]{lmessage_user,lmessage_pic,lmessage_date,lmessage_content,uuid};
        int flag = qr.update(sql,params);
        return flag;
    }

    public  List<T_lmessage> findAllLMessage(String uuid) throws SQLException {

        //创建sql语句
        String sql = "SELECT * FROM t_lmessage WHERE uuid = ?";
        List<T_lmessage> lList = qr.query(sql, new BeanListHandler<T_lmessage>(T_lmessage.class),uuid);
        return lList;
    }

    //------------------------------------------回复部分-----------------------------------------------------------------
    public int insertSmessage(String smessage_username, String smessage_userpic, String smessage_usercontent, String smessage_userdate,int id) throws SQLException {
        //创建SQL语句
        String sql = "INSERT INTO t_smessage(smessage_username,smessage_userpic,smessage_usercontent,smessage_userdate,id) VALUES(?,?,?,?,?)";
        Object[] params = new Object[]{smessage_username,smessage_userpic,smessage_usercontent,smessage_userdate,id};
        int flag = qr.update(sql,params);
        return flag;
    }

    public List<T_smessage> findAllSmessage() throws SQLException {
        //创建SQL语句
        String sql = "SELECT * FROM t_smessage";
        List<T_smessage> sList = qr.query(sql,new BeanListHandler<T_smessage>(T_smessage.class));
        return sList;
    }

//=====================================================测试==================================================================
    public static void main(String [] args) throws SQLException {
        MessageDao md = new MessageDao();

        List<T_smessage> sList = md.findAllSmessage();
        for(T_smessage t : sList){
            System.out.println("----> " + t);
        }

//        int flag = md.insertSmessage("Hiro","imgages/uimg/pic02.png/","HelloWorld","2018-12-07 12:00:00");
//        if (flag > 0){
//            System.out.println("回复成功.............");
//        }else{
//            System.out.println("回复失败！");
//        }

//        try {
//            List<T_lmessage> allLMessage = md.findAllLMessage("b73b54a2-f42d-44da-a1d0-21bbbc59ea5b");
//            for(T_lmessage ls : allLMessage){
//                System.out.println("---> " + ls);
//                System.out.println("lmessage_user: " + ls.getLmessage_user());
//                System.out.println("lmessage_pic: " + ls.getLmessage_pic());
//                System.out.println("lmessage_date: " + ls.getLmessage_date());
//                System.out.println("===============Content===============");
//                System.out.println("lmessage_content: " + ls.getLmessage_content());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }


}
