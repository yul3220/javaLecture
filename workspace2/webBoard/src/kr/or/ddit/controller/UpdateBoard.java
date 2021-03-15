package kr.or.ddit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@WebServlet("/UpdateBoard.do")
public class UpdateBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("board_title");
		String content = request.getParameter("board_content");
		BoardVO vo = new BoardVO();
		vo.setBoard_no(boardNo);
		vo.setBoard_title(title);
		vo.setBoard_content(content);
		
		IBoardService service = BoardServiceImpl.getService();
		
		int cnt = service.UpdateBoard(vo);
		
		request.setAttribute("result", cnt);
		
		request.getRequestDispatcher("board/boardList.jsp").forward(request, response);
	}

}
