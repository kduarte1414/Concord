package views;

import concord.CssElement;
import concord.CssProperty;
import concord.Server;
import concord.Theme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ColorPickerSkin;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class ThemeViewController
{
	ViewTransitionModelInterface tran_model;
	ConcordModel con_model;
	
	public void setModel(ViewTransitionModelInterface newTran,ConcordModel newCon) {
		tran_model = newTran;
		con_model = newCon;
		
		ExisitingThemesListView.setItems(con_model.getThemes());
		
	}
	
	@FXML
	private ListView<Theme> themeList;
	
	@FXML
    void onClickHome(ActionEvent event) {
    	tran_model.showHomePage();
    }
	
	    @FXML
	    private ListView<String> ChangesMadeListView;

	    @FXML
	    private ListView<CssElement> ElementsListView;

	    @FXML
	    private ListView<Theme> ExisitingThemesListView;

	    @FXML
	    private ListView<CssProperty> PropertiesListVew;

	    @FXML
	    private Button SaveButton;

	    @FXML
	    private TextField newThemeNameTextField;

	    @FXML
	    private TextField newValueTextField;

	    @FXML
	    void onClickChangeTo(ActionEvent event) {
	    	
	    }

	    @FXML
	    void onClickDelete(ActionEvent event) {

	    }

	    @FXML
	    void onClickEdit(ActionEvent event) {

	    }

	    @FXML
	    void onClickSave(ActionEvent event) {

	    }

	    @FXML
	    void onClickSet(ActionEvent event) {

	    }

	}


