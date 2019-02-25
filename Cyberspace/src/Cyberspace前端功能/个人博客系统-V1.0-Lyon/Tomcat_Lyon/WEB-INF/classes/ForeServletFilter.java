package blog.flowingsun.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

public class ForeServletFilter implements Filter {

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String contextPath = request.getServletContext().getContextPath();
        request.getServletContext().setAttribute("contextPath", contextPath);
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);

        if (uri.startsWith("/fore")) {//后台其他路径
            String method = StringUtils.substringAfterLast(uri,"/fore");
            request.setAttribute("method", method);
            req.getRequestDispatcher("/HomeServlet").forward(request, response);
            return;
        }else if(uri.equals("/Home")){
              String method = "foreHome";
              request.setAttribute("method", method);
              req.getRequestDispatcher("/HomeServlet").forward(request, response);
              return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}
