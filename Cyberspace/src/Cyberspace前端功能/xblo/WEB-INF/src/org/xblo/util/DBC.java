package org.xblo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBC {

	private static Connection con = null;
	private static Statement stm = null;

	private static TNS oraTNS = TG.GetFromXML();

	public DBC() {
		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			Class.forName(oraTNS.getDriver()).newInstance();
		} catch (Exception e) {
			XT.logE(e, "加载数据库驱动失败！");
		}
	}

	/* 创建数据库连接 */
	public void createCon() {
		if (con == null) {
			try {
				// con =
				// DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.168:1521:orcl",
				// "scott", "x");
				con = DriverManager.getConnection(oraTNS.getUrl(),
						oraTNS.getUsername(), oraTNS.getPassword());
			} catch (Exception e) {
				XT.logE(e, "获取数据库连接失败！");
			}
		}
	}

	/* 获取Statement对象 */
	public void getStm() {
		createCon();
		try {
			stm = con.createStatement();
		} catch (SQLException e) {
			XT.logE(e, "创建Statement对象失败！");
		}
	}

	/**
	 * @功能 对数据库的增加、修改和删除的操作
	 * @参数 sql为要执行的SQL语句
	 * @返回值 boolean型值
	 */
	public boolean executeUpdate(String sql) {
		System.out.println(sql);
		boolean mark = false;
		try {
			getStm();
			int iCount = stm.executeUpdate(sql);
			if (iCount > 0)
				mark = true;
			else
				mark = false;
		} catch (Exception e) {
			XT.logE(e, "更新数据失败! 更新sql: " + sql);
			mark = false;
		}
		return mark;
	}

	/* 查询数据库 */
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			getStm();
			try {
				rs = stm.executeQuery(sql);
			} catch (Exception e) {
				XT.logE(e, "查询数据库失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/* 关闭数据库的操作 */
	public void close() {
		if (stm != null)
			try {
				stm.close();
			} catch (SQLException e) {
				XT.logE(e, "关闭stm对象失败！");
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				XT.logE(e, "关闭con对象失败！");
			}
	}

	/* 关闭游标  */
	public void closeStm() {
		if (stm != null)
			try {
				stm.close();
			} catch (SQLException e) {
				XT.logE(e, "关闭stm对象失败！");
			}
	}

	public static void main(String[] args) {
		new DBC().createCon();
		// DBC con = DBC.getInstance();
		// System.out.println("con:" + con);
		System.out.println("########DB TEST OK########");
	}
}
