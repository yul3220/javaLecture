package kr.or.ddit.basic;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {
	private static final String key = "a1b2c3d4e5f6g7h8";
	//암호화에서 사용된 키와 복호화에서 사용된 키가 일치해야한다. => 값이 변하지 않도록 private final로 주는 것이 좋다.
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 암호화 할 데이터
		//String plainText = "Hello, World! 가나다라 대한민국 12345 ^&*()";
		String plainText = "Hello, World!";
		
		//final String key = "a1b2c3d4e5f6g7h8";// 키값으로 사용한 문자는 16글자 이상
		System.out.println("MD5 : " + CryptoUtil.md5(plainText));
		System.out.println("SHA-256 : " + CryptoUtil.sha256(plainText));
		System.out.println("SHA-512 : " + CryptoUtil.sha512(plainText));
		
		System.out.println("--------------------------------------------");
		System.out.println();
		
		String result = CryptoUtil.encryptAES256(plainText, key);
		String result2 = CryptoUtil.decryptAES256(result, key);
		
		System.out.println("원본데이터 : " + plainText);
		System.out.println("암호화 된 데이터 : " + result);
		System.out.println("복호화 된 데이터 : " + result2);
	}
}
