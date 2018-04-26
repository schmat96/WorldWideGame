package controller;


import controller.menu.HomeViewController;
import controller.menu.MainViewsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBean;
import model.Spieler;
import view.LoginView;
import view.menu.HomeView;

public class LoginViewController {

	private LoginView mainView;
	
	private Spieler spieler;
	
	private DataBean dataBean;

	
	public LoginViewController(DataBean dataBean)  {
		this.dataBean = dataBean;
		this.mainView = new LoginView(dataBean.getDimension());
		this.addListener();
		
		//this.dataBean.getGetData().closeSessionFactory();
	}
	
	protected void addListener() {
		this.mainView.getBtnLogin().setOnAction(new loginListener());
		this.mainView.getRegister().setOnAction(new registerListener());
	}

	
	public void login(String name, String pw) {
		if (dataBean.login(name, pw)) {
			HomeViewController mainWindow = new HomeViewController(dataBean);
			mainWindow.show();   
		} else {
			
		}
	}	
	
	public void show() {
		mainView.show(dataBean.getPrimaryStage());
	}
	
	class loginListener implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			login(mainView.getUserTextField().getText(), mainView.getPwBox().getText());
		}
	}
	
	class registerListener implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			 RegisterViewViewController registerVC = new RegisterViewViewController(dataBean);
			 registerVC.show();   
		}
	}


	
}
