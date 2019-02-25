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
public class Journal {

    private int j_id;
    private String j_title;
    private String j_content;
    private String j_date;
    private int j_readcount;
    private int j_likecount;
    private String uuid;

}
