package views;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import concord.Client;
import concord.CloudServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class CreateAccountViewController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
		
	}
	 
	@FXML
	private TextField nameTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField hobbiesTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField pictureTextField;

    @FXML
    private TextField usernameTextField;
	

    @FXML
    void onClickCreateAccount(ActionEvent event) {
    	
    	//Change this usernameTextField
    	String name = nameTextField.textProperty().get();
    	String username = usernameTextField.textProperty().get();
    	String password = passwordTextField.textProperty().get();
    	String hobbies =  hobbiesTextField.textProperty().get();
    	String picture =  pictureTextField.textProperty().get();
    	String description =  descriptionTextField.textProperty().get();
   
    	if(name.isEmpty()|| username.isEmpty()|| password.isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Error<content missing>");
    		alert.setContentText("You have not filled out all required fields");
    		alert.show();
    		
    	}else
    	{
    	
    	Client newClient;
			try
			{
				newClient = new Client(con_model.getRc());
				con_model.getRc().addUser(name, username, password);
				if(newClient.authentication(username,password))
				{
					if(!hobbies.isBlank()) {
						newClient.getAccount().setHobbies(hobbies);
					}
					if(!picture.isBlank())
					{
						newClient.getAccount().setPicture(picture);
					}
					if(!description.isBlank())
					{
						newClient.getAccount().setUserBio(description);
					}
					newClient.getAccount().setStatus(true);
					con_model.setClient(newClient);
					tran_model.showHomePage();
				}
			} catch (RemoteException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	
    	}
    }

   
    
}
