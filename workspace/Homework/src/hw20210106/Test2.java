package hw20210106;

import java.util.Arrays;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("-----------------1번문제---------------------");
		//arr배열의 모든값을 더하는 프로그램을 작성하세요
		int[] arr ={10,20,30,40,50};
		int sum = 0;
		
		for(int i = 0; i< arr.length; i++){
			sum += arr[i];
		}
		System.out.println("합계는 "+sum+"입니다.");
		
		System.out.println();
		System.out.println("-----------------2번문제---------------------");
		//5명의 이름을 입력받아 배열에 저장하는 프로그램을 작성하세요.
		String[] names = new String[5];
		for(int i = 0; i< 5; i++){
			System.out.print((i+1)+"번째의 사람의 이름을 입력> ");
			String name = sc.nextLine();
			names[i] = name;
		}
		System.out.println(Arrays.toString(names));
		
		System.out.println();
		System.out.println("-----------------3번문제---------------------");
		// score배열의 최대값, 최소값구하기 
		int[] score = { 79, 88, 91, 33, 100, 55, 95};
		int min = score[0];
		int max = score[0];
		for(int i = 0;i<score.length; i++){
			if(min>score[i]){
				min = score[i];
			}
			if(max<score[i]){
				max = score[i];
			}
		}
		System.out.println("최소값은 "+min+"이고, 최댓값은 "+max+"입니다.");
		
		System.out.println();
		System.out.println("-----------------4번문제---------------------");
		//2차원 배열 arr2에 담긴 모든값의 총합과 평균을 구하는 프로그램을 작성하세요
		int[][] arr2 = {
			    {5,5,5,5,5},
			    {10,10,10,10,10},
			    {20,20,20,20,20},
			    {30,30,30,30,30}
		};
		int total = 0;
		double avg = 0;
		for(int i = 0; i<arr2.length;i++){
			for(int j = 0; j<arr2[i].length;j++){
				total += arr2[i][j];
			}
		}
		avg = Math.round(((double)total/(arr2.length*arr2[0].length))*100)/100.0;
		System.out.println("합계 : " + total);
		System.out.println("평균 : " + avg);
		
		System.out.println();
		System.out.println("-----------------5번문제---------------------");
		int[] answer = new int[30];
		int[] count = new int[4];
		for(int i = 0; i< answer.length;i++){
			answer[i] = (int) ((Math.random()* 4)+1) ;
			count[answer[i]-1]++;
		}
		for(int i = 0; i<count.length;i++){
			for(int j = 0;j<count[i]; j++){
				System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("-----------------6번문제---------------------");
		//menu배열에서 메뉴이름과 가격중 메뉴이름을 추출하는 프로그램을 작성하세요.
		String menu[] ={ "아메리카노 3000원", "카푸치노 4000원", "카페라떼 3500원"};
		String name[] = new String[menu.length];
		for(int i = 0; i<menu.length;i++){
			name = menu[i].split(" ");
			System.out.println(name[0]);
		}
		
		System.out.println();
		System.out.println("-----------------7번문제---------------------");
		//str변수에서 Java의 위치를 찾아 출력하는 프로그램을 작성하세요
		String str="i Love Java";
		int index = str.indexOf("Java");
		System.out.println("index의 자리 : "+index);
		
		System.out.println();
		System.out.println("-----------------8번문제---------------------");
		//s변수에서 lang문자열을 추출하는 프로그램을 작성하세요 
		String  s = "java.lang.Object";
		index = s.indexOf("lang");
		String ss = s.substring(index, index+4);
		System.out.println(ss);
		
		System.out.println();
		System.out.println("-----------------9번문제---------------------");
		// animals변수에서 ,를 기준으로  문자열을  분리해서  3개의 단어로 각각 출력하는
		// 프로그램을 작성하세요
		String animals = "dog,cat,bear";
		String[] animalnames = animals.split(",");
		for(int i = 0;i<animalnames.length;i++){
			System.out.println(animalnames[i]);
		}
		
		System.out.println();
		System.out.println("-----------------10번문제---------------------");
		//str2문자열에서 ,를 .으로 바꾸어 출력하는 프로그램을 작성하세요.
		String str2="java,lang,Object";
		String[] nn = str2.split(",");
		String str3 = "";
		for(int i = 0; i<nn.length; i++){
			if(i==nn.length-1){
				str3+=nn[i];
			}else{
				str3 += nn[i]+".";
			}
		}
		System.out.println(str3);
		sc.close();
	}//main
}//
