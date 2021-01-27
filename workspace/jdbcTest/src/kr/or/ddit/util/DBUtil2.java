package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC 드라이버를 로딩하고 Connection객체를 생서하는
// 메서드로 구성된 class 만들기

// Properties객체를 이용하여 처리하기
public class DBUtil2 {
	private static Properties prop; // Properties 객체 변수 선언
	// static 초기화 블럭
	static{
		prop = new Properties(); // Properties 객체 생성
		File f = new File("res/dbinfo.properties");
		
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);
			prop.load(fin);
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일이 없거나 파일 입출력 오류입니다.");
			System.out.println("드라이버 로딩 실패~~~");
			e.printStackTrace();
		}
	}
	
	// DB애 접속하고 접속에 성공하면 Connection객체를 반환하는 메서드
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(
				prop.getProperty("url"),
				prop.getProperty("user"),
				prop.getProperty("pass") );
		} catch (SQLException e) {
			System.out.println("DB연결 실패~~~");
			return null;
		}
	}
	
}
