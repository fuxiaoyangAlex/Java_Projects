package com.spark.servlet;

import com.spark.service.LogService;
import com.spark.service.MessageService;

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
 * @date Date : 2018-12-06  23:09
 * @version： V1.0
 */
@WebServlet(name = "LmessageInsertServlet",urlPatterns={"/lmessageServlet.do"})
public class LmessageInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1716574207834129812L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String lmessage_content=request.getParameter("lmessage");//留言的内容
        String lmessage_user=request.getParameter("uname");//自己给自己留言时自己的用户名
        String lmessage_pic=request.getParameter("lmessage_pic");
        String uuid=request.getParameter("uuid");//自己给自己留言也要UUID作为之后统计时使用
        System.out.println("留言人名称："+lmessage_user);//留言人的名称
        System.out.println("留言人的头像："+lmessage_pic);//留言人的头像
        System.out.println("----------------Lmessage-----------------");
        System.out.println(lmessage_content);
        Date date = new Date(System.currentTimeMillis());
        String lmessage_date=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(date);
        MessageService ms = new MessageService();
        try{
            String flag = ms.MessageService(lmessage_user,lmessage_pic,lmessage_date,lmessage_content,uuid);
            if(flag.equals("留言失败！")){
            //跳转回到登录页，回显错误信息
                request.setAttribute("error","留言失败！");
            //转发,服务器内部转发
                request.getRequestDispatcher("cyberspace_board.jsp").forward(request,response);
            }else{
            //日志
            //============================================================================
            //写入操作日志
                String o_way="用户为："+lmessage_user;
                String o_operation="给用户名称为："+lmessage_user+"的留言板留言";
                LogService ls= new LogService();
                ls.operationOnRecord(o_way,o_operation);
            //写入操作视图
                Date date1 = new Date(System.currentTimeMillis());
                String date_ = new SimpleDateFormat("HHmm").format(date1);
                ls.t_ochart(date_);
            //============================================================================

            //跳转回到登录页，回显错误信息
                request.setAttribute("error","留言成功");
            //转发,服务器内部转发
                request.getRequestDispatcher("lMessageServlet.do?uuid="+uuid).forward(request,response);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }
}
