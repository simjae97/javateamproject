package view;

import controller.EmployController;
import controller.MailController;
import model.dto.PartDTO;

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
                } else if (ch2==1) { // 일반 메일
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

                } else if (ch2==2) { // 부서 메일보내기 시작
                    int ch3;
                    while (true){
                        ArrayList<PartDTO> partArr = MailController.getInstance().partView();// part 테이블 어레이리스트로 받음
                        for(int i = 0; i<partArr.size();i++){ //part 테이블 저장된 레코드들 빼내기 로직
                            System.out.print(partArr.get(i).getPartno()+". "+partArr.get(i).getPartname());
                        }
                        System.out.println();
                        System.out.print("입력 > ");
                        ch3 = scanner.nextInt();
                        scanner.nextLine();
                        if(MailController.getInstance().sendpartMail(ch3)==null) {// 입력받은 partno로 배열로 뽑아내기. 유효성 검사
                            System.out.println("잘못된 부서 입력입니다.");
                            continue;
                        }else{ // 부서 잘못 입력 안했으면 반복 끝
                            break;
                        }
                    }
                    int[] enoarr= MailController.getInstance().sendpartMail(ch3); //partno로 알아낸 eno 배열
                    ArrayList<Map<String, String>> sendemailarr = new ArrayList<>();
                    for (int i = 0; i < enoarr.length; i++) { // 보낼 사람 수만큼
                        Map<String, String> map = new HashMap<>();
                        map.put("eno", ""+enoarr[i]);
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
                    if (MailController.getInstance().sendpartMail(sendemailarr)) {
                        System.out.println("메일 전송 완료.");
                    } else {
                        System.out.println("메일 전송 실패.");
                    }
                }
            } else if (ch == 2) { // 받은 메일함 // 안 읽은 메일 / 읽은 메일 들어가서 세부 구분할 것
                System.out.println("받은 메일 함");
                // 전체 메일로그의 내 eno 로직 출력
                MailController.getInstance().receiveMail(EmployController.loginEno.getEno());// ArrayList로 Map을 받기.
                System.out.println("1. 안 읽은 메일 | 2. 읽은 메일 |");
                // 안 읽은 메일 로그의 인트 0 / 읽은 메일 로그의 인트 1
                System.out.print("입력 > ");
            } else if (ch == 3) { // 보낸 메일함
                // 내가 보낸 메일만 로직 출력 (메일 테이블)
                System.out.println("보낸 메일함");
                System.out.print("입력 > ");

            } else if (ch == 4) { // 휴지통( 추가하면 자동 삭제 기능.(시간 남으면))
                // 내 메일로그의 인트만 2번으로 바뀌기 // 여기서 삭제하면 3번으로 바뀌기
                System.out.println("메일 휴지통");
                // 2번인 로그 불러오기
                System.out.print("입력 > ");
            } else {
                System.out.println();
                System.out.println("잘못된 입력입니다.");
                System.out.println();
            }
        }
    }


}
