package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC 드라이버를 로딩하고 Connection객체를 생서하는
// 메서드로 구성된 class 만들기

// ResourceBundle객체 이용하기
public class DBUtil3 {
	// ResourceBundle객체 변수 선언
	private static ResourceBundle bundle;
	
	// static 초기화 블럭
	static{
		// 객체 생성
		bundle = ResourceBundle.getBundle("dbinfo");
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~");
			e.printStackTrace();
		}
	}
	
	// DB애 접속하고 접속에 성공하면 Connection객체를 반환하는 메서드
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결 실패~~~");
			return null;
		}
	}
	
}
