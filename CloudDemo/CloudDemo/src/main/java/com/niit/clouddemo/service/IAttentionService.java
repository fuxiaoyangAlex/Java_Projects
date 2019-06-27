package com.niit.clouddemo.service;

import com.niit.clouddemo.pojo.front.UserAttentionQuestion;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 10:22
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface IAttentionService {

    /**
     *  TODO: 用户关注问题
     * */
    String addAttentionQuestion(UserAttentionQuestion userAttentionQuestion);

    /**
     *  TODO: 判断用户关注逻辑
     * */
    String getResultInfo(UserAttentionQuestion userAttentionQuestion);
}
