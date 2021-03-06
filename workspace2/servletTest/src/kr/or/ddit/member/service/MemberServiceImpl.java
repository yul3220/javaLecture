package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {//현재 고급자바 시간에는 Service는 이런식이면 끝
	// Service객체는 Dao객체를 사용하기 때문에 
	// Dao객체가 저장될 변수가 필요하다.
	//다른 SQL 사용할때(MYSQL, NOSQL등) 확장성을 위해서
	//Dao부분만 수정하면 생성자에서 다른 거 갖고 와서 쓰면 됨(밑부분 다 수정하지 않아도 된다.)
	private IMemberDao memDao;
	private static MemberServiceImpl service; //싱글톤(01.29)
	
	// 생성자
	private MemberServiceImpl(){ //싱글톤(01.29) => public에서 private로 바꿈
		//IMemberDao객체인데 어떻게 MemberDaoImpl()객체를 넣을 수 있는지?
		//그걸 받아서 구현한거라서 조상클래스니까 가능하다.
		//인터페이스가 조상이고 dao가 interface를 상속받아 구현한것이기때문에 자식이다.
		//memDao = new MemberDaoImpl();
		memDao = MemberDaoImpl.getInstance(); //싱글톤(01.29)
	}
	
	public static MemberServiceImpl getInstance(){
		if(service==null) service = new MemberServiceImpl();
		return service;
	} //싱글톤(01.29)
	
	@Override
	public int insertMember(MemberVO memVo) {
		return memDao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return memDao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return memDao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return memDao.getMemberCount(memId);
	}


	/*@Override
	public int updateMember(String updateData, String updateField, String memId) {
		return memDao.updateMember(updateData, updateField, memId);
	}*/

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return memDao.updateMember2(paramMap);
	}

}
