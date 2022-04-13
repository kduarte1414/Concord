package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class JoinViewController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
	}
	
    @FXML
    void onClickCancel(ActionEvent event) {
    	tran_model.showHomePage();
    }

    @FXML
    void onClickJoinServer(ActionEvent event) {
    	tran_model.showServer();
    }


}
