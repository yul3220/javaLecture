package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class javaTestSem06 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JYR03",
					"java");*/
			conn = DBUtil.getConnection();
			// Lprod_id는 현재의 lprod_id 중 제일 큰 값보다 1 큰값으로 
			String sql = "select max(lprod_id)+1 maxid from lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int lprodId = 0;
			if(rs.next()){
				lprodId = rs.getInt("maxid");
			}
			
			// 입력받은 lprod_gu가 이미 있으면 다시 입력하기
			String lprodGu; // 상품분류 코드가 저장될 변수
			int count = 0;
			do{
				System.out.print("상품 분류 코드 입력 : ");
				lprodGu = scan.next();
				
				String sql2 = "select count(*) as cnt"
						+ " from lprod"
						+ " where lprod_gu = ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, lprodGu);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count==1){
					System.out.println("입력한 상품 분류 코드 " + lprodGu 
							+ "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count==1);
			
			System.out.print("상품 분류명 입력 : ");
			String lprodNm = scan.next();
			
			String sql3 = "insert into lprod(lprod_id, lprod_gu, lprod_nm)"
					+ " values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, lprodId);
			pstmt.setString(2, lprodGu);
			pstmt.setString(3, lprodNm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("등록 성공~~");
			}else{
				System.out.println("등록 실패!!");
			}
			
		//} catch (ClassNotFoundException e) {
		//	e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}

	}
}
