package com.niit.clouddemo.dao;

import com.niit.clouddemo.pojo.front.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/17 14:52
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Repository
public interface UserDao {

    /**
     * 获取所有用户信息
     * */
    List<User> getAllUsersInfo();

    /****
     * 统计用户的人数
     */
    int countUsersNum();
    /**
     * 根据用户ID获取用户信息
     *
     * @param 	id	用户主键
     * @return		指定ID对应用户对象
     */
    User getUserInfoById(String id);

    /**
     * 添加用户
     *
     * @param user	要添加的用户对象
     */
    void addUser(User user);

    /**
     * 根据用户手机号获取对应的账号信息
     *
     * @param 	phone
     * @return
     */
    User getUserByPhone(String phone);



}
