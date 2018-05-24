package controller.menu;

import java.util.Random;

import constants.LayoutConstants;
import controller.LoginViewController;

import exceptions.NotEnoughMoneyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.DataBean;
import model.unit.Charakter;
import view.menu.HomeView;

public class HomeViewController extends MainViewsController {

	public HomeViewController(DataBean dataBean) {
		super(dataBean);
		super.view = new HomeView(LayoutConstants.DIMENSION);
		addListener();
		
	}

	@Override
	protected void addListener() {
		super.addListener();
		//((HomeView) view).getAddMoneyBtn().setOnAction(new addMoney());
		//((HomeView) view).getRemoveMoneyBtn().setOnAction(new removeMoney());
		((HomeView) view).getBackBtn().setOnAction(new backBtnEventHandler());
		Random rng = new Random();
		//Charakter charakter = dataBean.getRandomUnit(rng);
		//Pane image = ((HomeView) view).addImage(charakter);

		//image.addEventHandler(MouseEvent.MOUSE_CLICKED, new addMoney());
	}

	// +++++++++++++++++++++++++++++++++++++++++++++
	// Events
	// +++++++++++++++++++++++++++++++++++++++++++++

	class backBtnEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			LoginViewController eingabeVC = new LoginViewController(dataBean);
			eingabeVC.show();
		}
	}

	class addMoney implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			try {
				dataBean.addMoney(10);
			} catch (NotEnoughMoneyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			view.getHeader().setEnergie(dataBean.getLoggedInSpieler().getGeld());
			
		}
	}

	class removeMoney implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			try {
				dataBean.addMoney(-10);
			} catch (NotEnoughMoneyException e1) {
				System.out.println("Exception wurde geworfen! Muss das ganze jetzt im Window darstellen...");
				return;
			}
			view.getHeader().setEnergie(dataBean.getLoggedInSpieler().getGeld());
		}

	}

}
