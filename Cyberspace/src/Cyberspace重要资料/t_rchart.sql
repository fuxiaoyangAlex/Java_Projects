/*
Navicat MySQL Data Transfer

Source Server         : Mysql_Connection
Source Server Version : 50560
Source Host           : 127.0.0.1:3306
Source Database       : cyberspace

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-05 18:51:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_rchart
-- ----------------------------
DROP TABLE IF EXISTS `t_ochart`;
CREATE TABLE `t_rchart` (
  `id` int(11) NOT NULL,
  `to1` int(11) DEFAULT NULL COMMENT '00:00-02:00',
  `to2` int(11) DEFAULT NULL COMMENT '02:00-04:00',
  `to3` int(11) DEFAULT NULL COMMENT '04:00-06:00',
  `to4` int(11) DEFAULT NULL COMMENT '6:00-8:00',
  `to5` int(11) DEFAULT NULL COMMENT '8:00-10:00',
  `to6` int(11) DEFAULT NULL COMMENT '10:00-12:00',
  `to7` int(11) DEFAULT NULL COMMENT '12:00-14:00',
  `to8` int(11) DEFAULT NULL COMMENT '14:00-16:00',
  `to9` int(11) DEFAULT NULL COMMENT '16:00-18:00',
  `to10` int(11) DEFAULT NULL COMMENT '18:00-20:00',
  `to11` int(11) DEFAULT NULL COMMENT '20:00-22:00',
  `to12` int(11) DEFAULT NULL COMMENT '22:00-24:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


protectedvoiddoPost(HttpServletRequestrequest,HttpServletResponseresponse)throwsServletException,IOException{
Stringlmessage_content=request.getParameter("lmessage");//留言的内容
Stringlmessage_user=request.getParameter("uname");//自己给自己留言时自己的用户名
Stringlmessage_pic=request.getParameter("lmessage_pic");
Stringuuid=request.getParameter("uuid");//自己给自己留言也要UUID作为之后统计时使用
System.out.println("留言人名称："+lmessage_user);//留言人的名称
System.out.println("留言人的头像："+lmessage_pic);//留言人的头像
System.out.println("----------------Lmessage-----------------");
System.out.println(lmessage_content);
Datedate=newDate(System.currentTimeMillis());
Stringlmessage_date=newSimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(date);
MessageServicems=newMessageService();
try{
Stringflag=ms.MessageService(lmessage_user,lmessage_pic,lmessage_date,lmessage_content,uuid);
if(flag.equals("留言失败！")){
//跳转回到登录页，回显错误信息
request.setAttribute("error","留言失败！");
//转发,服务器内部转发
request.getRequestDispatcher("cyberspace_board.jsp").forward(request,response);
}else{
//写入日志
//============================================================================
Stringo_way="用户为："+lmessage_user;
Stringo_operation="给用户名称为："+lmessage_user+"的留言板留言";
LogServicels=newLogService();
ls.operationOnRecord(o_way,o_operation);
//============================================================================
//跳转回到登录页，回显错误信息
request.setAttribute("error","留言成功");
//转发,服务器内部转发
request.getRequestDispatcher("lMessageServlet.do?uuid="+uuid).forward(request,response);
}
}catch(SQLExceptione){
e.printStackTrace();
}
}

protectedvoiddoGet(HttpServletRequestrequest,HttpServletResponseresponse)throwsServletException,IOException{
doGet(request,response);
}

