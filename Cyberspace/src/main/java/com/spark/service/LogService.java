package com.spark.service;

import com.spark.dao.LogDao;

import java.net.UnknownHostException;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-06  12:23
 * @version： V1.0
 */
public class LogService {
    public void operationOnRecord(String o_way,String o_operation) throws UnknownHostException {
        LogDao ld = new LogDao();
        ld.operationOnRecord(o_way,o_operation);
    }

    public void registOnRecord(String r_name,String r_status,String r_mail) throws UnknownHostException {
        LogDao ld = new LogDao();
        ld.registOnRecord(r_name,r_status,r_mail);
    }
}
