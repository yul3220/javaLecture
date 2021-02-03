package kr.or.ddit.mvc.vo;

/*
 	DB테이블에 있는 컬럼을 기준으로 데이터를 객체화할 클래스이다.
 	DB테이블의 '컬럼명'이 이 클래스의 멤버변수가 된다.
 	
 	DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.
*/

public class MemberVO {
	//여기에서 만들때는 소문자로 만들어야 한다.
	private String mem_id;
	private String mem_name;
	private String mem_tel;
	private String mem_adrr;
	
	// 만약에 생성자를 만들었을 때는 반드시 기본 생성자도 같이 만들어 준다.
	
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_adrr() {
		return mem_adrr;
	}

	public void setMem_adrr(String mem_adrr) {
		this.mem_adrr = mem_adrr;
	}
	
}
