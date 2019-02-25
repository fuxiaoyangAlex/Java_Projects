package blog.flowingsun.bean;
import java.util.Date;
import blog.flowingsun.bean.Thank;

public class Thank{
    private int id;
    private int userId;
    private int articleId;
    private String articleTitle;
    private String userName;
    private Date thankDate;
    private String thankDay;


    public int getId(){
      return id;
    }

    public void setId(int id){
      this.id = id;
    }

    public int getuserId(){
      return userId;
    }

    public void setuserId(int userId){
      this.userId = userId;
    }

    public int getarticleId(){
      return articleId;
    }

    public void setarticleId(int articleId){
      this.articleId = articleId;
    }

    public String getarticleTitle(){
      return articleTitle;
    }

    public void setarticleTitle(String articleTitle){
      this.articleTitle = articleTitle;
    }

    public String getuserName(){
      return userName;
    }

    public void setuserName(String userName){
      this.userName = userName;
    }

    public Date getthankDate(){
      return thankDate;
    }

    public void setthankDate(Date thankDate){
      this.thankDate = thankDate;
    }

    public String getthankDay(){
      return thankDay;
    }

    public void setthankDay(String thankDay){
      this.thankDay = thankDay;
    }





}
