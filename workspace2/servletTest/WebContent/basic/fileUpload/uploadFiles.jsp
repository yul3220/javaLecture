<%@page import="kr.or.ddit.basic.fileupload.UploadDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드/다운로드 연습</title>
</head>
<body>
<h1><%=request.getAttribute("memId") %>님이 방금 업로드한 파일 목록들</h1>
<table border="1">
	<thead>
		<tr><th>파일이름</th><th>파일크기</th>
		<th>업로드상태</th><th>비고</th></tr>
	</thead>
	<tbody>
		<%
			List<UploadDetail> uploadList 
				= (List<UploadDetail>)request.getAttribute("uploadList");
			if(uploadList != null && uploadList.size()>0){
				for(UploadDetail detail : uploadList){
		%>
			<tr>
				<td><%=detail.getFileName() %></td>
				<td><%=detail.getFileSize() %></td>
				<td><%=detail.getUploadStatus() %></td>
				<td><a href="">DownLoad</a></td>
			</tr>
		<%
				}
			}
		%>
	</tbody>
</table>
<hr>
<a href="<%=request.getContextPath() %>/basic/fileUpload/fileUpload.jsp">파일 업로드 폼으로 가기</a>
</body>
</html>