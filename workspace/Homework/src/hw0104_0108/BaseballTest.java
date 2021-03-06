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
	ArrayList<Integer> numList;// 난수가 저장될 list
	ArrayList<Integer> userList;// 사용자가 입력한 값이 저장될 list
	
	int strike, ball;// 스트라이크 개수와 볼의 개수를 저장
	
	Scanner scan = new Scanner(System.in);
	
	// 1~9사이의 서로 다른 난수 3개를 만들어서 List에 저장하는 메서드
	// (Set)이용
	public void makeNum(){
		Set<Integer> numSet = new HashSet<>();
		
		// 1~9사이의 난수 3개 만들기
		while(numSet.size()<3){
			numSet.add((int)(Math.random()*9+1));
		}
		
		// 만들어진 난수를 List에 저장하기
		numList = new ArrayList<>(numSet);
		
		// List의 데이터를 섞어준다.
		Collections.shuffle(numList);
	}
	
	// 사용자로부터 3개의 정수를 입력받아 List에 저장하는 메서드
	// 입력한 값은 서로 중복되지 않는다.
	public void inputNum(){
		int n1, n2, n3;// 입력한 정수값이 저장될 변수
		
		do{
			System.out.println("숫자 입력 ==> ");
			n1 = Integer.parseInt(scan.nextLine());
			n2 = Integer.parseInt(scan.nextLine());
			n3 = Integer.parseInt(scan.nextLine());
			
			if(n1==n2 || n1==n3 || n2==n3){
				System.out.println("중복되는 숫자를 입력할 수 없습니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(n1==n2 || n1==n3 || n2==n3);
		
		//입력한 데이터를 List에 저장한다.
		userList = new ArrayList<>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	// 스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	public void ballCount(){
		strike = 0;
		ball = 0; // 스트라이크와 볼의 개수 초기화
		
		for(int i = 0; i< numList.size(); i++){
			for(int j=0; j< userList.size(); j++){
				if(numList.get(i) == userList.get(j)){//값비교
					if(i==j){
						strike++;
					}else{
						ball++;
					}
				}
			}// for - j
		}// for - i
		
		//볼카운트 결과를 출력한다.
		System.out.println(userList.get(0)+", "+userList.get(1)+
				", "+userList.get(2) + "==> "+strike+"S "+ball+"B");
	}
	
	// 게임을 진행하는 메서드 
	public void gameStart() {
		// 난수를 만들어주는 메서드 호출
		System.out.println("게임을 시작합니다.");
		makeNum();
		System.out.println("확인용 : " + numList);
		
		int cnt = 0; // 몇 번만에 맞췄는지를 저장하는 변수
		
		do{
			cnt++;
			
			//사용자 입력 메서드 호출
			inputNum();
			
			//볼카운트 구하는 메서드 호출
			ballCount();
			
		}while(strike!=3);//3 스트라이크가 될때까지 반복한다.
		
		System.out.println();
		System.out.println("축하합니다....");
		System.out.println("당신은 "+cnt+"번만에 맞췄습니다.");
	}
	
	public static void main(String[] args) {
		BaseballTest b = new BaseballTest();
		
		b.gameStart();
	}
}//

/*public static void main(String[] args) {
내가한것
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
}*/