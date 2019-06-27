package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.dao.UserDao;
import com.niit.clouddemo.pojo.front.User;
import com.niit.clouddemo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/18 13:56
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Service(value = "loginServiceImpl")
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public String getUserByPhoneAndPassword(String phone, String password) {
        User userByPhone = userDao.getUserByPhone(phone);
        if (userByPhone == null) {
            return "此用户不存在，请注册后登录。";
        }else if(!userByPhone.getPassword().equals(password)){
            return "输入密码错误！";
        }else{
            return "登录成功";
        }
    }
}
