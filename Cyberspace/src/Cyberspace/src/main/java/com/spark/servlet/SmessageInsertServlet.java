package com.spark.servlet;

import com.spark.service.LogService;
import com.spark.service.MessageService;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-06  23:12
 * @version： V1.0
 */
@WebServlet(name = "SmessageInsertServlet",urlPatterns = {"/smessageInsertServlet.do"})
public class SmessageInsertServlet extends HttpServlet {
    private static final long serialVersionUID = -9038827328501772665L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String uuid = request.getParameter("uuid");
        String smessage_username = request.getParameter("smessage_username");
        String id_ = request.getParameter("id");
        int id = Integer.parseInt(id_);
        String smessage_userpic = request.getParameter("smessage_userpic");
        String smessage_usercontent = request.getParameter("smessage_usercontent");
        Date smessage_date_ = new Date(System.currentTimeMillis());
        String smessage_userdate = new SimpleDateFormat("yyyy-mm-dd").format(smessage_date_);
        //将回复的内容存入数据库中
        MessageService ms = new MessageService();
        try {
            String s = ms.MessageService2(smessage_username, smessage_userpic, smessage_usercontent, smessage_userdate, id);
            if("留言失败！".equals(s)){
                System.out.println("留言失败！");
            }else{
                LogService ls = new LogService();
                //=========================================
                //写入操作日志
                String o_way = "用户名: " + smessage_username; //回复的人
                String o_operation = "对留言板ID为：" + id + "进行回复";
                ls.operationOnRecord(o_way,o_operation);
                //写入操作视图
                Date date = new Date(System.currentTimeMillis());
                String date_ = new SimpleDateFormat("HHmm").format(date);
                ls.t_ochart(date_);
                request.getRequestDispatcher("lMessageServlet.do?uuid="+uuid).forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("uuid: " + uuid);
        System.out.println("smessage_username: " + smessage_username);
        System.out.println("id: " + id);
        System.out.println("smessage_userpic: " + smessage_userpic);
        System.out.println("smessage_content: " + smessage_usercontent);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
