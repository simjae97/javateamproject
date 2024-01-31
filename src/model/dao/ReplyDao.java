package model.dao;

import model.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReplyDao extends SuperDao{

    // 싱글톤
    private ReplyDao(){};
    private static ReplyDao replyDao = new ReplyDao();
    public static ReplyDao getInstance(){return replyDao;}

    public ArrayList<Map> replyWrite(int boardno){ // 리플들을 담을 배열 빼오기.
        ArrayList<Map> result = new ArrayList<>();
        try{
            String sql = "select * from reply where boardno = ? order by replyno;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, boardno);
            rs = ps.executeQuery();
            while (rs.next()){// 한 줄(레코드) 단위로 소환.
                Map<String, String> map1 = new HashMap<>();
                // 리플 하나 하나당 있을 데이터 , 작성자, 부서, 쓴 날짜, 내용
                int eno = rs.getInt("eno"); // 작성자 정보 받을 로직짜기.
                EmployeeDTO employeeDTO = enoSearch(eno);
                map1.put("ename", employeeDTO.getEname());
                map1.put("", employeeDTO.getEname());
                map1.put("replycontent" , rs.getString("replycontent"));
                map1.put("replydate", rs.getString("replydate"));
                result.add(map1); // 댓글 하나 어레이리스트에 넣기
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
}
