package com.spark.service;

import com.spark.dao.CheckDao;
import com.spark.dao.GetAllUserInfoDao;
import com.spark.domain.User;

import java.sql.SQLException;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/29
 */
public class UserService {

    public static String uname;

    public User login(String umail, String upassword) throws Exception {
        //调用dao中的checkData处理用户是否存在的判断
        CheckDao cd = new CheckDao();
        User user = cd.checkUser(umail, upassword);
        if (user != null) {
            return user;
        } else {
            throw new Exception("邮箱或密码错误！");
        }
    }

    public User login2(String umail) {
        User user = GetAllUserInfoDao.getAllUser(umail);
        return user;
    }
}
