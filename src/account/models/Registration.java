package account.models;

import java.sql.ResultSet;

public class Registration {

	public boolean doRegistration(String fName, String lName, String email,
			String pass) {

		// To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from Registration doRegistration. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from Registration doRegistration:  " + e);
		}
		
		ResultSet rs = null;
		try {
			// After this, create your own logic
			String query = "SELECT * FROM Users WHERE username = '" + email + "'";
			rs = dbconnection.executeQuery(query);
		
			//return false if email already taken
			if (rs == null) {
				return false;
			} else {
				query = "INSERT INTO Users (username, fname,"
						+ " lname, password, privileges ) VALUES ('" + email + "', '" + fName 
						+ "', '" + lName + "', '" + pass + "', 'U' )";

				dbconnection.executeUpdate(query);
			}
		}
		catch (Exception e) {
			System.out.println(" Error executing update into the database  from Registration doRegistration:  " + e);
			return false;
		}
		//registration successful
		return true;
		
	}

}
