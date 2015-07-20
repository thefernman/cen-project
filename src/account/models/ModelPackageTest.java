package account.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import account.models.ResultSet;

public class ModelPackageTest {

	private Facade fake;
	private DBConnection dbstub;

	@Before
	public void setUp() throws Exception {
		fake = new Facade();
		dbstub = new DBConnection();
	}

	@After
	public void tearDown() throws Exception {
		fake = null;
	}

	public boolean equalUsers(User user1, User user2) {
		return user1.getFname().equalsIgnoreCase(user2.getFname())
				&& user1.getLname().equalsIgnoreCase(user2.getLname())
				&& user1.getPriviledges().equalsIgnoreCase(
						user2.getPriviledges())
				&& user1.getUserName().equalsIgnoreCase(user2.getUserName());
	}

	@Test
	public void AS001RSubSystemTC001() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("Registration", true, fake.doRegistration("Anais",
				"Hernandez", "ahern379@fiu.edu", "anais123"));
	}

	@Test
	public void AS001RSubSystemTC002() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("Registration", true, fake.doRegistration("John", "Smith",
				"biggomz@gmail.com", "abc123"));
	}

	@Test
	public void AS001RSubSystemTC003() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("Registration", true, fake.doRegistration("Jeffrey",
				"Perez", "jpere610@fiu.edu", "abc123"));
	}

	@Test
	public void AS001RSubSystemTC004() {
		DBConnection.getInstance(null);
		assertEquals("Registration", false, fake.doRegistration("John",
				"Smith", "jpere610@fiu.edu", "abc123"));
	}

	@Test
	public void AS001RSubSystemTC005() {
		DBConnection.getInstance(null);
		assertEquals("Registration", false, fake.doRegistration("Jane", "Doe",
				"ahern379@fiu.edu", "abc123"));
	}

	@Test
	public void AS001RSubSystemTC006() {
		DBConnection.getInstance(null);
		assertEquals("Registration", false,
				fake.doRegistration("Jim", "Davis", "jd@db.com", "xyz890"));
	}

	@Test
	public void AS001LSubSystemTC001() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("ahern379@fiu.edu", "Anais", "Hernandez", "U"));
		dbstub.setPassword("abc123");
		assertEquals("Login", true,
				Facade.doLogin("ahern379@fiu.edu", "abc123"));
	}

	@Test
	public void AS001LSubSystemTC002() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("biggomz@gmail.com", "Jeff", "Perez", "U"));
		dbstub.setPassword("abc123");
		assertEquals("Login", true,
				Facade.doLogin("biggomz@gmail.com", "abc123"));
	}

	@Test
	public void AS001LSubSystemTC003() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("jpere610@fiu.edu", "Jeff", "Perez", "U"));
		dbstub.setPassword("abc123");
		assertEquals("Login", true,
				Facade.doLogin("jpere610@fiu.edu", "abc123"));
	}

	@Test
	public void AS001LSubSystemTC004() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("jibba@gmail.com", "Jeff", "Perez", "U"));
		dbstub.setPassword("abc123");
		assertEquals("Login", false,
				Facade.doLogin("jibba@gmail.com", "aaa111"));
	}

	@Test
	public void AS001LSubSystemTC005() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("jpere610@fiu.edu", "Jeff", "Perez", "U"));
		dbstub.setPassword("abc123");
		assertEquals("Login", false,
				Facade.doLogin("jpere610@fiu.edu", "acc123"));
	}

	@Test
	public void AS001LSubSystemTC006() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("fcamp001@fiu.edu", "Fernando", "Campo", "U"));
		dbstub.setPassword("abc123");
		assertEquals("Login", false, Facade.doLogin("fcamp001@fiu.edu", "camp"));
	}

//	 @Test
	 public void AS001PRSubSystemTC001() {
	 DBConnection.getInstance(new ResultSet());
	 dbstub.addUser(new User("ahern379@fiu.edu", "Anais", "Hernandez", "U"));
	 dbstub.setPassword("abc123");
	 assertEquals("PasswordReset", true, fake.checkEmail("ahern379@fiu.edu"));
	 }
	
//	 @Test
	 public void AS001PRSubSystemTC002() {
	 DBConnection.getInstance(new ResultSet());
	 dbstub.addUser(new User("jpere610@fiu.edu", "Jeff", "Perez", "U"));
	 dbstub.setPassword("abc123");
	 assertEquals("PasswordReset", true, fake.checkEmail("jpere610@fiu.edu"));
	 }
	
//	 @Test
	 public void AS001PRSubSystemTC003() {
	 DBConnection.getInstance(new ResultSet());
	 dbstub.addUser(new User("biggomz@gmail.com", "Jeff", "Perez", "U"));
	 dbstub.setPassword("abc123");
	 assertEquals("PasswordReset", true,
	 fake.checkEmail("biggomz@gmail.com"));
	 }

	@Test
	public void AS001PRSubSystemTC004() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("PasswordReset", false, fake.checkEmail("amc@db.com"));
	}

	@Test
	public void AS001PRSubSystemTC005() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("PasswordReset", false, fake.checkEmail("jibba@gmail.com"));
	}

	@Test
	public void AS001PRSubSystemTC006() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("PasswordReset", false, fake.checkEmail(""));
	}

	@Test
	public void AS001ASubSystemTC001() {
		DBConnection.getInstance(new ResultSet());
		User user1 = new User("jpere610@fiu.edu", "Jeff", "Perez", "U");
		dbstub.addUser(user1);
		ArrayList<User> list = Facade.findNoAdmin();
		assertEquals("Users", true, equalUsers(user1, list.get(0)));
		assertEquals("MakeAdmin", true, Facade.makeAdmin(list));
	}

	@Test
	public void AS001ASubSystemTC002() {
		DBConnection.getInstance(new ResultSet());
		User user1 = new User("ahern379@fiu.edu", "Anais", "Hernandez", "U");
		dbstub.addUser(user1);
		ArrayList<User> list = Facade.findNoAdmin();
		assertEquals("Users", true, equalUsers(user1, list.get(0)));
		assertEquals("MakeAdmin", true, Facade.makeAdmin(list));
	}

	@Test
	public void AS001ASubSystemTC003() {
		DBConnection.getInstance(new ResultSet());
		User user1 = new User("fcamp001@fiu.edu", "Fernando", "Campo", "U");
		dbstub.addUser(user1);
		ArrayList<User> list = Facade.findNoAdmin();
		assertEquals("Users", true, equalUsers(user1, list.get(0)));
		assertEquals("MakeAdmin", true, Facade.makeAdmin(list));
	}

	@Test
	public void AS001ASubSystemTC004() {
		DBConnection.getInstance(new ResultSet());
		ArrayList<User> list = Facade.findNoAdmin();
		assertEquals("MakeAdmin", false, Facade.makeAdmin(list));
	}

	@Test
	public void AS001ASubSystemTC005() {
		DBConnection.getInstance(new ResultSet());
		ArrayList<User> list = Facade.findNoAdmin();
		assertEquals("MakeAdmin", false, Facade.makeAdmin(list));
	}

	@Test
	public void AS001ASubSystemTC006() {
		DBConnection.getInstance(new ResultSet());
		ArrayList<User> list = Facade.findNoAdmin();
		assertEquals("MakeAdmin", false, Facade.makeAdmin(list));
	}

	@Test
	public void AS001RRSubSystemTC001() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("RequestRights", "Your request has been sent!",
				Facade.sendRequest("ahern379@fiu.edu", "Anais", "Hernandez"));
	}

	@Test
	public void AS001RRSubSystemTC002() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("RequestRights", "Your request has been sent!",
				Facade.sendRequest("fcamp001@fiu.edu", "Anais", "Hernandez"));
	}

	@Test
	public void AS001RRSubSystemTC003() {
		DBConnection.getInstance(new ResultSet());
		assertEquals("RequestRights", "Your request has been sent!",
				Facade.sendRequest("jpere610@fiu.edu", "Jeff", "Perez"));
	}

	@Test
	public void AS001RRSubSystemTC004() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("ahern379@fiu.edu", "Anais", "Hernandez", "U"));
		assertEquals("RequestRights",
				"Your request is already waiting for approval",
				Facade.sendRequest("ahern379@fiu.edu", "Anais", "Hernandez"));
	}

	@Test
	public void AS001RRSubSystemTC005() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("jpere610@fiu.edu", "Jeff", "Perez", "U"));
		assertEquals("RequestRights",
				"Your request is already waiting for approval",
				Facade.sendRequest("jpere610@fiu.edu", "Jeff", "Perez"));
	}

	@Test
	public void AS001RRSubSystemTC006() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("fcamp001@fiu.edu", "Fernando", "Campo", "U"));
		assertEquals("RequestRights",
				"Your request is already waiting for approval",
				Facade.sendRequest("fcamp001@fiu.edu", "Fernando", "Campo"));
	}

	@Test
	public void AS001ARSubSystemTC001() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("ahern379@fiu.edu", "Anais", "Hernandez", "U"));
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001ARSubSystemTC002() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("fcamp001@fiu.edu", "Fernando", "Campo", "U"));
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001ARSubSystemTC003() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("jpere610@fiu.edu", "Jeff", "Perez", "U"));
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001ARSubSystemTC004() {
		DBConnection.getInstance(new ResultSet());
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001ARSubSystemTC005() {
		DBConnection.getInstance(new ResultSet());
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001ARSubSystemTC006() {
		DBConnection.getInstance(new ResultSet());
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}
	
	@Test
	public void AS001DRSubSystemTC001() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("ahern379@fiu.edu", "Anais", "Hernandez", "U"));
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001DRSubSystemTC002() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("fcamp001@fiu.edu", "Fernando", "Campo", "U"));
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001DRSubSystemTC003() {
		DBConnection.getInstance(new ResultSet());
		dbstub.addUser(new User("jpere610@fiu.edu", "Jeff", "Perez", "U"));
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001DRSubSystemTC004() {
		DBConnection.getInstance(new ResultSet());
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001DRSubSystemTC005() {
		DBConnection.getInstance(new ResultSet());
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

	@Test
	public void AS001DRSubSystemTC006() {
		DBConnection.getInstance(new ResultSet());
		Facade.dropFromTable(dbstub.db.allUsers);
		assertEquals("Dropped from db", true, dbstub.db.allUsers.isEmpty());
	}

}
