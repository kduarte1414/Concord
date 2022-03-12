package concord;
import java.util.ArrayList;
import java.util.HashMap;

public class Server
{
	
  
	ArrayList <User> users;
	ArrayList <Channel> channels;
	HashMap<User,Role>roles;
	int id;
	
	String name;
	String description;
	
	int userCount;
	
	public Server(String nm, int id) {
		
		this.name = nm;
		users = new ArrayList <User>();
		channels = new ArrayList <Channel>();
		roles = new HashMap <User,Role>();
		userCount= users.size();
		this.id = id;
		
		
	}
	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers()
	{
		return users;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * @return the channels
	 */
	public ArrayList<Channel> getChannels()
	{
		return channels;
	}

	/**
	 * @return the roles
	 */
	public HashMap<User, Role> getRoles()
	{
		return roles;
	}
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the userCount
	 */
	public int getUserCount()
	{
		userCount=users.size();
		return userCount;
	}

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
	public void setDescription(String des) 
	{
		description=des;
	}
	
	public void addChannel(Channel c) 
	{
		if(!channels.contains(c)){
			channels.add(c);
		}
	}
	public void deleteChannel(Channel c) 
	{
		if(channels.contains(c)){
			channels.remove(c);
		}
	}
	
	public void addUsers(User u) 
	{
		//make sure no duplicate users 
		if(!users.contains(u)) {
			users.add(u);
			Default basic = new Default(this);
			roles.put(u,basic);
		}
	}
	
	public void kickUser(User u) 
	{
		users.remove(u);
		roles.remove(u);
		
	}
	public void lockChannel(Channel c)
	{
		if(channels.contains(c)){
			c.lockChannel();
		}
	}
	public void assignModerator(User u)
	{
		Moderator mod = new Moderator(this);
		if(roles.containsKey(u)) {
			roles.replace(u, mod);
		}
		else //assuming you can just add a user to be a moderator
		{
			roles.put(u, mod);
			users.add(u);
		}
		
	}
	
	public void assignAdmin(User u)
	{
		Admin adm = new Admin(this);
		if(roles.containsKey(u)){
			roles.replace(u,adm);
		}
		else //assuming you can just add a user to be an Admin 
			//they don't have to exist yet
		{
			roles.put(u, adm);
			users.add(u);
		}
	}
	public String getUserRole(User u)
	{
		return roles.get(u).getRoleName();
	}
	
	
	

	
}
