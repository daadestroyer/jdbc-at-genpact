
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
Prepare quiz application user should enter answers and that is stored in admin table
-- 
--
--
-- 
-----------
     	
     
*/
public class Quiz {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genpactquiz", "root", "income@tax1998");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		while (true) {
			System.out.println();
			System.out.println("---------------------------");
			System.out.println("WELCOME TO QUIZ APPLICATION");
			System.out.println("---------------------------");

			System.out.println("1. Press 1 to login as Admin");
			System.out.println("2. Press 2 to login as User");

			int loginChoice = sc.nextInt();

			if (loginChoice == 1) {
				System.out.println("Enter Admin Id");
				String adminId = sc.next();

				System.out.println("Enter Admin Password");
				String adminPassword = sc.next();

				if (adminId.equals("101@shubham") && adminPassword.equals("shubham")) {
					System.out.println();
					System.out.println("---------------------------------------");
					System.out.println("******Admin Logged In Sucessfully******");
					System.out.println("---------------------------------------");
					System.out.println();

					while (true) {
						System.out.println("1. Press 1 to see all quiz questions and answers");
						System.out.println("2. Press 2 to enter some quiz questions");
						System.out.println("3. Press 3 to delete quiz questions");
						System.out.println("4. Press 4 to update quiz questions");
						System.out.println("5. Press 5 to view all user details");
						System.out.println("6. exit");
						int ch = sc.nextInt();

						if (ch == 1) {
							try {
								pstmt = con.prepareStatement("select * from quizTable");
								rs = pstmt.executeQuery();

								while (rs.next()) {
									System.out.println("------------------------------------------");
									System.out.println("Ques Id : " + rs.getInt("quesId"));
									System.out.println("Ques Name : " + rs.getString("quesName"));
									System.out.println("Ques Answer1 : " + rs.getString("quesAnswer1"));
									System.out.println("Ques Answer2 : " + rs.getString("quesAnswer2"));
									System.out.println("Ques Answer3 : " + rs.getString("quesAnswer3"));
									System.out.println("Correct Answer : " + rs.getString("correctAnswer"));
									System.out.println("-------------------------------------------");
									System.out.println();
								}

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else if (ch == 2) {
							System.out.println("Enter question id to check it is already available or not");
							int quesId = sc.nextInt();

							try {
								pstmt = con.prepareStatement("select * from quizTable where quesId = ? ");

								pstmt.setInt(1, quesId);
								rs = pstmt.executeQuery();

								if (rs.next()) {
									System.out.println();
									System.out.println("----------------------------------------------");
									System.out.println("Question with this id " + quesId + " already exist");
									System.out.println("-----------------------------------------------");
									System.out.println();
								} else {

									pstmt = con.prepareStatement("insert into quizTable values (?,?,?,?,?,?)");

									System.out.println("Enter question Name");
									String quesName = sc.next();

									System.out.println("Enter 1st option");
									String quesAnswer1 = sc.next();

									System.out.println("Enter 2nd option");
									String quesAnswer2 = sc.next();

									System.out.println("Enter 3rd option");
									String quesAnswer3 = sc.next();

									System.out.println("Enter correct answer");
									String correctAnswer = sc.next();

									pstmt.setInt(1, quesId);
									pstmt.setString(2, quesName);
									pstmt.setString(3, quesAnswer1);
									pstmt.setString(4, quesAnswer2);
									pstmt.setString(5, quesAnswer3);
									pstmt.setString(6, correctAnswer);

									int executeUpdate = pstmt.executeUpdate();
									if (executeUpdate > 0) {
										System.out.println();
										System.out.println("----------------");
										System.out.println("Data inserted...");
										System.out.println("----------------");
										System.out.println();
									} else {
										System.out.println("Data not inserted...");
									}
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (ch == 3) {
							System.out.println("Enter question id to delete");
							int quesId = sc.nextInt();

							try {
								pstmt = con.prepareStatement("delete from quizTable where quesId = ? ");
								pstmt.setInt(1, quesId);
								int executeUpdate = pstmt.executeUpdate();
								if (executeUpdate > 0) {
									System.out.println("Data deleted...");
								} else {
									System.out.println("Ques Id not found...");
								}

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else if (ch == 4) {
							System.out.println("Enter question id to update");
							int quesId = sc.nextInt();

							try {
								pstmt = con.prepareStatement("select * from quizTable where quesId = ? ");
								pstmt.setInt(1, quesId);

								rs = pstmt.executeQuery();

								if (rs.next() == false) {
									System.out.println();
									System.out.println("---------------");
									System.out.println("No record found");
									System.out.println("---------------");
									System.out.println();
								} else {
									System.out.println("Record Found");
									System.out.println("Enter question Name");
									String quesName = sc.next();

									System.out.println("Enter option 1");
									String quesAnswer1 = sc.next();

									System.out.println("Enter option 2");
									String quesAnswer2 = sc.next();

									System.out.println("Enter option 3");
									String quesAnswer3 = sc.next();

									System.out.println("Enter correct answer");
									String correctAnswer = sc.next();

									pstmt = con.prepareStatement(
											"update quizTable set quesName = ? , quesAnswer1 = ? , quesAnswer2 = ? , quesAnswer3 = ? , correctAnswer  = ?  where quesId = ?");

									pstmt.setString(1, quesName);
									pstmt.setString(2, quesAnswer1);
									pstmt.setString(3, quesAnswer2);
									pstmt.setString(4, quesAnswer3);
									pstmt.setString(5, correctAnswer);
									pstmt.setInt(6, quesId);

									int executeUpdate = pstmt.executeUpdate();
									if (executeUpdate > 0) {
										System.out.println();
										System.out.println("------------------");
										System.out.println("Records Updated...");
										System.out.println("------------------");
										System.out.println();
									} else {
										System.out.println("Problem Occured while updating records");
									}
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else if (ch == 5) {
							try {
								pstmt = con.prepareStatement("select * from userTable");
								rs = pstmt.executeQuery();

								while (rs.next()) {
									System.out.println("------------------------------------------");
									System.out.println("userId : " + rs.getString("userId"));
									System.out.println("userName : " + rs.getString("userName"));
									System.out.println("userPassword : " + rs.getString("userPassword"));
									System.out.println("userScore : " + rs.getInt("userScore"));
									System.out.println("-------------------------------------------");
									System.out.println();
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else if (ch == 6) {
							System.out.println("Exiting...");
							break;
						} else {
							System.out.println("Invalid Choice...");
						}
					}
				} else {
					System.out.println("Invalid Admin Id/Password");
				}
			} else if (loginChoice == 2) {
				int marks = 0;
				System.out.println("Enter User Id");
				String userId = sc.next();

				System.out.println("Enter User Password");
				String userPassword = sc.next();

				try {
					pstmt = con.prepareStatement("select * from userTable where userid = ? and userPassword = ? ");
					pstmt.setString(1, userId);
					pstmt.setString(2, userPassword);

					rs = pstmt.executeQuery();
					if (rs.next() == true) {
						// user found
						System.out.println();
						System.out.println("-----------------------------");
						System.out.println("User Logged in sucessfully...");
						System.out.println("-----------------------------");
						System.out.println();
						pstmt = con.prepareStatement("select * from quizTable");
						rs = pstmt.executeQuery();
						while (rs.next()) {

							String quesName = rs.getString("quesName");
							String quesAnswer1 = rs.getString("quesAnswer1");
							String quesAnswer2 = rs.getString("quesAnswer2");
							String quesAnswer3 = rs.getString("quesAnswer3");
							String correctAnswer = rs.getString("correctAnswer");

							System.out.println("Question : " + quesName);
							System.out.println("--------------------");
							System.out.println("Option 1 : " + quesAnswer1);
							System.out.println("Option 2 : " + quesAnswer2);
							System.out.println("Option 3 : " + quesAnswer3);

							System.out.println("Enter you answer");

							String userAnswer = sc.next().trim();

							if (userAnswer.equalsIgnoreCase(correctAnswer)) {
								System.out.println("Correct");
								marks = marks + 1;
							} 
							
							System.out.println("Your Marks : "+marks);
						}
						pstmt = con.prepareStatement("update userTable set userScore = ? where userId = ? ");
						pstmt.setInt(1, marks);
						pstmt.setString(2, userId);
						int executeUpdate = pstmt.executeUpdate();
						if (executeUpdate > 0) {
							System.out.println("Marks Assigned");
						}
					} else {
						// user not found
						System.out.println();
						System.out.println("---------------------------------------");
						System.out.println("User not found please register first...");
						System.out.println("---------------------------------------");
						System.out.println();

						System.out.println("Ente userID");
						String newUserId = sc.next();

						System.out.println("Enter userName ");
						String newUserName = sc.next();

						System.out.println("Enter userPassword");
						String newUserPassword = sc.next();

						pstmt = con.prepareStatement("insert into userTable values (?,?,?,?) ");
						pstmt.setString(1, newUserId);
						pstmt.setString(2, newUserName);
						pstmt.setString(3, newUserPassword);
						pstmt.setNull(4, Types.NULL);
						int executeUpdate = pstmt.executeUpdate();
						if (executeUpdate > 0) {
							System.out.println("Records Inserted...");
						} else {
							System.out.println("Something went wrong while inserting records....");
						}

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("Invalid Input Enter");
			}
		}
	}
}
