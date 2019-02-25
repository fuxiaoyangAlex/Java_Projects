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
    private String article_id;
    private String article_authro;
    private String article_title;
    private String content;
    private String update_time;
    private int read_count;
    private int praise_count;

}
