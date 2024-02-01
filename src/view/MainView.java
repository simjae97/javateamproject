package view;

import controller.EmployController;

import java.util.Scanner;

public class MainView {
    public static Scanner scanner = new Scanner(System.in);
    public void run(){
        while (true) {
            System.out.println("1.회원가입  2.로그인 3.회원탈퇴");
            int ch = scanner.nextInt();
            scanner.nextLine();
            if (ch == 1) {
                new EmployeeView().signUp();
            } else if (ch == 2) {

                boolean run = new EmployeeView().logIn();
                while (run) {  // 로그인 되면
                    System.out.println("1.보고서  2.게시판 3.로그아웃 4.회원탈퇴");
                    int ch2 = scanner.nextInt();
                    scanner.nextLine();
                    if (ch2 == 1){
                        while (true) {
                            System.out.println("0.뒤로가기 1.받은 보고서 2.보낸 보고서 3.보고서 작성");
                            int ch3 = scanner.nextInt();
                            if (ch3 == 1) {
                                new ReportView().allReport();
                            } else if (ch3 == 2) {
                                new ReportView().goReport();
                            } else if (ch3 == 0) {
                                break;
                            }
                            else if(ch3 == 3){
                                if(new ReportView().writeReport()){
                                    System.out.println("작성완료");
                                }
                                else {
                                    System.out.println("작성실패");
                                }
                            }
                        }
                    } else if(ch2 == 2){
                        new BoardAllView().boardAllView();
                    } else if(ch2 == 3){
                        run = false;
                        EmployController.getInstance().logOut();
                    } else if(ch2 == 4){
                        new EmployeeView().exit();
                    }
                }
            } else if (ch == 3) {
                new EmployeeView().exit();
            }
        }
    }
}
