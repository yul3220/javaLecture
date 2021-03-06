package kr.or.ddit.basic;

// yield()메서드 연습

public class ThreadTest13 {
	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("111111111 +++++++++++++++++++++++++++++++++");
		
		th1.work = false;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("222222222 +++++++++++++++++++++++++++++++++");
	
		th1.work = true;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("333333333 +++++++++++++++++++++++++++++++++");
		
		th1.stop = true;
		th2.stop = true;
	}
}//

// yield()메서드 연습을 쓰레드
class YieldThread extends Thread{
	public boolean stop = false;
	public boolean work = true;
	
	public YieldThread(String name){
		super(name); //  쓰레드의 이름 설정하기
	}

	@Override
	public void run() {
		while(!stop){ // stop변수가 true이면 반복문이 끝난다.
			if(work){
				System.out.println(getName() + "-- 작업 중...");
			}else{
				System.out.println(getName() + "-- 양보!!!!!!");
				Thread.yield();
			}
		}
	}
}