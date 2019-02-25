package com.spark.domain;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
public class UserTest {

    @Test
    public void UserTest(){
        User user = new User();
        user.setUgender("male");
        Date date = new Date(System.currentTimeMillis());
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        user.setUdate(format); //birthday
        user.setUmail("Wang.zhuang@outlook.com");
        user.setUpassword("mysqladmin");
        user.setUbio("Greate is time to mill！");
        user.setUrealname("Wz");
        user.setUimg("First.png");
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        user.setUuid(uuid);
        user.setUname("Sunshineisbright");
        System.out.println("用户个人信息：" +
                user);
    }



}