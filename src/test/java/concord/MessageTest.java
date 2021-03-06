package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageTest
{
	String s1; 
	String s2;
	Server server1;
	Message m1 ;
	Message m2 ;
	Message m3 ;
	Channel c1;
	
	@BeforeEach
	void setUp() throws Exception
	{
		 s1 = "hello world";
		 s2 = "world hello";
		 server1 = new Server("world",1);
		 c1 = new Channel("intro",1);
		 m1 = new Message(s1,1,1);
		 m2 = new Message(s2,2,2);
		 m3 = new Message(s1,3,3);
		
	}
	@Test 
	void testConstructor()
	{
		assertEquals(m1,m3.getLinkMessage());
	}
	@Test 
	void testlinkMessage()
	{
		//m1.getLinkMessage();
		
		m1.linkMessage(m2);
		assertEquals(m2, m1.getLinkMessage());
		
	}
	@Test
	void testPin()
	{
	   m1.pinMessage();
	   assertEquals(true,m1.isPinned());
	   m1.unPin();
	   assertEquals(false,m1.isPinned());
	}
	
	
}
