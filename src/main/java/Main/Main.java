package Main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

import concord.Channel;
import concord.Cloud;
import concord.CloudServer;
import concord.Theme;
import concord.realCloud;
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
		
		
		/*Cloud cloud = new Cloud();
		realCloud rc = new realCloud(cloud);
		Registry registry = LocateRegistry.getRegistry(1099);
		registry.rebind("RCLOUD",rc);	
		//CloudServer cs = (CloudServer) Naming.lookup("rmi://127.0.0.1:1099/RCLOUD");
		*/
		
		Cloud cloud = new Cloud();
		cloud.createUser("kat", "katd", "123");
		
		ArrayList <String> names = new ArrayList<String>(Arrays.asList("Music","grapes","watermelon","lemon","lime","strawberries"));
		
		
		for(String s: names) {
			cloud.createServer(s);
			Channel c = new Channel("test"+s,cloud.getServer(s).getId());
			cloud.getServer(s).addChannel(c);
			for(String s2:names)
			{
				Channel c2 = new Channel("test2"+s,cloud.getServer(s).getId());
				cloud.getServer(s).addChannel(c2);
			}
		}
		
		cloud.createUser("Liz", "LizzyLi","123");
		cloud.createUser("Eren", "rage", "123");
		cloud.createUser("big", "BIG_CHUNGUS", "123");
		cloud.createUser("little", "little_CHUNGUS", "123");
		cloud.createUser("happy", "Happy_CHUNGUS:)", "123");
		cloud.createUser("sexy", "Sexxyyy_CHUNGUS;)", "123");
		
		CloudServer cs = new realCloud(cloud);
		
		ConcordModel model  = new ConcordModel(cs);
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/MainView.fxml"));
		BorderPane view = loader.load();
		
		MainViewController cont = loader.getController();
		ViewTransitionalModel vm = new ViewTransitionalModel(view, model, stage);
		cont.setModel(vm);
		vm.showLogin();
		
		//ArrayList <Theme> themes =model.getThemes();
		
		
		Scene s = new Scene(view);
		s.getStylesheets().add(getClass().getResource("DefaultPurple.css").toExternalForm());
		stage.setScene(s);
		stage.show();

	}
	
	public static void main(String []args) {
		launch(args);
	}

}
