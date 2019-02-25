package com.spark.servlet;

import com.spark.domain.User;
import com.spark.service.UserService;
import com.spark.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/30
 */
@WebServlet(name = "LogoutServlet",urlPatterns = {"/logoutServlet.do"})
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 6483879235486874922L;


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String umail = request.getParameter("umail");
        //============================================================
        //写入日志
        QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
        String sql2 = "INSERT INTO t_operationrecord(o_way,o_operation,o_date,o_ip) VALUES(?,?,?,?)";
        String o_operation = "用户退出本系统";
        Date date = new Date(System.currentTimeMillis());
        String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        String o_way = "用户邮箱: " + umail;
        InetAddress ia = null;
        ia = InetAddress.getLocalHost();
        String r_ip = ia.getHostAddress();
        try {
            queryRunner.update(sql2,o_way,o_operation,date_,r_ip);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        //清除session
        //============================================================
        HttpSession session = request.getSession(true);
        session.invalidate(); //销毁用户的session对象
        this.getServletConfig().getServletContext().removeAttribute("user");
        //===========================================================
        response.sendRedirect("user_login.jsp");

    }

}
