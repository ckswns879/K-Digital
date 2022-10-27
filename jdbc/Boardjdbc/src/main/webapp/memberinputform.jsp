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
	<form action="memberinputBoard.jsp" method="post">
	<table>
	<tr>		
		<td>id</td>
		<td><input type='text' name='id'></td>
	</tr>
	<tr>		
		<td>pass</td>
		<td><input type='text' name='pass'></td>
	<tr>		
		<td>name</td>
		<td><input type='text' name='name'></td>
	<tr>
		<td colspan='2'><input type='submit' value='저장'>
	</tr>

	</table>
	</form>
</body>
</html>