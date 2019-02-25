package com.spark.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
public class CommentTest {

    @Test
    public void CommentTest(){
        Comment ct = new Comment();
        ct.setComment_article("这个不错哦！(垃圾🐕");
        ct.setComment_id(01);
        ct.setComment_content("We are the world");
        ct.setComment_user("Erick");
        ct.setComment_article("001");
        System.out.println("评论信息：" + ct);
    }

}