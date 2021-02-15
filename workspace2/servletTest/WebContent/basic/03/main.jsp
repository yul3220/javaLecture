<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("loginUser")==null){
	response.sendRedirect(request.getContextPath()+"/basic/03/sessionLogin.jsp");
} else {
%>
    <h3><%=session.getAttribute("loginUser")%>님 반갑습니다.</h3><br>
    <a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>
<%
}
%>
