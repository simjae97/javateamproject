package model.dao;

import controller.BoadAllViewController;
import model.dto.BoardDTO;

import java.util.ArrayList;

public class BoardAllViewDao extends SuperDao {
    private BoardAllViewDao(){}
    private static BoardAllViewDao boardAllViewDao = new BoardAllViewDao();
    public static BoardAllViewDao getInstance(){return boardAllViewDao;}

    public ArrayList<BoardDTO> boardAllView(int ch){
        // [5] 받은 ch마다 카테고리 값 변경
        String sql = null;
        if(ch == 1){    // [5-1] 전체글보기 = 모든
            sql = "select * from board order by boardno;";
        } else if (ch == 2){    // [5-2] 직급게시판 = 현재로그인된 직급
            sql = "select * from board order by boardno;";
        } else if (ch == 3){    // [5-3] 부서별게시판 = 현재 로그인된 부서
            sql = "select * from board order by boardno;";
        }


        return null;
    }
}
