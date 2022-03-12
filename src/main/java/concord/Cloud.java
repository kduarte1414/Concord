package concord;

import java.util.ArrayList;

public class Cloud
{
	ArrayList < Server > servers = new ArrayList <Server>();
	ArrayList < User > users = new ArrayList <User>();
	
	int userCounter = 0;
	int serverCounter = 0;
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
	public int getUserCount()
	{
		return userCounter;
	}
	public int getServerCount()
	{
		return serverCounter;
	}

	public void createUser(String name, String un) {
		userCounter= userCounter+1;
		User u = new User(name, un, userCounter);
		users.add(u);
		
	}
	
	public void createServer(String name){
		serverCounter = serverCounter+1;
		Server s = new Server(name, serverCounter);
		servers.add(s);
	}
	
	
	
	
}
