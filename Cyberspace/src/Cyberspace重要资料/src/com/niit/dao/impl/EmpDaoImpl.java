package com.niit.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.niit.dao.EmpDao;
import com.niit.domain.Emp;
import com.niit.util.DBConnect;

public class EmpDaoImpl implements EmpDao{
	DBConnect dbConnect=DBConnect.getInitDBConnect();
	Statement  stmt=null;
	PreparedStatement pstmt=null;
	Connection conn=null;
	String sql;
	@Override
	public int insert(Emp emp) throws SQLException {
		int result= 0;
		sql="insert into emp values(null,?,?,?,?,?)";		
		conn=dbConnect.getConnection();//连接数据库
		pstmt=conn.prepareStatement(sql);
		java.sql.Date date = new java.sql.Date(emp.getHiredate().getTime());
		pstmt.setString(1, emp.getEname());
		pstmt.setString(2, emp.getJob());
		pstmt.setDate(3,date);
		pstmt.setInt(4, emp.getSal());
		pstmt.setInt(5, emp.getComm());			
		result=pstmt.executeUpdate();		
		pstmt.close();
		conn.close();		
		return result;
	}
	@Override
	public int deleteById(int empno) throws SQLException {
		int result=0;
		conn=dbConnect.getConnection();
		String  sql="delete from emp where empno=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, empno);		
		result=pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return result;
	}

	@Override
	public List<Emp> findByName(String keyword) throws SQLException {
		List<Emp> all=new ArrayList<>();//用来存储查询到的数据
		String sql="select * from emp where ename like ?";//查询语句
		conn=dbConnect.getConnection();//数据库连接
		ResultSet rs=null;//存放结果记录集		
		pstmt=conn.prepareStatement(sql);//实例化预处理对象	
		pstmt.setString(1, "%"+keyword+"%"); //设置模糊查询
		rs=pstmt.executeQuery();//执行查询
		ResultSetMetaData rsmd=rs.getMetaData();//获得结果集元数据
		while(rs.next()){      //读取数据
			int empno=rs.getInt(1);
			String name=rs.getString(2);
			String job=rs.getString(3);
			java.util.Date hiredate=rs.getDate(4);
			int sal=rs.getInt(5);
			int comm=rs.getInt(6);			
			Emp emp=new Emp();
			emp.setEmpno(empno);
			emp.setEname(name);
			emp.setJob(job);
			emp.setHiredate(hiredate);
			emp.setSal(sal);
			emp.setComm(comm);
			all.add(emp);	
			System.out.println(emp);
		}

		System.out.println("一共返回："+rsmd.getColumnCount()+"列数据，列名分别为：");	
		int count=rsmd.getColumnCount();
		for(int i=1;i<=count;i++){
			System.out.print(rsmd.getColumnName(i)+"\t");
		}
		System.out.println();
		rs.close();
		pstmt.close();
		conn.close();
		return all;
	}

	@Override
	public int modify(Emp emp) throws SQLException {
		int result=0;
		int empno=emp.getEmpno();
		String sql="update emp set ename=?,job=?,hiredate=?,sal=?,comm=? where empno="+empno;
		conn=dbConnect.getConnection();
		pstmt=conn.prepareStatement(sql);
		java.sql.Date date = new java.sql.Date(emp.getHiredate().getTime());
		pstmt.setString(1, emp.getEname());
		pstmt.setString(2, emp.getJob());
		pstmt.setDate(3, date);
		pstmt.setInt(4, emp.getSal());
		pstmt.setInt(5, emp.getComm());
		result=pstmt.executeUpdate();
		return result;
	}
/******************************************findAll**************************************************/
	@Override
	public List<Emp> findAll() throws SQLException {
		List<Emp> all=new ArrayList<>();//用来存储查询到的数据
		String sql="select * from emp";//查询语句
		conn=dbConnect.getConnection();//数据库连接
		ResultSet rs=null;//存放结果记录集
		pstmt=conn.prepareStatement(sql);//实例化预处理对象
		rs=pstmt.executeQuery();//执行查询

//		ResultSetMetaData rsmd=rs.getMetaData();//获取结果集元数据
//		int columnCount=rsmd.getColumnCount();//获得结果元数据列数
//		List<Object> list=new ArrayList<>();//用来存储结果记录集的list
		while (rs.next()) {
			Emp emp = new Emp();
//			emp.setName(rs.getString("name"));
			emp.setEmpno(rs.getInt("empno"));
			emp.setEname(rs.getString("ename"));
			emp.setJob(rs.getString("job"));
			emp.setHiredate(rs.getDate("hiredate"));
			emp.setSal(rs.getInt("sal"));
			emp.setComm(rs.getInt("comm"));
//			emp.setComm(rs.getInt("comm"));

//			stu.setAge(rs.getInt("age"));
//			stu.setId(rs.getInt("id"));
			all.add(emp);
		}
//		while(rs.next()){      //依次读取数据
//			Map<String, Object> map=new HashMap<>();
//			for(int i=1;i<=columnCount;i++){
//				map.put(rsmd.getColumnName(i), rs.getObject(i));
//			}
//			list.add(map);
//		}
//		System.out.println(list.size());
//		int len=list.size();//查询的记录数
//		将数据依次存储到all之中

//		for(int i=0;i<len;i++){
//			Object object=list.get(i);
//			@SuppressWarnings("unchecked")
//			Map<String, Object> map=(Map<String, Object>)object;
//			Emp emp=new Emp();
//			emp.setEmpno((Integer)map.get("empno"));
//			emp.setJob(map.get("job").toString());
////			emp.setEname(map.get("ename").toString());
//
////			emp.setComment(map.get("comment").toString());
//
//			emp.setHiredate((java.util.Date)map.get("hiredate"));
//			emp.setSal((Integer)map.get("sal"));
//			emp.setComm((Integer)map.get("comm"));
//			all.add(emp);
//		}

		rs.close();
		pstmt.close();
		conn.close();
		return all;
	}
/********************************************************************************************/
	@Override
	public Emp findById(int id) throws SQLException {
		
		String sql="select * from emp where empno=?";//查询语句
		conn=dbConnect.getConnection();//数据库连接
		ResultSet rs=null;//存放结果记录集		
		pstmt=conn.prepareStatement(sql);//实例化预处理对象	
		pstmt.setInt(1, id); //设置模糊查询
		rs=pstmt.executeQuery();//执行查询
		
		Emp emp=new Emp();
		
		if(rs.next()){      //读取数据
			int empno=rs.getInt(1);
			String name=rs.getString(2);
			String job=rs.getString(3);
			java.util.Date hiredate=rs.getDate(4);
			int sal=rs.getInt(5);
			int comm=rs.getInt(6);			
			
			emp.setEmpno(empno);
			emp.setEname(name);
			emp.setJob(job);
			emp.setHiredate(hiredate);
			emp.setSal(sal);
			emp.setComm(comm);
		}
		return emp;
	}

}
