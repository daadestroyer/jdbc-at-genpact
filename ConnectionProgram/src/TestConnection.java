import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcatgenpact", "root",
					"income@tax1998");

			if (con != null) {
				System.out.println("Connection Successfull");
			} else {
				System.out.println("Connection Failed...");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Something wen wrong");
			e.printStackTrace();
		}
	}
}
