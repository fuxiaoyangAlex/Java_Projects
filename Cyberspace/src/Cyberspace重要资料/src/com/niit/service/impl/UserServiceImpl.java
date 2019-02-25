package com.niit.service.impl;

import java.sql.SQLException;

import com.niit.domain.Emp;
import com.niit.factory.DaoFactory;
import com.niit.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public int insert(Emp emp) throws SQLException {
		int result=DaoFactory.getEmpDaoInstance02().insert(emp);
		return result;
	}

	@Override
	public int delete() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
