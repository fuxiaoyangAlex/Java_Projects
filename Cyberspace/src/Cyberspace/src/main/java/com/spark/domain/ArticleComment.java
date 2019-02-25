package com.spark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.domain
 * @Description: TODO
 * @date Date : 2018-12-11  9:26
 * @version： V1.0
 */
@Setter
@Getter
@ToString(callSuper=true, includeFieldNames=true)
public class ArticleComment {

    private int id;                       //文章评论的id
    private int article_id;              //此条评论所属的文章id
    private String ac_user;             //评论用户的名称
    private String ac_img;             //评论用户的头像
    private String ac_content;        //用户评论的内容
    private String ac_date;          //用户评论的日期
}
