package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	private SqlMapClient smc;//(02.02)
	private static MemberDaoImpl dao; // 1번 
	
	// 2번 생성자
	private MemberDaoImpl(){
		smc = SqlMapUtil.getSqlMapClient();
	} 
	
	// 3번
	public static MemberDaoImpl getInstance(){
		if(dao == null) dao = new MemberDaoImpl();
		return dao;	
	} 
	
	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("mymember.insertMember", memVo);
			
			if(obj==null) cnt = 1; // insert 성공 여부 판
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = smc.delete("mymember.deleteMember", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		try {
			cnt = smc.update("mymember.updateMember", memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = null;
		try {
			memList = smc.queryForList("mymember.getAllMember");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("mymember.getMemberCount", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		int cnt = 0;
		
		try {
			cnt = smc.update("mymember.updateMember2", paramMap);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
		
}