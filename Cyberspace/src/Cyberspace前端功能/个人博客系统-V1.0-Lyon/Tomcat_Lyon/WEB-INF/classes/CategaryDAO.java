package blog.flowingsun.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import blog.flowingsun.util.DateUtil;
import blog.flowingsun.bean.Categary;
public class CategaryDAO{
    public CategaryDAO(){
    }
    private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/myblog?characterEncoding=utf-8";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "admin";

    public List getMainCategarys() {  //查询所有主分类名称id：0~6（目前有6大分类）结果以bean列表返回
        List<Categary> maincategarys = new ArrayList<Categary>();
        String sql = "SELECT * FROM categary";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DBDRIVER);                       //加载驱动程序
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt = conn.prepareStatement(sql);    //准备执行语句
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              Categary bean = new Categary();
              int mid = rs.getInt(1);
              String name = rs.getString(2);
              bean.setMid(mid);
              bean.setName(name);
              maincategarys.add(bean);
            }
            conn.close();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }return maincategarys;
    }

    public String getMainCat(int id) {  //根据传入的id查询所对应的主分类名称
        String maincategary = null;
        String sql = "SELECT * FROM categary WHERE id=" + id ;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                maincategary = rs.getString("name");
            }
            conn.close();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }return maincategary;
    }

    public List getSecondCategarys() {   //依次查询从0~total的主分类号下所有次级分类的Bean，结果以二维列表形式返回
       int total = getMainCatNum();      //total即主分类的个数，目前为6个
       List<List<Categary>> secondCats = new ArrayList<List<Categary>>();
       for(int i=1;i<=total;i++){
           String sql = "SELECT * FROM categary_2 WHERE mainId=" + i;
           Connection conn = null;
           PreparedStatement pstmt = null;
           List<Categary> mainCats = new ArrayList<Categary>();
           try{
               Class.forName(DBDRIVER);
               conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
               pstmt = conn.prepareStatement(sql);
               ResultSet rs = pstmt.executeQuery();
               while (rs.next()) {
                  Categary bean = new Categary();
                  int id = rs.getInt(1);
                  int mid = rs.getInt(2);
                  String name = rs.getString(3);
                  bean.setId(id);
                  bean.setMid(mid);
                  bean.setName(name);
                  mainCats.add(bean);
               }
               secondCats.add(mainCats);
               conn.close();
               pstmt.close();
           }catch(Exception e){
               e.printStackTrace();
           }
       }return secondCats;
    }

    public String getSecondCat(int id) {  //根据传入的id查询所对应的次级分类名称
        String categary = null;
        String sql = "SELECT * FROM categary_2 WHERE id=" + id ;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DBDRIVER);
             conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
             pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              categary = rs.getString("name");
            }
            conn.close();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }return categary;
    }

    public int getMainCatNum() {  //查询主分类总数
        int mainCatNum=0;
        String sql = "SELECT count(*) FROM categary";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              mainCatNum = rs.getInt(1);
            }
            conn.close();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }return mainCatNum;
    }

    public int getSecondCatNumber() {  //查询次级分类总数
        int categaryNum=0;
        String sql = "SELECT count(*) FROM categary_2";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              categaryNum = rs.getInt(1);
            }
            conn.close();
            pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }return categaryNum;
    }
}
