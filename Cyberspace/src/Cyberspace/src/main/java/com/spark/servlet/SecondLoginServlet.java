package com.spark.servlet;

import com.spark.domain.User;
import com.spark.service.UserService;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-05  15:19
 * @version： V1.0
 */
@WebServlet(name = "SecondLoginServlet",urlPatterns = {"/secondLoginServlet.do"})
public class SecondLoginServlet extends HttpServlet {
    private static final long serialVersionUID = -4676095627855389245L;

    protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
//		第二种方法处理邮箱是否存在问题！
        //获取邮箱， 密码
            String umail = request.getParameter("umail");
            UserService userService = new UserService();
            User user = userService.login2(umail);
            //把用户保存
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("umail",umail);
            //跳转到登录
            //重定向，让浏览器去跳转到指定的位置
            //============================================================
            //写入日志
            QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
            String sql2 = "INSERT INTO t_operationrecord(o_way,o_operation,o_date,o_ip) VALUES(?,?,?,?)";
            String o_operation = "用户修改个人信息";
            Date date = new Date(System.currentTimeMillis());
            String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            String o_way = "邮箱：" + umail;
            InetAddress ia = null;
            ia = InetAddress.getLocalHost();
            String r_ip = ia.getHostAddress();
            try {
                queryRunner.update(sql2,o_way,o_operation,date_,r_ip);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //============================================================
            response.sendRedirect(request.getContextPath()+ "/cyberspace_index.jsp");
            System.out.println("---> " + request.getContextPath());
//			response.setHeader("refresh", "3;url=personal_space.jsp");
    }

}
