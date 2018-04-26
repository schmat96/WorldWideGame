package controller;


import controller.menu.HomeViewController;
import controller.menu.MainViewsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBean;
import model.Spieler;
import view.LoginView;
import view.RegisterView;

public class RegisterViewViewController {

	private RegisterView mainView;
	
	private DataBean dataBean;
	private Spieler spieler;
	
	public RegisterViewViewController(DataBean dataBean)  {
		this.dataBean = dataBean;
		this.mainView = new RegisterView(dataBean.getDimension());
		this.addListener();
		//this.dataBean.getGetData().closeSessionFactory();
	}
	
	protected void addListener() {
		this.mainView.getBtnBack().setOnAction(new backListener());
		this.mainView.getBtnRegister().setOnAction(new registerListener());
	}

	public void initializeView() {
		
	}
	
	public void register(String name, String pw) {
		if (this.dataBean.register(name, pw)) {
			HomeViewController mainWindow = new HomeViewController(dataBean);
			mainWindow.show();   
		} else {
			
		}
	}	
	
	public void show() {
		mainView.show(dataBean.getPrimaryStage());
		
	}
	
	class registerListener implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			register(mainView.getUserTextField().getText(), mainView.getPwBox().getText());
		}
	}
	
	class backListener implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			 LoginViewController backVC = new LoginViewController(dataBean);
			 backVC.show();   
		}
	}


	
}
