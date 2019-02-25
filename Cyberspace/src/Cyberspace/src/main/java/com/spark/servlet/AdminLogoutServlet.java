package com.spark.servlet;

import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-04  12:59
 * @version： V1.0
 */
@WebServlet(name = "AdminLogoutServlet",urlPatterns = {"/adminLogoutServlet.do"})
public class AdminLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = -4861359100877932897L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //写入日志
        //=============================================================================================================
        //解决乱码方法之一
        String name = req.getParameter("name");
        byte[] b = name.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
        String str = new String(b,"utf-8");//采用utf-8去接string
        resp.setContentType("text/html;charset=utf-8");//设置页面的字符编码
        System.out.println("===> " + str);
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "INSERT INTO t_operationrecord(o_way,o_operation,o_date,o_ip) VALUES(?,?,?,?)";
        String o_way = "用户名：" + str;
        String o_operation = "管理员退出后台管理系统";
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
        //清除session
        HttpSession session = req.getSession(true);
        session.invalidate(); //销毁用户的session对象
        this.getServletConfig().getServletContext().removeAttribute("user");
        //===============================================================================================================
        resp.sendRedirect(req.getContextPath() + "/admin_login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
