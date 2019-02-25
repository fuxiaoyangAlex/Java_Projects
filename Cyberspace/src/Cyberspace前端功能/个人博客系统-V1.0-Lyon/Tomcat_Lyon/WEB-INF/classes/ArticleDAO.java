package blog.flowingsun.dao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import blog.flowingsun.util.DateUtil;
import blog.flowingsun.bean.Articlelist;
import blog.flowingsun.dao.ConnectionPool;
public class ArticleDAO{
    public ArticleDAO(){

    }
    ConnectionPool conPool = new ConnectionPool(3);

    public Articlelist getArticle(int id) {       //查询序号为id的文章内容，结果以bean返回
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt = null;
        Articlelist bean = new Articlelist();
        String sql = "SELECT * FROM articles WHERE id = " + id;//构造数字为id的文章查询SQL
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        try{
            pstmt = conn.prepareStatement(sql);    //准备执行语句
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
              String author = rs.getString(2);
              String title = rs.getString(3);
              String summary = rs.getString(4);
              String content = rs.getString(5);
              Date createDate = DateUtil.t2d(rs.getTimestamp(6));
              String createDay = sdf.format(createDate);
              Date editDate = DateUtil.t2d(rs.getTimestamp(7));
              String editDay = sdf.format(editDate);
              int cid = rs.getInt(8);
              int mid = rs.getInt(9);
              bean.setId(id);
              bean.setCid(cid);
              bean.setMid(mid);
              bean.setTitle(title);
              bean.setSummary(summary);
              bean.setAuthor(author);
              bean.setCreateDay(createDay);
              bean.setContent(content);
            }
            pstmt.close();
            conPool.returnConnection(conn);
        }catch(Exception e){
            e.printStackTrace();
        }return bean;
    }


    public int getArticlenum(int cid) {            //查询对应cid下文章总数,返回int型数字
        String sql = "SELECT COUNT(*) FROM articles WHERE cid=" + cid;
        int articlenum =  0;                       //cid为categoryid，即二级分类id
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt2 = null;
        try{
            pstmt2 = conn.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
              articlenum = rs.getInt(1);
            }
            conPool.returnConnection(conn);
            pstmt2.close();
        }catch(Exception e){
            e.printStackTrace();
        }return articlenum;
    }

    public int getMainArticlenum(int mid) {            //查询对应mid下文章总数,返回int型数字
        String sql = "SELECT COUNT(*) FROM articles WHERE mid=" + mid;
        int articlenum =  0;                       //cid为categoryid，即二级分类id
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt2 = null;
        try{
            pstmt2 = conn.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
              articlenum = rs.getInt(1);
            }
            conPool.returnConnection(conn);
            pstmt2.close();

        }catch(Exception e){
            e.printStackTrace();
        }return articlenum;
    }

    public ArrayList getCatsArticles(int cid) {      //查询对应cid下所有文章，结果以bean列表形式保存（文章总数隐含在结果列表长度中）
        String sql = "SELECT * FROM articles WHERE cid=? ORDER BY id DESC";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Articlelist> articlesbean = new ArrayList<Articlelist>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt3 = null;
        try{
                           //加载驱动程序

            pstmt3 = conn.prepareStatement(sql);  //准备执行语句
            pstmt3.setInt(1,cid);
            ResultSet rs = pstmt3.executeQuery();
            while (rs.next()) {
              Articlelist bean = new Articlelist();
              int id = rs.getInt(1);
              String author = rs.getString(2);
              String title = rs.getString(3);
              String summary = rs.getString(4);
              String content = rs.getString(5);
              Date createDate = DateUtil.t2d(rs.getTimestamp(6));
              String createDay = sdf.format(createDate);
              int mid = rs.getInt(9);
              bean.setId(id);
              bean.setMid(mid);
              bean.setCid(cid);
              bean.setTitle(title);
              bean.setSummary(summary);
              bean.setAuthor(author);
              bean.setCreateDay(createDay);
              bean.setContent(content);
              bean.setCid(cid);
              articlesbean.add(bean);
            }
            conPool.returnConnection(conn);
            pstmt3.close();

        }catch(Exception e){
            e.printStackTrace();
        }return articlesbean;
    }

    public boolean setArticle(Articlelist bean) { //插入新生成的文章,接收参数为bean
        boolean flag = false;                         //flag为布尔型返回值,成功为true
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt4 = null;
        String sql = "INSERT INTO articles (author,title,subtitle,content,createdate,editdate,cid,mid) VALUES(?,?,?,?,?,?,?,?)";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
                             //加载驱动程序

            pstmt4 = conn.prepareStatement(sql);    //准备执行语句
            pstmt4.setString(1,bean.getAuthor());
            pstmt4.setString(2,bean.getTitle());
            pstmt4.setString(3,bean.getSummary());
            pstmt4.setString(4,bean.getContent());
            //String createDay = sdf.format(createDate);
            pstmt4.setString(5,bean.getCreateDay());
            pstmt4.setString(6,bean.getEditDay());
            pstmt4.setInt(7,bean.getCid());
            pstmt4.setInt(8,bean.getMid());
            int rs = pstmt4.executeUpdate();
            if (rs==1) {
              flag = true;
            }
            conPool.returnConnection(conn);
            pstmt4.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public ArrayList getPartArticles(int index,int length) {      //查询数据库所有文章的除主体内容外的全部信息，结果以bean列表形式保存（文章总数隐含在结果列表长度中）
        String sql = "SELECT articles.id,author,title,subtitle,createdate,editdate,cid,categary_2.name,mid,categary.name FROM articles,categary,categary_2 WHERE articles.cid=categary_2.id AND articles.mid=categary.id ORDER BY articles.id DESC LIMIT ?,?";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Articlelist> partArticlesBean = new ArrayList<Articlelist>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt5 = null;
        try{
                           //加载驱动程序

            pstmt5 = conn.prepareStatement(sql);  //准备执行语句
            pstmt5.setInt(1,index);
            pstmt5.setInt(2,length);
            ResultSet rs = pstmt5.executeQuery();
            while (rs.next()) {
              Articlelist bean = new Articlelist();
              int id = rs.getInt(1);
              String author = rs.getString(2);
              String title = rs.getString(3);
              String summary = rs.getString(4);
              Date createDate = DateUtil.t2d(rs.getTimestamp(5));
              String createDay = sdf.format(createDate);
              Date editDate = DateUtil.t2d(rs.getTimestamp(6));
              String editDay = sdf.format(editDate);
              int cid = rs.getInt(7);
              String cidName = rs.getString(8);
              int mid = rs.getInt(9);
              String midName = rs.getString(10);
              bean.setMidName(midName);
              bean.setMid(mid);
              bean.setCidName(cidName);
              bean.setCid(cid);
              bean.setCreateDay(createDay);
              bean.setEditDay(editDay);
              bean.setSummary(summary);
              bean.setTitle(title);
              bean.setAuthor(author);
              bean.setId(id);
              partArticlesBean.add(bean);
            }
            conPool.returnConnection(conn);
            pstmt5.close();

        }catch(Exception e){
            e.printStackTrace();
        }return partArticlesBean;
    }

    public ArrayList getAllArticles() {      //查询数据库所有文章的除主体内容外的全部信息，结果以bean列表形式保存（文章总数隐含在结果列表长度中）
        String sql = "SELECT id,author,title,subtitle,createdate,cid,mid FROM articles";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Articlelist> allArticlesBean = new ArrayList<Articlelist>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt6 = null;
        try{
                           //加载驱动程序

            pstmt6 = conn.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt6.executeQuery();
            while (rs.next()) {
              Articlelist bean = new Articlelist();
              int id = rs.getInt(1);
              String author = rs.getString(2);
              String title = rs.getString(3);
              String summary = rs.getString(4);
              Date createDate = DateUtil.t2d(rs.getTimestamp(5));
              String createDay = sdf.format(createDate);
              int cid = rs.getInt(6);
              int mid = rs.getInt(7);
              bean.setId(id);
              bean.setCid(cid);
              bean.setMid(mid);
              bean.setTitle(title);
              bean.setSummary(summary);
              bean.setAuthor(author);
              bean.setCreateDay(createDay);
              allArticlesBean.add(bean);
            }
            conPool.returnConnection(conn);
            pstmt6.close();

        }catch(Exception e){
            e.printStackTrace();
        }return allArticlesBean;
    }

    public int getArticlesnum() {            //查询文章总数,返回int型数字
        String sql = "SELECT COUNT(*) FROM articles";
        int totalnums =  0;                       //cid为categoryid，即二级分类id
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt7.executeQuery();
            if (rs.next()) {
              totalnums = rs.getInt(1);
            }
            conPool.returnConnection(conn);
            pstmt7.close();

        }catch(Exception e){
            e.printStackTrace();
        }return totalnums;
    }

    public boolean updatecommentNum(int commentNum,int articleId) {            //查询对应id下文章的评论总数
        String sql = "UPDATE articles SET commentNum = ? WHERE id = ?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setInt(1,commentNum);
            pstmt7.setInt(2,articleId);
            int rs = pstmt7.executeUpdate();
            if (rs==1) {
              flag = true;
            }
            conPool.returnConnection(conn);
            pstmt7.close();

        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean updatethankNum(int thankNum,int articleId) {            //查询对应id下文章的评论总数
        String sql = "UPDATE articles SET thankNum = ? WHERE id = ?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setInt(1,thankNum);
            pstmt7.setInt(2,articleId);
            int rs = pstmt7.executeUpdate();
            if (rs==1) {
              flag = true;
            }
            conPool.returnConnection(conn);
            pstmt7.close();

        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }



    public boolean DeleteArticle(int id) {            //查询对应cid下文章总数,返回int型数字
        String sql = "DELETE FROM articles WHERE id=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,id);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public ArrayList getcategaryArticles(int cid,int mid,int indexa,int length) {      //查询数据库所有给定mid和cid的文章(除主体内容外）的全部信息，结果以bean列表形式保存
        String sql = "SELECT articles.id,author,title,subtitle,createdate,editdate,cid,categary_2.name,mid,categary.name FROM (articles,categary,categary_2) WHERE (articles.cid=categary_2.id) AND (articles.mid=categary.id) AND (articles.cid=?) AND (articles.mid=?) ORDER BY articles.id ASC LIMIT ?,?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Articlelist> catsBean = new ArrayList<Articlelist>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt9 = null;
        try{
                           //加载驱动程序

            pstmt9 = conn.prepareStatement(sql);  //准备执行语句
            pstmt9.setInt(1,cid);
            pstmt9.setInt(2,mid);
            pstmt9.setInt(3,indexa);
            pstmt9.setInt(4,length);
            ResultSet rs = pstmt9.executeQuery();
            while (rs.next()) {
              Articlelist bean = new Articlelist();
              int id = rs.getInt(1);
              String author = rs.getString(2);
              String title = rs.getString(3);
              String summary = rs.getString(4);
              Date createDate = DateUtil.t2d(rs.getTimestamp(5));
              String createDay = sdf.format(createDate);
              Date editDate = DateUtil.t2d(rs.getTimestamp(6));
              String editDay = sdf.format(editDate);
              int cId = rs.getInt(7);
              String cidName = rs.getString(8);
              int mId = rs.getInt(9);
              String midName = rs.getString(10);

              bean.setMidName(midName);
              bean.setMid(mId);
              bean.setCidName(cidName);
              bean.setCid(cId);
              bean.setCreateDay(createDay);
              bean.setEditDay(editDay);
              bean.setSummary(summary);
              bean.setTitle(title);
              bean.setAuthor(author);
              bean.setId(id);
              catsBean.add(bean);
            }
            conPool.returnConnection(conn);
            pstmt9.close();

        }catch(Exception e){
          e.printStackTrace();
        }return catsBean;
    }

    public ArrayList getMaincatArticles(int mid,int indexa,int length) {      //查询数据库所有给定mid的全部信息，结果以bean列表形式保存
        String sql = "SELECT articles.id,author,title,subtitle,createdate,editdate,cid,categary_2.name,mid,categary.name FROM (articles,categary,categary_2) WHERE (articles.cid=categary_2.id) AND (articles.mid=categary.id)  AND (articles.mid=?) ORDER BY articles.id ASC LIMIT ?,?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Articlelist> catsBean = new ArrayList<Articlelist>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt9 = null;
        try{
                           //加载驱动程序

            pstmt9 = conn.prepareStatement(sql);  //准备执行语句
            pstmt9.setInt(1,mid);
            pstmt9.setInt(2,indexa);
            pstmt9.setInt(3,length);
            ResultSet rs = pstmt9.executeQuery();
            while (rs.next()) {
              Articlelist bean = new Articlelist();
              int id = rs.getInt(1);
              String author = rs.getString(2);
              String title = rs.getString(3);
              String summary = rs.getString(4);
              Date createDate = DateUtil.t2d(rs.getTimestamp(5));
              String createDay = sdf.format(createDate);
              Date editDate = DateUtil.t2d(rs.getTimestamp(6));
              String editDay = sdf.format(editDate);
              int cId = rs.getInt(7);
              String cidName = rs.getString(8);
              String midName = rs.getString(10);

              bean.setMidName(midName);
              bean.setMid(mid);
              bean.setCidName(cidName);
              bean.setCid(cId);
              bean.setCreateDay(createDay);
              bean.setEditDay(editDay);
              bean.setSummary(summary);
              bean.setTitle(title);
              bean.setAuthor(author);
              bean.setId(id);
              catsBean.add(bean);
            }
            conPool.returnConnection(conn);
            pstmt9.close();

        }catch(Exception e){
          e.printStackTrace();
        }return catsBean;
    }

    public boolean reSetArticle(Articlelist bean) { //插入新生成的文章,接收参数为bean
        boolean flag = false;                         //flag为布尔型返回值,成功为true
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt10 = null;
        String sql = "UPDATE articles SET author = ?,title = ?,subtitle = ?,content = ?,editdate = ? WHERE id = ?";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
                             //加载驱动程序

            pstmt10 = conn.prepareStatement(sql);    //准备执行语句
            pstmt10.setString(1,bean.getAuthor());
            pstmt10.setString(2,bean.getTitle());
            pstmt10.setString(3,bean.getSummary());
            pstmt10.setString(4,bean.getContent());
            pstmt10.setString(5,bean.getEditDay());
            pstmt10.setInt(6,bean.getId());
            int rs = pstmt10.executeUpdate();
            if (rs!=0) {
              flag = true;
            }
            conPool.returnConnection(conn);
            pstmt10.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public ArrayList<String> getarticleTags(int id) {            //查询对应id下文章的所有标签
        String sql = "SELECT tagName FROM tagRelations WHERE articleId = ?";
        ArrayList<String> tags = new ArrayList<String>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setInt(1,id);
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              String tag = rs.getString(1);
              tags.add(tag);
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return tags;
    }

    public ArrayList<String> getallTags() {            //查询并返回所有标签
        String sql = "SELECT tagName FROM articleTags;";
        ArrayList<String> tags = new ArrayList<String>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              String tag = rs.getString(1);
              tags.add(tag);
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return tags;
    }

    public int gettagName(String tagName) {            //查询对应tagName下表articleTags有无响应的tag记录
        String sql = "SELECT id FROM articleTags WHERE tagName=?";
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        int tagId = 0;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setString(1,tagName);
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              tagId = rs.getInt(1);
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return tagId;
    }

    public boolean setTag(String tagName,String createDate) {            //查询对应tagName下表articleTags有无相应的tag记录
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        String sql = "INSERT INTO articleTags(tagName,createDate) VALUES (?,?)";
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setString(1,tagName);
            pstmt7.setString(2,createDate);
            int rs = pstmt7.executeUpdate();
            if (rs!=0) {
              flag = true;
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean settagRelation(int articleId,String tagName,int tagId,String createDate) {            //查询对应tagName下表articleTags有无响应的tag记录
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        String sql = "INSERT INTO tagRelations(articleId,tagName,tagId,createDate)VALUES(?,?,?,?)";
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setInt(1,articleId);
            pstmt7.setString(2,tagName);
            pstmt7.setInt(3,tagId);
            pstmt7.setString(4,createDate);
            int rs = pstmt7.executeUpdate();
            if (rs!=0) {
              flag = true;
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean getAtagRelation(int tagId,String tag) {//查询tagRelations表中有无tag-tagId关系记录
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        String sql = "SELECT tagName FROM tagRelations WHERE tagId=?";
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setInt(1,tagId);
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              if(rs.getString(1)==tag){
                  flag = true;
              }
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public int getarticleId(String title,int cid,int mid) {            //查询对应cid下文章总数,返回int型数字
        String sql = "SELECT id FROM articles WHERE (title,cid,mid) = (?,?,?)";
        int articleId =  0;                       //cid为categoryid，即二级分类id
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt2 = null;
        try{
                           //加载驱动程序

            pstmt2 = conn.prepareStatement(sql);  //准备执行语句
            pstmt2.setString(1,title);
            pstmt2.setInt(2,cid);
            pstmt2.setInt(3,mid);
            ResultSet rs = pstmt2.executeQuery();
            while(rs.next()) {
              articleId = rs.getInt(1);
            }
            conPool.returnConnection(conn);
            pstmt2.close();

        }catch(Exception e){
            e.printStackTrace();
        }return articleId;
    }

    public int getRelationNum(int tagId) {//查询tagid下记录的条数
        int relationNum = 0;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        String sql = "SELECT COUNT(*) FROM tagRelations WHERE tagId =?";
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setInt(1,tagId);
            ResultSet rs = pstmt7.executeQuery();
            if (rs.next()) {
              relationNum = rs.getInt(1);
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return relationNum;
    }

    public boolean deleteTag(int id) {            //删除ID下相应的标签
        String sql = "DELETE FROM articleTags WHERE id=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,id);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean deltagRelation(String tagName,int tagId) {            //查询对应cid下文章总数,返回int型数字
        String sql = "DELETE FROM tagRelations WHERE tagName=? AND tagId=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setString(1,tagName);
            pstmt8.setInt(2,tagId);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean delRecomment(int articleId) {            //查询对应cid下文章总数,返回int型数字
        String sql = "DELETE FROM recomments WHERE articleId=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,articleId);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean delComment(int articleId) {            //查询对应cid下文章总数,返回int型数字
        String sql = "DELETE FROM comments WHERE articleId=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,articleId);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean deleteThank(int articleId) {            //查询对应cid下文章总数,返回int型数字
        String sql = "DELETE FROM thanks WHERE articleId=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,articleId);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public boolean deleteTagRelation(int articleId) {            //查询对应cid下文章总数,返回int型数字
        String sql = "DELETE FROM tagRelations WHERE articleId=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,articleId);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

    public ArrayList<Articlelist> SearchKeyword(String keyWord) {//查询tagid下记录的条数
        ArrayList<Articlelist> resultList = new ArrayList<Articlelist>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sql = "SELECT * FROM articles WHERE (title LIKE ?) OR (subtitle LIKE ?) OR (content LIKE ?)";
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setString(1,'%'+keyWord+'%');
            pstmt7.setString(2,'%'+keyWord+'%');
            pstmt7.setString(3,'%'+keyWord+'%');
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              Articlelist bean = new Articlelist();
              int id = rs.getInt(1);
              String author = rs.getString(2);
              String title = rs.getString(3);
              String summary = rs.getString(4);
              //String content = rs.getString(5);
              Date createDate = DateUtil.t2d(rs.getTimestamp(6));
              String createDay = sdf.format(createDate);
              Date editDate = DateUtil.t2d(rs.getTimestamp(7));
              String editDay = sdf.format(editDate);
              int cid = rs.getInt(8);
              int mid = rs.getInt(9);
              int commentNum = rs.getInt(10);
              int thankNum = rs.getInt(11);
              bean.setId(id);
              bean.setMid(mid);
              bean.setCid(cid);
              bean.setAuthor(author);
              bean.setTitle(title);
              bean.setSummary(summary);
              //bean.setContent(content);
              bean.setCreateDay(createDay);
              bean.setEditDay(editDay);
              bean.setcommentNum(commentNum);
              bean.setthankNum(thankNum);
              resultList.add(bean);
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return resultList;
    }

    public ArrayList<Integer> getTagArticlesId(String tagName) {//查询相应tag下所有文章的id并返回
        ArrayList<Integer> idList = new ArrayList<Integer>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        String sql = "SELECT articleId from tagRelations WHERE tagName=?";
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setString(1,tagName);
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              int id = rs.getInt(1);
              idList.add(id);
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return idList;
    }

    public Map<Integer,String> queryCategary(int Mid) {            //查询对应cid下文章总数,返回int型数字
        String sql = "SELECT id,name FROM categary_2 WHERE mainId = ?";
        Map<Integer,String>  catsMap = new HashMap<Integer,String>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,Mid);
            ResultSet rs = pstmt8.executeQuery();
            while (rs.next()) {
                catsMap.put(rs.getInt(1),rs.getString(2));
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return catsMap;
    }

    public Map<Integer,String> getallMid() {            //查询并返回所有标签
        String sql = "SELECT * FROM categary";
        Map<Integer,String> MidMap = new HashMap<Integer,String>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              MidMap.put(rs.getInt(1),rs.getString(2));
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return MidMap;
    }

    public Map<Integer,String> getallCid(int Mid) {            //查询并返回所有标签
        String sql = "SELECT * FROM categary_2 WHERE mainId=?";
        Map<Integer,String> CidMap = new HashMap<Integer,String>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt7 = null;
        try{
                           //加载驱动程序

            pstmt7 = conn.prepareStatement(sql);  //准备执行语句
            pstmt7.setInt(1,Mid);
            ResultSet rs = pstmt7.executeQuery();
            while (rs.next()) {
              CidMap.put(rs.getInt(1),rs.getString(3));
            }
            conPool.returnConnection(conn);
            pstmt7.close();
        }catch(Exception e){
            e.printStackTrace();
        }return CidMap;
    }

    public ArrayList getCatsArticleAttrs(int cid) {      //查询对应cid下所有文章，结果以bean列表形式保存（文章总数隐含在结果列表长度中）
        String sql = "SELECT id,title,subtitle,commentNum,thankNum FROM articles WHERE cid=? ORDER BY id DESC";
        ArrayList<Articlelist> articles = new ArrayList<Articlelist>();
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt3 = null;
        try{
                           //加载驱动程序

            pstmt3 = conn.prepareStatement(sql);  //准备执行语句
            pstmt3.setInt(1,cid);
            ResultSet rs = pstmt3.executeQuery();
            while (rs.next()) {
              Articlelist bean = new Articlelist();
              int id = rs.getInt(1);
              String title = rs.getString(2);
              String summary = rs.getString(3);
              int commentNum = rs.getInt(4);
              int thankNum = rs.getInt(5);
              bean.setId(id);
              bean.setTitle(title);
              bean.setSummary(summary);
              bean.setcommentNum(commentNum);
              bean.setthankNum(thankNum);
              articles.add(bean);
            }
            conPool.returnConnection(conn);
            pstmt3.close();

        }catch(Exception e){
            e.printStackTrace();
        }return articles;
    }

    public boolean deleteOneTag(int articleId,String tagName) {            //查询对应cid下文章总数,返回int型数字
        String sql = "DELETE FROM tagRelations WHERE articleId=? AND tagName=?";
        boolean flag = false;
        Connection conn = conPool.getConnection();
        PreparedStatement pstmt8 = null;
        try{
                           //加载驱动程序

            pstmt8 = conn.prepareStatement(sql);  //准备执行语句
            pstmt8.setInt(1,articleId);
            pstmt8.setString(2,tagName);
            int rs = pstmt8.executeUpdate();
            if (rs==1) {
                flag=true;
            }
            conPool.returnConnection(conn);
            pstmt8.close();
        }catch(Exception e){
            e.printStackTrace();
        }return flag;
    }

}
