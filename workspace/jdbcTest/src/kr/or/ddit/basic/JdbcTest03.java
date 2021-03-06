package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제2) lprod_id값을 2개 입력받아 두 값중 작은 값 부터 큰 값 사이의 자료들을 출력하시오.

public class JdbcTest03 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; 
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫번째 숫자를 입력 : ");
		int a = sc.nextInt();
		System.out.print("두번째 숫자를 입력 : ");
		int b = sc.nextInt();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JYR03",
					"java");
			
			String sql = "";
			if(b<a){
				sql = "select * from lprod "
						+ "where lprod_id between " + b + " and " + a;
			}else if(a<b){
				sql = "select * from lprod "
						+ "where lprod_id between " + a + " and " + b;
			}
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == 쿼리문 처리 결과 == ");
			while(rs.next()){
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("----------------------------------");
			}
			System.out.println("출력 끝.....");	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null)try{ rs.close(); }catch(SQLException e){}
			if(stmt != null)try{ stmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}

	}
}
