package hw0104_0108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class HotelSem {
	Scanner scan;
	private HashMap<Integer, RoomSem> hotelMap;
	
	//생성자
	public HotelSem(){
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
		for(int i=2; i<=4; i++){
			String roomType = null;
			switch(i){
				case 2 : roomType = "싱글룸"; break;
				case 3 : roomType = "더블룸"; break;
				case 4 : roomType = "스위트룸"; break;
			}
			for(int j=1;j<=9;j++){
				int roomNum = i * 100 + j;
				hotelMap.put(roomNum, new RoomSem(roomNum, roomType));
			}
		}
	}
	
	public static void main(String[] args) {
		new HotelSem().hotelStart();
	}
	
	public void hotelStart(){
		System.out.println();
		System.out.println("**************************************");
		System.out.println("   호텔문을 열었습니다. 어서오십시요.");
		System.out.println("**************************************");
		System.out.println();
		
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1:		// 체크  인
					checkIn();
					break;
				case 2:		// 체크 아웃
					checkOut();
					break;
				case 3:		// 객실 상태 출력
					showRoom();
					break;
				case 4:
					System.out.println("**************************************");
					System.out.println("  		 호텔문을 닫았습니다.");
					System.out.println("**************************************");
					System.exit(0);
				default:
					System.out.println("잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}
	
	// 체크아웃을 처리하는 메서드
	private void checkOut() {
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("		체크아웃 작업");
		System.out.println("-------------------------------");
		System.out.print("방 번호 입력>> ");
		
		int num = scan.nextInt();
		
		// 입력한 값이 Map의 Key값에 없으면 잘못 입력한 방번호이다.
		if(!hotelMap.containsKey(num)){
			System.out.println(num+"호 객실이 존재하지 않습니다.");
			return;
		}
		
		// 해당 객실에 투숙객이 있는지 검사
		if(hotelMap.get(num).getGuestName()==null){
			System.out.println(num+"호 객실에는 체크인 한 사람이 없습니다.");
			return;
		}
		
		// 체크아웃 작업은 해당 객실의 투숙객 이름을 null로 변경하면 된다.
		String name = hotelMap.get(num).getGuestName();
		hotelMap.get(num).setGuestName(null);
		System.out.println(num + "호 객실의 " + name + "씨를 체크아웃 했습니다.");
	}

	private void checkIn() {
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("		체크인 작업");
		System.out.println("-------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("-------------------------------");
		System.out.print("방 번호 입력>> ");
		
		int num = scan.nextInt();
		
		// 입력한 값이 Map의 Key값에 없으면 잘못 입력한 방번호이다.
		if(!hotelMap.containsKey(num)){
			System.out.println(num+"호 객실이 존재하지 않습니다.");
			return;
		}
		
		// 해당 객실에 투숙객이 있는지 검사
		if(hotelMap.get(num).getGuestName()!=null){
			System.out.println(num+"호 객실에는 이미 손님이 있습니다.");
			return;
		}
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력>> ");
		String name = scan.next();
		
		//입력 받은 투숙객 이름을 해당 객실의 투숙객 명단에 저장한다.
		hotelMap.get(num).setGuestName(name);
		
		System.out.println(name + "씨가 " + num + "호 객실에 체크인 되었습니다.");
	}

	// 객실 상태를 출력하는 메서드
	private void showRoom() {
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("방 번호\t방 종류\t투숙객 이름");
		System.out.println("-----------------------------------");
		
		// Map의 key값들(방번호)
		List<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());
		
		// List의 값들을 정렬한다.
		Collections.sort(roomNumList);
		
		// 정렬이 된 List에서 key값을 구해서 Map에서 Room객체를 얻는다.
		for(int roomNum : roomNumList){
			RoomSem r = hotelMap.get(roomNum);
			System.out.print(r.getRoomNumber()+"\t");
			System.out.print(r.getRoomType()+"\t");
			String name = r.getGuestName() == null ? "-" : r.getGuestName();
			System.out.println(name);
		}
		System.out.println("-----------------------------------");
		System.out.println();
	}

	//메뉴 출력 및 작업번호를 반환하는 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("----------------------------------------------");
		System.out.print("선택>> ");
		int num = scan.nextInt();
		return num;
	}
}//

class RoomSem{
	private int roomNumber;
	private String roomType;
	private String guestName;
	
	public RoomSem(int roomNumber, String roomType) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
}
