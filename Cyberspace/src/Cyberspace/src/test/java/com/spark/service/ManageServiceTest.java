package com.spark.service;

import com.spark.service.impl.ManageImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service
 * @Description: TODO
 * @date Date : 2018-12-12  8:32
 * @version： V1.0
 */
public class ManageServiceTest {

    //统一实例化
    private ManageService manageService = new ManageImpl();
    @Test
    public void getAllCountUser() {
        try {
            int allCountUser = manageService.getAllCountUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllCountArticle() {
        try {
            int allCountArticle = manageService.getAllCountArticle();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllCountComment() {
        try {
            int allCountComment = manageService.getAllCountComment();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAlllCountPhoto() {
        try {
            int alllCountPhoto = manageService.getAlllCountPhoto();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}