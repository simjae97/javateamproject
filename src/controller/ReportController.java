package controller;

import model.dao.ReportDAO;
import model.dto.EmployeeDTO;
import model.dto.ReportDTO;

import java.util.ArrayList;


public class ReportController {
    public static EmployeeDTO loginemployee = new EmployeeDTO( 2,1,1);

    ReportDAO reportDAO = new ReportDAO();
    public ArrayList<ReportDTO> allReport(){
        return reportDAO.allReport();
    }
    public ArrayList<ReportDTO> allReport2(){
        return reportDAO.allReport2();
    }
    public boolean reportWrite(ReportDTO dto, ArrayList<Integer> array){
        return reportDAO.reportWrite(dto,array);
    }
}
