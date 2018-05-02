package controller.menu;

import constants.LayoutConstants;
import model.DataBean;
import model.unit.Charakter;
import view.menu.UnitView;

public class UnitViewController extends MainViewsController {
	
	Charakter selectedCharacter;
	
	public UnitViewController(DataBean dataBean, Charakter character) {
		super(dataBean);
		this.selectedCharacter = character;
		view = new UnitView(LayoutConstants.DIMENSION);
		addListener();
		displayCharacter();
	}

	private void displayCharacter() {
		((UnitView) view).displayUnit(this.selectedCharacter);
		
	}
}
