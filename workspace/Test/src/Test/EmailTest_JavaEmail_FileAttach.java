package Test;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailTest_JavaEmail_FileAttach {
	public static void main(String[] args) {
		// 이메일을 보낼 사람
		String to = "thelovemsg@naver.com";

		// 이메일을 받을 사람
		String from = "sunjune1828@gmail.com";

		// 보낼 이메일의 아이디와 비밀번호
		final String username = "sunjune1828";// change accordingly
		final String password = "tjs15915!!@Q";// change accordingly

		// 사용하길 원하는 이메일 주소
		String host = "smtp.gmail.com";

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
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Testing Subject");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText("This is message body i made for test");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "D:\\A_TeachingMaterial\\5.JQuery\\images\\shutup.png";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);

			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}