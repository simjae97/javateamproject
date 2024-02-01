package view;

import java.util.Scanner;

public class Application {
    public Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportView reportView = new ReportView();
        while (true) {
            System.out.println("1.받은 보고서 확인 2.보낸 보고서 확인.");
            int ch = scanner.nextInt();
            if(ch==1) {
                reportView.allReport();
            }
            else if (ch==2){
                reportView.goReport();
            }
        }
    }
}
