package model;

import model.unit.Enemy;
import model.unit.Unit;

public class NumbersToDisplay {
	
	private Unit unit;
	private int value;
	private AbilityArt art; 
	private int destroying;
	private double XDisplay;
	private double YDisplay;
	private double YDisplayDirection;
	

	public NumbersToDisplay(int value, Enemy unit, AbilityArt asd) {
		this.unit = unit;
		this.value = value;
		this.art = asd;
		this.destroying = 30;
		this.XDisplay = 100;

	}
	
	public Unit getUnit() {
		return unit;
	}

	public int getValue() {
		return value;
	}

	public AbilityArt getArt() {
		return art;
	}

	public boolean destroy() {
		destroying--;
		if (destroying<0) {
			return true;
		} else {
			return false;
		}
	}

	public double getXDisplay() {
		XDisplay--;
		return XDisplay;
	}

}
