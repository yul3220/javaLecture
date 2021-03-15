package kr.or.ddit.service;

import java.util.List;
import kr.or.ddit.vo.BoardVO;

public interface IBoardService {
	
	public List<BoardVO> AllBoard(String word);
	
	public BoardVO SelectBoard(int boardNo);
	
	public int UpBoardCnt(int boardNo);
	
	public int InsertBoard(BoardVO vo);
	
	public int DeleteBoard(int boardNo);
	
	public int UpdateBoard(BoardVO vo);
}
