package com.niit.clouddemo.service.impl;

import com.niit.clouddemo.pojo.front.Answer;
import com.niit.clouddemo.service.IAdminService;
import com.niit.clouddemo.service.IAnswerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/20 16:11
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class AnswerServiceImplTest {

    @Test
    public void getAnswerInfoandUserInfoByQID() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IAnswerService answerServiceImpl = ac.getBean("answerServiceImpl", IAnswerService.class);
        List<Answer> answerInfoandUserInfoByQID = answerServiceImpl.getAnswerInfoandUserInfoByQID("1141317528597958661");
        System.out.println(answerInfoandUserInfoByQID.toString());
    }

    @Test
    public void getAnswerQuestionResultInfo(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        IAnswerService iAnswerService = applicationContext.getBean("answerServiceImpl", IAnswerService.class);
        String answerQuestionResultInfo = iAnswerService.getAnswerQuestionResultInfo("426180554760", "276717092880",
                "如果说一本好书可以启发人的灵魂的话，" +
                        "那么一部好的电影则可以震撼人的灵魂。一部优秀的电影，对人生有着深刻的启发意义和教育意义。《霸王别姬》\n" +
                        "\n" +
                        "人生如戏，戏如人生，不一样的场景总是带给人不一样的触动。仿佛在这部电影中看到了自己人生的起起落落；" +
                        "爱、恨、情、仇、离、怨，人生的写照在这部电影中得到了体现。《活着》\n" +
                        "\n" +
                        "从人生的高峰到人生的低估，经历了无尽的挫折与磨难。始终在不屈不挠的坚持着。战乱、灾害、意外接连发生，唯一的信念就是“活着”。" +
                        "《阿甘正传》\n" +
                        "\n" +
                        "从一个人人厌恶的“傻子”一直在坚持自我，从未放弃，我们的人生起点或许要比他好过许多，但是有没想过要像阿甘一样坚持着，外边的流言蜚语丝毫不动我心。" +
                        "《机器人总动员》\n" +
                        "\n" +
                        "我们在看到一个自己爱的人的时候，是否也可以像剧中的主人公小机器人一样，敢于追求，不畏艰难，冲破层层阻碍。" +
                        "《疯狂动物城》\n" +
                        "\n" +
                        "这是一部喜剧片，但是我却看成了一部纪录片。在剧中也同样可以映射社会的种种现象，我们是否可以坚持自我，无论何时何地。" +
                        "《黑鹰坠落》\n" +
                        "\n" +
                        "战争是残酷的、无情的。没有谁想要战争，每一个人都想要生存下去。“只有对不起国家的军人，没有国家对不起的军人”。");
        System.out.println(answerQuestionResultInfo);

    }
}