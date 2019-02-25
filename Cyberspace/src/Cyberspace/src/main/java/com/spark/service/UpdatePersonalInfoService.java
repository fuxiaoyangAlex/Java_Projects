package com.spark.service;

import com.spark.dao.UpdatePersonalInfoDao;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-01  0:31
 * @version： V1.0
 */
public class UpdatePersonalInfoService {
    public String updatePersonalInfo(String uimg, String urealname, String ugender, String ubio, String umail) throws Exception {
        UpdatePersonalInfoDao upid = new UpdatePersonalInfoDao();
        int result = upid.updatePersonaInfo(uimg,urealname,ugender,ubio,umail);
        String flag;
        if(result > 0){
            flag = "数据保存成功！";
            return flag;
        }else{
            flag = "数据保存失败！";
            return flag;
        }
    }
}
