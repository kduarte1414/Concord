package models;


import java.io.IOException;

import concord.Server;
import concord.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.CreateAccountViewController;
import views.CreateDmController;
import views.CreateServerViewModel;
import views.HomePageController;
import views.InviteViewController;
import views.JoinViewController;
import views.LoginViewController;
import views.EditProfileViewController;
import views.ServerViewController;
import views.ThemeViewController;



public class ViewTransitionalModel implements ViewTransitionModelInterface
{
	BorderPane mainview;
	ConcordModel model;
	Stage s;
	
	public ViewTransitionalModel(BorderPane view, ConcordModel newModel, Stage newS) {
		mainview = view;
		model = newModel;
		s = newS;
	}

	@Override
	public void showCreateAccount()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/CreateAccountView.fxml"));
		try
		{
			s.setWidth(600);
			s.setHeight(500);
			Pane view = loader.load();
			mainview.setCenter(view);
			CreateAccountViewController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void showCreateServer()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/CreateServerView.fxml"));
		try
		{
			s.setWidth(600);
			s.setHeight(400);
			Pane view = loader.load();
			mainview.setCenter(view);
			CreateServerViewModel cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Override
	public void showHomePage()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/HomePageView.fxml"));
		try
		{
			s.setWidth(600);
			s.setHeight(400);
			Pane view = loader.load();
			mainview.setCenter(view);
			HomePageController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void showInvite()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/InviteView.fxml"));
		try
		{
			s.setWidth(500);
			s.setHeight(400);
			Pane view = loader.load();
			mainview.setCenter(view);
			InviteViewController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void showJoinServer()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/JoinServerView.fxml"));
		try
		{
			s.setWidth(500);
			s.setHeight(400);
			Pane view = loader.load();
			mainview.setCenter(view);
			JoinViewController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void showServer( )
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/ServerView.fxml"));
		try
		{
			s.setWidth(600);
			s.setHeight(400);
			Pane view = loader.load();
			mainview.setCenter(view);
			ServerViewController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void showLogin()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/LoginView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			LoginViewController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void showEditProfile()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/EditProfileView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			EditProfileViewController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void showTheme()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/ThemeView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			ThemeViewController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void showServer(Server server)
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/ServerView.fxml"));
		try
		{
			s.setWidth(600);
			s.setHeight(400);
			Pane view = loader.load();
			mainview.setCenter(view);
			ServerViewController cont = loader.getController();
			cont.setModel(this,model,server); 
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void showCreateDM()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/CreateDmView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			CreateDmController cont = loader.getController();
			cont.setModel(this,model);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void showInvite(Server server)
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/InviteView.fxml"));
		try
		{
			s.setWidth(600);
			s.setHeight(400);
			Pane view = loader.load();
			mainview.setCenter(view);
			InviteViewController cont = loader.getController();
			cont.setModel(this,model,server); 
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
