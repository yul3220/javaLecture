package kr.or.ddit.member.main;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.util.CryptoUtil;
import kr.or.ddit.vo.MemberVO;
/* 회원 ID를 암호화시켜서 DB에 저장하고, 화면에 보여줄 때는 원래의 데이터로 복호화시켜서 보여준다.*/
public class MemberController {
	private Scanner scan = new Scanner(System.in);
	private static final String key = "a1b2c3d4e5f6g7h8";
	
	// Controller는 Service객체를 사용하기 때문에
	// Service객체가 저장될 변수가 필요하다.
	private IMemberService service;
	
	//생성자
	public MemberController(){
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();//싱글톤(01.29)
	}
	
	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		new MemberController().memberStart();
	}
	
	// 메뉴 출력 및 작업번호를 입력 받아 반환하는 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println(" -- 작업 선택 -- ");
		System.out.println("1. 자 료 추 가");
		System.out.println("2. 자 료 삭 제");
		System.out.println("3. 자 료 수 정");
		System.out.println("4. 전체  자료 출력");
		System.out.println("5. 자 료 수 정 2");
		System.out.println("0. 작 업 끝");
		System.out.println("--------------");
		System.out.print("작업선택 >> ");
		int num = scan.nextInt();
		System.out.println();
		return num;
	}

	private void memberStart() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		while(true){
			int choice = displayMenu();
			
			switch(choice){
				case 1: // 추가
					insertMember();
					break;
				case 2: // 삭제
					deleteMember();
					break;
				case 3: // 수정
					updateMember();
					break;
				case 4: // 전체 출력
					displayMember();
					break;
				case 5: // 수정
					updateMember2();
					//updateMember2();
					break;
				case 0: // 작업 끝
					System.out.println("작업을 마칩니다.");
					return;
				default:
					System.out.println("번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}	
	}

	// 회원 정보를 추가하는 메서드
	private void insertMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int count = 0;
		String memId; // 입력할 회원ID가 저장될 변수
		do{
			System.out.print("회원ID >> ");
			memId = CryptoUtil.encryptAES256(scan.next(), key);
			
			count = service.getMemberCount(memId);
			if(count>0){
				System.out.println("회원 ID가 " + CryptoUtil.decryptAES256(memId, key) + "인 회원은 이미 존재합니다.");
				System.out.println("다른 ID로 다시 입력하세요.");
				System.out.println();
			}
		}while(count>0);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("주 소 >> ");
		String memAddr = scan.nextLine();
	
		// Service로 보낼 MemberVO객체를 생성하고
		// 입력한 자료를 셋팅한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		// service의 insert하는 메서드 호출하기
		int cnt = service.insertMember(memVo);
		
		if(cnt>0){
			System.out.println("insert 성공!!");
		}else{
			System.out.println("insert 실패!!");
		}
	}
		
	private void updateMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = CryptoUtil.encryptAES256(scan.next(), key);
		
		int count = service.getMemberCount(memId);
		if(count==0){ // 없는 회원이면...
			System.out.println(CryptoUtil.decryptAES256(memId, key) + "회원은 없는 회원ID 입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
			
		System.out.print("새로운 회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); 
		System.out.print("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.updateMember(memVo);
			
		if(cnt>0){
			System.out.println(CryptoUtil.decryptAES256(memId, key) + " 회원의 정보를 변경하였습니다.");
		}else{
			System.out.println("수정 작업 실패~~");
		}	
	}
	
	private void updateMember2() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = CryptoUtil.encryptAES256(scan.next(), key);
		
		int count = service.getMemberCount(memId);
		if(count==0){ // 없는 회원이면...
			System.out.println(CryptoUtil.decryptAES256(memId, key) + "회원은 없는 회원ID 입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		int num;
		String updateField = null; // 수정할 컬럼명이 저장될 변수
		String updateStr = null; // 수정할 컬럼의 한글명이 저장될 변수
		
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println(" 1.회원이름    2.전화번호   3.회원주소");
			System.out.println("--------------------------------");
			System.out.print("수정항목 입력 >> ");
			num = scan.nextInt();
			
			switch(num){
				case 1:
					updateField = "mem_name";
					updateStr = "회원이름";
					break;
				case 2:
					updateField = "mem_tel";
					updateStr = "전화번호";
					break;
				case 3:
					updateField = "mem_addr";
					updateStr = "회원주소";
					break;
				default:
					System.out.println("수정 항목을 잘못 입력했습니다.");
					System.out.println("다시 선택하세요.");
			}
		}while(num<1 || num>3);
		
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateStr + " >> ");
		String updateData = scan.nextLine();
		
		// 수정할 정보를 Map에 추가한다.
		// Key값 정보 => 회원ID(memid), 수정할컬럼명(field), 수정할 데이터(data)
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("memid", memId);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0){
			System.out.println("수정 작업 성공!!!!");
		}else{
			System.out.println("수정 작업 실패~~~~");				
		}
	}
	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		
		System.out.print("삭제할 회원ID >> ");
		String memId = CryptoUtil.encryptAES256(scan.next(), key);
			
		int cnt = service.deleteMember(memId);
			
		if(cnt>0){
			System.out.println(CryptoUtil.decryptAES256(memId, key) + " 회원 정보 삭제 성공!!");
		}else{
			System.out.println(CryptoUtil.decryptAES256(memId, key) + " 회원은 등록 안된 회원이거나 삭제에 실패");
		}
	}

	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t\t주소");
		System.out.println("--------------------------------------------");
		
		if(memList==null || memList.size()==0){//null이면 오류가 뜨는 경우도 있기때문에 검사를 해야한다.
			System.out.println("등록된 회원이 하나도 없습니다.");
		}else{
			for(MemberVO memVo:memList){
				System.out.print(CryptoUtil.decryptAES256(memVo.getMem_id(), key)+"\t");
				System.out.print(memVo.getMem_name()+"\t");
				System.out.print(memVo.getMem_tel()+"\t");
				System.out.println(memVo.getMem_addr());
			}
		}
		System.out.println("--------------------------------------------");
		System.out.println("출력 끝...");
	}
}