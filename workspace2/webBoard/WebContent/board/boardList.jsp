<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.service.BoardServiceImpl"%>
<%@page import="kr.or.ddit.service.IBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".detail").on("click", function(){
			var idx = $(this).attr("idx");
			location.href = "<%=request.getContextPath()%>/SelectBoard.do?no="+idx;
		})
		
		$("#addBtn").on("click", function(){
			location.href = "<%=request.getContextPath()%>/board/BoardForm.jsp";
		})
		
		$(".search").on("click", function(){
			var word = $(".word").val();
			location.href = "<%=request.getContextPath()%>/board/boardList.jsp?word="+word;
		})
	})
</script>
</head>
<body>
<h2>게시글 목록 보기</h2>
<div>
	<span>검색(제목) : </span>
	<input type="text" class="word"/>
	<img class="search" alt="" src="../image/search.png" height="30px" width="30px">
</div>
<br>
<input type="button" id="addBtn" value="게시글 추가"/>
<table border="1" class="table table-bordered">
	<thead>
		<tr>
			<th>No</th><th>제목</th><th>작성자</th><th>조회수</th><th></th>
		</tr>
	</thead>
	<tbody>
		<%
			IBoardService service = BoardServiceImpl.getService();
			String word = request.getParameter("word");
			List<BoardVO> list = service.AllBoard(word);
			if(list==null || list.size()==0){
		%>
			<tr><td colspan="4">게시글이 하나도 없습니다.</td></tr>
		<% 
			}else{
				for(BoardVO vo : list){
		%>
			<tr>
				<td><%=vo.getBoard_no()%></td>
				<td><%=vo.getBoard_title() %></td>
				<td><%=vo.getBoard_writer()%></td>
				<td><%=vo.getBoard_cnt() %></td>
				<td><input type="button" class="detail" idx="<%=vo.getBoard_no() %>" value="상세보기"/></td>
			</tr>
		<% 
				}
			}
		%>
		
	</tbody>
</table>
</body>
</html>