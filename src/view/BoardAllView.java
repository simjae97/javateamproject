package view;


import controller.BoadAllViewController;
import controller.Board1Controller;
import model.dto.BoardDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardAllView {

    // Application에서 스케너 받아오기
    Scanner scanner = new MainView().scanner;
    private static final int PAGE_NATION = 15;

    public void boardAllView(){
        while (true){
            try {
                System.out.println("0.뒤로가기 1.전체게시판 2.직급게시판 3.부서별게시판");
                System.out.println("선택 > "); int ch = scanner.nextInt();
                ArrayList<BoardDTO> boardDTOArrayList = new ArrayList<>();  // [1]글 출력을 위한 배열 생성
                if(ch == 0){
                  return;
                } else if(ch > 3){ // [2] 입력받은 숫자 확인
                    System.out.println("숫자를 다시 입력해주세요.");
                } else {    // [3] 입력받은 숫자를 매개 변수로 BoadAllViewController View 메소드 실행
                    int nowpage = 1;
                    while (true){
                        boardDTOArrayList = BoadAllViewController.getInstance().boardAllView(ch);
                        if(boardDTOArrayList.size() == 0){
                            System.out.println("표시할 게시글이 없습니다.");
                        } else {
                            System.out.printf("%-5s \t %-10s \t %-10s \t %-10s \n","번호","제목","작성자","작성일");
                            for(int i=0+(nowpage-1)*PAGE_NATION; i<PAGE_NATION*nowpage; i++){
                                if(i >= boardDTOArrayList.size()){break;}
                                System.out.printf("%-5d \t %-10s \t %-10s \t %-10s \n", i + 1, boardDTOArrayList.get(i).getBoardtitle(), BoadAllViewController.getInstance().enoSearch(boardDTOArrayList.get(i).getEno()).getEname(), boardDTOArrayList.get(i).getBoarddate().split(":")[0]);
                            }
                        }
                        System.out.println("0.뒤로가기1.개별글보기 2.글쓰기 3.이전페이지 4.다음페이지");
                        int ch4 = scanner.nextInt();
                        if(ch4 == 0){
                            break;
                        } else if(ch4 == 1){
                            System.out.print("보고싶은 게시물을 선택하시오.> ");
                            int boardIndex = scanner.nextInt() - 1;
                            int boardno = boardDTOArrayList.get(boardIndex).getBoardno();
                            System.out.println(boardno);
                            if(!Board1Controller.getInstance().board1Checking(boardno)){
                                System.out.println("권한이 없습니다.");
                                continue;
                            }
                            new Board1View().board1(boardno);
                        } else if(ch4 == 2){
                            new BoardWriteView().BoardWrite();
                        } else if(ch4 == 3){
                            if(nowpage == 1 ){
                                System.out.println("이전페이지가 없습니다.");
                            } else {
                                --nowpage;
                            }
                        } else if(ch4 == 4){
                            if(boardDTOArrayList.size()%15 == 0 ? boardDTOArrayList.size()/15 == nowpage : boardDTOArrayList.size()/15+1 == nowpage){
                                System.out.println("다음페이지가 없습니다.");
                            } else {
                                ++nowpage;
                            }
                        } else {
                            System.out.println("숫자를 다시 입력해주세요.");
                        }
                    }
                }
            } catch (Exception e){ // 입력 유효성 검사
                scanner.nextLine();
                System.out.println(e);
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
