<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function() {
	$("button[type=submit]").on("click", function(){
		var title = $(".form-group input[name=board_title]").val();
		if(title == ""){
			alert("제목을 입력하세요.");
			return false;
		}
		
		var writer = $(".form-group input[name=board_writer]").val();
		if(writer == ""){
			alert("작성자를 입력하세요.");
			return false;
		}
		
		var content = $(".form-group textarea[name=board_content]").val();
		if(content == ""){
			alert("내용을 입력하세요.");
			return false;
		}
	})
})
</script>
</head>
<body>
<div class="container">
  <h2>게시물 추가</h2>
  <form action="<%=request.getContextPath()%>/InsertBoard.do" method="post">
    <div class="form-group">
      <label>제목 :</label>
      <input type="text" class="form-control" name="board_title">
    </div>
    <div class="form-group">
      <label>작성자:</label>
      <input type="text" class="form-control" name="board_writer">
    </div>
   <div class="form-group">
      <label>내용</label><br>
      <textarea rows="10" cols="30" name="board_content"></textarea>
    </div>
    <button type="submit" class="btn btn-default">등록</button>
  </form>
</div>
</body>
</html>