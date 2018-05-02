package model.unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.Buff;

public class Enemy extends Unit {
	
	
	public Enemy(String name, int hp, int def, int spr, int mag, int atk) {
		super.hp = this.hpCalc = hp;
		this.def = this.defCalc = def;
		this.spr = this.sprCalc = spr;
		this.mag = this.magCalc = mag;
		this.atk = this.atkCalc = atk;
		this.name = name;
		
		setCalcAsDefaults();
		
		this.buffs = new ArrayList<Buff>();

	}

	public String getName() {
		return this.name;
	}

	public void hisTurn(ArrayList<Charakter> choosenCharakters) {
		Random rng = new Random();
		choosenCharakters.get(rng.nextInt(choosenCharakters.size())).setIsDead(true);
		choosenCharakters.get(rng.nextInt(choosenCharakters.size())).losehpPhy(rng.nextInt(90), 1);
		choosenCharakters.get(rng.nextInt(choosenCharakters.size())).losehpPhy(rng.nextInt(90), 1);
		choosenCharakters.get(rng.nextInt(choosenCharakters.size())).losehpPhy(rng.nextInt(90), 1);
		this.getsHealing(100, 1);
	}
}
