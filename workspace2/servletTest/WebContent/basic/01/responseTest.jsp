<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>forward, sendRedirect연습</h2>
<hr>

<form method="post" action="<%= request.getContextPath()%>/responseTest01.do">
	forward action : <input type="text" name="username"/>
	<input type="submit" value="확인"/>
</form>

<form method="post" action="<%= request.getContextPath()%>/responseTest02.do">
	response.sendRedirect : <input type="text" name="username"/>
	<input type="submit" value="확인"/>
</form>

</body>
</html>