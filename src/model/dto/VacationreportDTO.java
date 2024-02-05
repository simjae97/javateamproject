package model.dto;

public class VacationreportDTO extends ReportDTO{
    String startdate;
    String enddate;

    public VacationreportDTO(String reporttitle, String reportcontent, String startdate, String enddate) {
        super(reporttitle, reportcontent);
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "안녕월드"+super.toString() +
                "startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }
}
