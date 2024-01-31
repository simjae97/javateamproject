package model.dao;

import model.dto.EmployeeDTO;

public class EmployeeDao extends SuperDao{
    //싱글톤
    private EmployeeDao(){}
    private static EmployeeDao employeeDao = new EmployeeDao();
    public static EmployeeDao getInstance(){return  employeeDao;}


    public int signUp(EmployeeDTO employeeDTO){
        try {
            String sql = "insert into employee(eid,epw,ename,ephone,partno,eemail) values (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,employeeDTO.getEid());
            ps.setString(2,employeeDTO.getEpw());
            ps.setString(3,employeeDTO.getEname());
            ps.setString(4,employeeDTO.getEphone());
            ps.setInt(5,employeeDTO.getPartno());
            ps.setString(6, employeeDTO.getEemail());

            int count =ps.executeUpdate(); //count => 실행된 레코드 수
            if(count==1){ // 실행된 레코드 수가 1 이면 성공
                return 0;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return 1; //실패
    }

    public boolean checkId(String eid){

        try{
            String sql = "select eid from employee where eid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,eid);
            rs = ps.executeQuery();
            if(rs.next()){ return true; }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
