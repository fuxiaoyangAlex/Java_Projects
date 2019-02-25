package com.spark.service;

import com.spark.dao.MessageDao;
import com.spark.domain.T_lmessage;
import com.spark.domain.T_smessage;

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

    public String MessageService2(String smessage_username, String smessage_userpic, String smessage_usercontent, String smessage_userdate,int id) throws SQLException {
        MessageDao md = new MessageDao();
        int flag = md.insertSmessage(smessage_username,smessage_userpic,smessage_usercontent,smessage_userdate,id);
        if(flag > 0){
            return "回复成功！";
        }else{
            return "回复失败！";
        }
    }

    public List<T_smessage> getAllSMessage() throws SQLException {
        MessageDao md = new MessageDao();
        List<T_smessage> allSmessage = md.findAllSmessage();
        if(allSmessage != null){
            System.out.println("查询成功！");
            return allSmessage;
        }else{
            System.out.println("查询失败！");
            return null;
        }
    }
}
