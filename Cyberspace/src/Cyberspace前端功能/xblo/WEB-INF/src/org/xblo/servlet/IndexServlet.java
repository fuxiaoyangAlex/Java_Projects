package org.xblo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xblo.bean.ArticleBean;
import org.xblo.bean.ArticleTypeBean;
import org.xblo.dao.ArticleDao;
import org.xblo.dao.ArticleTypeDao;

public class IndexServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// HttpSession session = request.getSession();
		ArticleDao articleDao = new ArticleDao();
		ArticleTypeDao articleTypeDao = new ArticleTypeDao();

		String strArticleTypeId = request.getParameter("articleTypeId");
		if (strArticleTypeId == null) {
			List<ArticleBean> articleList = articleDao
					.queryByArticleTypeId(ArticleDao.ALLTYPE);
			request.setAttribute("articleList", articleList);

			List<ArticleTypeBean> articleTypeList = articleTypeDao.queryAll();
			request.setAttribute("articleTypeList", articleTypeList);
		} else {
			int articleTypeId = Integer.parseInt(strArticleTypeId);
			List<ArticleBean> articleList = articleDao
					.queryByArticleTypeId(articleTypeId);
			request.setAttribute("articleList", articleList);

			List<ArticleTypeBean> articleTypeList = articleTypeDao.queryAll();
			request.setAttribute("articleTypeList", articleTypeList);
		}
		// response.getWriter().println("***DEBUG:articleList = " +
		// articleList);

		RequestDispatcher rd = request
				.getRequestDispatcher("/front/FrontIndex.jsp");
		rd.forward(request, response);
	}
}
