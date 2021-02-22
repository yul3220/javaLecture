<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	// 회원 목록 버튼 클릭 처리
	$("#memListBtn").on("click", function(){
		location.href = "<%=request.getContextPath()%>/member/memberList.ddit";
	})
	
	// 수정 버튼 클릭 처리
	$("#updateBtn").on("click", function(){
		var memId = $("#mem_id").val(); // 입력한 회원ID 읽어오기
		
		$.ajax({
			url : "<%=request.getContextPath()%>/member/memberUpdate.ddit",
			method : "post",
			data : {"mem_id" : memId},
			dataType : "json",
			success : function(result){
				alert("수정버튼클릭");
			},
			error : function(xhr){
				alert("오류 - 상태값 : " + xhr.status);
			}
		})
	})
	
	// 삭제 버튼 클릭 처리
});
</script>
</head>
<body>
<h2>회원 정보 상세 보기</h2>
<% 
	MemberVO memVo = (MemberVO)request.getAttribute("memberVo");
%>
<form id="memberForm" method="post">
	<input type="hidden" name="mem_id" value="<%=memVo.getMem_id() %>"/>
	<table border="1">
		<tr>
			<td>회원ID</td>
			<td><%=memVo.getMem_id() %></td>
		</tr>
		<tr>
			<td>회원이름</td>
			<td><%=memVo.getMem_name() %></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%=memVo.getMem_tel() %></td>
		</tr>
		<tr>
			<td>회원주소</td>
			<td><%=memVo.getMem_addr() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" id="updateBtn"/>
				<input type="button" value="삭제" id="deleteBtn"/>
				<input type="button" value="회원목록" id="memListBtn"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>