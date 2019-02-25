package org.xblo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xblo.bean.ArticleBean;
import org.xblo.bean.ArticleTypeBean;
import org.xblo.bean.ArticleCommentBean;
import org.xblo.bean.XbloLinkBean;
import org.xblo.bean.XbloUserBean;
import org.xblo.dao.ArticleDao;
import org.xblo.dao.ArticleTypeDao;
import org.xblo.dao.ArticleCommentDao;
import org.xblo.dao.XbloLinkDao;
import org.xblo.dao.XbloUserDao;

public class ActionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		if (action.equals("viewArticle"))
			this.viewArticle(request, response);
		if (action.equals("updArticle"))
			this.updArticle(request, response);
		if (action.equals("delArticle"))
			this.delArticle(request, response);
		if (action.equals("comment"))
			this.comment(request, response);
		if (action.equals("login"))
			this.login(request, response);
		if (action.equals("logout"))
			this.logout(request, response);
		if (action.equals("addArticle"))
			this.addArticle(request, response);
		if (action.equals("addArticleType"))
			this.addArticleType(request, response);
		if (action.equals("updArticleType"))
			this.updArticleType(request, response);
		if (action.equals("delArticleType"))
			this.delArticleType(request, response);
		if (action.equals("addXbloLink"))
			this.addXbloLink(request, response);
		if (action.equals("updXbloLink"))
			this.updXbloLink(request, response);
		if (action.equals("delXbloLink"))
			this.delXbloLink(request, response);
		if (action.equals("addXbloUser"))
			this.addXbloUser(request, response);
		if (action.equals("updXbloUser"))
			this.updXbloUser(request, response);
		if (action.equals("delXbloUser"))
			this.delXbloUser(request, response);
		if (action.equals("searchArticle"))
			this.searchArticle(request, response);
	}

	public void searchArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		String keyword = request.getParameter("keyword");
		ArticleDao articleDao = new ArticleDao();
		List<ArticleBean> searchArticleList = articleDao
				.queryByKeyword(keyword);

		request.setAttribute("searchArticleList", searchArticleList);

		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/siteInfo.jsp");
		rd.forward(request, response);
	}

	public void delXbloUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int xbloUserId = Integer.parseInt(request.getParameter("xbloUserId"));
		XbloUserDao xbloUserDao = new XbloUserDao();
		XbloUserBean xbloUserBean = xbloUserDao.queryByXbloUserId(xbloUserId);
		xbloUserDao.update("delete", xbloUserBean);
		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/xbloUser/mgrXbloUser.jsp");
		rd.forward(request, response);
	}

	public void updXbloUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int xbloUserId = Integer.parseInt(request.getParameter("xbloUserId"));
		XbloUserDao xbloUserDao = new XbloUserDao();
		String updXbloUser = request.getParameter("updXbloUser");
		if (updXbloUser == null) {
			XbloUserBean xbloUserBean = xbloUserDao
					.queryByXbloUserId(xbloUserId);
			request.setAttribute("xbloUserBean", xbloUserBean);
			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/xbloUser/updXbloUser.jsp");
			rd.forward(request, response);
		} else {
			String xbloUsername = request.getParameter("xbloUsername");
			String xbloPassword = request.getParameter("xbloPassword");

			XbloUserBean xbloUserBean = new XbloUserBean();
			xbloUserBean.setXbloUserId(xbloUserId);
			xbloUserBean.setXbloUsername(xbloUsername);
			xbloUserBean.setXbloPassword(xbloPassword);

			xbloUserDao.update("update", xbloUserBean);

			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/xbloUser/mgrXbloUser.jsp");
			rd.forward(request, response);
		}
	}

	public void addXbloUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String xbloUsername = request.getParameter("xbloUsername");
		String xbloPassword = request.getParameter("xbloPassword");

		// response.getWriter().println("articleTypeName:" + articleTypeName);

		XbloUserBean xbloUserBean = new XbloUserBean();
		xbloUserBean.setXbloUsername(xbloUsername);
		xbloUserBean.setXbloPassword(xbloPassword);

		XbloUserDao xbloUserDao = new XbloUserDao();
		xbloUserDao.update("insert", xbloUserBean);

		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/xbloUser/mgrXbloUser.jsp");
		rd.forward(request, response);
	}

	public void delXbloLink(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int xbloLinkId = Integer.parseInt(request.getParameter("xbloLinkId"));
		XbloLinkDao xbloLinkDao = new XbloLinkDao();
		XbloLinkBean xbloLinkBean = xbloLinkDao.query(xbloLinkId);
		xbloLinkDao.update("delete", xbloLinkBean);
		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/xbloLink/mgrXbloLink.jsp");
		rd.forward(request, response);
	}

	public void updXbloLink(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int xbloLinkId = Integer.parseInt(request.getParameter("xbloLinkId"));
		XbloLinkDao xbloLinkDao = new XbloLinkDao();
		String updXbloLink = request.getParameter("updXbloLink");
		if (updXbloLink == null) {
			XbloLinkBean xbloLinkBean = xbloLinkDao.query(xbloLinkId);
			request.setAttribute("xbloLinkBean", xbloLinkBean);
			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/xbloLink/updXbloLink.jsp");
			rd.forward(request, response);
		} else {
			String xbloLinkName = request.getParameter("xbloLinkName");
			String xbloLinkUrl = request.getParameter("xbloLinkUrl");

			XbloLinkBean xbloLinkBean = new XbloLinkBean();
			xbloLinkBean.setXbloLinkId(xbloLinkId);
			xbloLinkBean.setXbloLinkName(xbloLinkName);
			xbloLinkBean.setXbloLinkUrl(xbloLinkUrl);

			xbloLinkDao.update("update", xbloLinkBean);

			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/xbloLink/mgrXbloLink.jsp");
			rd.forward(request, response);
		}
	}

	public void addXbloLink(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String xbloLinkName = request.getParameter("xbloLinkName");
		String xbloLinkUrl = request.getParameter("xbloLinkUrl");

		// response.getWriter().println("articleTypeName:" + articleTypeName);

		XbloLinkBean xbloLinkBean = new XbloLinkBean();
		xbloLinkBean.setXbloLinkName(xbloLinkName);
		xbloLinkBean.setXbloLinkUrl(xbloLinkUrl);

		XbloLinkDao xbloLinkDao = new XbloLinkDao();
		xbloLinkDao.update("insert", xbloLinkBean);

		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/xbloLink/mgrXbloLink.jsp");
		rd.forward(request, response);
	}

	public void delArticleType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int articleTypeId = Integer.parseInt(request
				.getParameter("articleTypeId"));
		ArticleTypeDao articleTypeDao = new ArticleTypeDao();
		ArticleTypeBean articleTypeBean = articleTypeDao.query(articleTypeId);
		articleTypeDao.update("delete", articleTypeBean);
		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/articleType/mgrArticleType.jsp");
		rd.forward(request, response);
	}

	public void updArticleType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int articleTypeId = Integer.parseInt(request
				.getParameter("articleTypeId"));
		ArticleTypeDao articleTypeDao = new ArticleTypeDao();
		String updArticleType = request.getParameter("updArticleType");
		if (updArticleType == null) {
			ArticleTypeBean articleTypeBean = articleTypeDao
					.query(articleTypeId);
			request.setAttribute("articleTypeBean", articleTypeBean);
			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/articleType/updArticleType.jsp");
			rd.forward(request, response);
		} else {
			String articleTypeName = request.getParameter("articleTypeName");
			String articleTypeDesc = request.getParameter("articleTypeDesc");

			ArticleTypeBean articleTypeBean = new ArticleTypeBean();
			articleTypeBean.setArticleTypeId(articleTypeId);
			articleTypeBean.setArticleTypeName(articleTypeName);
			articleTypeBean.setArticleTypeDesc(articleTypeDesc);

			articleTypeDao.update("update", articleTypeBean);

			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/articleType/mgrArticleType.jsp");
			rd.forward(request, response);
		}
	}

	public void addArticleType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String articleTypeName = request.getParameter("articleTypeName");
		String articleTypeDesc = request.getParameter("articleTypeDesc");

		// response.getWriter().println("articleTypeName:" + articleTypeName);

		ArticleTypeBean articleTypeBean = new ArticleTypeBean();
		articleTypeBean.setArticleTypeName(articleTypeName);
		articleTypeBean.setArticleTypeDesc(articleTypeDesc);

		ArticleTypeDao articleTypeDao = new ArticleTypeDao();
		articleTypeDao.update("insert", articleTypeBean);

		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/articleType/mgrArticleType.jsp");
		rd.forward(request, response);
	}

	public void addArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String articleTitle = request.getParameter("articleTitle");
		String articleSummary = request.getParameter("articleSummary");
		String articleContent = request.getParameter("articleContent");
		int articleTypeId = Integer.parseInt(request
				.getParameter("articleTypeId"));
		int createUserId = Integer.parseInt(request
				.getParameter("createUserId"));
		// response.getWriter().println("articleTitle:" + articleTitle);

		ArticleBean articleBean = new ArticleBean();
		articleBean.setArticleTypeId(articleTypeId);
		articleBean.setArticleTitle(articleTitle);
		articleBean.setArticleSummary(articleSummary);
		articleBean.setArticleContent(articleContent);
		articleBean.setCreateUserId(createUserId);

		ArticleDao articleDao = new ArticleDao();
		articleDao.update("insert", articleBean);

		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/siteInfo.jsp");
		rd.forward(request, response);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
		rd.forward(request, response);
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		String xbloUsername = request.getParameter("xbloUsername");
		String xbloPassword = request.getParameter("xbloPassword");

		XbloUserBean xbloUserBean = new XbloUserBean();
		xbloUserBean.setXbloUsername(xbloUsername);
		xbloUserBean.setXbloPassword(xbloPassword);

		XbloUserDao XbloUserDao = new XbloUserDao();
		XbloUserBean validXbloUserBean = null;
		validXbloUserBean = XbloUserDao.getValidXbloUser(xbloUserBean);

		String forward = "";
		if (validXbloUserBean != null) {
			request.getSession().setAttribute("validXbloUserBean",
					validXbloUserBean);
			forward = "/admin/admin.jsp";
		} else {
			forward = "/admin/login.jsp";
		}

		// response.getWriter().println("forward:" + forward);
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}

	public void comment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int articleId = Integer.parseInt(request.getParameter("articleId"));

		ArticleCommentBean articleCommentBean = new ArticleCommentBean();
		articleCommentBean.setArticleId(articleId);
		String articleCommentUser = request.getParameter("articleCommentUser");
		articleCommentBean.setArticleCommentUser(articleCommentUser);
		String articleCommentEmail = request
				.getParameter("articleCommentEmail");
		articleCommentBean.setArticleCommentEmail(articleCommentEmail);
		String articleCommentContent = request
				.getParameter("articleCommentContent");
		articleCommentBean.setCommentContent(articleCommentContent);

		ArticleCommentDao articleCommentDao = new ArticleCommentDao();
		articleCommentDao.update("insert", articleCommentBean);

		ArticleDao articleDao = new ArticleDao();
		ArticleBean articleBean = articleDao.queryByArticleId(articleId);
		request.setAttribute("articleBean", articleBean);

		RequestDispatcher rd = request
				.getRequestDispatcher("/front/article/viewArticle.jsp");
		rd.forward(request, response);
	}

	public void delArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		ArticleDao articleDao = new ArticleDao();
		ArticleBean articleBean = articleDao.queryByArticleId(articleId);
		articleDao.update("delete", articleBean);

		RequestDispatcher rd = request
				.getRequestDispatcher("/admin/article/mgrArticle.jsp");
		rd.forward(request, response);
	}

	public void updArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		ArticleDao articleDao = new ArticleDao();
		String updArticle = request.getParameter("updArticle");
		if (updArticle == null) {
			ArticleBean articleBean = articleDao.queryByArticleId(articleId);
			request.setAttribute("articleBean", articleBean);

			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/article/updArticle.jsp");
			rd.forward(request, response);
		} else {
			String articleTitle = request.getParameter("articleTitle");
			String articleSummary = request.getParameter("articleSummary");
			String articleContent = request.getParameter("articleContent");
			int createUserId = Integer.parseInt(request
					.getParameter("createUserId"));

			ArticleBean articleBean = new ArticleBean();
			articleBean.setArticleId(articleId);
			articleBean.setArticleTitle(articleTitle);
			articleBean.setArticleSummary(articleSummary);
			articleBean.setArticleContent(articleContent);
			articleBean.setCreateUserId(createUserId);

			articleDao.update("update", articleBean);

			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/article/mgrArticle.jsp");
			rd.forward(request, response);
		}
	}

	public void viewArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().println("ViewArticle");
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		ArticleDao articleDao = new ArticleDao();
		ArticleBean articleBean = articleDao.queryByArticleId(articleId);

		// 增加阅读次数
		articleBean.setVisitCount(articleBean.getVisitCount() + 1);
		articleDao.update("update", articleBean);

		request.setAttribute("articleBean", articleBean);

		RequestDispatcher rd = request
				.getRequestDispatcher("/front/article/viewArticle.jsp");
		rd.forward(request, response);
	}

}
