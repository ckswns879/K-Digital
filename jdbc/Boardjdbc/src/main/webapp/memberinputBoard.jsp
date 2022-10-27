<%@ page import ="java.sql.Connection" %>
<%@ page import ="java.sql.DriverManager" %>
<%@ page import ="java.sql.PreparedStatement" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 1 파라미터 읽기 (title, content, id-->
<%
	String id = (String)request.getParameter("id");
	String pass = (String)request.getParameter("pass");
	String name = (String)request.getParameter("name");

//	System.out.println(title +","+ content + ","+ id);

	// 2데이터베이스 연결 및 기타

	//드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver");

	//데이터 베이스연결
	String dburl = "jdbc:mysql://localhost:3306/musthave";
	String dbid = "musthave";
	String dbpw = "tiger";	
	Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
	
	//PreparedStatment
	String sql = "insert into member(id,pass,name)values(?,?,?)";
	PreparedStatement psmt = con.prepareStatement(sql);
	psmt.setString(1, id);
	psmt.setString(2, pass);
	psmt.setString(3, name);
	
	//query 실행
	// 3 데이터베이스 입력
	int result = psmt.executeUpdate();

 	//4 완료 메시지 출력
 	psmt.close();
 	con.close();
 	
 	if (result == 1)
 		out.println("입력성공");
 	else
 		out.println("입력실패");
%>
	<a href="memberinputform.jsp">입력창으로 돌아가기</a>
</body>
</html>