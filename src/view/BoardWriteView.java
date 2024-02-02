package view;

import controller.BoardWriteController;
import controller.EmployController;
import model.dto.BoardDTO;

import java.util.Scanner;

public class BoardWriteView {

    Scanner scanner = new MainView().scanner;
    public void BoardWrite(){
        System.out.print("제목 : "); String boardTitle = scanner.next();
        System.out.print("내용 : "); String boardContent = scanner.next();

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setEno(EmployController.loginEno.getEno());
        boardDTO.setBoardtitle(boardTitle);
        boardDTO.setBoardcontent(boardContent);
        if(BoardWriteController.getInstance().boardWrite(boardDTO)){
            System.out.println("게시물 등록 성공");
        } else {
            System.out.println("게시물 등록 실패");
        }
    }
}
