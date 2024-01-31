package controller;

import model.dao.ReportDAO;
import model.dto.EmployeeDTO;
import model.dto.ReportDTO;

import java.util.ArrayList;


public class ReportController {
    public static EmployeeDTO loginemployee = new EmployeeDTO( 2,1,1);

    ReportDAO reportDAO = new ReportDAO();
    public ArrayList<ReportDTO> allReport(){
        System.out.println("리포트 전체보기 컨트롤러 실행");
        return reportDAO.allReport();
    }
    public ArrayList<ReportDTO> allReport2(){
        System.out.println("리포트 전체보기 컨트롤러 실행");
        return reportDAO.allReport2();
    }
    public boolean reportWrite(ReportDTO dto, ArrayList<Integer> array){
        System.out.println("리포트 작성 컨트롤러 실행");
        return reportDAO.reportWrite(dto,array);
    }
}
