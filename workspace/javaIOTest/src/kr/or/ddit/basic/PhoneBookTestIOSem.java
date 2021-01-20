package kr.or.ddit.basic;

/*문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 작성하고,
Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.

- 삭제, 검색기능은 '이름'을 입력 받아서 처리한다.

(Map의 구조는 key값으로 '이름'을 사용하고, 
value값으로는 'Phone클래스의 인스턴스'로 한다.)

- 추가조건)
1) '6. 전화번호 정보 저장'메뉴를 추가하고 구현한다.
	(저장파일명은 'phoneData.dat'로 한다.)
2) 프로그램이 시작할 때 저장된 파일이 있으면 그 데이터를 읽어와 
	Map에 셋팅하여 사용한다.
3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가 또는 삭제되면
	저장한 후 종료되도록 한다.
	
	아래 메뉴의 내용을 프로그램하시오.

실행예시)
-------------------------
다음 메뉴를 선택하시오
1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력
0. 프로그램 종료
-----------------------
번호입력 >> 1

새롭게 등록할 전화번호 정보를 입력하세요.
이름>> 홍길동
전화번호>> 010-1111-1111
주소>> 대전시 중구 대흥동

'홍길동' 전화번호 등록 완료!!

-------------------------
다음 메뉴를 선택하시오
1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력
0. 프로그램 종료
-----------------------
번호입력 >> 1

새롭게 등록할 전화번호 정보를 입력하세요.
이름 >> 홍길동

'홍길동'은 이미 등록된 사람입니다.

-------------------------
다음 메뉴를 선택하시오
1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력
0. 프로그램 종료
-----------------------
번호입력 >> 5

-------------------------------------------------
번호		이름		전화번호				주소
-------------------------------------------------
1		홍길동	010-1111-1111		대전시 중구 대흥동

-------------------------------------------------
출력 완료....

-------------------------
다음 메뉴를 선택하시오
1. 전화번호 등록
2. 전화번호 수정
3. 전화번호 삭제
4. 전화번호 검색
5. 전화번호 전체 출력5
0. 프로그램 종료
-----------------------
번호입력 >> 0

프로그램을 종료합니다.

*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestIOSem {
	HashMap<String, Phone1> phoneBookMap;
	Scanner scan = new Scanner(System.in);
	String fileName = "d:/d_other/phoneDataTest.dat";
	// 데이터가 변경되었는지 여부를 나타내는 변수 선언
	// 데이터가 변경되면 이 변수값이 true가 된다.
	boolean dataChange;
	
	public PhoneBookTestIOSem(){
		phoneBookMap = load();
		if(phoneBookMap == null){
			phoneBookMap = new HashMap<>();
		}
		scan = new Scanner(System.in);
	}
	public static void main(String[] args) {
		new PhoneBookTestIOSem().Phonestart();
	}

	private void Phonestart() {
		System.out.println("+++++++++++++++++++++++");
		System.out.println("   전화 번호 관리 프로그램");
		System.out.println("+++++++++++++++++++++++");

		while(true){
			int choice = displayMenu();
			switch(choice){
			case 1:  //등록
				insert();
				break;
			case 2: //수정
				update();
				break;
			case 3: //삭제
				delete();
				break;
			case 4: //검색
				search();
				break;
			case 5: //전체출력
				displayAll();
				break;
			case 6: //저장
				save();
				break;
			case 0: //종료
				if(dataChange==true){
					save();
				}
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}
	// 저장된 전화번호 정보를 읽어오는 메서드
	private HashMap<String, Phone1> load(){
		// 읽어올 데이터가 저장될 변수 선언
		HashMap<String, Phone1> pMap = null;
		
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
			pMap = (HashMap<String, Phone1>) ois.readObject();
			
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
			
			oos.writeObject(phoneBookMap);
			
			System.out.println("저장이 완료되었습니다.");
			dataChange = false;//저장이 완료되면 false반환
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//사용했던 객체스트림 닫기
			try {oos.close();} catch (IOException e) { }
		}
	}

	//새로운 전화번호 정보를 등록하는 메서드
	private void insert(){
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름>> ");
		String name = scan.next();

		//이미 등록되 사람인지 검사
		if(phoneBookMap.containsKey(name)){
			System.out.println(name+"씨는 이미 등록된 사람입니다.");
		}else{
			System.out.print("전화번호>> ");
			String tel = scan.next();
			
			scan.nextLine(); //입력 버퍼 비우기
			
			System.out.print("주 소>> ");
			String addr = scan.nextLine();

			
			phoneBookMap.put(name, new Phone1(name, addr, tel));
			System.out.println(name+" 등록 완료!!");
			dataChange = true;
		}
	}

	/*
	  	next(), nextInt(), nextDouble()등등
	 	==> 사이띄기, Tab키, Enter키를 구분문자로 분리해서 분리된 자료만 읽어간다.
	 	
	 	nextLine() ==> 한 줄 단위로 입력한다.
	 	즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다.
	 	
	 	Scanner객체는 입력 버퍼에 자료가 남아 있으면 새로 입력 받지 않고
	 	입력 버퍼에 있는 값을 가져간다.
	 	
	 	------------------------------------------------------------
	 	
	 	nextLine()은 값을 입력하지 않아도 enter키가 인식되면 값없이도 입력된다.
	 	next와 nextLine()을 섞여쓰면 에러가 발생된다.
	 	
	 */
	
	//전체 전화번호 정보를 출력하는 메서드
	private void displayAll(){
		System.out.println();
		Set<String> keySet = phoneBookMap.keySet();

		System.out.println("------------------------------------------");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		if(keySet.size()==0){
			System.out.println("출력할 데이터가 하나도 없습니다.");
			return;
		}
		System.out.println("------------------------------------------");
		
		int cnt = 0;
		for(String key : keySet){
			Phone1 p = phoneBookMap.get(key);
			String tel = p.getTel();
			String addr = p.getAddr();
			System.out.println((++cnt)+"\t"+key+"\t"
					+tel+"\t"+addr);
		}

		System.out.println("------------------------------------------");
		System.out.println("출력 끝..");
	}

	//전화번호 정보를 수정하는 메서드
	private void update(){
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름>> ");
		String name = scan.next();

		//이미 등록된 사람인지 검사
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name+"씨는 등록되지 않은 사람입니다.");
			return;
		}

		System.out.print("새로운 전화번호>> ");
		String newtel = scan.next();
		
		scan.nextLine();
		
		System.out.print("새로운 주소>> ");
		//String newaddr = scan.next();
		String newaddr = scan.nextLine();
		
		phoneBookMap.put(name, new Phone1(name, newaddr, newtel));
		
		System.out.println(name+"씨의 전화번호 정보를 수정했습니다.");
		
		dataChange = true;
	}
	
	//전화번호를 삭제하는 메서드
		private void delete(){
			System.out.println();
			System.out.println("수정할 전화번호 정보를 입력하세요.");
			System.out.print("이름>> ");
			String name = scan.next();
			
			//이미 등록된 사람인지 검색
			if(!phoneBookMap.containsKey(name)){
				System.out.println(name+"씨는 등록되지 않은 사람입니다.");
				return;
			}
			
			phoneBookMap.remove(name);
			System.out.println(name+"씨 전화번호를 삭제했습니다.");
			
			dataChange = true;
		}
	
	//전화번호 정보를 검색하는 메서드
	private void search(){
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름>> ");
		String name = scan.next();
		
		//이미 등록된 사람인지 검색
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name+"씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		Phone1 p = phoneBookMap.get(name);
		System.out.println("검        색         결          과");
		System.out.println("=======================");
		System.out.println(name+"씨 전화번호 정보");
		System.out.println("------------------------");
		System.out.println("이      름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주      소 : " + p.getAddr());
		System.out.println("------------------------");
	}
		
	//메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("다음 메뉴를 선택하세요");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 정보 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("-----------------------------");
		System.out.print("번호입력>> ");
		int num = scan.nextInt();
		return num;
	}
}//

//이름, 주소, 전화번호
class Phone1 implements Serializable{
	
	private static final long serialVersionUID = 2608786391357104473L;
	private String name;
	private String addr;
	private String tel;

	public Phone1(String name, String addr, String tel) {
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public String getAddr() {
		return addr;
	}

	public String getTel() {
		return tel;
	}
}
