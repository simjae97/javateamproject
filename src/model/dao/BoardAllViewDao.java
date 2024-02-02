package model.dao;

import controller.BoadAllViewController;
import model.dto.BoardDTO;
import model.dto.EmployeeDTO;

import java.util.ArrayList;

public class BoardAllViewDao extends SuperDao {
    private BoardAllViewDao(){}
    private static BoardAllViewDao boardAllViewDao = new BoardAllViewDao();
    public static BoardAllViewDao getInstance(){return boardAllViewDao;}

    public ArrayList<BoardDTO> boardAllView(int ch, EmployeeDTO loginstate){
        ArrayList<BoardDTO> boardDTOArrayList = new ArrayList<>();
        // [5] 받은 ch마다 카테고리 값 변경
        String sql = null;
        try {
            if(ch == 1){    // [5-1] 전체글보기 = board게시판 모든글 가져오기
                sql = "select * from board order by boardno desc;";
                ps = conn.prepareStatement(sql);
            } else if (ch == 2){    // [5-2] 직급게시판 = 현재로그인된 직급
                sql = "SELECT * FROM board JOIN boardpermit ON board.boardno = boardpermit.boardno where boardpermit.gradeno = ? order by board.boardno desc ;";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, loginstate.getGradeno());
            } else if (ch == 3){    // [5-3] 부서별게시판 = 현재 로그인된 부서
                sql = "SELECT * FROM board JOIN boardpermit ON board.boardno = boardpermit.boardno where boardpermit.partno = ? order by board.boardno desc;";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, loginstate.getPartno());
            }
            rs = ps.executeQuery();
            while (rs.next()){  //  rs.next() -> 데이터베이스 테이블에서 한칸이동후 값이 있으면 true 없으면 false
                // true 일때
                BoardDTO boardDto = new BoardDTO();
                boardDto.setEno(rs.getInt("eno"));  // eno 가져와 객체에 넣어준다.
                boardDto.setBoardno(rs.getInt("boardno"));  // boardno 가져와 객체에 넣어준다.
                boardDto.setBoardtitle(rs.getString("boardtitle"));  // boardtitle 가져와 객체에 넣어준다.
                boardDto.setBoardcontent(rs.getString("boardcontent")); // boardcontent 가져와 객체에 넣어준다.
                boardDto.setBoarddate(rs.getString("boarddate"));   // boarddate 가져와 객체에 넣어준다.
                boardDTOArrayList.add(boardDto); // 9.필요한 자료를 넣은 객체를 배열에 넣어준다.
            }   // false(다음께 없으면) 종료
            return boardDTOArrayList;
        } catch (Exception e){
            System.out.println(e);
        }

        /*//2.sql 기재
        ps = conn.prepareStatement(sql);
        //2-1 매개변수 대입
        ps.setInt(1, num);
        //3.sql 실행 //4.sql 결과
        rs = ps.executeQuery();
        if(rs.next()){ //검색결과 테이블에서 다음레코드 이동 존재하면 true 없으면 false
            return rs.getString("mid");
        }*/


        return null;
    }
}
