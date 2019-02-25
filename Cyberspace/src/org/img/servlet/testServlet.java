package org.img.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class testServlet
 */
@WebServlet(
		urlPatterns= {"/testServlet"}
		)
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public testServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		
			String name=request.getParameter("name");
			String age =request.getParameter("age");
		
			if(age.equals("fxy") || age.equals("20")) {
				
				HttpSession session = request.getSession();
				session.setAttribute("name",name);
				session.setAttribute("age",age);
				request.getRequestDispatcher("2.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("1.jsp");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
