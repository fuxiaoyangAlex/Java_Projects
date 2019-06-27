package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.pojo.front.PageInfo;
import com.niit.clouddemo.pojo.front.Question;
import com.niit.clouddemo.pojo.vo.NumQuestionAndUserWithBestAnswerAndUserMap;
import com.niit.clouddemo.pojo.vo.QuestionDetail;
import com.niit.clouddemo.service.IQuestionService;
import com.niit.clouddemo.util.BaseUtil;
import com.niit.clouddemo.util.IdWorker;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 15:05
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class QuestionServiceImplTest {

    /**
     *  TODO:<!-- 添加新问题 【问题的提出用户uid、问题的qid、问题的标题、问题的描述、问题的标签、问题提出的时间】-->
     * */
    @Test
    public void getAddNewQuestionResultInfo(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IQuestionService questionServiceImpl = applicationContext.getBean("questionServiceImpl", IQuestionService.class);
        Question question = new Question();
        String qid = BaseUtil.getUID();
        question.setQuestionId(qid);
        question.setUserId("276717092880");
        question.setTitle("计算机科学的基础是什么?");
        question.setDesc("计算机科学（Computer Science）的基础是计算机科学思维 —— 一种思考问题的逻辑方式。首先，对计算机科学的认识至关重要，要知道计算机科学和会编程、开发、修电脑是完全不同概念。其次，要对：计算机导论、算法、图灵机（状态机 State Machine）和相关逻辑、类推、证明要有所概念。那么，你就会理解所谓计算机科学思维则是寻求通法解决问题的思想，对于问题解决的定义则是根据正确性（Correctness）与完整性（Completeness）。\n" +
                "\n"+"作者：匿名用户\n" +
                "链接：https://www.zhihu.com/question/319537300/answer/653387675\n" +
                "来源：知乎\n" +
                "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n" +
                "\n" +
                "如果你只是一点点的照着学校的课程学，那是一件非常令人难过的事。" +
                "即使你学过学校的算法与数据结构，拿过算法导论依然几乎看不懂；" +
                "即使你学过编译原理，你依然写不出来一门好的编程语言；就拿 C++ 为例… 我们同学有八成不知道 RAII，你能想象这有多夸张么。建议从一门新语言开始，见识一下更大的世界：Lisp 系的 Scheme，Racket，CLFunctional 系的 Haskell，Agda，IdirsC 系的 C++，Rust应用的 Swift，Kotlin不建议 Java，Python 这种，这些语言比较老，没法给你接触新事物的动力。当然光会语言是没有用的，接下来要不要看看计算机视觉呢？下一个 OpenCV 玩一玩，跑一点小 Demo，做一个三维重建，做一个图像识别…或者你喜欢 PLT，读一读 lambda-calculus，读一读 tt，都是非常有趣的。然后试试写一门高级的（比如 dt）语言。如果喜欢做出来能用的东西，试试前端和移动开发呢？TypeScript，Swift，Kotlin 都是非常不错的东西，顺便你还能学到各式各样的GUI。做大型项目的时候，还有设计模式，Unit Test，全都很好玩。如果觉得这些太遥远，那算法怎么样，读一读算法导论，刷一刷 leetcode，简直是学生时代最让人舒服的事。这不都是… 计算机技术么？我天天都因为时间不够，学不会东西而苦恼，居然有人不知道学什么…");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        question.setTag("计算机科学");
        question.setCreatetime(timestamp);
        String addNewQuestionResultInfo = questionServiceImpl.getAddNewQuestionResultInfo(question);
        System.err.println(addNewQuestionResultInfo);
//        questionServiceImpl.getAddNewQuestionResultInfo()

    }

    @Test
    public void getQuestionDetail(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IQuestionService questionServiceImpl = applicationContext.getBean("questionServiceImpl", IQuestionService.class);
        QuestionDetail questionDetail = questionServiceImpl.getQuestionDetail("17528602152962");
        System.out.println(questionDetail);
    }

    @Test
    public void getDistinctQuestionNumFromAnswer(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IQuestionService questionServiceImpl = applicationContext.getBean("questionServiceImpl", IQuestionService.class);
        Integer distinctQuestionNumFromAnswer = questionServiceImpl.getDistinctQuestionNumFromAnswer();
        System.out.println("问题数： " + distinctQuestionNumFromAnswer);
    }

    @Test
    public void getNumOfAllQuestion() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IQuestionService questionSericeImpl = ac.getBean("questionServiceImpl", IQuestionService.class);
        Integer numOfAllQuestion = questionSericeImpl.getNumOfAllQuestion();
        System.err.println(numOfAllQuestion);
    }

    @Test
    public void getNumQuestionAndUserWithBestAnswerAndUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IQuestionService questionServiceImpl = applicationContext.getBean("questionServiceImpl", IQuestionService.class);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(1);
        pageInfo.setPageSize(3);
        List<NumQuestionAndUserWithBestAnswerAndUserMap> numQuestionAndUserWithBestAnswerAndUserMaps = questionServiceImpl.getNumQuestionAndUserWithBestAnswerAndUser(pageInfo);
        System.out.println(numQuestionAndUserWithBestAnswerAndUserMaps);
    }
}