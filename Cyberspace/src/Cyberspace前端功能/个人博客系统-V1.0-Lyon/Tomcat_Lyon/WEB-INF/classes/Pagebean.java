package blog.flowingsun.bean;


public class Pagebean {
    private int total;     //文章总数
    private int pagesize;  //每页显示条数
    private int pagecount; //翻页总页数
    private int pagenum;   //当前页数
    private int queryindex;//SQL中,查询索引起始号码
    private int endindex;  //与查询索引相对的查询结束索引号码
    private int cid;       //二级分类id
    private int mid;       //主分类id
    private int flag;  //falg用来检测有无执行分类条件查询，若执行，则flag=1，否则flag=0


    public Pagebean() {
    }

    public int getTotal(){
        return this.total;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public int getPagesize(){
        return this.pagesize;
    }

    public void setPagesize(int pagesize){
        this.pagesize = pagesize;
    }

    public int getPagecount(){
        return this.pagecount;
    }

    public void setPagecount(int pagecount){
        this.pagecount = pagecount;
    }

    public int getPagenum(){
        return this.pagenum;
    }

    public void setPagenum(int pagenum){
        this.pagenum = pagenum;
    }

    public int getQueryindex(){
        return this.queryindex;
    }

    public void setQueryindex(int queryindex){
        this.queryindex = queryindex;
    }

    public int getEndindex(){
        return this.endindex;
    }

    public void setEndindex(int endindex){
        this.endindex = endindex;
    }

    public int getCid(){
        return this.cid;
    }

    public void setCid(int cid){
        this.cid = cid;
    }

    public int getMid(){
        return this.mid;
    }

    public void setMid(int mid){
        this.mid = mid;
    }

    public int getFlag(){
        return this.flag;
    }

    public void setFlag(int flag){
        this.flag = flag;
    }

}
