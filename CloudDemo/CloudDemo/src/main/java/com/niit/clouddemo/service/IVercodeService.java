package com.niit.clouddemo.service;

import com.niit.clouddemo.pojo.front.User;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 14:59
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface IVercodeService {

    /**
     * 发送手机验证码
     * @param phone 手机号
     * */
    void setPhoneVercode(String phone);

    /**
     *  手机验证码验证
     *  验证提交的验证码和发送的验证码是否一致
     * @param user 注册用户
     * @param {boolean} 验证通过: true 不通过false
     * */
    boolean validateVercode(User user);


}
