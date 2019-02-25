package com.spark.servlet;

import com.spark.service.AdminService;
import com.spark.service.LogService;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @date Date : 2018-12-05  23:14
 * @version： V1.0
 */
@WebServlet(name = "AdminDeleteServlet",urlPatterns = "/adminDeleteServlet.do")
public class AdminDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 8653700430703783675L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String admin_realname = request.getParameter("admin_realname");
        String admin_realname = request.getParameter("admin_realname");
        byte[]b=admin_realname.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
        String str = new String(b,"utf-8");//采用utf-8去接string
        response.setContentType("text/html;charset=utf-8");//设置页面的字符编码
        System.out.println("===>"+str);
        System.out.println("admin_realname: " + str);
        AdminService adminService = new AdminService();
        try {
            adminService.deleteAdminInfo(str);

            //=============================================================================================================
            //写入操作日志
            String o_way = "真实姓名：" + str;
            String o_operation = "删除管理员账号";
            LogService ls = new LogService();
            ls.operationOnRecord(o_way,o_operation);
            //写入操作视图
            Date date = new Date(System.currentTimeMillis());
            String date_ = new SimpleDateFormat("HHmm").format(date);
            ls.t_ochart(date_);
            //=============================================================================================================
            //进行转发

            request.getRequestDispatcher("adminGetAllInfoServlet.do");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
