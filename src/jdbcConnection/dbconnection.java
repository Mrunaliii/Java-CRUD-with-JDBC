package jdbcConnection;
import java.sql.*;
public class dbconnection {
	
/*
Step 1 – Import the Packages
Step 2 – Load the drivers using the forName() method 
Step 3 – Register the drivers using DriverManager 
Step 4 – Establish a connection using the Connection class object
Step 5 – Create a statement
Step 6 – Execute the query
Step 7 – Close the connections
 */
	
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/carsdb";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "21112000@Mru";
	
	static Connection con = null;
	
	public static Connection getConnect() {
		try {
			
			//Load Class driver
			Class.forName(JDBC_DRIVER);
			System.out.println("Class Loaded Succesfully..");
			
			//Register the driver
			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			System.out.println("Connection Established");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getConnect();
	}

}
