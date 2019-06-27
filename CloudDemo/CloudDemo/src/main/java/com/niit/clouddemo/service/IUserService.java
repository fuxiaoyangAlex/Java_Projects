package com.niit.clouddemo.service;

import com.niit.clouddemo.pojo.front.User;

import java.io.InputStream;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/17 15:07
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface IUserService {

    /**
     *  TODO: 获取所有用户信息
     * */
    List<User> getAllUsersInfo();

    /**
     *  TODO: 导出用户信息
     * */
    InputStream getInputStream() throws Exception;

    /**
     *  TODO: 通过用户个数
     * */
    int countUsersNum();

    /**
     *  TODO: 🔼根据用户的UID查询用户所有信息
     * @Param uid
     * */
    User getUserInfoById(String uid);

    /**
     * TODO: 手机号、密码验证
     * */
    void userPwdValidate(String phone,String password);


    /**
     * TODO：根据手机号获取用户信息对象
     *
     * @param phone 手机号
     * @return 用户信息对象
     * */
    User getUserByPhone(String phone);

    /**
     * TODO: 👨‍💻保存注册用户信息
     * @param user 保存用户信息对象
     * */
    void saveRegisterUser(User user);

    /**
     * TODO: 更新用户信息
     * */
    void updateUserInfo(User user);


}
