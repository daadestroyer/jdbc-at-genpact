import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.exceptions.RSAException;

public class PhotoInsertionProgram {
	public static void main(String[] args) {
		
		InputStream iStream = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcatgenpact", "root",
					"income@tax1998");

			PreparedStatement pstmt = con.prepareStatement("insert into phototable values (?,?)");
			pstmt.setString(1, "image 1");
//
//			InputStream inputStream = new FileInputStream("/Users/shubhamnigam/Desktop/3rd sem/RVCE/three.png");
//			pstmt.setBlob(2, inputStream);
//			pstmt.execute();
//
//			System.out.println("Image Inserted...");

			
			 
			 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
