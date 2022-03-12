package concord;
import java.util.ArrayList;


public class Channel
{

	ArrayList <Message> messages; 
	String name;
	boolean locked;
	int serverIn;
	
public Channel(String nm,int s) 
{
		messages = new ArrayList <Message>();
		name = nm;
		serverIn = s; //what server this channel is created in
		locked = false;// default not locked 
		
}

/**
 * @return the messages
 */
public ArrayList<Message> getMessages()
{
	return messages;
}


/**
 * @return the name
 */
public String getName()
{
	return name;
}

/**
 * @param name the name to set
 */
public void setName(String name)
{
	this.name = name;
}

/**
 * @return the serverIn
 */
public int getServerIn()
{
	return serverIn;
}
public void deleteMessage(Message m)
{
	messages.remove(m);
}

public void newMessage(Message m)
{
	messages.add(m);
}

public void replyMessage(Message m1, Message m2){
	m1.linkMessage(m2);
	messages.add(m2);
	
}

public void lockChannel()
{
	locked = true;
	
}
public void unlock()
{
	locked = false;
}
public boolean isLocked()
{
	return locked;
}

}
