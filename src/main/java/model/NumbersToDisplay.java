package model;

import constants.LayoutConstants;
import javafx.scene.paint.Color;
import model.unit.Enemy;
import model.unit.Unit;

public class NumbersToDisplay {
	
	private Unit unit;
	private String value;
	private AbilityArt art; 
	private int destroying;
	private double XDisplay;
	private double YDisplay;
	private double YDisplayDirection = -5;
	private double XDisplayDirection = 0;
	private Color color;
	

	public NumbersToDisplay(int value, Unit unit, AbilityArt asd) {
		this.unit = unit;
		this.value = value+"";
		this.art = asd;
		this.destroying = 10;
		switch (asd) {
		case Angriff:
			color = Color.rgb(123,50,0);
			break;
		case BuffEinzeln:
			break;
		case BuffGegner:
			break;
		case BuffGruppe:
			break;
		case BuffSelf:
			break;
		case Defense:
			break;
		case Heilung:
			color = Color.rgb(50,255,0);
			break;
		case Raise:
			break;
		case Magie:
			color = Color.rgb(0,123,255);
			break;
		default:
			break;
		}
		
		
		if (unit.getClass()==Enemy.class) {
			XDisplay = LayoutConstants.GetPositionEnemyX(unit.getPosition());
			YDisplay = LayoutConstants.GetPositionEnemyY(unit.getPosition());
		} else {
			this.destroying = 10;
			XDisplay = LayoutConstants.GetPositionUnitX(unit.getPosition());
			YDisplay = LayoutConstants.GetPositionUnitY(unit.getPosition());
		}
		

	}
	

	public NumbersToDisplay(String string, Unit unit2) {
		this.unit = unit2;
		this.value = string;
		YDisplayDirection = -1;
		this.destroying = 2;
		color = Color.rgb(123,255,0);   
		XDisplay = LayoutConstants.GetPositionEnemyX(unit.getPosition())+30;
		YDisplay = LayoutConstants.GetPositionEnemyY(unit.getPosition());
	}


	public Unit getUnit() {
		return unit;
	}

	public String getValue() {
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
		XDisplay = XDisplay + XDisplayDirection;
		return XDisplay;
	}
	
	public double getYDisplay() {
		YDisplay = YDisplay + YDisplayDirection;
		return YDisplay;
	}

	public Color getColor() {
		return this.color;
	}

}
