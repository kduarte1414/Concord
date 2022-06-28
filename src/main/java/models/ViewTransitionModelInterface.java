package models;

import concord.Server;
import concord.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface ViewTransitionModelInterface
{
	public void showCreateAccount();
	public void showCreateServer();
	public void showInvite();
	public void showInvite(Server s);
	public void showJoinServer();
	public void showServer();
	public void showServer(Server s);
	public void showLogin();
	public void showTheme();
	public void showEditProfile();
	public void showHomePage();
	public void showCreateDM();
}
