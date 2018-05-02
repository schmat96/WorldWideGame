package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.unit.Charakter;
import model.unit.CharakterDAO;
import model.unit.Enemy;
import main.Main;

public class DataBeanFight {

	private ArrayList<Charakter> choosenCharakters;
	private Enemy gegner; 
	private DataBean dataBean;
	private CharakterDAO charakterDAO;
	
	private ArrayList<NumbersToDisplay> numbersToDisplay;
	
	public DataBeanFight(DataBean dataBean, ArrayList<NumbersToDisplay> numbersToDisplay) {
		this.dataBean = dataBean;
		this.numbersToDisplay = numbersToDisplay;
		gegner = new Enemy("Gilgamesh", 1000, 10,10,10,10);
	}
	
	public void initChoosen() {
		Random rng = new Random();
		while (choosenCharakters.size()<6) {
			Charakter charakter = dataBean.getRandomUnit(rng);
			charakter.setCalcAsDefaults();
			choosenCharakters.add(charakter);
		}
	}

	public ArrayList<Charakter> getChoosenCharakters() {
		if (choosenCharakters ==null ) {
			choosenCharakters = new ArrayList<Charakter>();
		}
		initChoosen();
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

	public void executeSkill(Charakter charakter, ArrayList<NumbersToDisplay> numbersToDisplay) {
		System.out.println("Used Skill on charakter "+charakter.getName()+" "+charakter.getChoosenAbility().getName());
		
		Ability ability = charakter.getChoosenAbility();
		
		System.out.println(ability.getAbilityArt());
		
		ability.execute(this, charakter);

		
		charakter.setEndTurn(true);
		charakter.setChoosenAbility(null);
		finished();
	}
	
	public Enemy getGegner() {
		return this.gegner;
	}

	public boolean finished() {
		boolean finished = true;
		
		if (gegner.getHpCalc()<=0) {
			System.out.println("Du hast gewonnen!");
			return finished;
		}
		
		Iterator<Charakter> iter = choosenCharakters.iterator();
		while (iter.hasNext()) {
			Charakter character = iter.next();
			if (!character.getEndTurn() && !character.getIsDead()) {
				finished = false;
			}
		}
		if (finished) {
			gegner.hisTurn(choosenCharakters);
			Boolean alleTod = true;
			Iterator<Charakter> iter2 = choosenCharakters.iterator();
			while (iter2.hasNext()) {
				Charakter character = iter2.next();
				character.setEndTurn(false);
				if (character.getHpCalc()<=0) {
					character.setEndTurn(true);
					character.setIsDead(true);
				} else {
					alleTod = false;
				}
			}
			
			if (alleTod) {
				System.out.println("Du hast verloren!");
			}
			return true;
		}
		return false;
		
	}

	public void makeDmgPhy(int attackWert) {
		int value = this.gegner.losehpPhy(attackWert, 1);
		numbersToDisplay.add(new NumbersToDisplay(value,gegner, AbilityArt.Angriff));
	}

}
