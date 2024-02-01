package view;

import controller.EmployController;
import model.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EmployeeView {
    Scanner scanner = new MainView().scanner;
    List<String> employeeList = new ArrayList<>();

    //회원가입
    public void signUp(){

        //입력
        System.out.println("아이디를 입력하세요 : ");            String id = scanner.nextLine();
        System.out.println("비밀번호를 입력하세요 : ");          String pw = scanner.nextLine();
        System.out.println("이름을 입력하세요 :");              String name = scanner.nextLine();
        System.out.println("전화번호를 입력하세요 : ");          String phone = scanner.nextLine();
        System.out.println("본인 부서 번호를 입력하세요 : ");     int partno = scanner.nextInt(); scanner.nextLine();
        System.out.println("이메일을 입력하세요 : ");            String email = scanner.nextLine();

        //객체화
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEid(id);             employeeDTO.setEpw(pw);        employeeDTO.setEname(name);
        employeeDTO.setEphone(phone);       employeeDTO.setPartno(partno); employeeDTO.setEemail(email);

        //컨트롤러에 전달/결과 받기
        int result = EmployController.getInstance().signUp(employeeDTO);

        if(result ==0){
            System.out.println("<안내> 회원가입에 성공하였습니다.");

        }else if (result == 1){
            System.out.println("<안내> 회원가입에 실패했습니다.");
        }else if (result == 2){
            System.out.println("<안내> 중복된 아이디 입니다.");
        }
    }

    public boolean logIn(){
        //입력
        System.out.println("아이디 : ");       String id = scanner.nextLine();
        System.out.println("비밀번호 : ");      String pw = scanner.nextLine();

        //객체화
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEid(id); employeeDTO.setEpw(pw);

        boolean result = EmployController.getInstance().logIn(employeeDTO);

        if(result){
            System.out.println("<안내> 로그인 성공");
        }else{
            System.out.println("<안내> 로그인 실패");
        }
        return result;
    }

    public void exit(){
        System.out.println("회원 탈퇴 아이디 : ");     String id = scanner.nextLine();
        System.out.println("회원 탈퇴 비밀번호 : ");    String pw = scanner.nextLine();

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEid(id);     employeeDTO.setEpw(pw);


        boolean result = EmployController.getInstance().exit(employeeDTO);

        if(result){
            System.out.println("<안내>회원 탈퇴를 성공했습니다.");
        }else {
            System.out.println("<안내>회원 탈퇴에 실패했습니다.");
        }

    }

    public void employeeInfo(){

        System.out.println("직원 정보를 출력합니다.");

        //객체화
        EmployeeDTO employeeDTO = new EmployeeDTO();

        ArrayList<EmployeeDTO> result = EmployController.getInstance().employeeInfo(employeeDTO);


        //결과 출력

    }

}
