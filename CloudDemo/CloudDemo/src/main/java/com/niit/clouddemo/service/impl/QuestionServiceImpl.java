package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.dao.QuestionDao;
import com.niit.clouddemo.pojo.front.PageInfo;
import com.niit.clouddemo.pojo.front.Question;
import com.niit.clouddemo.pojo.vo.NumQuestionAndUserWithBestAnswerAndUserMap;
import com.niit.clouddemo.pojo.vo.QuestionDetail;
import com.niit.clouddemo.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 15:04
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public String getAddNewQuestionResultInfo(Question question) {
        int i = questionDao.addNewQuestion(question);
        if (i != 0){
            return "添加新问题成功!";
        }
        return "添加失败!";
    }

    @Override
    public QuestionDetail getQuestionDetail(String qid) {
        QuestionDetail questionDetail = questionDao.getQuestionDetail(qid);
        return questionDetail;
    }

    @Override
    public Integer getDistinctQuestionNumFromAnswer() {
        Integer distinctQuestionNumFromAnswer = questionDao.getDistinctQuestionNumFromAnswer();
        return distinctQuestionNumFromAnswer;
    }

    @Override
    public Integer getNumOfAllQuestion() {
        Integer numOfAllQuestion = questionDao.getNumOfAllQuestion();
        return numOfAllQuestion;
    }

    @Override
    public List<NumQuestionAndUserWithBestAnswerAndUserMap> getNumQuestionAndUserWithBestAnswerAndUser(PageInfo pageInfo) {
        List<NumQuestionAndUserWithBestAnswerAndUserMap> numQuestionAndUserWithBestAnswerAndUser =
                questionDao.getNumQuestionAndUserWithBestAnswerAndUser(pageInfo); questionDao.getNumQuestionAndUserWithBestAnswerAndUser(pageInfo);
        return numQuestionAndUserWithBestAnswerAndUser;
    }


}
