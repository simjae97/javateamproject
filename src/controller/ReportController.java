package controller;

import model.dao.ReportDAO;
import model.dto.ReportDTO;

import java.util.ArrayList;


public class ReportController {
    public static int loginnum = 1;

    ReportDAO reportDAO = new ReportDAO();
    public void allReport(){
        System.out.println("리포트 전체보기 컨트롤러 실행");
        reportDAO.allReport();
    }
    public boolean reportWrite(ReportDTO dto, ArrayList<Integer> array){
        System.out.println("리포트 작성 컨트롤러 실행");
        return reportDAO.reportWrite(dto,array);
    }
}
