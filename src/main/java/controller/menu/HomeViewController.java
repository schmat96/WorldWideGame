package controller.menu;

import controller.LoginViewController;
import exceptions.NotEnoughMoney;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.CharakterDAO;
import model.DataBean;
import view.menu.HomeView;
import view.menu.MenuViews;

public class HomeViewController extends MainViewsController {
    

	   
	    
	    
	   public HomeViewController(DataBean dataBean) {
		  super(dataBean);
	      super.view = new HomeView(dataBean.getDimension());
	      addListener();
	      
	   }
	   
		@Override
		protected void addListener() {
			super.addListener();
			 ((HomeView) view).getAddMoneyBtn().setOnAction(new addMoney());   
		      ((HomeView) view).getRemoveMoneyBtn().setOnAction(new removeMoney());   
		      ((HomeView) view).getBackBtn().setOnAction(new backBtnEventHandler());   
			
		}
	    
	   
	    
	   //+++++++++++++++++++++++++++++++++++++++++++++
	   // Events
	   //+++++++++++++++++++++++++++++++++++++++++++++
	 
	    
	   class backBtnEventHandler implements EventHandler<ActionEvent>{
	      public void handle(ActionEvent e) {   
	    	  LoginViewController eingabeVC = new LoginViewController(dataBean);
	          eingabeVC.show();   
	      }
	   }
	   
	   class addMoney implements EventHandler<ActionEvent>{
		  public void handle(ActionEvent e) {   
			  try {
				dataBean.addMoney(10);
			} catch (NotEnoughMoney e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  view.getHeader().setEnergie(dataBean.getLoggedInSpieler().getGeld());
		  }
	   }
	   
	   class removeMoney implements EventHandler<ActionEvent>{
		  public void handle(ActionEvent e) {   
			  try {
				dataBean.addMoney(-10);
			} catch (NotEnoughMoney e1) {
				System.out.println("Exception wurde geworfen! Muss das ganze jetzt im Window darstellen...");
				return;
			}
			  view.getHeader().setEnergie(dataBean.getLoggedInSpieler().getGeld());
		  }
		  
	   }


	   
	   
	}
