package com.spark.servlet;

import com.spark.domain.Category;
import com.spark.service.CategoryService;
import com.spark.service.LogService;
import com.spark.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @date Date : 2018-12-08  19:27
 * @version： V1.0
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/categoryServlet.do"})
public class CategoryServlet extends BaseServlet {
    private static final long serialVersionUID = -7647487161005643202L;
        private Category category = new Category(); //实例化domain层
        private LogService lg = new LogService(); //实例化日志层
        private CategoryService categoryService = new CategoryServiceImpl(); //实例化Service层

    public String getAllCategory(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        //0. 获取用户的参数
        String cauthor_ = request.getParameter("uname");
        byte[] bytes = cauthor_.getBytes("ISO-8859-1");
        String cauthor = new String(bytes,"UTF-8");
        category.setCauthor(cauthor);
        //1.调用服务层（service层）的业务
        try {
            List<Category> allCategory = categoryService.getAllCategory(category);
            //2.对集合进行反转
            Collections.reverse(allCategory);
            //3.把获取到的数据存储到request域中
            request.setAttribute("allCategory",allCategory);
            //添加返回值
            return "cyberspace_article_category.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addCategory(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("Hello add ...............");
        //1.调用服务层（service层）增加分类的业务
        String cname_ = request.getParameter("cname");
        byte [] b = cname_.getBytes("ISO-8859-1");
        String cname = new String(b,"UTF-8");

        String cauthor_ = request.getParameter("uname");
        byte [] b2 = cauthor_.getBytes("ISO-8859-1");
        String  cauthor = new String(b2,"UTF-8");
        Category category = new Category();
        category.setCname(cname);
        category.setCauthor(cauthor);

        try {
            categoryService.save(category);//添加成功后为了让页面出现新添加的数据调用getAllCategory方法
            //=======================================================
            //写入到用户活跃度的视图
            Date date_ = new Date(System.currentTimeMillis());
            String date = new SimpleDateFormat("HHmm").format(date_);
            lg.t_ochart(date);
            //=======================================================
            return "categoryServlet.do?action=getAllCategory";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateCategory(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("Hello update ...............");
        String cid_ = request.getParameter("cid");
        String cauthor_ = request.getParameter("uname");
        byte[] b2 = cauthor_.getBytes("ISO-8859-1");
        String cauthor = new String(b2,"UTF-8");

        String cname_ = request.getParameter("cname");
        byte [] b = cname_.getBytes("ISO-8859-1");
        String cname = new String(b,"UTF-8");
        int cid;
        cid = Integer.parseInt(cid_);
        System.out.println("cid: " + cid + "cauthor: " + cauthor + "cname:" + cname);
        Category category = new Category();
        category.setCname(cname);
        category.setCid(cid);
        try {
            categoryService.update(category);
            //=====================================================================
            //写入用户活跃视图
            Date date_ = new Date(System.currentTimeMillis());
            String date = new SimpleDateFormat("HHmm").format(date_);
            lg.t_ochart(date);
            //====================================================================
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "categoryServlet.do?action=getAllCategory";
    }

    public String deleteCategory(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("delete category.........");
        String cid_ = request.getParameter("cid");
        int cid = Integer.parseInt(cid_);
        String cauthor_ = request.getParameter("uname");
        byte [] b2 = cauthor_.getBytes("ISO-8859-1");
        String  cauthor = new String(b2,"UTF-8");
        System.out.println("cid: " + cid + "uname: " + cauthor);
        Category category = new Category();
        category.setCid(cid);
        category.setCauthor(cauthor);
        try {
            categoryService.delete(category);
            //=======================================
            //写入用户活跃视图
            Date date_ = new Date(System.currentTimeMillis());
            String date = new SimpleDateFormat("HHmm").format(date_);
            try {
                lg.t_ochart(date);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "categoryServlet.do?action=getAllCategory";
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return null;
    }


    /**
     * //★★★★★
     * 在发布文章的时候先获取所有属于指定用户的分类！------------------------------------
     * */
    public String getMyCategory(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        //0. 获取用户的参数
        String cauthor_ = request.getParameter("uname");
        byte[] bytes = cauthor_.getBytes("ISO-8859-1");
        String cauthor = new String(bytes,"UTF-8");
        category.setCauthor(cauthor);
        //1.调用服务层（service层）的业务
        try {
            List<Category> myCategory = categoryService.getAllCategory(category);
            //2.对集合进行反转
            Collections.reverse(myCategory);
            //3.把获取到的数据存储到request域中
            request.setAttribute("myCategory",myCategory);
            //添加返回值
            return "cyberspace_article_edit.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
