<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 입력폼</title>
</head>
<body>
	<h2>게시판 입력폼</h2>
	<form action="InputBoardProcess.jsp" method="post">
	<table>
	<tr>		
		<td>num</td>
		<td><input type='text' name='num'></td>
	</tr>
	<tr>		
		<td>title</td>
		<td><input type='text' name='title'></td>
	</tr>
	<tr>		
		<td>content</td>
		<td><input type='text' name='content'></td>
	<tr>		
		<td>id</td>
		<td><input type='text' name='id'></td>
	<tr>
		<td colspan='2'><input type='submit' value='저장'>
	</tr>

	</table>
	</form>
</body>
</html>