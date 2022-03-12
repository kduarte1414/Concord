package concord;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChannelTest
{
	Server s1;
	Server s2;
	Channel c1;
	Message m1;
	Message m2;
	Message m3;
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		s1 = new Server("MathStudy",1);
		s2 = new Server("ScienceStudy",2);
		c1 = new Channel("Linear Algebra",1);
		m1 = new Message("hello Linear Algebra world", 1);
		m2 = new Message("Can I get some help with LU decomposition?",2);
		m3 = new Message("I can help, what about it?",4);
	}

	@Test
	void testLock()
	{
		assertEquals(false, c1.isLocked());
		c1.lockChannel();
		assertEquals(true, c1.isLocked());
		c1.unlock();
		assertEquals(false,c1.isLocked());
	}
	@Test 
	void messaging()
	{
		c1.newMessage(m1);
		c1.newMessage(m2);
		c1.replyMessage(m2, m3);
		
		ArrayList<Message> checker= new ArrayList <Message>();
		checker.add(m1);
		checker.add(m2);
		checker.add(m3);
		
		
		assertEquals(checker,c1.getMessages());
		assertEquals(m3,m2.getLinkMessage());
		
		c1.deleteMessage(m1);
		checker.remove(m1);
		assertEquals(checker,c1.getMessages());
		
		//c1.deleteMessage();
	}
}
