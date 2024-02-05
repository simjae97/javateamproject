package view;

import controller.MailController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MailView {
    Scanner scanner = new MainView().scanner;
    public void run() {
        while (true) {
            System.out.println("0.뒤로 가기 | 1.메일 보내기 | 2.받은 메일 | 4.보낸 메일함 | 5.휴지통");
            System.out.print("입력 > ");
            int ch = scanner.nextInt();
            if (ch == 0) { // 뒤로 가기
                return;
            } else if (ch == 1) { // 메일 보내기
                System.out.println("0. 뒤로 가기 | 1.일반 메일보내기 | 2.부서 메일보내기 ");
                System.out.print("입력 > ");
                int ch2 = scanner.nextInt();
                scanner.nextLine();
                if(ch2==0){continue; // 뒤로 가기
                } else if (ch==1) { // 일반 메일
                    System.out.print("보낼 사람 수를 선택하세요 : ");
                    int ch3 = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Map<String, String>> sendemailarr = new ArrayList<>();
                    for (int i = 0; i < ch3; i++) { // 보낼 사람 수만큼
                        System.out.print("보낼 사람 이메일 : ");
                        String sendemail = scanner.nextLine();
                        // 입력하는 순간 DB 유효성 검사로 보내기.
                        if (MailController.getInstance().emailCheck(sendemail)) {// false로 나오면 db에 없는 메일주소
                            i--; // 다시 입력 받으러 되돌아가면서 i 증감식 전 -1 해주기.
                            continue;
                        }
                        Map<String, String> map = new HashMap<>();
                        map.put("email", sendemail);
                        sendemailarr.add(map);
                        //어레이리스트에 값을 집어넣을때에는 add,get의 경우 값을 return받을때, set의 경우 값을 "변경" 할 때
                        // 따라서 sendmailarr에 값을 반복해서 집어넣어야하므로 add사용
                    } //for문 끝
                    System.out.print("보낼 메일 제목 : ");
                    String sendEtitle = scanner.nextLine();
                    Map<String, String> map = new HashMap<>();
                    map.put("sendEtitle", sendEtitle);
                    System.out.print("보낼 메일 내용 : ");
                    String sendEContent = scanner.nextLine();
                    map.put("sendEContent", sendEContent);
                    sendemailarr.add(map); // 어레이리스트 마지막 Map에 title 과 content 넣기
                    if (MailController.getInstance().sendMail(sendemailarr)) {
                        System.out.println("메일 전송 완료.");
                    } else {
                        System.out.println("메일 전송 실패.");
                    }

                } else if (ch==2) { // 부서 메일보내기 시작
                    System.out.println("1. 인사 | 2. 총무 | 3. 개발");
                    System.out.print("입력 > ");
                    int ch3 = scanner.nextInt();
                    scanner.nextLine();
                    MailController.getInstance().sendMail(ch3);

                }

            } else if (ch == 2) { // 받은 메일함 // 안 읽은 메일 / 읽은 메일 들어가서 세부 구분할 것

            } else if (ch == 3) { // 보낸 메일함

            } else if (ch == 4) { // 휴지통( 추가하면 자동 삭제 기능.(시간 남으면))

            } else {
                System.out.println();
                System.out.println("잘못된 입력입니다.");
                System.out.println();
            }
        }
    }


}
