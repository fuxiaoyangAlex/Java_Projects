package org.xblo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xblo.util.DBC;
import org.xblo.util.XT;
import org.xblo.bean.ArticleTypeBean;

public class ArticleTypeDao {
	private DBC connection = null;
	private ArticleTypeBean articleTypeBean = null;

	public ArticleTypeDao() {
		connection = new DBC();
	}

	public boolean update(String code, ArticleTypeBean articleTypeBean) {
		String sql = null;
		if (code.equals("insert"))
			sql = "INSERT INTO ArticleType\n"
					+ "  (articletypeid, articletypename, articletypedesc)\n"
					+ "VALUES (ARTICLETYPE_S1.Nextval, '"
					+ articleTypeBean.getArticleTypeName() + "', '"
					+ articleTypeBean.getArticleTypeDesc() + "')";
		if (code.equals("delete"))
			sql = "delete from ArticleType where articletypeid = '"
					+ articleTypeBean.getArticleTypeId() + "'";
		if (code.equals("update"))
			sql = "update ArticleType set articletypename = '"
					+ articleTypeBean.getArticleTypeName() + "', "
					+ " articletypedesc ='"
					+ articleTypeBean.getArticleTypeDesc() + "'"
					+ " where articleTypeId = '" + articleTypeBean.getArticleTypeId()
					+ "'";
		boolean flag = connection.executeUpdate(sql);
		connection.closeStm();
		return flag;
	}

	public List<ArticleTypeBean> queryAll() {
		List<ArticleTypeBean> typeList = new ArrayList<ArticleTypeBean>();
		String sql = null;
		sql = "SELECT articletypeid, articletypename, articletypedesc FROM ArticleType";
		ResultSet rs = connection.executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					articleTypeBean = new ArticleTypeBean();
					articleTypeBean.setArticleTypeId(rs.getInt(1));
					articleTypeBean.setArticleTypeName(rs.getString(2));
					articleTypeBean.setArticleTypeDesc(rs.getString(3));
					typeList.add(articleTypeBean);
				}
			} catch (SQLException e) {
				XT.logE(e, "分类列表读取失败！");
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					XT.logE(e, "查询结果集关闭失败！");
				}
//				connection.closed();
			}
		}
		connection.closeStm();
		return typeList;
	}

	public ArticleTypeBean query(int articleTypeId) {
		String sql = "SELECT articletypeid, articletypename, articletypedesc "
				+ " FROM ArticleType where articletypeid='" + articleTypeId
				+ "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				articleTypeBean = new ArticleTypeBean();
				articleTypeBean.setArticleTypeId(rs.getInt(1));
				articleTypeBean.setArticleTypeName(rs.getString(2));
				articleTypeBean.setArticleTypeDesc(rs.getString(3));
			}
		} catch (SQLException e) {
			XT.logE(e, "分类(" + articleTypeId + ")读取失败！");
		}
		connection.closeStm();
		return articleTypeBean;
	}

	public static void main(String[] args) {
		ArticleTypeBean atb = new ArticleTypeBean();
		atb.setArticleTypeId(1);
		atb.setArticleTypeName("typename");
		atb.setArticleTypeDesc("typedesc");
		new ArticleTypeDao().update("insert", atb);
	}
}
