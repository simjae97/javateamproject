package model.dto;

public class MailDTO {
    private int mailno;
    private int eno;
    private String mailtitle;
    private String mailcontetnt;
    private String maildate;

    public MailDTO() {
    }

    public MailDTO(int mailno, int eno, String mailtitle, String mailcontetnt, String maildate) {
        this.mailno = mailno;
        this.eno = eno;
        this.mailtitle = mailtitle;
        this.mailcontetnt = mailcontetnt;
        this.maildate = maildate;
    }

    public int getMailno() {
        return mailno;
    }

    public void setMailno(int mailno) {
        this.mailno = mailno;
    }

    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public String getMailtitle() {
        return mailtitle;
    }

    public void setMailtitle(String mailtitle) {
        this.mailtitle = mailtitle;
    }

    public String getMailcontetnt() {
        return mailcontetnt;
    }

    public void setMailcontetnt(String mailcontetnt) {
        this.mailcontetnt = mailcontetnt;
    }

    public String getMaildate() {
        return maildate;
    }

    public void setMaildate(String maildate) {
        this.maildate = maildate;
    }
}
