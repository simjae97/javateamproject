package controller;

import model.dao.BoardAllViewDao;
import model.dto.BoardDTO;
import model.dto.EmployeeDTO;

import java.util.ArrayList;

public class BoadAllViewController {
    private BoadAllViewController(){}
    private static BoadAllViewController boadAllViewController = new BoadAllViewController();
    public static BoadAllViewController getInstance(){return boadAllViewController;}

    // 테스트용 로그인 상태 등록(추후 병합 후 삭제 예정) - DAO 같이 수정
    public static EmployeeDTO loginstate = new EmployeeDTO(1,1,1);

    // [4] VIEW에서 받은 ch로 매개변수로 받아 BoardAllViewDao View 메소드 실행
    public ArrayList<BoardDTO> boardAllView(int ch){return BoardAllViewDao.getInstance().boardAllView(ch);}
}
