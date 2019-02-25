package com.spark.service;

import com.spark.dao.RegistRecordDao;
import com.spark.domain.PageBean;
import com.spark.domain.RegistRecord;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-04  9:26
 * @version： V1.0
 */
public class JdbcSqlRegistRecordServiceImpl implements RegistRecordService{
    private RegistRecordDao registRecordDao;
    public JdbcSqlRegistRecordServiceImpl(){
        registRecordDao = new RegistRecordDao();
    }
    @Override
    public PageBean<RegistRecord> findStudent(RegistRecord searchModel, int pageNum, int pageSize) {
        PageBean<RegistRecord> result = registRecordDao.findStudent(searchModel, pageNum, pageSize);
        return result;
    }
}
