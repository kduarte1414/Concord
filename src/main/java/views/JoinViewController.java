package views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import concord.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class JoinViewController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
		ArrayList<Server> serversIn = con_model.getClient().getAccount().getServers();
		
		for(Server s: con_model.getRc().getAllServers())
		{
			ServerListView.getItems().add(s);
			for(Server a:serversIn)
			{
				if(s.getName().equals(a.getName()))
				{
					ServerListView.getItems().remove(s);
				}
			}
		}
		
		
	}
	
	@FXML
	private ListView<Server> ServerListView;

	@FXML
	private TextField ServerNameTextField;
	    
    @FXML
    void onClickCancel(ActionEvent event) {
    	tran_model.showHomePage();
    }

    @FXML
    void onClickJoinServer(ActionEvent event) {
    	Server selectedServer = ServerListView.getSelectionModel().getSelectedItem();
    	try
		{
			con_model.getClient().joinServer(selectedServer);
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tran_model.showServer();
    }


}
