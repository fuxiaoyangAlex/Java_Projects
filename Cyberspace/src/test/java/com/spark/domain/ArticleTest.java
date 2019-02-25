package com.spark.domain;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
public class ArticleTest {

    @Test
    public void ArticleTest(){
        Article at = new Article();
        at.setArticle_authro("Erick");
        at.setArticle_id("01");
        at.setArticle_title("We are the world!");
        at.setContent("================================First Content===============================");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("--" + date);
        String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:s.SSS").format(date);
        System.out.println("------> 时间是：" + date_);
        at.setUpdate_time(date_);
        System.out.println("***** " + at);

    }

}