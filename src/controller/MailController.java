package controller;


import model.dao.MailDao;

import java.util.ArrayList;
import java.util.Map;

public class MailController {
    // 싱글톤
    private static MailController mailController = new MailController();
    private MailController(){};
    public static MailController getInstance(){
        return mailController;
    }

    public boolean emailCheck(String email){
        if(MailDao.getInstance().emailSearch(email)!=-1){ //eno가 있으면 true
            return true;
        }
        return false;
    }
    public boolean sendMail(int partno){ // 부서별
        return MailDao.getInstance().sendMail(partno);
    }

    public boolean sendMail(ArrayList<Map<String,String>> sendemailarr){ // 직접 입력
        return MailDao.getInstance().sendMail(sendemailarr);
    }

}
