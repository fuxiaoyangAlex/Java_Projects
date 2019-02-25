package com.spark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.domain
 * @Description: TODO
 * @date Date : 2018-12-06  11:30
 * @version： V1.0
 */
@Setter
@Getter
@ToString(callSuper = true,includeFieldNames = true)
public class T_smessage {
    private int smessage_id; //主键
    private String smessage_username;
    private String smessage_userpic;
    private String smessage_usercontent;
    private String smessage_userdate;
    private int id; //外键
}
