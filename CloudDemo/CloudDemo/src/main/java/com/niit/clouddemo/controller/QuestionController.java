package com.niit.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.pojo.front.PageInfo;
import com.niit.clouddemo.pojo.front.Question;
import com.niit.clouddemo.pojo.vo.NumQuestionAndUserWithBestAnswerAndUserMap;
import com.niit.clouddemo.pojo.vo.QuestionDetail;
import com.niit.clouddemo.service.IQuestionService;
import com.niit.clouddemo.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 21:50
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
@RequestMapping("/question")
@Controller
public class QuestionController {

    @Autowired
    private IQuestionService iQuestionService;

    @RequestMapping(value = "/addNewQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addNewQuestion(@RequestBody Map<String,Object> map){
        // 获取添加的数据
        // 1.问题标题
       String title = (String)map.get("title");
        // 2.问题描述
       String desc = (String)map.get("desc");
        // 3.问题标签
       String tag = (String)map.get("tag");
        // 4.问题提出者uid
       String uid = (String)map.get("uid");
       System.out.println("title: " + title + "desc: " + desc + "tag: " + tag + "uid" + uid);
        Date date = new Date();
        // 5. 问题提出的时间
        Timestamp timestamp = new Timestamp(date.getTime());
        // 6. 获取问题提出的qid
        String qid = BaseUtil.getUID();
        // 获取添加的数据
        // 构建实体
        Question question = new Question();
        question.setQuestionId(qid);
        question.setUserId(uid);
        question.setTitle(title);
        question.setDesc(desc);
        question.setTag(tag);
        question.setCreatetime(timestamp);
        // 添加问题
        String addNewQuestionResultInfo = iQuestionService.getAddNewQuestionResultInfo(question);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message",addNewQuestionResultInfo);
        return  jsonObject;
    }

    @RequestMapping(value = "/getDetailQuestionInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public QuestionDetail  getDetailQuestionInfo(@RequestBody Map<String,Object> map){
        String qid = (String)map.get("qid");
        System.out.println("qid: -----Controller-------> " + qid);
        QuestionDetail questionDetail = iQuestionService.getQuestionDetail(qid);
        System.err.println(questionDetail);
        return questionDetail;
    }

    @RequestMapping(value = "/getnumOfAllQuestion.do", method = RequestMethod.GET)
    public @ResponseBody
    JSONObject getnumOfAllQuestion(){
        Integer numOfAllQuestion = iQuestionService.getNumOfAllQuestion();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numOfAllQuestion",numOfAllQuestion);
        return jsonObject;
    }


    @RequestMapping(value = "/getNewestQuestionsDetails.do", method = RequestMethod.GET)
    public@ResponseBody
    List<NumQuestionAndUserWithBestAnswerAndUserMap> getNewestQuestionsDetails(@RequestParam("pageindex")int page){

        System.err.println("Controller" + page + "---------->" + "分页");
        // 每页显示的条数：
        int pageSize = 5;
        // 当前页数： currPage
        int currPage;
        // 总数据量
        int totalNum = iQuestionService.getDistinctQuestionNumFromAnswer();
        // 总页数

        int totalPages = totalNum / pageSize;

        if (totalNum % pageSize != 0) {
            totalPages ++;

        }
            // 总的数据
        if(page == 1){
            currPage = 0;
        }else{
           currPage = ((page - 1)* 5) - 1;
        }
            PageInfo pageInfoBean = getPageInfoBean(currPage, pageSize);
            List<NumQuestionAndUserWithBestAnswerAndUserMap> content = getContent(pageInfoBean);
            for(int i=0; i < content.size(); i ++){
                content.get(i).setTotalnum(totalPages);
            }
        System.out.println(content);
        return content;
    }


    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {}
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

/** ------------------------------------工具方法-----------------------------------------------------*/

    public List<NumQuestionAndUserWithBestAnswerAndUserMap> getContent(PageInfo pageInfo){
        List<NumQuestionAndUserWithBestAnswerAndUserMap> result =

                iQuestionService.getNumQuestionAndUserWithBestAnswerAndUser(pageInfo);
        return  result;
    }

    private PageInfo getPageInfoBean(int currPage, int pageSize){
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(currPage);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }
}
