package kr.or.ddit.basic.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = "test";
		String pass = "1234";
		
		if(id.equals(request.getParameter("id")) && pass.equals(request.getParameter("pass"))){
			   session.setAttribute("loginUser", id);  //세션에 값 저장
			   response.sendRedirect(request.getContextPath()+"/basic/03/main.jsp");  //페이지 전환
		}else{
			response.sendRedirect(request.getContextPath()+"/basic/03/sessionLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
