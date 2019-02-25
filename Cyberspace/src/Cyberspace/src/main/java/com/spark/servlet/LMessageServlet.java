package com.spark.servlet;

import com.spark.domain.T_lmessage;
import com.spark.domain.T_smessage;
import com.spark.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-06  19:51
 * @version： V1.0
 */
@WebServlet(name = "LMessageServlet",urlPatterns = {"/lMessageServlet.do"})
public class LMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 2295395806303300456L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
//查询该用户所有的大留言内容
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        System.out.println("---> " + uuid);
        MessageService ms = new MessageService();
        try {
            List<T_lmessage> allLMessage = ms.getAllLMessage(uuid);
            List<T_smessage> allSmessage  = ms.getAllSMessage();
            if (allLMessage != null || allSmessage != null){
                //将获取的数据存入session域中
                HttpSession session =  request.getSession();
                //对集合进行反转，输出的数据总是最新的
                Collections.reverse(allLMessage);
                Collections.reverse(allSmessage);
                session.setAttribute("sList",allSmessage);
                session.setAttribute("lList",allLMessage);
                //服务器内部转发
                request.getRequestDispatcher("cyberspace_board.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
