package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
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
		
		
		
		user1 = rc.findUser("RumblingKing");
		user2 = rc.findUser("PotatoGirl");
		user3 = rc.findUser("cartTitan");
		user4 = rc.findUser("imBald");
		
		s1 = rc.findServer(1);
		s2 = rc.findServer(2);
		rc.visits =0;
			
	}
	
	
	@Test
	void verifyPass()
	{
		Boolean verify = false;
			
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			verify = cs.verifyPassword(user1.getUniqueID(),"RumblingKing","foundingtitan");
			assertEquals(true,verify);
			assertEquals(false, cs.verifyPassword(user2.getUniqueID(),"PotatoGirl","grub"));
			assertEquals(4,cs.getVisits());
				
		} catch ( RemoteException | NotBoundException e)
		{
			fail("Bad call to registry");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			fail("Bad call to registry");
			e.printStackTrace();
		}		
		
	}
	
	
	
	@Test
	void findTest()
	{
			try
			{
				cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
				assertEquals(1,cs.findUser("RumblingKing").getUniqueID());
				assertEquals(user1.getUsername(), cs.findUser(1).getUsername());
				assertEquals(user1.getPassword(), cs.findUser(1).getPassword());
				assertEquals(s2.getName(),cs.findServer(2).getName());
				assertEquals(cloud.getServers().get(1).getName(),cs.findServer(2).getName());
				assertEquals(5,cs.getVisits());
			
			}  catch (MalformedURLException | RemoteException | NotBoundException e)
			{
				e.printStackTrace();
				fail("I failed");
			}
		
		
		
	}
	
	@Test
	void blockUserTest()
	{
		ArrayList <User> checkBlocked = new ArrayList <User>();
		try
			{
				cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
				assertEquals(checkBlocked,user1.getBlocked());
				cs.blockUser(user1.getUniqueID(), user3.getUniqueID());
				checkBlocked.add(user3);
				assertEquals(checkBlocked,user1.getBlocked());
				//Testing unblock
				cs.unBlock(user1.getUniqueID(),user3.getUniqueID());
				checkBlocked.remove(user3);
				assertEquals(checkBlocked,user1.getBlocked());
		}  catch (MalformedURLException | RemoteException | NotBoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("I failed");
			}
		
	}
	
	//SUCCES UP TO HERE 
	
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
		
		
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
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
			
		} catch (RemoteException | MalformedURLException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	
	
	@Test
	void joinServerTest()
	{
		ArrayList <User> userCheck = new ArrayList <User>();
		userCheck.add(user1);
		userCheck.add(user2);
		
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			cs.joinServer(user1.getUniqueID(), 1);
			cs.joinServer(user2.getUniqueID(), 1);
			assertEquals(userCheck.size(),cs.findServer(1).getUserCount());
			assertEquals(userCheck.get(0).getRealName(),cs.findServer(1).getUsers().get(0).getRealName());
			assertEquals(userCheck.get(1).getRealName(),cs.findServer(1).getUsers().get(1).getRealName());
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void createServerTest()
	{
		
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			cs.createServer(user3.getUniqueID(), "masterCrawling");
			assertEquals( "masterCrawling",user3.getServers().get(0).getName());
			assertEquals("Admin",user3.getServers().get(0).getUserRole(user3) );
			assertEquals("masterCrawling",cloud.getServers().get(2).getName());
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void leaveServerTest()
	{
		ArrayList<Server> serverCheck = new ArrayList <Server>();
		user3.joinServer(s1);
		user3.joinServer(s2);
		
		serverCheck.add(s1);
		serverCheck.add(s2);
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			cs.leaveServer(user3.uniqueID, s1.getId());
			serverCheck.remove(s1);
			assertEquals(serverCheck,user3.getServers());
			
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void inviteTest()
	{
	
		user1.joinServer(s1);
		
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			cs.invite(user1.uniqueID, user2.getUniqueID(), 1);
			assertEquals(true,s1.getUsers().contains(user2));
			
			//someone who isn't in server can't invite 
			cs.invite(user4.uniqueID, user2.getUniqueID(), 2);
			assertEquals(false,s2.getUsers().contains(user2));
		} catch (MalformedURLException | RemoteException | NotBoundException e)
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
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			cs.joinServer(user1.getUniqueID(), 1);
			cs.createChannel(user1.getUniqueID(),s1.getId(), "fuel management");
			assertEquals(1,s1.getChannels().size());
			
			cs.createChannel(user4.getUniqueID(),1, "blades");
			//shouldn't add channel person isn't even in server
			assertEquals(1,s1.getChannels().size());
			assertEquals("fuel management",s1.getChannels().get(0).getName());
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	@Test 
	void MessageFunctions() 
	{
		try
		{
			
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			user1.joinServer(s1);
			cs.createChannel(user1.getUniqueID(),s1.getId(), "fuel management");
			assertEquals(1,s1.getChannels().size());
			
			cs.sendMessageChannel(user1.getUniqueID(),s1.getId(),"fuel management","Hello all");
			//after sending messages Messages in channel now updated to 1
			assertEquals(1,s1.getChannels().get(0).getMessages().size());
			assertEquals("Hello all",s1.getChannels().get(0).getMessages().get(0).getText());
			
			//Checking pinned
			assertEquals(false,s1.getChannels().get(0).getMessages().get(0).isPinned());
			cs.pinMessage(user1.getUniqueID(), s1.getId(),"fuel management" ,s1.getChannels().get(0).getMessages().get(0).getId());
			assertEquals(true,s1.getChannels().get(0).getMessages().get(0).isPinned());
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void AssignAdminTest()
	{
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			
			//When user creates a server they are Admin
			cs.createServer(user1.getUniqueID(),"Titans");
			
			cs.AssignAdmin(user1.getUniqueID(),user2.getUniqueID(),3);
			assertEquals("Titans",rc.findServer(3).getName());
			assertEquals("Eren Y", rc.findServer(3).getUsers().get(0).getRealName());
			
			//Both should be Admin
			assertEquals("Admin",cs.findServer(3).getUserRole(user1));
			assertEquals("Admin",cs.findServer(3).getUserRole(user2));
			
			//Shouldn't be able to assign Admin because user 1 is default 
			cs.joinServer(1,1);
			cs.joinServer(2,1);
			cs.AssignAdmin(user1.getUniqueID(), user2.getUniqueID(), 1);
			assertEquals("Default",rc.findServer(1).getUserRole(user1));
			assertEquals("Default",rc.findServer(1).getUserRole(user2));
			
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void AssignModeratorTest()
	{
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			//When user creates a server they are Admin
			cs.createServer(user1.getUniqueID(),"Titans");
			cs.AssignModerator(user1.getUniqueID(),user2.getUniqueID(),3);
			assertEquals("Admin",cs.findServer(3).getUserRole(user1));
			assertEquals("Moderator",cs.findServer(3).getUserRole(user2));
			
			//Moderator can assign moderator 
			cs.AssignModerator(user2.getUniqueID(), user3.getUniqueID(), 3);
			assertEquals("Moderator",cs.findServer(3).getUserRole(user2));
			assertEquals("Moderator",cs.findServer(3).getUserRole(user3));
			
			//Shouldn't be able to assign Moderator because user 1 is default 
			cs.joinServer(1,1);
			cs.joinServer(2,1);
			cs.AssignModerator(user1.getUniqueID(), user2.getUniqueID(), 1);
			assertEquals("Default",rc.findServer(1).getUserRole(user1));
			assertEquals("Default",rc.findServer(1).getUserRole(user2));	
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void setDescriptionTest()
	{
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			//When user creates a server they are Admin
			cs.createServer(user1.getUniqueID(),"Titans");
			cs.AssignModerator(user1.getUniqueID(),user2.getUniqueID(),3);
			assertEquals("Admin",cs.findServer(3).getUserRole(user1));
			assertEquals("Moderator",cs.findServer(3).getUserRole(user2));
			
			//Admin and Mdoerator can change descriptions in Servers
			cs.setServerDescription(1,3,"Everything you need to now baout every kind of titan. Abnormals and shifters as well.");
			assertEquals("Everything you need to now baout every kind of titan. Abnormals and shifters as well.",cs.findServer(3).getDescription());
			cs.setServerDescription(2, 3,"Titans suck");
			assertEquals("Titans suck",cs.findServer(3).getDescription());
			
			//Shouldn't be able to set description because user 1 is default 
			cs.joinServer(1,1);
			cs.setServerDescription(1,1,"Learn how to use gear");
			assertEquals(null,cs.findServer(1).getDescription());
	
		
		} catch (MalformedURLException | RemoteException | NotBoundException e)
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
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			//When user creates a server they are Admin
			cs.createServer(user1.getUniqueID(),"Titans");
			cs.AssignModerator(user1.getUniqueID(),user2.getUniqueID(),3);
			assertEquals("Admin",cs.findServer(3).getUserRole(user1));
			assertEquals("Moderator",cs.findServer(3).getUserRole(user2));
			cs.createChannel(1, 3, "weak spots");
		
			cs.lockChannel(1,3,"weak spots");
			assertEquals(true, cs.findServer(3).getChannels().get(0).isLocked());
			
			//Shouldn't be able to  
			cs.joinServer(1,1);
			cs.createChannel(1,1,"fuel");
			cs.lockChannel(1, 1,"fuel");
			assertEquals(false, cs.findServer(1).getChannels().get(0).isLocked());
	
		
		} catch (MalformedURLException | RemoteException | NotBoundException e)
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
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			cs.setRealName(user2.getUniqueID(),"Sasha Braus");
			assertEquals("Sasha Braus",user2.getRealName());
			
			cs.setUserBio(user2.getUniqueID(),"I love food");
			assertEquals("I love food",user2.getUserBio());
			
			cs.setUsername(user2.getUniqueID(),"GabiSucks");
			assertEquals("GabiSucks",user2.getUsername());
			
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test 
	void testPassword()
	{
		try
		{
			cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
			user1.joinServer(s1);
			cs.changePassword(user2.getUniqueID(),"potatoes4life");
			assertEquals("potatoes4life",user2.getPassword());
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void testUpdating()
	{
		
		
	}
	//XML testing what is going on 
	@Test
	void testXML()
	{
		
		rc.storeData();
		Cloud diskRC =  realCloud.loadFromDisk();
		
	for(int i=0; i<4; i++) {
		assertEquals(rc.getUsers().get(i).getRealName(),diskRC.getUsers().get(i).getRealName());
		assertEquals(rc.getUsers().get(i).getPassword(),diskRC.getUsers().get(i).getPassword());
		assertEquals(rc.getUsers().get(i).getUsername(),diskRC.getUsers().get(i).getUsername());
		assertEquals(rc.getUsers().get(i).getRealName(),diskRC.getUsers().get(i).getRealName());
	}
	for(int i=0; i<2; i++) {
		assertEquals(rc.getAllServers().get(i).getName(),diskRC.getServers().get(i).getName());
		
		}
	}
		
	


}
