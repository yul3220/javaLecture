package kr.or.ddit.mvc.dao;

import java.util.List;
import kr.or.ddit.mvc.vo.BoardVO;

public interface IBoardDao {
	public int insertBoard(BoardVO boardVo);
	
	public List<BoardVO> AllBoard();
	
	public BoardVO showBoard(int boardNo);
	
	public int CountUp(int boardNo);
	
	public int updateBoard(BoardVO boardVo);
	
	public int deleteBoard(int boardNo);
	
	public List<BoardVO> SearchBoard(String title);
	
	public int existBoard(int boardNo);
}
