package com.niit.util;

import com.niit.domain.Emp;

import javax.print.attribute.standard.PagesPerMinute;
import java.util.ArrayList;
import java.util.List;

public class MyPagination {
    public List<Emp> list = null;
    private int recordCount = 0; //总记录数
    private int pageSize = 0; //每页显示的记录数
    private int maxPage = 0; //最大页数

    //初始化分页信息
    public List<Emp> getInitPage(List<Emp> list, int Page, int pageSize){
        //初始化相关信息
        List<Emp> newList = new ArrayList<Emp>();
        this.list = list; //list为获取出来的整个数据记录
        recordCount = list.size(); //初始化记录数
        this.pageSize = pageSize;
        this.maxPage =  getMaxPage();
        for(int i = (Page - 1) * pageSize; i <= Page*pageSize-1; i ++){
            if(i > recordCount){
                break;
            }
            newList.add(list.get(i));
        }
        return newList;
    }

    //获取指定页的信息
    public List<Emp> getAppointPage(int Page){
        List<Emp> newList = new ArrayList<>();
        for(int i = (Page - 1) * pageSize; i <= Page*pageSize-1; i ++){
            if(i > recordCount){
                break;
            }
            newList.add(list.get(i));
        }
        return newList;
    }

    //获取总的记录数
    public int getRecordCount(){
        return recordCount;
    }

    //获取最大页数
    public int getMaxPage(){
        int maxPage = (recordCount%pageSize==0?
                recordCount/pageSize:(recordCount/pageSize+1)
                );
        return maxPage;
    }

    //获取当前的页码数
    public int getPage(String str){
        if(str==null){
            str = "0";
        }
        int Page = Integer.parseInt(str);
        if (Page < 1){
            Page = 1;
        }else{
            if((Page - 1) * pageSize>recordCount){
                Page = maxPage;
            }
        }
        return Page;
    }
    //输出页面的信息
    public String prinCtrl(int Page, String urlString){
        String strHtml = "<table><tr><td>";
        strHtml = strHtml + "当前页数：[ " + Page + "/" + maxPage + " ]";
        if(Page > 1){
            strHtml = strHtml + "<a href='" + urlString + "?Page=" + (Page-1) + "'>上一页</a>";
        }
        if(Page<maxPage){
            strHtml =  strHtml + "<a href='" + urlString + "?Page=" + (Page + 1) + "'>下一页</a>";
            strHtml = strHtml + "<a hre`f='" + urlString + "?Page=" + maxPage + "'>最后一页</a>";
        }
        strHtml = strHtml + "</td></tr></table>";
        return strHtml;
    }

}
