package blog.flowingsun.servlet;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.flowingsun.dao.ArticleDAO;
import blog.flowingsun.dao.CategaryDAO;
import blog.flowingsun.dao.CommentDAO;

public abstract class BaseBackServlet extends HttpServlet{
    public abstract String writeBlog(HttpServletRequest request, HttpServletResponse response);
    public abstract String submitBlog(HttpServletRequest request, HttpServletResponse response);
    public abstract String deleteBlog(HttpServletRequest request, HttpServletResponse response);
    public abstract String editorBlog(HttpServletRequest request, HttpServletResponse response);
    public abstract String rewriteSubmit(HttpServletRequest request, HttpServletResponse response);
    public abstract String linkQuery(HttpServletRequest request, HttpServletResponse response);
    public abstract String categaryQuery(HttpServletRequest request, HttpServletResponse response);
    public abstract String pageSizeQuery(HttpServletRequest request, HttpServletResponse response);
    public abstract String adminHome(HttpServletRequest request, HttpServletResponse response);
    public abstract String queryCategary(HttpServletRequest request, HttpServletResponse response);
    public abstract String editTags(HttpServletRequest request, HttpServletResponse response);

    protected CategaryDAO CategaryDAO = new CategaryDAO();
    protected ArticleDAO ArticleDAO = new ArticleDAO();
    protected CommentDAO CommentDAO = new CommentDAO();

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
