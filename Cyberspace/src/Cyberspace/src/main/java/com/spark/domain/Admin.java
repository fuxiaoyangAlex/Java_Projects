package com.spark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/27
 */
@Setter@Getter
@ToString(callSuper=true, includeFieldNames=true)
public class Admin {
    private int admin_id;                //管理员ID
    private String admin_password;      //管理员的密码
    private String admin_name;         //管理员的用户名称
    private String admin_realname;    //管理员真实姓名
    private String admin_phone;      //管理员的电话
    private String admin_mail;      //管理员的邮箱
    private String admin_age;      //管理员年龄
    private String admin_qq;      //管理员qq
    private String admin_gender; //管理员性别
    private String admin_date; //注册日期

public static void main(String [] args){
    System.out.println("士大夫");
}
}
