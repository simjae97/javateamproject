package view;

import controller.Board1Controller;
import controller.ReplyController;

import java.util.ArrayList;
import java.util.Map;

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

    }
}