package com.spark.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.domain
 * @Description: TODO
 * @date Date : 2018-12-09  18:42
 * @version： V1.0
 */

@Setter@Getter
public class PageBean {
    //一页要展示多少条数据

    //当前是哪一页
    private Integer currentPage;

    //共多少页
    private Integer totalPage;

    //多少条记录
    private Integer totalCount;

    //当前页商品
    private List<Article> articleList = new ArrayList<>();
}
