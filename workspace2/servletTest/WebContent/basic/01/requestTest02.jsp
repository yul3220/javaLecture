<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<h2>Request연습 Form(숫자 입력은 정수형으로 입력하세요)</h2>
<hr>
<form name="testForm2" method="post"
action = "<%= request.getContextPath() %>/RequestTest02.do">
	<table>
	 <tr>
	 	<td colspan="2">
	 		<input type="text" size="10" name="num1"/>
	 		<select name="operator">
				<option value="+">+</option>
				<option value="-">-</option>
				<option value="*">*</option>
				<option value="/">/</option>
				<option value="%">%</option>
			</select>
	 		<input type="text" size="10" name="num2"/>
	 		<input type="submit" value="확인"/>
	 	</td>
	 </tr>
	</table>
</form>
</body>
</html>