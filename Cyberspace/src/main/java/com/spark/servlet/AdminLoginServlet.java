package com.spark.servlet;

import com.spark.domain.Admin;
import com.spark.service.AdminService;
import com.spark.service.LogService;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-02  16:52
 * @version： V1.0
 */
@WebServlet(name = "AdminLoginServlet",urlPatterns = {"/adminLoginServlet.do"})
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = -8698065481754838925L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取登录的管理员用户名和密码
        String admin_name = request.getParameter("admin_name");
        String admin_password = request.getParameter("admin_password");
        System.out.println(admin_password + admin_name + "---->");
        //2.调用登录页面
        AdminService adminService = new AdminService();
        try {
            Admin admin = adminService.adminLogin(admin_name, admin_password);


            //============================================================
            //写入日志
            String o_operation = "管理员登录成功";
            String o_way = "用户名登录：" + admin_name;
            LogService ls = new LogService();
            ls.operationOnRecord(o_way,o_operation);
            //============================================================

            HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
            response.sendRedirect(request.getContextPath()+ "/admin_index.jsp" );
            System.out.println("---> " + request.getContextPath());
        } catch (Exception e) {
            //============================================================
            //写入日志
            String o_operation = "管理员登录失败";
            String o_way = "用户名登录：" + admin_name;
            LogService ls = new LogService();
            ls.operationOnRecord(o_way,o_operation);
            //============================================================
            if(e.getMessage().equals("用户名或密码错误！")){
                System.out.println("登录失败!");
                //跳转到登录页，回显错误信息
                request.setAttribute("err",e.getMessage());
                //转发, 服务器内部进行转发
                request.getRequestDispatcher("admin_login.jsp").forward(request,response);
            }else{
                e.printStackTrace();
            }
        }

    }
}
