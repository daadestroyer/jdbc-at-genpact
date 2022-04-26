import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App2 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcatgenpact", "root",
					"income@tax1998");

			Statement stmt = con.createStatement();
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
