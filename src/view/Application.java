package view;

import model.dto.EmployeeDTO;

import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1.회원가입  2.로그인 3.회원탈퇴");
            int ch = scanner.nextInt();
            scanner.nextLine();
            if (ch == 1) {
                new EmployeeView().signUp();
            } else if (ch == 2) {
                new EmployeeView().logIn();

                System.out.println();
            } else if (ch == 3) {
              new EmployeeView().exit();
            }
        }
    }
}
