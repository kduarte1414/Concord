package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class createRoleController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
	}
	
    @FXML
    void onClickCreateRole(ActionEvent event) {
    	tran_model.showHomePage();
    }

    @FXML
    void onClickBack(ActionEvent event) {
    	tran_model.showServer();
    }

}
