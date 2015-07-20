package account.models;

//import java.sql.ResultSet;
import account.models.ResultSet;

public class Link {

	public static int getMemberID(String fName, String lName, String bday) {

		// To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from Link getMemberID. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from Link getMemberID:  " + e);
		}
		ResultSet rs = null;
		try {
			String query = "SELECT member_id FROM Members WHERE first_name = '"
					+ fName + "' AND last_name = '" + lName + "' AND birthdate = "
					+ bday;

			rs = dbconnection.executeQuery(query);

			if (!rs.next()) {
				return -1;
			}
			return rs.getInt(1);

		} catch (Exception e) {
			System.out.println(" Error executing query to database from Link getMemberID:  " + e);
		}

		return 0;

	}
}
