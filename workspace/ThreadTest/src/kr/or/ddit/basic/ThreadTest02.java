package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		// 방법1
		// Thread클래스를 상속한 class를 작성한 후
		// 이 class의 인스턴스를 생성한 후, 이 인스턴스의 start()메서드를 호출한다.
		MyThread1 th1 = new MyThread1();
		th1.start();

		// 방법2
		// Runnable 인터페이스를 구현한 class를 작성한 후
		// 이 class의 인스턴스를 생성한 후, 
		// 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자에
		// '인자값'으로 넣어서 생성한다.
		// 생성된 Thread의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r2 = new MyThread2();
		Thread th2 = new Thread(r2); // Runnable을 상속받은것은 바로 start를 하지 못함
		th2.start();
		
		/*자바가 상속을 한개만 할수 있기때문에 runnable()과 Thread()를 상속받는것이
		따로 존재한다.
		오버라이딩을 한것은 run()인데 실행할때는 start를 사용
		start()는 쓰레드에 만들어져 있음
		run()을 바로 호출한것은 쓰레드를 사용하지 않고 바로 run()메서드를 실행하게 되는 것
		현재는 쓰레드 3개 => 우리가 만든 쓰레드2게와 메인쓰레드1개
		프로그램은 모든쓰레드가 종료되어야 끝난다.*/
		
		// 방법3 ==> 익명구현체를 이용하는 방법
		Runnable r3 = new Runnable(){
			@Override
			public void run(){
				for(int i = 1; i<=200; i++){
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread th3 = new Thread(r3);
		th3.start();
		
		System.out.println("main()메서드 끝...");
		
		//Runnable은 인터페이스이기때문에 객체를 생성할 수 없음
		//=> 메서드가 구현이 안되어있기때문
		//추상메서드 => 구현이 안되어 있고 선언만 되어있는 메서드
		//추상클래스 => 추상메서드를 가지고 있는 클래스
		//넗게보면 인터페이스도 추상메서드이다.
		//추상클래스는 객체를 생성할 수 없음(구현이 안된게 있으므로)
		
	}
}//

// 방법1 ==> Thread클래스 상속하기
class MyThread1 extends Thread{
	@Override
	public void run() {
		// 이 run() 메서드 안에 이 Thread가 처리할 내용을 기술한다.
		for(int i = 1; i<= 200; i++){
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) ==> 주어진 '시간'동안
				// 		작업을 잠시 멈춘다.
				// 		'시간'의 단위는 밀리세컨드(ms)이다.(1/1000초)		
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}// 이 run()메서드의 실행이 끝나면 해당 Thread도 끝난다.
}

// 방법2 ==> Runnable인터페이스 구현하기
class MyThread2 implements Runnable{
	@Override
	public void run() {
		for(int i = 1; i<= 200; i++){
			System.out.print("$");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}