package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	문제) 사용자로부터 도, 시, 군, 구 중 한가지를 입력 받아
 	 DB의 Member테이블에서 회원의 주소에 입력한 값이 포함되는 데이터를
 	 모두 출력하세요.(아이디, 이름, 우편번호, 주소)
 */

public class JdbcTest04 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JYR03",
					"java");
			
			System.out.print("검색할 주소 입력  : ");
			String addr = sc.nextLine();
			
			String sql = "select mem_id, mem_name, mem_zip, mem_add1 ||' '|| mem_add2 as address"
					+ " from member"
					+ " where mem_add1 like '%" + addr +"%'";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println();
			System.out.println(" == 쿼리문 처리 결과 == ");
			while(rs.next()){
				System.out.println("아이디 : " + rs.getString("mem_id"));
				System.out.println("이름 : " + rs.getString("mem_name"));
				System.out.println("우편번호 : " + rs.getString("mem_zip"));
				System.out.println("주소 : " + rs.getString("address"));
				System.out.println("----------------------------------");
			}
			System.out.println("출력 끝.....");	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			// 5. 자원 반납하기
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(stmt != null)try{ stmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}
}
