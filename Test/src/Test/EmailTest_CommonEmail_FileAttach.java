package Test;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailTest_CommonEmail_FileAttach {

	public static void main(String[] args) {
		// 첨부 파일 생성을 위한 객체 생성
		EmailAttachment attachment = new EmailAttachment();

		// setPath : 실제 파일이 존재하는 경로 주소 설정
		attachment.setPath("D:\\A_TeachingMaterial\\5.JQuery\\images\\shutup.png");

		// 파일에 대한 설명
		attachment.setDescription("Picture of what");

		// 파일 이름 설정
		attachment.setName("what");
		

		try {

			/*
			 * MultiPartEmail 확장으로 , HTML멀티 파트 이메일이다. 이 클래 스는 HTML 형식의 이메일을 보내는
			 * 데 사용됩니다. 텍스트 기반 이메일 클라이언트 와 같은 HTML 인식 이메일 클라 이언트에 대해 텍스트 메시지를
			 * 설정할 수도 있습니다.이 클래스는 MultiPartEmail에서도 상속되므로 이메일 에 첨부 파일을 쉽게 추가 할 수
			 * 있습니다.
			 */

			//인스턴스 객체 생성
			MultiPartEmail email = new MultiPartEmail();

			// 인코딩 - 한글 지원
			email.setCharset("utf-8");

			// SMTP서버의 Host이름이나 IP주소를 입력하도록한다.
			email.setHostName("smtp.naver.com");

			// setAuthentication() 메소드를 사용하여 SMTP 계정 정보인 사용자 아이디와 암호를 설정하면 된다.
			// setAuthentication()메소드에 의해서 설정된계정은 send()메소드에 의해서 SMTP 서버 인증을 받을
			// 때 사용된다.
			email.setAuthentication("thelovemsg", "slal15915!!@");

			// 보내는 사람의 이메일 주소를 넣는다.
			email.setFrom("thelovemsg@naver.com", "SeonJun");

			// 이메일을 받는 사람의 이메일 주소를 넣는다. 여러 사람에게 보낼 수 있으므로 계속 메소드를 호출해도 무방하다
			email.addTo("yangjj94@naver.com", "yangAhhChi");

			// 메일 메시지의 제목을 설정합니다. 실제로 “Subject”헤더를 설정
			email.setSubject("현재 파일 첨부 테스트중입니다. ");

			// 보낼 메시지를 설정합니다.
			email.setMsg("현재 파일 첨부 테스트중입니다. ...호롤롤로 아힝흥행!");

			// 첨부 파일 추가
			email.attach(attachment);

			// 메일 전송
			email.send();

		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("성공!");

	}

}
