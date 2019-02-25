package com.spark.servlet;

import com.spark.dao.UimgDao;
import com.spark.dao.UpdatePersonalInfoDao;
import com.spark.domain.User;
import com.spark.service.LogService;
import com.spark.service.UpdatePersonalInfoService;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:Wangzhuang2
 * @Description:
 *
 * 	对用户资料进行更新操作：
 * 	更新数据：
 * 	-> 用户真实姓名（urealname 更新用户真实姓名）
 * 	-> 用户性别 （ugender 更新用户性别）
 * 	-> 个性签名 （ubio 更新用户个性签名)
 *
 * @Date:2018/11/30 22点37分
 */
@WebServlet(name = "PersonalIntroServlet",urlPatterns = {"/personalIntroServlet.do"})


public class PersonalIntroServlet extends HttpServlet {
    private static final long serialVersionUID = 6116762210248301937L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        updateInfo(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateInfo(request,response);
    }


    private static void updateInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String urealname = request.getParameter("urealname");
        String ugender = request.getParameter("ugender");
        String ubio = request.getParameter("ubio");
        String umail = request.getParameter("umail");
        String uimg = request.getParameter("uimg");

        System.out.println("ubio: " + ubio);
        System.out.println("umail: " + umail);
        String result = null;
        try {
            UpdatePersonalInfoService upis = new UpdatePersonalInfoService();
            result  = upis.updatePersonalInfo(uimg,urealname,ugender,ubio,umail);
            // String urealname,String ugender,String ubio, String umail
            if("数据保存成功！".equals(result)){
                //回显成功信息
                LogService ls = new LogService();
                //===========写入操作日志===================
                String o_way = "邮箱：" + umail;
                String o_operation = "更改个人信息";
                ls.operationOnRecord(o_way,o_operation);
                //============写入操作视图====================
                Date date = new Date(System.currentTimeMillis());
                String date_ = new SimpleDateFormat("HHmm").format(date);
                ls.t_ochart(date_);
                //============================================
                request.setAttribute("result",result);
            }
            if("数据保存失败！".equals(result)){
                //回显错误信息
                request.setAttribute("result",result);
            }
//            转发,服务器内部转发
            try {
                request.getRequestDispatcher("personal_intro.jsp").forward(request,response);
            } catch (ServletException | IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }

    }


}
