package com.niit.clouddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/18 22:46
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@Controller
public class PublicController {

    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        System.out.println("-----------> 退出");
        return "redirect:login/toLogin.do";
    }

}
