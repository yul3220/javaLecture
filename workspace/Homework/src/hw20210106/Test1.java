package hw20210106;

import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("-----------------1번문제---------------------");
		//	변수 num의 값에 따라 양수, 음수, 0을 출력하는 코드를 작성하세요,
		//	삼항연산자를  이용 
		System.out.print("숫자를 입력> ");
		int num = Integer.parseInt(sc.nextLine());
		String result = (num > 0) ? "양수" : ((num<0) ?  "음수" : "0" ) ;
		System.out.println(num+"은  "+result+"입니다.");

		System.out.println();
		System.out.println("-----------------2번문제---------------------");
		//	문자형 변수 ch가 영문(대문자 또는 소문자)자이거나 숫자일때만 
		//	변수 b의 값이  true가 되도록 하는 코드를 작성하세요 삼항연산자이용 
		//char ch='z'
		char ch='z';

		boolean b = (ch>='A'&&ch<='Z')?  true: (ch>='a'&&ch<='z'? true : (ch>='0'&&ch<='9'? true : false));
		if(b==true){
			System.out.println(ch+"가 영어이거나 숫자입니다.");
		}else{
			System.out.println(ch+"가 영어이거나 숫자가 아닙니다.");
		}

		System.out.println();
		System.out.println("-----------------3번문제---------------------");
		//문자형변수 ch의 값을 소문자로 변경하는 코드를 작성하세요
		//char ch='A'
		ch = 'A';
		char out = 0;
		if('A'<=ch&&ch<='Z'){
			out=(char)(ch + 32);
		}
		System.out.println(ch+"의 소문자는"+out+"입니다.");

		System.out.println();
		System.out.println("-----------------4번문제---------------------");
		//Math.random() 을 이용하여 1~6 사이의 임의의 정수를 변수 value에 저장하는 코드를 작성하세요 
		int value = (int) ((Math.random()* 6)+1) ;
		System.out.println(value);

		System.out.println();
		System.out.println("-----------------5번문제---------------------");
		//1~100 까지의 합을 구하는 코드를 작성하세요 
		int sum = 0;
		for(int i = 1; i<=100;i++){
			sum+=i;
		}
		System.out.println("1~100에서의 합은 "+sum);

		System.out.println();
		System.out.println("-----------------6번문제---------------------");
		//1~100사이의 랜덤수를 발생시켜 저장한다
		//임의의 값을 입력받아 랜덤값을  맞추는 코드 작성하세요
		//맞출때가지 계속 입력받고 맞추면 종료한다.
		int random = (int) ((Math.random()* 100)+1) ;
		System.out.println("답 : "+random);
		System.out.print("값을 입력> ");
		int forecast = Integer.parseInt(sc.nextLine());
		while(true){
			if(random==forecast){
				System.out.println("정답입니다.");
				break;
			}else{
				System.out.println("답이 틀립니다.");
				System.out.print("값을 입력> ");
				forecast = Integer.parseInt(sc.nextLine());
			}
		}
		sc.close();
	}//main

}
