package account.models;

import java.util.ArrayList;

public class Facade {
	
	
	//Administration class
	public static void dropFromTable(ArrayList<User> list) {
		Administration.dropFromTable(list);
	}

	public static ArrayList<User> findNoAdmin() {
		return Administration.findNoAdmin();
	}
	
	public static boolean makeAdmin(ArrayList<User> newAdmins) {
		return Administration.makeAdmin(newAdmins);
	}
	
	public static String sendRequest(String username, String fname, String lname) {
		return Administration.sendRequest(username, fname, lname);
	}
	
	public static ArrayList<User> showAdminRequest() {
		return Administration.showAdminRequest();
	}
	
	//Link class
	public static int getMemberID(String fName, String lName, String bday){
		return Link.getMemberID(fName, lName, bday);
	}
	
	//LogInAndOut class
	public static boolean doLogin(String username, String password) {
		return LogInAndOut.doLogin(username, password);
	}
	
	//getCommentsInfo class
	public static ArrayList<Comments> getComments() {
		return getCommentsInfo.getComments();
	}
	
	//getUserRow class
	public static String[] getUserInfo(String username) {
		return getUserRow.getUserInfo(username);
	}
	
	//some that were objects
	//Registration
	public boolean doRegistration(String fName, String lName, String email,
			String pass) {
		Registration reg = new Registration();
		return reg.doRegistration(fName, lName, email, pass);
	}

	//PasswordReset
	public boolean checkEmail(String username) {
		PasswordReset passwordReset = new PasswordReset();
		return passwordReset.checkEmail(username);
	}
	
	//From CommentServlet
	//static calls to MakeReplay
	public static void postReplay(int id, String text, String owner) {
		MakeReplay.postReplay(id, text, owner);
	}
	
	public static String getLast(int total) {
		return MakeReplay.getLast(total);
	}
	
	public static int postComment(String text, String owner) {
		return MakeReplay.postComment(text, owner);
	}
	
}
