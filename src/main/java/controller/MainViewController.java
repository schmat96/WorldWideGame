package controller;

import controller.LoginViewVC.loginListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBean;
import view.MainWindow;
import view.MenuViews;

public abstract class MainViewController {
	
	protected DataBean dataBean;
	
	protected MenuViews view;
	
	public MainViewController(DataBean dataBean) {
		this.dataBean = dataBean;
	}
	
	public void show(){
	      // View mit Daten befuellen
	      view.setData(dataBean.getLoggedInSpieler());
	      view.show(dataBean.getPrimaryStage());
	   }
	
	protected void addListener() {
		view.getFooter().getUnitButton().setOnAction(new changeToUnits(dataBean));
		view.getFooter().getHomeButton().setOnAction(new changeToHome(dataBean));
	}
}


class changeToUnits implements EventHandler<ActionEvent> {
	
	private DataBean dataBean;
	
	public changeToUnits(DataBean dataBean) {
		super();
		this.dataBean = dataBean;
	}
	
	public void handle(ActionEvent event) {
		UnitsVC backVC = new UnitsVC(this.dataBean);
		backVC.show();   
	}
}

class changeToHome implements EventHandler<ActionEvent> {
	
	private DataBean dataBean;
	
	public changeToHome(DataBean dataBean) {
		super();
		this.dataBean = dataBean;
	}
	
	public void handle(ActionEvent event) {
		HomeVC backVC = new HomeVC(this.dataBean);
		backVC.show();   
	}
}
