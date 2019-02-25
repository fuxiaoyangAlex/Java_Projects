package com.spark.servlet;

import com.spark.dao.UimgDao;
import com.spark.service.UpdatePersonalInfoService;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package ${PACKAGE_NAME}
 * @Description: TODO
 * @MultipartConfig加上整个注解，反射该servlet时才知道处理的是文件上传。（☠ very important）
 * @date Date : 2018-12-01  16:50
 * @version： V1.0
 */
@WebServlet(name = "UimgServlet",urlPatterns = "/uimgServlet.do")
@MultipartConfig
public class UimgServlet extends HttpServlet {

    private static final long serialVersionUID = 2627317012153165233L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateInfo(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateInfo(request,response);
    }

    private static void updateInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            List<String> list = new ArrayList<String>();

            String filename= UimgDao.getPhotoNewName(); //得到新的图片名称
            System.out.println("得到的新的图片名称： " + filename);
            //==========================================================================================================
            //ServletContext是什么？ -----------> SerlvetContext是整个Web应用程序运行后的代表对象。（通过ServletConfig的
            //getServletContext()方法取得）servletContext接口是Servlet中最大的一个接口，呈现了web应用的Servlet视图。ServletContext
            //实例是通过 getServletContext()方法获得的，由于//HttpServlet继承GenericServlet的关系，GenericServlet类和HttpServlet
            //类同时具有该方法。
            //==========================================================================================================
            ServletContext servletContext = request.getServletContext();
            System.out.println("servletContext： " + servletContext);
            //--->result
            //org.apache.catalina.core.ApplicationContextFacade@6a17e56c
            //org.apache.catalina.core.ApplicationContextFacade@298008a4
            //org.apache.catalina.core.ApplicationContextFacade@289a91c4
            //获取//数据库中存储格式:/webTest/imgs/***.jpg（理论上）
            //第一步:获取页面上上传的图片资源
            //@params: request：封装用户的请求 | servletContext 代表整个Web应用程序的对象
            List<FileItem> items = UimgDao.getRequsetFileItems(request,servletContext);
            boolean isLoadToSQL =false;
            for(FileItem item:items) {
                if(!item.isFormField()){
                    //判断后缀名是否是jpg
                    if(UimgDao.isGif(item)) {
                        isLoadToSQL = UimgDao.saveFile(item,filename);
                    }else {
                        System.out.println("后缀格式有误，保存文件失败");
                    }
                }else {
                    //获取表单中的非文件值表单中的空间name值
                    System.out.println("name值:  "+item.getFieldName());
                    //该name值空间中的value值
                    System.out.println(item.getString("UTF-8"));
                    list.add(item.getString("UTF-8"));
                }
            }

//        存在数据库里面的照片路径是在项目里的相对路径一般来说在项目中相对路径有request.getContextPath() + ... 开头，但经过实践在idea中获取
//        文件不需要项目名称直接通过二级父目录路径进行取得
////      String finalPhotoName= request.getContextPath()+"/images/uimg/"+filename;
//        System.out.println("****************************************");
//        for(String v : list){
//            System.out.println("v: " + v);
//
//        }
            String [] info = new String[list.size()];
            list.toArray(info);
            String urealname = info[0];
            String ugender = info[1];
            String ubio = info[2];
            String uimg = "images/uimg/"+filename;
            String umail = request.getParameter("umail");
            System.out.println("---------> umail: " + umail);
            System.out.println("urealName: " + urealname);
            System.out.println("ugender: " + ugender);
            System.out.println("ubio: " + ubio);
            System.out.println("uimg: " + uimg);

            String result = null;
            try {
                UpdatePersonalInfoService upis = new UpdatePersonalInfoService();
                result  = upis.updatePersonalInfo(uimg,urealname,ugender,ubio,umail);
                // String urealname,String ugender,String ubio, String umail
                //            转发,服务器内部转发
                if("数据保存失败！".equals(result)){
                    //回显错误信息
                    request.setAttribute("result",result);
                    request.getRequestDispatcher("cyberspace_about.jsp").forward(request,response);
                }else {
                   //考虑到需要及时更新数据浏览器地址栏传参的过程中不能有用户私人信息
                    // 只把用户的用户名传入到SencondLoginServlet进行快速登陆
                    HttpSession session = request.getSession(true);
                    session.invalidate(); //销毁用户的session对象
                    request.getRequestDispatcher("/secondLoginServlet.do?umail=" + umail).forward(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}
