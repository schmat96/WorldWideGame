package controller;


import controller.HomeVC.backBtnEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBean;
import model.Spieler;
import view.ViewLogin;
import view.ViewRegister;

public class RegisterViewVC extends MainViewController {

	private ViewRegister mainView;
	
	
	private Spieler spieler;
	
	public RegisterViewVC(DataBean dataBean)  {
		super(dataBean);
		this.mainView = new ViewRegister(dataBean.getDimension());
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
			HomeVC mainWindow = new HomeVC(dataBean);
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
			 LoginViewVC backVC = new LoginViewVC(dataBean);
			 backVC.show();   
		}
	}


	
}
