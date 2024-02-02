package controller;

import model.dao.BoardWriteDao;
import model.dto.BoardDTO;

public class BoardWriteController {
    private BoardWriteController(){}
    private static BoardWriteController boadAllWriteController = new BoardWriteController();
    public static BoardWriteController getInstance(){return boadAllWriteController;}

    public boolean boardWrite(BoardDTO boardDTO){
        return BoardWriteDao.getInstance().boardWrite(boardDTO,EmployController.loginEno);
    }
}
