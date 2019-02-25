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
@Setter
@Getter
@ToString(callSuper=true, includeFieldNames=true)
public class Comment {

    private int comment_id;
    private String comment_user;
    private String comment_article;
    private String comment_time;
    private String comment_content;
}
