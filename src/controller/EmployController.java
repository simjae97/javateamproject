package controller;

import model.dao.EmployeeDao;
import model.dto.EmployeeDTO;

import java.util.ArrayList;

public class EmployController {
    //싱글톤
    private EmployController(){}
    private static EmployController employController = new EmployController();
    public static EmployController getInstance(){return  employController;}

    public int signUp(EmployeeDTO employeeDTO){
        int result =0;

        //id 중복검사
        if(EmployeeDao.getInstance().checkId(employeeDTO.getEid())){
            return 2;
        }

        //회원가입 요청
        result = EmployeeDao.getInstance().signUp(employeeDTO);
        return 0;
    }

    public static EmployeeDTO loginEno;

    public boolean logIn(EmployeeDTO employeeDTO){
        boolean result = false;
        result = EmployeeDao.getInstance().logIn(employeeDTO);
        if(result){
            int[] loginint = EmployeeDao.getInstance().findEPGno(employeeDTO);
            loginEno = new EmployeeDTO();
            loginEno.setEno(loginint[0]);
            loginEno.setPartno(loginint[1]);
            loginEno.setGradeno(loginint[2]);
        }
        return result;
    }

    public void logOut(){
        loginEno = null;
    }

    public boolean exit(EmployeeDTO employeeDTO){

        boolean result = false;

        result = EmployeeDao.getInstance().exit(employeeDTO, loginEno.getEno());

        return result;
    }

    public ArrayList<EmployeeDTO> employeeInfo(EmployeeDTO employeeDTO){
        ArrayList<EmployeeDTO> result = new ArrayList<>();

        result = EmployeeDao.getInstance().employeeInfo(employeeDTO);

        return result;
    }
}
