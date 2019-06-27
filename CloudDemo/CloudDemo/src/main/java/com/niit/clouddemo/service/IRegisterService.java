package com.niit.clouddemo.service;

import com.niit.clouddemo.pojo.front.User;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/17 21:07
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO: 注册相关逻辑具体实现类
 */
public interface IRegisterService  {

    /**
     *  用户注册验证
     * @param user 注册用户
     * @return
     * */
    String registerValidate(User user);


}
