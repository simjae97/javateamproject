package view;

import java.util.Scanner;

public class Application {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BoardAllView boardAllView = new BoardAllView();
        boardAllView.boardAllView();
    }
}
