package views;

import concord.DirectMessage;
import concord.Message;
import concord.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.ConcordModel;
 
import models.ViewTransitionModelInterface;

public class HomePageController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
		ServerListView.setItems(con_model.getServers());
		DmListView.setItems(con_model.getDms());
		
		//messageListView.setItems(con_model.getDMmessages(null));
	}
   
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
   
   //Model methods 
    @FXML
    void onClickSend(ActionEvent event)
    {
    	//con_model.getDms();
    }

}
