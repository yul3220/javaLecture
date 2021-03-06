package kr.or.ddit.basic.singleton;

public class singletonTest {
	public static void main(String[] args) {
		// 외부에서 new 명령으로 생성 불가
		//Mysingleton test1 = new Mysingleton(); // 오류
		
		Mysingleton test2 = Mysingleton.getInstance();
		Mysingleton test3 = Mysingleton.getInstance();
		
		System.out.println("test2 = " + test2);
		System.out.println("test3 = " + test3);
//		생성자입니다... => 객체는 한번만 생성된것이다.
//		test2 = kr.or.ddit.basic.singleton.Mysingleton@7700b3c2
//		test3 = kr.or.ddit.basic.singleton.Mysingleton@7700b3c2
		
		System.out.println(test2.equals(test3));
		System.out.println(test2==test3); //현재는 참조값이 같기 때문에 서로가 같게 나온다. => true
		
		test2.displayTest();
	}
}
