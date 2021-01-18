package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 4개짜리 배열 생성
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			// 읽어올 데이터가 있는지 검사한다.
			// available() ==> 남아있는 데이터 개수를 반환한다.
			while(input.available() > 0){
				//input.read(temp); // 입력(읽기)
				//output.write(temp); // 출력(쓰기)
				
				// 실제로 읽어온 byte수를 반환한다.
				int len = input.read(temp);
				
				output.write(temp, 0, len);
				
				System.out.println("반복문 안에서 temp => " + 
										Arrays.toString(temp));
				//반복문 안에서 temp => [4, 5, 6, 7]
				//반복문 안에서 temp => [8, 9, 6, 7]
				//=> 마지막은 2개만 읽었기때문에 8,9만 채우고 나머지 전의 값중에 뒤의 2개가 출력된다.
			}
			
			outSrc = output.toByteArray();
			
			System.out.println();
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
		} catch (IOException e) {
			
		}
	}
}//
