package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  	문제)
 	'd:/d_other'폴더에 있는 '코알라.jpg'파일을 'd:/d_other/연습용'폴더에 
 	'복사본-코알라.jpg'파일로 복사하는 프로그램을 작성하시오.
 */
public class FileCopyTest {
	/*public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream("d:/d_other/코알라.jpg");
			
			FileOutputStream fos = new FileOutputStream("d:/d_other/연습용/복사본-코알라.jpg");
			
			int c;
			
			while((c=fin.read())!=-1){
				fos.write((byte)c);
			}
			fin.close();
			fos.close();
		} catch (IOException e) {
			
		}
	}*/
	
	public static void main(String[] args) {
		File file = new File("d:/d_other/코알라.jpg");
		if(!file.exists()){
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중지 합니다.");
			return;
		}
		
		try {
			// 복사할 파일 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fin);
			
			// 저장될 파일 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/복사본-코알라.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			System.out.println("복사 시작....");
			
			int data;// 읽어온 데이터가 저장될 변수
			
			/*while((data=fin.read())!=-1){
				fout.write((byte)data);
			} //FileStream이용할떄 사용*/
			
			// 사용한 스트림 닫기
			//fout.close();
			//fin.close();
			
			// 버퍼스트림 이용하기
			while((data=bis.read())!=-1){
				bout.write(data);
			}//버퍼스트림을 사용하면 속도가 빨라진다.
			bout.flush(); // 닫기 전에 해주는 습관을 들이는 것이 좋음
			
			// 사용한 스트림 닫기
			bout.close();
			bis.close();
			
			System.out.println("복사 완료....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
