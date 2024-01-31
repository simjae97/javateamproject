package view;

import java.util.Scanner;

public class Application {
    public Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ReportView reportView= new ReportView();
        reportView.allReport();
    }
}
