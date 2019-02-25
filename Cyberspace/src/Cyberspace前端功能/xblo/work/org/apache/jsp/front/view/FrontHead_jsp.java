package org.apache.jsp.front.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class FrontHead_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>博客-页头</title>\r\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/css/default.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- start header -->\r\n");
      out.write("<div id=\"feeds\"><a href=\"#\" id=\"entries-rss\">Entries (RSS)</a> <b>&nbsp;|&nbsp;</b> <a href=\"#\" id=\"comments-rss\">Comments (RSS)</a></div>\r\n");
      out.write("<hr />\r\n");
      out.write("<div id=\"logo\">\r\n");
      out.write("\t<h1><a href=\"index.jsp\">Xblo</a></h1>\r\n");
      out.write("\t<p><a href=\"http://blog.csdn.net/t0nsha\" target=_blank>珍惜每一天</a></p>\r\n");
      out.write("</div>\r\n");
      out.write("<hr />\r\n");
      out.write("<!-- end header -->\r\n");
      out.write("<!-- start menu -->\r\n");
      out.write("<div id=\"menu\">\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li class=\"active\"><a href=\"index.jsp\">首页</a></li>\r\n");
      out.write("\t\t<li><a href=\"ActionServlet?action=login\">管理</a></li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- end menu -->\r\n");
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
