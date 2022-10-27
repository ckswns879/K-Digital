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
	request.setCharacterEncoding("utf-8");	//h2콘솔 한글입력해도 정상출력

	String num = (String)request.getParameter("num");
	String title = (String)request.getParameter("title");
	String content = (String)request.getParameter("content");
	String id = (String)request.getParameter("id");

//	System.out.println(title +","+ content + ","+ id);

	// 2데이터베이스 연결 및 기타

	//드라이버 로드
	//Class.forName("com.mysql.cj.jdbc.Driver");
	Class.forName("org.h2.Driver");
	//데이터 베이스연결
// 	String dburl = "jdbc:mysql://localhost:3306/musthave";
// 	String dbid = "musthave";
// 	String dbpw = "tiger";	
	String dburl = "jdbc:h2:tcp://localhost/~/test";
	String dbid = "sa";
	String dbpw = "";	
	
	Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
	
	//PreparedStatment
	String sql = "insert into board(num,title,content,id)values(?,?,?,?)";
	PreparedStatement psmt = con.prepareStatement(sql);
	psmt.setString(1, num);
	psmt.setString(2, title);
	psmt.setString(3, content);
	psmt.setString(4, id);
	
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
	<a href="Boardinputform.jsp">입력창으로 돌아가기</a>
</body>
</html>