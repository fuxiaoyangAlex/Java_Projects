package com.niit.clouddemo.service;

import com.niit.clouddemo.pojo.front.PageInfo;
import com.niit.clouddemo.pojo.front.Question;
import com.niit.clouddemo.pojo.vo.NumQuestionAndUserWithBestAnswerAndUserMap;
import com.niit.clouddemo.pojo.vo.QuestionDetail;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 15:03
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public interface IQuestionService {

    /**
     *  添加新问题
     * */
    String getAddNewQuestionResultInfo(Question question);

    /**
     *  获取问题详情以及所对应问题的提出者信息
     * */
    QuestionDetail getQuestionDetail(String qid);
    /**
     * 获取所有已经被回答的问题条数（去重）
     * */
    Integer getDistinctQuestionNumFromAnswer();

    /**
     *  获取所有问题的条数
     * */
    Integer  getNumOfAllQuestion();

    /**
     *  获取推荐页信息
     * */
   List<NumQuestionAndUserWithBestAnswerAndUserMap> getNumQuestionAndUserWithBestAnswerAndUser(PageInfo pageInfo);

}
