package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rmiObserver.RMIObserved;


class realCloudTest
{
	
	CloudServer cs;
	
	static Registry registry;
	
	static Cloud cloud;
	static realCloud rc;
	User user1;
	User user2;
	User user3;
	User user4;
	
	Server s1;
	Server s2;
	
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
		cloud.createUser("Eren Y", "RumblingKing","foundingtitan");
		cloud.createUser("Sasha ", "PotatoGirl","imhungry");
		cloud.createUser("Connie ", "imBald","titanmom");
		cloud.createUser("Pieck", "cartTitan","prettyhuman");
		
		cloud.createServer("ODM Gear");
		cloud.createServer("Soups");
		
		cs = new realCloud(cloud);
		
		user1 = cs.findUser("RumblingKing");
		user2 = cs.findUser("PotatoGirl");
		user3 = cs.findUser("cartTitan");
		user4 = cs.findUser("imBald");
		
		s1 = cs.findServer(1);
		s2 = cs.findServer(2);
			
	}
	@Test
	void verifyPass()
	{
		Boolean verify = false;
			
		try
		{
			String [] names = registry.list();
			for(String name:names) {
				System.out.println("name"+name);
			}
			//Observed observed = (Observed) Naming.lookup("rmi://127.0.0.1/RCLOUD");
			cs = (CloudServer) registry.lookup("RMI://127.0.0.1/RCLOUD");
			verify= cs.verifyPassword(user1.getUniqueID(),"RumblingKing","foundingtitan");
				
		} catch ( RemoteException | NotBoundException e)
		{
			fail("Bad call to registry");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(true,verify);
		assertEquals(1,cs.getVisits());
		
		
	}
	/*
	
	@Test
	void verifyPasswordTest()
	{
		
		assertEquals(true, cs.verifyPassword(user1.getUniqueID(),"RumblingKing","foundingtitan"));
		assertEquals(false, cs.verifyPassword(user2.getUniqueID(),"PotatoGirl","grub"));
		
		
		
	}
	@Test
	void findTest()
	{
		assertEquals(1,cs.findUser("RumblingKing").getUniqueID());
		assertEquals(user1,cs.findUser(1));
		assertEquals(cloud.getServers().get(1),cs.findServer(2));
	}

	@Test
	void blockUserTest()
	{
		ArrayList <User> checkBlocked = new ArrayList <User>();
		assertEquals(checkBlocked,user1.getBlocked());
		cs.blockUser(user1.getUniqueID(), user3.getUniqueID());
		checkBlocked.add(user3);
		assertEquals(checkBlocked,user1.getBlocked());
		
		//Testing unblock
		cs.unBlock(user1.getUniqueID(),user3.getUniqueID());
		checkBlocked.remove(user3);
		assertEquals(checkBlocked,user1.getBlocked());
	}
	
	@Test
	void kickUserTest()
	{
		ArrayList <User> userCheck = new ArrayList <User>();
		
		user1.joinServer(s1);
		user2.joinServer(s1);
		user3.joinServer(s1);
		user4.joinServer(s1);
		
		//Should do nothing user1 doesn't have permission
		userCheck.add(user1);
		userCheck.add(user2);
		userCheck.add(user3);
		userCheck.add(user4);
		cs.kickUser(user1.getUniqueID(), user2.getUniqueID(),s1.getId());
		assertEquals(userCheck,s1.getUsers());
		assertEquals(true, s1.getRoles().containsKey(user2));
		
		//should remove from list
		s1.assignAdmin(user1);
		cs.kickUser(user1.getUniqueID(),user2.getUniqueID(),1);
		userCheck.remove(user2);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(false, s1.getRoles().containsKey(user2));
		
		//should remove from list
		s1.assignModerator(user3);
		cs.kickUser(user3.getUniqueID(),user4.getUniqueID(),1);
		userCheck.remove(user4);
		assertEquals(userCheck,s1.getUsers());
		assertEquals(false, s1.getRoles().containsKey(user4));
			
	}
	@Test
	void joinServerTest()
	{
		ArrayList <User> userCheck = new ArrayList <User>();
		userCheck.add(user1);
		userCheck.add(user2);
		
		Server s = cs.findServer(1);
		cs.joinServer(user1.getUniqueID(), 1);
		cs.joinServer(user2.getUniqueID(), 1);
		assertEquals(userCheck,s.getUsers());
		
	}
	@Test
	void createServerTest()
	{
		ArrayList<Server> serverCheck = new ArrayList <Server>();
		
		cs.createServer(user3.getUniqueID(), "masterCrawling");
		Server s = cs.findServer(3); 
		serverCheck.add(s);
		assertEquals(serverCheck,user3.getServers());
		assertEquals("Admin", s.getUserRole(user3));
		assertEquals(true,cloud.getServers().contains(s));
		
	}
	
	@Test
	void leaveServerTest()
	{
		ArrayList<Server> serverCheck = new ArrayList <Server>();
		user3.joinServer(s1);
		user3.joinServer(s2);
		
		serverCheck.add(s1);
		serverCheck.add(s2);
		
		cs.leaveServer(user3.uniqueID, s1.getId());
		serverCheck.remove(s1);
		assertEquals(serverCheck,user3.getServers());
		
	}

	@Test
	void inviteTest()
	{
	
		user1.joinServer(s1);
		cs.invite(user1.uniqueID, user2.getUniqueID(), 1);
		assertEquals(true,s1.getUsers().contains(user2));
		
		//someone who isn't in server can't invite 
		cs.invite(user4.uniqueID, user2.getUniqueID(), 2);
		assertEquals(false,s2.getUsers().contains(user2));
		
	}
	@Test
	void createChannelTest()
	{
		user1.joinServer(s1);
		cs.createChannel(user1.getUniqueID(),s1.getId(), "fuel management");
		assertEquals(1,s1.getChannels().size());
		cs.createChannel(user4.getUniqueID(),1, "blades");
		//shouldn't add channel person isn't even in server
		assertEquals(1,s1.getChannels().size());
		assertEquals("fuel management",s1.getChannels().get(0).getName());
		
	}
	@Test 
	void pinMessage() 
	{
		user1.joinServer(s1);
		Message m1 = new Message("hello",1);
		Channel c1 = new Channel("fuel management",1);
		assertEquals(false,m1.isPinned());
		cs.pinMessage(user1.getUniqueID(), s1, c1, m1);
		assertEquals(true,m1.isPinned());
	}
	
	@Test
	void setPassword()
	{
		 
		cs.changePassword(user2.getUniqueID(),"potatoes4life");
		assertEquals("potatoes4life",user2.getPassword());
	
	}
	
	*/
	
	@AfterEach
	void tearDown() throws Exception
	{
		registry.unbind("RCLOUD");
	}



}
