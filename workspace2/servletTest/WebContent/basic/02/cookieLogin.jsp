<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookie연습용 Login폼</title>
</head>
<body>
	<!-- 체크박스가 체크된 상태로 로그인 하면 쿠키에 로그인할 때 사용한 'id'를 저장한다.
		(쿠키변수 : userId)   -->
	<%
		// 쿠키에 저장된 userId 정보 가져오기
		String cookieUserId = ""; // id값 저장할 변수
		String chk = ""; // 체크박스의 체크 여부를 나타내는 'checked'속성값을 저장할 변수
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null && cookies.length>0){
			for(int i=0; i<cookies.length; i++){
				if(cookies[i].getName().equals("userId")){ // 원하는 쿠키변수가 있는지 검사
					cookieUserId = cookies[i].getValue(); // 쿠키값(사용자ID)
					chk = "checked";
				}
			}
		}
	%>
	<form method="post" action="<%=request.getContextPath()%>/cookieLoginServlet.do">
		<table>
			<tr>
				<td> ID : </td>
				<td><input name="userid" value="<%=cookieUserId %>" type="text" placeholder="ID를 입력하세요."></td>
			</tr>
			
			<tr>
				<td> PASS : </td>
				<td><input name="pass" type="password" placeholder="Password를 입력하세요."></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="checkbox" name="chkid" value="check" <%=chk %>>ID 기억하기</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
</body>
</html>