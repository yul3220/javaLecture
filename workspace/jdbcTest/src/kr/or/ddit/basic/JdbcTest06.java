package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	LPROD테이블에 새로운 데이터를 추가하시오.
 	
 	조건) LPROD_GU와 LPROD_NM은 직접 입력 받아서 처리하고,
 		LPROD_ID는 현재의 LPROD_ID중 제일 큰 값보다 1만큼 증가된 값으로 한다.
 		그리고, 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
 */

public class JdbcTest06 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JYR03",
					"java");
			*/
			conn = DBUtil.getConnection();
				while(true){
					System.out.print("LPROD_GU 입력 : ");
					String gu = scan.next();
					
					String sql = "select lprod_id"
							+ " from lprod"
							+ " where lprod_gu = upper(?)";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, gu);
					
					rs = pstmt.executeQuery();
					
					int n = 0;
					
					if(rs.next()){
						n = rs.getInt("lprod_id");
					}
					
					if(n>0){
						System.out.println("다른 분류번호를 입력하세요");
						continue;
					}
					
					System.out.print("LPROD_NM 입력 : ");
					String nm = scan.next();
					
					sql = "insert into lprod"
						+ " select max(lprod_id)+1, ?, ?"
						+ "	from lprod";
						
					pstmt = conn.prepareStatement(sql);
						
					pstmt.setString(1, gu);
					pstmt.setString(2, nm);
						
					int cnt = pstmt.executeUpdate();
						
					System.out.println(cnt + "행 이(가) 삽입되었습니다.");
					
					break;
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
