package kr.or.ddit.basic;

//쓰레드의 상태값을 출력하는 예제

public class ThreadTest10 {
	public static void main(String[] args) {
		StartPrintThread th 
			= new StartPrintThread(new TargetThread());
		th.start();

	}
}//

// 쓰레드 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=1L; i<=20_000_000_000L;i++){}// 시간 지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for(long i=1L; i<=20_000_000_000L;i++){}// 시간 지연용
	}
}

// 검사 대상 쓰레드의 상태값을 출력하느 쓰레드
class StartPrintThread extends Thread{
	// 검사 대상 쓰레드가 저장될 변수 선언
	private TargetThread target;

	// 생성자
	public StartPrintThread(TargetThread target){
		this.target = target;
	}

	@Override
	public void run() {
		while(true){
			// 쓰레드의 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);

			// 검사 대상 쓰레드가 NEW상태이면....
			if(state==Thread.State.NEW){ 
				//start가 되기 전상태
				target.start(); // 검사 대상 쓰레드를 작동 시킨다.
			}

			// 검사 대상 쓰레드가 종료 상태이면...
			if(state==Thread.State.TERMINATED){
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}