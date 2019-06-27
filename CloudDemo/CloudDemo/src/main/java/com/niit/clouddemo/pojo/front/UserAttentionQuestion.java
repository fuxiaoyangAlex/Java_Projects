package com.niit.clouddemo.pojo.front;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 9:57
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class UserAttentionQuestion {

    private Integer attention_id;
    private String user_id;
    private String attention_question_id;

    public Integer getAttention_id() {
        return attention_id;
    }

    public void setAttention_id(Integer attention_id) {
        this.attention_id = attention_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAttention_question_id() {
        return attention_question_id;
    }

    public void setAttention_question_id(String attention_question_id) {
        this.attention_question_id = attention_question_id;
    }

    @Override
    public String toString() {
        return "UserAttentionQuestion{" +
                "attention_id=" + attention_id +
                ", user_id='" + user_id + '\'' +
                ", attention_question_id='" + attention_question_id + '\'' +
                '}';
    }
}
