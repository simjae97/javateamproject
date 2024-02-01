package controller;

import model.dao.EmployeeDao;
import model.dto.EmployeeDTO;

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

    public static EmployeeDTO loginEno = new EmployeeDTO();

    public boolean logIn(EmployeeDTO employeeDTO){
        boolean result = false;
        result = EmployeeDao.getInstance().logIn(employeeDTO);
        if(result){
            int[] loginint = EmployeeDao.getInstance().findEPGno(employeeDTO);
            loginEno.setEno(loginint[0]);
            loginEno.setPartno(loginint[1]);
            loginEno.setGradeno(loginint[2]);
        }
        return result;
    }

    public void logOut(){
        loginEno.setEno(0);
        loginEno.setPartno(0);
        loginEno.setGradeno(0);
    }

    public boolean exit(EmployeeDTO employeeDTO){

        boolean result = false;

        result = EmployeeDao.getInstance().exit(employeeDTO, loginEno.getEno());

        return result;
    }

}
