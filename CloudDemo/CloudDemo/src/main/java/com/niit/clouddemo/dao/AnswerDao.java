package com.niit.clouddemo.dao;

import com.niit.clouddemo.pojo.front.Answer;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 14:22
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface AnswerDao {


    /**
     *  TODO: 通过回答实体添加用户回答
     * */
    int addAnswer(Answer answer);
    /**
     *  TODO： 通过[问题的qid]获取此回答的所有信息以及所属用户的信息 【1:1】
     * */
    List<Answer> getAnswerInfoandUserInfoByQID(String qid);


}
