package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.xblo.bean.*;
import org.xblo.dao.*;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<title>管理界面</title>\r\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/admin/style.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"js.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");

	XbloUserBean validXbloUserBean = (XbloUserBean) session
			.getAttribute("validXbloUserBean");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<body id=\"index\">\r\n");
      out.write("<h1>欢迎你，");
      out.print(validXbloUserBean.getXbloUsername());
      out.write("</h1>\r\n");
      out.write("<ul id=\"globalNav\">\r\n");
      out.write("\t<li><a href=\"");
      out.print(basePath);
      out.write("/admin/siteInfo.jsp\" target=\"frameBord\">站点信息</a></li>\r\n");
      out.write("\t<li><a href=\"");
      out.print(basePath);
      out.write("/admin/article/addArticle.jsp\"\r\n");
      out.write("\t\ttarget=\"frameBord\">发表文章</a></li>\r\n");
      out.write("\t<li><a href=\"");
      out.print(basePath);
      out.write("/admin/article/mgrArticle.jsp\"\r\n");
      out.write("\t\ttarget=\"frameBord\">管理文章</a></li>\r\n");
      out.write("\t<li><a href=\"");
      out.print(basePath);
      out.write("/admin/articleType/mgrArticleType.jsp\"\r\n");
      out.write("\t\ttarget=\"frameBord\">管理类别</a></li>\r\n");
      out.write("\t<li><a href=\"");
      out.print(basePath);
      out.write("/admin/xbloLink/mgrXbloLink.jsp\"\r\n");
      out.write("\t\ttarget=\"frameBord\">管理链接</a></li>\r\n");
      out.write("\t<li><a href=\"");
      out.print(basePath);
      out.write("/admin/xbloUser/mgrXbloUser.jsp\"\r\n");
      out.write("\t\ttarget=\"frameBord\">管理用户</a></li>\r\n");
      out.write("\t<li><a href=\"ActionServlet?action=logout\">退出</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("<iframe id=\"frameBord\" name=\"frameBord\" frameborder=\"0\" width=\"100%\"\r\n");
      out.write("\theight=\"100%\" src=\"");
      out.print(basePath);
      out.write("/admin/siteInfo.jsp\"></iframe>\r\n");
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
