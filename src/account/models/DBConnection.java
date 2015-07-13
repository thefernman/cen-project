package account.models;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBConnection {
	
	static String connectionURL = "jdbc:mysql://localhost/familytree";
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet rs = null;
	static String dbUsername = "root"; // Database username
	static String dbPassword = "softwaretesting"; // Database password

	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(" Unable to load driver. ");
		}
		try {
			connection = (Connection) DriverManager.getConnection(
					connectionURL, dbUsername, dbPassword);
			System.out.println(" Connection Established. ");
		}
		catch(Exception e)
		{
			System.out.println(" Error connecting to database from DBConnection constructor:  " + e);
		}
	}
	
	public ResultSet executeQuery(String query)
	{
		try
		{
			PreparedStatement st = (PreparedStatement) connection
					.prepareStatement(query);
			rs = st.executeQuery();
//			closeConnection();
		}
		catch(Exception e)
		{
			System.out.println(" Error connecting to database from DBConnection executeQuery:  " + e);
//			closeConnection();
		}
		return rs;
	}
	
	public void executeUpdate(String query)
	{
		try
		{
			statement = (PreparedStatement) connection
					.prepareStatement(query);
			statement.executeUpdate(query);
//			closeConnection();
		}
		catch(Exception e)
		{
			System.out.println(" Error updating to database from DBConnection executeUpdate:  " + e);
//			closeConnection();
		}
	}
	
	public void closeConnection()
	{
		try
		{
			connection.close();
		} 
		catch (SQLException e)
		{
			System.out.println(" Error closing to database:  " + e);
		}
	}
	
}
