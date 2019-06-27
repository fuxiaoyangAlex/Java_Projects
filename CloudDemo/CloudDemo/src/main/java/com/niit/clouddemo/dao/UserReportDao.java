package com.niit.clouddemo.dao;

import com.niit.clouddemo.pojo.front.UserReport;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/27 0:25
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface UserReportDao {

    /**
     *  提交举报
     * */
    int reportUser();

    /**
     *  获取所有举报
     * */
    List<UserReport> getAllReportInfo();

}
