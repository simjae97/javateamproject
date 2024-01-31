package view;


import java.util.Scanner;

public class 임시뷰 { // Test용
    public void test(){
        while (true) {
            System.out.println("개별 글 번호를 입력하세요.");
            Scanner scanner = new Application().scanner;
            int ch = scanner.nextInt(); // 2번 // 실제글 5번 boardno 5
            new Board1View().board1(ch); // 지금은 그냥 입력받은 값으로 가져오기.
        }
    }
}
