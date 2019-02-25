package com.niit.dao;

import java.sql.SQLException;
import java.util.List;

import com.niit.domain.Emp;

/**
 * @author HP
 * 对emp表实现增删改查
 */
public interface EmpDao {
	
	public int insert(Emp emp)throws SQLException; //插入操作
	
	public int deleteById(int empno) throws SQLException;//根据id删除
	
	public List<Emp> findByName(String keyword) throws SQLException;//根据姓名实现查询
	
	public int modify(Emp emp) throws SQLException;//修改
	
	public List<Emp> findAll() throws SQLException;//查询所有信息	
	
	public Emp findById(int id) throws SQLException;

}
