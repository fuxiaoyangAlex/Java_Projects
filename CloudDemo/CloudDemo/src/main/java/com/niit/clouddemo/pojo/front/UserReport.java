package com.niit.clouddemo.pojo.front;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/27 0:23
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO:
 */
public class UserReport {

    private Integer report_id;
    private String informer;
    private String reporteduser;
    private String reportcontent;
    private String reporttime;

    public Integer getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }

    public String getInformer() {
        return informer;
    }

    public void setInformer(String informer) {
        this.informer = informer;
    }

    public String getReporteduser() {
        return reporteduser;
    }

    public void setReporteduser(String reporteduser) {
        this.reporteduser = reporteduser;
    }

    public String getReportcontent() {
        return reportcontent;
    }

    public void setReportcontent(String reportcontent) {
        this.reportcontent = reportcontent;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    @Override
    public String toString() {
        return "UserReport{" +
                "report_id=" + report_id +
                ", informer='" + informer + '\'' +
                ", reporteduser='" + reporteduser + '\'' +
                ", reportcontent='" + reportcontent + '\'' +
                ", reporttime='" + reporttime + '\'' +
                '}';
    }
}
