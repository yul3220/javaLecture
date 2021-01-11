package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	문제 ) 5명의 사람 이름을 입력 받아서 ArrayList에 저장한 후에 이들 중 '김'씨 성을 가진 사람을 모두 출력하시오.
 		(입력은 Scanner를 이용한다.)
 */
public class ArrayListTest02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		for(int i = 0; i < 5; i++){
			System.out.print(i+1 + "번 째 사람의 이름을 입력 : ");
			String name = sc.nextLine();
			list.add(name);
		}
		System.out.println("김씨 성을 가진 사람들 ");
		for(int i = 0; i< list.size(); i++){
			//첫번째방법
			/*if(list.get(i).indexOf("김") == 0){
				System.out.print(list.get(i)+"\t");
			}*/
			//두번째방법
			/*if(list.get(i).charAt(0)=='김'){
				System.out.print(list.get(i)+"\t");
			}*/
			//세번째방법
			/*if(list.get(i).substring(0,1).equals("김")){
				System.out.print(list.get(i)+"\t");
			}*/
			//네번째방법
			if(list.get(i).startsWith("김")){//비교 대상 문자열이 입력된 문자열 (prefix) 값으로 시작되는지 여부를 확인
				System.out.print(list.get(i)+"\t");
			}
		}
		System.out.println();
		
		sc.close();
	}
}//
