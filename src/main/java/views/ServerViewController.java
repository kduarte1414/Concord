package views;

import concord.Channel;
import concord.Server;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class ServerViewController
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
	
		System.out.println(s);
		
		if(server.getChannels()!= null)
		{
			for (Channel c: server.getChannels())
			{
				ChannelListView.getItems().add(c);
			}
		}
		ServerListView.getItems().clear();
		
		
		//Only show servers already in 
	
		/*for (Server s1: con_model.getServersIn())
				{
					ServerListView.getItems().set(0, s)
					ServerListView.getItems().add(s1);
				}
		*/
		
		ServerListView.setItems(con_model.getServersIn());
		if(!ServerListView.getItems().get(0).equals(s))
		{
			int ServerSelectedIndex = 0;
			for(int i=0; i<ServerListView.getItems().size(); i++)
			{
				if(ServerListView.getItems().get(i).equals(s))
				{
					ServerSelectedIndex =i;
				}
			
			}
			Server ItemReplace = ServerListView.getItems().get(0);
			ServerListView.getItems().set(ServerSelectedIndex,ItemReplace);
			ServerListView.getItems().set(0, s);
		}
		
		
		
	}
	
	
    @FXML
    private ListView<Channel> ChannelListView;

    @FXML
    private ListView<Server> ServerListView;
    
    @FXML
    void onClickHome(ActionEvent event) {
    	tran_model.showHomePage();
    }
   
    @FXML
    void OnSelectServer(MouseEvent event) {
    	ServerListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		
        	@Override
    		public void handle(MouseEvent click)
    		{
    			Server selected;
    			System.out.println("here");
    			if (click.getClickCount() == 2) {
    				selected = ServerListView.getSelectionModel().getSelectedItem();
    				System.out.println("here");
    				tran_model.showServer(selected);
    			}
    		}	
        });
    }
    
    @FXML
    void onClickInvite(ActionEvent event) {
    	
    	tran_model.showInvite(server);
    }
    @FXML
    void onClickTheme(ActionEvent event) {
    	tran_model.showTheme();
    }
    @FXML
    void onClickEditProfile(ActionEvent event)
    {
    	tran_model.showEditProfile();
    }
    @FXML
    void onClickJoinServer(ActionEvent event)
    {
    	tran_model.showJoinServer();
    }
    @FXML
    void onClickCreateServer(ActionEvent event) {
    	tran_model.showCreateServer();

    }
   

}
