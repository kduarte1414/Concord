package concord;
import java.io.Serializable;
import java.util.ArrayList;


public class Channel implements Serializable 
{

	
	private static final long serialVersionUID = -5195145756948582548L;
	
	ArrayList <Message> messages; 
	String name;
	boolean locked;
	int serverIn;
	ArrayList <Message> pinned;
	
public Channel(String nm,int s) 
{
		messages = new ArrayList <Message>();
		name = nm;
		serverIn = s; //what server this channel is created in
		locked = false;// default not locked 
		pinned = new ArrayList <Message>();;
		
}

/**
 * @return the messages
 */
public Message searchMessage(int id ) {
	Message found = messages.get(0);
	for(Message m: messages)
	{
		if(m.id ==id)
		{
			found=m;
		}
	}
	return found;
}

public void newMessage(String text,int userID)
{
	Message newText = new Message(text,userID,messages.size()+1);
	messages.add(newText);

}
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
public void replyMessage(Message m1, String text, int userID){
	
	Message m2 = new Message(text,userID,messages.size()+1);
	messages.add(m2);
	m1.linkMessage(m2);
}
public void pinMessage(Message m1)
{
	m1.pinMessage();
	pinned.add(m1);
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
