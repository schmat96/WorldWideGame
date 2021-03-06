package controller.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DataBean;
import view.menu.MenuViews;

public abstract class MainViewsController {

	protected DataBean dataBean;

	protected MenuViews view;

	public MainViewsController(DataBean dataBean) {
		this.dataBean = dataBean;
	}

	public void show() {
		view.setData(dataBean.getLoggedInSpieler());
		view.show(dataBean.getPrimaryStage());
	}

	protected void addListener() {
		view.getFooter().getUnitButton().setOnAction(new changeToUnits(dataBean));
		view.getFooter().getHomeButton().setOnAction(new changeToHome(dataBean));
		view.getFooter().getSummonButton().setOnAction(new changeToSummon(dataBean));
		view.getFooter().getSpecialButton().setOnAction(new changeToSpecial(dataBean));
	}
}

class changeToUnits implements EventHandler<ActionEvent> {

	private DataBean dataBean;

	public changeToUnits(DataBean dataBean) {
		super();
		this.dataBean = dataBean;
	}

	public void handle(ActionEvent event) {
		UnitsViewController backVC = new UnitsViewController(this.dataBean);
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
		HomeViewController backVC = new HomeViewController(this.dataBean);
		backVC.show();
	}
}

class changeToSpecial implements EventHandler<ActionEvent> {

	private DataBean dataBean;

	public changeToSpecial(DataBean dataBean) {
		super();
		this.dataBean = dataBean;
	}

	public void handle(ActionEvent event) {
		  ChooseViewController mfc = new ChooseViewController(dataBean);
		  mfc.show();    
	}
}

class changeToSummon implements EventHandler<ActionEvent> {

	private DataBean dataBean;

	public changeToSummon(DataBean dataBean) {
		super();
		this.dataBean = dataBean;
	}

	public void handle(ActionEvent event) {
		SummonViewController summonVC = new SummonViewController(this.dataBean);
		summonVC.show();
	}
}
