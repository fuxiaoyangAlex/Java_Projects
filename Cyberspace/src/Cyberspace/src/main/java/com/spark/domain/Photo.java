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
public class Photo {
    private int photo_id;
    private String photo_user;
    private String photo_name;
    private String photo_time;
}
