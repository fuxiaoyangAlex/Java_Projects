package blog.flowingsun.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import blog.flowingsun.bean.User;
public class UserDAO{
    public UserDAO(){
    }
    private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/myblog?characterEncoding=utf-8";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "admin";
    public User findLogin(User user)throws Exception{
        Connection conn=null;                   //定义数据库连接对象
        PreparedStatement pstmt = null;         //定义数据库操作对象
        try{
            String sql = "SELECT id,usrname FROM User WHERE email=? AND password=? OR telephone=? AND password=?";
            Class.forName(DBDRIVER);                       //加载驱动程序
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt = conn.prepareStatement(sql);//实例化操作
            pstmt.setString(1,user.getEmail());     //设置邮箱名称
            pstmt.setString(2,user.getPassword());  //设置密码
            pstmt.setString(3,user.getTelephone()); //设置电话号码
            pstmt.setString(4,user.getPassword());  //设置密码
            ResultSet rs = pstmt.executeQuery();   //取得查询结果
            User usertoken = new User();
            if(rs.next()){
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));           //取得用户名
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pstmt!=null){
                try{
                    pstmt.close();                  //关闭操作
                }catch(Exception e){
                    throw e;
                }
            }
        }
        return user;
    }

    public boolean findRegister(User user)throws Exception{
        boolean flag2 = false;
        Connection conn2=null;                   //定义数据库连接对象
        PreparedStatement pstmt2 = null;         //定义数据库操作对象
        try{
            String sql2 = "INSERT INTO User(telephone,email,usrname,password) VALUES(?,?,?,?)";
            Class.forName(DBDRIVER);                       //加载驱动程序
            conn2 = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt2 = conn2.prepareStatement(sql2);//实例化操作
            pstmt2.setString(1,user.getTelephone()); //设置电话号码
            pstmt2.setString(2,user.getEmail());     //设置邮箱名称
            pstmt2.setString(3,user.getName());      //设置用户名
            pstmt2.setString(4,user.getPassword());  //设置密码
            int rs2 = pstmt2.executeUpdate();   //取得查询结果
            if(rs2>=1){
                flag2 = true;                             //登录成功
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pstmt2!=null){
                try{
                    pstmt2.close();                  //关闭操作
                }catch(Exception e){
                    throw e;
                }
            }
        }
        return flag2;
    }


}
