package com.niit.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.niit.dao.EmpDao;
import com.niit.domain.Emp;
import com.niit.util.JDBCUtil;

public class EmpDaoImpl02 implements EmpDao{
    //	获取JDBCUtil实例
    JDBCUtil jdbcUtil=JDBCUtil.getInitJDBCUtil();

    @Override
    public int insert(Emp emp) throws SQLException {

        int result=0;//响应的行数
        String sql="insert into emp values(null,?,?,?,?,?)";//sql语句

        result=jdbcUtil.executeUpdate(sql, new Object[]{emp.getEname(),
                emp.getJob(),new Date(emp.getHiredate().getTime()),
                emp.getSal(),emp.getComm()});

        return result;
    }

    @Override
    public int deleteById(int empno) throws SQLException {
        int result=0;
        String sql="delete from emp where empno=?";
        result=jdbcUtil.executeUpdate(sql, new Object[]{empno});
        return result;
    }


    @Override
    public int modify(Emp emp) throws SQLException {
        int result=0;
        String sql="update emp set ename=?,job=?,hiredate=?,sal=?,comm=? where empno=?";

        result=jdbcUtil.executeUpdate(sql, new Object[]{emp.getEname(),
                emp.getJob(),new Date(emp.getHiredate().getTime()),
                emp.getSal(),emp.getComm(),emp.getEmpno()});

        return result;
    }

    @Override
    public List<Emp> findAll() throws SQLException {
        List<Emp> all=new ArrayList<>();
        String sql="select * from emp";
        List<Object> list=jdbcUtil.executeQuery(sql, null);
        int size=list.size();

        for(int i=0;i<size;i++){
            Object obj=list.get(i);
            @SuppressWarnings("unchecked")
            Map<String, Object> map=(Map<String, Object>)obj;
            Emp emp=new Emp();
            emp.setEmpno((Integer)map.get("empno"));
            emp.setEname(map.get("ename").toString());
            emp.setJob(map.get("job").toString());
            emp.setHiredate((java.util.Date)map.get("hiredate"));
            emp.setSal((Integer)map.get("sal"));
            emp.setComm((Integer)map.get("comm"));

            all.add(emp);
        }

        return all;
    }

    @Override
    public List<Emp> findByName(String keyword) throws SQLException {

        return null;
    }



    @Override
    public Emp findById(int id) throws SQLException {

        return null;
    }

}
