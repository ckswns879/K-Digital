<%@ page import ="java.sql.Connection" %>
<%@ page import ="java.sql.DriverManager" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.domain.BoardDTO" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%!
	List<BoardDTO> list = new ArrayList<>();
%>

<%

	// 2데이터베이스 연결 및 기타

	//드라이버 로드
// 	Class.forName("com.mysql.cj.jdbc.Driver");
	Class.forName("org.h2.Driver");   
	
	
	//데이터 베이스연결
// 	String dburl = "jdbc:mysql://localhost:3306/musthave";
// 	String dbid = "musthave";
// 	String dbpw = "tiger";	

	String dburl = "jdbc:h2:tcp://localhost/~/test";
	String dbid = "sa";
	String dbpw = "";	
	Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
	
	//Statement
	Statement st = con.createStatement();
	
	//query 실행
	ResultSet rs = st.executeQuery("select * from board order by num ASC limit 10");//10번까지보여줌
//실행결과를 객체 리스트에 저장
while(rs.next()){
	list.add(new BoardDTO(
			rs.getInt("num"),
			rs.getString("title"),
			rs.getString("content"),
			rs.getString("id"),
			rs.getString("postdate"),
			rs.getInt("visitcount")
			));
}
	
	
	// 3 데이터베이스 입력
	

 	//4 완료 메시지 출력
 	rs.close();
	st.close();
 	con.close();
 	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table border="1">
 <%
 	
 	for (BoardDTO b : list){
 		//out.println(b.toString() + "<br>");
 %>
 	<tr>
 		<td><%=b.getNum() %></td>
 		<td><%=b.getTitle() %></td>
 		<td><%=b.getContent() %></td>
 		<td><%=b.getId() %></td>
 		<td><%=b.getPostdate() %></td>
 		<td><%=b.getVisitcount() %></td>
 	</tr>
 	
 	
 	
 	<%
 	}
 	%>
</table>
</body>
</html>