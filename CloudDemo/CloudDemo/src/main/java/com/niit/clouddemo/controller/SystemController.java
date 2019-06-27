package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.service.IAdminService;
import com.niit.clouddemo.service.IUserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 21:41
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Controller
@RequestMapping("/sys")
public class SystemController {

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IUserService iUserService;

    /**
     *  TODO： 导出所有用户信息
     * */



    /**
     *  TODO： Excel导出所有用户信息
     *
    */

    @RequestMapping("/exportUsers")
    public void export(HttpServletResponse response) throws Exception{
        InputStream is=iUserService.getInputStream();
        //excel文件名
        String fileName = "CloudDemo系统用户信息表"+System.currentTimeMillis()+".xls";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename="+fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is,output);
    }

    /**
     *  TODO: 到达登录页面
     * */
    @RequestMapping(value = "/toSysLoginPage.action")
    public String toSysLoginPage(){
        return "redirect:/html/syslogin.html";
    }

    /**
     *  TODO: 到达后台管理首页页面
     * */
    @RequestMapping(value = "/toSysIndexPage.action")
    public String toSysIndexPage(){
        return "redirect:/html/index.html";
    }

    /**
     *  TODO: 管理员登录验证
     * */
    @RequestMapping(value = "/adminLoginValidate.action")
    public @ResponseBody
    JSONObject toAdminLoginValidate(@RequestBody Map<String,Object> map){
        String adminphone = (String)map.get("adminphone");
        String adminpassword = (String)map.get("adminpassword");
        System.out.println("adminphone: " + adminphone + "adminpassword: " + adminpassword);
        String loginValidateResultInfo = iAdminService.getLoginValidateResultInfo(adminphone, adminpassword);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", loginValidateResultInfo);
        jsonObject.put("phone", adminphone);

        return jsonObject;
    }


    /**
     *
     * */
}
