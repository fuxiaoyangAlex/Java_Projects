package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.dao.AdminDao;
import com.niit.clouddemo.pojo.background.Admin;
import com.niit.clouddemo.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 20:59
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdminInfoByPhone(String phone) {
        Admin adminInfoByPhone = adminDao.getAdminInfoByPhone(phone);
        return adminInfoByPhone;
    }

    @Override
    public String getLoginValidateResultInfo(String adminphone,String adminpassword) {
        Admin admin = adminDao.adminLoginValidate(adminphone);
        if (admin == null){
            return "账号不存在,请重新输入!";
        }else if(!(adminpassword.equals(admin.getPassword()))){
            return "输入密码错误,请重新输入!";
        }else{
            return "登录成功!";
        }

    }
}
