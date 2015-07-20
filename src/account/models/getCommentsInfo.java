package account.models;

//import java.sql.ResultSet;
import account.models.ResultSet;
import java.util.ArrayList;

public class getCommentsInfo {

	public static ArrayList<Comments> getComments() {

		ArrayList<Comments> list = new ArrayList<Comments>();

		// To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from getCommentsInfo getComments. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from getCommentsInfo getComments:  " + e);
		}

		ResultSet rs = null;
		try {
			// After this, create your own logic
			rs = dbconnection.executeQuery("CALL get_comments");

			if (rs != null) {

				int id;
				int parent;
				String date = "";
				String owner = "";
				String text = "";
				String fname = "";
				String lname = "";

				while (rs.next()) {
					id = rs.getInt("comment_id");
					parent = rs.getInt("parent");
					date = rs.getString("create_dt");
					owner = rs.getString("create_by");
					text = rs.getString("content");
					fname = rs.getString("fname");
					lname = rs.getString("lname");

					Comments newcomment = new Comments(id, parent, date, owner,
							text, fname, lname);
					list.add(newcomment);
				}
			}
		} catch (Exception e) {
			System.out.println(" Error with database results from getCommentsInfo getComments:  " + e);
		}
		return list;
	}
}
