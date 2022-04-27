import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
	public static void main(String[] args) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		int val = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genpactquiz", "root", "income@tax1998");

			System.out.println("details of employee working in QA Department");

			pstmt = con.prepareStatement(
					"select count(*) from emp_info left join dept_info on emp_info.deptid = dept_info.deptid where dept_info.deptname='QA' ");

			rs = pstmt.executeQuery();
			while (rs.next())
				val = rs.getInt(1);

			System.out.println(val);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
