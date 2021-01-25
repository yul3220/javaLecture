package Test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
public class EmailTest_CommonEMail {
	//컴파일러가 일반적으로 경고하는 내용 중 하지 말아야 할 내용을
	//제외시킬 때 쓰이는 것이다. 
	//deprecation -> 사용하지 말아야 할 메소드 관련 경고 억제
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			
			// SimpleEmail 클래스는 간단히 이메일만 보내기 위해서 사용됨(파일첨부 불가)
			SimpleEmail email = new SimpleEmail(); 
			
			// 인코딩을 설정한다.
			email.setCharset("UTF-8");     
			
			//사용하길 원하는 인터넷 메일 주소를 입력한다. 
			email.setHostName("smtp.naver.com");
			
			//SMTP 인증이 필요할 경우 아이디와 비밀번호를 각각 입력한다. (없으면 지워버린다.)
			email.setAuthentication("thelovemsg", "slal15915!!@");
			
			// 보내는 사람의 이메일 주소를 넣는다.
			email.setFrom("thelovemsg@naver.com", "thelovemsg");
			
			//받는사람 - 여러 명의 이메일 주소를 입력해도 된다. 
			email.addTo("sunjune1828@gmail.com", "mememe"); 
			
			 //메일 제목
			email.setSubject("메일의 제목입니다."); 
			
			//메일 내용
			email.setMsg("메일의 내용입니다."); 
			
			//메일 발송
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("송신완료");
	}
}
