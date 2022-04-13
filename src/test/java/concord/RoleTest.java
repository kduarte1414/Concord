package concord;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoleTest
{
	 Server s1;
	 Server s2;
	 
	 Channel c1;
	 Channel c2;
	 
	 User u1;
	 User u2;
	
	 Message m1;
	 Message m2;
	 Message m3;
	 
	 Admin a1;
	 Moderator mod1;
	 Default d1;
	 
	 Admin a2;
	 Moderator mod2;
	 Default d2;
	 
	 
	@BeforeEach
	void setUp() throws Exception
	{
		s1 = new Server("Reality TV",1);
		s2 = new Server("'Anime ",2);
		
		
		c1 = new Channel("Vanderpump Rules",1);
		c2 = new Channel("HunterxHunter",2);
		
		u1 = new User("Kat","KatDua",1, "duakit");
		u2 = new User("Liz","LizzyLi",2, "satsuma");
		
		a1 = new Admin(s1);
		mod1 = new Moderator(s1);
		d1 = new Default(s1);
		
		a2 = new Admin(s2);
		mod2 = new Moderator(s2);
		d2 = new Default(s2);
		
		m1 = new Message("It's not about the pasta!",1);
		m2 = new Message("I don't know what I did to you but I will take a Pinot Grigio",2);
		m3 = new Message ("Bungee gum",1);
		
		
	}

	@Test
	void testBlockChannel()
	{
		
		a1.blockChannel(c1);  //should be allowed
		assertEquals(true, c1.isLocked());
		
		mod1.blockChannel(c2); //should be allowed
		assertEquals(true, c2.isLocked());
	
		
		
	}
	
	@Test 
	void testBlockChannelno()
	{
		d1.blockChannel(c2); //default not allowed
		assertEquals(false, c2.isLocked());
	}
	
	@Test
	void kickUser()
	{
		ArrayList <User> userCheck = new ArrayList <User>();
		Default basic = new Default(s1);
		//initializing user 
		u1.joinServer(s1);
		u2.joinServer(s1);
		
		userCheck.add(u1);
		userCheck.add(u2);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(basic.getRoleName(),s1.getUserRole(u2));
		
		//nothing should change
		d1.KickUser(u1); //not allowed 
		assertEquals(userCheck,s1.getUsers());
		assertEquals(true, s1.getRoles().containsKey(u1));
		
		//user1 should be removed from both users and roles 
		a1.KickUser(u1);
		userCheck.remove(u1);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(false, s1.getRoles().containsKey(u1));
		
		//user2 should be removed Moderator allowed to do this too 
		mod1.KickUser(u2);
		userCheck.remove(u2);
		assertEquals(false, s1.getRoles().containsKey(u2));
		
		assertEquals(userCheck,s1.getUsers());

		
	}
	
	@Test
	void testAssignModerator()
	{
		ArrayList <User> userCheck = new ArrayList <User>();
		
		//shouldn't do anything
		u1.joinServer(s1);
		userCheck.add(u1);
		Default basic = new Default(s1);
	
		d1.AssignModerator(u1);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(basic.getRoleName(),s1.getUserRole(u1));
		
		//should work 
		mod1.AssignModerator(u1);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(mod1.getRoleName(),s1.getUserRole(u1));
		
		//should work
		u2.joinServer(s1);
		userCheck.add(u2);
		a1.AssignModerator(u2);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(mod1.getRoleName(),s1.getUserRole(u2));
		
		
		
	}
	@Test
	void testAssignAdmin() 
	{
		ArrayList <User> userCheck = new ArrayList <User>();
		ArrayList <User> userCheck2 = new ArrayList <User>();

		
		u1.CreateServer(s2); //admin status in this server 
		//check if user that creates has admin status
		assertEquals(a2.getRoleName(),s2.getUserRole(u1));
		a2.AssignAdmin(u2);
		userCheck.add(u1);
		userCheck.add(u2);
		assertEquals(a2.getRoleName(),s2.getUserRole(u2));
		assertEquals(userCheck, s2.getUsers());
		
		//shouldn't change anything don't have permissions 
		u2.joinServer(s1);
		Default basic = new Default(s1);
		userCheck2.add(u2);
		assertEquals(basic.getRoleName(),s1.getUserRole(u2));
		
		d1.AssignAdmin(u2); 
		assertEquals(basic.getRoleName(),s1.getUserRole(u2));
		
		//shouldn't change
		mod1.AssignAdmin(u1);
		u1.joinServer(s1);
		assertEquals(basic.getRoleName(),s1.getUserRole(u1));
		
	}
	//following should work the same
	@Test 
	void chatDefault() 
	{
		s1.addChannel(c1);
		u1.joinServer(s1);
		u2.joinServer(s1);
		
		ArrayList <Message> checkMessages = new ArrayList <Message>();
		d1.createNewMessage(c1,m1);
		checkMessages.add(m1);
		assertEquals(checkMessages,c1.getMessages());
		d1.respond(c1,m1,m2);
		checkMessages.add(m2);
		assertEquals(checkMessages,c1.getMessages());
		d1.respond(c1,m1,m2);
		checkMessages.add(m2);
		assertEquals(checkMessages,c1.getMessages());
		assertEquals(m2,m1.getLinkMessage());
	
		
	}
	
	@Test 
	void chatMod() {
		
		s1.addChannel(c1);
		u1.joinServer(s1);
		u2.joinServer(s1);
		ArrayList <Message> checkMessages = new ArrayList <Message>();
		mod1.createNewMessage(c1,m1);
		checkMessages.add(m1);
		assertEquals(checkMessages,c1.getMessages());
		mod1.respond(c1,m1,m2);
		checkMessages.add(m2);
		assertEquals(checkMessages,c1.getMessages());
		assertEquals(m2,m1.getLinkMessage());
		
	}
	@Test 
	void chatAdm() {
		s1.addChannel(c1);
		u1.joinServer(s1);
		u2.joinServer(s1);
		ArrayList <Message> checkMessages = new ArrayList <Message>();
		
		a1.createNewMessage(c1,m1);
		checkMessages.add(m1);
		assertEquals(checkMessages,c1.getMessages());
		a1.respond(c1,m1,m2);
		checkMessages.add(m2);
		assertEquals(checkMessages,c1.getMessages());
		assertEquals(m2,m1.getLinkMessage());
		
	}
	
	/*making sure I can't send messages in a channel
		from a different server*/
	@Test 
	void checkChannels()
	{
		ArrayList <Message> checkMessages = new ArrayList <Message>();
		s2.addChannel(c2);
		a1.createNewMessage(c2,m3);
		/*should be empty a1 is an admin in server 1 not server 2
		 * and channel 2 was created in server 2
		 * */
		assertEquals(checkMessages,c2.getMessages());
		a2.createNewMessage(c2,m3);
		checkMessages.add(m3);
		//a2 should add the message because role attached to server2
		assertEquals(checkMessages,c2.getMessages());
	}

}
