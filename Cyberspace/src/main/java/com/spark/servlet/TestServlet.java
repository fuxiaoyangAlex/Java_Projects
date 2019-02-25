package com.spark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-11-30  22:53
 * @version： V1.0
 */
@WebServlet(name = "TestServlet",urlPatterns = {"/testServlet.do"})

@MultipartConfig//**加上这个注解，反射该Servlet时才知道处理的是文件上传
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = -1847257820233282421L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String dvdName = request.getParameter("dvdName");
        String dvdState = request.getParameter("dvdState");
        String dvdType = request.getParameter("dvdType");

        System.out.println("dvdName: " + dvdName);
        System.out.println("dvdState: " + dvdState);
        System.out.println("dvdType: " + dvdType);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
