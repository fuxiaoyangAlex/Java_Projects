package com.spark.servlet;

import com.spark.domain.PageBean;
import com.spark.domain.RegistRecord;
import com.spark.service.JdbcSqlRegistRecordServiceImpl;
import com.spark.service.RegistRecordService;
import com.spark.util.Constant;
import com.spark.util.StringUtil;

import javax.servlet.ServletException;
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
 * @date Date : 2018-12-03  21:30
 * @version： V1.0
 */
@WebServlet(name = "RegistRecordServlet",urlPatterns = {"/registRecordServlet.do"})
public class RegistRecordServlet extends HttpServlet {
    private static final long serialVersionUID = -1770176814762264300L;


    //向上转型调用Service实现层
    private RegistRecordService registRecordServic = new JdbcSqlRegistRecordServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 接收request里的参数----> 用于查询指定学生
//        String name = request.getParameter("name"); //学生姓名

        // 获取学生性别
        int status = Constant.DEFAULT_GENDER;
        String statusStr = request.getParameter("status");
        System.out.println("statusStr: " + statusStr);
        if(statusStr!=null && !"".equals(statusStr.trim())){
            status = Integer.parseInt(statusStr);
        }

        // 校验pageNum参数输入合法性
        String pageNumStr = request.getParameter("pageNum");
        System.out.println("pageNumStr: " + pageNumStr);
        if(pageNumStr !=null && !StringUtil.isNum(pageNumStr)){
            request.setAttribute("errorMsg", "参数传输错误");
            request.getRequestDispatcher("A_ajdbcSql.jsp").forward(request, response);
            return;
        }
////
        int pageNum = Constant.DEFAULT_PAGE_NUM; //显示第几页数据
        if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
            pageNum = Integer.parseInt(pageNumStr);
        }

        int pageSize = Constant.DEFAULT_PAGE_SIZE;  // 每页显示多少条记录
        String pageSizeStr = request.getParameter("pageSize");
        if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        System.out.println("pageSize: " + pageSize);
//
        // 组装查询条件
        RegistRecord registRecord = new RegistRecord();
//        registRecord.setName(name);
        System.out.println("status: " + status);
        registRecord.setStatus(status);
//
        //调用service 获取查询结果
        System.out.println("pageNum: " + pageNum);
        PageBean<RegistRecord> result = registRecordServic.findStudent(registRecord,
                pageNum, pageSize);
        System.out.println("-----------");
        System.out.println(result);
//
        // 返回结果到页面
        request.setAttribute("result", result);
//        request.setAttribute("name", name);
        request.setAttribute("status", status);
        System.out.println("result : " + result);
        System.out.println("name : " + result);
        System.out.println("status: " + result);

        request.getRequestDispatcher("A_ajdbcSql.jsp").forward(request, response);
    }
////
//    //获取所有记录
//    public String getListRegistRecord(HttpServletRequest request, HttpServletResponse response){
//
//        try{
//            //1.调用服务层
//            RegistRecordService rrs = new RegistRecordService();
//            List<RegistRecord> allRegistRecord = new RegistRecordService();
//            //对集合进行反转
//            Collections.reverse(allRegistRecord);
//            //把数据写到request域
//            PageBean pageBean = new PageBean();
//            pageBean.setRegistRecordList(allRegistRecord);
//            pageBean.setTotalCount(5);
//            pageBean.setTotalPage(10);
//            request.setAttribute("pageBean",pageBean);
//            //当前页商品
//            //当前是那一页
//            //共多少页
//            //多少条记录
//            return "test";
//        }catch(SQLException se){
//            se.printStackTrace();
//        }
//        return null;
//    }

//    public String getPageData(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        //1.获取当前页码
//        String currentPage = request.getParameter("currentPage");
//        System.out.println("currentPage: " + currentPage);
//        //2.把页码给业务层，根据页码给我一个pageBean
//        RegistRecordService rrs = new RegistRecordService();
//        PageBean<R> pageBean = null;
//        try {
//            pageBean = rrs.getPageBean(Integer.parseInt(currentPage));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        //3.把pageBean写到域当中
//        request.setAttribute("pageBean", pageBean);
//        //4.转发
//        request.getRequestDispatcher("jdbcSqlStudent.jsp").forward(request, response);
//        return "";
//    }
}
