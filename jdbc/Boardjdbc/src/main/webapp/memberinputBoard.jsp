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
<!-- 1 �Ķ���� �б� (title, content, id-->
<%
	String id = (String)request.getParameter("id");
	String pass = (String)request.getParameter("pass");
	String name = (String)request.getParameter("name");

//	System.out.println(title +","+ content + ","+ id);

	// 2�����ͺ��̽� ���� �� ��Ÿ

	//����̹� �ε�
	Class.forName("com.mysql.cj.jdbc.Driver");

	//������ ���̽�����
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
	
	//query ����
	// 3 �����ͺ��̽� �Է�
	int result = psmt.executeUpdate();

 	//4 �Ϸ� �޽��� ���
 	psmt.close();
 	con.close();
 	
 	if (result == 1)
 		out.println("�Է¼���");
 	else
 		out.println("�Է½���");
%>
	<a href="memberinputform.jsp">�Է�â���� ���ư���</a>
</body>
</html>