
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUD {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcatgenpact", "root",
					"income@tax1998");

			Statement stmt = con.createStatement();

//			if (con != null) {
//				System.out.println("Connection Successfull");
//			} else {
//				System.out.println("Connection Failed...");
//			}

			// inserting data
			/*
			 * int executeUpdate = stmt.
			 * executeUpdate("insert into Employee values (104 , 'Shubhanshu' , '60000' , 'Kanpur')"
			 * ); if(executeUpdate > 0) { System.out.println("Data Inserted..."); }
			 */

			// fetching data

			ResultSet rs = stmt.executeQuery("select * from Employee");
			while (rs.next()) {
				// Retrieve by column name
				System.out.println("Emp Id : " + rs.getInt("empid"));
				System.out.println("Emp Name : " + rs.getString("empname"));
				System.out.println("Emp Salary : " + rs.getInt("empsal"));
				System.out.println("Emp Address : " + rs.getString("empaddr"));
				System.out.println();
			}

			// Deletion data
			
//			int executeUpdate = stmt.executeUpdate("delete from Employee where empid = 104");
//			if(executeUpdate > 0) {
//				System.out.println("Data Deleted...");
//			}
//			else {
//				System.out.println("Data not found");
//			}

			// Updating Data
			
//			int executeUpdate = stmt.executeUpdate("update Employee set empsal = empsal+500 where empaddr = 'Kanpur' ");
//			if (executeUpdate > 0) {
//				System.out.println("Data Updated...");
//			} else {
//				System.out.println("No record found...");
//			}
			
			
	
			
		}

		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}
}
