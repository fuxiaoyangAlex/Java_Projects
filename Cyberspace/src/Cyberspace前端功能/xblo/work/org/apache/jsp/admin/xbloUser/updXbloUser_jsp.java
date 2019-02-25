package org.apache.jsp.admin.xbloUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.xblo.bean.*;
import org.xblo.dao.*;
import java.util.Iterator;

public final class updXbloUser_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("/admin/js.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");

	XbloUserBean xbloUserBean = (XbloUserBean) request
			.getAttribute("xbloUserBean");
	XbloUserBean validXbloUserBean = (XbloUserBean) session
			.getAttribute("validXbloUserBean");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<body id=\"page\">\r\n");
      out.write("<h2>修改密码</h2>\r\n");
      out.write("<form action=\"");
      out.print(basePath);
      out.write("/ActionServlet?action=updXbloUser\"\r\n");
      out.write("\tmethod=\"post\">\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"updXbloUser\" value=\"Y\">\r\n");
      out.write("\t</input>\r\n");
      out.write("\t<input type=\"hidden\" name=\"xbloUserId\"\r\n");
      out.write("\t\tvalue=\"");
      out.print(xbloUserBean.getXbloUserId());
      out.write("\">\r\n");
      out.write("\t</input>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<th>用户名：</th>\r\n");
      out.write("\t\t<td><input type=\"text\" name=\"xbloUsername\" size=\"50\"\r\n");
      out.write("\t\t\treadonly=\"readonly\" value=\"");
      out.print(xbloUserBean.getXbloUsername());
      out.write("\"></input></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<th>新密码：</th>\r\n");
      out.write("\t\t<td><input type=\"password\" id=\"xbloPassword1\" name=\"xbloPassword\"\r\n");
      out.write("\t\t\tsize=\"50\"></input></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<th>重复新密码：</th>\r\n");
      out.write("\t\t<td><input type=\"password\" id=\"xbloPassword2\" name=\"xbloPassword\"\r\n");
      out.write("\t\t\tsize=\"50\"></input></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<th>&nbsp;</th>\r\n");
      out.write("\t\t<td><input type=\"submit\" class=\"bt\" value=\"提交\"\r\n");
      out.write("\t\t\tonclick=\"return chkPwdEql()\"></input></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
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
