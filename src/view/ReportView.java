package view;

import controller.ReportController;
import model.dto.ReportDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class ReportView {
    Application application = new Application();
    ReportController reportController = new ReportController();
    public void allReport() {
        while (true) {
            System.out.println("리포트 전체보기 실행");
            reportController.allReport();
            System.out.println("0.뒤로가기 1.보고서 작성 2.개별보고서 보기");
            int ch = application.scanner.nextInt();
            if(ch==0){
                return;
            }
            else if(ch==1){
                System.out.println("보고서 작성함수 뷰 실행 ");
                System.out.println("제목 입력");
                String title = application.scanner.next();
                System.out.println("내용 입력");
                String content = application.scanner.next();
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setReporttitle(title);
                reportDTO.setReportcontent(content);
                System.out.println("보낼 사람 수 선택");
                int users = application.scanner.nextInt();
                ArrayList<Integer> userArray = new ArrayList<>();
                for (int i =0; i< users; i++){
                    System.out.println("보내고싶은 사람의 번호 입력");
                    userArray.add(application.scanner.nextInt());
                }
                boolean result = reportController.reportWrite(reportDTO,userArray);
                if(result){
                    System.out.println("작성 완료");
                }
                else{
                    System.out.println("작성에 실패했습니다.");
                }
            }
            else if(ch==2){
                System.out.println("개별보고서 뷰 보기 함수 실행");
            }

        }
    }
}