package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.dao.UserDao;
import com.niit.clouddemo.poi.WriteExcel;
import com.niit.clouddemo.pojo.front.User;
import com.niit.clouddemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/17 18:30
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    // TODO: Excel导出数据
    @Override
    public InputStream getInputStream() throws Exception {
        String [] title = new String[]{"用户编号","用户姓名","用户手机号码","用户E-mail","用户签名","用户性别","用户头像","用户注册时间","用户状态信息"};
        List<User> stuList = userDao.getAllUsersInfo();
        List<Object[]> dataList = new ArrayList<Object[]>();
        int cur;
        for(cur=0;cur<stuList.size();cur++){
            Object[] obj = new Object[9];
            obj[0] = stuList.get(cur).getUserid();
            obj[1] = stuList.get(cur).getUsername();
            obj[2] = stuList.get(cur).getPhone();
            if((stuList.get(cur).getEmail() == null) ||("".equals(stuList.get(cur).getEmail()))){
                obj[3] = "未定义";
            }else{
                obj[3] = stuList.get(cur).getEmail();
            }
            obj[4] = stuList.get(cur).getSignature();
            if((stuList.get(cur).getGender() == null) ||("".equals(stuList.get(cur).getGender()))){
                obj[5] = "未定义";
            }else{
                obj[5] = stuList.get(cur).getGender();
            }
            obj[6] = stuList.get(cur).getHeadimg();
            obj[7] = stuList.get(cur).getCreatetime();
            if(1 == stuList.get(cur).getLockedflag()){
                obj[8] = "已锁定";
            }else{
                obj[8] = "未锁定";
            }
            dataList.add(obj);
        }
        WriteExcel ex = new WriteExcel(title,dataList);
        InputStream in;
        in = ex.export();
        return in;
    }


    @Override
    public List<User> getAllUsersInfo() {
        List<User> allUsersInfo = userDao.getAllUsersInfo();
        return allUsersInfo;
    }

    @Override
    public int countUsersNum() {
        int i = userDao.countUsersNum();
        return i;
    }

    @Override
    public User getUserInfoById(String userId) {
        User userInfoById = this.userDao.getUserInfoById(userId);
        return userInfoById;
    }


    @Override
    public void userPwdValidate(String phone, String password) {

    }

    @Override
    public User getUserByPhone(String phone) {
        User userByPhone = this.userDao.getUserByPhone(phone);
        return userByPhone;
    }



    @Override
    public void saveRegisterUser(User user) {
        /**
         * 获取一个Date对象
         * */
        java.util.Date date = new java.util.Date();
        /**
         * 讲日期时间转换为数据库中的timestamp类型
         * */
        Timestamp timeStamp = new Timestamp(date.getTime());
        user.setCreatetime(timeStamp);
        user.setUpdatetime(timeStamp);
        /**
         * 表未锁定 1表示锁定
         * */
        user.setLockedflag(0);
        this.userDao.addUser(user);
    }

    @Override
    public void updateUserInfo(User user) {

    }
}
