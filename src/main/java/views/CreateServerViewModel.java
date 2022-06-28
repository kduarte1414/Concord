package views;

import java.rmi.RemoteException;

import concord.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class CreateServerViewModel
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
	}
	
	 @FXML
	 private TextField DescriptionTextField;

	 @FXML
	 private TextField nameServerTextField;

	 @FXML
	 void onClickCancelCreateServer(ActionEvent event) {
    	tran_model.showHomePage();
    }
    @FXML
    void onClickCreateServer(ActionEvent event) {
    	
    	String name = nameServerTextField.textProperty().get();
    	String description = DescriptionTextField.textProperty().get();
    	try
		{
    		con_model.getClient().createServer(name);
			Server newServer =con_model.getRc().findServer(name);
			if(!description.isEmpty())
			{
				con_model.getClient().changeServerDescription(newServer,description);
			}
			
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tran_model.showServer();
    }


}
