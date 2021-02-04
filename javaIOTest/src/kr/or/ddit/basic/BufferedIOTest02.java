package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {
	public static void main(String[] args) {
		// 문자 기반의 Bufferd스트림 사용하기
		try {
			FileReader fr 
				= new FileReader("./src/kr/or/ddit/basic/FileIOTest01.java");
			//자바는 실행하면 폴더(javaIOTest)로 시작된다.
			//패키지도 폴더이다.
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";
			
			// readLine(); ==> 한줄 단위로 읽어온다.
			for(int i=0;(temp=br.readLine())!=null; i++){
				System.out.printf("%4d : %s\n", i, temp);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
