package concord;
import java.io.Serializable;
import java.util.ArrayList;

public class User  implements  Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270789288736559417L;
	String username;
	String realName;
	String password;
	String userBio;
	int uniqueID;
	boolean isOnline;
	
	ArrayList <Server> servers;
	ArrayList <User> blocked; //list of blocked users
	
	public User()
	{
		
	}
	public User(String name, String un, int id , String pass)
	{
		servers = new ArrayList <Server>();
		blocked = new ArrayList <User>();
		
		realName = name;
		username = un;
		uniqueID = id;
		password = pass;
	}
	
	/**
	 * @return the realName
	 */
	public String getRealName()
	{
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	/**
	 * @return the uniqueID
	 */
	public int getUniqueID()
	{
		return uniqueID;
	}

	/**
	 * @param uniqueID the uniqueID to set
	 */
	public void setUniqueID(int uniqueID)
	{
		this.uniqueID = uniqueID;
	}

	/**
	 * @return the isOnline
	 */
	public boolean isOnline()
	{
		return isOnline;
	}

	/**
	 * @param isOnline the isOnline to set
	 */
	public void setOnline(boolean isOnline)
	{
		this.isOnline = isOnline;
	}

	/**
	 * @return the servers
	 */
	public ArrayList<Server> getServers()
	{
		return servers;
	}


	/**
	 * @return the blocked
	 */
	public ArrayList<User> getBlocked()
	{
		return blocked;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @return the userBio
	 */
	public String getUserBio()
	{
		return userBio;
	}


	
	public void setUsername(String un)
	{
		username = un;
	}
	
	public void setPassword(String pass)
	{
		password = pass;
	}
	
	public void setUserBio(String bio)
	{
		userBio = bio;
	}
	
	public void blockUser(User u)
	{
		if(!blocked.contains(u)) 
		{
			blocked.add(u);
		}
		
	}
	public void unBlock(User u)
	{
		
	if(blocked.contains(u)) 
		{
			blocked.remove(u);
		}
	}
	
	public void setStatus(boolean status) 
	{
		isOnline = status;
	}
	
	public void leaveServer(Server s) 
	{
		servers.remove(s);
		s.kickUser(this);
	}
	
	
	public void joinServer(Server s)
	{
		servers.add(s);
		s.addUsers(this);
		
	}
	
	public void CreateServer(Server s)
	{
		
		servers.add(s);
		s.addUsers(this);
		s.assignAdmin(this); //all permissions to creator
		
	}
	public void CreateServer(String name, int id)
	{
		Server s = new Server(name,id);
		servers.add(s);
		s.addUsers(this);
		s.assignAdmin(this); //all permissions to creator
		
	}
	
	public void CreateChannel(Server s, String name)
	{
		if(hasUser(s)) {
			Channel c = new Channel(name,s.getId());
			s.addChannel(c);
		}
	}
	
	public void pinMessage(Channel c, Message m1) {
		c.pinMessage(m1);
	}
	
	public void invite(Server s, User u) {
		if(servers.contains(s)) {
			s.addUsers(u);
		}
	}
	
	public boolean hasUser(Server s)
	{
		boolean found = false;

		for(User u: s.getUsers())
		{
			if(u.getUniqueID()==uniqueID)
			{
				found = true;
			}
		}
		return found;
	}
	
}

