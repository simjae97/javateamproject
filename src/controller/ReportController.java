package controller;

import model.dao.ReportDAO;
import model.dto.EmployeeDTO;
import model.dto.ReportDTO;

import java.util.ArrayList;
import java.util.TreeMap;


public class ReportController {
    ReportDAO reportDAO = new ReportDAO();

//    public ArrayList<ReportDTO> allReport(){
//        return reportDAO.allReport();
//    }

    public TreeMap<ReportDTO,Boolean> allReport2(){
        return reportDAO.allReport2();
    }


//    public HashMap<ReportDTO,Boolean> allReport2(){
//        return reportDAO.allReport2();
//    }


    public boolean reportWrite(ReportDTO dto, ArrayList<Integer> array){
        return reportDAO.reportWrite(dto,array);
    }
    public EmployeeDTO enoSearch(int num){
        return reportDAO.enoSearch(num);
    }
    public TreeMap<ReportDTO,Boolean> goReport(){
        return reportDAO.goReport();
    }

    public ReportDTO specificreport(int num){
        return reportDAO.specificreport(num);
    }

    public boolean accept(int num){
        return reportDAO.accept(num);
    }

    public ArrayList<EmployeeDTO> findSuperior(){
        return reportDAO.findSuperior();
    }

    public boolean reportDelete(int num){return reportDAO.reportDelete(num);}
    public TreeMap<Integer, Boolean > findSuperiors(int num){
        return reportDAO.findSuperiors(num);
    }
}
