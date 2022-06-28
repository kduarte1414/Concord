package views;

import java.rmi.RemoteException;

import concord.Channel;
import concord.Server;
import concord.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class CreateDmController {

	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;

	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
		
		UserListView.getItems().clear();
		UserListView.setItems(con_model.getUsers());
	}
	
    @FXML
    private ListView<User> UserListView;

    @FXML
    private Label UserNameLabel;

    @FXML
    private TextField UserNameTextField;

    @FXML
    void onClickCancel(ActionEvent event) {
    	tran_model.showHomePage();
    }

    @FXML
    void onClickCreate(ActionEvent event) {
    	User selected= UserListView.getSelectionModel().getSelectedItem();
    	try
		{
			con_model.getClient().createDM(selected);
			tran_model.showHomePage();
			
		
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    @FXML
    void onSelectUser(MouseEvent event) {
    	UserListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent click)
    		{
    			if (click.getClickCount() == 1) {
    				User selected = UserListView.getSelectionModel().getSelectedItem();
    				UserNameLabel.setText(selected.getUsername());
    			}
    		}	
        });
    }

}
