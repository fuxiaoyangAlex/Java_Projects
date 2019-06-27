package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.dao.AnswerDao;
import com.niit.clouddemo.pojo.front.Answer;
import com.niit.clouddemo.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 16:09
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@Service
@Transactional
public class AnswerServiceImpl implements IAnswerService {

    @Autowired
    private AnswerDao answerDao;


    @Override
    public List<Answer> getAnswerInfoandUserInfoByQID(String qid) {
        List<Answer> answerInfoandUserInfoByQID = answerDao.getAnswerInfoandUserInfoByQID(qid);
        return answerInfoandUserInfoByQID;
    }

    /**
     * answer_id
     * content
     * createtime
     * hotstar
     * question_id
     * user_id
     * */
    @Override
    public String getAnswerQuestionResultInfo(String questionid, String userid, String content) {
        // 组装实体
        Answer answer = new Answer();
            // 1. 问题id
            answer.setQuestionId(questionid);
            // 2. 用户id
            answer.setUserid(userid);
            // 3. 回答内容
            answer.setContent(content);
            // 4. 回答时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
            answer.setCreatetime(timestamp);
        // 调用DAO层
        int i = answerDao.addAnswer(answer);

        if(i != 0){
            return "提交成功!";
        }else {

            return "提交失败!";
        }
    }
}
