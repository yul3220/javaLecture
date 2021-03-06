package hw0104_0108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Hotel {
	Scanner sc = new Scanner(System.in);
	HashMap<Integer, Room> hotel = new HashMap<>();

	public static void main(String[] args) {
		new Hotel().start();
	}

	private void start(){
		System.out.println("*****************************");
		System.out.println("   호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*****************************");

		room();

		while(true){
			int choice = displayMenu();
			switch(choice){
			case 1: 
				checkin();
				break;
			case 2:
				checkout();
				break;
			case 3:
				allview();
				break;
			case 4: 
				System.out.println("*******************");
				System.out.println(" 호텔문을 닫았습니다.");
				System.out.println("*******************");
				System.exit(0);
			default:
				System.out.println("다른 번호를 입력하였습니다.");
			}
		}
	}
	private int displayMenu(){
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("----------------------------------------------");
		System.out.print("선택>> ");
		int num = Integer.parseInt(sc.nextLine());
		System.out.println();
		return num;
	}

	private void room(){
		for(int i = 201; i<=209; i++){
			hotel.put(i,new Room(i, "싱글룸", "-"));
		}

		for(int i = 301; i<=309; i++){
			hotel.put(i,new Room(i, "더블룸", "-"));
		}

		for(int i = 401; i<=409; i++){
			hotel.put(i,new Room(i, "스위트룸", "-"));
		}
	}
	private void checkin() {
		System.out.println("-------------------------------");
		System.out.println("체크인 작업");
		System.out.println("-------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("-------------------------------");
		System.out.print("방 번호 입력>> ");
		int roomnum = Integer.parseInt(sc.nextLine());
		Room r = hotel.get(roomnum);
		if(!hotel.containsKey(roomnum)){
			System.out.println(roomnum+"호 객실은 존재하지 않습니다.");
			return;
		}else if(!r.getName().equals("-")){
			System.out.println(roomnum+"호 객실은 이미 손님이 있습니다.");
			return;
		}else{
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력>> ");
			String name = sc.nextLine();
			r.setName(name);
			System.out.println("체크인이 완료되었습니다.");
		}
	}

	private void checkout() {
		System.out.println("-------------------------------");
		System.out.println("체크아웃 작업");
		System.out.println("-------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력>> ");
		int roomnum = Integer.parseInt(sc.nextLine());
		Room r = hotel.get(roomnum);
		if(!hotel.containsKey(roomnum)){
			System.out.println(roomnum+"호 객실은 존재하지 않습니다.");
			return;
		}else if(r.getName().equals("-")){
			System.out.println(roomnum+"호 객실에는 체크인 한 사람이 없습니다.");
			return;
		}else{
			System.out.println(roomnum+"호 객실의 "+r.getName()+"님 체크아웃을 완료하였습니다.");
			r.setName("-");
		}
	}

	private void allview() {
		System.out.println("-----------------------------------");
		System.out.println("           현재 객실 상태");
		System.out.println("-----------------------------------");
		System.out.println("방 번호\t방 종류\t투숙객 이름");
		System.out.println("-----------------------------------");
		List<Room> room = new ArrayList<>(hotel.values());
		Collections.sort(room);
		for(Room r: room){
			System.out.println(r.getRoomnum()+"\t"+r.getRoomtype()
					+"\t"+r.getName());
		}
	}


}

class Room implements Comparable<Room>{
	private int roomnum;
	private String roomtype;
	private String name;

	Room(int roomnum, String roomtype, String name){
		this.roomnum = roomnum;
		this.roomtype = roomtype;
		this.name = name;
	}

	public int getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Room r) {
		return new Integer(this.roomnum).compareTo(r.getRoomnum());
	}


}
