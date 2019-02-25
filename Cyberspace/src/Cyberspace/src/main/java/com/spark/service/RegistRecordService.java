package com.spark.service;

import com.spark.domain.Pager;
import com.spark.domain.RegistRecord;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-04  9:24
 * @version： V1.0
 */
public interface RegistRecordService {
    /**
     * 根据查询条件，查询学生分页信息
     *
     * @param searchModel 封装查询条件
     * @param pageNum     查询第几页数据
     * @param pageSize    每页显示多少条记录
     * @return 查询结果
     */
    public Pager<RegistRecord> findStudent(RegistRecord searchModel, int pageNum, int pageSize);
}
