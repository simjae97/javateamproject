package view;

import controller.Board1Controller;
import controller.EmployController;
import controller.ReplyController;
import model.dto.ReplyDTO;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Board1View { // 개별 글 뷰

    public void board1(int boardno){ // 직접 보드넘버를 받아온다.
            System.out.println("===================== 개별 글 출력 실행 =====================");
            // 글 하나 하나 출력 되는 공간 // Controller로 인트값 넘기기.
            Map<String, String> map = Board1Controller.getInstance().board1(boardno);
            System.out.println("번호 : "+map.get("boardno"));
            System.out.println("제목 : "+map.get("boardtitle"));
            System.out.println("작성자 : "+map.get("ename"));
            System.out.println();
            System.out.println("내용 : "+map.get("boardcontent"));
            System.out.println();
            System.out.println("작성일 : "+map.get("boarddate"));
            System.out.println("댓글 ======================================================");
            ArrayList<Map> map2 = ReplyController.getInstance().replyview(boardno); // 리플 담은 배열 출력
            for(int i =0 ; i<map2.size(); i++){ // 댓글 출력 포문
                    System.out.print(i+1 +"번째 댓글 : ");
                    System.out.print(map2.get(i).get("partname")+"팀/");
                    System.out.printf("%3s %35s\n", map2.get(i).get("ename"), map2.get(i).get("replydate"));
                    System.out.println(map2.get(i).get("replycontent"));
                    System.out.println("----------------------------------------------------------");
            }
            System.out.println("0.뒤로 가기 | 1.댓글 입력 | 2.글 수정 | 3.글 삭제");

            int ch = new MainView().scanner.nextInt();
            new MainView().scanner.nextLine();
            if(ch==0){return;} // 뒤로 가기
            else if (ch==1) { // 댓글 등록
                    System.out.print("댓글 입력 :");
                    Scanner scanner = new MainView().scanner;
                    String newreplycontent = scanner.nextLine(); // 적을 댓글 받기
                    ReplyDTO replyDTO = new ReplyDTO();
                    replyDTO.setEno(EmployController.loginEno.getEno()); // 로그인 중인 Eno DTO에 넣기
                    replyDTO.setReplycontent(newreplycontent);  // newreplycontent를 DTO에 넣기
                    replyDTO.setBoardno(boardno); // 받아온 보드넘버도 같이 넣기.
                    if(ReplyController.getInstance().replyWrite(replyDTO)){// DTO 보내기 boolean값으로 리턴 받기
                            System.out.println("댓글 등록 완료.");
                            board1(boardno);
                    }else{
                            System.out.println("댓글 등록이 되지 않았습니다.");
                    }
            } else if (ch==2) { // 글 수정 실행
                    new BoardUpdateView().boardUpdate(boardno);
                    board1(boardno); //함수 다시 실행되서 수정된 글 보이게.
            } else if (ch==3) { // 글 삭제 실행
                    new BoardUpdateView().boardDelete(boardno);
                    return; // 함수 나가기.
            }


    }
}
