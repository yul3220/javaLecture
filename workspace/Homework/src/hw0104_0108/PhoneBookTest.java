package hw0104_0108;

/*
 	문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 작성하고,
 		Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.

		- 삭제, 검색기능은 '이름'을 입력 받아서 처리한다.

 		(Map의 구조는 key값으로 '이름'을 사용하고, 
 		value값으로는 'Phone클래스의 인스턴스'로 한다.)

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
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;

public class PhoneBookTest {
	HashMap<String, Phone> phoneBookMap = new HashMap<>();
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		new PhoneBookTest().start();
	}

	private void start() {
		while(true){
			int choice = displayMenu();
			switch(choice){
			case 1 : 
				register();
				break; 
			case 2 : 
				update();
				break; 
			case 3 : 
				delete();
				break; 
			case 4 : 
				search();
				break; 
			case 5 : 
				allview();
				break; 
			case 0 :
				System.out.println("프로그램 종료합니다.");
				System.exit(0);
			default: 
				System.out.println("다시 입력하세요.");
			}
		}
	}

	//전화번호 등록
	private void register() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름>> ");
		String name = sc.nextLine();
		if(phoneBookMap.get(name)==null){
			System.out.print("전화번호>> ");
			String tel = sc.nextLine();
			System.out.print("주소>> ");
			String addr = sc.nextLine();

			Phone phone = new Phone();
			phone.setName(name);
			phone.setTel(tel);
			phone.setAddr(addr);

			phoneBookMap.put(name, phone);

			System.out.println("'"+name+"'전화번호 등록 완료!!");
		}else{
			System.out.println("'"+name+"'은 이미 등록된 사람입니다.");
		}
	}

	private void update() {
		System.out.print("수정하고 싶은 사람의 이름을 입력> ");
		String name = sc.nextLine();
		if(phoneBookMap.get(name)!=null){
			System.out.print("수정하고 싶은 전화번호 입력> ");
			String tel = sc.nextLine();
			Phone p = phoneBookMap.get(name);
			p.setTel(tel);
			System.out.println(name+"의 전화번호 수정 완료!!");
		}else{
			System.out.println("해당이름에 대한 데이터가 존재하지 않습니다.");
		}
	}

	private void delete() {
		System.out.print("삭제하고 싶은 사람의 이름을 입력> ");
		String name = sc.nextLine();
		if(phoneBookMap.get(name)!=null){
			phoneBookMap.remove(name);
			System.out.println(name+"의 전화번호 삭제 완료!!");
		}else{
			System.out.println("해당이름에 대한 데이터가 존재하지 않습니다.");
		}
	}

	private void search() {
		System.out.print("검색하고 싶은 사람의 이름을 입력> ");
		String name = sc.nextLine();
		if(phoneBookMap.get(name)!=null){
			Phone p = phoneBookMap.get(name);
			System.out.println("이름 : " + p.getName());
			System.out.println("전화번호 : " + p.getTel());
			System.out.println("주소 : " + p.getAddr());
		}else{
			System.out.println("해당이름에 대한 데이터가 존재하지 않습니다.");
		}
	}

	private void allview() {
		System.out.println("----------------------------------------");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		System.out.println("----------------------------------------");
		int num = 0;
		for(String key : phoneBookMap.keySet()){
			Phone p = phoneBookMap.get(key);
			num++;
			System.out.print(num+"\t");
			System.out.print(p.getName()+"\t");
			System.out.print(p.getTel()+"\t\t");
			System.out.println(p.getAddr());
		}
		System.out.println("----------------------------------------");
		System.out.println("출력 완료");
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("다음 메뉴를 선택하세요");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("-----------------------------");
		System.out.print("번호입력>> ");
		int input = Integer.parseInt(sc.nextLine());
		System.out.println();
		return input;
	}
}

class Phone{
	private String name;
	private String addr;
	private String tel;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
}
