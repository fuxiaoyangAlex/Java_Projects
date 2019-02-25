package org.apache.jsp.front;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.xblo.bean.*;
import org.xblo.dao.*;

public final class FrontIndex_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      out.write("<!--\r\n");
      out.write("\r\n");
      out.write("Design by Free CSS Templates\r\n");
      out.write("http://www.freecsstemplates.org\r\n");
      out.write("Released for free under a Creative Commons Attribution 2.5 License\r\n");
      out.write("\r\n");
      out.write("Title      : Artificial\r\n");
      out.write("Version    : 1.0\r\n");
      out.write("Released   : 20070808\r\n");
      out.write("Description: A two-column, fixed-width with fluid header ideal for 1024x768 resolutions. Suitable for blogs and small websites.\r\n");
      out.write("\r\n");
      out.write("-->\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>Xblo首页</title>\r\n");
      out.write("<meta name=\"keywords\" content=\"\" />\r\n");
      out.write("<meta name=\"description\" content=\"\" />\r\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/css/default.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" media=\"screen\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<!-- start header -->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/front/view/FrontHead.jsp", out, false);
      out.write("\r\n");
      out.write("<!-- end header -->\r\n");
      out.write("\r\n");
      out.write("<!-- start page -->\r\n");
      out.write("<div id=\"page\"><!-- start content -->\r\n");
      out.write("<div id=\"content\">\r\n");

	List<ArticleBean> articleList = (List<ArticleBean>) request
			.getAttribute("articleList");
	if (articleList == null || articleList.size() == 0) {

      out.write("\r\n");
      out.write("<div class=\"post\">\r\n");
      out.write("<h1 class=\"title\">欢迎使用Xblo!</h1>\r\n");
      out.write("<p class=\"meta\"><small>当前类别下博主还未发表文章</small></p>\r\n");
      out.write("<div class=\"entry\">\r\n");
      out.write("<p>精彩即将来临...</p>\r\n");
      out.write("<p class=\"more\"><a href=\"#\">阅读全文&hellip;</a></p>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");

	} else {
		int i = 0;
		ArticleCommentDao articleCommentDao = new ArticleCommentDao();
		XbloUserDao xbloUserDao = new XbloUserDao();
		ArticleTypeDao articleTypeDao = new ArticleTypeDao();

		String username = "";
		int commentCount = 0;
		while (i < articleList.size()) {
			ArticleBean articleBean = (ArticleBean) articleList.get(i);
			XbloUserBean xbloUserBean = xbloUserDao
					.queryByXbloUserId(articleBean.getCreateUserId());
			username = xbloUserBean.getXbloUsername();
			List<ArticleCommentBean> articleCommentList = articleCommentDao
					.queryByArticleId(articleBean.getArticleId());
			if (articleCommentList == null)
				commentCount = 0;
			else
				commentCount = articleCommentList.size();
			ArticleTypeBean articleTypeBean = articleTypeDao
					.query(articleBean.getArticleTypeId());
			String articleTypeName = null;
			if (articleTypeBean == null)
				articleTypeName = "未分类";
			else
				articleTypeName = articleTypeBean.getArticleTypeName();

      out.write("\r\n");
      out.write("<div class=\"post\">\r\n");
      out.write("<h2 class=\"title\">[");
      out.print(articleTypeName);
      out.write(']');
      out.print(articleBean.getArticleTitle());
      out.write("</h2>\r\n");
      out.write("<p class=\"meta\"><small>");
      out.print(username);
      out.write(" 发布于 ");
      out.print(articleBean.getCreateDate());
      out.write("\r\n");
      out.write("<b>&nbsp;|&nbsp;</b> 阅读 (");
      out.print(articleBean.getVisitCount());
      out.write(") <b>&nbsp;|&nbsp;</b>\r\n");
      out.write("<a\r\n");
      out.write("\thref=\"ActionServlet?action=viewArticle&articleId=");
      out.print(articleBean.getArticleId());
      out.write("#FeedBack\">评论\r\n");
      out.write("(");
      out.print(commentCount);
      out.write(")</a></small></p>\r\n");
      out.write("<div class=\"entry\">\r\n");
      out.write("<p>");
      out.print(articleBean.getArticleSummary());
      out.write("</p>\r\n");
      out.write("<p class=\"more\"><a\r\n");
      out.write("\thref=\"ActionServlet?action=viewArticle&articleId=");
      out.print(articleBean.getArticleId());
      out.write("\">阅读全文&hellip;</a></p>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");

	i++;
		}
	}

      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<!-- end content --> <!-- start sidebar --> ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/front/view/FrontLeft.jsp", out, false);
      out.write(" <!-- end sidebar -->\r\n");
      out.write("\r\n");
      out.write("<div style=\"clear: both;\">&nbsp;</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- end page -->\r\n");
      out.write("\r\n");
      out.write("<!-- start footer -->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/front/view/FrontFoot.jsp", out, false);
      out.write("\r\n");
      out.write("<!-- end footer -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
