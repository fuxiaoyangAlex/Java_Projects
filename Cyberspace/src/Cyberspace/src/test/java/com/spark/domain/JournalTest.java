package com.spark.domain;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
public class JournalTest {

    @Test
    public void JournalTest(){
        Journal jo = new Journal();
        jo.setJ_content("今天天气不错！");
        Date date = new Date();
        String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date.getTime());
        jo.setJ_date(date_);
        jo.setJ_likecount(2);
        jo.setJ_readcount(23);

        System.out.println("日志信息： " + jo);
    }

}