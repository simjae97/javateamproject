package view;


import controller.BoadAllViewController;
import model.dto.BoardDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardAllView {

    // Application에서 스케너 받아오기
    Scanner scanner = new Application().scanner;

    public void boardAllView(){
        while (true){
            try {
                System.out.println("1.전체게시판 2.직급게시판 3.부서별게시판");
                System.out.println("선택 > "); int ch = scanner.nextInt();
                ArrayList<BoardDTO> boardDTOArrayList = new ArrayList<>();  // [1]글 출력을 위한 배열 생성
                if(ch > 3){ // [2] 입력받은 숫자 확인
                    System.out.println("숫자를 다시 입력해주세요.");
                } else {    // [3] 입력받은 숫자를 매개 변수로 BoadAllViewController View 메소드 실행
                    boardDTOArrayList = BoadAllViewController.getInstance().boardAllView(ch);
                }
            } catch (Exception e){ // 입력 유효성 검사
                scanner.nextLine();
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
