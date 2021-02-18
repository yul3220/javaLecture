package kr.or.ddit.basic.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testEncodingFilterServlet.do")
public class TestEncodingFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("username");
		System.out.println("userName = " + userName);
		
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body>");
		out.println("<h2>당신이 입력한 이름은 " + userName + "이군요</h2>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
