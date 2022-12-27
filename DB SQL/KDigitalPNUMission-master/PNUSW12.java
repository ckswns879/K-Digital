package sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// SQL 관련 클래스는 java.sql .*에 포함되어 있다.
public class PNUSW12 {
	Connection con;
// 클래스 list를 선언한다. java.sql의 Connection 객체 con을 선언한다.  	
	public PNUSW12() {
		String Driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/pnusw12";
		String userid = "musthave";
		String pwd = "tiger";
// 접속변수를 초기화한다. url은 자바 드라이버 이름, 호스트명(localhost), 포트번호를 입력한다
// userid는 관리자, pwd는 사용자의 비밀번호를 입력한다.    
		try { /* 드라이버를 찾는 과정 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
// Class.forName()으로 드라이버를 로딩한다. 드라이버 이름을 Class.forName에 입력한다.      
		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 접속 객체 con을 DriverManager.getConnection 함수로 생성한다. 
// 접속이 성공하면 "데이터베이스 연결 성공"을 출력하도록 한다.  
// 문자열 query에 수행할 SQL 문을 입력한다.
	private void selectsqlRun() {//ㅇ
		String query = "SELECT * FROM 학생"; /* SQL 문 */
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("select [학생번호]  [이름] [주소] [학과]");
			while (rs.next()) {
				System.out.print("\t" + rs.getString(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t" + rs.getString(3));
				System.out.print("\t" + rs.getString(4));
				System.out.println();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void gradeselectsqlRun() {//ㅇ
		String query = "SELECT * FROM 성적"; /* SQL 문 */
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("select [학생번호]  [강좌이름] [학점]");
			while (rs.next()) {
				System.out.print("\t" + rs.getString(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t" + rs.getString(3));

				System.out.println();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertsqlRun() {
		Scanner scan = new Scanner(System.in);
		System.out.print("추가할 새학과를 입력하세요 : ");
		String ncs = scan.next();
		System.out.print("추가할 새사무실을 입력하세요 : ");
		String csname = scan.next();

		try {
		CallableStatement css = con.prepareCall("{ call insert학과(?, ?)}");
		System.out.println(" insert ");
		css.setString(1, ncs);
		css.setString(2, csname);
		css.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("추가할 학생번호를 입력하세요 : ");
		String stcode = scan.next();
		System.out.print("추가할 학생이름을 입력하세요 : ");
		String stname = scan.next();
		System.out.print("추가할 주소를 입력하세요 : ");
		String ad = scan.next();


		try {
		CallableStatement css = con.prepareCall("{ call insert학생(?, ?, ?, ?)}");
		System.out.println(" insert ");
		css.setString(1, stcode);
		css.setString(2, stname);
		css.setString(3, ad);
		css.setString(4, ncs);
		css.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deletesqlRun() {//ㅇ
	      Scanner d = new Scanner(System.in);
	      System.out.print("삭제할 데이터의 학생번호를 입력하세요");
	      String deleteCode = d.next();
		try {
			CallableStatement cs = con.prepareCall("{ call delete학생번호(?)}");
			cs.setString(1, deleteCode);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void updatesqlRun() {
	      Scanner s = new Scanner(System.in);
	      System.out.println("변경할 학생번호를 입력하세요");
	      int updateCode = s.nextInt();
	      System.out.println("변경할 학생이름을 입력하세요");
	      String updateName = s.next();
	      System.out.println("변경할 주소를 입력하세요");
	      String updatepnn = s.next();
	      System.out.println("변경할 학과를 입력하세요");
	      String updatecss = s.next();
	      

	      try {
	    	  CallableStatement cs = con.prepareCall("{ call update학생(?, ?, ?, ?)}");
	    	  	cs.setInt(1, updateCode);
				cs.setString(2, updateName);
				cs.setString(3, updatepnn);
				cs.setString(4, updatecss);
				cs.executeUpdate();
	    	  
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		int choice;
		Scanner in = new Scanner(System.in);
		PNUSW12 sc = new PNUSW12();
		while (true) {
			System.out.println("MySQL java crud operation");
			System.out.println("1 insert");
			System.out.println("2 update");
			System.out.println("3 delete");
			System.out.println("4 select");
			System.out.println("5 grade select");
			System.out.println("6 exit");
			System.out.println(" enter a choice: ");
			choice = in.nextInt();
			System.out.println("-------------------------------------------");

			switch (choice) {

			case 1:
				sc.insertsqlRun();
				break;
			case 2:
				sc.updatesqlRun();
				break;
			case 3:
				sc.deletesqlRun();
				break;
			case 4:
				sc.selectsqlRun();
				break;
			case 5:
				sc.gradeselectsqlRun();
				break;
			case 6:
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
				break;
			default:
				System.out.println("잘못된 선택입니다");
				break;
			}
			System.out.println("---------------------------------------");
		}
	}
}