package Test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailTest{
   @SuppressWarnings("deprecation")
   public static void main(String[] args) {
      try {
         SimpleEmail email = new SimpleEmail(); // 선언을 한다
         email.setCharset("UTF-8");     // 인코딩을 설정한다.
         email.setHostName("smtp.naver.com");
         
         email.setAuthentication("wlsdbfl04", "10220dbfl"); //SMTP 인증이 필요할 경우(없으면 지워버린다.)
         email.setFrom("wlsdbfl04@naver.com", "yuri");
         email.addTo("wlsdbfl04@naver.com", "mememe"); //받는사람
         email.setSubject("메일의 제목입니다."); //메일 제목
         email.setMsg("메일의 내용입니다."); //메일 내용
         email.send(); //메일 발송
      } catch (EmailException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } //보내는 사람
   }
}