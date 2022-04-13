package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest
{
	Client client;
	Cloud cloud;
	
	CloudServer cs;
	
	User user1;
	User user2;
	User user3;
	
	Server s1;
	Server s2;
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		cloud = new Cloud();
		
		cloud.createUser("Mitski","NobodyNobody","onlyHeartbreaker");
		cloud.createUser("LykkeLi","soSadsoSexy","twoNights");
		cloud.createUser("FKA","TWIGS","HomeWithYou");
		
		cloud.createServer("IndieWomen");
		cloud.createServer("SadGirlHourArtists");
		
		user1 = cloud.getUser("NobodyNobody");
		user2 = cloud.getUser("soSadsoSexy");
		user3 = cloud.getUser("TWIGS");
		
		
		CloudServer cs = new realCloud(cloud);
		client = new Client(cs);
		
		s1 = cs.findServer("IndieWomen");
		s2 = cs.findServer("SadGirlHourArtists");
		
		
		
		
		client.authentication("NobodyNobody", "onlyHeartbreaker");
		//setPassword("onlyHeartbreaker");
	}

	@Test
	void authenticationTest()
	{
		
		assertEquals(true, client.authentication("NobodyNobody","onlyHeartbreaker"));
		client.setPassword("smallChild");
		assertEquals("smallChild",client.getAccount().getPassword());
		assertEquals(false, client.authentication("NobodyNobody","onlyHeartbreaker"));
		
	}
	@Test 
	void blockTest()
	{
		
		ArrayList <User> checkBlocked = new ArrayList <User>();
		assertEquals(checkBlocked,user1.getBlocked());
		
		client.blockUser(user3);
		checkBlocked.add(user3);
		assertEquals(checkBlocked,user1.getBlocked());
		
		//Testing unblock
		checkBlocked.remove(user3);
		client.unBlock(user3);
		assertEquals(checkBlocked,user1.getBlocked());
		
	}
	@Test
	void ServersTest()
	{
		client.joinServer(s1);
		assertEquals(true,s1.getUsers().contains(user1));
		
		//creating servers
		assertEquals(1,user1.getServers().size()); 
		client.createServer("Guitar");
		assertEquals(2,user1.getServers().size());
		
		//leaving servers
		client.leaveServer(s1);
		assertEquals(1,user1.getServers().size());
		
	}
	@Test
	void createChannelTest()
	{
		client.joinServer(s2);
		client.createChannel(s2, "Mitski");
		assertEquals("Mitski",s2.getChannels().get(0).getName());
		
		//not in server shouldn't add any channels
		client.createChannel(s1, "Mitski");
		assertEquals(0,s1.getChannels().size());
		
	}
	@Test
	void inviteTest()
	{
		client.joinServer(s1);
		client.invite(user2, s1);
		assertEquals(true,s1.getUsers().contains(user2));
		
		//someone who isn't in server can't invite 
		client.invite(user2, s2);
		assertEquals(false,s2.getUsers().contains(user2));
	}
	@Test 
	void pinMessageTest() 
	{
		client.joinServer(s1);
		Message m1 = new Message("hello",1);
		Channel c1 = new Channel("Mitski",1);
		assertEquals(false,m1.isPinned());
		client.pinMessage(s1, c1, m1);
		assertEquals(true,m1.isPinned());
	}
	
	@Test
	void kickUserTest()
	{
		ArrayList <User> userCheck = new ArrayList <User>();
		
		user1.joinServer(s1);
		user2.joinServer(s1);
		user3.joinServer(s1);
	
		
		//Should do nothing user1 doesn't have permission
		userCheck.add(user1);
		userCheck.add(user2);
		userCheck.add(user3);
		client.kickUser(s1,user2);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(true, s1.getRoles().containsKey(user2));
		
		//should remove from list
		s1.assignAdmin(user1);
		
		client.kickUser(s1,user2);
		userCheck.remove(user2);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(false, s1.getRoles().containsKey(user2));
		
		//should remove from list
		s1.assignModerator(user1);
		client.kickUser(s1,user3);
		userCheck.remove(user3);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(false, s1.getRoles().containsKey(user3));
		
		
	}
	

}
