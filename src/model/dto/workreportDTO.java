package model.dto;

public class workreportDTO extends ReportDTO{
    String content2;

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public workreportDTO(String content2) {
        this.content2 = content2;
    }

    public workreportDTO(String reporttitle, String reportcontent, String content2) {
        super(reporttitle, reportcontent);
        this.content2 = content2;
    }

    @Override
    public String toString() {
        return super.toString() +
                "content2='" + content2 + '\'' +
                '}';
    }
}
