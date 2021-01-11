package kr.or.ddit.basic;
/*
 	- enum(열거형) ==> 서로 관련있는 상수들의 집합을 나타낸다.
 		=⇒  클래스처럼 보이게하는 상수
		=⇒  열거형은 클래스처럼 독립된 java파일에 만들수 있고, 
			하나의 java파일에 클래스와 함께 만들수 있고,
			클래스 내부에 내부 클래스처럼 만들수 있다.
			
	- 열거형의 속성 및 메서드
		1) name() ==> 열거형 상수의 이름을 문자열로 반환한다.
		2) ordinal() ==> 열거형 상수가 정의된 순서값(index값)을 반환한다.
						 (0부터 시작)
		3) valueOf("열거형상수형") ==> 지정된 열거형에서 '열거형 상수명'과 일치하는 열거형 상수를 반환한다.
		4) 열거형이름.상수명 ==> valueOf()메서드와 기능이 같다.
		
	- 열거형 선언하기
		방법1) enum 열거형이름{ 상수명1, 상수명2, .....}
		방법2) ==> 각각의 상수에 임의의 값을 설정할 수 있는 열거형 만들기
			enum 열거형이름{
				상수명1(값들.....),
				상수명2(값들.....),
				....
				상수명n(값들.....);
			}
			
			// '값들'이 저장될 변수 선언
			private 자료형이름 변수명1;
			private 자료형이름 변수명2;
			....
			
			// 열거형의 생성자를 만든다.
			// 열거형의 생성자는 '열거형상수'에 '값들'을 셋팅하는 역할을 수행한다.
			// (열거형 생성자는 묵시적으로 private이다.)
			 
			// '변수명'의 개수는 '값들'의 개수와 같고, 
			// 각각의 '값들'의 선언된 변수와 자료형이 같아야 한다.
			 private 열거형이름(자료형명 변수명. ...){
			 	위에 선언된 변수들을 초기화하는 작업을 수행한다.
			 	....
			 }
			 
			 // 구성된 '값들'을 외부에서 불러올 수 있도록 getter메서드를 작성한다.
			  
		==> 주로 1번 방법을 사용하나, 2번 방법을 사용하는 경우도 존재
 */
public class EnumTest {
	//클래스 내부에 내부클래스처럼 만들수 있음, 열거형을 선언할때는 마지막에 세미클론을 붙이지 않는다.
	//방법1로 선언하기
	public enum Color{RED, GREEN, BLUE}
	
	public enum Count{ONE, TWO, THREE}
	
	// '방법2'로 열거형 선언하기 
	public enum Season{
		봄("3월부터 5월까지", 3),
		여름("6월부터 8월까지", 6),
		가을("9월부터 11월까지", 9),
		겨울("12월부터 2월까지", 12);
		
		// 변수 선언
		private String span;
		private int startMonth;
		
		// 생성자
		Season(String span, int startMonth){
		//private Season(String span, int startMonth){} 와같다.
			this.span = span;
			this.startMonth = startMonth;
		}
		
		// getter메서드
		public String getSpan(){
			return span;
		}
		
		public int getStartMonth(){
			return startMonth;
		}
	}
	
	public static void main(String[] args) {
		/*
		System.out.println("Red => " + ConstTest.RED);
		System.out.println("Three => " + ConstTest.THREE);
		
		if(ConstTest.RED == ConstTest.ONE){
			System.out.println("....");
		}else{
			System.out.println("???");
		}*/
		
		//방법1 사용
		Color myCol = Color.valueOf("GREEN"); // Color.GREEN와 같다.
		Count myCnt = Count.ONE; // Count.valueOf("ONE")와 같다.
		
		System.out.println("myCol : " + myCol.name());
		System.out.println("myCnt : " + myCnt.name());
		System.out.println();
		
		System.out.println("myCol oridinal : " + myCol.ordinal());
		System.out.println("myCnt oridinal : " + myCnt.ordinal());
		
		// 서로 다른 종류의 열거형끼리의 비교는 불가
//		if(myCol == myCnt){
//			System.out.println("***");
//		}
		
		if(myCol == Color.GREEN){
			System.out.println("같다.");
		}else{
			System.out.println("같지 않다.");
		}
		System.out.println();
		
		// switch문에서의 비교
		// switch문의 case에 상수명을 지정할 때는 '열거형이름'을 생략하고
		// 		'상수명'만 지정한다.
		switch(myCnt){
		case ONE : 
			System.out.println("상수 ONE");
			break;
		case TWO : 
			System.out.println("상수 TWO");
			break;
		case THREE : 
			System.out.println("상수 THREE");
			break;
		}
		System.out.println("--------------------------------------");
		
		//방법2 사용
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("oridinal : " + ss.ordinal());
		System.out.println("span : " + ss.span);
		System.out.println("startMonth : " + ss.startMonth);
		System.out.println();
		
		// 열거형명.values()메서드 ==> 모든 상수들을 배열로 가져온다.
		for(Season time : Season.values()){
			System.out.println(time.name() + "==" + time
					+ " ==> " + time.getSpan());
		}
		System.out.println();
		
		for(Color col : Color.values()){
			System.out.println(col + " ==> " + col.ordinal());
		}
	}
}//
