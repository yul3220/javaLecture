package kr.or.ddit.basic;

public class ArgsTest {
	
	// 메서드 만들기
	
	// 매개변수로 받은 정수들의 합계를 구하는 메서드를 작성하시오.
	// (매개 변수의 정수들은 상황에 따라 개수가 다르다.)
	
	// 배열을 이용한 메서드
	public int sumArr(int[] data){
		int sum = 0;
		for(int i=0; i< data.length;i++){
			sum+=data[i];
		}
		return sum;
	}
	
	/*
	 	가변형 인수 ==> 메서드의 매개변수의 개수가 호출될 때마다 다를 때 사용한다.
	 	  - 가변형 인수는 메서드 안에서는 배열로 처리된다.
	 	  - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	 	  변수형... 변수이름 => int...data = int[] data
	 */
	public int sumArg(int...data){
		int sum = 0;
		for(int i=0; i< data.length;i++){
			sum+=data[i];
		}
		return sum;
	}
	
	// 가변형 인수와 일반적인 인수를 같이 사용하기
	// ==> ※가변형 인수를 제일 뒤쪽에 배치해야 한다.
	
	//int... data, String name은 불가능
	//뒤에 일반적인 변수를 놓게되면 가변형 인수와 타입이 같을 때 문제가 발생
	// sumArg2(1,2,3,4,5,6,7)
	// public String sumArg2(int name, int...data)(가능)
	// public String sumArg2(int...data, int name)(불가능)
	// ==> 어디까지가 가변형인수인지를 모른다. 
	//public String sumArg2( int...data, String name){ (불가능)
	
	public String sumArg2(String name, int...data){
		int sum = 0;
		for(int i=0; i< data.length;i++){
			sum+=data[i];
		}
		return name+ "씨의 합계 : " + sum;
	}
	
	public void test(int a){
		
	}
	
	public static void main(String[] args) {
		ArgsTest at = new ArgsTest();
		
		int[] nums = {100, 200, 300};
		
		int result = at.sumArr(nums);
		System.out.println(result);
		System.out.println(at.sumArr(new int[]{1 , 2 , 3 , 4 , 5}));
		//System.out.println(at.sumArr({1 , 2 , 3 , 4 , 5})) => 에러가 뜸
		
		int k = 100;
		at.test(k);
		at.test(300);
		System.out.println();
		System.out.println("가변형 인수 사용 예");
		System.out.println(at.sumArg(100,200,300));
		System.out.println(at.sumArg(1,2,3,4,5));
		//사용할때도 new를 통해 새로 만들지 않아도 된다.
		System.out.println();
		System.out.println(at.sumArg2("홍길동", 1,2,3,4,5,6));
		
	}
}//
