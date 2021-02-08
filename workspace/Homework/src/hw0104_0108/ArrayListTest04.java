package hw0104_0108;

import java.util.ArrayList;
import java.util.Scanner;
/*
	문제1 ) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오. => ArrayListTest03
	(단, 각 별명의 길이는 모두 다르게 입력한다.)
	
	문제2) 문제1에서 별명의 길이가 같은 것을 입력할 수 있는 경우를 처리하시오. => ArrayListTest04
*/
public class ArrayListTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		for(int i = 0; i < 5; i++){
			System.out.print(i+1 + "번째 별명을 입력 : ");
			String nickname = sc.nextLine();
			list.add(nickname);
		}
		
		String temp = "";
		for(int i = 0; i < list.size(); i++){
			if(temp.length() < list.get(i).length()){
				temp = list.get(i);
			}
		}
		System.out.println("길이가 제일 긴 별명  ");
		
		for(int i = 0; i < list.size(); i++){
			if(temp.length() == list.get(i).length()){
				System.out.println("=>" + list.get(i));
			}
		}
		
		/*
		//선생님 코드
		ArrayList<String> aliaslist = new ArrayList<>();
		System.out.println("별명을 5번 입력하시오.");
		for(int i = 1; i <= 5; i++){
			System.out.print(i + "번째 별명을 입력 : ");
			String alias = sc.nextLine();
			aliaslist.add(alias);
		}
		
		// 제일 긴 별명의 길이가 저장될 변수를 선언하고
		// 이 변수는 List의 첫번째 데이터의 길이로 초기화한다.
		int maxLength = aliaslist.get(0).length();
		
		for(int i = 1; i < aliaslist.size(); i++){
			if(maxLength < aliaslist.get(i).length()){
				maxLength = aliaslist.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명을..");
		for(int i = 0; i < aliaslist.size(); i++){
			if(aliaslist.get(i).length() == maxLength){
				System.out.println(aliaslist.get(i));
			}
		}*/
		
		sc.close();
	}
}//
