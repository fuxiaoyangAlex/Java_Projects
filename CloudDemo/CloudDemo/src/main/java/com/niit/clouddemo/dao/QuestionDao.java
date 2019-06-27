package com.niit.clouddemo.dao;

import com.niit.clouddemo.pojo.front.PageInfo;
import com.niit.clouddemo.pojo.front.Question;
import com.niit.clouddemo.pojo.vo.NumQuestionAndUserWithBestAnswerAndUserMap;
import com.niit.clouddemo.pojo.vo.QuestionDetail;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/19 21:32
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface QuestionDao {

    /**
     *  TODO： 根据qid获取问题详情以及所对应问题的提出者信息
     * */
    QuestionDetail getQuestionDetail(String qid);

    /**
     *  TODO: 选取回答问题的所有不同的个数
     * */
    Integer getDistinctQuestionNumFromAnswer();


    /**
     *  TODO: 👨‍💻 获取问题的所有个数
     * */
    Integer getNumOfAllQuestion();

    /**
     *  TODO: 👨‍💻 ★ 四表查询-->首页推荐显示内容【最新问题 + 一个最赞的回答】
     *  1. 最新的问题（包含此问题所提出的用户【名称、头像、签名】）
     *  2. 最新问题下的最赞回答（包含此回答的内容 & 此回答所提出的用户【名称、头像、签名】）
     * */
     List<NumQuestionAndUserWithBestAnswerAndUserMap> getNumQuestionAndUserWithBestAnswerAndUser(PageInfo pageInfo);
    /**
     *  TODO:🔽👨‍💻 显示【最新】所有问题
     * */
    Question getAllQuestionByTime();

    /**
     *  TODO:🔽👨‍💻 三表查询-->通过问题的ID获取此问题以及对应回答详情页和每个回答的用户
     * */
    Question getQuestionDetailsByQIDAndUID(Integer qid);

    /**
     *  TODO：💢 提出问题即增加问题
     * */
    int addNewQuestion(Question question);




}
