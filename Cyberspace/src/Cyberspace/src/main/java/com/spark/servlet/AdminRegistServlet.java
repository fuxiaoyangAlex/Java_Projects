package com.spark.servlet;

import com.spark.dao.AdminDao;
import com.spark.dao.CheckDao;
import com.spark.domain.Admin;
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
 * @date Date : 2018-12-04  15:05
 * @version： V1.0
 */
@WebServlet(name = "AdminRegistServlet",urlPatterns = "/adminRegistServlet.do")
public class AdminRegistServlet extends HttpServlet {
    private static final long serialVersionUID = 6749871181067770380L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String admin_name = request.getParameter("admin_name");
        String admin_realname = request.getParameter("admin_realname");
        String admin_password = request.getParameter("admin_password");
        String admin_gender = request.getParameter("admin_gender");
        String admin_age = request.getParameter("admin_age");
        String admin_phone = request.getParameter("admin_phone");
        String admin_mail = request.getParameter("admin_mail");
        String admin_qq = request.getParameter("admin_qq");
        System.out.println("admin_name: " + admin_name);
        System.out.println("admin_age: " + admin_age);
        System.out.println("admin_qq: " + admin_qq);
        AdminService ad = new AdminService();
        /**获取注册此用户的用户名和索引到数据库中查重*/
        CheckDao checkDao = new CheckDao();

        try {
            Admin admin = checkDao.checkAdmin(admin_name, admin_password);
            if(admin != null){
                request.getSession().setAttribute("error","此用户以存在");
                response.setHeader("refresh","1,url=administrator_.jsp");
            }else{
                System.out.println("realname: " + admin_realname);
                ad.adminRegist(admin_name,admin_realname,admin_password, admin_gender, admin_age, admin_phone, admin_mail, admin_qq);
                //============================================================
                //写入注册日志
                String r_name = "名称： " + admin_name;    //名字
                String r_status = "管理员";    //身份
                String r_mail = admin_mail;  //邮箱
                LogService ls = new LogService();
                ls.registOnRecord(r_name,r_status,r_mail);
                //写入注册视图---------------------
                Date date = new Date(System.currentTimeMillis());
                String date_ = new SimpleDateFormat("HHmm").format(date);
                ls.t_rchart(date_);
                //============================================================
                request.getRequestDispatcher("administrator_.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
