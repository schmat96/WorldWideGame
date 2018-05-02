package controller.fight;

import model.DataBean;
import model.DataBeanFight;
import model.NumbersToDisplay;
import model.Spieler;
import model.Universum;
import model.unit.Charakter;
import model.unit.CharakterDAO;

import java.util.ArrayList;
import java.util.Iterator;

import constants.LayoutConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Ability;
import model.AbilityArt;
import view.LoginView;
import view.fight.MainFightView;
import view.fight.SkillCase;
import view.fight.UnitCase;
import view.fight.UnitCaseMarks;
import view.menu.MenuViews;

public class MainFightController {
	protected DataBean dataBean;
	private DataBeanFight dataBeanFight;

	protected MainFightView mainView;
	
	private ArrayList<Charakter> charakters;
	
	private ArrayList<NumbersToDisplay> numbersToDisplay = new ArrayList<NumbersToDisplay>();
	
	private Ability abilityNeedsToMark = null;
	private Charakter abilityMarkedCharakter = null;

	public MainFightController(DataBean dataBean) {
		this.dataBean = dataBean;
		dataBeanFight = new DataBeanFight(dataBean, numbersToDisplay);
		this.mainView = new MainFightView(LayoutConstants.DIMENSION, numbersToDisplay);
	}

	public void show() {
		mainView.setData(dataBean.getLoggedInSpieler());
		mainView.show(dataBean.getPrimaryStage());
		init();
		showCharacters();
		showGegner();
	}
	
	private void showGegner() {
		mainView.removeGegner();
		mainView.showGegner(dataBeanFight.getGegner());	
	}

	public void showCharacters() {
		mainView.removeAllUnitAndSkills();
		int zahl = 0;
		if (abilityNeedsToMark!=null) {
			GridPane cancel = mainView.addBackCase();
			cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new cancel());
			zahl++;
		}
		
		
		Iterator<Charakter> iter = charakters.iterator();
		while (iter.hasNext()) {
			Charakter charakter = iter.next();
			if (abilityNeedsToMark==null) {
				createUnitCase(zahl, charakter);
			} else {
				createUnitCaseSkillNeedsMark(zahl, charakter);
			}
			zahl++;
		}
	}

	private void createUnitCaseSkillNeedsMark(int zahl, Charakter charakter) {
				
		UnitCase uc = mainView.addUnitCase(zahl+1, charakter);
		
		switch (abilityNeedsToMark.getAbilityArt()) {
			case Heilung:
				uc.setOwnStyle(UnitCaseMarks.markable);
				uc.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnitForAbility(charakter));
			case Raise:
				if (charakter.getIsDead()) {
					uc.setOwnStyle(UnitCaseMarks.markable);
					uc.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnitForAbility(charakter));
				}
			default:
				
				break;
		}
	}

	private void createUnitCase(int zahl, Charakter charakter) {
		UnitCase uc = mainView.addUnitCase(zahl, charakter);

		if (charakter.getIsDead()) {
			uc.setOwnStyle(UnitCaseMarks.dead);
		} else if (charakter.getEndTurn()) {
			uc.setOwnStyle(UnitCaseMarks.endedTurn);
		} else if (charakter.getChoosenAbility()==null) {
			uc.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnit(charakter, uc));
			uc.setOwnStyle(UnitCaseMarks.canMove);
		} else {
			uc.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnit(charakter, uc));
			uc.setOwnStyle(UnitCaseMarks.abilityChoosen);
		}
	}
	
	private void init() {
		charakters = dataBeanFight.getChoosenCharakters();
		Iterator<Charakter> iter = charakters.iterator();
		while (iter.hasNext()) {
			Charakter charakter = iter.next();
			charakter.setEndTurn(false);
		}
	}
	
	private void changeViewToSkills(Charakter charakter) {
		mainView.removeAllUnitAndSkills();
		GridPane back = mainView.addBackCase();
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, new back(charakter));
		ArrayList<Ability> abilities = dataBeanFight.getAbilitiesOfCharakter(charakter);
		Iterator<Ability> iter = abilities.iterator();
		int zahl = 1;
		while (iter.hasNext()) {
			Ability ability = iter.next();
			SkillCase sc = mainView.addSkillCase(zahl,ability);
			sc.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnSkill(charakter, ability));
			zahl++;
		}
		
	}
	
	private void executeSkill(Charakter charakter) {
		dataBeanFight.executeSkill(charakter, numbersToDisplay);
		showGegner();
		showCharacters();
	}
	
	
	
	class clickedOnUnitForAbility implements EventHandler<MouseEvent> {

		private Charakter choosenCharakter;

		public clickedOnUnitForAbility(Charakter choosenCharakter) {
			super();
			this.choosenCharakter = choosenCharakter;
		}

		@Override
		public void handle(MouseEvent event) {
			abilityMarkedCharakter.setChoosenAbility(abilityNeedsToMark);
			abilityNeedsToMark.setMarkedCharakter(choosenCharakter);
			abilityMarkedCharakter = null;
			abilityNeedsToMark = null;
			showCharacters();
		}
	}
	
	
	class clickedOnUnit implements EventHandler<MouseEvent> {

		private Charakter charakter;
		private UnitCase unitCase; 

		public clickedOnUnit(Charakter charakter, UnitCase unitCase) {
			super();
			this.charakter = charakter;
			this.unitCase = unitCase;
		}

		@Override
		public void handle(MouseEvent event) {
			if (charakter.getChoosenAbility()==null) {
				changeViewToSkills(this.charakter);
			} else {
				executeSkill(charakter);
				unitCase.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
			}
			
		}
	}
	
	class cancel implements EventHandler<MouseEvent> {

		
		public cancel() {
			super();
		}
		
		@Override
		public void handle(MouseEvent event) {
			abilityMarkedCharakter.setChoosenAbility(null);
			abilityMarkedCharakter = null;
			abilityNeedsToMark = null;
			showCharacters();

		}
	}
	
	class back implements EventHandler<MouseEvent> {

		private Charakter charakter;
		
		public back(Charakter charakter) {
			super();
			this.charakter = charakter;
		}
		
		@Override
		public void handle(MouseEvent event) {
			abilityMarkedCharakter = null;
			abilityNeedsToMark = null;
			showCharacters();
			charakter.setChoosenAbility(null);
			
		}
	}
	
	class clickedOnSkill implements EventHandler<MouseEvent> {

		private Charakter charakter;
		private Ability ability;

		public clickedOnSkill(Charakter charakter, Ability ability) {
			super();
			this.charakter = charakter;
			this.ability = ability;
		}

		@Override
		public void handle(MouseEvent event) {
			switch (ability.getAbilityArt()) {
			case Heilung:
				abilityNeedsToMark = ability;
				abilityMarkedCharakter = charakter;
			case Raise:
				abilityNeedsToMark = ability;
				abilityMarkedCharakter = charakter;
			default:
				charakter.setChoosenAbility(ability);
				break;
			}
			showCharacters();
		}
	}
	
	
}
