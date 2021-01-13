package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest07_Sem {
	public static boolean inputCheck = false;
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		// 난수를 이용해서  컴퓨터의 가위 바위 보 정하기
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random()*3); // 0~2사이의 난수 발생
		String com = data[index];
		
		// 사용자의 가위 바위 보 입력받기
		String man = null;
		gt.start();
		do{
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		}while(!(man.equals("가위") || man.equals("바위") || man.equals("보")));
		// !man.equals("가위") && !man.equals("바위") && !man.equals("보")
		inputCheck = true;
		
		// 결과 판정하기
		String result = "";
		if(com.equals(man)){
			result = "비겼습니다.";
		}else if(man.equals("가위")&&com.equals("보") 
				|| man.equals("바위")&&com.equals("가위")
				|| man.equals("보")&&com.equals("바위")){
			result = "당신이 이겼습니다.";
		}else{
			result = "당신이 졌습니다.";
		}
		
		// 결과 출력하기
		System.out.println("   -- 결과  --");
		System.out.println("컴퓨터  : " + com);
		System.out.println("사용자  : " + man);
		System.out.println("결과  : " + result);
	}
}

class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for(int i = 5; i>0; i--){
			// 가위바위보를 입력받는 쓰레드에서 입력이 완료되면 카운트 다운을 멈춘다.
			if(ThreadTest07_Sem.inputCheck == true){
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// for문
		
		System.out.println("  -- 결과 --  ");
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}