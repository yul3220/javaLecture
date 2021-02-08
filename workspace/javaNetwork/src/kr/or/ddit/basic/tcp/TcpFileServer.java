package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 클라이언트가 보낸 파일을 받아서 'd:/d_other/data'폴더에 
// 보낸 파일과 같은 이름을 저장한다.
public class TcpFileServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다.");
		
		Socket socket = server.accept();
		
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();
		
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		FileOutputStream fout = new FileOutputStream("d:/d_other/data/코알라.jpg");
		
		int b = 0;
		while((b=dis.read())!=-1){
			fout.write(b);
		}
		
		fout.close();
		dis.close();
		socket.close();
		server.close();
	}
}
