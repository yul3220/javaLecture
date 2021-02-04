package hw0104_0108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {
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
	}
}