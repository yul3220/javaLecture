package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 주어진 byte배열에서 데이터를 읽어오는 스트림 객체
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		
		// 출력하는 byte값을 내부에 있는 byte배열에 출력하는 스트림 객체
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data; // 읽어온 데이터가 저장될 변수 선언
		
		// read()메서드 ==> 더 이상 읽어올 데이터가 없으면 -1을 반환한다.
		while(( data = input.read() ) != -1){ // 읽기 
			// 이 부분에 읽어온 데이터를 처리하는 코드가 들어가면 된다.
			
			output.write(data); // 출력(쓰기)
		}
		
		// ByteArrayOutput스트림에 출력한 데이터를 
		// toByteArray()메서드를 이용해서 byte배열로 구해온다.
		outSrc = output.toByteArray();
		
		//자원을 사용한 후에 반납이 해주어야 한다. => 스트림 객체 닫기
		// 사용했던 자원을 반납한다.
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
	}
}
