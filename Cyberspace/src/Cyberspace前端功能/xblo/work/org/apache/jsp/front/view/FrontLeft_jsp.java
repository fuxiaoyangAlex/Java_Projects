package org.apache.jsp.front.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.xblo.bean.*;
import org.xblo.dao.*;
import java.util.Iterator;

public final class FrontLeft_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	ArticleDao articleDao = new ArticleDao();

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>博客-侧栏</title>\r\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/css/default.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" media=\"screen\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"sidebar\">\r\n");
      out.write("<ul>\r\n");
      out.write("\t<li id=\"search\">\r\n");
      out.write("\t<h2>搜索</h2>\r\n");
      out.write("\t<!-- Google站内搜索开始 -->\r\n");
      out.write("\t<form method=get action=\"http://www.google.com/search\">\r\n");
      out.write("\t<fieldset><input type=text name=q> <input\r\n");
      out.write("\t\ttype=submit name=btnG value=\"Google\"> <input type=hidden\r\n");
      out.write("\t\tname=ie value=UTF-8> <input type=hidden name=oe value=UTF-8>\r\n");
      out.write("\t<input type=hidden name=hl value=zh-CN> <input type=hidden\r\n");
      out.write("\t\tname=domains value=\"");
      out.print(basePath);
      out.write("\"> <input type=hidden\r\n");
      out.write("\t\tname=sitesearch value=\"");
      out.print(basePath);
      out.write("\"></fieldset>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<!-- Google 站内搜索结束 --></li>\r\n");
      out.write("\t<li>\r\n");
      out.write("\t<h2>排行</h2>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t");

			List<ArticleBean> articleListTop = articleDao
					.queryTopOnVisitCount(10);
			if (articleListTop.size() > 0) {
				for (int i = 0; i < articleListTop.size(); i++) {
					ArticleBean articleBean = (ArticleBean) articleListTop
							.get(i);
		
      out.write("\r\n");
      out.write("\t\t<li><a\r\n");
      out.write("\t\t\thref=\"ActionServlet?action=viewArticle&articleId=");
      out.print(articleBean.getArticleId());
      out.write('"');
      out.write('>');
      out.print(articleBean.getArticleTitle());
      out.write("</a>(");
      out.print(articleBean.getVisitCount());
      out.write(")</li>\r\n");
      out.write("\t\t");

			}
			}
		
      out.write("\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t</li>\r\n");
      out.write("\t<li>\r\n");
      out.write("\t<h2>分类</h2>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t");

			ArticleTypeDao articleTypeDao = new ArticleTypeDao();
			List<ArticleTypeBean> articleTypeList = articleTypeDao.queryAll();
			if (articleTypeList.size() > 0) {
				for (int i = 0; i < articleTypeList.size(); i++) {
					ArticleTypeBean articleTypeBean = (ArticleTypeBean) articleTypeList
							.get(i);
					List<ArticleBean> articleList = articleDao
							.queryByArticleTypeId(articleTypeBean
									.getArticleTypeId());
		
      out.write("\r\n");
      out.write("\t\t<li><a\r\n");
      out.write("\t\t\thref=\"IndexServlet?articleTypeId=");
      out.print(articleTypeBean.getArticleTypeId());
      out.write('"');
      out.write('>');
      out.print(articleTypeBean.getArticleTypeName());
      out.write("</a>(");
      out.print(articleList.size());
      out.write(")</li>\r\n");
      out.write("\t\t");

			}
			}
		
      out.write("\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t</li>\r\n");
      out.write("\t<li>\r\n");
      out.write("\t<h2>链接</h2>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t");

			XbloLinkDao xbloLinkDao = new XbloLinkDao();
			List<XbloLinkBean> xbloLinkList = xbloLinkDao.queryAll();
			if (xbloLinkList.size() > 0) {
				for (int i = 0; i < xbloLinkList.size(); i++) {
					XbloLinkBean xbloLinkBean = (XbloLinkBean) xbloLinkList
							.get(i);
		
      out.write("\r\n");
      out.write("\t\t<li><a href=\"http://");
      out.print(xbloLinkBean.getXbloLinkUrl());
      out.write("\"\r\n");
      out.write("\t\t\ttarget=_blank>");
      out.print(xbloLinkBean.getXbloLinkName());
      out.write("</a></li>\r\n");
      out.write("\t\t");

			}
			}
		
      out.write("\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t</li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
