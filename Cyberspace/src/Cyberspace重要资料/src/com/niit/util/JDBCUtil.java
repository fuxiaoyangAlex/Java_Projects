package com.niit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
	private static final String DRIVWER="com.mysql.cj.jdbc.Driver";//数据库驱动
	private static final String URL="jdbc:mysql://localhost:3306/db_emp?serverTimezone=GMT&useUnicode=true"
			+"&characterEncoding=utf-8&useSSL=false";//数据库连接的url
	private static final String USER="root";
	private static final String PSD="mysqladmin"; //根据自己数据库链接的密码进行适当的修改
	
	private static Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	private static JDBCUtil jdbcUtil=null;
	
	private JDBCUtil(){
		
	}
	
	public static JDBCUtil getInitJDBCUtil(){
		if(jdbcUtil==null){
			synchronized (JDBCUtil.class) {
				if(jdbcUtil==null){
					jdbcUtil=new JDBCUtil();
				}
			}
		}
		return jdbcUtil;
	}	
	
//	通过静态代码块注册数据库驱动，保证注册只执行一次
	static{
		try {
			Class.forName(DRIVWER);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}
//	数据库的链接
	public Connection getConnection(){
		try {
			conn=DriverManager.getConnection(URL,USER,PSD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	封装insert、update、delete操作
	public int executeUpdate(String sql,Object[] params){
		int  affectLine=0;
		
		try {
			conn=this.getConnection(); //获得连接
			pstmt=conn.prepareStatement(sql);//预处理对象pstmt调用sql
//			传递参数
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			affectLine=pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			closeAll();
		}		
		return affectLine;
	}
	
	private ResultSet executeQueryRS(String sql,Object[] params){
		try {
			conn=jdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
		/*传递参数*/
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
		/*执行得到结果记录集*/
			rs=pstmt.executeQuery();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return rs;
	}
	
     /*将结果记录集中的记录保存至List之中*/
	public List<Object> executeQuery(String sql,Object[] params){
		List<Object> list=new ArrayList<>();
		ResultSetMetaData rsmd=null;
		ResultSet rs=executeQueryRS(sql, params);
		int columnCount=0;//用来记录rs中的字段数
		
		try {
			rsmd=rs.getMetaData();
			columnCount=rsmd.getColumnCount();
			while(rs.next()){
				Map<String, Object>map=new HashMap<>();
				for(int i=1;i<=columnCount;i++){
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			closeAll();
		}
		
		return list;
	}
	
	public List<Object> executeQuerySingle(String sql,Object[] params){
		List<Object> list=new ArrayList<>();
		ResultSetMetaData rsmd=null;
		ResultSet rs=executeQueryRS(sql, params);
		int columnCount=0;//用来记录rs中的字段数
		
		try {
			rsmd=rs.getMetaData();
			columnCount=rsmd.getColumnCount();
			if(rs.next()){
				Map<String, Object>map=new HashMap<>();
				for(int i=1;i<=columnCount;i++){
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			closeAll();
		}
		
		return list;
	}
	
	
//	数据库相关操作的关闭
	public void closeAll(){
		if(rs!=null){//关闭结果记录集
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){//关闭pstmt对象
			try {
				pstmt.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		if(conn!=null){//关闭连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
