package controller;


import controller.HomeVC.backBtnEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBean;
import model.Spieler;
import view.MainWindow;
import view.ViewLogin;

public class LoginViewVC extends MainViewController {

	private ViewLogin mainView;
	
	private Spieler spieler;

	
	public LoginViewVC(DataBean dataBean)  {
		super(dataBean);
		this.mainView = new ViewLogin(dataBean.getDimension());
		this.addListener();
		
		//this.dataBean.getGetData().closeSessionFactory();
	}
	
	protected void addListener() {
		this.mainView.getBtnLogin().setOnAction(new loginListener());
		this.mainView.getRegister().setOnAction(new registerListener());
	}

	
	public void login(String name, String pw) {
		if (super.dataBean.login(name, pw)) {
			HomeVC mainWindow = new HomeVC(dataBean);
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
			 RegisterViewVC registerVC = new RegisterViewVC(dataBean);
			 registerVC.show();   
		}
	}


	
}
