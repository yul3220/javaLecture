package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  	문제)
 	'd:/d_other'폴더에 있는 '코알라.jpg'파일을 'd:/d_other/연습용'폴더에 
 	'복사본-코알라.jpg'파일로 복사하는 프로그램을 작성하시오.
 */
public class FileCopyTest {
	public static void main(String[] args) {
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
	}
}
