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
public class Message {

    private int message_id;
    private String message_user;
    private String message_content;
    private String message_time;

}
