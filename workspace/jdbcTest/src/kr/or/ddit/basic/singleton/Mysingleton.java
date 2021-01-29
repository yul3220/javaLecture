package kr.or.ddit.basic.singleton;
/*
 	singleton패턴 ==> 객체가 1개만 만들어지게 하는 방법
 		( 외부에서 new 명령을 사용하지 못하게 한다.)
 	
 	- singleton 클래스 작성하는 방법(필수 구성 요소)
 	1. 자기 자신 class의 참조값이 저장될 변수를 private static으로 선언한다.
 	
 	2. 생성자의 접근 제한자를 private으로 한다.
 	
 	3. 자기 자신 class의 인스턴스를 생성하고, 반환하는 메서드를 public static으로 작성한다.
 		(이 메서드의 이름은 'getInstance()'로 한다.)
*/
// 메모리 절약할 수 있다. 데이터를 공유할 때 사용
//쓰레드를 사용하면서 하는 것도 존재하지만 현재 시점에서는 거기까지 진도를 나가지 않음
//But,만드는 방법을 알아야 한다.
//service, dao => 싱글톤으로 많이 만든다.
public class Mysingleton {
	// 1번
	private static Mysingleton single;
	
	// 2번
	private Mysingleton(){
		System.out.println("생성자입니다...");
	}
	
	//3번
	public static Mysingleton getInstance(){
		if(single==null) single = new Mysingleton();
		return single;
	}

	// 기타 이 클래스가 처리할 내용을 기술한다.
	public void displayTest(){
		System.out.println("싱글톤 클래스의 메서드 호출입니다.");
	}
}
