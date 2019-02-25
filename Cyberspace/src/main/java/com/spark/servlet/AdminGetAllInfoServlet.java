package com.spark.servlet;

import com.spark.domain.Admin;
import com.spark.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-05  20:54
 * @version： V1.0
 */
@WebServlet(name = "AdminGetAllInfoServlet",urlPatterns = {"/adminGetAllInfoServlet.do"})
public class AdminGetAllInfoServlet extends HttpServlet {
    private static final long serialVersionUID = -8388600011986547160L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService as = new AdminService();
        List<Admin> list = as.adminGetAllInfo();
        HttpSession session = request.getSession(); //获取会话对象
        session.setAttribute("list",list); //保存获取的对象
//        response.sendRedirect("Index_administrator.jsp");
        request.getRequestDispatcher("Index_administrator.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
