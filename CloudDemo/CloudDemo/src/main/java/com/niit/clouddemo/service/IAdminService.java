package com.niit.clouddemo.service;

import com.niit.clouddemo.pojo.background.Admin;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 20:58
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface IAdminService {

    /**
     *  TODO：利用手机号检索管理员信息
     * */
    Admin getAdminInfoByPhone(String phone);

    /**
     *  TODO: 登录验证
     * */
    String getLoginValidateResultInfo(String adminphone,String adminpassword);

}
