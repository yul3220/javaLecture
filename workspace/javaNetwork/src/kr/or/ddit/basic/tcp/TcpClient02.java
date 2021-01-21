package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient02 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.0.66", 7777);
		System.out.println("서버에 연결되었습니다..");
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}