package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.pojo.front.UserAttentionQuestion;
import com.niit.clouddemo.service.IAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/24 10:36
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

@Controller
@RequestMapping(value = "/attention")
public class AttentionController {

    @Autowired
    private IAttentionService iAttentionService;

    /**
     *  TODO: 测试界面
     * */
    @RequestMapping(value = "/getTestPage.do")
    public String getTestPage(){
        return "redirect:/jsp/newest_questions.jsp";
    }


    @RequestMapping(value = "/addAttentionQuestion.do")
    public @ResponseBody
    JSONObject addAttentionQuestion(@RequestBody Map<String, Object> map) {
        System.out.println("---------------------------->");
        String questionid = (String)map.get("questionid");
        String userid = (String)map.get("userid");
        System.out.println("userid: " + userid + "questionid: " + questionid);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        UserAttentionQuestion userAttentionQuestion = new UserAttentionQuestion();
        userAttentionQuestion.setAttention_question_id(questionid);
        userAttentionQuestion.setUser_id(userid);
        String s = iAttentionService.addAttentionQuestion(userAttentionQuestion);
        //返回JSON
        JSONObject jsonObject = new JSONObject();
        System.out.println("----------> " + s);
            jsonObject.put("message",s);


        return jsonObject;
    }









}
