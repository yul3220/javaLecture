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

@WebServlet("/InsertBoard.do")
public class InsertBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("board_title");
		String writer = request.getParameter("board_writer");
		String content = request.getParameter("board_content");
		
		IBoardService service = BoardServiceImpl.getService();
		BoardVO vo = new BoardVO();
		vo.setBoard_title(title);
		vo.setBoard_writer(writer);
		vo.setBoard_content(content);
		
		service.InsertBoard(vo);
		
		request.getRequestDispatcher("board/boardList.jsp").forward(request, response);
	}

}
