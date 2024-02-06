package view;

import controller.EmployController;
import controller.ReportController;
import model.dto.*;

import java.util.*;

public class ReportView {
    MainView application = new MainView();

    public void allReport() {
        while (true) {
            TreeMap<ReportDTO, Boolean> reportDTOS2 = ReportController.getInstance().allReport2();
            ArrayList<Integer> reportS = allsend(reportDTOS2);

            System.out.println("0.뒤로가기 1.개별보고서 보기");
            int ch = application.scanner.nextInt();
            if (ch == 0) {
                return;
            } else if (ch == 1) {
                System.out.println("보고싶은 게시물 번호 입력");
                int ch2 = application.scanner.nextInt();
                if (reportS.contains(ch2)) {
                    ReportDTO reportDTO = ReportController.getInstance().specificreport(ch2);
                    while (true) {
                        System.out.println(reportDTO.toString());

                        TreeMap<Integer, Boolean> superiors = ReportController.getInstance().findSuperiors(reportDTO.getReportno());
                        for (Map.Entry<Integer, Boolean> entry : superiors.entrySet()) {
                            Integer key = entry.getKey();
                            Boolean value = entry.getValue();
                            // 키와 값을 사용하여 작업 수행
                            System.out.println("결재자: " + ReportController.getInstance().enoSearch(key).getEname() + ", 결재상태: " + value);
                        }
                        System.out.println("0.뒤로가기"+(reportDTO.getEno()== EmployController.loginEno.getEno()?"1.게시물삭제":"1.결재승인"));
                        int ch3 = application.scanner.nextInt();
                        if (ch3 == 0) {
                            break;
                        }
                        else if (ch3 == 1 && reportDTO.getEno()== EmployController.loginEno.getEno()){
                            System.out.println("삭제함수 실행");
                        }
                        else if (ch3 == 1) {
                            if (ReportController.getInstance().accept(reportDTO.getReportno())) {
                                System.out.println("결재완료");
                            }
                            else {
                                System.out.println("결재실패");
                            }
                        }

                    }
                }
                else {
                    System.out.println("볼 권한이 존재하지 않는 보고서 번호입니다");
                }
            }
        }
    }
    public void goReport() {
        TreeMap<ReportDTO, Boolean> reportDTOS = ReportController.getInstance().goReport();
        ArrayList<Integer> reportS = allsend(reportDTOS);
        System.out.println("0.뒤로가기 1.게시물확인");
        int ch = application.scanner.nextInt();
        if (ch == 1) {
            System.out.println("보고싶은 게시물 번호 입력");
            int ch2 = application.scanner.nextInt();
            if (reportS.contains(ch2)) {
                ReportDTO reportDTO = ReportController.getInstance().specificreport(ch2);
                while (true) {
                    System.out.println(reportDTO.toString());
                    TreeMap<Integer, Boolean> superiors = ReportController.getInstance().findSuperiors(reportDTO.getReportno());
                    for (Map.Entry<Integer, Boolean> entry : superiors.entrySet()) {
                        Integer key = entry.getKey();
                        Boolean value = entry.getValue();
                        // 키와 값을 사용하여 작업 수행
                        System.out.println("결재자: " + ReportController.getInstance().enoSearch(key).getEname() + ", 결재상태: " + value);
                    }
                    System.out.println("0.뒤로가기" + (reportDTO.getEno() == EmployController.loginEno.getEno() ? "1.보고서삭제 " : " 1.결재승인"));
                    int ch3 = application.scanner.nextInt();
                    if (ch3 == 0) {
                        break;
                    } else if (ch3 == 1 && reportDTO.getEno() == EmployController.loginEno.getEno()) {
                        System.out.println("삭제함수 실행");
                        if (ReportController.getInstance().reportDelete(reportDTO.getReportno())) {
                            System.out.println("삭제성공");
                            return;
                        } else {
                            System.out.println("삭제 실패");
                        }
                    } else if (ch3 == 1) {
                        if (ReportController.getInstance().accept(reportDTO.getReportno())) {
                            System.out.println("결재완료");
                        } else {
                            System.out.println("결재실패");
                        }
                    }
                }
            }
            else {
                System.out.println("볼 권한이 없는 게시물입니다");
            }
        }
    }
    public boolean writeReport() {
        ArrayList <Integer> reportlist = findCategories();
        System.out.println("보고서 카테고리 번호 입력"); //추후 보고서 출력 구현
        int ch = application.scanner.nextInt();
        if(!reportlist.contains(ch)){
            System.out.println("없는 카테고리입니다");
            return false;
        }
        System.out.println("============"+ReportController.getInstance().findType(ch)+"=========");
        ReportDTO reportDTO = null;
        if(ch == 1){
            System.out.println("제목 입력");
            String title = application.scanner.next();
            System.out.println("업무 내용 입력");
            String content1 = application.scanner.next();
            System.out.println("업무 결과 입력");
            String content2 = application.scanner.next();
            reportDTO = new WorkreportDTO(title,content1,content2);
        }

        if(ch == 2){
            System.out.println("제목 입력");
            String title = application.scanner.next();
            System.out.println("휴가사유 입력");
            String content = application.scanner.next();
            System.out.println("휴가 시작일 입력");
            String startdate = application.scanner.next();
            System.out.println("휴가 종료일 입력");
            String enddate = application.scanner.next();
            reportDTO = new VacationreportDTO(title,content,startdate,enddate);
        }
        if(ch == 3){
            System.out.println("제목 입력");
            String title = application.scanner.next();
            System.out.println("구매사유 입력");
            String content = application.scanner.next();
            System.out.println("품목/개수/개별가격 입력");
            String itemlist = application.scanner.next();
            System.out.println("총가격 입력");
            int totalprice = application.scanner.nextInt();
            reportDTO = new PurchasereportDTO(title,content,itemlist,totalprice);
        }

        ArrayList<Integer> superiors = findSuperior();
        System.out.println("보낼 사람 수 선택");
        int users = application.scanner.nextInt();
        if (users > superiors.size()) {
            System.out.println("너무 많습니다");
            return false;
        }
        ArrayList<Integer> userArray = new ArrayList<>();
        for (int i = 0; i < users; i++) {
            System.out.println("보내고싶은 사람의 번호 입력");
            int user = application.scanner.nextInt();
            if (superiors.contains(user) && !userArray.contains(user)) {
                userArray.add(user);
            } else {
                System.out.println("보낼 권한이 없거나 이미 선택한 상사입니다");
                return false;
            }
        }
        return ReportController.getInstance().reportWrite(reportDTO, userArray);

    }

    public ArrayList<Integer> findSuperior(){
        ArrayList<EmployeeDTO> superior = ReportController.getInstance().findSuperior();
        System.out.println("보낼수 있는 사람 ");
        ArrayList<Integer> superiors = new ArrayList<>();
        for (EmployeeDTO i : superior) {
            System.out.println("번호 :" + i.getEno());
            System.out.println("성함 :" + i.getEname());
            superiors.add(i.getEno());
        }
        return superiors;
    }

    public ArrayList<Integer> allsend(TreeMap<ReportDTO, Boolean> send){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            for (Map.Entry<ReportDTO, Boolean> entry : send.entrySet()) {
                if(entry.getKey() == null){
                    System.out.println("삭제된 글입니다");
                    return result;
                }
                System.out.println("리포트 번호:"+entry.getKey().getReportno()+" 제목 : "+entry.getKey().getReporttitle()+"상태 : "+ (entry.getValue() ? "결재완료" : "결재대기")+" 타입 :"+ReportController.getInstance().findType(entry.getKey().getCno()) );
                result.add(entry.getKey().getReportno());
            }
        } catch (Exception e) {
            System.out.println("서류함이 비었습니다");
        }
        return result;
    }

    public ArrayList<Integer> findCategories(){
        ArrayList<Integer> result1 = new ArrayList<>();
        ArrayList<HashMap<Integer,String >> result = ReportController.getInstance().findCategories();
        for(HashMap<Integer,String >   i : result){
            for(Map.Entry<Integer,String > entry: i.entrySet()){
                System.out.println("카테고리 번호 : "+entry.getKey()+"카테고리 제목: "+entry.getValue()+"\n");
                result1.add(entry.getKey());
            }

        }
        return result1;
    }

}