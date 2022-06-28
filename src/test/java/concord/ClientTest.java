package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.Naming;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest
{
	Client client;
	static Cloud cloud;
	static Registry registry;
	static realCloud rc;
	
	User user1;
	User user2;
	User user3;
	
	Server s1;
	Server s2;
	
	String path;
	
	@BeforeAll
	static void setupServer()
	{
		cloud = new Cloud();
		
		try
		{
			rc = new realCloud(cloud);
			registry = LocateRegistry.createRegistry(1099);
			registry.rebind("RCLOUD",rc);	
		
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		rc.clearData();
		cloud.createUser("Mitski","NobodyNobody","onlyHeartbreaker");
		cloud.createUser("LykkeLi","soSadsoSexy","twoNights");
		cloud.createUser("FKA","TWIGS","HomeWithYou");
		
		cloud.createServer("IndieWomen");
		cloud.createServer("SadGirlHourArtists");
		

		CloudServer cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
		client = new Client(cs);
		rc.addObserver(client);
		
		s1 = rc.findServer("IndieWomen");
		s2 = rc.findServer("SadGirlHourArtists");
		user1 = rc.findUser("NobodyNobody");
		user2 =rc.findUser("soSadsoSexy");
		user3 = rc.findUser("TWIGS");
		
		
		
		
		client.authentication("NobodyNobody", "onlyHeartbreaker");
		client.updateCounter=0;
		path ="/Users/katherineduarte/eclipse-workspace/Concord/src/main/java/Main/";
	}

	@Test
	void authenticationTest()
	{
		
		try
		{
			assertEquals(true, client.authentication("NobodyNobody","onlyHeartbreaker"));
			client.setPassword("tallChild");
			assertEquals("tallChild",rc.findUser(1).getPassword());
			assertEquals(false, client.authentication("NobodyNobody","onlyHeartbreaker"));
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test 
	void blockTest()
	{
		
		ArrayList <User> checkBlocked = new ArrayList <User>();
		assertEquals(checkBlocked,user1.getBlocked());
		
		try
		{
			client.blockUser(user3);
			checkBlocked.add(user3);
			assertEquals(checkBlocked,user1.getBlocked());
			//Testing unblock
			checkBlocked.remove(user3);
			client.unBlock(user3);
			assertEquals(checkBlocked,user1.getBlocked());
			
		
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void ServersTest()
	{
		try
		{
			client.joinServer(s1);
			assertEquals(1,rc.findServer(1).getUserCount());
			assertEquals("Mitski",rc.findServer(1).getUsers().get(0).getRealName());
			assertEquals(1,client.updateCounter);
			
			//creating servers User should have one server
			assertEquals(1,rc.findUser(1).getServers().size()); 
			//After creating one - client is now in 2 servers
			client.createServer("Guitar");
			assertEquals(2,rc.findUser(1).getServers().size());
			//User in server name should be Mitski
			assertEquals("Mitski",rc.findServer(3).getUsers().get(0).getRealName());
			//Server ID3 (bc already 2 servers in cloud)name should be Guitar
			assertEquals("Guitar",rc.findServer(3).getName());
			//Update was called on observers
			assertEquals(2,client.updateCounter);
			//leaving servers
			client.leaveServer(s1);
			assertEquals(3,client.updateCounter);
			//client should be in 1 server now 
			assertEquals(1,rc.findUser(1).getServers().size()); 
			assertEquals("Guitar",rc.findUser(1).getServers().get(0).getName());
			
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	void createChannelTest()
	{
		try
		{
			client.joinServer(s1);
			client.createChannel(s1,"Florence + the Machine");
			client.createChannel(s1,"Sharon Van Etten");
			
			assertEquals(3,client.updateCounter);
			
			assertEquals(2, user1.getServers().get(0).getChannels().size());
			assertEquals("Florence + the Machine",s1.getChannels().get(0).getName());
			assertEquals("Sharon Van Etten",s1.getChannels().get(1).getName());
			
			//not in server shouldn't add any channels
			client.createChannel(s2, "Mitski");
			//not observer either no operation performed
			assertEquals(3,client.updateCounter);
			assertEquals(0,s2.getChannels().size());
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void inviteTest()
	{
		try
		{
			client.joinServer(s1);
			client.invite(user2, s1);
			assertEquals(2,client.updateCounter);
			assertEquals(true,s1.getUsers().contains(user2));
			//someone who isn't in server can't invite 
			client.invite(user2, s2);
			assertEquals(false,s2.getUsers().contains(user2));
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test 
	void MessagingTest() 
	{
		
		try
		{
			client.joinServer(s1);
			client.createChannel(s1, "Mitski");
			assertEquals(2,client.updateCounter);
			Channel c1 = s1.getChannels().get(0);
			client.sendMessageChannel(s1,"Mitski","Hello");
			assertEquals(3,client.updateCounter);
			Message m1 = c1.getMessages().get(0);
			assertEquals(false,m1.isPinned());
			assertEquals("Hello",c1.getMessages().get(0).getText());
			client.pinMessage(s1, c1, m1);
			assertEquals(4,client.updateCounter);
			assertEquals(true,m1.isPinned());
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void kickUserTest()
	{
		ArrayList <User> userCheck = new ArrayList <User>();
	
	
		
		//Should do nothing user1 doesn't have permission
		userCheck.add(user1);
		userCheck.add(user2);
		//userCheck.add(user3);
		
		try
		{
			rc.joinServer(user1.getUniqueID(), s1.getId());
			rc.joinServer(user2.getUniqueID(), s1.getId());
			//client.kickUser(s1,user2); //user 1 is account tied to Client so u1 kicking u2
			assertEquals(2,rc.findServer(1).getUserCount());
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
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	@Test 
	void setters()
	{
		try
		{
			client.setPassword("Cowboy");
			assertEquals("Cowboy",user1.getPassword());
			client.setRealName("Mitsuki Laycock");
			assertEquals("Mitsuki Laycock",user1.getRealName());
			client.setUsername("Mitski");
			assertEquals("Mitski",user1.getUsername());
			client.setUserBio("be the cowboy");
			assertEquals("be the cowboy",user1.getUserBio());
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test 
	void AssignRoles()
	{
		try
		{
			rc.joinServer(user1.getUniqueID(), s1.getId());
			rc.joinServer(user2.getUniqueID(), s1.getId());
			//Client isn't admin/Moderator so shouldn't do anything
			client.AssignAdmin(user2, s1);
			assertEquals(0,client.updateCounter);
			assertEquals("Default",s1.getUserRole(user2));
			client.AssignModerator(user2, s1);
			assertEquals("Default",s1.getUserRole(user2));
			
			client.createServer("Guitar");
			//rc.findServer(3).addObserver(client);
			//assertEquals(2,client.updateCounter);
			System.out.println(rc.findServer(3).getObservers().size());
			assertEquals("Guitar", rc.findServer(3).getName());
			
			client.AssignAdmin(user2,rc.findServer(3));
	
			assertEquals("Admin",rc.findServer(3).getUserRole(user1));
			assertEquals("Admin",rc.findServer(3).getUserRole(user2));
			
			//Admin can assign Moderator
			client.AssignModerator(user3,rc.findServer(3));
			assertEquals("Admin",rc.findServer(3).getUserRole(user1));
			assertEquals("Moderator",rc.findServer(3).getUserRole(user3));
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test 
	void lockChannelTest()
	{
		try
		{
			client.joinServer(s1);
			assertEquals(1,client.updateCounter);
			client.createChannel(s1, "Florence + the Machine");
			assertEquals(2,client.updateCounter);
			client.setLockChannel(s1.findChannel("Florence + the Machine"), s1);
			assertEquals(false,s1.findChannel("Florence + the Machine").isLocked());
			assertEquals(2,client.updateCounter);
			
			client.createServer("Guitar");
			assertEquals(3,client.updateCounter);
			client.createChannel(rc.findServer(3),"Chords");
			assertEquals(3,client.updateCounter);
			client.setLockChannel(rc.findServer(3).findChannel("Chords"), rc.findServer(3));
			assertEquals(3,client.updateCounter);
			assertEquals(true, rc.findServer(3).findChannel("Chords").isLocked());
			
			
		
		} catch (RemoteException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		
	}
	@Test
	void ServerDescription()
	{
		try
		{
			
			client.joinServer(s1);
			client.changeServerDescription(s1,"Women of Indie music");
			assertEquals(null,s1.getDescription());
			
			client.createServer("Guitar");
			client.changeServerDescription(rc.findServer(3),"Everything about guitar");
			assertEquals("Everything about guitar",rc.findServer(3).getDescription());			
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	void ThemeTest()
	{
		try
		{
			client.createTheme("CoffeeBeans", path);
			assertEquals(1,user1.getThemes().size());
			assertEquals("CoffeeBeans",user1.getThemes().get(0).getThemeName());
			assertEquals(false,user1.getThemes().get(0).isSetTheme);
			client.setToTheme("CoffeeBeans");
			assertEquals(true,user1.getThemes().get(0).isSetTheme);
			client.createTheme("DarkMode", path);
			assertEquals(2,user1.getThemes().size());
			assertEquals("DarkMode",user1.getThemes().get(1).getThemeName());
			assertEquals(false,user1.getThemes().get(1).isSetTheme);
			client.setToTheme("DarkMode");
			assertEquals(true,user1.getThemes().get(1).isSetTheme);
			client.deleteTheme("DarkMode");
			assertEquals(1,user1.getThemes().size());
			
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
