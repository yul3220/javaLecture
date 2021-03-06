package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 접속한 클라이언트 정보를 저장할 Map객체 변수 선언
	// ==> key값 : 접속한 사람의 이름, value값 : 접속한 Socket객체
	//client와 연결되어 있는 소켓을 저장하기 위한 공간
	private Map<String, Socket> clientMap;
	
	// 생성자
	public TcpMultiChatServer(){
		// Map객체를 동기화 처리
		clientMap = Collections.synchronizedMap(
					new HashMap<String, Socket>()
				);
	}
	
	// 서버 프로그램의 시작메서드
	public void serverStart(){
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다....");
			System.out.println();
			
			while(true){
				socket = server.accept();// 클라이언트의 접속을 기다린다.
				
				System.out.println("[" + socket.getInetAddress() 
						+ " : " + socket.getPort() + "] 에서 접속했습니다.");
				
				// 데이터를 받아서 전체에게 전송하는 쓰레드 작동
				ServerReceiver serverThread = new ServerReceiver(socket);
				
				serverThread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(server!=null) try {server.close();}catch(IOException e){}
		}
	}
	
	// ClientMap에 저장된 모든 사용자에게 메시지를 전송하는 메서드
	private void sendToAll(String msg){//msg는 전송할 메시지
		// clientMap의 데이터 갯수만큼 반복
		for(String name : clientMap.keySet()){
			try {
				DataOutputStream dos = 
					new DataOutputStream(
						clientMap.get(name).getOutputStream()
					);
				
				dos.writeUTF(msg);//메시지를 전송
				
			} catch (Exception e) {
				
			}
		}
	}
	
	//----------------------------------------------------------------
	// 서버에서 클라이언트로 메시지를 전송하는 Thread를 innerClass로 작성한다.
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;

		// 생성자 
		public ServerReceiver(Socket socket){
			this.socket = socket;
			try {
				// 수신용 스트림 객체 생성
				dis = new DataInputStream(socket.getInputStream());
				// 송신용 스트림 객체 생성
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				
			}
		}//생성자 끝....

		@Override
		public void run() {
			String name = "";// 클라이언트가 보낸 이름이 저장될 변수
			try {
				while(true){
					// 클라이언트가 최초로 보내는 데이터는 사용자의 이름인데
					// 이 이름이 중복되는지 여부를 feedback으로 클라이언트에게 보낸다.
					name = dis.readUTF();// 클라이언트가 보낸 이름
					
					if(clientMap.containsKey(name)){// 이름이 중복될 때
						dos.writeUTF("이름 중복"); // '이름중복'메시지 전송
					}else{// 중복되지 않을 때
						dos.writeUTF("OK");
						break; // 반복문 탈출 //중복되지 않으므로
					}
				}// while문
				
				//이름이 중복되지 않은 사람들이 하게되는 것들
				// 대화명(이름)을 받아서 전체 클라이언트에게 대화방 참여 메시지를 전송한다.
				sendToAll("[" + name + "]님이 들어오셨습니다.");
				
				// 대화명과 클라이언트와 연결된 Socket을 Map에 추가한다.
				clientMap.put(name, socket);
				
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
				
				// 하나의 클라이언트가 보내온 메시지를 받아서 전체 클라이언트들에게 보낸다.
				while(dis!=null){//inputStream이 null이 아니면
					sendToAll(dis.readUTF());
				}
			} catch (Exception e) {
				
			}finally{
				// 이 finally절이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미가 된다.
				sendToAll("[" + name + "]님이 접속을 종료했습니다.");
				
				// 사용자 목록(Map)에서 삭제한다.
				clientMap.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " 
									+ socket.getPort() + "]에서 접속을 종료했습니다.");
				
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
			}//try()
		}//run()
	}
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}
}
