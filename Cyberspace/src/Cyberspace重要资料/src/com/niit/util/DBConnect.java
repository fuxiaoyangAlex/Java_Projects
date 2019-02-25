package com.niit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static final String DRIVER="com.mysql.jdbc.Driver";   //	数据库驱动
	private static final String URL="jdbc:mysql://localhost:3306/db_emp?serverTimezone=GMT&useUnicode=true"
			+"&characterEncoding=utf-8&useSSL=false";//数据库连接的url
	private static final String USER="root"; //连接数据库的用户名
	private static final String PASSWORD="mysqladmin"; //连接数据库的密码
	
	private static Connection conn=null;
	private static DBConnect dbConnect=null;
	
//	利用静态代码块注册数据库驱动，保证注册只执行一次
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}		
	}		
	
	private DBConnect(){		
	}
	
	public static DBConnect getInitDBConnect(){
		if(dbConnect==null){
			synchronized(DBConnect.class){
				if(dbConnect==null){
					dbConnect=new DBConnect();
				}
			}
		}
		return dbConnect;
	}
	
//	数据库的连接
	public Connection getConnection(){
		try {
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return conn;
	}
//	关闭数据库
	public void closeDB(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}
