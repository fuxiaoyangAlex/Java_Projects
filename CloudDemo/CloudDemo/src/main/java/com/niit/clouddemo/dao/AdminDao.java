package com.niit.clouddemo.dao;

import com.niit.clouddemo.pojo.background.Admin;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 20:55
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface AdminDao {

    /**
     *  TODO： 登录检查
     * */
    Admin adminLoginValidate(String adminphone);


    /**
     *  TODO: 根据管理员的手机号码获取管理员所有信息
     * */
    Admin getAdminInfoByPhone(String phone);
}
