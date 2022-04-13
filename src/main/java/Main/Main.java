package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.ConcordModel;
import models.ViewTransitionalModel;
import views.MainViewController;


public class Main extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		ConcordModel model  = new ConcordModel();
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/MainView.fxml"));
		BorderPane view = loader.load();
		MainViewController cont = loader.getController();
		ViewTransitionalModel vm = new ViewTransitionalModel(view,model);
		cont.setModel(vm);
		vm.showLogin();
		
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
	}
	
	public static void main(String []args) {
		launch(args);
	}

}
