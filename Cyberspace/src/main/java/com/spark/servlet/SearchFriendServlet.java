package com.spark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/29
 */
@WebServlet(name = "SearchFriendServlet",urlPatterns = {"/searchFriendServlet.do"})
public class SearchFriendServlet extends HttpServlet {
    private static final long serialVersionUID = 5732625552231982888L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
