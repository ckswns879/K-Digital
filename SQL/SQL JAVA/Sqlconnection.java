package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// SQL 관련 클래스는 java.sql .*에 포함되어 있다.
public class Sqlconnection {
	Connection con;
// 클래스 list를 선언한다. java.sql의 Connection 객체 con을 선언한다.  	
	public Sqlconnection() {
		String Driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mydb";
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
	private void selectsqlRun() {
		String query = "SELECT * FROM 학과"; /* SQL 문 */
		try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("select:[코드]    [학과명]    [전화번호]");
			while (rs.next()) {
				System.out.print("\t" + rs.getInt(1));
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
		System.out.print("추가할 학과 코드를 입력하세요 : ");
		int code = scan.nextInt();
		System.out.print("추가할 학과 명을 입력하세요 : ");
		String name = scan.next();
		System.out.print("추가할 전화번호를 입력하세요 : ");
		String pn = scan.next();
		String query = " insert into 학과(학과코드, 학과명, 전화번호) " + " values (?, ?, ?)";
		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			System.out.println(" insert ");
			preparedStmt.setInt(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, pn);
			preparedStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deletesqlRun() {
	      Scanner d = new Scanner(System.in);
	      System.out.print("삭제할 데이터의 학과코드를 입력하세요");
	      int deleteCode = d.nextInt();
		String query = "delete from 학과 where 학과코드 = ?";
		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, deleteCode);
			preparedStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void updatesqlRun() {
	      Scanner s = new Scanner(System.in);
	      System.out.print("변경할 학과 코드를 입력하세요");
	      int updateCode = s.nextInt();
	      System.out.println("변경할 학과명을 입력하세요");
	      String updateName = s.next();
	      System.out.println("변경할 전화번호를 입력하세요");
	      String pnn = s.next();
		String query = " update 학과 set 학과명 = ? , 전화번호 = ? where 학과코드 = ?";
		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(3, updateCode);
			preparedStmt.setString(1, updateName);
			preparedStmt.setString(2, pnn);
			preparedStmt.executeUpdate();
			System.out.println(" update ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

//		sc.selectsqlRun();
//		sc.insertsqlRun();	
//		sc.deletesqlRun();
//		sc.updatesqlRun();
//		sc.selectsqlRun();

		int choice;
		Scanner in = new Scanner(System.in);
		Sqlconnection sc = new Sqlconnection();
		while (true) {
			System.out.println("MySQL java crud operation");
			System.out.println("1 insert");
			System.out.println("2 update");
			System.out.println("3 delete");
			System.out.println("4 select");
			System.out.println("5 exit");
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