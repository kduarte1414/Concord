package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CloudTest
{
	Cloud cloud;
	User u1;
	User u2;
	Server s3;
	
	@BeforeEach
	void setUp() throws Exception
	{
		cloud = new Cloud();
		 u1 = new User("cat","yarnlover",1,"catsrule");
		 u2 = new User("dog","tennisball",2,"dogsarebest");
		 s3 = new Server("lizardcare",3);
	}

	@Test
	void testCreatingUser()
	{
		cloud.createUser("cat", "yarnlover","catsrule");
		cloud.createUser("dog", "tennisball","dogsarebest");
		assertEquals(2,cloud.getUserCount());
		assertEquals(u1.getUniqueID(),cloud.getUsers().get(0).getUniqueID());
		assertEquals(u2.getUniqueID(),cloud.getUsers().get(1).getUniqueID());
		
		
		
	}
	
	@Test 
	void testCreatingServer()
	{
		cloud.createServer("catsRule");
		cloud.createServer("dogRule");
		cloud.createServer("lizardcare");
		
		assertEquals(3,cloud.getServerCount());
		assertEquals(s3.getId(),cloud.getServers().get(2).getId());
		
		
		
	}

}
