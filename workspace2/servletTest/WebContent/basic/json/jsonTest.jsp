<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON데이터 처리하기</title>
<script type="text/javascript" 
src = "//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(function(){
	// 문자열
	$("#strBtn").on("click", function(){
		$.ajax({
			"url" : "<%=request.getContextPath()%>/JSONServlet.do",
			"type" : "post",
			"data" : "choice=str",
			"success" : function(data){
				$("#result").empty(); // DIV태그 안의 내용 삭제
				$("#result").append(data);
				$("#result").append("<hr color='red'>")
			},
			"dataType" : "json"
		});
	})
	
	// 배열
	$("#arrayBtn").on("click", function(){
		$.ajax({
			"url" : "<%=request.getContextPath()%>/JSONServlet.do",
			"type" : "post",
			"data" : "choice=array",
			"success" : function(data){
				$("#result").empty();
				$.each(data, function(i,v){
					$("#result").append(i + "번째 자료 : " + v + "<br>")
				})
				$("#result").append("<hr color='red'>");				
			},
			"dataType" : "json"
		});
	})
	
	// 객체
	$("#objBtn").on("click", function(){
		$.ajax({
			"url" : "<%=request.getContextPath()%>/JSONServlet.do",
			"type" : "post",
			"data" : "choice=obj",
			"success" : function(data){
				$("#result").empty();
				//data가 samvo라고 생각하면 됨
				$("#result").append("ID : " + data.id + "<br>");
				$("#result").append("NAME : " + data.name + "<br>");
				$("#result").append("<hr color='red'>");
			},
			"dataType" : "json"
		});
	})
	
	// 리스트
	$("#listBtn").on("click", function(){
		$.ajax({
			"url" : "<%=request.getContextPath()%>/JSONServlet.do",
			"type" : "post",
			"data" : "choice=list",
			"success" : function(data){
				//jsonData : [{"id":100,"name":"강감찬"},{"id":200,"name":"이순신"},{"id":300,"name":"일지매"}]
				$("#result").empty();
				$.each(data, function(i,v){
					$("#result").append("ID : " + v.id + "<br>");
					$("#result").append("NAME : " + v.name + "<hr>");
				});
				$("#result").append("<hr color='red'>");
			},
			"dataType" : "json"
		});
	})
	
	// map객체
	$("#mapBtn").on("click", function(){
		$.ajax({
			"url" : "<%=request.getContextPath()%>/JSONServlet.do",
			"type" : "post",
			"data" : "choice=map",
			"success" : function(data){
				$("#result").empty();
				// 방법1
				/*$("#result").append("NAME : " + data.name + "<br>");
				$("#result").append("TEL : " + data.tel + "<br>");
				$("#result").append("ADDR : " + data.addr + "<br>");*/
				
				// 방법2
				$.each(data, function(key, value){
					$("#result").append(key + " : " + value + "<br>")
				})
				$("#result").append("<hr color='red'>");
			},
			"dataType" : "json"
		});
	})
});
</script>
</head>
<body>
<form>
	<input type="button" id="strBtn" value="문자열">
	<input type="button" id="arrayBtn" value="배 열">
	<input type="button" id="objBtn" value="객 체">
	<input type="button" id="listBtn" value="리스트">
	<input type="button" id="mapBtn" value="Map객체">
</form>
<br>
<div id="result"></div>
</body>
</html>