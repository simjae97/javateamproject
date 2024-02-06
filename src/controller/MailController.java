package controller;


import model.dao.MailDao;
import model.dao.SuperDao;
import model.dto.MailDTO;
import model.dto.PartDTO;

import java.util.ArrayList;
import java.util.Map;

public class MailController {
    // 싱글톤
    private static MailController mailController = new MailController();
    private MailController(){};
    public static MailController getInstance(){
        return mailController;
    }

    public ArrayList<PartDTO> partView(){ // 파트 가져오기
        return MailDao.getInstance().partView();
    }

    public boolean emailCheck(String email){
        if(MailDao.getInstance().emailSearch(email)!=-1){ //eno가 있으면 true
            return true;
        }
        return false;
    }
    public int[] sendpartMail(int partno){ // 부서별 eno 검색
        return MailDao.getInstance().sendpartMail(partno);
    }

    public boolean sendpartMail(ArrayList<Map<String,String>> sendemailarr){ // 부서별 실제 보내기
        return MailDao.getInstance().sendpartMail(sendemailarr);
    }

    public boolean sendMail(ArrayList<Map<String,String>> sendemailarr){ // 직접 입력
        return MailDao.getInstance().sendMail(sendemailarr);
    }

    public ArrayList<Map<String,String>> receiveMail(int loginEno){
        return MailDao.getInstance().receiveMail(loginEno);
    }

}
