package org.xblo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xblo.util.DBC;
import org.xblo.util.XT;
import org.xblo.bean.ArticleCommentBean;

public class ArticleCommentDao {
	private DBC connection = null;
	private ArticleCommentBean articleCommentBean = null;

	public ArticleCommentDao() {
		connection = new DBC();
	}

	public boolean update(String code, ArticleCommentBean articleComment) {
		String sql = null;
		if (code.equals("insert"))
			sql = "INSERT INTO articlecomment(\n" + "articlecommentid,\n"
					+ "articleid,\n" + "articlecommentdate,\n"
					+ "articlecommentuser,\n" + "articlecommentemail,\n"
					+ "articlecommentcontent) VALUES ("
					+ " ARTICLECOMMENT_S1.Nextval " + ",'"
					+ articleComment.getArticleId() + "'," + " SYSDATE " + ",'"
					+ articleComment.getArticleCommentUser() + "','"
					+ articleComment.getArticleCommentEmail() + "','"
					+ articleComment.getCommentContent() + "')";
		if (code.equals("delete"))
			sql = "delete from articlecomment where articlecommentid = "
					+ articleComment.getArticleCommentId()
					+ " and articleid = " + articleComment.getArticleId();
		boolean flag = connection.executeUpdate(sql);
		connection.closeStm();
		return flag;
	}

	public List<ArticleCommentBean> queryByArticleId(int articleId) {
		List<ArticleCommentBean> articleCommentList = new ArrayList<ArticleCommentBean>();
		String sql = null;
		sql = "SELECT articlecommentid\n" + "      ,articleid\n"
				+ "      ,articlecommentdate\n" + "      ,articlecommentuser\n"
				+ "      ,articlecommentemail\n"
				+ "      ,articlecommentcontent\n" + "  FROM articlecomment\n"
				+ " WHERE articleid = \n" + articleId
				+ " ORDER BY articlecommentdate ASC";
		ResultSet rs = connection.executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					articleCommentBean = new ArticleCommentBean();
					articleCommentBean.setArticleCommentId(rs.getInt(1));
					articleCommentBean.setArticleId(rs.getInt(2));
					articleCommentBean.setArticleCommentDate(rs.getString(3));
					articleCommentBean.setArticleCommentUser(rs.getString(4));
					articleCommentBean.setArticleCommentEmail(rs.getString(5));
					articleCommentBean.setCommentContent(rs.getString(6));
					articleCommentList.add(articleCommentBean);
				}
			} catch (SQLException e) {
				XT.logE(e, "评论列表读取失败！");
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
		return articleCommentList;
	}

	public static void main(String[] args) {
		ArticleCommentBean acb = new ArticleCommentBean();
		acb.setArticleCommentId(1);
		acb.setArticleId(10);
		acb.setArticleCommentDate("2010/08/28 01:39:12");
		acb.setArticleCommentEmail("cmm@c.c");
		acb.setArticleCommentUser("ccuser");
		acb.setCommentContent("ccontetcccc");
		new ArticleCommentDao().update("insert", acb);
	}
}
