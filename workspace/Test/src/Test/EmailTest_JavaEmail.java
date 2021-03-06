package Test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest_JavaEmail {

	public static void main(String[] args) {

		// 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		final String user = "sunjune1828@gmail.com";

		// 패스워드
		final String password = "tjs15915!!@Q";

		// SMTP 서버 정보를 설정한다. (SMTP - simple mail transfer protocol : 간이 우편 전송
		// 프로토콜)
		/*
		 * Properties는 속성 SMTP 프로토콜 공급자로 JavaMail 세션 개체에서 설정할 수있습니다. 속성은 항상 문자열로
		 * 설정됨 ,Map처럼 Key - Value 형식으로 삽입 가능
		 */
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com"); // 내가 사용할 이메일
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true"); // Authentication - 인증
		prop.put("mail.smtp.ssl.enable", "true"); //
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// 설정한 SMTP 정보를 넘겨줌
		// Session 클래스의 getDefaultInstance() 메소드는 파라미터로 전달받은 Properties에 저장되어 있는
		// 속성값을 사용하여 세션을 생성

		// PasswordAuthentication <= 사용자 명과 패스워드를 위한 리포지터리(repository) / 저장소
		// 매개변수로 사용할 이메일의 아이디와 비밀번호를 입력한다.
	
		// Authenticator는 네트워크 연결을 위해 인증을 얻기 위한 수단으로 사용하는 객체
		Session session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

		try {
			// 메시지 입력을 위한 인스턴스 생성
			MimeMessage message = new MimeMessage(session);
			
			//ARPA : 방위고등연구계획국 - 약칭으로 아파넷
			//RFC822: Standard for ARPA Internet Text Messages
			//InternetAddress는 syntax RFC822를 사용하는 인터넷이메일 주소를 나타냄 
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"sunjune1828@naver.com"));

			// Subject - 제목을 입력
			message.setSubject("ghfhffhff호롤롤로");

			// Text - 메일 내용 입력
			message.setText("dkgldgodgdjkfjjㅇ랑라알라");

			// send the message - 메일 전송
			Transport.send(message);

			// 성공 여부 출력
			System.out.println("message sent successfully...");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
