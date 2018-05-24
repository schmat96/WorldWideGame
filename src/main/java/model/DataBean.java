package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import exceptions.DataBaseNotRunningException;
import exceptions.NotEnoughMoneyException;
import javafx.stage.Stage;
import main.Main;
import model.unit.Charakter;
import model.unit.CharakterDAO;

public class DataBean {   
	private Stage primaryStage = null;
	private SpielerDAO spielerDAO;   
	private CharakterDAO charakterDAO;
	private UniversumDAO universumDAO;
	private ChallengeDAO challengeDAO;
	private ArrayList<Charakter> loadedCharakters;
	private ArrayList<Universum> loadedUniversums;
	
	private ArrayList<Charakter> choosenUnits;
	
	private Challenge selectedChallenge;
	
	private Spieler loggedInSpieler = null;  
	
	private boolean loading = true;
	    
	   public DataBean(Main main) { 
		   try {
			HibernateFactory.getSessionFactory(main);
		} catch (DataBaseNotRunningException e) {
			main.notifyProgressError("Database not running. Please start your database and restart the application");
			//e.printStackTrace();
		}
		   spielerDAO = new SpielerDAO(); 
		  
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
			Random rng = new Random();
			
			
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
	
	public void addMoney(int money) throws NotEnoughMoneyException {
		spielerDAO.addMoney(loggedInSpieler, money);
	}

	public void setPrimaryStage(Stage primaryStage) {
		 this.primaryStage = primaryStage;	
	}

	public void loadCharakters() {
		if (charakterDAO==null) {
			charakterDAO = new CharakterDAO();
		}
		loadedCharakters = new ArrayList<Charakter>();
		this.loading = charakterDAO.getCharaktersOfCertainPlayer(loggedInSpieler, loadedCharakters);
		
	}

	public ArrayList<Charakter> getLoadedCharakters() {
		this.loading = true;
		return loadedCharakters;
	}
	
	public void loadUniversums() {
		if (universumDAO==null) {
			universumDAO = new UniversumDAO();
		}
		loadedUniversums = new ArrayList<Universum>();
		this.loading = universumDAO.getUniversums(loadedUniversums);
	}

	public ArrayList<Universum> getLoadedUniversums() {
		this.loading = true;
		return loadedUniversums;
	}
	

	public boolean loading() {
		return this.loading;
	}
	
	public Charakter summonUnit(Universum universum) throws NotEnoughMoneyException  {
		this.addMoney(-100);

		if (universumDAO==null) {
			universumDAO = new UniversumDAO();
		}
		Charakter summonedCharakter = universumDAO.getRandomUnitFromUniversum(universum);
		if (this.spielerDAO==null) {
			spielerDAO = new SpielerDAO();
		}
		if (spielerDAO.playerOwnsCharakter(loggedInSpieler, summonedCharakter)) {
			System.out.println("Doppelte Unit!");
			return null;
		} else {
			spielerDAO.addCharakter(loggedInSpieler, summonedCharakter);
			System.out.println(summonedCharakter.getName()+" erhalten!");
		}
		
		return summonedCharakter;
	}

	public Charakter getRandomUnit(Random rng) {
		if (universumDAO==null) {
			universumDAO = new UniversumDAO();
		}
		if (loadedUniversums==null) {
			loadedUniversums = new ArrayList<Universum>();
			this.loading = universumDAO.getUniversums(loadedUniversums);
		}
		Charakter summonedCharakter = universumDAO.getRandomUnitFromUniversum(loadedUniversums.get(rng.nextInt(loadedUniversums.size())));
		return summonedCharakter;
	}

	public ArrayList<Challenge> getChallenges() {
		if (challengeDAO==null) {
			challengeDAO = new ChallengeDAO();
		}
		ArrayList<Challenge> loadedUniversums = new ArrayList<Challenge>();
		this.loading = challengeDAO.getChallenges(loadedUniversums);
		return loadedUniversums;
	}

	public int getChallengeCount(Challenge current) {
		if (challengeDAO==null) {
			challengeDAO = new ChallengeDAO();
		}
		return challengeDAO.getChallengeTurnCount(current);
	}

	public boolean selectChoosenUnit(Charakter choosen) {
		if (choosenUnits == null) {
			choosenUnits = new ArrayList<Charakter>();
		}
		if (choosenUnits.size()<6) {
			if (choosenUnits.contains(choosen)) {
				choosenUnits.remove(choosen);
				
				choosen.setPosition(-1);
				return false;
			}
			choosenUnits.add(choosen);
			
			choosen.setPosition(-1);
			
			Iterator<Charakter> iter = choosenUnits.iterator();

			ArrayList<Integer> keys = new ArrayList<Integer>();
			
			while (iter.hasNext()) {
				Charakter dammed = iter.next();
				keys.add(dammed.getPosition());
			}
			
			for (int i = 6;i>=0;i--) {
				if (!keys.contains(i)) choosen.setPosition(i);
			}
			
			return true;
		} else {
			choosenUnits.remove(choosen);
			return false;
		}
		
	}

	public int getSelectedUnitsCount() {
		if (choosenUnits == null) {
			choosenUnits = new ArrayList<Charakter>();
		}
		return this.choosenUnits.size();
	}
	
	public ArrayList<Charakter> getChoosenUnits() {
		return this.choosenUnits;
	}

	public void resetChoosenUnits() {
		this.choosenUnits = new ArrayList<Charakter>();
		
	}

	public void setChallenge(Challenge challenge) {
		this.selectedChallenge = challenge;
		
	}

	public Challenge getChoosenChallenge() {
		return this.selectedChallenge;
	}
	
	public ArrayList<Ability> getAbilitiesOfCharakter(Charakter charakter) {
		ArrayList<Ability> abilites = new ArrayList<Ability>();
		
		if (charakterDAO==null) {
			charakterDAO = new CharakterDAO();
		}

		abilites = charakterDAO.getAbilitiesOfCharakter(charakter);
		return abilites;
	}

	public HashMap<Stats, Integer> getUnitStats(Charakter selectedCharacter) {
		HashMap<Stats, Integer> stats = new HashMap<Stats, Integer>();
		stats.put(Stats.atk, selectedCharacter.getAtk());
		stats.put(Stats.hp, selectedCharacter.getHp());
		stats.put(Stats.def, selectedCharacter.getDef());
		stats.put(Stats.mag, selectedCharacter.getMag());
		stats.put(Stats.spr, selectedCharacter.getSpr());
		stats.put(Stats.mp, selectedCharacter.getMp());
		stats.put(Stats.blitzRes, selectedCharacter.getBlitzRes());
		stats.put(Stats.feuerRes, selectedCharacter.getFeuerRes());
		stats.put(Stats.wasserRes, selectedCharacter.getWasserRes());
		stats.put(Stats.windRes, selectedCharacter.getWindRes());
		stats.put(Stats.eisRes, selectedCharacter.getEisRes());
		return stats;
	}

	public ArrayList<String> getResults(Boolean gewonnen) {
		ArrayList<String> results = new ArrayList<String>();
		int multiplier = 1;
		if (gewonnen) {
			multiplier = 2;
		}
		int geld = this.getChoosenChallenge().getSchwierigkeit()*10*multiplier;
		try {
			this.addMoney(geld);
		} catch (NotEnoughMoneyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		results.add("Geld: "+geld);
		return results;
	}

	
}