package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {
	public static void main(String[] args) {
		// 파일에 데이터를 출력하는 연습
		try {
			FileOutputStream fos = new FileOutputStream("d:/d_other/out.txt");
			
			for(char ch='A'; ch<='Z'; ch++){
				// ch변수의 값을 파일로 출력한다.
				fos.write(ch);
			}
			
			System.out.println("쓰기 작업 완료");
			fos.close(); // 쓰기 작업 완료 후 스트림 닫기
			
			// =====================================
			// 위에서 저장된 데이터를 읽어와 화면에 출력하시오.
			FileInputStream fin = new FileInputStream("d:/d_other/out.txt");
			
			int c; // 읽어온 데이터를 저장할 변수
			while((c=fin.read())!= -1){
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char)c + " ");
			}
			fin.close();
			System.out.println();
			System.out.println("읽어오기 작업 끝~~");
		} catch (IOException e) {
			
		}
	}
}
