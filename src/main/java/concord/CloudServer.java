package concord;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CloudServer extends Remote
{
	
	public int getNextUserId() throws RemoteException;
	public int getNextServerId()throws RemoteException;
	public ArrayList<User> getUsers()throws RemoteException;
	public ArrayList <Server>  getAllServers()throws RemoteException;
	public User findUser(String username)throws RemoteException;
	public Server findServer(int i)throws RemoteException;
	public User findUser(int id)throws RemoteException;
	public Server findServer(String string)throws RemoteException;
	public boolean verifyPassword(int userID,String username,String pass)throws RemoteException;
	public void storeData()throws RemoteException;
	public void blockUser(int id1,int id2)throws RemoteException;
	public void unBlock(int id1,int id2)throws RemoteException;
	public void joinServer(int userId, int serverId)throws RemoteException;
	public void createServer(int userId, String name)throws RemoteException;
	public void invite(int userId1,int userId2, int serverId)throws RemoteException;
	public void createChannel(int userId,int serverId, String name)throws RemoteException;
	public void leaveServer(int userId,int serverId)throws RemoteException;
	public void kickUser(int userId, int kickedUserId, int serverId)throws RemoteException;
	public void sendMessageChannel(int userId, int serverId, String name,String text) throws RemoteException;
	public void text(Role r, Channel c, Message m )throws RemoteException;
	public void changePassword(int uniqueID, String pass)throws RemoteException;
	public void pinMessage(int userId, int serverId,String name, int messageID) throws RemoteException;
	public int getVisits() throws RemoteException;
	public void AssignAdmin(int userId, int userId2,int serverId) throws RemoteException;
	public void AssignModerator(int userId,int userId2, int serverId) throws RemoteException;
	public void setServerDescription(int userId, int serverId, String text) throws RemoteException;
	public void lockChannel(int userId, int serverId, String name) throws RemoteException;
	public void setRealName(int userId, String text) throws RemoteException;
	public void setUserBio(int userId, String text) throws RemoteException;
	public void setUsername(int userId, String text) throws RemoteException;
	public void notifyUpdated() throws RemoteException;
	public void addObserverServer(Client c, int serverId) throws RemoteException;
	
}
