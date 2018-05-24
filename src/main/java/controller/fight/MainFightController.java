package controller.fight;

import java.util.ArrayList;
import java.util.Iterator;

import constants.ImagesPreloader;
import constants.LayoutConstants;
import controller.menu.ResultViewController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Ability;
import model.AbilityArt;
import model.DataBean;
import model.DataBeanFight;
import model.NumbersToDisplay;
import model.unit.Charakter;
import model.unit.Enemy;
import view.fight.EnemyCase;
import view.fight.GegnerHPThread;
import view.fight.MainFightView;
import view.fight.SkillCase;
import view.fight.UnitCase;
import view.fight.UnitCaseMarks;

/**
 * This Class Builds the Controller for the Main Fight and is therefore here to 
 * interpret Inputs by the View and give them to the Model vise-versa.
 * @author Mathias Schmid
 *
 */
public class MainFightController {
	
	protected DataBean dataBean;
	private DataBeanFight dataBeanFight;
	protected MainFightView mainView;
	
	private ArrayList<Charakter> charakters;
	
	private static ArrayList<NumbersToDisplay> numbersToDisplay = new ArrayList<NumbersToDisplay>();
	
	private Ability abilityNeedsToMark = null;
	private Charakter abilityMarkedCharakter = null;
	
	/**
	 * Holds all Threads for the Enemies HPThreads. They are needed to display the enemies health up to date cause
	 * of the Attack Patterns.
	 */
	private ArrayList<GegnerHPThread> gegnerHpThread;
	public static boolean newGegner = false;
	

	public MainFightController(DataBean dataBean) {
		this.dataBean = dataBean;
		dataBeanFight = new DataBeanFight(this, this.dataBean.getChoosenUnits(), this.dataBean.getChoosenChallenge());
		this.mainView = new MainFightView(LayoutConstants.DIMENSION);
		gegnerHpThread = new ArrayList<GegnerHPThread>();
		ImagesPreloader.loadImages(this.dataBean.getChoosenUnits());
		FinishedThread ft = new FinishedThread(this);
		ft.start();
	}

	public void show() {
		mainView.setData(dataBean.getLoggedInSpieler());
		mainView.show(dataBean.getPrimaryStage());
		init();
		showCharacters();
		showGegner();
	}
	
	public void showGegner() {
		mainView.removeGegner();
		ArrayList<Enemy> enemies = dataBeanFight.getEnemies();
		mainView.clearGegnerHitBox();
		Iterator<Enemy> gosh = enemies.iterator();
		while (gosh.hasNext()) {
			Enemy current = gosh.next();
			EnemyCase enemy = mainView.showGegner(current);
			enemy.addEventHandler(MouseEvent.MOUSE_CLICKED, new MarkEnemy(current, enemy));

			mainView.addGegnerHitBox(current.getPosition(), current);
			GegnerHPThread ght = new GegnerHPThread(enemy.getProgressEnemy(), enemy.getHPEnemy(), current, this);
	        ght.start();
	        gegnerHpThread.add(ght);
		}
	}

	public void showCharacters() {
		Iterator<Charakter> iter = charakters.iterator();
		while (iter.hasNext()) {
			Charakter charakter = iter.next();
			if (abilityNeedsToMark==null) {
				createUnitCase(charakter);
			} else {
				createUnitCaseSkillNeedsMark(charakter);
			}

		}
	}
	
	public void showCharacters(Charakter character) {

		if (abilityNeedsToMark==null) {
			createUnitCase(character);
		} else {
			createUnitCaseSkillNeedsMark(character);
		}
		
	}

	private void createUnitCaseSkillNeedsMark(Charakter character) {
		this.mainView.removeAllSkillCases();
		this.mainView.setUnitCaseVisible(true);
		
		if (character==this.abilityMarkedCharakter) {
			Pane cancel = mainView.getUnitCase(character.getPosition()).setCancelAbilityButton();
			cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnCancelMarkAbility(character));
		}
		
		UnitCase unitCase = mainView.getUnitCase(character.getPosition());
		switch (abilityNeedsToMark.getAbilityArt()) {
		case Heilung:
			if (!character.getIsDead()) {
				unitCase.setOwnStyle(UnitCaseMarks.markable);
				unitCase.addSingleEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnitForAbility(character));
			} else {
				unitCase.setOwnStyle(UnitCaseMarks.dead);
				unitCase.removeEventHandler();
			}
			break;
		case Raise:
			if (character.getIsDead()) {
				unitCase.setOwnStyle(UnitCaseMarks.markable);
				unitCase.addSingleEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnitForAbility(character));
			} else {
				unitCase.setOwnStyle(UnitCaseMarks.dead);
				unitCase.removeEventHandler();
			}
			break;
		default:

			break;
		}
	}


	private void createUnitCase(Charakter charakter) {
		UnitCase uc = mainView.getUnitCase(charakter.getPosition());
		if (charakter.getIsDead()) {
			uc.setOwnStyle(UnitCaseMarks.dead);
			uc.addSingleEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnit(charakter));
		} else if (charakter.getEndTurn()) {
			uc.setOwnStyle(UnitCaseMarks.endedTurn);
		} else if (charakter.getChoosenAbility()==null) {
			uc.addSingleEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnit(charakter));
			uc.setOwnStyle(UnitCaseMarks.canMove);
			uc.removeAbilityIcon();
		} else {
			uc.setOwnStyle(UnitCaseMarks.abilityChoosen);
			Pane cancel = uc.getAbilityIcon();
			cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnCancelAbility(charakter));
			uc.addSingleEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnUnit(charakter));
		}
	}
	
	private void init() {
		charakters = dataBeanFight.getChoosenCharakters();
		Iterator<Charakter> iter = charakters.iterator();
		while (iter.hasNext()) {
			Charakter charakter = iter.next();
			charakter.setEndTurn(false);
			this.mainView.addUnitHitBox(charakter.getPosition(), charakter);
			this.mainView.addUnitCase(charakter);
		}
	}
	
	private void changeViewToSkills(Charakter charakter) {
		
		mainView.setUnitCaseVisible(false);
		
		mainView.removeAllSkillCases();
		GridPane back = mainView.addBackCase();
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, new back(charakter));
		ArrayList<Ability> abilities = dataBeanFight.getAbilitiesOfCharakter(charakter);
		Iterator<Ability> iter = abilities.iterator();
		int zahl = 1;
		while (iter.hasNext()) {
			Ability ability = iter.next();
			SkillCase sc = mainView.addSkillCase(zahl,ability);
			if (charakter.enoughMana(ability.getMana())) {
				sc.addSingleEventHandler(MouseEvent.MOUSE_CLICKED, new clickedOnSkill(charakter, ability));
			} else {
				sc.markDisabled();
			}			
			zahl++;
		}
		
	}
	
	private void executeSkill(Charakter charakter) {
		Boolean updatehp = (charakter.getChoosenAbility().getAbilityArt() == AbilityArt.Heilung || charakter.getChoosenAbility().getAbilityArt() == AbilityArt.Raise);
		dataBeanFight.executeSkill(charakter);
		if (updatehp) {
			this.mainView.updateHp();
		}
		this.mainView.getUnitCase(charakter.getPosition()).removeCancel();
		this.mainView.getUnitCase(charakter.getPosition()).removeAbilityIcon();
		this.mainView.updateMana(charakter.getPosition());
	}
	
	
	
	class clickedOnUnitForAbility implements EventHandler<MouseEvent> {

		private Charakter choosenCharakter;

		public clickedOnUnitForAbility(Charakter choosenCharakter) {
			super();
			this.choosenCharakter = choosenCharakter;
		}

		@Override
		public void handle(MouseEvent event) {
			if (newGegner) {
				executeBullshitAndKYS();
			} else {
				abilityMarkedCharakter.setChoosenAbility(abilityNeedsToMark);
				abilityNeedsToMark.setMarkedCharakter(choosenCharakter);
				abilityMarkedCharakter = null;
				abilityNeedsToMark = null;
				mainView.getUnitCase(choosenCharakter.getPosition()).removeCancel();
				showCharacters();
			}
			
		}
	}
	
	class MarkEnemy implements EventHandler<MouseEvent> {

		private Enemy choosenEnemy;
		private EnemyCase choosenHBox;

		public MarkEnemy(Enemy choosenEnemy, EnemyCase choosenHBox) {
			super();
			this.choosenEnemy = choosenEnemy;
			this.choosenHBox = choosenHBox;
		}

		@Override
		public void handle(MouseEvent event) {
			if (newGegner) {
				executeBullshitAndKYS();
			} else {
				if (dataBeanFight.setSelectedEnemy(choosenEnemy)) {
					mainView.resetStyleEnemyCases();
					choosenHBox.markSelected();
				}
				
			}
			
		}
	}

	
	class clickedOnCancelAbility implements EventHandler<MouseEvent> {

		private Charakter choosenCharakter;

		
		public clickedOnCancelAbility(Charakter choosenCharakter) {
			super();
			this.choosenCharakter = choosenCharakter;
		}

		@Override
		public void handle(MouseEvent event) {
			if (newGegner) {
				executeBullshitAndKYS();
			} else {
				choosenCharakter.setChoosenAbility(null);
				showCharacters(choosenCharakter);
			}
		}
	}
	
	class clickedOnCancelMarkAbility implements EventHandler<MouseEvent> {

		private Charakter choosenCharakter;
		
		public clickedOnCancelMarkAbility(Charakter choosenCharakter) {
			super();
			this.choosenCharakter = choosenCharakter;
		}

		@Override
		public void handle(MouseEvent event) {
			if (newGegner) {
				executeBullshitAndKYS();
			} else {
				abilityMarkedCharakter = null;
				abilityNeedsToMark = null;
				choosenCharakter.setChoosenAbility(null);
				mainView.getUnitCase(choosenCharakter.getPosition()).removeCancel();
				showCharacters();
			}
		}
	}
	
	
	class clickedOnUnit implements EventHandler<MouseEvent> {

		private Charakter charakter;

		public clickedOnUnit(Charakter charakter) {
			super();
			this.charakter = charakter;
		}

		@Override
		public void handle(MouseEvent event) {
			if (newGegner) {
				executeBullshitAndKYS();
			} else {
				if (!charakter.getIsDead() && !charakter.getEndTurn()) {
					if (charakter.getChoosenAbility()==null) {
						changeViewToSkills(this.charakter);
					} else {
						executeSkill(charakter);
						showCharacters(charakter);
					}
				}
				
			}
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
			if (newGegner) {
				executeBullshitAndKYS();
			} else {
				abilityMarkedCharakter = null;
				abilityNeedsToMark = null;
				showCharactersAgain();
				charakter.setChoosenAbility(null);
			}
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
			if (newGegner) {
				executeBullshitAndKYS();
			} else {
				switch (ability.getAbilityArt()) {
				case Heilung:
					abilityNeedsToMark = ability;
					abilityMarkedCharakter = charakter;
					showCharacters();
					break;
				case Raise:
					abilityNeedsToMark = ability;
					abilityMarkedCharakter = charakter;
					showCharacters();
					break;
				default:
					charakter.setChoosenAbility(ability);
					showCharactersAgain();
					createUnitCase(charakter);
					break;
				}
				
			}
		}
	}

	public void changeViewToResult(boolean gewonnen) {
		cleanup();
		ResultViewController backVC = new ResultViewController(this.dataBean, gewonnen);
		backVC.show();
		
	}

	public void showCharactersAgain() {
		mainView.removeBack();
		
		mainView.removeAllSkillCases();
		
		mainView.setUnitCaseVisible(true);
		
		
	}

	public void executeBullshitAndKYS() {
		if (newGegner) {
			this.dataBeanFight.newEnemy();
		}
		newGegner = false;
		
		if (this.dataBeanFight.getEnemies()==null) {
			this.changeViewToResult(true);
		} else {
			
			Iterator <GegnerHPThread> iter = gegnerHpThread.iterator();
			
			while (iter.hasNext()) {
				iter.next().setRunning(false);
			}
			
			gegnerHpThread.clear();
			
			showCharacters();
			showGegner();
			mainView.updateHp();
			mainView.updateMana();
		}
		
		
	}

	private void cleanup() {
		this.mainView.getShowNumbersThread().setRunning(false);
		this.dataBean.resetChoosenUnits();
		MainFightController.numbersToDisplay.clear();
	}

	public static ArrayList<NumbersToDisplay> getNumbersToDisplay() {
		return numbersToDisplay;
	}

	public static void setNumbersToDisplay(ArrayList<NumbersToDisplay> numbersToDisplay) {
		MainFightController.numbersToDisplay = numbersToDisplay;
	}

	public ArrayList<Enemy> getGegner() {
		return this.dataBeanFight.getEnemies();
	}

	public void afterGegnerTurn() {
		mainView.updateHp();
		mainView.updateMana();
	}
}
