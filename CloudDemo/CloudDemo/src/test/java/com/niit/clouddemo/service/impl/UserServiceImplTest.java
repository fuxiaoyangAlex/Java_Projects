package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.pojo.front.User;
import com.niit.clouddemo.service.ILoginService;
import com.niit.clouddemo.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/17 18:35
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class UserServiceImplTest {

    /**
     *TODO: Excel导出数据
     *
     * */
    @Test
    public void getInputStream() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IUserService userServiceImpl = ac.getBean("userServiceImpl", IUserService.class);
        InputStream inputStream = null;
        try {
            inputStream = userServiceImpl.getInputStream();
            System.out.println(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  获取所有用户信息
     * */
    @Test
    public void getAllUsersInfo(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IUserService userServiceImpl = ac.getBean("userServiceImpl", IUserService.class);
        List<User> allUsersInfo = userServiceImpl.getAllUsersInfo();
        System.err.println(allUsersInfo);
    }

    /**
     *  统计用户个数
     * */
    @Test
    public void countUsersNum(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IUserService userServiceImpl = ac.getBean("userServiceImpl", IUserService.class);
        int i = userServiceImpl.countUsersNum();
        System.err.println("用户的个数为：" + i);
    }

    /**
     *  TODO： 根据用户uid查询用户信息
     * */

    @Test
    public void getUserInfoById() {
       ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IUserService userServiceImpl = ac.getBean("userServiceImpl", IUserService.class);
        User userInfoById = userServiceImpl.getUserInfoById("1140836848315273216");
        System.err.println("用户头像的相对路径：" + userInfoById.getHeadimg());
        System.err.println("用户名称: " + userInfoById.getUsername() + "用户手机号码: " + userInfoById.getPhone());
    }

    /**
     *  TODO: 根据用户phone查询用户信息
     * */
    @Test
    public void getUserByPhone(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IUserService userServiceImpl = ac.getBean("userServiceImpl", IUserService.class);
        User userByPhone = userServiceImpl.getUserByPhone("18851999121");
        System.out.println(userByPhone);
    }

    /**
     *  TODO: 注册用户信息即添加新用户
     * */
    @Test
    public void saveRegisterUser() throws ParseException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IUserService userServiceImpl = ac.getBean("userServiceImpl", IUserService.class);
        // 创建用户实体
        User userEntity = new User();
        userEntity.setUserid("123456");
        userEntity.setUsername("37.2°");
        userEntity.setPhone("5200000000");
        userEntity.setPassword("wz123456789");
        java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        userEntity.setCreatetime(timeStamp);
        userEntity.setUpdatetime(timeStamp);
        userEntity.setLockedflag(0); // 0表未锁定 1表示锁定
//        用户名称
//        用户手机号
//        用户密码
//        用户UUID
//        创建时间
//        更新时间
//        用户状态

        userServiceImpl.saveRegisterUser(userEntity);

    }

    @Test
    public void getUserByPhoneAndPassword01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        ILoginService loginServiceImpl = ac.getBean("loginServiceImpl", ILoginService.class);
        String resultInfo = loginServiceImpl.getUserByPhoneAndPassword("17805129727", "wz123456");
        System.out.println(resultInfo);


    }


}