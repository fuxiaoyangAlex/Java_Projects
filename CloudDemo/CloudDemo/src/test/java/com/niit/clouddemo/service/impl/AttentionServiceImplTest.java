package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.pojo.front.UserAttentionQuestion;
import com.niit.clouddemo.service.IAttentionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 10:31
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class AttentionServiceImplTest {

    @Test
    public void addAttentionQuestion() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IAttentionService attentionServiceImpl = applicationContext.getBean("attentionServiceImpl", IAttentionService.class);
        UserAttentionQuestion userAttentionQuestion = new UserAttentionQuestion();
        userAttentionQuestion.setUser_id("1141141276717092880");
        userAttentionQuestion.setAttention_question_id("2642618");
        String s = attentionServiceImpl.addAttentionQuestion(userAttentionQuestion);
        System.out.println(s);

    }

    @Test
    public void getResultInfo(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IAttentionService attentionServiceImpl = applicationContext.getBean("attentionServiceImpl", IAttentionService.class);
        UserAttentionQuestion userAttentionQuestion = new UserAttentionQuestion();
        userAttentionQuestion.setUser_id("1141141276717092880");
        userAttentionQuestion.setAttention_question_id("2642618");
        String resultInfo = attentionServiceImpl.getResultInfo(userAttentionQuestion);
        System.out.println(":::" + resultInfo);

    }
}