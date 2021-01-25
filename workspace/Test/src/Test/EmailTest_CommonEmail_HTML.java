package Test;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailTest_CommonEmail_HTML {
	//deprecation -> 사용하지 말아야 할 메소드 관련 경고 억제
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		try {
			// SimpleEmail 클래스는 간단히 이메일만 보내기 위해서 사용됨(파일첨부 불가)
			HtmlEmail email = new HtmlEmail();

			// 인코딩 - 한글 지원
			email.setCharset("UTF-8");

			// 연결시 SMTP 전송에 대해 SSL / TLS 암호화를 활성화할지 여부를 설정합니다 (SMTPS / POPS).
			email.setSSL(true);

			// SMTP서버의 Host이름이나 IP주소를 입력하도록한다.
			email.setHostName("smtp.naver.com");

			// setAuthentication() 메소드를 사용하여 SMTP 계정 정보인 사용자 아이디와 암호를 설정하면 된다.
			// setAuthentication()메소드에 의해서 설정된계정은 send()메소드에 의해서 SMTP 서버 인증을 받을
			// 때 사용된다.
			email.setAuthentication("thelovemsg", "slal15915!!@");

			// 보내는 사람의 이메일 주소를 넣는다.
			email.setFrom("thelovemsg@naver.com", "thelovemsg");

			// 이메일을 받는 사람의 이메일 주소를 넣는다. 여러 사람에게 보낼 수 있으므로 계속 메소드를 호출해도 무방하다
			email.addTo("sunjune1828@gmail.com", "mememe");
			
			//메일 메시지의 제목을 설정합니다. 실제로 “Subject”헤더를 설정
			email.setSubject("<b>HTML</b> 메일입니다."); // 메일 제목
			
			//Html 메시지를 설정합니다.
			email.setHtmlMsg("<b>HTML<b> 메일 내용입니다.");
			
			// 메일 발송
			email.send(); 
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 보내는 사람

	}

}
