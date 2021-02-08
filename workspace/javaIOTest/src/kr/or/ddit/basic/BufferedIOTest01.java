package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		try {
			// 입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			// 버퍼의 크기가 5인 버퍼 스트림 객체 생성
			// 버퍼의 크기를 지정하지 않으면 기본 크기(8192byte)
			//버퍼기반의 스트림은 줄단위로 한줄씩 읽어오는 기능도 있음
			//BufferedOutputStream bos = new BufferedOutputStream(fout, 5);
			BufferedOutputStream bos = new BufferedOutputStream(fout);
			
			for(char ch='1'; ch<='9'; ch++){
				bos.write(ch); //12345
			}
			
			// 버퍼에 남아있는 모든 데이터를 모두 출력 시킨다.
			bos.flush();
			
			bos.close();
			System.out.println("출력 작업 끝....");
		} catch (IOException e) {
			// TODO: handle exception
		}	
	}
}//
