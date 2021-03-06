package hw0104_0108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Lotto {
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new Lotto().lottoStart();
	}

	public void lottoStart(){
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1: //구입
					lottoBuy();
					break;
				case 2: //종료
					System.out.println("감사합니다.");
					System.exit(0);
				default:
					System.out.println("번호를 다시 선택하세요.");
			}
		}
	} 

	//로또 구입을 처리하는 메서드
	private void lottoBuy(){
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int money = scan.nextInt();
		if(money<1000){
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
		}else if(money/1000 > 100){
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
		}else{
			//로또 번호를 생성하고 출력하는 메서드 호출
			lottoNum(money);
		}
	}
	
	//로또 번호를 생성하고 출력하는 메서드
	private void lottoNum(int money){
		System.out.println();
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for(int i = 0; i< money/1000; i++){
			HashSet<Integer> lottoSet = new HashSet<>();
			while(lottoSet.size()<6){
				lottoSet.add((int)(Math.random()*45 +1));
			}
			List<Integer> lottolist = new ArrayList<>(lottoSet);
			Collections.sort(lottolist);
			System.out.print("로또번호  " + (i+1) + " : ");
			for(int j = 0; j< lottolist.size(); j++){
				if(j>0) System.out.print(", ");
				System.out.print(lottolist.get(j));
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("받음 금액 "+money+
				"원이고  거스름돈은 "+ (money%1000)+"원입니다.");
	}

	//메뉴를 출력하고 선택한 메뉴 번호를 반환하는 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("==========================");
		System.out.println("1.Lotto 구입");
		System.out.println("2.프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 : ");
		int num = scan.nextInt();
		return num;
	}
}//

/*
 //내가한것
 static Scanner sc = new Scanner(System.in);
public static void main(String[] args) {
	Lotto lotto = new Lotto();
	lotto.start();
}

public void start(){
	while(true){
		System.out.println();
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("==========================");
		System.out.println("1.Lotto 구입");
		System.out.println("2.프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 : ");
		int input = Integer.parseInt(sc.nextLine());
		System.out.println();
		switch(input){
			case 1: buylotto();
					break;
			case 2:
				System.out.println("감사합니다.");
				System.exit(0);
		}
	}
}

public void buylotto(){
	System.out.println("Lotto 구입시작");
	System.out.println();
	System.out.println("(1000원에 로또번호 하나입니다.)");
	System.out.print("금액 입력 : ");
	int money = Integer.parseInt(sc.nextLine());
	int count = money/1000;
	int change = money%1000;
	System.out.println();
	if(money<1000){
		System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!");
	}else if(count>100){
		System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
	}else{
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		createlottonum(count);
		System.out.println("받은 금액은"+money+"원이고 거스름돈은 "+change+"원입니다.");
		System.out.println();
	}
}

public void createlottonum(int count){
	int num = 0;
	for(int i = 0 ; i<count;i++){
		HashSet<Integer> lottonum = new HashSet<>();
		while(lottonum.size()<6){
			int number = (int) ((Math.random()* 45)+1);
			lottonum.add(number);
		}
		ArrayList<Integer> lottolist = new ArrayList<>(lottonum);
		Collections.sort(lottolist);
		String str = "";
		System.out.print("로또번호 "+(++num)+" : ");
		for(int j = 0; j < lottolist.size();j++){
			if(j == lottolist.size()-1){
				str += lottolist.get(j);
			}else{
				str += lottolist.get(j)+", ";
			}
		}
		System.out.println(str);
	}
}*/
