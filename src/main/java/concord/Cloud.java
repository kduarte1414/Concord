package concord;

import java.io.Serializable;
import java.util.ArrayList;

public class Cloud implements Serializable 
{
	
	private static final long serialVersionUID = -6057569205537813651L;
	
	ArrayList < Server > servers = new ArrayList <Server>();
	ArrayList < User > users = new ArrayList <User>();
	ArrayList <DirectMessage> dms = new ArrayList<DirectMessage>();
	
	int userCounter = 0;
	int serverCounter = 0;
	int dmCounter =0;
	/**
	 * @return the servers
	 */
	public ArrayList<Server> getServers()
	{
		return servers;
	}


	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public ArrayList<DirectMessage> getDMs()
	{
		return dms;
	}
	public int getUserCount()
	{
		return userCounter;
	}
	public int getServerCount()
	{
		return serverCounter;
	}
	public int getDmCount()
	{
		return dmCounter;
	}
	public void setServers( ArrayList<Server> sers )
	{
		servers = sers;
	}
	public  void setUsers(ArrayList<User> us)
	{
		users= us;
	}
	public void setDms(ArrayList <DirectMessage> directMessages)
	{
		dms = directMessages;
	}
	

	public void createUser(String name, String un, String password) {
		if(!usernameExists(un)) 
		{
			userCounter= userCounter+1;
			User u = new User(name, un, userCounter, password);
			users.add(u);
		}
		
	}
	
	public void createServer(String name){
		if(!serverExists(name)) 
		{
			serverCounter = serverCounter+1;
			Server s = new Server(name, serverCounter);
			servers.add(s);
		}
	}
	public void addDM(DirectMessage dm){
		if(!DmExist(dm)) 
		{
			dmCounter = dmCounter+1;
			dms.add(dm);
			
		}
	}
	public boolean DmExist(DirectMessage dm2)
	{
		boolean found= false;
		for(DirectMessage dm: dms)
		{
			if(dm.userIds().get(0)== dm2.userIds().get(0)&&dm.userIds().get(1)== dm2.userIds().get(1))
			{
				found=true;
			}
		}
		return found;
	}
	
	
	public User getUser(String username)
	{
	
		User found= users.get(0);
		for(User u: users) {
			if(u.getUsername()== username) {
				found = u;
			}
		}
		return found;
	}
	public Server getServer(String name)
	{
	
		Server found= servers.get(0);
		for(Server s: servers) {
			if(s.getName()== name) {
				found = s;
			}
		}
		return found;
	}
	
	public boolean usernameExists(String name)
	{
		boolean found= false;
		
		for(User u: users) {
			if(u.getUsername()== name) {
				found = true;
			}
		}
		return found;
	}
	
	public boolean serverExists(String name)
	{
		boolean found= false;
		
		for(Server s: servers) {
			if(s.getName()== name) {
				found = true;
			}
		}
		return found;
	}


	public void clearData()
	{
		servers.clear();
		users.clear();
		userCounter = 0;
		serverCounter = 0;
		
		
		
	}


	public void addServer(Server s)
	{
		
			serverCounter = serverCounter+1;
			servers.add(s);
	}
	
	
	
}
