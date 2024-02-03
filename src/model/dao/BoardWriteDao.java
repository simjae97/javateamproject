package model.dao;

import controller.BoardWriteController;
import model.dto.BoardDTO;
import model.dto.EmployeeDTO;

import java.sql.Statement;

public class BoardWriteDao extends SuperDao {
    private BoardWriteDao(){}
    private static BoardWriteDao boadAllWriteDao = new BoardWriteDao();
    public static BoardWriteDao getInstance(){return boadAllWriteDao;}

    public boolean boardWrite(BoardDTO boardDTO , EmployeeDTO loginEno){
        try {
            String sql = "insert into board(boardtitle,boardcontent,eno) values(?,?,?);";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); // getGeneratedKeys() 메소드를 실행 하기위한 매개변수 등록
            ps.setString(1,boardDTO.getBoardtitle());
            ps.setString(2,boardDTO.getBoardcontent());
            ps.setInt(3,boardDTO.getEno());
            ;//executeUpdate() 기재된 sql 실행하고 insert된 레코드 개수 반환.
            int count = ps.executeUpdate();
            if(count == 1) {
                rs = ps.getGeneratedKeys(); // executeUpdate() 실행시 업데이트된 기본키 가져오기
                rs.next();
                int boardno = rs.getInt(1);
                int gradno = loginEno.getGradeno();
                int partno = loginEno.getPartno();
                sql = "insert into boardpermit(boardno,gradeno,partno) values(?,?,?);";
                ps = conn.prepareStatement(sql); // getGeneratedKeys() 메소드를 실행 하기위한 매개변수 등록
                ps.setInt(1,boardno);
                ps.setInt(2,gradno);
                ps.setInt(3,partno);
                count = ps.executeUpdate();
                if(count == 1) return true;
            }
        } catch (Exception e){
            System.out.println("등록오류");
            System.out.println(e);
        }

        return false;
    }
}
