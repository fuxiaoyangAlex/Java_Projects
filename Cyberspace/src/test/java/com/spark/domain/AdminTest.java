package com.spark.domain;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
public class AdminTest {

    @Test
    public void AdminTest(){
//        String uuid = UUID.randomUUID().toString();
//        uuid = uuid.replace("-","");
        Admin ad = new Admin();
        ad.setAdmin_id(1);
        ad.setAdmin_mail("Wang.zhuang@outlook.com");
        ad.setAdmin_name("Admin01");
        ad.setAdmin_password("cyberspaceadmin");
        ad.setAdmin_phone("173652323");
        System.out.println("Admin Info: " + ad);
    }


}