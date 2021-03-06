package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(10, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));
		
		System.out.println("정렬 전....");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------------------");
		
		Collections.sort(memList);
		
		System.out.println("정렬 후....");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------------------");
		
		//회원 번호의 내림 차순으로 정렬하기(외부 정렬 기준으로..)
		System.out.println("회원번호를 내림차순으로 정렬 후....");
		Collections.sort(memList, new memnum());
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------------------");
		
	}
}//

// Member클래스와 회원이름의 오름차순 정렬이 되도록 하는 내부 정렬 기준 구현하기
// ==> Comparable인터페이스를 구현한다.
class Member implements Comparable<Member>{
	private int num;//회원번호
	private String name;//회원이름
	private String tel;//전화번호
	
	//생성자 자동으로 만드는 방법 : Alt+Shift+S를 누른 후 generate Constructor using fields를
	//눌러서 설정하면 가능
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	//Alt+Shift+S => generate Getters and Setters
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	//Alt+Shift+S => Override/Implement Methods를 눌러서 toString 오버라이딩
	@Override
	public String toString() {
		return "Member [num = " + num + ", name = "+ name + ", tel = "+tel +"]";
	}

	@Override
	public int compareTo(Member mem) {
		//멤버가 가지고 따져보면 앞의 데이터와 뒤의 데이터가 무엇인지 알아야함
		//현재 클래스의 회원이름과 괄호속에 들어오는 회원이름과 비교
		//현재 클래스의 데이터를 앞의 데이터로 봄

		// 회원 이름의 오름차순 정렬 기준 만들기
		return this.name.compareTo(mem.getName());
		
		//내림차순으로 하고 싶으면 부호를 반대로 함
		//=> return this.name.compareTo(mem.getName()) * -1;
	}
}

// 회원 번호의 내림차순 정렬이 되도록하는 외부 정렬 기준 클래스 작성하기
class memnum implements Comparator<Member>{
	@Override
	public int compare(Member mem1, Member mem2) {
		/*if(mem1.getNum() > mem2.getNum()){
			return -1;
		}else if(mem1.getNum() < mem2.getNum()){
			return 1;
		}else{
			return 0;
		}*/
		
		//Wrapper클래스를 이용하는 방법 1
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
		//Wrapper클래스를 이용하는 방법 2
		//return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}
}