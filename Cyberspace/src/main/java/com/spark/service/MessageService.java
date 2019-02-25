package com.spark.service;

import com.spark.dao.MessageDao;
import com.spark.domain.T_lmessage;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-06  11:00
 * @version： V1.0
 */
public class MessageService {

    public String MessageService(String lmessage_user, String lmessage_pic, String lmessage_date, String lmessage_content,String uuid) throws SQLException {
        MessageDao md = new MessageDao();
       int flag =  md.insertLmessage(lmessage_user, lmessage_pic,lmessage_date,lmessage_content,uuid);
       if(flag > 0){
           return "留言成功！";
       }else{
           return "留言失败！";
       }
    }


    public List<T_lmessage> getAllLMessage(String uuid) throws SQLException {
        MessageDao md = new MessageDao();
        List<T_lmessage> allLMessage = md.findAllLMessage(uuid);
        if(allLMessage != null){
            System.out.println( "查询成功！");
        }else{
            System.out.println("查询失败！");
        }
        return allLMessage;
    }

}
