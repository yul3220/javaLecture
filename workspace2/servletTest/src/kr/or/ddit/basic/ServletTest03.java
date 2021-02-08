package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	서블릿의 동작 방식에 대하여...
  	1. 사용자(클라이언트)가 URL을 클릭하면 HttpRequest를 Servlet Container로 
  		전송(요청)한다.
  	2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어는 서블릿을 통해서
  	 	처리할 것인지를 검색한다.
  		(이 때 로딩이 안된 경우에는 해당 서블릿을 로딩한다. 최초에 로딩할 때 
  		init()메서드를 자동으로 호출한다.)
  		(Servlet 3.0이상에서는 애노테이션(@webSerlvet)으로 설정 가능)
  	3. Servlet Container는 요청을 처리할 개별 쓰레드 객체를 생성하여 해당 서블릿 
  		객체의 service()메서드를 호출한다.
  		(이 때 HttpServletRequest 및 HttpServletResponse객체를 생성하여
  		파라미터로 넘겨준다.)
  	4. service()메서드는 요청의 메서드 타입(get 또는 post)을 체크하여 적절한 메서드를 호출한다.
  	  (doGet(), doPost(), doPut(), doDelete() 등)
  	5. 요청처리가 완료되면  HttpServletRequest 및  HttpServletResponse객체는
  		자동으로 소멸된다.
  	6. 컨테이너로부터 서블릿이 제거되는 경우 destroy()메서드가 호출된다.
 */

// Servlet의 life Cycle
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@Override
	public void init() throws ServletException {
		System.out.println("servlet : " + this.getServletName()
						+ "에서 init()메서드 호출....");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service()메서드 시작...");
		
		// GET방식과 POST방식에 맞는 메서드 호출하기
		
		// 방법1 ==> 상위 객체(HttpServlet)의 service()메서드로 위임
		// super.service(req, resp);
		
		// 방법2 ==> 클라이언트의 요청 방식(GET, POST등)을 구분해서 직접 메서드를 호출하기
		String method = req.getMethod();
		System.out.println("method=" + method);
		
		if("GET".equals(method)) {
			this.doGet(req, resp);
		}else {
			this.doPost(req, resp);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body><h1>doGet()메서드로 처리한 결과입니다.</h1></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body><h1>doPost()메서드로 처리한 결과입니다.</h1></body></html>");
	}
	
	@Override
	public void destroy() {
		System.out.println("servlet : " + this.getServletName());
		System.out.println("에서 destroy()메서드 호출...");
	}

}
