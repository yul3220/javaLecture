package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.dao.BoardDaoImpl;
import kr.or.ddit.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getService() {
		if(service==null) service = new BoardServiceImpl();
		return service;
	}
	
	@Override
	public List<BoardVO> AllBoard(String word) {
		List<BoardVO> list = null;
		
		try {
			list = dao.AllBoard(word);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public BoardVO SelectBoard(int boardNo) {
		BoardVO vo = null;
		
		try {
			vo = dao.SelectBoard(boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	@Override
	public int UpBoardCnt(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = dao.UpBoardCnt(boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int InsertBoard(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.InsertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int DeleteBoard(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = dao.DeleteBoard(boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int UpdateBoard(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.UpdateBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}


}