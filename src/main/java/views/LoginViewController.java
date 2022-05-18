package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void onClickCreateAccount(ActionEvent event) {
    	tran_model.showCreateAccount();
    }

    @FXML
    void onClickLogin(ActionEvent event) {
    	String username = usernameTextField.textProperty().get();
    	String password = passwordTextField.textProperty().get();

    	tran_model.showHomePage();
    }
    
    

}
