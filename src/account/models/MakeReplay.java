package account.models;

import java.sql.ResultSet;

public class MakeReplay {

	public static void postReplay(int id, String text, String owner) {

		// To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from MakeReplay postReplay. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from MakeReplay postReplay:  " + e);
		}
		try {
			// After this, create your own logic
			String query = "CALL insert_replay('"+ text + "','"+ id + "','"+ owner + "')";
			dbconnection.executeQuery(query);
		} catch (Exception e) {
			System.out.println(" Error calling insert_replay to database from MakeReplay postReplay:  " + e);
		}
	}
	
	public static int postComment(String text, String owner) {
		
		int id = -1;
		// To connect to the database
		DBConnection dbconnection = null;
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from MakeReplay postComment. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from MakeReplay postComment:  " + e);
		}
		ResultSet rs = null;
		try {
			// After this, create your own logic
			String query = "CALL insert_comment('" + text + "','" + owner + "')";
			System.out.println("Called insert_comment from from MakeReplay postComment");
			rs = dbconnection.executeQuery(query);
			
			if (rs != null) 
				while (rs.next()) 
					id = rs.getInt("comment_id");
		} catch (Exception e) {
			System.out.println(" Error executing insert_comment to database from MakeReplay postComment:  " + e);
		}
	return id;
	}
	
	public static String getLast(int total) {
		
		int result = 0;
		String json = "";
		// To connect to the database
		DBConnection dbconnection = null;
		String query = "";
		try {
			dbconnection = new DBConnection();
			System.out.println(" Connection Established from MakeReplay getLast. ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Error connecting to database from MakeReplay getLast:  " + e);
		}
		ResultSet rs = null;
		try {
			// After this, create your own logic
			query = "SELECT Count(*) as total FROM Comments";
			rs = dbconnection.executeQuery(query);
			if (rs != null){
				while (rs.next()){
					result = rs.getInt("total");
				}
			}
			
			if(result > total){
				result = result - total;
				query = "SELECT comment_id,comment_family,content,fname,lname "
						+ "FROM Comments,Users where created_by = username Order "
						+ "by comment_id Desc Limit " + result;
				rs = dbconnection.executeQuery(query);
				if (rs != null){ 
					json = "[";
					while (rs.next()){
						json = json + "{\"id\":\"" + rs.getInt("comment_id");
						json = json + "\",\"parent\":\"" + rs.getInt("comment_family");
						json = json + "\",\"text\":\"" + rs.getString("content");
						json = json + "\",\"first\":\"" + rs.getString("fname");
						json = json + "\",\"last\":\"" + rs.getString("lname");
						json = json + "\",\"total\":\"" + result--;
						json = json + "\"},";
					}
					json = json.substring(0,json.length()-1);
					json = json + "]";
				}
			}			
		} catch (Exception e) {
			System.out.println("Error connecting to database from MakeReplay getLast: " + e);
		}
		return json;
	}
}
