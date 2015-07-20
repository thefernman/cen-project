package account.models;

import java.util.ArrayList;

public class ResultSet {
	
	ArrayList<User> allUsers;
	int currentIndex = -1; 
	String password;
	
	public ResultSet()
	{
		allUsers = new ArrayList<User>();
	}
	
	public void addUser(User newUser)
	{
		allUsers.add(newUser);
	}
	
	public Boolean next()
	{
		if(currentIndex >= allUsers.size()-1)
			return false;
		else 
		{
			currentIndex++;
			return true;
		}
	}
	
	public String getString (String parameter)
	{
		User u = allUsers.get(currentIndex);
		if (parameter.equalsIgnoreCase("username"))
			return u.getUserName();
		else if(parameter.equalsIgnoreCase("fname"))
			return u.getFname();
		else if(parameter.equalsIgnoreCase("lname"))
			return u.getLname();
		else if(parameter.equalsIgnoreCase("privileges"))
			return u.getPriviledges();
		else if(parameter.equalsIgnoreCase("password"))
			return password;
		else
			return "";
	}
	
	public void setPassword(String s) {
		password = s;
	}
	
	public void deleteAll()
	{
		allUsers.clear();
	}

}
