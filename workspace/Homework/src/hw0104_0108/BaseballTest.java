package hw0104_0108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 	문제) Set와 List를 이용하여 숫자 야구 게임프로그램을 작성하시오.
 		컴퓨터의 숫자는 난수와 이용하여 구한다.
 		(스트라이크는 S, 볼은 B로 나타낸다)

 		예시)  
 		컴퓨터의 난수 ==> 9 5 7

 		실행예시)
 		숫자입력 => 3 5 6
 		3 5 6 => 1S 0B
 		숫자입력 => 7 8 9
 		7 8 9 => 1S 0B
 		숫자입력 => 9 7 5
 		9 7 5 => 1S 2B
 		숫자입력 => 9 5 7
 		9 5 7 => 3S 0B

 		축하합니다.
 		당신은 4번째만에 맞췄군요...
 */

public class BaseballTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자 야구게임 설명");
		System.out.println("컴퓨터에서 생각한 숫자와 입력한 숫자를 비교하게 됩니다.");
		System.out.println("자리와 숫자까지 모두 같으면 Strike");
		System.out.println("숫자는 맞으나 자리가 다르면 Ball");
		System.out.println();

		int count = 0;
		HashSet<Integer> testSet = new HashSet<>();
		while(testSet.size()<3){
			int rnd = (int)(Math.random() * 9 + 1);
			testSet.add(rnd);
		}

		ArrayList<Integer> testlist = new ArrayList<>(testSet);

		Collections.shuffle(testlist);
		System.out.println("컴퓨터의 난수" + testlist);

		while(true){
			int num = 0;
			int strike = 0;
			int ball = 0;
			HashSet<Integer> userset = new HashSet<>();
			ArrayList<Integer> userlist = new ArrayList<>();
			while(userset.size()<3){
				System.out.print((num+1)+"번째 숫자를 입력하시오> ");
				int n = Integer.parseInt(sc.nextLine());
				if(userset.add(n)==true){
					userlist.add(n);
					num++;
				}else{
					System.out.println("중복된 숫자를 입력했습니다.");
				}
			}

			for(int i = 0; i<testlist.size();i++){
				if(testlist.contains(userlist.get(i))
						&&testlist.indexOf(userlist.get(i))==userlist.indexOf(testlist.get(i))){
					strike++;
				}
				else if(testlist.contains(userlist.get(i))){
					ball++;
				}
			}

			System.out.println(strike+"S"+ball+"B");
			count++;

			if(strike==3){
				System.out.println("축하합니다.");
				System.out.println("당신은"+count+"번째에 맞추셨습니다..");
				break;
			}
		}	
	}
}//

