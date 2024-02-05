package model.dao;

import controller.EmployController;
import model.dto.EmployeeDTO;
import view.EmployeeView;

import java.util.ArrayList;

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


    public boolean logIn(EmployeeDTO employeeDTO){
        try{
            String sql = "select eno from employee where eid = ? and epw = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,employeeDTO.getEid());
            ps.setString(2,employeeDTO.getEpw());
            rs= ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

//    public int findEno(String eid){
//        try {
//            String sql = "select eno from employee where eid = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1,eid);
//            rs = ps.executeQuery();
//            if(rs.next()){
//                return rs.getInt("eno");
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//        return 0;
//    }

    public int[] findEPGno(EmployeeDTO employeeDTO){
        try {
            String sql = "select eno,partno,gradeno from employee where eid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,employeeDTO.getEid());
            rs= ps.executeQuery();
            if(rs.next()){
                return new int[]{rs.getInt("eno"),rs.getInt("partno"),rs.getInt("gradeno")};

            }
            int[] result = new int[3];
            result[0] = rs.getInt("eno");
            result[1] = rs.getInt("partno");
            result[2] = rs.getInt("gradeno");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean exit(EmployeeDTO employeeDTO, int i){

        try {
            String sql = "select eno from employee where eid = ? and epw = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,employeeDTO.getEid());
            ps.setString(2,employeeDTO.getEpw());

            rs = ps.executeQuery();

            if(rs.next()){
                if(rs.getInt("eno")==i){
                    String sqld = "delete from employee where eno = ?";
                    ps= conn.prepareStatement(sqld);
                    ps.setInt(1,i);

                    ps.executeUpdate();
                    return true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<EmployeeDTO> employeeInfo(EmployeeDTO employeeDTO){
        ArrayList<EmployeeDTO> result = new ArrayList<>();
        try{

            String sql = "select * from employee";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                employeeDTO = new EmployeeDTO();
                employeeDTO.setEno(rs.getInt("eno"));
                employeeDTO.setGradeno(rs.getInt("gradeno"));
                employeeDTO.setEid(rs.getString("eid"));
                employeeDTO.setEpw(rs.getString("epw"));
                employeeDTO.setEname(rs.getString("ename"));
                employeeDTO.setPartno(rs.getInt("partno"));
                employeeDTO.setEphone(rs.getString("ephone"));
                employeeDTO.setEemail(rs.getString("eemail"));
                employeeDTO.setEdate(rs.getString("edate"));
                result.add(employeeDTO);


            }


            return result;

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public boolean changegradeno(EmployeeDTO employeeDTO){
        try{
            String sql = "update employee set gradeno = ? where ename = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeDTO.getGradeno());
            ps.setString(2,employeeDTO.getEname());

            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean changepartno(EmployeeDTO employeeDTO){
        try{
            String sql = "update employee set partno = ? where ename = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeDTO.getPartno());
            ps.setString(2,employeeDTO.getEname());

            ps.executeUpdate();
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
