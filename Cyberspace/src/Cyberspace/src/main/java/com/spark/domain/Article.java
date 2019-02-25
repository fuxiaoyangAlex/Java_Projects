package com.spark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
@Setter@Getter
@ToString(callSuper=true, includeFieldNames=true)
public class Article {
    private int article_id;             //文章编号
    private String article_author;     //作者
    private String article_title;     //文章标题
    private String article_content;  //文章内容
    private String article_time;    //发布文章的时间
    private String article_pic;    //文章的缩略图
    private String article_desc;  //文章的简介
    private String article_sort; //文章所属分类
    private int is_public;      //1代表公开2代表不公开
    private int praise_count;  //点赞次数
    private int read_count;   //阅读次数
}
