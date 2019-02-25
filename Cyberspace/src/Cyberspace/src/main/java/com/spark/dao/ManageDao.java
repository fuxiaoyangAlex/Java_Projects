package com.spark.dao;

import java.sql.SQLException;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-12-12  8:13
 * @version： V1.0
 */
public interface ManageDao {

    public int getAllCountUser() throws SQLException;
    public int getAllCountArticle() throws SQLException;
    public int getAllCountComment() throws SQLException;
    public int getAlllCountPhoto() throws SQLException;
}
