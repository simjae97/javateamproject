package model.dao;

import controller.ReportController;
import model.dto.ReportDTO;

import javax.annotation.processing.Generated;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportDAO extends SuperDao {
    public ArrayList<ReportDTO> allReport(){
        System.out.println("리포트 전체보기다오 실행");
        try {
            String sql = "SELECT report.* FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = ?;";
            int loginnum = ReportController.loginemployee.getEno();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, loginnum);
            rs = ps.executeQuery();
            ArrayList<ReportDTO> reportDTOS = new ArrayList<>();
            while(rs.next()){
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setReporttitle(rs.getString("reporttitle"));
                reportDTO.setReportcontent(rs.getString("reportcontent"));
                reportDTOS.add(reportDTO);
            }
            return reportDTOS;
        }
        catch (Exception e){

        }
        return null;
    }
    public boolean reportWrite(ReportDTO dto, ArrayList<Integer> array){
        System.out.println("리포트 작성 다오 실행");
            try {
                String sql = " insert into Report(eno,reporttitle, reportcontent) values (?, ?,?);";
                int loginnum = ReportController.loginemployee.getEno();
                // 2. SQL 기재
                ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS );
                // ? 매개변수 대입
                ps.setInt(1, loginnum); // 기재된 SQL내 첫번째 ?에 값 대입
                ps.setString(2, dto.getReporttitle()); // 기재된 SQL내 2번째 ?에 값 대입
                ps.setString(3, dto.getReportcontent()); // 기재된 SQL내 3번째 ?에 값 대입



                // 3. SQL 실행 // 4. SQL 결과
                int count = ps.executeUpdate();//executeUpdate() 기재된 sql 실행하고 insert된 레코드 개수 반환.
                rs = ps.getGeneratedKeys();
                rs.next();
                int pk =  rs.getInt(1) ;


                if (count == 1) { // 1개가 영향을 받았다는 소리니까 혹시 0개일때를 대비해서 유효성
                    for(int i : array) {
                        sql = "insert into rEPOrtLOg(reportno, eno) vALuEs(?,?);";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, pk);
                        ps.setInt(2, i);
                        int check = ps.executeUpdate();
                        if (check != 1){
                            System.out.println("오류발생");
                            return false;
                        }
                    }
                    return true;

                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            // 5. 함수 종료
            return false; // 실패 샘플
        }
}
