package com.niit.domain;
import java.util.List;

public class PageBean<T> {
    private int cp;//当前页

    private int pc;//可以通过每页记录数和共多少条记录得到 （dt%md ==0 ）？dt/md ： dt/md+1 一共多少页

    private int md;//每页记录数

    private List<T> pd;//页中的记录数据 

    private int dt;//一共多少条记录

    private int cd;//可以通过每页记录是*(当前页数-1) + 1得到，当前页第一条记录的行数

    private String url;
//为了清楚起见，我就没有粘上我的setget方法
}
