package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectMessageTest
{
	Cloud cloud;
	User u;
	User u2;
	
	DirectMessage dm;
	DirectMessage dm2;
	
	Message m1;
	Message m2;
	
	@BeforeEach
	void setUp() throws Exception
	{
		cloud = new Cloud();
		cloud.createUser("Kat Duarte","xXkatDuaXx","alaskanmalamute");
		cloud.createUser("liz Chavez","LavendarTown","Lykkeli");
		u = cloud.getUser("xXkatDuaXx");
		u2 = cloud.getUser("LavendarTown");
		
	
		
	}
	@Test
	void test()
	{
		u.createDM(u2);
		assertEquals(u2.getDms().get(0),u.getDms().get(0));
		dm = u.getDms().get(0);
		cloud.addDM(dm);
		assertEquals(1,cloud.getDmCount());
		dm2 = u2.getDms().get(0);
		
		cloud.addDM(dm2);
		//this dm already exist shouldn't add anything
		assertEquals(1,cloud.getDmCount());
	}
	@Test
	void Messaging()
	{
		dm = u.getDms().get(0);
		m1= new Message("hey how are you?",1,1);
		m2= new Message("hey! im cool",2,2);
		dm.replyMessage(m1, m2);
		
		
	}

}
