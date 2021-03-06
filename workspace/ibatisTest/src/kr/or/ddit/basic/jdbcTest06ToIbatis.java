package kr.or.ddit.basic;

import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Scanner;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/*
 *  jdbcTest프로젝트에 있는 'jdbcTest6,java'의 처리방법을 
 *  ibatis를 이용하여 처리하는 것으로 변경하시오
 *  쿼리문이 저장될 xml문서 이름은 'jdbc06.xml'으로 한다.
	LPROD테이블에 새로운 데이터를 추가하시오.
	
	조건) LPROD_GU와 LPROD_NM은 직접 입력 받아서 처리하고,
		LPROD_ID는 현재의 LPROD_ID중 제일 큰 값보다 1만큼 증가된 값으로 한다.
		그리고, 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
*/
public class jdbcTest06ToIbatis {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			SqlMapClient smc =
					SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			int lprodId = (int) smc.queryForObject("jdbc06.maxid");
			
			int cnt;
			String lprodGu;
			do {
				System.out.print("추가할 lprod_gu 입력 : ");
				lprodGu = scan.next();
				lprodGu = (lprodGu + "    ").substring(0,4);
				cnt = (int) smc.queryForObject("jdbc06.existLprod", lprodGu);
				
				if(cnt==1) {
					System.out.println("입력한 상품 분류 코드 " + lprodGu 
							+ "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(cnt==1);
			
			System.out.print("상품 분류명 입력 : ");
			String lprodNm = scan.next();
			
			LprodVO lvo = new LprodVO();
			
			lvo.setLprod_id(lprodId);
			lvo.setLprod_gu(lprodGu);
			lvo.setLprod_nm(lprodNm);
			
			Object obj = smc.insert("jdbc06.insertLprod", lvo);
			
			if(obj==null) {
				System.out.println("insert 작업 성공!!!");
			}else {
				System.out.println("insert 작업 실패~~~");
			}
			
			System.out.println("insert 작업 끝....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
