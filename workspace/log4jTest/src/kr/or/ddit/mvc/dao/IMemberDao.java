package kr.or.ddit.mvc.dao;

import java.util.List;
import java.util.Map;
import kr.or.ddit.mvc.vo.MemberVO;

//역슬래시**하고 enter를 치면 이렇게 나옴
/** 
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 
 * Service에 전달하는 Interface
 * 
 * 각각의 메서드 하나가 DB와 관련된 작업 1개를 수행하도록 작성한다.
 * 
 * @author PC-21
 *
 */

public interface IMemberDao{
	//public int insertMember(String id, String name, String tel, String addr)
	//형식으로 가져와도 되긴하지만, 매개변수의 수를 줄이는 것이 좋음
	
	//전체적인 주석을 작성하는 것이 좋음, 이런식이 주석을 달을때는 메서드를 
	//작성한 후 메서드 바로 위에 주석을 닫느게 좋음
	 
	/** 
	 * MemberVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo insert할 데이터가 저장된 MemberVO객체
	 * @return insert 성공 : 1 , 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원 ID를 인수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 삭제 성공 : 1, 삭제 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO자료를 이용하여 회원 정보를 Update하는 메서드
	 * @param memVo update할 회원 정보가 저장된 MemberVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원 ID를 인수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원ID 개수
	 */
	public int getMemberCount(String memId);
	
	//public int updateMember(String updateData, String updateField, String memId);
	
	/**
	 * Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 * Key값 정보 => 회원ID(memid), 수정할컬럼명(field), 수정할 데이터(data)
	 * @param paramMap 수정할 컴럼, 수정할 데이터, 회원 id가 저장된 Map객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember2(Map<String, String> paramMap);
}
