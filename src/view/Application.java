package view;

import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1.회원가입  2.로그인 ");
        int ch = scanner.nextInt();
        scanner.nextLine();
        if(ch==1) {
            new EmployeeView().signUp();
        }else if(ch==2){
            new EmployeeView().logIn();
        }

    }
}
