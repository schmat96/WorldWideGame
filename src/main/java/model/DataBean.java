package model;

import java.awt.Dimension;
import java.util.ArrayList;

import exceptions.NotEnoughMoney;
import javafx.stage.Stage;
import main.Main;

public class DataBean {   
	private Stage primaryStage = null;
	private SpielerDAO spielerDAO;   
	private CharakterDAO charakterDAO;
	private ArrayList<Charakter> loadedCharakters;
	private Spieler loggedInSpieler = null;  
	
	private final Dimension DIMENSION;
	private boolean loading = true;
	    
	   public DataBean(Main main, int x, int y) { 
		   HibernateFactory.getSessionFactory();
		   spielerDAO = new SpielerDAO(); 
		   DIMENSION = new Dimension(x,y);
	   }
	 
	   public Stage getPrimaryStage() {
	      return primaryStage;
	   }



	public boolean login(String name, String pw) {
		Spieler spieler = this.spielerDAO.checkUserLogin(name, pw);
		
		if (spieler != null) {
			System.out.println("Login Succesfull");
			this.loggedInSpieler = spieler;
			return true;
		} else {
			System.out.println("Login not Succesfull");
			return false;
		}
	}
	
	public void close() {
		HibernateFactory.getSessionFactory().close();
	}

	public boolean register(String name, String pw) {
		Spieler spieler = this.spielerDAO.registerUser(name, pw);
		
		if (spieler != null) {
			System.out.println("Login Succesfull");
			this.loggedInSpieler = spieler;
			return true;
		} else {
			System.out.println("Login not Succesfull");
			return false;
		}
	}
	
	public void logout() {
		loggedInSpieler = null;
	}
	
	public Spieler getLoggedInSpieler() {
		return this.loggedInSpieler;
	}
	
	public void addMoney(int money) throws NotEnoughMoney {
		spielerDAO.addMoney(loggedInSpieler, money);
	}

	public void setPrimaryStage(Stage primaryStage2) {
		 this.primaryStage = primaryStage2;
		
	}
	
	public Dimension getDimension() {
		return this.DIMENSION;
	}

	public void loadCharakters() {
		if (charakterDAO==null) {
			charakterDAO = new CharakterDAO();
		}
		loadedCharakters = new ArrayList<Charakter>();
		this.loading = true;
		this.loading = charakterDAO.getCharaktersOfCertainPlayer(loggedInSpieler, loadedCharakters);
		
	}

	public ArrayList<Charakter> getLoadedCharakters() {
		return loadedCharakters;
	}

	public boolean loading() {
		return this.loading;
	}

	}