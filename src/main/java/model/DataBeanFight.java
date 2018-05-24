package model;

import java.util.ArrayList;
import java.util.Iterator;

import controller.fight.MainFightController;
import model.unit.Charakter;
import model.unit.CharakterDAO;
import model.unit.Enemy;

public class DataBeanFight {

	private ArrayList<Charakter> choosenCharakters;
	private ArrayList<Enemy> enemies; 
	private CharakterDAO charakterDAO;
	
	private int turn = 0;
	
	
	private Enemy selectedEnemy = null;
	
	Boolean gewonnen = false;
	private MainFightController mainFightController;
	private ChallengeDAO challengeDAO;
	private Challenge choosenChallenge;
	
	public DataBeanFight(MainFightController mainFightController, ArrayList<Charakter> choosenCharakters, Challenge choosenChallenge) {
		this.mainFightController = mainFightController;
		this.choosenChallenge = choosenChallenge;
		this.choosenCharakters = choosenCharakters;
		setEnemyAtTurnCount();
		initChoosen();
	}
	
	public void initChoosen() {
		Iterator<Charakter> iter = choosenCharakters.iterator();
		while (iter.hasNext()) {
			Charakter character = iter.next();
			character.setCalcAsDefaults();
		}
	}

	public ArrayList<Charakter> getChoosenCharakters() {
		return choosenCharakters;
	}

	public ArrayList<Ability> getAbilitiesOfCharakter(Charakter charakter) {
		ArrayList<Ability> abilites = new ArrayList<Ability>();
		
		if (charakterDAO==null) {
			charakterDAO = new CharakterDAO();
		}

		abilites = charakterDAO.getAbilitiesOfCharakter(charakter);
		return abilites;
	}

	public void executeSkill(Charakter charakter) {
		Ability ability = charakter.getChoosenAbility();
		ability.execute(this, charakter);
		charakter.setEndTurn(true);
		charakter.setChoosenAbility(null);
		
		Boolean finished = true;
		Iterator<Charakter> iter1 = choosenCharakters.iterator();
		while (iter1.hasNext()) {
			Charakter character = iter1.next();
			if (!character.getEndTurn()) {
				if (!character.getIsDead()) {
					finished = false;
				}
			}
		}
		
		if (finished) {
			areWeFinished();
		}
		
	}
	
	private void areWeFinished() {

			
			Iterator<Charakter> iter1 = choosenCharakters.iterator();
			
			while (iter1.hasNext()) {
				Charakter character = iter1.next();
				if (!character.getIsDead()) {
					character.setEndTurn(false);
				}
			}
			
			
			this.gegnerHisTurn();
			
			
			Boolean allDead = true;
			Iterator<Charakter> iter2 = choosenCharakters.iterator();
			while (iter2.hasNext()) {
				Charakter character = iter2.next();
				character.calculateBuffs();
				if (!character.getIsDead()) {
					allDead = false;
				}
			}
			
			if (allDead) {
				this.mainFightController.changeViewToResult(false);
				return;
			}
			
			this.mainFightController.showCharacters();
			this.mainFightController.afterGegnerTurn();
		
		
	}
	
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}

	public void newEnemy() {
		Iterator<Charakter> iter2 = choosenCharakters.iterator();
		while (iter2.hasNext()) {
			Charakter character = iter2.next();
			character.setEndTurn(false);
		}
		setEnemyAtTurnCount();
	}

	public void setEnemyAtTurnCount() {
		if (challengeDAO==null) {
			challengeDAO = new ChallengeDAO();
		}
		turn++;
		if (challengeDAO.getChallengeTurnCount(this.choosenChallenge)<turn) {
			enemies = null;
			return;
		}
		enemies = challengeDAO.getEnemiesAtTurnCount(this.choosenChallenge, turn);
		Iterator<Enemy> gosh = enemies.iterator();
		int pos = 0;
		while (gosh.hasNext()) {
			Enemy current = gosh.next();
			current.setPosition(pos);
			current.setCalcAsDefaults();
			current.setIsDead(false);
			selectedEnemy = current;
			pos++;
		}
		

	}

	public void gegnerHisTurn() {
		Iterator<Enemy> gosh = enemies.iterator();
		while (gosh.hasNext()) {
			Enemy current = gosh.next();
			current.hisTurn(choosenCharakters, this);
			current.calculateBuffs();
		}
	}

	public void makeDmgPhy(int attackWert) {
		
		selectedEnemy.losehpPhy(attackWert);
	}

	public void makeDmgMag(int i, Elemente ele) {
		
		selectedEnemy.losehpMag(i, ele);
		
		
	}

	public Boolean setSelectedEnemy(Enemy choosenEnemy) {
		if (this.selectedEnemy==choosenEnemy) {
			return false;
		} else {
			this.selectedEnemy = choosenEnemy;	
			return true;
		}
		
	}

	public void dispel() {
		this.selectedEnemy.removeBuffs();
		
	}

}
