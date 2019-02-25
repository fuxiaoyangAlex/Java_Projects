package com.niit.factory;

import com.niit.dao.EmpDao;
import com.niit.dao.impl.EmpDaoImpl;
import com.niit.dao.impl.EmpDaoImpl02;

public class DaoFactory {
	public static EmpDao getEmpDaoInstance(){
		return new EmpDaoImpl();
	}
	
	public static EmpDao getEmpDaoInstance02(){
		return new EmpDaoImpl02();
	}
	

}
