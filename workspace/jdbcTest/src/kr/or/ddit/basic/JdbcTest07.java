package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import kr.or.ddit.util.DBUtil;

/*
  	- 회원을 관리하는 프로그램을 작성하시오.
  	(오라클 DB의 MYMEMBER 테이블 이용)
  	
  	- 아래 메뉴의 기능을 모두 구현하시오.(CRUD 구현하기 연습 : create read update delete)
  	메뉴예시)
  	-- 작업 선택 --
  	1. 자료 추가
  	2. 자료 삭제
  	3. 자료 수정
  	4. 전체 자료 출력
  	5. 자료 수정2
  	0. 작업 끝.
  	-----------
  	작업선택 >> 
  	
  	자료추가할떄는 아이디가 주요키이기 때문에 id를 체크하는것도 있어야한다.
*/
public class JdbcTest07 {
	Connection conn = null;
	PreparedStatement pstmt = null; 
	Statement stmt = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		new JdbcTest07().start();	
	}

	private void start() {
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1:		
					add();
					break;
				case 2:	
					delete();
					break;
				case 3:		
					update();
					break;
				case 4:
					show();
					break;
				case 5:
					update2();
					break;
				case 0:
					System.out.println("프로그램을 닫습니다.");
					System.exit(0);
				default:
					System.out.println("잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}

	// 회원 정보를 수정하는 메서드(원하는 컬럼 한가지만 수정한다.)
	private void update2() {
		try {
			conn = DBUtil.getConnection();
			String memId; 
			int count = 0;
			do{
				System.out.print("아이디 입력 : ");
				memId = sc.next();
				
				String sql = "select count(*) as cnt"
						+ " from mymember"
						+ " where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count==0){
					System.out.println("입력한 아이디 " + memId 
							+ "는 등록되지 않은 아이디입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count==0);
			
			System.out.println("============================");
			System.out.println("      변경하고 싶은 정보    ");
			System.out.println("============================");
			System.out.println("1.이름\t2.전화번호\t3.주소");
			System.out.println("============================");
			System.out.print("선택 >> ");
			int input = sc.nextInt();
			
			String sql = "";
			int cnt=0;
			switch(input){
				case 1: 
					System.out.print("변경할 이름>> ");
					String memName = sc.next();
					sql = "update mymember set mem_name = ? where mem_id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, memName);
					pstmt.setString(2, memId);
					
					cnt = pstmt.executeUpdate();
					
					if(cnt>0){System.out.println("이름 수정 완료!!");}
					else{System.out.println("이름 수정 실패!!");}
					return;
				case 2: 
					System.out.print("변경할 전화번호>> ");
					String memTel = sc.next();
					sql = "update mymember set mem_tel = ? where mem_id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, memTel);
					pstmt.setString(2, memId);
					
					cnt = pstmt.executeUpdate();
					
					if(cnt>0){System.out.println("전화번호 수정 완료!!");}
					else{System.out.println("전화번호 수정 실패!!");}
					return;
				case 3: 
					sc.nextLine();
					System.out.print("변경할 주소>> ");
					String memAddr = sc.nextLine();
					sql = "update mymember set mem_addr = ? where mem_id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, memAddr);
					pstmt.setString(2, memId);
					
					cnt = pstmt.executeUpdate();
					
					if(cnt>0){System.out.println("주소 변경 완료!!");}
					else{System.out.println("주소 변경 실패!!");}
					return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
		
	}

	private void add(){
		try {
			conn = DBUtil.getConnection();
			String memId; 
			int count = 0;
			do{
				System.out.print("아이디 입력 : ");
				memId = sc.next();
				
				String sql = "select count(*) as cnt"
						+ " from mymember"
						+ " where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count==1){
					System.out.println("입력한 아이디 " + memId 
							+ "는 이미 등록된 아이디입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count==1);
			
			System.out.print("이름 입력 : ");
			String memName = sc.next();
			System.out.print("전화번호 입력 : ");
			String memTel = sc.next();
			System.out.print("주소 입력 : ");
			String memAddr = sc.next();
			
			String sql2 = "insert into mymember(mem_id, mem_name, mem_tel, mem_addr)"
					+ " values(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("등록 성공~~");
			}else{
				System.out.println("등록 실패!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	private void delete() {
		try {
			conn = DBUtil.getConnection();
			String memId; 
			int count = 0;
			do{
				System.out.print("아이디 입력 : ");
				memId = sc.next();
				
				String sql = "select count(*) as cnt"
						+ " from mymember"
						+ " where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count==0){
					System.out.println("입력한 아이디 " + memId 
							+ "는 등록되지 않은 아이디입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count==0);
			
			String sql2 = "delete mymember"
					+ " where mem_id=?";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("삭제 성공~~");
			}else{
				System.out.println("삭제 실패!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	private void update() {
		try {
			conn = DBUtil.getConnection();
			String memId; 
			int count = 0;
			do{
				System.out.print("아이디 입력 : ");
				memId = sc.next();
				
				String sql = "select count(*) as cnt"
						+ " from mymember"
						+ " where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count==0){
					System.out.println("입력한 아이디 " + memId 
							+ "는 등록되지 않은 아이디입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count==0);
			
			System.out.print("변경할 이름 입력 : ");
			String memName = sc.next();
			System.out.print("변경할 전화번호 입력 : ");
			String memTel = sc.next();
			System.out.print("변경할 주소 입력 : ");
			String memAddr = sc.next();
			
			String sql2 = "update mymember"
					+ " set mem_name = ?,"
					+ " mem_tel=?,"
					+ " mem_addr=?"
					+ " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("수정 성공~~");
			}else{
				System.out.println("수정 실패!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	private void show() {
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("=================================");
			System.out.println("아이디\t이름\t우편번호\t주소");
			System.out.println("=================================");
			while(rs.next()){
				System.out.print(rs.getString("mem_id")+"\t");
				System.out.print(rs.getString("mem_name")+"\t");
				System.out.print(rs.getString("mem_tel")+"\t");
				System.out.print(rs.getString("mem_addr"));
				System.out.println();
			}
			System.out.println("----------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("-- 작업 선택 --");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자 료 수 정 2");
		System.out.println("0. 작업 끝.");
		System.out.println("-----------");
		System.out.print("작업선택 >> ");
		int num = sc.nextInt();
		System.out.println();
		return num;
	}
}