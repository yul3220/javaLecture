<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="memberForm" method="post" action="<%=request.getContextPath()%>/member/memberInsert.ddit">
	<table border="1">
		<tr>
			<td>회원ID</td>
			<td>
				<input type="text" name="mem_id" id="mem_id"/>
				<input type="button" value="중복확인" id="idCheck"/><br>
				<span id="idChkResult"></span>
			</td>
		</tr>
		<tr>
			<td>회원이름</td>
			<td><input type="text" name="mem_name" id="mem_name"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="mem_tel" id="mem_tel"></td>
		</tr>
		<tr>
			<td>회원주소</td>
			<td><input type="text" name="mem_addr" id="mem_addr"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장"/>
				<input type="reset" value="취소"/>
				<input type="button" value="회원목록" id="memListBtn"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>