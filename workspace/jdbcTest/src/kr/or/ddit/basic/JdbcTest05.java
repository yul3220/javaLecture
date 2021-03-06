package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest05 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null; 
		Scanner scan = new Scanner(System.in);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"JYR03",
					"java");
			
			System.out.println("계좌번호 정보 추가하기");
			System.out.print("계좌번호  : ");
			String bankNo = scan.next();
			
			System.out.print("은행명  : ");
			String bankName = scan.next();
			
			System.out.print("예금주명  : ");
			String userName = scan.next();
			
			/*
			// Statement객체를 이용하기
			String sql = "insert into bankinfo(bank_no, bank_name, bank_user_name, bank_date)"
					+ " values('" + bankNo + "','" + bankName 
					+ "','" + userName + "', sysdate)";
			
			System.out.println("SQL : " + sql);
			
			stmt = conn.createStatement();
			
			// SQL문이 select문일 때의 실행은 excuteQuery()메서드를 사용하고
			// SQL문 insert, update, delete등과 같이  select문이 아닐 경우에는
			// excuteUpdate()메서드를 사용하여 실행한다.
			// excuteUpdate()메서드의 반환값은 작업에 성공한 레코드 수 이다.
			int cnt = stmt.executeUpdate(sql);
			System.out.println("반환값 : " + cnt);*/
			
			// PreparedStatement 객체 이용하기
			
			// SQL문을 작성할 때 SQL문에 데이터가 들어갈 자리를
			// 물음표(?)로 표시해서 작성한다.
			String sql = "insert into bankinfo(bank_no, bank_name, "
					+ "bank_user_name, bank_date)"
					+ " values(?, ?, ?, sysdate)";

			// PreparedStatement 객체 생성하기
			// ==> 실행할 SQL문을 인수값으로 지정하여 생성한다.
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?) 자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표 번호, 데이터);
			// 		==> 물음표번호는 1번부터 시작한다.
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			// 데이터의 셋팅이 완료되면 SQL문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값 : " + cnt);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(stmt != null)try{ stmt.close(); }catch(SQLException e){}
			if(pstmt != null)try{ pstmt.close(); }catch(SQLException e){}
			if(conn != null)try{ conn.close(); }catch(SQLException e){}
		}
	}
}
//Statement는 컴파일을 그때그때 하지만, PreparedStatement는 사전 컴파일이 된다.
//값이 적은 경우는 Statement로 돌려도 시간이 많이 걸리지는 않지만, 데이터가 많은 경우 시간이 많이 걸려진다.
//PreparedStatement는 반복문을 처리할때 setString부분만 실행함으로써 속도가 빨라지는 효과를 가질 수 있다.
//
