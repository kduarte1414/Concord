package concord;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest
{
	User u1;
	User u2;
	User u3;
	User u4;
	
	Server s1;
	Server s2;
	Server s3;
	
	String path;
	
	Theme t1;
	Theme t2;
	Theme t3;
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		path ="/Users/katherineduarte/eclipse-workspace/Concord/src/main/java/Main/";u1 = new User("Killua","zoldyck" , 2, "1234");
		u2 = new User("Hisoka","cardFool", 3,"567"); 
		u3 = new User("Gon", "fishingRod3", 4,"dadsucks");
		u4 = new User("Chrollo","PhantomLead", 5, "7889");
		
		s1 = new Server("Science",1);
		s2 = new Server("Cooking",2);
		s3 = new Server("Nen training",3);
		
		t1 = new Theme("LighteningColor",path);
		t2 = new Theme("GonGreen",path);
		//t3 = new Theme("PinknWhite",path);
		
		
		
		
	}

	@Test
	void testBlock()
	{
		
		
		ArrayList <User> checkBlocked = new ArrayList <User>();
		assertEquals(checkBlocked,u1.getBlocked());
		u1.blockUser(u2);
		checkBlocked.add(u2);
		assertEquals(checkBlocked,u1.getBlocked());
		
		//after unblock
		u1.unBlock(u2);
		checkBlocked.remove(u2);
		assertEquals(checkBlocked,u1.getBlocked());
		
	}
	
	@Test 
	void testServer(){
		//check servers when initialized
		ArrayList <Server> checkServers = new ArrayList <Server>();
		assertEquals(checkServers, u1.getServers());
		
		//check after joining
		u1.joinServer(s1);
		checkServers.add(s1);
		assertEquals(checkServers, u1.getServers());
		
	
		//check after creating 
		u1.CreateServer(s3);
		checkServers.add(s3);
		assertEquals(checkServers, u1.getServers());
		
		//after leaving server 
		u1.leaveServer(s1);
		checkServers.remove(s1);
		assertEquals(checkServers, u1.getServers());
	
		
	}
	@Test 
	void soloSprintCSS(){
		u1.createTheme("PinknWhite",path);
		assertEquals(1, u1.getThemes().size());
		assertEquals("PinknWhite", u1.getThemes().get(0).getThemeName());
		
		u1.setTheme("PinknWhite");
		assertEquals(true, u1.getThemes().get(0).getisSetTheme());
		
		u1.createTheme("LighteningColor",path);
		assertEquals(2, u1.getThemes().size());
		
		assertEquals(false, u1.getThemes().get(1).getisSetTheme());
		
		u1.setTheme("LighteningColor");
		assertEquals(false, u1.getThemes().get(0).getisSetTheme());
		assertEquals(true, u1.getThemes().get(1).getisSetTheme());
		
		u1.deleteTheme("PinknWhite");
		assertEquals(1, u1.getThemes().size());
	
	
	}
}
	


