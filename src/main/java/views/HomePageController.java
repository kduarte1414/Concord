package views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import concord.DirectMessage;
import concord.Message;
import concord.Server;
import concord.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.ConcordModel;
 
import models.ViewTransitionModelInterface;

public class HomePageController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	User account;
	DirectMessage dmSelected;
	
	/*public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
		
		ServerListView.setItems(con_model.getServers());
		DmListView.setItems(con_model.getDms());
		//messageListView.setItems(con_model.getDMmessages(null));
	}*/
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
		account = con_model.getClient().getAccount();
		System.out.println(account.getUsername());
		profileButton.textProperty().set(account.getUsername());
		
		for(Server s: account.getServers())
		{
			ServerListView.getItems().add(s);
		}
		
		DmListView.setItems(con_model.getDms());
		
		//messageListView.setItems()
	}
    
	@FXML
    private Button profileButton;
	@FXML
	private ListView<Server> ServerListView;
	@FXML
	private ListView<DirectMessage>DmListView;
	@FXML
	private ListView<Message>messageListView;
	@FXML
	private TextField TextMessageTextField;
	@FXML
	private Label FriendNameLabel;
	

	//Tran_model Methods
	@FXML
    void onClickHome(ActionEvent event) {
	
		tran_model.showHomePage();
    }
    @FXML
    void onClickInvite(ActionEvent event) {
    	tran_model.showInvite();
    }
    @FXML
    void onClickJoinServer(ActionEvent event) {
    	tran_model.showJoinServer();
    }
   
    @FXML
    void onClickCreateServer(ActionEvent event) {
    	tran_model.showCreateServer();
    }

    @FXML
    void onClickServer(ActionEvent event) {
    	tran_model.showServer();
    }
   
    @FXML
    void onClickEditProfile(ActionEvent event)
    {
    	tran_model.showEditProfile();
    }
    @FXML
    void onClickTheme(ActionEvent event)
    {
    	tran_model.showTheme();
    	
    }
    @FXML
    void onClickSelectServer(MouseEvent event) {
    	ServerListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		
    	@Override
		public void handle(MouseEvent click)
		{
			Server selected;
		
			if (click.getClickCount() == 2) {
				selected = ServerListView.getSelectionModel().getSelectedItem();
				
				tran_model.showServer(selected);
			}
		}	
    });
    }
   
    
    @FXML
    void onClickCreateDM(ActionEvent event) {
    	tran_model.showCreateDM();
    }

    @FXML
    void onSelectDM(MouseEvent event) {
    	DmListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent click)
    		{
    			if (click.getClickCount() == 2) {
    				dmSelected = DmListView.getSelectionModel().getSelectedItem();
    			
    				//FriendNameLabel.accessibleTextProperty().set(selected.getUsers().get(0).getUsername());
    				FriendNameLabel.setText(dmSelected.getUsers().get(0).getUsername());
    				
    				if(con_model.getDMmessages(dmSelected)!= null)
    				{
    					messageListView.setItems(con_model.getDMmessages(dmSelected));
    				}
    				
    				
    			}
    		}	
        });
    }
   //Model methods 
    @FXML
    void onClickSend(ActionEvent event)
    {
    	String text = TextMessageTextField.accessibleTextProperty().get();
    	try
		{
			con_model.getClient().sendDMmessage(dmSelected,text);
			messageListView.setItems(con_model.getDMmessages(dmSelected));
			for(Message m: con_model.getDMmessages(dmSelected))
			{
				System.out.println(dmSelected.getMessages().size());
				System.out.println(m.getText());
			}
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

       	//con_model.getDms();
    }

}
