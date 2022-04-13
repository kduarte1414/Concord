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
	public boolean authentication(String username, String password) {
	//inside TEST you ask for username and password 
		User u1 = Users.get(0);
		account= u1;
		for(User u: Users) {
			if(u.getUsername() == username) {
				account = u;
			}
		}
		return rc.verifyPassword(account.getUniqueID(), username, password);

		
	}
	public void setPassword(String pass) {
		rc.changePassword(account.getUniqueID(),pass);
	}
	
	public void blockUser(User u) {
		int id1 = account.getUniqueID();
		int id2= u.getUniqueID();
		rc.blockUser(id1, id2);	
		
	}
	
	public void unBlock( User u) {
		int id1 = account.getUniqueID();
		int id2= u.getUniqueID();
		rc.unBlock(id1, id2);
		
	}
	
	public void joinServer(Server s) {	
		int userId= account.getUniqueID();
		int serverId = s.getId();
		rc.joinServer(userId,serverId);
		
	}
	
	//come back to this and check it 
	public void createServer( String name) {
		rc.createServer(account.getUniqueID(),name);
	}


	public void text(Role r, Channel c, Message m )
	{
		rc.text(r,c,m);
	}
	
	public void createChannel(Server s, String name)
	{
		rc.createChannel(account.getUniqueID(), s.getId(), name);
	}
	public void DM(String username)
	{	
		
		
	}
	public void leaveServer(Server s)
	{
		rc.leaveServer(account.getUniqueID(), s.getId());
	}
	
	public void kickUser(Server s,User u)
	{
		rc.kickUser(account.getUniqueID(),u.getUniqueID(),s.getId());
	}

	@Override
	public void update() throws RemoteException
	{
		updateCounter++;
	
	}
	
	public void pinMessage(Server s,Channel c, Message m)
	{
		rc.pinMessage(account.getUniqueID(),s,c,m);
	}
	
	public void invite(User invited ,Server s) {
		int id1 = account.getUniqueID();
		int id2 = invited.getUniqueID();
		int sId = s.getId();
		rc.invite(id1,id2,sId);
	}
	
	
	
	
	
	
	
}
