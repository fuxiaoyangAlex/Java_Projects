package blog.flowingsun.servlet;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.flowingsun.dao.ArticleDAO;
import blog.flowingsun.dao.CategaryDAO;
import blog.flowingsun.dao.UserDAO;
import blog.flowingsun.dao.CommentDAO;

public abstract class BaseForeServlet extends HttpServlet{

    public abstract String foreHome(HttpServletRequest request, HttpServletResponse response);
    public abstract String LoginPage(HttpServletRequest request, HttpServletResponse response);
    public abstract String userRegister(HttpServletRequest request, HttpServletResponse response);
    public abstract String userLogin(HttpServletRequest request, HttpServletResponse response);
    public abstract String viewCatBlogs(HttpServletRequest request, HttpServletResponse response);
    public abstract String viewSingleBlog(HttpServletRequest request, HttpServletResponse response);

    protected CategaryDAO CategaryDAO = new CategaryDAO();
    protected ArticleDAO ArticleDAO = new ArticleDAO();
    protected CommentDAO CommentDAO = new CommentDAO();
    protected UserDAO UserDAO = new UserDAO();

    public void service(HttpServletRequest request,HttpServletResponse response){
        try{
            List categarys = CategaryDAO.getMainCategarys();
            List secondcats = CategaryDAO.getSecondCategarys();
            request.setAttribute("categarys",categarys);
            request.setAttribute("secondcats",secondcats);
            /*借住反射，调用AdminhomeServlet中对应的方法*/
            String method = (String) request.getAttribute("method");
            Method m = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
            String redirect = m.invoke(this,request,response).toString();
            /*根据方法的返回值，进行相应的客户端跳转*/
            if(redirect.startsWith("%")){
                response.getWriter().print(redirect.substring(1));
            }else{
                request.getRequestDispatcher(redirect).forward(request, response);
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
