package com.niit.clouddemo.pojo.front;

import java.util.Date;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:13
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class NewUserInform {

    private Integer informId;
    private String userId;
    private Date informTime;
    private String informInfo;

    public Integer getInformId() {
        return informId;
    }

    public void setInformId(Integer informId) {
        this.informId = informId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getInformTime() {
        return informTime;
    }

    public void setInformTime(Date informTime) {
        this.informTime = informTime;
    }

    public String getInformInfo() {
        return informInfo;
    }

    public void setInformInfo(String informInfo) {
        this.informInfo = informInfo;
    }
}
