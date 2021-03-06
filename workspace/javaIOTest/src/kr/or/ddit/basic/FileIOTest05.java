package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {
	
	public static void main(String[] args) {
		// 한글이 저장된 파일 읽어오기
		try {
			//FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
			//FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			//FileInputStream fis = new FileInputStream("d:/d_other/test_ansi.txt");
			FileInputStream fis = new FileInputStream("d:/d_other/test_utf8.txt");
			
			// 기본 인코딩 방식으로 읽어온다.
			//InputStreamReader isr = new InputStreamReader(fis);
			
			// 인코딩방식을 지정해서 읽어오기
			// 인코딩 방식 예시
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다.)
			// - UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			
			//InputStreamReader isr = new InputStreamReader(fis, "ms949");
			//ms949는 ansi는 깨지지 않으나, utf-8은 깨짐
			
			//InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			//ms949는 ansi는 깨지고, utf-8은 깨지지않음
			
			InputStreamReader isr = new InputStreamReader(fis, "US-ASCII");
			//영문전용이기때문에 한글은 다 깨진다.
			
			int c;
			//while((c=fr.read()) != -1){
			while((c=isr.read()) != -1){
				System.out.print((char)c);
			}
			
			//fr.close();
			
			// 보조 스트림을 닫으면 보조 스트림에서 사용한 기반이 되는 스트림도 자동으로 닫힌다.
			isr.close();
		} catch (IOException e) {
			
		}
	}
}
