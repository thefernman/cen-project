package account.models;

//import java.sql.ResultSet;
import account.models.ResultSet;

public class PasswordReset {

	public boolean checkEmail(String username) {

		// To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from PasswordReset checkEmail. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from PasswordReset checkEmail:  " + e);
		}
		ResultSet rs = null;
		try {
			// After this, create your own logic
			String query = "SELECT username, fname, password FROM Users WHERE username = '" + username + "'";
			rs = dbconnection.executeQuery(query);

			if (rs != null) {
				String email = "";
				String name = "";
				String pass = "";

				while (rs.next()) {
					email = rs.getString("username");
					name = rs.getString("fname");
					pass = rs.getString("password");
				}
				
				if (username.compareTo(email) == 0) {

					//send(username, name, pass);
					SendEmail.send(username, name, "Forgot Password",  "You have requested your password "
							+ "for your Family Tree Account. " +
							"The password for your account is " + pass);

					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(" Error executing query to database from PasswordReset checkEmail:  " + e);
		}
		return false;
	}
}
