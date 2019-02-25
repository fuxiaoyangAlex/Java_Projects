package org.img.servlet;

import java.io.File;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
/*import org.apache.commons.fileupload.FileItemFactory;*/
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.img.dao.ImageDao;
import org.img.domain.Image;
import org.img.factory.ImgDaoFactory;


@WebServlet(
		urlPatterns= {"/UploadServlet"}
		)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ImageDao dao=null;
		int use_id=7;
		String sname=null;
		FileItem item=null;
		// 上传
		try {	
				//首先要判断客户端<form>标记的enctype属性是否是“multipart/form-data"
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				//设置环境:创建一个DiskFileItemFactory工厂    
				//FileItemFactory factory = new DiskFileItemFactory();
				DiskFileItemFactory factory = new DiskFileItemFactory();
				 //2、核心操作类:创建一个文件上传解析器。
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 通过parseRequest解析form中的所有请求字段，并保存到items集合
				List<FileItem> items = upload.parseRequest(request);
				// 遍历items中是数据（sno sname spicture）
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					item = iter.next();
									//获取普通表单字段的名字					
					String itemName = item.getFieldName();
					
					// 全段前端字段，是普通form表单，还是文件
					//request.getParameter() --- iter.getString();
					if (item.isFormField()) {
						/*if (itemName.equals("sno")) {// 根据name属性怕那段Item是sno sname 还是pict
							sno = Integer.parseInt(item.getString("UTF-8"));
							sno = Integer.parseInt(item.getString());	*/					
						if (itemName.equals("sname")) {
							sname = item.getString();
							System.out.println("sname="+sname);
						}else if(itemName.equals("use_id")) {
							use_id = Integer.parseInt(item.getString());	
							System.out.println("use_id="+use_id);
						}else{
							System.out.println("其他字段");
						}		
					}else{
						//文件上传
						//文件名getFieldName是获取普通的表单字段
						//getNme()获取文件名
						//控制文件上传类型
						String fileName=item.getName();//a.txt  a.docx,png 
						String ext=fileName.substring(fileName.indexOf(".")+1);
						if(!(ext.equals("png")||ext.equals("gif") || ext.equals("jpg"))) {
							System.out.println("上传图片格式有误！");
							return;
						}
						//获取文件内容并上传
						//指定上传位置(服务区路径)
						//获取服务器路径 
						String path="D:\\OpenSources\\IDEeclipse_workplace\\eclipse-workspace\\UpAndDown\\WebContent\\image";
						File file = new File(path,fileName);
						//设置上传文件时，用到的临时目录 DiskFileItemFactory
						factory.setSizeThreshold(10240);
						factory.setRepository(new File("E:\\uploadtep"));//设置临时目录
						
						//控制单个上传文件的大小20kb   ServletFileUpload 
						upload.setSizeMax(20480);//字节B
						item.write(file);//上传
						System.out.println(fileName+"上传成功！");
					}
				}
			}
		}catch(FileUploadBase.SizeLimitExceededException e){
		System.out.println("上传文件大小超过限制");	
		} 
		catch (FileUploadException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();		
		}	
		//将前台传来的数据进行保存
		Image image = new Image();
		image.setImg_name(sname);
		image.setImg_address("image/"+item.getName());
		image.setUse_id(use_id);		
		dao=ImgDaoFactory.getImgDaoInstance();
		int res =dao.save(image);
		
//		获取新传入图片的ID
//		int imgID=dao.getImgID();
//		
//		获取某个人所有对象的属性
//		Use_img use = new Use_img();
//		use.setId(use_id);
		List<Image> list = dao.getAll(use_id);
		if(res>=1) {
			HttpSession session = request.getSession();
			session.setAttribute("List",list);
			session.setAttribute("address",image.getImg_address());
			request.getRequestDispatcher("mood1.jsp").forward(request, response);
		}
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
