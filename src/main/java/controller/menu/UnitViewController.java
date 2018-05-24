package controller.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import constants.LayoutConstants;
import model.Ability;
import model.DataBean;
import model.Stats;
import model.unit.Charakter;
import view.menu.UnitView;

public class UnitViewController extends MainViewsController {
	
	Charakter selectedCharacter;
	
	public UnitViewController(DataBean dataBean, Charakter character) {
		super(dataBean);
		this.selectedCharacter = character;
		view = new UnitView(LayoutConstants.DIMENSION, character.getName());
		addListener();
		displayCharacter();
	}

	private void displayCharacter() {
		
		HashMap<Stats, Integer> statsHashMap = dataBean.getUnitStats(selectedCharacter);
	
		for (Map.Entry<Stats, Integer> entry : statsHashMap.entrySet())
		{

			((UnitView) view).addStat("", entry.getValue(), entry.getKey());
		}
		
		ArrayList<Ability> abilities = dataBean.getAbilitiesOfCharakter(selectedCharacter);
		Iterator<Ability> iter = abilities.iterator();
		while (iter.hasNext()) {
			Ability abi = iter.next();
			((UnitView) view).addAbility(abi.getAbilityArt(), abi.getName(), abi.getModifier(), abi.getMana(), abi.getAttackPattern().getAttackPattern());
			
		}

		
		
		
		
	}
}
