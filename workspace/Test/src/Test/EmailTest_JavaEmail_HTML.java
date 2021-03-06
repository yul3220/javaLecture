package Test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest_JavaEmail_HTML {
	public static void main(String[] args) {

		// 받는 사람 이메일
		String to = "thelovemsg@naver.com";

		// 보내는 사람 이메일
		String from = "sunjune1828@gmail.com";

		// 보내는 사람 이메일 계정
		final String username = "sunjune1828";

		// 보내는 사람 이메일 계정 비밀번호
		final String password = "tjs15915!!@Q";

		// 사용하길 원하는 이메일 주소
		String host = "smtp.gmail.com";

		// SMTP 서버 정보를 설정한다. (SMTP - simple mail transfer protocol : 간이 우편 전송
		// 프로토콜)

		/*
		 * Properties는 속성 SMTP 프로토콜 공급자로 JavaMail 세션 개체에서 설정할 수있습니다. 속성은 항상 문자열로
		 * 설정됨 ,Map처럼 Key - Value 형식으로 삽입 가능
		 */

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// 설정한 SMTP 정보를 넘겨줌
		// Session 클래스의 getDefaultInstance() 메소드는 파라미터로 전달받은 Properties에 저장되어 있는
		// 속성값을 사용하여 세션을 생성

		// PasswordAuthentication <= 사용자 명과 패스워드를 위한 리포지터리(repository) / 저장소
		// 매개변수로 사용할 이메일의 아이디와 비밀번호를 입력한다.

		// Authenticator는 네트워크 연결을 위해 인증을 얻기 위한 수단으로 사용하는 객체
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			// 메시지 입력을 위한 인스턴스 생성
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// 수신자메일주소.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Subject - 제목을 입력
			message.setSubject("Testing Subject");

			// Text - HTML 메일 내용 입력
			message.setContent(
					"<h1>This is actual message embedded in HTML tags</h1>",
					"text/html");

			// send the message - 메일 전송
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}