package com.spark.domain;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.domain
 * @Description: TODO
 * @date Date : 2018-12-08  12:55
 * @version： V1.0
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true, includeFieldNames = true)
public class Category {
    private int cid;
    private String cname;
    private String cauthor;


}
