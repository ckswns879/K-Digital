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
	request.setCharacterEncoding("utf-8");	//h2�ܼ� �ѱ��Է��ص� �������

	String num = (String)request.getParameter("num");
	String title = (String)request.getParameter("title");
	String content = (String)request.getParameter("content");
	String id = (String)request.getParameter("id");

//	System.out.println(title +","+ content + ","+ id);

	// 2�����ͺ��̽� ���� �� ��Ÿ

	//����̹� �ε�
	//Class.forName("com.mysql.cj.jdbc.Driver");
	Class.forName("org.h2.Driver");
	//������ ���̽�����
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
	<a href="Boardinputform.jsp">�Է�â���� ���ư���</a>
</body>
</html>