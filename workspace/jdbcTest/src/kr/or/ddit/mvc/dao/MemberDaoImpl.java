package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{//최종적으로 DB에 처리하는 것들을 하면됨
	private static MemberDaoImpl dao; // 1번 //싱글톤(01.29)
	
	//프로그램에서 만들어진 기본생성자는 public이다.
	// 2번 생성자
	private MemberDaoImpl(){} //싱글톤(01.29)
	
	// 3번
	public static MemberDaoImpl getInstance(){
		if(dao == null) dao = new MemberDaoImpl();
		return dao;	
	} //싱글톤(01.29)
	
	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember"
					+ "(mem_id, mem_name, mem_tel, mem_addr)"
					+ " values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_adrr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from mymember"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set"
					+ " mem_name = ?,"
					+ " mem_tel = ?,"
					+ " mem_addr = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setString(2, memVo.getMem_tel());
			pstmt.setString(3, memVo.getMem_adrr());
			pstmt.setString(4, memVo.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				MemberVO memVo = new MemberVO();
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_adrr(rs.getString("mem_addr"));
				memList.add(memVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null) try { rs.close();}catch(SQLException e){}
			if(stmt!=null) try { stmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select count(*) as cnt"
					+ " from mymember"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null) try { rs.close();}catch(SQLException e){}
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}


	/*public int updateMember(String updateData, String updateField, String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember"
					+ " set " + updateField +" = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}*/

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// Key값 정보 => 회원ID(memid), 수정할컬럼명(field), 수정할 데이터(data)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update mymember"
					+ " set " + paramMap.get("field") +" = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memid"));
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}
		
}