package kr.or.ddit.mvc.service;

import java.util.List;
import kr.or.ddit.mvc.vo.MemberVO;

/**
 * Dao에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고
 * 받아온 결과를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 Dao의 메서드 구조와 같게 만든다.
 * 
 * @author PC-21
 *
 */

public interface IMemberService {
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
	
	public int updateMember(String updateData, String updateField, String memId);
}
