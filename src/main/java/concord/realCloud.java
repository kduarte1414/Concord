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
		
		return cloud.getUserCount()+1;
	}
	

	@Override
	public boolean verifyPassword(int userID, String username, String password)
	{
		// TODO Auto-generated method stub
		visits++;
		User u = findUser(userID);
		if(u.getUsername()== username && u.getPassword()==password) {
			return true;
		}else {
			return false;
		}

	}

	@Override
	public int getNextServerId()
	{
		// TODO Auto-generated method stub
		return cloud.getServerCount()+1;
	}
	
	@Override
	public void storeData()
	{
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("realCloud.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File realCloud.xml");
		}
		encoder.writeObject(this);
		encoder.close();

	}
	public static realCloud loadFromDisk()
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("realCloud.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File realCloud.xml not found");
		}
		
		realCloud real = (realCloud) decoder.readObject();
		return real;
		
		
	}
	
	//Search Function
	public ArrayList <User> getAllUsers()
	{
		return cloud.getUsers();
	}
	public ArrayList <Server>  getAllServers()
	{
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
		u.CreateServer(cloud,name);
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
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) throws RemoteException
	{
		observers.remove(o);
		
	}

	@Override
	public ArrayList<User> getUsers()
	{
		// TODO Auto-generated method stub
		return cloud.getUsers();
	}

	@Override
	public void changePassword(int uniqueID, String pass)
	{
		User u= findUser(uniqueID);
		u.setPassword(pass);
		
	}
	
	
	public void pinMessage(int userId, Server s, Channel c, Message m)
	{
		visits++;
		User u = findUser(userId);
		Role r = s.getUserRoleObject(u);
		r.pinMessage(c, m);
	}
	/*
	//Can I treat a DM like a private channel with just two people?
	public void DM(String username)
	{
		
	}
	*/

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
		
	}
		

}
