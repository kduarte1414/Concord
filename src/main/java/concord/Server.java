package concord;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements Serializable, Observed
{
	
 
	private static final long serialVersionUID = -8873450771650362307L;
	
	ArrayList <User> users;
	ArrayList <Channel> channels;
	HashMap<User,Role>roles;
	int id;
	int userCount;
	String name;
	String description;
	ArrayList <Observer> observers;

	public Server()
	{
		
	}
	public Server(String nm, int id) {
		
		this.name = nm;
		users = new ArrayList <User>();
		channels = new ArrayList <Channel>();
		roles = new HashMap <User,Role>();
		observers = new ArrayList <Observer>();
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
	public Channel findChannel(String name)
	{
		Channel found = channels.get(0);
		for(Channel c: channels)
		{
			if(c.getName().equals(name))
			{
				found = c;
			}
		}
		return found;
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
		update();
	}
	
	public void addChannel(Channel c) 
	{

		channels.add(c);
		update();
				
	}
	public boolean channelAlreadyExist(String name)
	{
		boolean found =false;
		for(Channel c: channels)
		{
			if(c.getName().equals(name))
			{
				found= true;
			}
		}
		return found;
	}
	public void deleteChannel(Channel c) 
	{
		if(channels.contains(c)){
			channels.remove(c);
			update();
		}
	}
	
	public void addUsers(User u) 
	{
		//make sure no duplicate users 
		if(!users.contains(u)) {
			users.add(u);
			Default basic = new Default(this);
			roles.put(u,basic);
			update();
		}
	}
	
	public void kickUser(User u) 
	{
		users.remove(u);
		roles.remove(u);
		update();
	}
	public void lockChannel(Channel c)
	{
		if(channels.contains(c)){
			c.lockChannel();
			update();
		}
	}
	public void assignModerator(User u)
	{
		Moderator mod = new Moderator(this);
		if(roles.containsKey(u)) {
			roles.replace(u, mod);
			update();
		}
		else //assuming you can just add a user to be a moderator
		{
			roles.put(u, mod);
			users.add(u);
			update();
		}
		
	}
	
	public void assignAdmin(User u)
	{
		Admin adm = new Admin(this);
		if(roles.containsKey(u)){
			roles.replace(u,adm);
			update();
		}
		else //assuming you can just add a user to be an Admin 
			//they don't have to exist yet
		{
			roles.put(u, adm);
			users.add(u);
			update();
		}
	}
	public String getUserRole(User u)
	{
		User found = users.get(0);
		
		for(User u1: users)
		{
			if(u1.getUsername().equals(u.getUsername()))
			{
				found = u1;
			}
		}
		
		return roles.get(found).getRoleName();
	}
	public Role getUserRoleObject(User u)
	{
		return roles.get(u);
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
	/**
	 * @return the observers
	 */
	public ArrayList<Observer> getObservers()
	{
		return observers;
	}
	/**
	 * @param observers the observers to set
	 */
	public void setObservers(ArrayList<Observer> observers)
	{
		this.observers = observers;
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
