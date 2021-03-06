package views;

import java.rmi.RemoteException;

import concord.Server;
import concord.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class InviteViewController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	Server server;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
	}
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon,Server s) {
		tran_model = newTran;
		con_model = newCon;
		server = s;
		
		ServerNameLabel.setText(server.getName());
		
		UserListView.getItems().clear();
		UserListView.setItems(con_model.getUsers());
	}
	@FXML
    private Label ServerNameLabel;

  
    @FXML
    private TextField UsernameTextField;
	
    @FXML
	    private ListView<User> UserListView;

	@FXML
    void onClickCancel(ActionEvent event) {
    	tran_model.showHomePage();
    }

    @FXML
    void onClickInvite(ActionEvent event) {
    	User selected= UserListView.getSelectionModel().getSelectedItem();
    	try
		{
			con_model.getClient().invite(selected,server);
			tran_model.showHomePage();
			
		
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tran_model.showServer(server);
    }
    

}
