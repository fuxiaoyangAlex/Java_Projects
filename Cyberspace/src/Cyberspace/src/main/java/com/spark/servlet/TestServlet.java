package com.spark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

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
public class TestServlet extends BaseServlet{
    private static final long serialVersionUID = -1847257820233282421L;


    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("添加操作： " );
        //eg:例如想要跳转到add.jsp
//        request.getRequestDispatcher("/add.jsp").forward(request,response);
        return "add.jsp";
    }

    public String del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("删除操作");
//        request.getRequestDispatcher("/del.jsp").forward(request,response);
        return "del.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("更新操作");
//        request.getRequestDispatcher("/update.jsp").forward(request,response);
        return "update.jsp";
    }
}

