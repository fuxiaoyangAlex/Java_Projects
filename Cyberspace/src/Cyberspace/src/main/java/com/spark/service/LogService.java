package com.spark.service;

import com.spark.dao.LogDao;
import com.spark.domain.T_ochart;
import com.spark.domain.T_rchart;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-06  12:23
 * @version： V1.0
 */
public class LogService {
    private LogDao ld = new LogDao();
    private String rCharSql;
    private String oCharSql;
    public void operationOnRecord(String o_way,String o_operation) throws UnknownHostException {
        ld.operationOnRecord(o_way,o_operation);
    }

    public void registOnRecord(String r_name,String r_status,String r_mail) throws UnknownHostException {
        ld.registOnRecord(r_name,r_status,r_mail);
    }


    public void t_rchart(String time) throws SQLException {
        String sql = getRchartSql(time);
        System.out.println("sql: ************: " + sql);
        int i = ld.updateRchart(sql);
        if (i > 0){
            System.out.println("更新成功-------Rchart");
        }else{
            System.out.println("更新失败-------Rchart");
        }
    }


    public String getRchartSql(String time){
        int i_time = Integer.parseInt(time);
        if(i_time > 2200){
            rCharSql = "UPDATE t_rchart SET tr12 = tr12 + 2";
        }else if(i_time > 2000){
            rCharSql = "UPDATE t_rchart SET tr11 = tr11 + 2";
        }else if(i_time > 1800){
            rCharSql = "UPDATE t_rchart SET tr10 = tr10 + 2";
        }else if(i_time > 1600){
            rCharSql = "UPDATE t_rchart SET tr9 = tr9 + 2";
        }else if(i_time > 1400){
            rCharSql = "UPDATE t_rchart SET tr8 = tr8 + 2";
        }else if(i_time > 1200){
            rCharSql = "UPDATE t_rchart SET tr7 = tr7 + 2";
        }else if(i_time > 1000){
            rCharSql = "UPDATE t_rchart SET tr6 = tr6 + 2";
        }else if(i_time > 800){
            rCharSql = "UPDATE t_rchart SET tr5 = tr5 + 2";
        }else if(i_time > 600){
            rCharSql = "UPDATE t_rchart SET tr4 = tr4 + 2";
        }else if(i_time > 400){
            rCharSql = "UPDATE t_rchart SET tr3 = tr3 + 2";
        }else if(i_time > 200){
            rCharSql = "UPDATE t_rchart SET tr2 = tr2 + 2";
        }else{
            rCharSql = "UPDATE t_rchart SET tr1 = tr1 + 2";
        }
        return rCharSql;
    }

    public void t_ochart(String time) throws SQLException {
        String sql = getOchartSql(time);
        System.out.println("sql: ----------> " + sql);
        int i = ld.updateOchart(sql);
        if(i > 0){
            System.out.println("更新成功--------Ochart");
        }else{
            System.out.println("更新失败--------Ochart");
        }
    }

    public String getOchartSql(String time){

        int i_time = Integer.parseInt(time);
        if(i_time > 2200){
            oCharSql = "UPDATE t_ochart SET to12 = to12 + 2";
        }else if(i_time > 2000){
            oCharSql = "UPDATE t_ochart SET to11 = to11 + 2";
        }else if(i_time > 1800){
            oCharSql = "UPDATE t_ochart SET to10 = to10 + 2";
        }else if(i_time > 1600){
            oCharSql = "UPDATE t_ochart SET to9 = to9 + 2";
        }else if(i_time > 1400){
            oCharSql = "UPDATE t_ochart SET to8 = to8 + 2";
        }else if(i_time > 1200){
            oCharSql = "UPDATE t_ochart SET to7 = to7 + 2";
        }else if(i_time > 1000){
            oCharSql = "UPDATE t_ochart SET to6 = to6 + 2";
        }else if(i_time > 800){
            oCharSql = "UPDATE t_ochart SET to5 = to5 + 2";
        }else if(i_time > 600){
            oCharSql = "UPDATE t_ochart SET to4 = to4 + 2";
        }else if(i_time > 400){
            oCharSql = "UPDATE t_ochart SET to3 = to3 + 2";
        }else if(i_time > 200){
            oCharSql = "UPDATE t_ochart SET to2 = to2 + 2";
        }else{
            oCharSql = "UPDATE t_ochart SET to1 = to1 + 2";
        }
        return oCharSql;
    }


    /**
     * 操作视图的业务层
     * */
    public List<T_ochart> getAllOchart() throws SQLException {
        List<T_ochart> allOchart = ld.getAllOchart();
        return allOchart;
    }

    /**
     * 注册视图的业务层
     * */
    public List<T_rchart> getAllRchart() throws SQLException {
        List<T_rchart> allRchart = ld.getAllRchart();
        return allRchart;
    }

// =======================测试============================================
    public static void main(String [] args) throws SQLException {
        LogService ls = new LogService();
        List<T_ochart> allOchart = ls.getAllOchart();
        for(T_ochart to : allOchart){
            System.out.println("--------》 " + to);
        }
        System.out.println("*******************************************");
        List<T_rchart> allRchart = ls.getAllRchart();
        for(T_rchart tr : allRchart){
            System.out.println("-------》 " + tr);
        }
    }


}
