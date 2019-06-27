package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.service.IAdminService;
import com.niit.clouddemo.service.ILoginService;
import com.niit.clouddemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 18:21
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    @Autowired
    private IUserService iUserService;


    /**
     *  TODO: 测试界面
     * */
    @RequestMapping(value = "/getTestPage.action")
    public String getTestPage(){
        return "redirect:/html/adminarticleadd.html";
    }


    /**
     *  TODO: 🚗获取登录页面
     * */
    @RequestMapping(value = "/getLoginPage.do")
    public String getLoginPage(){
        return "redirect:toLogin.do";
    }
    /**
     * TODO: 🚗到达登录页面
     * */
    @RequestMapping(value = "/toLogin.do")
    public String toLoginPage(){
        return "f_login";
    }

    /**
     *  TODO: 🔗跳转到系统首页
     * */
    @RequestMapping(value = "/index.do")
    public String index(){
        System.out.println("---------> index ");
        return "redirect:/index.jsp";

    }

    /**
     *  TODO: 获取用户登录验证结果
     * */
    @RequestMapping(value = "/loginValidate.do", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject loginValidate(@RequestBody Map<String,Object> map){
        String password = (String) map.get("password");
        String phone = (String) map.get("phone");
        String result = iLoginService.getUserByPhoneAndPassword(phone,password);
        if ("登录成功".equals(result)) {
            HttpSession session = getSession();
            session.setAttribute("loginUserInfo",iUserService.getUserByPhone(phone));
        }
        System.out.println(result);
        //返回JSON
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", result );

        return jsonObject;
    }


    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {}
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
}
