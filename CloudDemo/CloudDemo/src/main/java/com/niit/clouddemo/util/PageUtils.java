package com.niit.clouddemo.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/19 23:10
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */

public class PageUtils implements Serializable {
    private static final long serialVersionUID = -1202716581589799959L;
    /**
     * TODO: 总记录数 🔽
     * */
    private int totalCount;
    /**
     * TODO: 每页记录数 🔽
     */
    private int pageSize;
    /**
     * TODO：总页数 🔽
     * */
    private int totalPage;
    /**
     * TODO: 当前页数 🔽
     * */
    private int currPage;

    //列表数据
    private List<?> list;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }
}