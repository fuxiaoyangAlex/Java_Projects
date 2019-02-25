package blog.flowingsun.bean;
import java.util.Date;
import java.util.ArrayList;

public class Articlelist {
    private int id;
    private int mid;
    private String midName;
    private int cid;
    private String cidName;
    private String title;
    private String author;
    private Date createDate;
    private Date editDate;
    private String createDay;
    private String editDay;
    private String summary;
    private String content;
    private int commentNum;
    private int thankNum;
    private ArrayList<String> articleTags;

    public Articlelist() {
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return this.mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMidName(){
        return this.midName;
    }

    public void setMidName(String midName){
        this.midName = midName;
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCidName(){
        return this.cidName;
    }

    public void setCidName(String cidName){
        this.cidName = cidName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDay() {
        return this.createDay;
    }
    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public Date getEditDate() {
        return this.editDate;
    }
    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getEditDay() {
        return this.editDay;
    }
    public void setEditDay(String editDay) {
        this.editDay = editDay;
    }


    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public int getcommentNum() {
        return this.commentNum;
    }
    public void setcommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getthankNum() {
        return this.thankNum;
    }
    public void setthankNum(int thankNum) {
        this.thankNum = thankNum;
    }

    public ArrayList<String> getarticleTags(){
      return articleTags;
    }

    public void setarticleTags(ArrayList<String> articleTags){
      this.articleTags = articleTags;
    }

}
