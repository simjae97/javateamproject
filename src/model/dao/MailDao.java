package model.dao;

import controller.EmployController;
import controller.MailController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MailDao extends SuperDao{
    // 싱글톤
    private static MailDao mailDao = new MailDao();
    private MailDao(){};
    public static MailDao getInstance(){
        return mailDao;
    }

    public int emailSearch(String eemail){ // email으로 eno 찾기
        try{
            String sql = "select eno from employee where = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, eemail);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("eno");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }

    public void partnoSearch(){ // 파트넘버로 검색해서 사람들 구하기
        return;
    }

    public boolean sendMail(int partno){ // 부서번호 받아서.
        try{

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean sendMail(ArrayList<Map<String,String>> sendemailarr){
        try{
            String sql1 = "insert into mail(eno, mailtitle, mailcontetnt) values (?,?,?)";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, EmployController.loginEno.getEno()); // 로그인 중인 사람 번호 넣기
            ps.setString(2, sendemailarr.get(sendemailarr.size()-1).get("mailtitle"));
            ps.setString(3, sendemailarr.get(sendemailarr.size()-1).get("mailcontetnt"));
            // 2, 3번째에 마지막 인덱스에서 가져온 제목 내용 넣기

            if(ps.executeUpdate()==1){ // mail table에 데이터 넣고, 이제 maillog에도 넣어야한다.(들어갈 값 : 받는사람들eno, mailno(fk))
                int mailno = ps.getGeneratedKeys().getInt("mailno"); // 방금 넣은 레코드의 mailno 저장.

                int[] arr = new int[sendemailarr.size()-2]; // 마지막 전까지 eno 넣을 배열 미리 만들기
                for(int i = 0 ; i<sendemailarr.size()-1 ; i++){
                    arr[i] = emailSearch(sendemailarr.get(i).get("email"));  // 빼온 email 값으로 eno 추출하기
                }
                String sql2 = "insert into maillog(mailno, eno) values (?,?)";
                for(int i = 0; i<sendemailarr.size()-1 ; i++) { // 마지막 전까지 email값 뽑아내서 넣기
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, mailno);
                    ps.setInt(2, arr[i]);
                    ps.executeUpdate(); // 한 줄 한 줄 업데이트.
                }
                return true; // 완료되면 트루
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

}
