package models;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import concord.Client;
import concord.Cloud;
import concord.CloudServer;
import concord.CssElement;
import concord.CssProperty;
import concord.DirectMessage;
import concord.Message;
import concord.Server;
import concord.Theme;
import concord.User;
import concord.realCloud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConcordModel
{
	ObservableList <User> users = FXCollections.observableArrayList();
	ObservableList <User> usersInServer = FXCollections.observableArrayList();
	ObservableList <Server> servers= FXCollections.observableArrayList();
	ObservableList <Server> serversIn= FXCollections.observableArrayList();
	ObservableList <Theme> Themes = FXCollections.observableArrayList();
	ObservableList <DirectMessage> dms = FXCollections.observableArrayList();
	ObservableList <Message> messages = FXCollections.observableArrayList();
	ObservableList <CssElement> elements = FXCollections.observableArrayList();
	ObservableList <CssProperty> properties =FXCollections.observableArrayList();
	
	realCloud rc;
	CloudServer cs;
	Client user;
	
	/**
	 * @return the rc
	 */
	public realCloud getRc()
	{
		return rc;
	}

	/**
	 * @param rc the rc to set
	 */
	public void setRc(realCloud rc)
	{
		this.rc = rc;
	}

	//ObservableList <Messages>
	public ObservableList <CssElement> getElements(Theme theme)
	{
		return (ObservableList<CssElement>)theme.getElements();
	}
	
	public ObservableList <CssProperty> getProperties(CssElement element)
	{
		return (ObservableList<CssProperty>)element.getProperties();
	}
	
	/**
	 * @return the dms
	 */
	public ObservableList<DirectMessage> getDms()
	{
		dms.clear();
		for(DirectMessage dm:getClient().getAccount().getDms())
		{
			dms.add(dm);
		}
		return dms;
	}

	/**
	 * @param dms the dms to set
	 */
	public void setDms(ObservableList<DirectMessage> dms)
	{
		this.dms = dms;
	}

	/**
	 * @return the users
	 */
	public ObservableList<User> getUsers()
	{
		users.clear();
		for(User u: rc.getAllUsers())
		{
			users.add(u);
		}
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ObservableList<User> users)
	{
		this.users = users;
	}

	/**
	 * @return the servers
	 */
	public ObservableList<Server> getServers()
	{
		servers.clear();
		for(Server s :rc.getAllServers())
		{
			servers.add(s);
		}
		
		return servers;
	}
	public ObservableList<Server> getServersIn()
	{
		serversIn.clear();
		for(Server s :user.getAccount().getServers())
		{
			serversIn.add(s);
		}
		
		return serversIn;
	}
	/**
	 * @param servers the servers to set
	 */
	public void setServers(ObservableList<Server> servers)
	{
		this.servers = servers;
	}

	/**
	 * @return the theme
	 */
	public ObservableList<Theme> getThemes()
	{
		return Themes;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setThemes(ObservableList<Theme> theme)
	{
		Themes = theme;
	}
	
	public ObservableList<Message> getDMmessages(DirectMessage dm)
	{
		messages.clear();
		for(Message m: dm.getMessages())
		{
			messages.add(m);
		}
			return messages;
	}
	
	public void setClient(Client account)
	{
		user = account;
	}
	public Client getClient()
	{
		return user;
	}
	
	public ConcordModel(CloudServer cs)
	{
		this.cs = cs;
		this.rc = (realCloud) cs;
		//CloudServer cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
	}

	public ObservableList<User> getUsersInServer(Server s)
	{
		usersInServer.clear();
		for(User u: s.getUsers())
		{
			usersInServer.add(u);
		}
		return usersInServer;
	}
	
	
	
	
}
