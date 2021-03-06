package hw0104_0108;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제1 ) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오. => ArrayListTest03
	(단, 각 별명의 길이는 모두 다르게 입력한다.)

	문제2) 문제1에서 별명의 길이가 같은 것을 입력할 수 있는 경우를 처리하시오. => ArrayListTest04
*/
public class ArrayListTest03 {
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
		System.out.println("길이가 제일 긴 별명 : " + temp);
		
		/*
		//선생님 코드
		ArrayList<String> aliaslist = new ArrayList<>();
		System.out.println("서로 다른 길이의 별명을 5번 입력하시오.");
		for(int i = 1; i <= 5; i++){
			System.out.print(i + "번째 별명을 입력 : ");
			String alias = sc.nextLine();
			aliaslist.add(alias);
		}
		
		// 제일 긴 별명이 저장될 변수를 선언하고 이 변수에는
		// List의 첫번째 데이터로 초기화 한다.
		//첫번째 방법
		String maxAlias = aliaslist.get(0);
		
		for(int i=1 ; i< aliaslist.size(); i++){
			if(maxAlias.length() < aliaslist.get(i).length()){
				maxAlias = aliaslist.get(i);
			}
		}
		
		System.out.println("제일 긴 별명 : " + maxAlias);
		
		int maxIndex = 0;
		for(int i=1 ; i< aliaslist.size(); i++){
			if(maxAlias.get(maxIndex).length() < aliaslist.get(i).length()){
				maxAlias = i;
			}
		}
		
		System.out.println("제일 긴 별명 : " + aliaslist.get(maxIndex));
		*/
		
		sc.close();
	}
}//
