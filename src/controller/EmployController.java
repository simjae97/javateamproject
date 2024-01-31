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




}
