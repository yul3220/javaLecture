package kr.or.ddit.basic;

import java.util.Arrays;

/*
 	문제 ) 10마리의 말들이 경주하는 경마 프로그램을 작성하시오.
 	
 	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데, 
 	이 클래스는 말이름(String), 등수(int), 현재 위치값(int)을
 	멤버변수로 갖는다. 그리고, 이 클래스에는 등수를 오름차순으로 
 	처리할 수 있는 내부 정렬 기준이 있다.(Comparable 인터페이스 구현)
 	경기 구간은 1~50구간으로 되어있다.
 	
 	경기가 진행되는 중간 중간에 각각의 말들의 위치를 아래와 같이 나타내시오.
 	예시)
 	01번말 : -->--------------------------------------
 	02번말 : ----------------->-----------------------
 	03번말 : ----------->-----------------------------
 	....
 	10번말 : -------->--------------------------------
 	
 	경기가 끝나면 등수 순으로 출력한다.
*/
public class ThreadTest12 {
	public static void main(String[] args) {
		Horse[] horses = new Horse[]{
			new Horse("01번 말"),	
			new Horse("02번 말"),	
			new Horse("03번 말"),	
			new Horse("04번 말"),	
			new Horse("05번 말"),	
			new Horse("06번 말"),	
			new Horse("07번 말"),	
			new Horse("08번 말"),	
			new Horse("09번 말"),	
			new Horse("10번 말")
		};
		
		GameState gs = new GameState(horses);
		
		System.out.println("경기 시작...");
		
		for(Horse h : horses){
			h.start();
		}
		
		gs.start();
		
		for(Horse h : horses){
			try {
				h.join();
			} catch (InterruptedException e) {
				
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
			
		};
		
		System.out.println();
		System.out.println("경기 끝....");
		System.out.println();
		
		// 경기가 끝난 후 등수순으로 정렬한다.
		Arrays.sort(horses); //배열을 내부정렬기준으로 정렬해주는 메소드
		
		System.out.println("경기 결과");
		for(Horse h : horses){
			System.out.println(h);
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank = 0; 
	// 경기가 끝난 말의 등수를 처리하는 변수
	private String horseName; // 말이름
	private int rank;		  // 등수
	private int position;	  // 현재위치	

	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "경주마" + horseName + "은(는) " + rank + "등 입니다.";
	}

	// 등수의 오름찬순으로 정렬할 수 있는 내부 정렬기준 설정하기 
	@Override
	public int compareTo(Horse h) {
		return Integer.compare(rank, h.getRank());
	}

	@Override
	public void run() {
		for(int i = 1; i<=50; i++){
			this.position = i; // 말의 현재 위치 설정
			
			try {
				Thread.sleep((int)(Math.random() * 500));//0 ~ 0.49초동안 쉬게끔함
			} catch (InterruptedException e) {
				
			}
		}// for문
		
		// 한 마리의 말이 경주가 끝나면 currentRank값을 증가시킨다.
		// 이 값이 현재 경주가 끝난 말의 등수가 된다.
		currentRank++;
		this.rank = currentRank;
	}
}

// 경기 중 말의 현재 위치를 나타내는 쓰레드
class GameState extends Thread{
	// 경기를 진행 중인 말들이 저장될 배열을 저장할 변수 선언
	private Horse[] horses; 
	
	// 생성자
	public GameState(Horse[] horses){
		this.horses = horses;
	}

	@Override
	public void run() {
		while(true){//경기가 언제끝날지 모르니까 무한반복문으로 돌림
			if(Horse.currentRank == horses.length){
				break;
			}
			
			for(int i=1; i<=10; i++){
				System.out.println();
			}
			
			for(int i=0; i<horses.length; i++){	
				System.out.print(horses[i].getHorseName() + " : ");
				
				for(int j=1; j<=50; j++){//구간이 총50개
					if(horses[i].getPosition() == j){
						System.out.print(">");
					}else{
						System.out.print("-");						
					}
				}
				System.out.println();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			
			}
		}
	}
}