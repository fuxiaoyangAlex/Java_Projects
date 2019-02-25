package com.niit.service;

import java.sql.SQLException;

import com.niit.domain.Emp;

public interface UserService {
	public int insert(Emp emp)throws SQLException;
	
	public int delete() throws SQLException;

}
