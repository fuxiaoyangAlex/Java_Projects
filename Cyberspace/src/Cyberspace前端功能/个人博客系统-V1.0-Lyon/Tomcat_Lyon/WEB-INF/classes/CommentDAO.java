package blog.flowingsun.dao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import blog.flowingsun.util.DateUtil;
import blog.flowingsun.bean.Comment;
import blog.flowingsun.bean.Recomment;
public class CommentDAO{
    public CommentDAO(){
    }
    private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";//"org.gjt.mm.mysql.Driver"属于早期驱动名称，现在是com.mysql.jdbc.Driver
    private static final String DBURL = "jdbc:mysql://localhost:3306/myblog?characterEncoding=utf-8";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "admin";

    public ArrayList<Comment> getComments(int articleid) { //查询序号为id的文章下的所有评论内容，结果以bean数组返回
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<Comment> commentBean = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE articleId ="+articleid+" ORDER BY commentDate";//构造数字为id的文章查询SQL
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt = conn.prepareStatement(sql);    //准备执行语句
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              Comment bean = new Comment();
              int id = rs.getInt(1);
              int articleId = rs.getInt(2);
              String articleTitle = rs.getString(3);
              int userId = rs.getInt(4);
              String userName = rs.getString(5);
              String comment = rs.getString(6);
              Date cmtDate = DateUtil.t2d(rs.getTimestamp(7));
              String commentDay = sdf.format(cmtDate);
              int starNum = rs.getInt(8);
              int recommentNum = rs.getInt(9);
              bean.setId(id);
              bean.setuserName(userName);
              bean.setcommentDay(commentDay);
              bean.setComment(comment);
              bean.setarticleId(articleId);
              bean.setarticleTitle(articleTitle);
              bean.setuserId(userId);
              bean.setstarNum(starNum);
              bean.setrecommentNum(recommentNum);
              commentBean.add(bean);
            }
            conn.close();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }return commentBean;
    }

    public ArrayList<Recomment> getreComments(int mainCommentid) { //查询主评论序号为id的下的所有二级评论内容，结果以bean数组返回
        Connection conn7 = null;
        PreparedStatement pstmt7 = null;
        String sql = "SELECT * FROM recomments WHERE mainCommentid ="+mainCommentid+" ORDER BY reCommentDate";//构造数字为id的文章查询SQL
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Recomment> recommentBean = new ArrayList<>();
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn7 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt7 = conn7.prepareStatement(sql);    //准备执行语句
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              Recomment bean = new Recomment();
              int id = rs.getInt(1);
              int articleId = rs.getInt(2);
              int userId = rs.getInt(4);
              String userName = rs.getString(5);
              String reComment = rs.getString(6);
              Date cmtDate = DateUtil.t2d(rs.getTimestamp(7));
              String reCommentDay = sdf.format(cmtDate);
              bean.setId(id);
              bean.setarticleId(articleId);
              bean.setmainCommentid(mainCommentid);
              bean.setuserId(userId);
              bean.setuserName(userName);
              bean.setreComment(reComment);
              bean.setreCommentDay(reCommentDay);
              recommentBean.add(bean);
            }
            conn7.close();
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return recommentBean;
    }

    public int getthankNum(int articleid) {     //查询对应id下文章评论总数,返回int型数字
        String sql = "SELECT COUNT(*) FROM thanks WHERE articleId=" + articleid;
        int thanksNum =  0;                       //cid为categoryid，即二级分类id
        Connection conn2 = null;
        PreparedStatement pstmt2 = null;
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn2 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt2 = conn2.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
              thanksNum = rs.getInt(1);
            }
            conn2.close();
            pstmt2.close();

        }catch(Exception e){
            e.printStackTrace();
        }return thanksNum;
    }

    public int getcommentNum(int articleid) {     //查询对应id下文章评论总数,返回int型数字
        String sql = "SELECT COUNT(*) FROM comments WHERE articleId=" + articleid;
        int commentNum =  0;                       //cid为categoryid，即二级分类id
        Connection conn2 = null;
        PreparedStatement pstmt2 = null;
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn2 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt2 = conn2.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
              commentNum = rs.getInt(1);
            }
            conn2.close();
            pstmt2.close();

        }catch(Exception e){
            e.printStackTrace();
        }return commentNum;
    }

    public int setComment(String userName,String commentTime,String usrComment,int articleId,int userId,int starNum) {     //查询对应id下文章评论总数,返回int型数字
        String sql = "INSERT INTO comments (username,commentDate,comment,articleId,userId,star) VALUES(?,?,?,?,?,?)";
        String sql2 = "SELECT COUNT(*) FROM comments WHERE articleId=?";
        int number = 0;
        Connection conn3 = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn3 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt3 = conn3.prepareStatement(sql);  //准备执行语句
            pstmt3.setString(1,userName);
            pstmt3.setString(2,commentTime);
            pstmt3.setString(3,usrComment);
            pstmt3.setInt(4,articleId);
            pstmt3.setInt(5,userId);
            pstmt3.setInt(6,starNum);
            int rs = pstmt3.executeUpdate();
            if (rs==1) {
              pstmt3.close();
              pstmt4 = conn3.prepareStatement(sql2);
              pstmt4.setInt(1,articleId);
              ResultSet rs2 = pstmt4.executeQuery();
              if (rs2.next()) {
                number = rs2.getInt(1);
              }
              pstmt4.close();
              conn3.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }return number;
    }

    public int setThank(int articleId,int userId,String userName,String thankTime) {     //查询对应id下文章评论总数,返回int型数字
        String sql = "INSERT INTO thanks (articleId,userId,username,thankDate) VALUES(?,?,?,?)";
        String sql2 = "SELECT COUNT(*) FROM thanks WHERE articleId=?";
        int number = 0;
        Connection conn3 = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn3 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt3 = conn3.prepareStatement(sql);  //准备执行语句
            pstmt3.setInt(1,articleId);
            pstmt3.setInt(2,userId);
            pstmt3.setString(3,userName);
            pstmt3.setString(4,thankTime);
            int rs = pstmt3.executeUpdate();
            if (rs==1) {
              pstmt3.close();
              pstmt4 = conn3.prepareStatement(sql2);
              pstmt4.setInt(1,articleId);
              ResultSet rs2 = pstmt4.executeQuery();
              if (rs2.next()) {
                number = rs2.getInt(1);
              }
              pstmt4.close();
              conn3.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }return number;
    }

    public boolean setreComment(int articleId,int mainCommentid,int userId,String userName,String reComment,String reCommentDate) {
        String sql = "INSERT INTO recomments (articleId,mainCommentid,userId,userName,reComment,reCommentDate) VALUES(?,?,?,?,?,?)";
        boolean flag = false;
        Connection conn6 = null;
        PreparedStatement pstmt6 = null;
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn6 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt6 = conn6.prepareStatement(sql);  //准备执行语句
            pstmt6.setInt(1,articleId);
            pstmt6.setInt(2,mainCommentid);
            pstmt6.setInt(3,userId);
            pstmt6.setString(4,userName);
            pstmt6.setString(5,reComment);
            pstmt6.setString(6,reCommentDate);
            int rs = pstmt6.executeUpdate();
            if (rs==1) {
              flag = true;
            }
            conn6.close();
            pstmt6.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean addStar(int commentId) {     //查询对应id下文章评论总数,返回int型数字
        String sql = "UPDATE comments set star = (star + 1) WHERE id =" + commentId;
        boolean flag = false;
        Connection conn4 = null;
        PreparedStatement pstmt4 = null;
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn4 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt4 = conn4.prepareStatement(sql);  //准备执行语句
            int rs = pstmt4.executeUpdate();
            if (rs==1) {
              flag = true;
            }
            conn4.close();
            pstmt4.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean delStar(int commentId) {     //查询对应id下文章评论总数,返回int型数字
        String sql = "UPDATE comments set star = (star - 1) WHERE id =" + commentId;
        boolean flag = false;
        Connection conn5 = null;
        PreparedStatement pstmt5 = null;
        try{
            Class.forName(DBDRIVER);               //加载驱动程序
            conn5 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt5 = conn5.prepareStatement(sql);  //准备执行语句
            int rs = pstmt5.executeUpdate();
            if (rs==1) {
              flag = true;
            }
            conn5.close();
            pstmt5.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

}
