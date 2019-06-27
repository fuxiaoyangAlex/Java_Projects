package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.pojo.front.User;
import com.niit.clouddemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/23 17:37
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@Controller
@Transactional
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /**
     *  TODO： 获取当前系统用户的个数
     * */
    @RequestMapping(value = "/getCountUsersNum.do")
    @ResponseBody
    public JSONObject getCountUsersNum(){
        int i = iUserService.countUsersNum();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cnum", i);
        return jsonObject;
    }

    /**
     *  TODO: 获取前端传来的userId获取用户信息传至用户信息展示界面
     *
     */
    @RequestMapping(value = "/getUserInfo.do", method = RequestMethod.GET)
    public String getUserInfo() throws UnsupportedEncodingException {
        HttpServletRequest request = getRequest();
        request.setCharacterEncoding("UTF-8");
        String userid = (String)request.getAttribute("userid");
        System.out.println("UserId; ----------> " + userid);
        User userInfoById = iUserService.getUserInfoById(userid);
        HttpSession session = getSession();
        session.setAttribute("userInfo",userInfoById);
        return "redirect:/toUserInfo.do";
    }


    /**
     *  TODO: 🔗跳转到用户信息详情
     * */
    @RequestMapping(value = "/toUserInfo.do")
    public String index(){
        System.out.println("---------> index ");
        return "redirect:/jsp/user_info_detail.jsp";

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
