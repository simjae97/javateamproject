package model.dao;

import controller.EmployController;
import controller.ReportController;
import model.dto.EmployeeDTO;
import model.dto.ReportDTO;

import javax.annotation.processing.Generated;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ReportDAO extends SuperDao {
//    public ArrayList<ReportDTO> allReport() {
//        try {
//            String sql = "SELECT report.* FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = ? and reportlog.confirm = true;";
//            int loginnum = EmployController.loginEno.getEno();
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, loginnum);
//            rs = ps.executeQuery();
//            ArrayList<ReportDTO> reportDTOS = new ArrayList<>();
//            while (rs.next()) {
//                ReportDTO reportDTO = new ReportDTO();
//                reportDTO.setReporttitle(rs.getString("reporttitle"));
//                reportDTO.setReportno(rs.getInt("reportno"));
//                reportDTOS.add(reportDTO);
//            }
//            return reportDTOS;
//        } catch (Exception e) {
//
//        }
//        return null;
//    }

    public TreeMap<ReportDTO, Boolean> allReport2() {
        try {
            String sql = "SELECT report.*,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = ?;";
            int loginnum = EmployController.loginEno.getEno();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, loginnum);
            rs = ps.executeQuery();
            TreeMap<ReportDTO, Boolean> reportDTOS = new TreeMap<>();
            while (rs.next()) {
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setReporttitle(rs.getString("reporttitle"));
                reportDTO.setReportno(rs.getInt("reportno"));
                boolean state = rs.getBoolean("confirm");
                reportDTOS.put(reportDTO, state);
            }
            return reportDTOS;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

//    public HashMap<ReportDTO,Boolean> allReport2(){
//        try {
//            String sql = "SELECT report.*,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = ?;";
//            int loginnum = ReportController.loginemployee.getEno();
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, loginnum);
//            rs = ps.executeQuery();
//            HashMap<ReportDTO,Boolean> reportDTOS = new HashMap<>();
//            while(rs.next()){
//                ReportDTO reportDTO = new ReportDTO();
//                reportDTO.setReporttitle(rs.getString("reporttitle"));
//                reportDTO.setReportno(rs.getInt("reportno"));
//                boolean state = rs.getBoolean("confirm");
//                reportDTOS.put(reportDTO,state);
//            }
//            return reportDTOS;
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        return null;
//    }

    public boolean reportWrite(ReportDTO dto, ArrayList<Integer> array) {
        try {
            String sql = " insert into Report(eno,reporttitle, reportcontent) values (?, ?,?);";
            int loginnum = EmployController.loginEno.getEno();
            // 2. SQL 기재
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // ? 매개변수 대입
            ps.setInt(1, loginnum); // 기재된 SQL내 첫번째 ?에 값 대입
            ps.setString(2, dto.getReporttitle()); // 기재된 SQL내 2번째 ?에 값 대입
            ps.setString(3, dto.getReportcontent()); // 기재된 SQL내 3번째 ?에 값 대입


            // 3. SQL 실행 // 4. SQL 결과
            int count = ps.executeUpdate();//executeUpdate() 기재된 sql 실행하고 insert된 레코드 개수 반환.

            rs = ps.getGeneratedKeys(); //바로 primary key 가져오기
            rs.next(); // rs.실행
            int pk = rs.getInt(1); //출력된 primary key를 pk에 저장

            if (count == 1) { // 1개가 영향을 받았다는 소리니까 혹시 0개일때를 대비해서 유효성
                for (int i : array) {
                    sql = "insert into rEPOrtLOg(reportno, eno) vALuEs(?,?);";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, pk);
                    ps.setInt(2, i);
                    int check = ps.executeUpdate();
                    if (check != 1) {
                        System.out.println("오류발생");
                        return false;
                    }
                }
                return true;

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // 5. 함수 종료
        return false; // 실패 샘플
    }

    public TreeMap<ReportDTO, Boolean> goReport() {
        try {
            String sql = "SElect report.reportno,report.reporttitle,Count(*) as count FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE report.eno = ? group by report.reportno;";
            String sql2 = "SELECT report.reportno,Count(*) as count FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE report.eno = ? and reportlog.confirm =true group by report.reportno, reportlog.confirm;";
            int loginnum = EmployController.loginEno.getEno();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, loginnum);
            rs = ps.executeQuery();

            ps = conn.prepareStatement(sql2);
            ps.setInt(1, loginnum);
            rs2 = ps.executeQuery();
            TreeMap<ReportDTO, Boolean> reportDTOS = new TreeMap<>();
            HashMap<Integer, Integer> compare = new HashMap<>();
            while (rs2.next()) {
                compare.put(rs2.getInt("reportno"), rs2.getInt("count"));
            }
            while (rs.next()) {
                ReportDTO reportDTO = new ReportDTO();
                int reportno = rs.getInt("reportno");
                reportDTO.setReporttitle(rs.getString("reporttitle"));
                reportDTO.setReportno(reportno);
                int count = rs.getInt("count");
                int result = 0;
                for (Map.Entry<Integer, Integer> entry : compare.entrySet()) {
                    int compareReportNo = entry.getKey();
                    int compareCount = entry.getValue();
                    if (compareReportNo == reportno && count == compareCount) {
                        reportDTOS.put(reportDTO, true);
                        result = 1;
                        break;
                    }
                }
                if (result == 0) {
                    reportDTOS.put(reportDTO, false);
                }
            }

            return reportDTOS;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ReportDTO specificreport(int num) {
        String sql = "select * from report where reportno = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            if(rs.next()){
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setReportcontent(rs.getString("reportcontent"));
                reportDTO.setReporttitle(rs.getString("reporttitle"));
                reportDTO.setEno(rs.getInt("eno"));
                reportDTO.setReportno(rs.getInt("reportno"));
                reportDTO.setReportdate(rs.getString("reportdate"));
                return reportDTO;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean accept(int num){
        String sql = "update reportlog set confirm = true where reportno = ? and eno = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            ps.setInt(2, EmployController.loginEno.getEno());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<EmployeeDTO> findSuperior(){
      try {
          String sql = "SELECT * FROM Employee WHERE gradeno>? and partno = ?;";
          int logingrade = EmployController.loginEno.getGradeno();
          int loginpart = EmployController.loginEno.getPartno();
          ps = conn.prepareStatement(sql);
          ps.setInt(1, logingrade);
          ps.setInt(2, loginpart);
          rs = ps.executeQuery();
          ArrayList<EmployeeDTO> reportDTOS = new ArrayList<>();

          while (rs.next()) {
              EmployeeDTO employeeDTO = new EmployeeDTO();
              employeeDTO.setEno(rs.getInt("eno"));
              employeeDTO.setEname(rs.getString("ename"));
              reportDTOS.add(employeeDTO);
          }
          return reportDTOS;
      }
      catch (Exception e) {
          System.out.println(e);
      }
        return null;
    }
    public boolean reportDelete(int num){
        try {
            String sql = "DELETE from report WHERE reportno = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){

        }
        return false;
    }
    public TreeMap<Integer, Boolean > findSuperiors(int num){
        try {
            TreeMap<Integer, Boolean > superiors = new TreeMap<>();
            String sql = "select eno, confirm from reportlog where reportno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            while(rs.next()) {
                superiors.put(rs.getInt("eno"),rs.getBoolean("confirm"));
            }
            return superiors;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
