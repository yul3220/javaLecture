package kr.or.ddit.mvc.service;

import java.util.List;
import kr.or.ddit.mvc.dao.BoardDaoImpl;
import kr.or.ddit.mvc.dao.IBoardDao;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao boardDao;
	private static BoardServiceImpl service;
	
	private BoardServiceImpl(){//public에서 privat로 바꿈
		//boardDao = new BoardDaoImpl();
		boardDao = BoardDaoImpl.getInstance();//싱글톤(01.29)
	}
	
	public static BoardServiceImpl getInstance(){
		if(service==null) service = new BoardServiceImpl();
		return service;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		return boardDao.insertBoard(boardVo);
	}

	@Override
	public List<BoardVO> AllBoard() {
		return boardDao.AllBoard();
	}

	@Override
	public BoardVO showBoard(int boardNo) {
		return boardDao.showBoard(boardNo);
	}

	@Override
	public int CountUp(int boardNo) {
		return boardDao.CountUp(boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return boardDao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> SearchBoard(String title) {
		return boardDao.SearchBoard(title);
	}

	@Override
	public int existBoard(int boardNo) {
		return boardDao.existBoard(boardNo);
	}

}
