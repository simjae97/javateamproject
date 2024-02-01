package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuperDao {
    protected Connection conn;
    protected PreparedStatement ps; //작성된 sql을 가지고있고 실행 담당
    protected ResultSet rs;
    protected ResultSet rs2;
    SuperDao(){
        try {
            //1.MYSQL 회사의 JDBC관련된 객체를 JVM에 로딩한다
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.연동된 결과의 객체를 connetction 인터페이스에 대입한다
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phoenix", "root", "1234");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
