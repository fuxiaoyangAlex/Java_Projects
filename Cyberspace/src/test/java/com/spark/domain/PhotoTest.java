package com.spark.domain;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
public class PhotoTest {

    @Test
    public void PhotoTest(){
        Photo pt = new Photo();
        pt.setPhoto_id(01);
        pt.setPhoto_name("We ");
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        pt.setPhoto_time(format);
        pt.setPhoto_user("we are the world!");
        System.out.println(".........." + pt);
    }

}