package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.util.SqlMapUtil;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	private SqlMapClient smc;
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {
		smc = SqlMapUtil.getSqlMapClient();
	}
	
	public static BoardDaoImpl getInstance() {
		if(dao==null) dao = new BoardDaoImpl();
		return dao;
	}

	@Override
	public List<BoardVO> AllBoard(String word) throws SQLException {
		return smc.queryForList("board.AllBoard", word);
	}

	@Override
	public BoardVO SelectBoard(int boardNo) throws SQLException {
		return (BoardVO) smc.queryForObject("board.SelectBoard", boardNo);
	}

	@Override
	public int UpBoardCnt(int boardNo) throws SQLException {
		return smc.update("board.UpBoardCnt", boardNo);
	}
	
	@Override
	public int InsertBoard(BoardVO vo) throws SQLException {
		return (int) smc.insert("board.InsertBoard", vo);
	}

	@Override
	public int DeleteBoard(int boardNo) throws SQLException {
		return smc.delete("board.DeleteBoard", boardNo);
	}

	@Override
	public int UpdateBoard(BoardVO vo) throws SQLException {
		return smc.update("board.UpdateBoard", vo);
	}


}
