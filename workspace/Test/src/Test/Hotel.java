package Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Hotel {
	HashMap<Integer, Room> hotelMap;
	Scanner scan = new Scanner(System.in);
	String fileName = "D:/d_other/hotel.dat";
	boolean dataChange;
	
	public Hotel(){
		hotelMap = load();
		if(hotelMap == null){
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
					hotelMap.put(roomNum, new Room(roomNum, roomType));
				}
			}
		}
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new Hotel().hotelStart();
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
					save();
					break;
				case 0:
					if(dataChange==true){
						save();
					}
					System.out.println("**************************************");
					System.out.println("  		 호텔문을 닫았습니다.");
					System.out.println("**************************************");
					return;
				default:
					System.out.println("잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}
	
	private HashMap<Integer, Room> load(){
		// 읽어올 데이터가 저장될 변수 선언
		HashMap<Integer, Room> pMap = null;
		
		File file = new File(fileName);
		if(!file.exists()){// 저장된 파일이 없으면...
			return null;
		}
		
		// 저장될 객체를 읽어올 스트림 변수 선언
		ObjectInputStream ois = null;
		try {
			// 입력용 스트림 객체 생성
			ois = new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream(fileName)
					)
				);
			pMap = (HashMap<Integer, Room>) ois.readObject();
			
		} catch (IOException e) {
			//e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			return null;
		}finally{
			try {ois.close();} catch (IOException e) {}
		}
		
		return pMap;
	}
	// 전화번호 정보를 저장하는 메서드
	private void save() {
		ObjectOutputStream oos = null;
		try {
			// 객체 출력용 스트림
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(fileName)
					)
				);
			
			oos.writeObject(hotelMap);
			
			System.out.println("저장이 완료되었습니다.");
			dataChange = false;//저장이 완료되면 false반환
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//사용했던 객체스트림 닫기
			try {oos.close();} catch (IOException e) { }
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
		if(hotelMap.get(num).getName()==null){
			System.out.println(num+"호 객실에는 체크인 한 사람이 없습니다.");
			return;
		}
		
		// 체크아웃 작업은 해당 객실의 투숙객 이름을 null로 변경하면 된다.
		String name = hotelMap.get(num).getName();
		hotelMap.get(num).setName(null);
		dataChange = true;
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
		if(hotelMap.get(num).getName()!=null){
			System.out.println(num+"호 객실에는 이미 손님이 있습니다.");
			return;
		}
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력>> ");
		String name = scan.next();
		
		//입력 받은 투숙객 이름을 해당 객실의 투숙객 명단에 저장한다.
		hotelMap.get(num).setName(name);
		dataChange = true;
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
			Room r = hotelMap.get(roomNum);
			System.out.print(r.getRoomnum()+"\t");
			System.out.print(r.getRoomtype()+"\t");
			String name = r.getName() == null ? "-" : r.getName();
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
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.저장\t0.업무종료");
		System.out.println("----------------------------------------------");
		System.out.print("선택>> ");
		int num = scan.nextInt();
		return num;
	}
}//

class Room implements Serializable{
	
	private int roomnum;
	private String roomtype;
	private String name;
	
	public Room(int roomnum, String roomtype) {
		super();
		this.roomnum = roomnum;
		this.roomtype = roomtype;
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
}