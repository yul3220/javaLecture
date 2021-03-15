<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		// 회원 목록 버튼 클릭 처리
		$("#memListBtn").on("click", function(){
			location.href = "<%=request.getContextPath()%>/member/memberList.ddit";
		})
		
		var chkMemId = ""; // 중복체크할 떄의 ID가 저장될 변수
		// 중복 확인 버튼 클릭 처리
		$("#idCheck").on("click", function(){
			var memId = $("#mem_id").val(); // 입력한 회원ID 읽어오기
			if(memId == ""){
				alert("회원 ID를 입력하세요");
				$("mem_id").focus();
				return;
			}
			
			$.ajax({
				url : "<%=request.getContextPath()%>/member/memberIdcheck.ddit",
				method : "post",
				data : {"mem_id" : memId},
				dataType : "json",
				success : function(result){
					//alert(result);
					if(result=="OK"){
						$("#idChkResult").html("사용 가능 ID");
						chkMemId = memId;
					}else{
						$("#idChkResult").html("ID 중복 - 사용불가");
						chkMemId = "";
					}
				},
				error : function(xhr){
					alert("오류 - 상태값 : " + xhr.status);
				}
			})
		})
		
		// 저장 버튼 클릭 ==> form이 submit될때
		$("#memberForm").on("submit",function(){
		      var memId = $("#mem_id").val();
		      var idChk = $("#idChkResult").html().trim();
		      a
		      if(chkMemId!=memId || idChk != "사용가능 ID"){
		         alert("ID가 중복 되거나 변경되었습니다. 새로운 ID를 입력하세요 ");
		         $("#mem_id").focus();
		         return false; //서버로 전송을 취소한다. 
		      }
		      return true; //서버로 전송 작업을 수행한다. 
		   });
	})
</script>
</head>
<body>
<h2>회원 정보 입력 폼</h2>
<form id="memberForm" method="post" action="<%=request.getContextPath()%>/member/memberInsert.ddit">
	<table border="1">
		<tr>
			<td>회원ID</td>
			<td>
				<input type="text" name="mem_id" id="mem_id">
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