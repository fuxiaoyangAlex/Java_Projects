package com.niit.clouddemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.pojo.front.User;
import com.niit.clouddemo.service.IVercodeService;
import org.springframework.stereotype.Service;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 15:03
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO: 验证处理逻辑
 */

@Service("vercodeServiceImpl")
public class VercodeServiceImpl implements IVercodeService {
    @Override
    public void setPhoneVercode(String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone",phone);
    }

    @Override
    public boolean validateVercode(User user) {
        return false;
    }
}
