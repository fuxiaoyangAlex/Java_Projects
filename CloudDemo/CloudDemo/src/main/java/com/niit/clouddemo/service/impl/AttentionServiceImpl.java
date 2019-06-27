package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.dao.AttentionQuestionDao;
import com.niit.clouddemo.pojo.front.UserAttentionQuestion;
import com.niit.clouddemo.service.IAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 10:27
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@Service("attentionServiceImpl")
@Transactional
public class AttentionServiceImpl implements IAttentionService {

    @Autowired
    private AttentionQuestionDao attentionQuestionDao;

    @Override
    public String addAttentionQuestion(UserAttentionQuestion userAttentionQuestion) {
        String resultInfo = getResultInfo(userAttentionQuestion);
        if (("未关注".equals(resultInfo))){
            int i = attentionQuestionDao.addAttentionQuestion(userAttentionQuestion);
            return "关注成功!";
        }else {
            return "已经关注了!";
        }

    }

    @Override
    public String getResultInfo(UserAttentionQuestion userAttentionQuestion) {
        UserAttentionQuestion resultInfo = attentionQuestionDao.getResultInfo(userAttentionQuestion);
        System.out.println(resultInfo + "resultInfo");

        if (resultInfo != null) {
            return "已经关注了!";
        }else {
            return "未关注";
        }
    }

    /**
     *  关注问题
     * */


}
