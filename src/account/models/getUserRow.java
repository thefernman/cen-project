package account.models;

import java.sql.ResultSet;

public class getUserRow {
	
	public static String[] getUserInfo(String username) {
		
		String [] row = new String[4];

		//To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from getUserInfo getUserInfo. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from getUserInfo getUserInfo:  " + e);
		}

		ResultSet rs = null;
		try {
			//After this, create your own logic
			String query = "SELECT * FROM Users WHERE username = '" + username + "'";
			rs = dbconnection.executeQuery(query);

			if (rs != null) {			
				
				while (rs.next()) {
					 row[0] = rs.getString("username");
					 row[1] = rs.getString("fname");
					 row[2] = rs.getString("lname");
					 row[3] = rs.getString("privileges");
				}
			}
		} catch (Exception e) {
			System.out.println(" Error executing query to database from getUserInfo getUserInfo:  " + e);
		}
		
		return row;
	}

}
