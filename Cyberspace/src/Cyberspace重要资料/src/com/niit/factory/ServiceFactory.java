package com.niit.factory;

import com.niit.dao.EmpDao;
import com.niit.service.UserService;
import com.niit.service.impl.UserServiceImpl;

public class ServiceFactory {
	public static UserService getUserServiceInstance(){
		return new UserServiceImpl();
	}
}
