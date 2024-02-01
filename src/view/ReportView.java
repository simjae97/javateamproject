package view;

import controller.ReportController;
import model.dto.ReportDTO;

import java.util.*;

public class ReportView {
    Application application = new Application();
    ReportController reportController = new ReportController();
    public void allReport() {
        while (true) {
            System.out.println("안녕하세요");
//            System.out.println("결재한서류");
//            ArrayList<ReportDTO> reportDTOS = reportController.allReport();
//            if(reportDTOS != null) {
//                for (ReportDTO i : reportDTOS) {
//                    System.out.println("번호: "+i.getReportno()+"제목: "+i.getReporttitle());
//                }
//            }


            TreeMap<ReportDTO,Boolean> reportDTOS2 = reportController.allReport2();

//            HashMap<ReportDTO,Boolean> reportDTOS2 = reportController.allReport2();

            try {
                for (Map.Entry<ReportDTO, Boolean> entry : reportDTOS2.entrySet()) {
                    System.out.println("번호"+entry.getKey().getReportno()+"제목 :" + entry.getKey().getReporttitle() + " 상태 :" + (entry.getValue() ?"결재완료":"결재대기"));
                }
            }
            catch (Exception e){
                System.out.println("서류함이 비었습니다");
            }

//            if(reportDTOS != null) {
//                for (ReportDTO i : reportDTOS2) {
//                    System.out.println("번호: "+i.getReportno()+"제목: "+i.getReporttitle());
//                }
//            }

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

            else if(ch==2) {
                System.out.println("보고싶은 게시물 번호 입력");
                int ch2 = application.scanner.nextInt();
                ReportDTO reportDTO = reportController.specificreport(ch2);
                while (true) {
                    System.out.println("제목 : " + reportDTO.getReporttitle());
                    System.out.println("번호 :" + reportDTO.getReportno());
                    System.out.println("내용 : " + reportDTO.getReportcontent());
                    System.out.println("보낸사람 :" + reportDTO.getEno());//eno로 사람 찾는 함수로 변경
                    System.out.println("보낸 일자 : " + reportDTO.getReportdate());
                    System.out.println("0.뒤로가기 1.결재승인");
                    int ch3 = application.scanner.nextInt();
                    if(ch3 == 0){
                        break;
                    }
                    else if(ch3 == 1){
                        if(reportController.accept(reportDTO.getReportno())){
                            System.out.println("결재완료");
                        }
                        else {
                            System.out.println("결재실패");
                        }
                    }

                }
            }
        }
    }

    public void goReport(){
        TreeMap<ReportDTO,Boolean> reportDTOS = reportController.goReport();
        try {
            for (Map.Entry<ReportDTO, Boolean> entry : reportDTOS.entrySet()) {
                System.out.println("번호"+entry.getKey().getReportno()+"제목 :" + entry.getKey().getReporttitle() + " 상태 :" + (entry.getValue() ?"결재완료":"결재대기"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}