package account.models;

//import java.sql.ResultSet;
import account.models.ResultSet;
import java.util.ArrayList;

public class Administration {

	public static ArrayList<User> findNoAdmin() {

		ArrayList<User> nonAdmin = new ArrayList<User>();
		ResultSet rs = null;

		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from Administration findNoAdmin. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from Administration findNoAdmin:  " + e);
		}

		try {
			rs = dbconnection.executeQuery("SELECT username, fname, lname, privileges FROM Users WHERE privileges = 'U'");

			if (rs != null) {
				String user = "";
				String fname = "";
				String lname = "";
				String privileges = "";

				while (rs.next()) {
					user = rs.getString("username");
					fname = rs.getString("fname");
					lname = rs.getString("lname");
					privileges = rs.getString("privileges");

					User user1 = new User(user, fname, lname, privileges);

					nonAdmin.add(user1);
				}
			}
		} catch (Exception e) {
			System.out.println(" Error executing query to database from Administration findNoAdmin:  " + e);
		}

		return nonAdmin;
	}

	/**
	 * Gets a list with the new users to be converted into admins
	 * 
	 * @param newAdmins
	 * @return
	 */
	public static boolean makeAdmin(ArrayList<User> newAdmins) {

		// record number of users to be changed
		int totalUsersToChange = newAdmins.size();

		if (totalUsersToChange == 0) {
			// Empty list of users passed
			System.err.println("No users in the list");
			return false;
		}

		DBConnection dbconnection;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established. ");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		int noChange = 0; // Count the amount of users no changed
		for (int i = 0; i < newAdmins.size(); i++) {

			String username = newAdmins.get(i).getUserName(); // saves the username

			try {
				String query = "UPDATE Users SET privileges = 'a' WHERE username = '"
						+ username + "'";

				dbconnection.executeUpdate(query);

			} catch (Exception e) {
				e.printStackTrace();
				noChange++;
			}

		}

		if (totalUsersToChange == noChange) {
			System.err.println("No users were changed");
			return false;
		}

		return true;
	}

	public static String sendRequest(String username, String fname, String lname) {

		// Connect to database
		DBConnection dbconnection;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established. ");
		} catch (Exception e) {
			e.printStackTrace();
			return "Error Conecting to Database from Administration sendRequest.";
		}

		String query = "";
		ResultSet rs = null;
		try {
			query = "SELECT * FROM AdminRequest WHERE requested_by = '"
					+ username + "'";

			rs = dbconnection.executeQuery(query);

			if (!rs.next()) {
				try {
					query = "INSERT INTO AdminRequest (requested_by, fname, lname) VALUES ('"
							+ username + "', '" + fname + "', '" + lname + "')";

					dbconnection.executeUpdate(query);

					return "Your request has been sent!";
				} catch (Exception e) {
					e.printStackTrace();
					return "An error occurred while processing your request";
				}
			} else {
				// if(rs.getString("requested_by").equals(username))
				return "Your request is already waiting for approval";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error executing to Database from Administration sendRequest.";
		}
	}

	public static ArrayList<User> showAdminRequest() {

		ArrayList<User> list = new ArrayList<User>();
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established. Administration showAdminRequest ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from Administration showAdminRequest:  " + e);
		}

		ResultSet rs = null;
		try {
			// After this, create your own logic
			rs = dbconnection.executeQuery("SELECT * FROM AdminRequest");

			String username = "";
			String fname = "";
			String lname = "";
			while (rs.next()) {

				User u = new User(null, null, null, null);

				username = rs.getString("requested_by").toString();
				fname = rs.getString("fname").toString();
				lname = rs.getString("lname").toString();

				u.setUserName(username);
				u.setFname(fname);
				u.setLname(lname);

				list.add(u);
			}

		} catch (Exception e) {
			System.out.println(" Error executing query to database from Administration showAdminRequest:  " + e);
		}
		System.out.println("I came into this method");
		return list;
	}

	public static void dropFromTable(ArrayList<User> list) {

		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from Administration dropFromTable:  " + e);
		}

		while (!list.isEmpty()) {
			String username = list.remove(0).getUserName(); // saves the
															// username
			System.out.println("User: " + username);
			try {
				String query = "DELETE FROM AdminRequest WHERE requested_by = '"
						+ username + "'";
				dbconnection.executeUpdate(query);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" Error deleting from database from Administration dropFromTable:  " + e);
			}
		}
	}
}
