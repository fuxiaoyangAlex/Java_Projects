package com.spark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.domain
 * @Description: TODO
 * @date Date : 2018-12-06  11:27
 * @version： V1.0
 */

@Setter@Getter
@ToString(callSuper = true,includeFieldNames = true)
public class T_lmessage {
    private int id; //主键被引用为外键
    private String lmessage_user;
    private String lmessage_pic;
    private String lmessage_date;
    private String lmessage_content;
    private String uuid; //外键
}
