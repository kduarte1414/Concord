package concord;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class realCloud extends UnicastRemoteObject implements Observed,CloudServer
{
	
	private static final long serialVersionUID = -5454161469763322610L;

	Cloud cloud;
	
	ArrayList <Observer> observers = new ArrayList <Observer>();
	
	int visits = 0;
	
	public realCloud(Cloud c) throws RemoteException
	{
		cloud = c ;
	}
	
	//returns the user with matching ID
	public User findUser(int id)
	{
		visits++;
		ArrayList <User> users = cloud.getUsers();
		
		User found= users.get(0);
		for(User u: users) {
			if(u.getUniqueID()== id) {
				found = u;
			}
		}
		return found;
		
	}
	//returns user with matching username assuming they are unique 
	public User findUser(String username)
	{
		visits++;
		ArrayList <User> users = cloud.getUsers();
		
		User found= users.get(0);
		for(User u: users) {
			if(u.getUsername()== username) {
				found = u;
			}
		}
		return found;
		
	}
	//Returns a server with matching ID
	public Server findServer(int id)
	{
		visits++;
		ArrayList <Server> servers = cloud.getServers();
		
		Server found= servers.get(0);
		for(Server s: servers) {
			if(s.getId()== id) {
				found = s;
			}
		}
		return found;
	}
	
	//Returns a server with matching name
	public Server findServer(String name)
	{
		visits++;
		ArrayList <Server> servers = cloud.getServers();
		
		Server found= servers.get(0);
		for(Server s: servers) {
			if(s.getName()== name) {
				found = s;
			}
		}
		return found;
	}
	
	@Override
	public int getNextUserId()
	{
		visits++;
		return cloud.getUserCount()+1;
	}
	

	@Override
	public boolean verifyPassword(int userID, String username, String password)
	{
		
		visits++;
		User u = findUser(userID);
		if(u.getUsername().equals(username) && u.getPassword().equals(password)) 
		{
			return true;
			
		}else {
			
			return false;
		}

	}

	@Override
	public int getNextServerId()
	{
		// TODO Auto-generated method stub
		visits++;
		return cloud.getServerCount()+1;
	}
	
	@Override
	public void storeData()
	{
		visits++;
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("realCloud.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File realCloud.xml");
		}
		encoder.writeObject(cloud);
		encoder.close();

	}
	public static Cloud loadFromDisk()
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("realCloud.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File realCloud.xml not found");
		}
		
		Cloud real = (Cloud) decoder.readObject();
		return real;
		
		
	}
	
	//Search Function
	public ArrayList <User> getAllUsers()
	{
		visits++;
		return cloud.getUsers();
	}
	public ArrayList <Server>  getAllServers()
	{
		visits++;
		return cloud.getServers();
	}
	
	public void blockUser(int id1,int id2){
		visits++;
		User blocking = findUser(id1);
		User blocked = findUser(id2);
		blocking.blockUser(blocked);
		
	}
	
	public void unBlock(int id1,int id2) {
		visits++;
		User unblocking =findUser(id1);
		User unblocked = findUser(id2);
		unblocking.unBlock(unblocked);
			
	}
	
	public void joinServer(int userId, int serverId) {	
		visits++;
		Server s = findServer(serverId);
		User u = findUser(userId);
		u.joinServer(s);
	}
	
	//come back to this 
	public void createServer(int userId, String name) {
		visits++;
		User u = findUser(userId);
		Server s = new Server(name,getNextServerId());
		u.CreateServer(s);
		cloud.addServer(s);
		try
		{
			notifyUpdated();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addObserverServer(Client client, int serverId)
	{
		Server s = findServer(serverId);
		
		try
		{
			s.addObserver(client);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void invite(int invites,int invited, int serverId)
	{
		visits++;
		Server s = findServer(serverId);
		User u1 = findUser(invites); //u1 invites
		User u2 = findUser(invited); //u2 is invited
		u1.invite(s,u2);	
	}
	
	
	public void text(Role r, Channel c, Message m)
	{
		visits++;
		r.createNewMessage(c, m);
	}
	
	public void createChannel(int userId,int serverId, String name)
	{
		visits++;
		User u = findUser(userId);
		Server s = findServer(serverId);
		u.CreateChannel(s, name);
	}
	
	public void leaveServer(int userId,int serverId)
	{
		visits++;
		User u = findUser(userId);
		Server s = findServer(serverId);
		u.leaveServer(s);
		
	}
	public void kickUser(int userId, int kickedUserId, int serverId)
	{
		visits++;
		User kicking = findUser(userId);
		User kicked = findUser(kickedUserId);
		Server s = findServer(serverId);
		Role r = s.getUserRoleObject(kicking);
		r.KickUser(kicked);
	}

	@Override
	public void addObserver(Observer o) throws RemoteException
	{
		visits++;
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) throws RemoteException
	{
		visits++;
		observers.remove(o);
		
	}

	@Override
	public ArrayList<User> getUsers()
	{
		visits++;
		return cloud.getUsers();
	}

	@Override
	public void changePassword(int uniqueID, String pass)
	{
		visits++;
		User u= findUser(uniqueID);
		u.setPassword(pass);
		
	}
	
	
	public void pinMessage(int userId, int serverId,String name, int messageID)
	{
		visits++;
		User u = findUser(userId);
		Server s = findServer(serverId);
		Role r = s.getUserRoleObject(u);
		Channel c = s.findChannel(name);
		r.pinMessage(c, c.searchMessage(messageID));
		
	}
	//Sending a message in a channel 
	public void sendMessageChannel(int userId, int serverId, String name,String text)
	{
		
		User u = findUser(userId);
		Server s = findServer(serverId);
		Role r = s.getUserRoleObject(u);
		Channel c = s.findChannel(name);
		r.createNewMessage(c,text,userId);
	}
	@Override
	public int getVisits()
	{
		return visits;
	}
	public void setVisits(int num)
	{
		visits=num;
	}
	
	public void clearData()
	{
		cloud.clearData();
		visits=0;
		
	}

	@Override
	public void AssignAdmin(int userId, int userId2, int serverId) throws RemoteException
	{
		// TODO Auto-generated method stub
		User u = findUser(userId);
		User u2 = findUser(userId);
		Server s = findServer(serverId);
		Role r = s.getUserRoleObject(u);
		r.AssignAdmin(u2);
		//notifyUpdated();
		
		
	}

	@Override
	public void AssignModerator(int userId, int userId2, int serverId) throws RemoteException
	{
		// TODO Auto-generated method stub
		User u = findUser(userId);
		User u2 = findUser(userId2);
		Server s = findServer(serverId);
		Role r = s.getUserRoleObject(u);
		r.AssignModerator(u2);
		
	}

	@Override
	public void setServerDescription(int userId, int serverId, String text) throws RemoteException
	{
		User u = findUser(userId);
		Server s = findServer(serverId);
		Role r = s.getUserRoleObject(u);
		r.ChangeServerDescription(text);
		
	}

	@Override
	public void lockChannel(int userId, int serverId, String name) throws RemoteException
	{
		User u = findUser(userId);
		Server s = findServer(serverId);
		Role r = s.getUserRoleObject(u);
		Channel c = s.findChannel(name);
		r.blockChannel(c);
	}
	
	public void setUserBio(int userId, String bio)
	{
		User u = findUser(userId);
		u.setUserBio(bio);
	}

	public void setRealName(int userId, String name)
	{
		User u = findUser(userId);
		u.setRealName(name);
	}
	public void setUsername(int userId, String username)
	{
		User u = findUser(userId);
		u.setUsername(username);
	}
	
	//Everyone is updated
	public void notifyUpdated() throws RemoteException
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
