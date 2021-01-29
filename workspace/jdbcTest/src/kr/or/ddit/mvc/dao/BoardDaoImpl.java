package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kr.or.ddit.mvc.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements IBoardDao {
	private static BoardDaoImpl dao;//싱글톤(01.29)
	
	private BoardDaoImpl(){}//싱글톤(01.29)
	
	public static BoardDaoImpl getInstance(){
		if(dao == null) dao = new BoardDaoImpl();//싱글톤(01.29)
		return dao;
	}
	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board"
					+ " (board_no, board_title, board_writer, "
					+ " board_date, board_cnt, board_content) "
					+ " values(board_seq.nextVal, ?, ?, sysdate, 0, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public List<BoardVO> AllBoard() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board order by board_no desc";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null) try { rs.close();}catch(SQLException e){}
			if(stmt!=null) try { stmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return boardList;
	}

	@Override
	public BoardVO showBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVo = new BoardVO();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board"
					+ " where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return boardVo;
	}

	@Override
	public int CountUp(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board"
					+ " set board_cnt = (select board_cnt+1 from jdbc_board "
					+ " where board_no = ?)"
					+ " where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);
			
			cnt = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board "
					+ " set board_title = ?, board_content = ?"
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
			if(conn!=null) try{conn.close();}catch(SQLException e){}
		}
		return cnt;
	}


	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board"
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
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
	public List<BoardVO> SearchBoard(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board  "
					+ " where board_title LIKE '%' || ? ||'%'"
					+ " order by board_no desc";
;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null) try { rs.close();}catch(SQLException e){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		return boardList;
	}

	@Override
	public int existBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select count(*) as cnt from jdbc_board"
					+ " where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try { pstmt.close();}catch(SQLException e){}
			if(conn!=null) try { conn.close();}catch(SQLException e){}
		}
		
		return cnt;
	}
}
