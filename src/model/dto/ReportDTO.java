package model.dto;

public class ReportDTO {

    private int eno;
    private int reportno;
    private String reporttitle;
    private String reportcontent;
    private boolean reportstate;
    private int reportemployee;

    public ReportDTO() {
    }

    public ReportDTO(int eno, int reportno, String reporttitle, String reportcontent, boolean reportstate, int reportemployee) {
        this.eno = eno;
        this.reportno = reportno;
        this.reporttitle = reporttitle;
        this.reportcontent = reportcontent;
        this.reportstate = reportstate;
        this.reportemployee = reportemployee;
    }

    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public int getReportno() {
        return reportno;
    }

    public void setReportno(int reportno) {
        this.reportno = reportno;
    }

    public String getReporttitle() {
        return reporttitle;
    }

    public void setReporttitle(String reporttitle) {
        this.reporttitle = reporttitle;
    }

    public String getReportcontent() {
        return reportcontent;
    }

    public void setReportcontent(String reportcontent) {
        this.reportcontent = reportcontent;
    }

    public boolean isReportstate() {
        return reportstate;
    }

    public void setReportstate(boolean reportstate) {
        this.reportstate = reportstate;
    }

    public int getReportemployee() {
        return reportemployee;
    }

    public void setReportemployee(int reportemployee) {
        this.reportemployee = reportemployee;
    }

}
