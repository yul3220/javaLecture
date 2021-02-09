package kr.or.ddit.basic.req_resp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Request 객체 관련 예제
@WebServlet("/RequestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 받은 데이터의 인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		//web.xml에 정의한 URIEncoding은 get방식일떄의 encoding방식을 지정한것이다.
		//따라서, post방식은 따로 지정을 해줘야한다.
		
		//	getParameter("파라미터명") ==> 해당 파라미터에 설정된 '값'을 가져온다.
		// 		==> 가져온 '값'의 자료형은 무조건 'String'이다.
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		//hobby처럼 여러개의 값이 넘어오게 되는 경우 자동으로 배열로 만들어준다.
		// getParametValues("파라미터명")
		//		==> 파라미터명이 같은 것이 여러개일 경우에 사용한다.
		//		==> 가져온 값의 자료형 'String[]'이다.
		
		// getParameterValues()를 이용해서 hobby로 설정된
		// checkbox의 값들을 모두 읽어오기
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Request객체연습</title></head>");
		out.println("<body>");
		out.println("<h2>request 테스트 결과 -1</h2>");
		out.println("<hr>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>"+userName+"</td></tr>");
		out.println("<tr><td>직업</td>");
		out.println("<td>"+job+"</td></tr>");
		out.println("<tr><td>취미</td>");
		out.println("<td>");
		
		// 배열의 크기만큼 반복문을 돌려서 값을 출력한다.
		/*for(int i=0; i< hobbies.length; i++) {
			out.println(hobbies[i]+ "<br>");
		}*/
		
		// 향상된 for문 이용하기
		for(String hobby : hobbies) {
			out.println(hobby+"<br>");
		}

		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<hr>");
		out.println("<h2>request 테스트 결과-2</h2>");
		out.println("<ol>");
		out.println("<li>클라이언트 IP주소 : " + request.getRemoteAddr() + "</li>");
		out.println("<li>요청메서드 : " + request.getMethod() + "</li>");
		out.println("<li>ContextPath : " + request.getContextPath() + "</li>");
		out.println("<li>프로토콜 : " + request.getProtocol() + "</li>");
		out.println("<li>URL 정보 : " + request.getRequestURL() + "</li>");
		out.println("<li>URI 정보 : " + request.getRequestURI() + "</li>");
		out.println("</ol>");
		out.println("<hr>");
		
		// getParameterNames() ==> 전송된 모든 파라미터명을 
		//		Enumeration<String>객체에 담아서 반환한다.
		Enumeration<String> params = request.getParameterNames();
		
		out.println("<h2>request 테스트 결과 - getParameterNames()메서드</h2>");
		
		while(params.hasMoreElements()) {//다음 데이터가 있는지 검사
			String name = params.nextElement();//다음의 데이터를 꺼내옴
			out.println(name + "<br>");
		}
		out.println("<hr>");
		
		//------------------------------------------
		
		// getParameterMap() ==> 전송된 모든 파라미터를 Map객체에 담아서 반환한다.
		// 		이 Map객체의 key값은 '파라미터명'이며 자료형은 'String'이고
		//		value값은 해당 파라미터에 저장된 '값'이며 자료형은  'String[]'이다.
		out.println("<h2>request 테스트 결과 - getParameterMap()메서드</h2>");
		out.println("<table border='1'>");
		out.println("<tr><td>파라미터 name</td>");
		out.println("<td>파라미터 value</td></tr>");
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Iterator<String> it = paramMap.keySet().iterator();
		while(it.hasNext()) {
			String paramName = it.next();// 파라미터 name값 구하기
			out.println("<tr><td>"+paramName+"</td>");
			out.println("<td>");
			
			// 파라미터 값 구하기
			String[] paramValue = paramMap.get(paramName);
			
			// 파라미터 값이 없는 경우
			if(paramValue==null || paramValue.length==0) {
				continue;
			}else if(paramValue.length==1){// 파라미터가 배열이 아닌 경우
				out.println(paramValue[0]);
			}else{// 파라미터가 배열인 경우
				for(String value: paramValue){
					out.println(value + "<br>");
				}
			}
			out.println("</td></tr>");
		}
		out.println("</table>");
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
