package concord;

import java.util.ArrayList;

public interface CloudServer
{
	
	public int getNextUserId();
	public int getNextServerId();
	public ArrayList<User> getUsers();
	public boolean verifyPassword(int userID,String username,String pass);
	public void storeData();
	public void blockUser(int id1,int id2);
	public void unBlock(int id1,int id2);
	public void joinServer(int userId, int serverId);
	public void createServer(int userId, String name);
	public void invite(int userId1,int userId2, int serverId);
	public void createChannel(int userId,int serverId, String name);
	public void leaveServer(int userId,int serverId);
	public void kickUser(int userId, int kickedUserId, int serverId);
	//TODO checkTexting and DMing
	public void text(Role r, Channel c, Message m );
	public User findUser(String username);
	public Server findServer(int i);
	public User findUser(int id);
	public Server findServer(String string);
	public void changePassword(int uniqueID, String pass);
	public void pinMessage(int uniqueID, Server s, Channel c, Message m);
	public int getVisits();
	
	
	
	
	
}
