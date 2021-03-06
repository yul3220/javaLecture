package kr.or.ddit.basic;

// 데몬 쓰레드 연습 ==> 자동 저장하는 쓰레드

public class ThreadTest09 {
	public static void main(String[] args) {
		AutoSaveThread auto = new AutoSaveThread();
		
		// 데몬 쓰레드로 설정하기 ==> 반드시 start()메서드 호출 전에 실행한다.
		auto.setDaemon(true);
		//데몬쓰레드로 정의를 안하는 경우 메인메소드가 끝나도 계속 돌아간다.
		//데몬쓰레드로 정의를 하면 일반쓰레드가 종료되면 자동으로 종료된다.
		auto.start();
		
		try{
			for(int i=1; i<=20; i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 쓰레드 종료...");
	}
}//

// 자동 저장하는 쓰레드(3초에 한번씩 자동 저장하는 쓰레드)
class AutoSaveThread extends Thread{
	// 작업 내용을 저장하는 메서드
	public void save(){
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();
		}
	}
	
}