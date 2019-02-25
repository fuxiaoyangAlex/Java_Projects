package blog.flowingsun.bean;
import java.util.Date;
import java.util.ArrayList;
import blog.flowingsun.bean.Recomment;

public class Comment{
    private int id;
    private int userId;
    private int articleId;
    private String articleTitle;
    private String userName;
    private Date commentDate;
    private String commentDay;
    private String comment;
    private int starNum;
    private int recommentNum;
    private ArrayList<Recomment> recommentsList;

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

    public Date getcommentDate(){
      return commentDate;
    }

    public void setcommentDate(Date commentDate){
      this.commentDate = commentDate;
    }

    public String getcommentDay(){
      return commentDay;
    }

    public void setcommentDay(String commentDay){
      this.commentDay = commentDay;
    }

    public String getComment(){
      return comment;
    }

    public void setComment(String comment){
      this.comment = comment;
    }

    public int getstarNum(){
      return starNum;
    }

    public void setstarNum(int starNum){
      this.starNum = starNum;
    }

    public int getrecommentNum(){
      return recommentNum;
    }

    public void setrecommentNum(int recommentNum){
      this.recommentNum = recommentNum;
    }

    public ArrayList<Recomment> getrecommentsList(){
      return recommentsList;
    }

    public void setrecommentsList(ArrayList<Recomment> recommentsList){
      this.recommentsList = recommentsList;
    }

}
