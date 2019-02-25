package com.niit.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/7
 */
@WebServlet(name = "MyEncodingFilterServlet",urlPatterns = "/myEncodingFilterServlet.do")
public class MyEncodingFilterServlet extends HttpServlet {
    private static final long serialVersionUID = 5469561778816711636L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
