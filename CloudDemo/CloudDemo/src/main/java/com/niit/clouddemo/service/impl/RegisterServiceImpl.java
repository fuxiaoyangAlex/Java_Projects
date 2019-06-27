package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.pojo.front.User;
import com.niit.clouddemo.service.IRegisterService;
import com.niit.clouddemo.service.IUserService;
import com.niit.clouddemo.service.IVercodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/17 21:39
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Service("registerServiceImpl")
public class RegisterServiceImpl implements IRegisterService {

    @Resource(name="vercodeServiceImpl")
    private IVercodeService vercodeService;

    @Resource(name="userServiceImpl")
    private IUserService userService;

    /**
     *  注册验证
     * @param user 注册用户
     * @return*/
    @Override
    public String registerValidate(User user) {
         /**
          *  前端已经完成相关验证
          * */
//        //验证用户名格式
//        if(!BaseUtil.validateUserName(user.getUsername())){
//            return "用户名格式不正确";
//        }
//
//        //验证密码格式
//        if(!BaseUtil.validatePassword(user.getPassword())){
//            return "密码格式不正确";
//        }
//
//        //验证手机号格式
//        if(!BaseUtil.isPhone(user.getPhone())){
//            return "手机号格式不正确";
//        }

        //验证手机号是否已注册过
        if(userService.getUserByPhone(user.getPhone()) != null){
            return "手机号已经注册过!";
        }


        try {

            //保存注册用户信息
            userService.saveRegisterUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return "注册失败!";
        }

        return "注册用户成功!";
    }

    }

