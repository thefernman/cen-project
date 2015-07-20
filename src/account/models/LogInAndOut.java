package account.models;

//import java.sql.ResultSet;
import account.models.ResultSet;

public class LogInAndOut {

	public static boolean doLogin(String username, String password) {

		// To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established. LogInandLogOut doLogin");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from LogInandLogOut doLogin:  " + e);
		}

		ResultSet rs = null;
		try {
			// After this, create your own logic
			String query = "SELECT username, password FROM Users WHERE username = '" + username + "'";
			rs = dbconnection.executeQuery(query);

			if (rs != null) {
				String user = "";
				String pass = "";

				while (rs.next()) {
					user = rs.getString("username");
					pass = rs.getString("password");
				}
				if (username.compareTo(user) == 0
						&& password.compareTo(pass) == 0)
					return true;
			}
		} catch (Exception e) {
			System.out.println(" Error executing query to database LogInandLogOut doLogin: " + e);
		}
		return false;
	}
}
