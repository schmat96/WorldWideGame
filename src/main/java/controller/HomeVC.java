package controller;

import exceptions.NotEnoughMoney;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.CharakterDAO;
import model.DataBean;
import view.MainWindow;
import view.MenuViews;

public class HomeVC extends MainViewController {
    

	   
	    
	    
	   public HomeVC(DataBean dataBean) {
		  super(dataBean);
	      super.view = new MainWindow(dataBean.getDimension());
	      addListener();
	      
	   }
	   
		@Override
		protected void addListener() {
			super.addListener();
			 ((MainWindow) view).getAddMoneyBtn().setOnAction(new addMoney());   
		      ((MainWindow) view).getRemoveMoneyBtn().setOnAction(new removeMoney());   
		      ((MainWindow) view).getBackBtn().setOnAction(new backBtnEventHandler());   
			
		}
	    
	   
	    
	   //+++++++++++++++++++++++++++++++++++++++++++++
	   // Events
	   //+++++++++++++++++++++++++++++++++++++++++++++
	 
	    
	   class backBtnEventHandler implements EventHandler<ActionEvent>{
	      public void handle(ActionEvent e) {   
	    	  LoginViewVC eingabeVC = new LoginViewVC(dataBean);
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
