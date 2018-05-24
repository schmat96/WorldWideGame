package model;

public class Buff {
	


	private Stats stat;
	private Double modifier;
	private int turnCount;
	
	public Buff(Stats stat, Double modifier, int turnCount) {
		this.stat = stat;
		this.modifier = modifier;
		this.turnCount = turnCount;
	}
	
	public Stats getStat() {
		return stat;
	}

	public void setStat(Stats stat) {
		this.stat = stat;
	}

	public Double getModifier() {
		return modifier;
	}

	public void setModifier(Double modifier) {
		this.modifier = modifier;
	}

	public int getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	public void lowerTurnCount() {
		this.turnCount--;
		
	}
	
	
}
