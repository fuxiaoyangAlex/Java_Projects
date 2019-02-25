package com.spark.service.impl;

import com.spark.dao.ManageDao;
import com.spark.dao.impl.ManageDaoImpl;
import com.spark.service.ManageService;

import java.sql.SQLException;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service.impl
 * @Description: TODO
 * @date Date : 2018-12-12  8:13
 * @version： V1.0
 */
public class ManageImpl implements ManageService {

    //统一实例化
    ManageDao manageDao = new ManageDaoImpl();
    @Override
    public int getAllCountUser() throws SQLException {
        int allCountUser = manageDao.getAllCountUser();
        System.out.println("allCountUser: " + allCountUser);
        return allCountUser;
    }

    @Override
    public int getAllCountArticle() throws SQLException {
        int allCountArticle = manageDao.getAllCountArticle();
        System.out.println("allCountArticle: " + allCountArticle);
        return allCountArticle;
    }

    @Override
    public int getAllCountComment() throws SQLException {
        int allCountComment = manageDao.getAllCountComment();
        System.out.println("allCountComment: " + allCountComment);
        return allCountComment;
    }

    @Override
    public int getAlllCountPhoto() throws SQLException {
        int allCountPhoto = manageDao.getAlllCountPhoto();
        System.out.println("allCountPhoto: " + allCountPhoto);
        return allCountPhoto;
    }
}
