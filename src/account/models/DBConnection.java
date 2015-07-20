package account.models;

//import java.sql.ResultSet;
import account.models.ResultSet;

public class DBConnection {
	
	private static DBConnection instance;
	static ResultSet db;
	Boolean testBool;
	String username;
	String password;
	
	protected DBConnection() {
		// Exists only to defeat instantiation
	}

	public void addUser(User user) {
		db.addUser(user);
	}

	public static DBConnection getInstance(ResultSet rs) {
		if (instance == null) {
			instance = new DBConnection();
		}
		db = rs;
		return instance;
	}
	
	public ResultSet executeQuery(String query)
	{
		return db;
	}
	
	public void executeUpdate(String query)
	{
		System.out.print("Ran update");
	}
	
	public void setPassword(String s) {
		db.setPassword(s);
	}

}
