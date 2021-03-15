package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;
import kr.or.ddit.vo.BoardVO;

public interface IBoardDao {
	public List<BoardVO> AllBoard(String word) throws SQLException;
	
	public BoardVO SelectBoard(int boardNo) throws SQLException;
	
	public int UpBoardCnt(int boardNo) throws SQLException;
	
	public int InsertBoard(BoardVO vo) throws SQLException;
	
	public int DeleteBoard(int boardNo) throws SQLException;
	
	public int UpdateBoard(BoardVO vo) throws SQLException;
}
 