package com.spark.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.domain
 * @Description: TODO
 * @date Date : 2018-12-03  21:24
 * @version： V1.0
 */
@Setter
@Getter
public class Pager<T> implements Serializable {
    private static final long serialVersionUID = 3420659125650252324L;

    //已经知道的数据
    private int pageSize; // 每页显示多少条记录

    private int currentPage; //当前第几页数据

    private int totalRecord; // 一共多少条记录

    //需要计算得到

    private int totalPage; // 一共多少页记录（总页数，通过totalRecord和pageSize计算得到
    private int startIndex; //开始索引，我们在数据库中要从第几行数据开始拿去，有了startIndex
    //和pageSize，就可以知道limit语句的两个数据，就能获得每页需要显示的数据

    private List<T> dataList; //要显示的数据（将每页要显示的数据放在List集合中）


    /**
     * Functional description:
     * ============================================
     * 通过pageNum（当前页自己指定），pageSize，totalRecord计算得来得totalPage和startIndex
     *构造方法中将pageNum，pageSize，totalRecord获得
     * ============================================
     * @param:
     * @return:
     * @auther: wangzhuang2
     * @date: 2018/12/03 14:04
     */
    public Pager(int pageNum, int pageSize, List<T> sourceList){
        //对获取得数据源进行判空
        if(sourceList == null || sourceList.isEmpty()){
            return;
        }

        // 总记录条数
        this.totalRecord = sourceList.size();

        // 每页显示多少条记录（自己指定）
        this.pageSize = pageSize;

        //获取总页数
        //================================================
        //第一种获取总页数： 总页数 = 总记录数 / 一页显示记录数（/取模） |通过计算无多余记录出现
        //第二种获取总页数： 总页数 = （总记录数 / 一页显示记录数） + 1  | 通过计算有多余记录出现
        //================================================
        this.totalPage = this.totalRecord / this.pageSize;

        if(this.totalRecord % this.pageSize !=0){ //若出现多余记录进行加 1
            this.totalPage = this.totalPage + 1;
        }

        // 当前第几页数据（pageNum由请求参数进行获取。）
        //=================================================
        //若总页数小于指定的页数，则当前页为最后一页，页的索引即为总页数
        //若总页数大于指定的页数，即为指定的页的索引
        //=================================================
        this.currentPage = this.totalPage < pageNum ?  this.totalPage : pageNum;

        // 起始索引映射到数据库中即当前从何处开始查找指定数据
        //=======================================================
        // 起始索引 = 当前页的页数 * （当前页的索引 - 1）
        //=======================================================
        int fromIndex	= this.pageSize * (this.currentPage -1);

        // 结束索引
        int toIndex  = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;

        //将获取的每一页应该显示的几条数据进行获取放入dataList中
        this.dataList = sourceList.subList(fromIndex, toIndex);
    }

    //无参构造方法
    public Pager(){

    }
    //全参构造方法
    public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,
                 List<T> dataList) {
        super();
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }
}
