package models;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.CreateAccountViewController;
import views.CreateServerViewModel;
import views.HomePageController;
import views.JoinViewController;
import views.LoginViewController;
import views.ServerViewController;



public class ViewTransitionalModel implements ViewTransitionModelInterface
{
	BorderPane mainview;
	ConcordModel model;
	
	public ViewTransitionalModel(BorderPane view, ConcordModel newModel) {
		mainview = view;
		model = newModel;
	}

	@Override
	public void showCreateAccount()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/CreateAccountView.fxml"));
		try
		{
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
		// TODO Auto-generated method stub

	}

	@Override
	public void showJoinServer()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/JoinView.fxml"));
		try
		{
			Node view = loader.load();
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
	public void showServer()
	{
		FXMLLoader loader  = new FXMLLoader();
		loader.setLocation(ViewTransitionalModel.class.getResource("../views/ServerView.fxml"));
		try
		{
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

}
