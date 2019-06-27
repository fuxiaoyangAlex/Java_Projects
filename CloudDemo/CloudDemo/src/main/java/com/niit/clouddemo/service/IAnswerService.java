package com.niit.clouddemo.service;


import com.niit.clouddemo.pojo.front.Answer;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 16:09
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface IAnswerService {

    /**
     *  TODO： 通过[问题的id]获取此回答的所有信息以及所属用户的信息 【1:1】
     * */
    List<Answer> getAnswerInfoandUserInfoByQID(String qid);

    /**
     *  TODO: 对指定的问题进行回答
     * */
    String getAnswerQuestionResultInfo(String questionid, String userid, String content);



}
