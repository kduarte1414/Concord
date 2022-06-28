package views;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import concord.Client;
import concord.Cloud;
import concord.CloudServer;
import concord.realCloud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class LoginViewController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
	}
	
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField passwordTextField;
	
	
	
    @FXML
    void onClickCreateAccount(ActionEvent event){
    	tran_model.showCreateAccount();
    }

    @FXML
    void onClickLogin(ActionEvent event) {
    	String username = usernameTextField.textProperty().get();
    	String password = passwordTextField.textProperty().get();
    	try
		{
			Client client = new Client(con_model.getRc());
			
			if(client.authentication(username, password))
			{
				con_model.setClient(client);
				tran_model.showHomePage();
			}else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("invalid usernamer or password");
				alert.show();
			}
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	
    	
    }
    
    

}
