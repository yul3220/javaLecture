package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

// 서버에 접속되면 'd:/d_other/코알라.jpg'파일을 서버로 전송한다.
public class TcpFileClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverIp = "localhost"; 
		System.out.println("localhost 서버에 접속 중입니다.....");
		
		Socket socket = new Socket(serverIp, 7777);
		
		System.out.println("서버에 연결되었습니다....");
		System.out.println();
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		Scanner scan = new Scanner(System.in);
		FileInputStream fin = new FileInputStream("d:/d_other/코알라.jpg");
		
		int b = 0;
		while((b=fin.read())!=-1){
			dos.write(b);
		}
		
		fin.close();
		dos.close();
		socket.close();
	}
}
