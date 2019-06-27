package com.niit.clouddemo.pojo.front;

import java.util.Date;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 10:15
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class CollectionCatagory {

    private Integer catagoryId;
    private String catagoryName;
    private String userId;
    private Date createtime;
    private Date updatetime;

    public Integer getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(Integer catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
