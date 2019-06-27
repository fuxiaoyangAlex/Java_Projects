package com.niit.clouddemo.dao;

import com.niit.clouddemo.pojo.front.UserAttentionQuestion;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 9:43
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface AttentionQuestionDao {

    /**
     * TODO: 用户关注的问题
     * */
    int addAttentionQuestion(UserAttentionQuestion userAttentionQuestion);

    /**
     * TODO： 判断用户是否已经关注
     * */
    UserAttentionQuestion getResultInfo(UserAttentionQuestion userAttentionQuestion);

}
