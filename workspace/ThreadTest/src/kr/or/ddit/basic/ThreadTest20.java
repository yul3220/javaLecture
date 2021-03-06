package kr.or.ddit.basic;

// wait(), notify()메서드를 이용해서
// 두 쓰레드가 번갈아 한번씩 실행되는 예제

// wait(), notify(), notifyAll()메서드는 동기화 영역에서만 사용 가능하다.

public class ThreadTest20 {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}//main
}//

// 공통으로 사용할 객체
class WorkObject{
	public synchronized void testMethodA(int a){
		System.out.println(a+" : testMethodA()메서드 호출....");
		
		notify();//wait()먼저 위에 있으면 둘다 waiting pool에 들어가서 깨워줄사람이 없음
		//waiting pool에서 깨워줄 역할을 함
		
		try {
			wait();
		} catch (InterruptedException e) {
			
		}
		System.out.println(a + " : testMethodA() - wait()메서드 호출 후......");
	}
	
	public synchronized void testMethodB(int a){
		System.out.println(a + " : testMethodB()메서드 호출 ~~~");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			
		}
		System.out.println(a+" : testMethodB - wait()메서드 호출 후 ~~~~~");
	}
}

// workObject의 testMethodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj){
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			workObj.testMethodA(i);
		}
		
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

//workObject의 testMethodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj){
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			workObj.testMethodB(i);
		}
		
		synchronized (workObj) {
			workObj.notify();
		}
	}
}
