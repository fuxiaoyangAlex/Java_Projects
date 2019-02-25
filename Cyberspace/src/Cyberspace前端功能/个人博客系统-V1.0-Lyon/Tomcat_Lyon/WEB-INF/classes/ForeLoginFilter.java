package blog.flowingsun.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import blog.flowingsun.bean.User;


public class ForeLoginFilter implements Filter{

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String contextPath=request.getServletContext().getContextPath();

        String[] noNeedLoginPage = new String[]{
                "foreHome",
                "LoginPage",
                "userRegister",
                "userLogin",
                "viewCatBlogs",
                "viewSingleBlog",
                "searchKeyword",
                "getTagarticles",
                };

        String uri = request.getRequestURI();
        uri =StringUtils.remove(uri, contextPath);
        if(uri.startsWith("/fore")&&!uri.startsWith("/foreServlet")){
            String method = StringUtils.substringAfterLast(uri,"/fore" );
            if(!Arrays.asList(noNeedLoginPage).contains(method)){
                User user =(User) request.getSession().getAttribute("user");
                if(null==user){
                    response.sendRedirect("jsp/userLogin.jsp");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
