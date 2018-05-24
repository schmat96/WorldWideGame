package controller.menu;

import java.util.ArrayList;
import java.util.Iterator;

import constants.LayoutConstants;
import controller.fight.MainFightController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Challenge;
import model.DataBean;
import model.unit.Charakter;
import view.menu.ChooseView;
import view.menu.UnitCaseMenu;

public class ChooseViewController extends MainViewsController {

	private BorderPane confirm;
	
	public ChooseViewController(DataBean dataBean) {
		super(dataBean);
		// TODO Auto-generated constructor stub
		super.view = new ChooseView(LayoutConstants.DIMENSION);
		addListener();
		displayChallenges();
		prepareCurrentChallenge();
	}

	@Override
	protected void addListener() {
		super.addListener();

	}
	
	private void displayChallenges() {
		
		while (dataBean.loading()) {
			dataBean.loadUniversums();
		}
		ArrayList<Challenge> universums = dataBean.getChallenges();
		Iterator<Challenge> iter = universums.iterator();
		while (iter.hasNext()) {
			Challenge current = iter.next();
			BorderPane bp = ((ChooseView) super.view).addChallenge(current, dataBean.getChallengeCount(current));
			bp.setOnMouseClicked(new changeChallengeChoose(current));
		}
		
		
		
	}
	
	private void setCurrentChallenge(Challenge challenge) {
		this.dataBean.setChallenge(challenge);
		((ChooseView) super.view).prepareForChallenge();
	}
	
	private void setChallenges() {
		((ChooseView) super.view).prepareForChallengeView();
	}
	
	public void enableConfirm() {
		confirm.setOnMouseClicked(new confirm());
		((ChooseView) super.view).setConfirm(true);
	}

	public void disableConfirm() {
		confirm.setOnMouseClicked(null);
		((ChooseView) super.view).setConfirm(false);
	}



	public void prepareCurrentChallenge() {
		
		BorderPane back = ((ChooseView) super.view).addBackButton();
		back.setOnMouseClicked(new back());
		
		while (dataBean.loading()) {
			dataBean.loadCharakters();
		}
		ArrayList<Charakter> characters = dataBean.getLoadedCharakters();
		Iterator<Charakter> iter = characters.iterator();
		while (iter.hasNext()) {
			Charakter current = iter.next();
			
			UnitCaseMenu unit = ((ChooseView) view).addUnits(current);
			
	
			
			unit.setOnMousePressed(new selectedUnit(dataBean, current, unit));
			unit.setOnMouseEntered(new changeFocus(unit));
			unit.setOnMouseExited(new removeFocus(unit));
		}
		
		confirm = ((ChooseView) super.view).addConfirmButton();
		
		if (this.dataBean.getSelectedUnitsCount()==6) {
			enableConfirm();
		} else {
			disableConfirm();
		}
		
		
		
	}
	
	class changeChallengeChoose implements EventHandler<MouseEvent> {

		private Challenge current;

		public changeChallengeChoose(Challenge current) {
			super();
			this.current = current;
		}

		@Override
		public void handle(MouseEvent arg0) {
			setCurrentChallenge(current);
		}
	}
	
	class selectedUnit implements EventHandler<MouseEvent> {

		private DataBean dataBean;
		private Charakter choosen;
		private UnitCaseMenu ucm;
		
		public selectedUnit(DataBean dataBean, Charakter choosen, UnitCaseMenu ucm) {
			super();
			this.dataBean = dataBean;
			this.choosen = choosen;
			this.ucm = ucm;
		}

		@Override
		public void handle(MouseEvent arg0) {
			if (this.dataBean.selectChoosenUnit(choosen)) {
				ucm.setMarked(choosen.getPosition());
			} else {
				ucm.removeMark();
			}
			
			if (this.dataBean.getSelectedUnitsCount()==6) {
				enableConfirm();
			} else {
				disableConfirm();
			}
		}
	}
	
	class back implements EventHandler<MouseEvent> {


		public back() {
			super();
		}

		@Override
		public void handle(MouseEvent arg0) {
			setChallenges();
		}
	}
	
	class confirm implements EventHandler<MouseEvent> {

		public confirm() {
			super();
		}

		@Override
		public void handle(MouseEvent arg0) {
			MainFightController wwg = new MainFightController(dataBean);
		    wwg.show(); 
		}
	}
}
