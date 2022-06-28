package concord;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
//import java.util.ArrayList;



public class Client extends UnicastRemoteObject implements Observer, Serializable 
{

	
	private static final long serialVersionUID = -7077120925444928086L;
	
	User account;
	CloudServer rc;
	ArrayList <User> Users;
	int updateCounter = 0;

	public Client(CloudServer c) throws RemoteException
	{
		rc = c;
		Users = c.getUsers();
	}
	
	public User getAccount()
	{
		return account;
	}
	public boolean authentication(String username, String password) throws RemoteException {
		User u1 = null;
		account= u1;
		for(User u: Users) {
			if(u.getUsername().equals(username)) {
				account = u;
				//System.out.println("account found");
			}
		}
		return rc.verifyPassword(account.getUniqueID(), username, password);

	}
	public void setPassword(String pass) throws RemoteException {
		rc.changePassword(account.getUniqueID(),pass);
	}
	
	public void blockUser(User u) throws RemoteException {
		int id1 = account.getUniqueID();
		int id2= u.getUniqueID();
		rc.blockUser(id1, id2);	
		
	}
	
	public void unBlock(User u) throws RemoteException {
		int id1 = account.getUniqueID();
		int id2= u.getUniqueID();
		rc.unBlock(id1, id2);
		
	}
	
	public void joinServer(Server s) throws RemoteException {	
		int userId= account.getUniqueID();
		int serverId = s.getId();
		s.addObserver(this);
		rc.joinServer(userId,serverId);
	}
	public void createServer(String name) throws RemoteException {
		rc.createServer(account.getUniqueID(),name);
		//TODO figure out why I can't add observer
		//rc.addObserverServer(this, rc.getNextServerId()-1);
		
	}
	public void text(Role r, Channel c, Message m ) throws RemoteException
	{
		rc.text(r,c,m);
		c.getServerIn();
		//rc.findServer(c.getServerIn()).addObserver(this);
	}
	
	public void createChannel(Server s, String name) throws RemoteException
	{
		rc.createChannel(account.getUniqueID(), s.getId(), name);
	}
	public void leaveServer(Server s) throws RemoteException
	{
		rc.leaveServer(account.getUniqueID(), s.getId());
	}
	
	public void kickUser(Server s,User u) throws RemoteException
	{
		rc.kickUser(account.getUniqueID(),u.getUniqueID(),s.getId());
	}

	public void pinMessage(Server s,Channel c, Message m) throws RemoteException
	{
		rc.pinMessage(account.getUniqueID(),s.getId(),c.getName(),m.getId());
	}
	public void sendMessageChannel(Server s, String name, String text) throws RemoteException
	{
		rc.sendMessageChannel(account.getUniqueID(), s.getId(),name, text);
	}
	
	public void invite(User invited ,Server s) throws RemoteException {
		int id1 = account.getUniqueID();
		int id2 = invited.getUniqueID();
		int sId = s.getId();
		rc.invite(id1,id2,sId);
		
	}
	public void setUserBio(String bio) throws RemoteException
	{
		rc.setUserBio(account.getUniqueID(),bio);
	}
	public void setRealName(String name) throws RemoteException
	{
		rc.setRealName(account.getUniqueID(), name);
	}
	public void setLockChannel(Channel c, Server s) throws RemoteException
	{
		rc.lockChannel(account.getUniqueID(),s.getId(),c.getName());
	}
	public void setUsername(String username)throws RemoteException
	{
		rc.setUsername(account.getUniqueID(), username);
	}
	public void AssignAdmin(User u2, Server s1) throws RemoteException
	{
		rc.AssignAdmin(account.getUniqueID(),u2.getUniqueID(),s1.getId());
	}
	
	public void AssignModerator(User u2, Server s1) throws RemoteException
	{
		rc.AssignModerator(account.getUniqueID(),u2.getUniqueID(),s1.getId());
	}
	
	public void changeServerDescription(Server s, String description) throws RemoteException
	{
		rc.setServerDescription(account.uniqueID,s.getId(),description);
	}
	public void createTheme(String name, String path) throws RemoteException
	{
		rc.createTheme(account.uniqueID,name,path);
		
	}
	public void createDM(User u) throws RemoteException
	{
		rc.createDM(account.getUniqueID(),u.getUniqueID());
	}
	public void deleteTheme(String name) throws RemoteException
	{
		rc.deleteTheme(account.uniqueID,name);
	}
	public void editTheme(String name) throws RemoteException
	{
		rc.editTheme(account.uniqueID,name);
	}
	public void setToTheme(String name) throws RemoteException
	{
		rc.setToTheme(account.uniqueID,name);
	}
	@Override
	public void update() throws RemoteException
	{
		updateCounter++;
	
	}

	public void sendDMmessage(DirectMessage dmSelected, String text) throws RemoteException
	{
		rc.sendDmMessage(account.getUniqueID(),dmSelected,text);
		
	}
	
	
}
