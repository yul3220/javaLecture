package kr.or.ddit.basic.req_resp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/RequestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		
		String operator = request.getParameter("operator");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Request객체연습</title></head>");
		out.println("<body>");
		out.println("<hr>");
		out.println("<h2>계산 결과</h2>");
		out.println("<hr>");
		
		double result = 0;
		boolean calOk = true;
		switch(operator) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			case "*":
				result = num1 * num2;
				break;
			case "/":
				if(num2!=0) {
					result = (double)num1 / num2;break;
				}else {
					calOk = false;
				}
				break;
			case "%":
				if(num2!=0) {
					result = num1 % num2;
				}else {
					calOk = false;
				}
				break;
		}
		//out.print(num1 +" "+ operator +" "+ num2 + " = " + result);
		//out.printf("%d %s %d = %f", num1, operator, num2, result);
		out.printf("%d %s %d = ", num1, operator, num2);
		
		// 계산 성공 여부를 확인해서 결과를 출력한다.
		if(calOk==true) { //정상적인 계산이 완료되었으면 결과 출력
			out.println(result);
		}else {//분모가 0일때 나눗셈이나 나머지 연산을 했을 때
			out.println("계산 불능(0으로 나누거나 나머지를 구함)");
		}
		
		out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
