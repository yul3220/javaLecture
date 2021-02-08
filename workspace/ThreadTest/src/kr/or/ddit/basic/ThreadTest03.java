package kr.or.ddit.basic;

public class ThreadTest03 {
	public static void main(String[] args) {
		// 쓰레드가 수행되는 시간을 체크해보자.
		Thread th = new Thread(new MyRunner());
		
		// 1970년 1월1일 0시0분0초(표준시간)으로부터 경과한 시간을
		// 밀리세컨드(1/1000) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		//start()는 메서드를 호출하면 쓰레드가 실행될 영역을 만들고 
		//run이라는 메서드를 만들고 끝낸다.
		//시간을 구할려면 run()메서드가 끝나지 않고 기다려야 한다.
		//이 메서드를 사용하기전에는 경과시간이 먼저나오고 합계가 나왔다.
		try{
			th.join(); // 현재 실행중인 쓰레드에서 대상이 되는 쓰레드(여기에서는 th)가
						// 종료될때까지 기다린다.
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + (endTime-startTime));
		
	}
}//

// 1 ~ 10억까지의 합계를 구하는 쓰레드
class MyRunner implements Runnable{
	@Override
	public void run() {
		//이 run()안에서 걸리는 시간을 구하면 싱글쓰레드의 시간을 구하는 것이다.
		long sum = 0L;
		for(long i = 1L; i<=1_000_000_000L; i++){
			//3자리마다 언더바를 사용할 수 있음(,용도)
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}