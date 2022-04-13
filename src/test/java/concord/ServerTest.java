package concord;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerTest
{
	Server s1;
	
	Channel c1;
	Channel c2;
	Channel c3;
	
	User u1;
	User u2;
	User u3;
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		s1 = new Server("Anime Fans",1);
		c1 = new Channel("Naruto",1);
		c2 = new Channel("Attack on Titan", 1);
		c3 = new Channel("Assasination Classroom",1);
		
		u1 = new User("koro","octoTeach", 1,"explosive");
		u2 = new User("Naruto", "nineTails", 2, "ramenrules");
		u3 = new User("Eren","attackTitan",3,"foundingTitan");
		
			
	}

	@Test
	void testChannel()
	{
		//check channels when initialized
		ArrayList <Channel> checkChannel = new ArrayList<Channel>();
		assertEquals(checkChannel,s1.getChannels());
		//check channels after adding
		s1.addChannel(c1);
		s1.addChannel(c2);
		s1.addChannel(c3);
		checkChannel.add(c1);
		checkChannel.add(c2);
		checkChannel.add(c3);
		
		assertEquals(checkChannel,s1.getChannels());
		//check channels after deleting
		s1.deleteChannel(c3);
		checkChannel.remove(c3);
		assertEquals(checkChannel,s1.getChannels());
		
	}
	@Test 
	void testUsers()
	{
		//check users when initialized 
		ArrayList <User> checkUser = new ArrayList<User>();
		assertEquals(checkUser, s1.getUsers());
		//check users after adding
		s1.addUsers(u1);
		s1.addUsers(u2);
		s1.addUsers(u3);
		checkUser.add(u1);
		checkUser.add(u2);
		checkUser.add(u3);
		assertEquals(checkUser,s1.getUsers());
		
		//check users after deleting
		s1.kickUser(u1);
		checkUser.remove(u1);
		assertEquals(checkUser,s1.getUsers());
	}
	@Test 
	void testRoles()
	{
		//assertEquals(checkRoles, s1.getRoles());
		s1.addUsers(u1);
		Default basic= new Default(s1);
		assertEquals(basic.getRoleName(), s1.getUserRole(u1));
		
		
	}

}
