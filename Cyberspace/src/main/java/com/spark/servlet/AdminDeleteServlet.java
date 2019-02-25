package com.spark.servlet;

import com.spark.service.AdminService;
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
            //写入日志
            //=============================================================================================================
            QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
            String sql = "INSERT INTO t_operationrecord(o_way,o_operation,o_date,o_ip) VALUES(?,?,?,?)";
            String o_way = "真实姓名：" + str;
            String o_operation = "删除管理员账号";
            Date date = new Date(System.currentTimeMillis());
            String o_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            InetAddress ia = null;
            ia = InetAddress.getLocalHost();
            String ip = ia.getHostAddress();
            String o_ip = ip;
            try {
                qr.update(sql,o_way,o_operation,o_date,o_ip);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //=============================================================================================================
            //进行转发

            request.getRequestDispatcher("adminGetAllInfoServlet.do");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
