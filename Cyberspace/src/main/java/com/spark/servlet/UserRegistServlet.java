package com.spark.servlet;


import com.spark.domain.User;

import com.spark.util.JdbcUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * Servlet implementation class UserRegistServlet
 */
@WebServlet(name = "UserRegistServlet",urlPatterns = {"/registServlet.do"})
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求编码与响应的编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userName = request.getParameter("userName");
		System.out.println("userName: " + userName);
//		System.out.println("username: " + userName + " password: " + password + " email : " + email);
		//获取参数 验证码
	    String checkcode = request.getParameter("checkcode").toUpperCase();
	    System.out.println("checkcode : " + checkcode);
	    //获取服务器的验证码
		String confirmcode = (String)this.getServletContext().getAttribute("piccode");
		//判断输入的验证码是否正确：
		/**
		 * 使用Beanutils时要注意实体类的属性要和表单中name="xxx"相等
		 *
		 * */
		if(confirmcode.equals(checkcode)){
			//获取唯一的标志：邮箱
			String umail = request.getParameter("umail");
			//到数据库中查看有没有过此用户
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			String sql = "SELECT * FROM t_user where umail= ? ";
			User u = null;
			try {
				u = qr.query(sql,new BeanHandler<User>(User.class),umail);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//1.如果这个用户已经存在
			if(u != null){
				response.getWriter().write("该用户已经存在！");
				//跳转到登录
				response.setHeader("refresh","3;url=user_regist.jsp");
			}else {


				//如果正确
				//1.接收参数
				Map<String, String[]> parameterMap = request.getParameterMap();
				//2.读取接收的参数封装成User对象
				User user = new User();
				try {
					BeanUtils.populate(user, parameterMap);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				System.out.println(user);
				//3.设置UUID
				user.setUuid(UUID.randomUUID().toString());
				Date date = new Date(System.currentTimeMillis());
				String date_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:s.SSS").format(date);
				//4.写入到数据库中
				QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
				String sql1 = "INSERT INTO t_user VALUES (?,?,?,?,?,?,?,?,?)";
				try {
					queryRunner.update(sql1, user.getUuid(), user.getUmail(), user.getUpassword(), user.getUname(), user.getUbio(), user.getUgender(), user.getUrealname(), date_, user.getUimg());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//============================================================
				//写入日志

				String sql2 = "INSERT INTO t_registrecord(r_status,r_mail,r_date,r_ip) VALUES(?,?,?,?)";
				String r_name = user.getUname();
				String r_status = "用户";
				String r_mail = user.getUmail();
				InetAddress ia = null;
				ia = InetAddress.getLocalHost();
				String r_ip = ia.getHostAddress();

				try {
					queryRunner.update(sql2,r_name,r_status,r_mail,date_,r_ip);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//============================================================
				//如果成功跳转到登录页面
				response.setHeader("refresh", "3;url=user_login.jsp");
			}
		}else{
			//不正确，告诉用户验证码错误，跳转回到注册界面
			response.getWriter().write("验证码错误3秒后返回...");
			response.setHeader("Refresh","3;url=user_regist.jsp");
		}
	}
}
