package model.dto;

import java.util.ArrayList;

public class EmployeeDTO {
//    eno int auto_increment,
//    gradeno int not null default 0,
//    ename varchar(30) not null,
//    ephone varchar(15) not null unique,
//    eemail varchar(50) not null unique,
//    eid varchar(30) not null unique,
//    epw varchar(30) not null,
//    edate datetime default now(),
//    partno int not null,
    private int eno;
    private int gradeno;
    private String ename;
    private String ephone;
    private String eemail;
    private String eid;
    private String epw;
    private String edate;
    private int partno;

    public EmployeeDTO() {
    }

    // 로그인 상태 생성자(테스트용)
    public EmployeeDTO(int eno, int gradeno, int partno) {
        this.eno = eno;
        this.gradeno = gradeno;
        this.partno = partno;
    }

    public EmployeeDTO(int eno, int gradeno, String ename, String ephone, String eemail, String eid, String epw, String edate, int partno) {
        this.eno = eno;
        this.gradeno = gradeno;
        this.ename = ename;
        this.ephone = ephone;
        this.eemail = eemail;
        this.eid = eid;
        this.epw = epw;
        this.edate = edate;
        this.partno = partno;
    }

    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public int getGradeno() {
        return gradeno;
    }

    public void setGradeno(int gradeno) {
        this.gradeno = gradeno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEphone() {
        return ephone;
    }

    public void setEphone(String ephone) {
        this.ephone = ephone;
    }

    public String getEemail() {
        return eemail;
    }

    public void setEemail(String eemail) {
        this.eemail = eemail;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEpw() {
        return epw;
    }

    public void setEpw(String epw) {
        this.epw = epw;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public int getPartno() {
        return partno;
    }

    public void setPartno(int partno) {
        this.partno = partno;
    }
}
