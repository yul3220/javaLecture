package kr.or.ddit.member.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.web.IAction;

public class MemberListAction implements IAction {

	@Override
	public boolean isRedirect() {
		return false;//fasle이면 forward를 하겠다는 뜻
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 실제 처리할 내용은 이곳에 기술하고 처리가 끝난 후에 
		// 보여줄 View페이지를 반환하면 된다.
		
		// 회원 전체 리스트 
		// 회원 관리용 Service 객체 생성
		IMemberService service = MemberServiceImpl.getInstance();
		// 회원 전체 목록 가져오기
		List<MemberVO> memList = service.getAllMember();
		// 가져온 전체 목록을 request객체에 저장한다.
		request.setAttribute("memList", memList);
		
		// 출력할 view페이지를 리턴한다.
		return "/member/memberList.jsp";
	}
}