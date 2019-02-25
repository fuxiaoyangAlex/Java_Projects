package org.img.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownUploadServlet")
public class DownUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public DownUploadServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		String filename=request.getParameter("filename");
		//获取需要下载的文件名			//万能类型（任意文件）
	  	response.addHeader("contentType","application/octet-stream");//filename包含了后缀，a.tet     给文件名转码 防乱码
	  	response.addHeader("content-Disposition","attachment;filename="+filename+URLEncoder.encode(filename,"UTF-8"));
		
	  	//Servlet通过文件的地址 将文件转为输入流 读到Servlet中
	  InputStream in=getServletContext().getResourceAsStream("/image/"+filename);
	  
	  	//通过输出流将刚才的文件转给用户
	  ServletOutputStream out = response.getOutputStream();
	  	byte[] bs = new byte[10];
	  	int len=-1;
	  	while((len=in.read(bs))!=-1) {
	  		out.write(bs,0,len);
	  	}
	  	out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
