package account.models;

import java.util.ArrayList;

public class ResultSet {
	
	ArrayList<User> allUsers;
	ArrayList<Comments> allComments;
	int currentIndexUser = -1;
	int currentIndexComments = -1;
	String password;
	boolean userRS;
	boolean commentRS;
	
	public ResultSet(String type)
	{
		if (type.equalsIgnoreCase("user")) {
			userRS = true;
			allUsers = new ArrayList<User>();
		}
		if (type.equalsIgnoreCase("comment")) {
			commentRS = true;
			allComments = new ArrayList<Comments>();
		}
		
	}
	
	public void addUser(User newUser)
	{
		allUsers.add(newUser);
	}
	
	public void addComments(Comments newComment)
	{
		allComments.add(newComment);
	}
	
	public Boolean next()
	{
		if(userRS) {
			if(currentIndexUser >= allUsers.size()-1 )
				return false;
			else 
			{
				currentIndexUser++;
				return true;
			}
		}
		else {
			if(currentIndexComments >= allComments.size()-1 )
				return false;
			else 
			{
				currentIndexComments++;
				return true;
			}
		}
		
	}
	
	public String getString (String parameter)
	{
		if(userRS) {
			User u = allUsers.get(currentIndexUser);
			if (parameter.equalsIgnoreCase("username"))
				return u.getUserName();
			else if(parameter.equalsIgnoreCase("fname"))
				return u.getFname();
			else if(parameter.equalsIgnoreCase("lname"))
				return u.getLname();
			else if(parameter.equalsIgnoreCase("privileges"))
				return u.priviledges;
			else if(parameter.equalsIgnoreCase("password"))
				return password;
			else if(parameter.equalsIgnoreCase("requested_by"))
				return u.getUserName();
			else
				return "";
		}
		else {
			Comments c = allComments.get(currentIndexComments);
			if (parameter.equalsIgnoreCase("create_dt"))
				return c.getDate();
			else if(parameter.equalsIgnoreCase("create_by"))
				return c.getOwner();
			else if(parameter.equalsIgnoreCase("content"))
				return c.getText();
			else if(parameter.equalsIgnoreCase("fname"))
				return c.getFname();
			else if(parameter.equalsIgnoreCase("lname"))
				return c.getLname();
			else
				return "";
		}
		
	}
	
	/*
	 * Function not implemented
	 */
	public int getInt (String parameter)
	{
		if(commentRS)
		{
			Comments c = allComments.get(currentIndexComments);
			if (parameter.equalsIgnoreCase("comment_id"))
				return c.getID();
			else if(parameter.equalsIgnoreCase("parent"))
				return c.getParent();
			else if(parameter.equalsIgnoreCase("total"))
				return 1;
			else if(parameter.equalsIgnoreCase("comment_family"))
				return 0;
			else
				return -1;
		}
		return -1;
	}
	
	public int getInt (int parameter)
	{
		return 1;
	}
	
	public void setPassword(String s) {
		password = s;
	}
	
	public void deleteAll()
	{
		allUsers.clear();
	}

}