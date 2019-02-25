package org.xblo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xblo.util.DBC;
import org.xblo.util.XT;
import org.xblo.bean.XbloLinkBean;

public class XbloLinkDao {
	private DBC connection = null;
	private XbloLinkBean xbloLinkBean = null;

	public XbloLinkDao() {
		connection = new DBC();
	}

	public boolean update(String code, XbloLinkBean xbloLinkBean) {
		String sql = null;
		if (code.equals("insert"))
			sql = "INSERT INTO XbloLink(xbloLinkId,xbloLinkName,xbloLinkUrl) VALUES ( XBloLink_S1.Nextval, '"
					+ xbloLinkBean.getXbloLinkName()
					+ "', '"
					+ xbloLinkBean.getXbloLinkUrl() + "')";
		if (code.equals("delete"))
			sql = "delete from XbloLink where xbloLinkId = '"
					+ xbloLinkBean.getXbloLinkId() + "'";
		if (code.equals("update"))
			sql = "update XbloLink set xbloLinkName = '"
					+ xbloLinkBean.getXbloLinkName() + "', "
					+ " xbloLinkUrl ='" + xbloLinkBean.getXbloLinkUrl()
					+ "' where xbloLinkId = '" + xbloLinkBean.getXbloLinkId()
					+ "'";
		boolean flag = connection.executeUpdate(sql);
		connection.closeStm();
		return flag;
	}

	public List<XbloLinkBean> queryAll() {
		List<XbloLinkBean> xbloLinkList = new ArrayList<XbloLinkBean>();
		String sql = null;
		sql = "SELECT xbloLinkId,xbloLinkName,xbloLinkUrl FROM XbloLink";
		ResultSet rs = connection.executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					xbloLinkBean = new XbloLinkBean();
					xbloLinkBean.setXbloLinkId(rs.getInt(1));
					xbloLinkBean.setXbloLinkName(rs.getString(2));
					xbloLinkBean.setXbloLinkUrl(rs.getString(3));
					xbloLinkList.add(xbloLinkBean);
				}
			} catch (SQLException e) {
				XT.logE(e, "分类列表读取失败！");
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					XT.logE(e, "查询结果集关闭失败！");
				}
				// connection.closed();
			}
		}
		connection.closeStm();
		return xbloLinkList;
	}

	public XbloLinkBean query(int xbloLinkId) {
		String sql = "SELECT xbloLinkId,xbloLinkName,xbloLinkUrl FROM XBloLink where xbloLinkId='"
				+ xbloLinkId + "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				xbloLinkBean = new XbloLinkBean();
				xbloLinkBean.setXbloLinkId(rs.getInt(1));
				xbloLinkBean.setXbloLinkName(rs.getString(2));
				xbloLinkBean.setXbloLinkUrl(rs.getString(3));
			}
		} catch (SQLException e) {
			XT.logE(e, "链接(" + xbloLinkId + ")读取失败！");
		}
		connection.closeStm();
		return xbloLinkBean;
	}

	public static void main(String[] args) {
		XbloLinkBean xbl = new XbloLinkBean();
		xbl.setXbloLinkId(1);
		xbl.setXbloLinkName("GOOGLE");
		xbl.setXbloLinkUrl("www.google.com");
		new XbloLinkDao().update("insert", xbl);
	}
}
