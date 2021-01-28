package kr.or.ddit.mvc.service;

import java.util.List;
import kr.or.ddit.mvc.vo.BoardVO;

public interface IBoardService {
	/**
	 * BoardVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * @param boardVo insert할 데이터가 저장된 BoardVO객체
	 * @return insert 성공 : 1, 실패 : 0
	 */
	public int insertBoard(BoardVO boardVo);
	
	public List<BoardVO> AllBoard();
	
	public BoardVO showBoard(int boardNo);
	
	public int CountUp(int boardNo);
	
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * boardNo를 인수로 받아서 해당 번호 게시판을 삭제하는 메서드
	 * @param boardNo 삭제할 번호
	 * @return 삭제 성공 : 1, 삭제 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	public List<BoardVO> SearchBoard(String title);
	
	public int existBoard(int boardNo);
}
