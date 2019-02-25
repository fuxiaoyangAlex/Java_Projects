package com.spark.domain;

import org.junit.Test;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.xml.crypto.dsig.SignatureMethod;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
public class MessageTest {

    @Test
    public void MessageTest(){
        Message ms = new Message();
        ms.setMessage_content("we ar et");
        ms.setMessage_id(01);
        Date date = new Date(System.currentTimeMillis());
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        ms.setMessage_time(format);
        ms.setMessage_user("we are ");
        System.out.println("--> " + ms);
    }

}