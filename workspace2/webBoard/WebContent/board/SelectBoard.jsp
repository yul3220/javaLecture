<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board 상세 리스트</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".modify").on("click", function(){
			var idx = $(".no").text().substring(0,1);
			location.href = "<%=request.getContextPath()%>/board/ModifyForm.jsp?no="+idx;
		})
		
		$(".delete").on("click", function(){
			var idx = $(".no").text().substring(0,1);
			$.ajax({
				url : "/webBoard/DeleteBoard.do",
				type : "post",
				data : {"no" : idx},
				dataType : "json",
				success : function(res){
					if(res.sw=="성공"){
						location.href = "<%=request.getContextPath()%>/board/boardList.jsp";
					}
				},
				error : function(xhr){
					alert("상태 : " + xhr.status)
				}
			})
		})
		
		$(".golist").on("click", function(){
			location.href = "<%=request.getContextPath()%>/board/boardList.jsp";
		})
	})
</script>
</head>
<body>
<%
	BoardVO vo = (BoardVO) request.getAttribute("boardvo");
	String content = vo.getBoard_content();
%>
<h3 class="no"><%=vo.getBoard_no() %>번글 내용</h3>
<table border="1" class="table">
	<tr><td>제목 : <%=vo.getBoard_title() %></td></tr>
	<tr><td>작성자 : <%=vo.getBoard_writer() %></td></tr>
	<tr><td>내용 : <%=content%></td></tr>
	<tr><td>작성일 : <%=vo.getBoard_date() %></td></tr>
	<tr><td>조회수 : <%=vo.getBoard_cnt()+1%></td></tr>
</table>
<input type="button" class="modify" value="수정"/>
<input type="button" class="delete" value="삭제"/>
<input type="button" class="golist" value="리스트로 가기"/>
</body>
</html>