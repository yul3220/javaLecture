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
	
	/**
	 * 게시물의 전체를 가져오기 위한 메서드
	 * @return 게시물의 전체 
	 */
	public List<BoardVO> AllBoard();
	
	/**
	 * boardNo와 일치하는 게시글의 정보를 가지고 오기 위한 메서드
	 * @param boardNo 게시물 번호
	 * @return 게시물번호에 해당하는 내용
	 */
	public BoardVO showBoard(int boardNo);
	
	/**
	 * 게시물을 볼때마다 조회수를 증가시키기 위한 메서드
	 * @param boardNo 게시물번호
	 * @return 게시물의 조회수+1
	 */
	public int CountUp(int boardNo);
	
	/**
	 * BoardVO자료를 이용하여 회원 정보를 Update하는 메서드
	 * @param boardVo update할 회원 정보가 저장된 BoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * boardNo를 인수로 받아서 해당 번호 게시판을 삭제하는 메서드
	 * @param boardNo 삭제할 번호
	 * @return 삭제 성공 : 1, 삭제 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * title을 조회하여 일치하는 게시물을 가지고 오기 위한 메서드
	 * @param title 게시물의 제목
	 * @return title의 이름을 가지고 있는 모든 게시글
	 */
	public List<BoardVO> SearchBoard(String title);
	
	/**
	 * 해당 번호의 게시글이 존재하는 여부를 확인하기 위한 메서드
	 * @param boardNo 게시판번호
	 * @return 존재x : 0 , 존재 : 1이상
	 */
	public int existBoard(int boardNo);
}
