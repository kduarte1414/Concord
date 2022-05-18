package concord;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class DirectMessage implements Serializable, Observed
	
{
	private static final long serialVersionUID = 71296056621362836L;
	ArrayList<User> users;
	ArrayList <Message> messages;
	ArrayList <Observer> observers;
	ArrayList <Message> pinned;
	int id;
	
	public DirectMessage(User u)
	{
		users = new ArrayList<User>();
		messages = new ArrayList <Message>();
		observers = new ArrayList <Observer>();
		pinned = new ArrayList <Message>();
		addUser(u);
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public void addUser(User u)
	{
		users.add(u);
	}
	public ArrayList<Message> getMessages()
	{
		return messages;
	}
	public void addMessage(Message m)
	{
		messages.add(m);
	}
	public void replyMessage(Message m1, Message m2){
		m1.linkMessage(m2);
		messages.add(m2);
		update();
		
	}
	public void replyMessage(Message m1, String text, int userID){
		
		Message m2 = new Message(text,userID,messages.size()+1);
		messages.add(m2);
		m1.linkMessage(m2);
		update();
	}
	public void pinMessage(Message m1)
	{
		m1.pinMessage();
		pinned.add(m1);
		update();
		
	}
	public ArrayList<Integer> userIds()
	{
		ArrayList<Integer>userIds = new ArrayList<Integer>();
		for(User u: users)
		{
			userIds.add(u.getUniqueID());
		}
		return userIds;
	}
	
	@Override
	public void addObserver(Observer o) throws RemoteException
	{
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) throws RemoteException
	{
		observers.remove(o);
		
	}
	public void update()
	{
		for(Observer o: observers)
		{
			try
			{
				o.update();
				
			} catch (RemoteException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}
}
