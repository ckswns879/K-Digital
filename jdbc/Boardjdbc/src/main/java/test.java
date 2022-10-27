import java.sql.Connection;
import java.sql.DriverManager;

public class test {

	public static void main(String[] args) {
		Class.forName("com.mysql.cj.jdbc.Driver");

		//데이터 베이스연결
		//Connection con = DriverManager.getConnection(dburl, dbid, dbpw);

	}

}
