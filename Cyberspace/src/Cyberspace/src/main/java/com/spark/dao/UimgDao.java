package com.spark.dao;

/**
 * @version V1.0
 * @Author:Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.dao
 * @Description: TODO
 * @date Date : 2018-11-30  22:27
 */
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

public class UimgDao {

    /**
     *这个函数的功能是获取前端的数据集合，将文件打包成File以便后续操作
     */
    public static List<FileItem> getRequsetFileItems(HttpServletRequest request,ServletContext servletContext){

        //用于判断是普通表单，还是带文件上传的表单。
        boolean isMultipart=ServletFileUpload.isMultipartContent(request);

        if(isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
        //临时工作目录每一个servlet上下文都需要一个临时存储目录。Servlet容器必须为每一个servlet上下文提供一个私有的临时目录，
        //并且使它可以通过javax.servlet.context.tempdir上下文属性可用。这些属性关联的对象必须是java.io.File类型。</p>
        //强调要把临时文件放到不会被公开写入的地方。在servlet容器这，上下文context的javax.servlet.context.tempdir也许会被使用。
        //这些为了文件而创建的临时文件，之后应该被删除。实现这个的最好方式是FileCleaningTracker，你可以在DiskFileItemFactory中设置它
            String str="javax.servelet.context.tempdir";
            File repository=(File) servletContext.getAttribute(str);
            factory.setRepository(repository);
            ServletFileUpload upload=new ServletFileUpload(factory);
            try {
                //解析表单中的每一个表单项，封装成FileItem对象，以List方式返回。
                return upload.parseRequest(request);
            }catch (FileUploadException e) {
                // TODO: handle exception
                return null;
            }
        }else {
            return null;
        }
    }
    /**
     *
     *这个函数的功能是将文件传到预先设置的绝对路径中，也就是项目里的imgs文件夹
     */
    public static boolean saveFile(FileItem item,String fileName) {
        File savePath=new File("E:\\Cyberspace\\src\\main\\webapp\\images\\uimg");
        if(!savePath.exists()) {
            savePath.mkdirs();
        }
        File uploadFile=new File(savePath+File.separator+fileName);
        try{
            item.write(uploadFile);
            System.out.println("保存文件成功");
            return true;
        }catch(Exception e){
            System.out.println("保存文件失败");
        }
        return false;
    }
    /**
     *
     *这个函数的功能是获取当前时间点与1970年的间隔秒数
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        System.out.println(timestamp);
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     *
     *
     *这个函数的功能是得到新的照片名称
     */
    public static String getPhotoNewName() {
        Date date=new Date();
        int second=getSecondTimestamp(date);
        String fileName=String.valueOf(second)+".jpg";
        return fileName;
    }

    /**
     *
     *这个函数的功能是判断文件后缀是否是jpg格式
     */
    public static boolean isGif(FileItem item) {
        String fileFullName=item.getName();
        File fileInfo=new File(fileFullName);
        String suffix = fileInfo.getName().substring(fileInfo.getName().lastIndexOf(".") + 1);
        if(suffix.equals("jpg")) {
            return true;
        }
        return false;
    }
}