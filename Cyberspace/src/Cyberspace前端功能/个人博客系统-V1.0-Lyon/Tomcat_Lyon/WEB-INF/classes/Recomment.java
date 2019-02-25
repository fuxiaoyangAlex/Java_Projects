package blog.flowingsun.bean;
import java.util.Date;

public class Recomment{
    private int id;
    private int userId;
    private String userName;
    private int mainCommentid;
    private int articleId;
    private Date reCommentDate;
    private String reCommentDay;
    private String reComment;


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

    public String getuserName(){
      return userName;
    }

    public void setuserName(String userName){
      this.userName = userName;
    }

    public int getmainCommentid(){
      return mainCommentid;
    }

    public void setmainCommentid(int mainCommentid){
      this.mainCommentid = mainCommentid;
    }

    public int getarticleId(){
      return articleId;
    }

    public void setarticleId(int articleId){
      this.articleId = articleId;
    }

    public Date getreCommentDate(){
      return reCommentDate;
    }

    public void setreCommentDate(Date reCommentDate){
      this.reCommentDate = reCommentDate;
    }

    public String getreCommentDay(){
      return reCommentDay;
    }

    public void setreCommentDay(String reCommentDay){
      this.reCommentDay = reCommentDay;
    }

    public String getreComment(){
      return reComment;
    }

    public void setreComment(String reComment){
      this.reComment = reComment;
    }

}
