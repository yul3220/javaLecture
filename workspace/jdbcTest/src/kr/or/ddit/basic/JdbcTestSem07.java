package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

/*
 	지역변수에서 선언한 것들은 close 안한것에 대해 덜 하지만
	전역변수로 선언한 것들은 반드시 finally에서 close하는 것이 좋음
	무료버전이 아닌 회사의 오라클들은 대부분 연결할 수 있는 회선들이 어느정도의 개수가 정해져 있음
	close를 하지 않으면 계속 오라클에 연결되어 있는 상태로 되어있음
	50회선이면 50명한테만 계속 연결되어 있고 , 나머지 사람들은 사용하지 못한다.
	다만, close()를 하게되면 사용하고 나서 DB의 연결이 끊기 때문에 다른사람들도 사용할 수 있다.
	교육용은 회선이 약 20회선 언저리 정도 되는데 현 시점에서는 오라클을 본인의 오라클을 쓰기 때문에 
	크게 상관이 없는 것처럼 느껴질 수 있음. But, 후에 중간 프로젝트와 파이널 프로젝트때는 
	서버를 전체적으로 프로젝트하는사람들이 다 쓰기때문에 close()를 하지 않아서 못 들어가는 경우도 있음
 */

public class JdbcTestSem07 {
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new JdbcTestSem07().memberStart();
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

	private void memberStart() {
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
	
	private void updateMember2() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0){ // 없는 회원이면...
			System.out.println(memId + "회원은 없는 회원ID 입니다.");
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
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember"
					+ " set " + updateField +" = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("수정 작업 성공!!!!");
			}else{
				System.out.println("수정 작업 실패~~~~");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	private void updateMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0){ // 없는 회원이면...
			System.out.println(memId + "회원은 없는 회원ID 입니다.");
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
	
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set"
					+ " mem_name = ?,"
					+ " mem_tel = ?,"
					+ " mem_addr = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + " 회원의 정보를 변경하였습니다.");
			}else{
				System.out.println("수정 작업 실패~~");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		
		System.out.print("삭제할 회원ID >> ");
		String memId = scan.next();
			
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember"
					+ " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + " 회원 정보 삭제 성공!!");
			}else{
				System.out.println(memId + " 회원은 등록 안된 회원이거나 삭제에 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
		
	}

	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t\t주소");
		System.out.println("--------------------------------------------");
		
		try {
			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				System.out.println(memId + "\t" + memName + "\t"
						+ memTel + "\t" + memAddr);
			}
			System.out.println("--------------------------------------------");
			System.out.println("출력 끝...");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(stmt != null)try{ stmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	// 회원 정보를 추가하는 메서드
	private void insertMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int count = 0;
		String memId; // 입력할 회원ID가 저장될 변수
		do{
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			count = getMemberCount(memId);
			if(count>0){
				System.out.println("회원 ID가 " + memId + "인 회원은 이미 존재합니다.");
				System.out.println("다른 ID로 다시 입력하세요.");
				System.out.println();
			}
		}while(count>0);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		//next()와 nextLine()을 이을때 버퍼에 데이터가 남아있기때문에 버퍼를 비워줘야한다.
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("주 소 >> ");
		String memAddr = scan.nextLine();//띄어쓰기가 존재해야하기 때문
	
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into mymember"
					+ "(mem_id, mem_name, mem_tel, mem_addr)"
					+ " values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + " 회원 추가 성공!!");
			}else{
				System.out.println(memId + " 회원 추가 실패~~");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	// 회원ID를 인수로 받아서 해당 회원 ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0; // 회원ID 개수가 저장될 변수
		try{
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) as cnt"
					+ " from mymember"
					+ " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("cnt");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
		return count;
	}
}