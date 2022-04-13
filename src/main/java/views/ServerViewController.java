package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class ServerViewController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
	}
	
    @FXML
    void onClickHome(ActionEvent event) {
    	tran_model.showHomePage();
    }
    
    @FXML
    void onClickInvite(ActionEvent event) {
    	tran_model.showInvite();
    }


    @FXML
    void onClickSearchServer(ActionEvent event) {
    	tran_model.showJoinServer();

    }

}
