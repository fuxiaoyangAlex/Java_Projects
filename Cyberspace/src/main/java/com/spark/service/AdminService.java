package com.spark.service;

import com.spark.dao.AdminDao;
import com.spark.dao.CheckDao;
import com.spark.domain.Admin;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-02  17:26
 * @version： V1.0
 */
public class AdminService {
//    String admin_name = request.getParameter("admin_name");
//    String admin_password = request.getParameter("admin_password");
//    String admin_gender = request.getParameter("admin_gender");
//    String admin_age = request.getParameter("admin_age");
//    String admin_phone = request.getParameter("admin_phone");
//    String admin_mail = request.getParameter("admin_mail");
//    String admin_qq = request.getParameter("admin_qq");
    public Admin adminLogin(String admin_name,String admin_password) throws Exception {
        //1.调用dao层到数据库中进行查询
        CheckDao checkDao = new CheckDao();
        Admin admin = checkDao.checkAdmin(admin_name,admin_password);
        if(admin != null){
            return admin;
        }else{
            throw new Exception("用户名或密码错误！");
        }
    }

    public void adminRegist(String admin_name,String admin_realname,String admin_password,String admin_gender,String admin_age,String admin_phone,String admin_mail,String admin_qq){
        AdminDao adao = new AdminDao();
        try {
            adao.registAdmin(admin_name,admin_realname,admin_password, admin_gender, admin_age, admin_phone, admin_mail, admin_qq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Admin> adminGetAllInfo() {
        List<Admin> allAdmin2 = null;
        try {
            allAdmin2 = AdminDao.findAllAdmin2();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAdmin2;
    }

    public void deleteAdminInfo(String admin_realname) throws SQLException {
        AdminDao ad = new AdminDao();
        int flag = ad.deleteAdmin(admin_realname);
    }
}
