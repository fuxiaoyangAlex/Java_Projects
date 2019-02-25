package org.xblo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xblo.util.DBC;
import org.xblo.util.XT;
import org.xblo.bean.ArticleBean;

public class ArticleDao {
	public static int ALLTYPE = -1;

	private DBC connection = null;
	private ArticleBean articleBean = null;

	public ArticleDao() {
		connection = new DBC();
	}

	public boolean update(String code, ArticleBean articleBean) {
		String sql = null;
		if (code.equals("insert"))
			sql = "INSERT INTO Article\n" + "  (articleId\n"
					+ "  ,articleTypeId\n" + "  ,articleTitle\n"
					+ "  ,articleSummary\n" + "  ,articleContent\n"
					+ "  ,createDate\n" + "  ,visitCount\n"
					+ "  ,createUserId)\n" + "VALUES\n" + "  ("
					+ " ARTICLE_S1.Nextval " + ",'"
					+ articleBean.getArticleTypeId() + "','"
					+ articleBean.getArticleTitle() + "','"
					+ articleBean.getArticleSummary() + "','"
					+ articleBean.getArticleContent() + "'," + " SYSDATE "
					+ ",'" + articleBean.getVisitCount() + "','"
					+ articleBean.getCreateUserId() + "')";
		if (code.equals("delete"))
			sql = "delete from article where articleId = "
					+ articleBean.getArticleId();
		if (code.equals("update"))
			sql = "update article set articleTypeId = '"
					+ articleBean.getArticleTypeId() + "', articleTitle = '"
					+ articleBean.getArticleTitle() + "'"
					+ ", articleSummary = '" + articleBean.getArticleSummary()
					+ "', articleContent = '" + articleBean.getArticleContent()
					+ "', visitCount = '" + articleBean.getVisitCount()
					+ "', createUserId = '" + articleBean.getCreateUserId()
					+ "' where articleId = '" + articleBean.getArticleId()
					+ "'";
		boolean flag = connection.executeUpdate(sql);
		connection.closeStm();
		return flag;
	}

	public List<ArticleBean> queryByArticleTypeId(int articleTypeId) {
		List<ArticleBean> articlelist = new ArrayList<ArticleBean>();
		String sql = null;
		sql = "SELECT articleId\n" + "      ,articleTypeId\n"
				+ "      ,articleTitle\n" + "      ,articleSummary\n"
				+ "      ,articleContent\n" + "      ,createDate\n"
				+ "      ,visitCount\n" + "      ,createUserId\n"
				+ "  FROM Article";
		if (articleTypeId != ALLTYPE)
			sql = sql + " where articleTypeId='" + articleTypeId + "'";
		sql = sql + " ORDER BY createDate DESC";
		ResultSet rs = connection.executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					articleBean = new ArticleBean();
					articleBean.setArticleId(rs.getInt(1));
					articleBean.setArticleTypeId(rs.getInt(2));
					articleBean.setArticleTitle(rs.getString(3));
					articleBean.setArticleSummary(rs.getString(4));
					articleBean.setArticleContent(rs.getString(5));
					articleBean.setCreateDate(rs.getString(6));
					articleBean.setVisitCount(rs.getInt(7));
					articleBean.setCreateUserId(rs.getInt(8));

					articlelist.add(articleBean);
				}
			} catch (SQLException e) {
				XT.logE(e, "文章列表读取失败！");
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
		return articlelist;
	}

	public ArticleBean queryByArticleId(int articleId) {
		String sql = "SELECT articleId\n" + "      ,articleTypeId\n"
				+ "      ,articleTitle\n" + "      ,articleSummary\n"
				+ "      ,articleContent\n" + "      ,createDate\n"
				+ "      ,visitCount\n" + "      ,createUserId\n"
				+ "  FROM Article\n" + " WHERE articleId =" + articleId;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				articleBean = new ArticleBean();
				articleBean.setArticleId(rs.getInt(1));
				articleBean.setArticleTypeId(rs.getInt(2));
				articleBean.setArticleTitle(rs.getString(3));
				articleBean.setArticleSummary(rs.getString(4));
				articleBean.setArticleContent(rs.getString(5));
				articleBean.setCreateDate(rs.getString(6));
				articleBean.setVisitCount(rs.getInt(7));
				articleBean.setCreateUserId(rs.getInt(8));

			}
		} catch (SQLException e) {
			XT.logE(e, "文章(" + articleId + ")读取失败！");
		}
		connection.closeStm();
		return articleBean;
	}

	public List<ArticleBean> queryTopOnVisitCount(int topCount) {
		List<ArticleBean> articlelist = new ArrayList<ArticleBean>();
		String sql = null;
		sql = 
			"SELECT articleId\n" +
			"      ,articleTypeId\n" + 
			"      ,articleTitle\n" + 
			"      ,articleSummary\n" + 
			"      ,articleContent\n" + 
			"      ,createDate\n" + 
			"      ,visitCount\n" + 
			"      ,createUserId\n" + 
			"  FROM (SELECT articleId\n" + 
			"              ,articleTypeId\n" + 
			"              ,articleTitle\n" + 
			"              ,articleSummary\n" + 
			"              ,articleContent\n" + 
			"              ,createDate\n" + 
			"              ,visitCount\n" + 
			"              ,createUserId\n" + 
			"          FROM Article\n" + 
			"         ORDER BY visitCount DESC) t\n" + 
			" WHERE ROWNUM < ";

		if (topCount <= 0)
			topCount = 10;
		sql = sql + topCount;
		ResultSet rs = connection.executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					articleBean = new ArticleBean();
					articleBean.setArticleId(rs.getInt(1));
					articleBean.setArticleTypeId(rs.getInt(2));
					articleBean.setArticleTitle(rs.getString(3));
					articleBean.setArticleSummary(rs.getString(4));
					articleBean.setArticleContent(rs.getString(5));
					articleBean.setCreateDate(rs.getString(6));
					articleBean.setVisitCount(rs.getInt(7));
					articleBean.setCreateUserId(rs.getInt(8));

					articlelist.add(articleBean);
				}
			} catch (SQLException e) {
				XT.logE(e, "文章列表读取失败！");
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
		return articlelist;
	}
	
	public List<ArticleBean> queryByKeyword(String keyword) {
		List<ArticleBean> articlelist = new ArrayList<ArticleBean>();
		String sql = null;
		sql = "SELECT articleId, articleTypeId ,articleTitle, articleSummary, " 
			+ " articleContent, createDate, visitCount, createUserId " 
			+ " FROM Article WHERE articleTitle LIKE '%"
			+ keyword + "%' OR articleSummary LIKE '%"
			+ keyword + "%' OR articleContent LIKE '%"
			+ keyword +"%'";
		ResultSet rs = connection.executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					articleBean = new ArticleBean();
					articleBean.setArticleId(rs.getInt(1));
					articleBean.setArticleTypeId(rs.getInt(2));
					articleBean.setArticleTitle(rs.getString(3));
					articleBean.setArticleSummary(rs.getString(4));
					articleBean.setArticleContent(rs.getString(5));
					articleBean.setCreateDate(rs.getString(6));
					articleBean.setVisitCount(rs.getInt(7));
					articleBean.setCreateUserId(rs.getInt(8));

					articlelist.add(articleBean);
				}
			} catch (SQLException e) {
				XT.logE(e, "文章列表读取失败！");
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					XT.logE(e, "查询结果集关闭失败！");
				}
//				connection.close();
			}
		}
		connection.closeStm();
		return articlelist;
	}
	
	public static void main(String[] args) {
		ArticleDao ad = new ArticleDao();
		ArticleBean articleBean = new ArticleBean();
		// articleBean.setArticleId(rs.getInt(1));
		articleBean.setArticleTypeId(101);
		articleBean.setArticleTitle("tttt");
		articleBean.setArticleSummary("ssssssss");
		articleBean.setArticleContent("content.cc");
		articleBean.setCreateDate("2004-06-08 05:33:59");
		boolean b = ad.update("insert", articleBean);
		b = ad.update("insert", articleBean);

		// List l = ad.queryByArticleTypeId(-1);
		// System.out.println(((ArticleBean) l.get(2)).getCreateDate());
	}
}
