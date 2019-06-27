package com.niit.clouddemo.service;

import org.apache.ibatis.annotations.Param;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/18 13:54
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface ILoginService {

    /**
     *  TODO: 🔼 根据用户输入的手机账号和密码进行登录
     * @param phone : 用户手机号码
     * @password : 用户密码
     * */
    String getUserByPhoneAndPassword(@Param(value = "phone") String phone, @Param(value = "password") String password);

}
