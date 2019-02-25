package org.xblo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xblo.util.DBC;
import org.xblo.util.XT;
import org.xblo.bean.XbloUserBean;

public class XbloUserDao {
	private DBC connection = null;
	private XbloUserBean xBloUserBean = null;

	public XbloUserDao() {
		connection = new DBC();
	}

	public boolean update(String code, XbloUserBean xBloUserBean) {
		String sql = null;
		if (code.equals("insert"))
			sql = "INSERT INTO XbloUser(xbloUserId, xbloUsername, xbloPassword)\n"
					+ "VALUES(XbloUser_S1.Nextval, '"
					+ xBloUserBean.getXbloUsername() + "','"
					+ xBloUserBean.getXbloPassword() + "')";
		if (code.equals("delete"))
			sql = "delete from XbloUser where xbloUserId = "
					+ xBloUserBean.getXbloUserId();
		if (code.equals("update"))
			sql = "update XbloUser set xbloUserId = '" + xBloUserBean.getXbloUserId()
					+ "'" + ", xbloUsername = '" + xBloUserBean.getXbloUsername()
					+ "', xbloPassword = '" + xBloUserBean.getXbloPassword() + "'"
					+ "WHERE xbloUserId = '" + xBloUserBean.getXbloUserId() + "'";
		boolean flag = connection.executeUpdate(sql);
		connection.closeStm();
		return flag;
	}

	public List<XbloUserBean> queryAll() {
		List<XbloUserBean> xBloUserList = new ArrayList<XbloUserBean>();
		String sql = null;
		sql = "SELECT xbloUserId, xbloUsername, xbloPassword FROM XbloUser";
		ResultSet rs = connection.executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					xBloUserBean = new XbloUserBean();
					xBloUserBean.setXbloUserId(rs.getInt(1));
					xBloUserBean.setXbloUsername(rs.getString(2));
					xBloUserBean.setXbloPassword(rs.getString(3));

					xBloUserList.add(xBloUserBean);
				}
			} catch (SQLException e) {
				XT.logE(e, "用户列表读取失败！");
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					XT.logE(e, "用户查询结果集关闭失败！");
				}
//				connection.closed();
			}
		}
		connection.closeStm();
		return xBloUserList;
	}

	public XbloUserBean queryByXbloUserId(int xbloUserId) {
		String sql = "SELECT xbloUserId, xbloUsername, xbloPassword FROM XbloUser WHERE xbloUserId ="
				+ xbloUserId;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				xBloUserBean = new XbloUserBean();
				xBloUserBean.setXbloUserId(rs.getInt(1));
				xBloUserBean.setXbloUsername(rs.getString(2));
				xBloUserBean.setXbloPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			XT.logE(e, "用户(" + xbloUserId + ")读取失败！");
		}
		connection.closeStm();
		return xBloUserBean;
	}

	public XbloUserBean getValidXbloUser(XbloUserBean xBloUserBean) {
		XbloUserBean validXbloUserBean = null;
		String sql = "SELECT xbloUserId, xbloUsername, xbloPassword FROM XbloUser WHERE xbloUsername = '"
				+ xBloUserBean.getXbloUsername()
				+ "' AND xbloPassword = '"
				+ xBloUserBean.getXbloPassword() + "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				validXbloUserBean = new XbloUserBean();
				validXbloUserBean.setXbloUserId(rs.getInt(1));
				validXbloUserBean.setXbloUsername(rs.getString(2));
				validXbloUserBean.setXbloPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			XT.logE(e, "用户(" + xBloUserBean.getXbloUsername() + ")读取失败！");
		}
		connection.closeStm();
		return validXbloUserBean;
	}

	public static void main(String[] args) {
		XbloUserDao ad = new XbloUserDao();
		XbloUserBean xBloUserBean = new XbloUserBean();
		xBloUserBean.setXbloUserId(10);
		xBloUserBean.setXbloUsername("user10");
		xBloUserBean.setXbloPassword("pass10");
		boolean b = ad.update("insert", xBloUserBean);

		XbloUserBean ub = ad.queryByXbloUserId(10);
		System.out.println(ub.getXbloUsername());
	}
}
