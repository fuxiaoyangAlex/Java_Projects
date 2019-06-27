package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.pojo.front.Answer;
import com.niit.clouddemo.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 8:13
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private IAnswerService iAnswerService;

    @RequestMapping(value = "/getAnswerInfoandUserInfoByQID.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Answer> getAnswerInfoandUserInfoByQID(@RequestBody Map<String,Object> map){
        String qid = (String)map.get("qid");
        System.out.println("Controller Answer: " + qid);
        List<Answer> answerInfoandUserInfoByQID = iAnswerService.getAnswerInfoandUserInfoByQID(qid);
        System.out.println(answerInfoandUserInfoByQID);
        return answerInfoandUserInfoByQID;

    }

    @RequestMapping(value = "/addAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getAnswerQuestionResultInfo(@RequestBody Map<String,Object> map){
        String questionid = (String)map.get("questionid");
        String userid = (String)map.get("userid");
        String content = (String)map.get("content");
        String answerQuestionResultInfo = iAnswerService.getAnswerQuestionResultInfo(questionid, userid, content);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", answerQuestionResultInfo);
        return jsonObject;
    }
}
