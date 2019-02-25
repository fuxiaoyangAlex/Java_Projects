package com.spark.servlet;

import com.spark.domain.Article;
import com.spark.domain.User;
import com.spark.service.ArticleService;
import com.spark.service.LogService;
import com.spark.service.UserService;
import com.spark.service.impl.ArticleServiceImpl;
import com.spark.util.JdbcUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet(name = "loginServlet",urlPatterns = {"/loginServlet.do"})
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
//		第二种方法处理邮箱是否存在问题！
		//获取邮箱， 密码
		String umail = request.getParameter("umail");
		String upassword = request.getParameter("upassword");
		System.out.println("-----> " + umail + upassword);
		UserService userService = new UserService();
		try {
			User user = userService.login(umail,upassword);
//			response.getWriter().write("登录成功");
			//把用户保存
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
//			System.out.println("★User： " + user);
			session.setAttribute("umail",umail);
			//跳转到登录
			//重定向，让浏览器去跳转到指定的位置
			//============================================================
			LogService lg = new LogService();
			//写入操作日志
			String o_operation = "用户登录成功";
			Date date = new Date(System.currentTimeMillis());
			String o_way = "邮箱登录：" + umail;
			lg.operationOnRecord(o_way,o_operation);
			//写入站点访问视图
			String date__ = new SimpleDateFormat("HHmm").format(date);
			try {
				lg.t_rchart(date__);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			//============================================================
			request.getRequestDispatcher("/cyberspace_index.jsp").forward(request,response);
//			response.sendRedirect(request.getContextPath()+ "/cyberspace_index.jsp");
//			System.out.println("---> " + request.getContextPath());
//			response.setHeader("refresh", "3;url=personal_space.jsp");
		} catch (Exception e) {
			//============================================================
			LogService lg = new LogService();
			//写入操作日志
			String o_operation = "用户登录失败";
			Date date = new Date(System.currentTimeMillis());
			String o_way = "邮箱登录：" + umail;
			lg.operationOnRecord(o_way,o_operation);
			//写入站点访问视图
			String date__ = new SimpleDateFormat("HHmm").format(date);
			try {
				lg.t_rchart(date__);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			//============================================================
			if(e.getMessage().equals("邮箱或密码错误！")){
				//跳转回到登录页，回显错误信息
				request.setAttribute("error",e.getMessage());
				//转发,服务器内部转发
				request.getRequestDispatcher("user_login.jsp").forward(request,response);
			}else{
				e.printStackTrace();
			}
		}


//		第一种方法处理用户是否存在问题！
////		1.有值
//		if(u != null) {
//			response.getWriter().write("登录成功");
//			//把用户保存
//			HttpSession session = request.getSession();
//			session.setAttribute("user", u);
//			//跳转到登录
//			response.setHeader("refresh", "3;url=personal_space.jsp");
//		}else {
//			response.getWriter().write("登录失败");
//			//跳转到登录
//			response.setHeader("refresh", "3;url=user_login.jsp");
//		}

	}

}
