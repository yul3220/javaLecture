package kr.or.ddit.basic;

/*
 	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다.
 	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어
 	나중에 실행되는 프로그램에 영향을 줄 수 있다.
 	그래서 stop()메서드는 비추천으로 되어 있다.
 */

public class ThreadTest14 {
	public static void main(String[] args) {
		/*ThreadStopTest1 th1= new ThreadStopTest1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th1.setStop(true);
		*/
		
		// interrupt()메서드를 이용한 쓰레드 멈추기
		ThreadStopTest2 th2= new ThreadStopTest2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th2.interrupt();
	}
}

// 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop){
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop){
			System.out.println("쓰레드 실행중....");
		}
		
		System.out.println("자원 정리...");
		System.out.println("쓰레드 종료...");
	}
}

// interrupt()메서드를 이용하여 쓰레드를 멈추게 하는 연습
class ThreadStopTest2 extends Thread{
	@Override
	public void run() {
		
		
		// 방법1
		/*try{
			while(true){
				System.out.println("실행 중....");
				
				// 일시 정지 상태에서 interrupt()메서드가 실행되면
				// InterruptedException이 발생한다.
				Thread.sleep(1);
			}
		}catch(InterruptedException e){
			
		}*/
		
		//방법2
		while(true){
			System.out.println("Thread 실행중...");
			
			// interrupt() 메서드가 호출되었는지 검사한다.
			
			// 검사방법1) Thread의 인스턴스 메서드인 isInterrupted()를
			// 			이용한다. ==> 이 메서드는 interrupt()메서드가
			//			호출되면 true를 반환한다.
			/*if(this.isInterrupted() == true){
				break;
			}*/
			
			// 검사방법2) Thread의 정적 메서드인 interrupted()메서드를
			// 			이용한다. ==> 이 메서드도 interrupt()메서드가
			// 			호출되면 true를 반환한다.
			//외부에서 강제적으로 종료시키고 싶을때 사용한다.
			/*if(Thread.interrupted()){
				break;
			}*/
		}
		
//		System.out.println("자원 정리.....");
//		System.out.println("쓰레드 종료.....");
	}
}
