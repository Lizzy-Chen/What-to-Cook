package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DBManager {
	public static Connection conn;
	
	// location of database
	private static String dbUrl = "jdbc:mysql://localhost:3306/foodmaterial?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	
	// username of database
	private static String dbUserName = "root";
	
	// password of database
	private static String dbPassword = "Lizzy408106";
	
	// database driver
	private static String jdbcName = "com.mysql.cj.jdbc.Driver";

	static {
		try {
			// Load the database driver class
			Class.forName(jdbcName);
			
			// Return the database connection object
			conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	
	
}
