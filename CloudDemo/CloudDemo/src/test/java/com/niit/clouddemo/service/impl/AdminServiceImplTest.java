package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.pojo.background.Admin;
import com.niit.clouddemo.service.IAdminService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 21:04
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class AdminServiceImplTest {

    @Test
    public void getAdminInfoByPhone(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IAdminService adminServiceImpl = applicationContext.getBean("adminServiceImpl", IAdminService.class);
        Admin adminInfoByPhone = adminServiceImpl.getAdminInfoByPhone("18851999121");
        System.out.println("管理员信息：" + adminInfoByPhone);

    }

    @Test
    public void getLoginValidateResultInfo() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IAdminService adminServiceImpl = applicationContext.getBean("adminServiceImpl", IAdminService.class);
        System.out.println(adminServiceImpl + "a");
        String fxy123456 = adminServiceImpl.getLoginValidateResultInfo("18851999121", "fxy12456");
        System.err.println(fxy123456);

    }
}