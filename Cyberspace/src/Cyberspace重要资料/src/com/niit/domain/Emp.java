package com.niit.domain;

import java.util.Date;

public class Emp{

	private int empno;
	private String ename;
	private String job;
	private Date hiredate;
	private int sal;
	private int comm;
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	@Override
	public String toString() {
		return "Emp{" +
				"empno=" + empno +
				", ename='" + ename + '\'' +
				", job='" + job + '\'' +
				", hiredate=" + hiredate +
				", sal=" + sal +
				", comm=" + comm +
				'}';
	}
}