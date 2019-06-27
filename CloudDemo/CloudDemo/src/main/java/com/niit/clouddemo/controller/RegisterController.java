package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.pojo.front.User;
import com.niit.clouddemo.service.IRegisterService;
import com.niit.clouddemo.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 14:29
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */


@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private IRegisterService iRegisterService;

    /**
     * 👨‍💻: 定义全局变量
     * */
    static public String PHONE_AUTH_CODE;
    /**
     * TODO: 🚗到达注册界面
     * */
    @RequestMapping(value = "/toRegister.do")
    public String toRegisterPage(HttpServletRequest request, HttpServletResponse response, Model model){
        System.out.println("-----> " + PHONE_AUTH_CODE);
        return "f_register";
    }

    /**
     * TODO: 📱获取短信验证码
     * @param: phone 请求手机号
     * */
    @ResponseBody
    @RequestMapping(value = "/getAuthCode.do", method = RequestMethod.GET)
    public void getAuthCode(@RequestParam("phone") String phone){

        String tempCode = BaseUtil.getRandomAuthCode();
        PHONE_AUTH_CODE = tempCode;
        System.out.println("phoneNum: " + phone);
        System.out.println("tempCode: " + tempCode + "PHONE_AUTH_CODE: " + PHONE_AUTH_CODE);

//        // 👨‍💻: 执行验证码的发送
        try {
            BaseUtil.SMSVerification(phone,tempCode);
            System.out.println("TEST 发送成功----------------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("【手机号码】: " + phone + "【手机验证码：】" + PHONE_AUTH_CODE );
    }


    /**
     * TODO：🔥 用户注册验证
     * */
    @RequestMapping(value = "/registerValidate.do", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject regiserValidate(@RequestBody Map<String,Object> map){

        String suid = BaseUtil.getUID();
        String susername = (String) map.get("username");
        String sphone = (String) map.get("phone");
        String spassword = (String) map.get("password");
        String svercode = (String) map.get("vercode");
        System.err.println("用户UID" + suid);
        System.err.println("用户名称：" + susername);
        System.err.println("用户手机号码：" + sphone);
        System.err.println("用户密码：" + spassword);
        System.err.println("用户验证码: " + svercode);

        User user = new User();
        user.setUserid(suid);
        user.setUsername(susername);
        user.setPhone(sphone);
        user.setPassword(spassword);

        String result = iRegisterService.registerValidate(user);
        //返回异常结果JSON数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", result );
        return jsonObject;

    }



}
