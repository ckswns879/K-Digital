<%@ page import ="java.sql.Connection" %>
<%@ page import ="java.sql.DriverManager" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.domain.MemberDTO" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%!
	List<MemberDTO> list = new ArrayList<>();
%>

<%

	// 2데이터베이스 연결 및 기타

	//드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver");

	//데이터 베이스연결
	String dburl = "jdbc:mysql://localhost:3306/musthave";
	String dbid = "musthave";
	String dbpw = "tiger";	
	Connection con = DriverManager.getConnection(dburl, dbid, dbpw);
	
	//Statement
	Statement st = con.createStatement();
	
	//query 실행
	ResultSet rs = st.executeQuery("select * from member order by id ASC limit 10");
//실행결과를 객체 리스트에 저장
	while(rs.next()){
		list.add(new MemberDTO(
			rs.getString("id"),
			rs.getString("pass"),
			rs.getString("name")
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
 	
 	for (MemberDTO b : list){
 		//out.println(b.toString() + "<br>");
 %>
 	<tr>
 		<td><%=b.getId() %></td>
 		<td><%=b.getPass() %></td>
 		<td><%=b.getName() %></td>

 	</tr>
 	
 	
 	
 	<%
 	}
 	%>
</table>
</body>
</html>