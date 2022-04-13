package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class HomePageController
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
    

    @FXML
    void onClickCreateServerView(ActionEvent event) {
    	tran_model.showCreateServer();

    }

    @FXML
    void onClickServer(ActionEvent event) {
    	tran_model.showServer();
    }

}
