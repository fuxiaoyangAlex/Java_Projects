package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.pojo.background.Admin;
import com.niit.clouddemo.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 21:30
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService iAdminService;

    /**
     *  TODO： 获取管理员信息通过手机号
     * */
    @RequestMapping(value = "/getAdminInfoByPhone.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getAdminInfoByPhone(@RequestBody Map<String,Object> map){
        String phone = (String)map.get("adminphone");
        System.out.println("管理员手机号码：" + phone);
        Admin adminInfoByPhone = iAdminService.getAdminInfoByPhone(phone);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("admininfo",adminInfoByPhone);
        return jsonObject;
    }
}
