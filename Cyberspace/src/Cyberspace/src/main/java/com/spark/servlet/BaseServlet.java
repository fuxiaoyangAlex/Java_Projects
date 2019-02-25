package com.spark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-08  17:15
 * @version： V1.0
 */
public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 826166921699386707L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String desPath = null;
        //接收参数
        String action = request.getParameter("action");
        try {
            Class clazz = this.getClass();
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //判断有没有传入的方法
            //如果有就调用
            if(method != null){
                //如果有就调用
                desPath = (String)method.invoke(this,request,response);
                System.out.println("desPath......" + desPath);
                /**在这个地方进行统一跳转*/
                if(desPath != null){
                    request.getRequestDispatcher(desPath).forward(request,response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
