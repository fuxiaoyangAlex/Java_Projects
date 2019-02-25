package com.spark.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author:Wangzhuang2
 * @Description:
 * @Date:2018/11/28
 */
@WebServlet(name = "VerificationcodeServlet", urlPatterns = {"/verificationCodeServlet.do"})
public class VerificationcodeServlet extends HttpServlet {

    private static final long serialVersionUID = -7159408164071228549L;
    public void init() throws ServletException{

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //create check code
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        BufferedImage bi = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(204, 255, 204);
        g.setColor(c);
        g.fillRect(0,0,68,22);

        char[] ch = "ABCDEFJHIJKLMNOPQISTUVWXYZ0123456789".toCharArray(); //产生数据源字符数组
        Random r = new Random(); //产生随机数对象
        int len = ch.length,index;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 4; i ++){
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(200),r.nextInt(200),r.nextInt(255)));
            g.drawString(ch[index] + "" ,(i * 15) + 3, 18);
            sb.append(ch[index]);
        }

        servletContext.setAttribute("piccode",sb.toString());
        System.out.println("--> " + servletContext.getAttribute("piccode") + "-");
        ImageIO.write(bi,"JPG",response.getOutputStream());
    }
}
