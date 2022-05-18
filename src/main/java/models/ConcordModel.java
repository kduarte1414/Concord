package models;

import java.util.ArrayList;

import concord.CssElement;
import concord.CssProperty;
import concord.DirectMessage;
import concord.Message;
import concord.Server;
import concord.Theme;
import concord.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConcordModel
{
	ObservableList <User> users = FXCollections.observableArrayList();
	ObservableList <Server> servers= FXCollections.observableArrayList();
	ObservableList <Theme> Themes = FXCollections.observableArrayList();
	ObservableList <DirectMessage> dms = FXCollections.observableArrayList();
	
	ObservableList <CssElement> elements = FXCollections.observableArrayList();
	ObservableList <CssProperty> properties =FXCollections.observableArrayList();
	
	
	
	
	
	
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
		return servers;
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
		ObservableList<Message> messages =(ObservableList<Message>) dm.getMessages();
			return messages;
	}

	
	public ConcordModel()
	{
		
	}
	
	
	
	
}
