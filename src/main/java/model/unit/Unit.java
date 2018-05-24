package model.unit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import constants.ModelConstants;
import controller.fight.MainFightController;
import model.AbilityArt;
import model.Buff;
import model.Elemente;
import model.NumbersToDisplay;

@MappedSuperclass
public abstract class Unit {
	
	@Column(name="NAME", length=20, nullable=true)
	protected String name;
	
	@Column(name="hp", length=20, nullable=true)
	protected int hp;
	
	@Column(name="def", length=20, nullable=true)
	protected int def;
	
	@Column(name="spr", length=20, nullable=true)
	protected int spr;
	
	@Column(name="mag", length=20, nullable=true)
	protected int mag;
	
	@Column(name="atk", length=20, nullable=true)
	protected int atk;
	
	@Column(name="mp", length=20, nullable=true)
	protected int mp;
	
	@Column(name="threat", length=20, nullable=true)
	protected int threat;
	
	@Column(name="blitzRes", length=20, nullable=true)
	protected int blitzRes = 0;
	
	@Column(name="feuerRes", length=20, nullable=true)
	protected int feuerRes = 0;
	
	@Column(name="wasserRes", length=20, nullable=true)
	protected int wasserRes = 0;
	
	@Column(name="windRes", length=20, nullable=true)
	protected int windRes = 0;
	
	@Column(name="eisRes", length=20, nullable=true)
	protected int eisRes = 0;
	
	@Transient
	protected int blitzResCalc = 0;
	
	@Transient
	protected int feuerResCalc = 0;
	
	@Transient
	protected int wasserResCalc = 0;
	
	@Transient
	protected int windResCalc = 0;
	
	@Transient
	protected int eisResCalc = 0;
	
	@Transient
	protected int hpCalc;
	
	@Transient
	protected int defCalc;
	
	@Transient
	protected int sprCalc;
	
	@Transient
	protected int magCalc;
	
	@Transient
	protected int atkCalc;
	
	@Transient
	protected int mpCalc;

	@Transient
	private Boolean isDead = false;
	
	@Transient
	protected ArrayList<Buff> buffs;
	
	@Transient
	private int position = 1;
	
	@Transient
	private double multiplier = 1;
	
	@Transient long lastCall = 0;
	
	public Unit() {
		buffs = new ArrayList<Buff>();
	}
	
	protected void outline() {
		System.out.println(name);
		System.out.println("------------------Base Stats----------------------");
		System.out.println(hp+"|"+def+"|"+spr+"|"+mag+"|"+atk+"|"+blitzRes+"|"+feuerRes+"|"+wasserRes+"|"+wasserRes+"|"+windRes+"|"+eisRes);
		System.out.println("------------------Calculated Stats----------------------");
		System.out.println(hpCalc+"|"+defCalc+"|"+sprCalc+"|"+magCalc+"|"+atkCalc+"|"+blitzResCalc+"|"+feuerResCalc+"|"+wasserResCalc+"|"+wasserResCalc+"|"+windResCalc+"|"+eisResCalc);
		System.out.println("");
	}
	
	public void removeBuffs() {
		this.buffs.clear();
		this.defCalc = def;
		this.sprCalc = spr;
		this.magCalc = mag;
		this.atkCalc = atk;
		this.mpCalc = mp;
		this.blitzResCalc = blitzRes;
		this.feuerResCalc = feuerRes;
		this.wasserResCalc = wasserRes;
		this.windResCalc = windRes;
		this.eisResCalc = eisRes;
	}
	
	public void addBuff(Buff buff) {
		this.buffs.add(buff);
		calculateBuff(buff);
	}
	
	public void calculateBuffs() {
		Iterator<Buff> iter = buffs.iterator();
		while (iter.hasNext()) {
			Buff buff = iter.next();
			buff.lowerTurnCount();
			if (buff.getTurnCount()<0) {
				switch (buff.getStat()) {
				case atk:
					atkCalc = atk;
					break;
				case blitzRes:
					blitzResCalc = blitzRes;
					break;
				case def:
					defCalc = def;
					break;
				case eisRes:
					eisResCalc = eisRes;
					break;
				case feuerRes:
					feuerResCalc = feuerRes;
					break;
				case hp:
					hpCalc = hp;
					break;
				case mag:
					magCalc = mag;
					break;
				case spr:
					sprCalc = spr;
					break;
				case wasserRes:
					wasserResCalc = wasserRes;
					break;
				case windRes:
					windResCalc = windRes;
					break;
				default:
					break;
				
				}
				iter.remove();
			} else {
				calculateBuff(buff);
			}
			
		}
	}
	
	
	
	private void calculateBuff(Buff buff) {
		switch (buff.getStat()) {
		case atk:
			atkCalc = (int) (atkCalc+(atk*buff.getModifier()));
			break;
		case blitzRes:
			blitzResCalc = (int) (blitzResCalc+(blitzRes*buff.getModifier()));
			break;
		case def:
			defCalc = (int) (defCalc+(def*buff.getModifier()));
			break;
		case eisRes:
			eisResCalc = (int) (eisResCalc+(eisRes*buff.getModifier()));
			break;
		case feuerRes:
			feuerRes = (int) (feuerRes+(feuerRes*buff.getModifier()));
			break;
		case hp:
			hpCalc = (int) (hpCalc+(hp*buff.getModifier()));
			break;
		case mag:
			magCalc = (int) (magCalc+(mag*buff.getModifier()));
			break;
		case spr:
			sprCalc = (int) (sprCalc+(spr*buff.getModifier()));
			break;
		case wasserRes:
			wasserResCalc = (int) (wasserResCalc+(wasserRes*buff.getModifier()));
			break;
		case windRes:
			windResCalc = (int) (windResCalc+(windRes*buff.getModifier()));
			break;
		default:
			break;

		}

		
	}

	public void losehpPhy(int attackWert) {
		
		if (System.currentTimeMillis()-lastCall<ModelConstants.WAIT_MULTIPLIER) {
			multiplier = multiplier + 0.5;
		} else {
			multiplier = 1;
		}
		lastCall = System.currentTimeMillis();
		
		int attacke = (int) ((attackWert-this.defCalc)*multiplier);
		if (attacke<=0) {
			attacke = 1;
		}
		this.hpCalc = (int) (this.hpCalc - attacke);
		if (this.hpCalc<0) {
			this.hpCalc=0;
			this.setIsDead(true);
		}
		
		if (multiplier>=2) {
			MainFightController.getNumbersToDisplay().add(new NumbersToDisplay("Chain:"+multiplier,this));
		}
		
		MainFightController.getNumbersToDisplay().add(new NumbersToDisplay(attacke,this, AbilityArt.Angriff));

	}
	
public void losehpMag(int attackWert, Elemente ele) {
		
		if (System.currentTimeMillis()-lastCall<ModelConstants.WAIT_MULTIPLIER) {
			multiplier = multiplier + 0.5;
		} else {
			multiplier = 1;
		}
		lastCall = System.currentTimeMillis();
		double elementMulti = 1;
		switch (ele) {
		case blitz:
			elementMulti = (this.blitzResCalc+100)/100;
			break;
		case eis:
			elementMulti = (this.eisResCalc+100)/100;
			break;
		case feuer:
			elementMulti = (this.feuerResCalc+100)/100;
			break;
		case wasser:
			elementMulti = (this.wasserResCalc+100)/100;
			break;
		case wind:
			elementMulti = (this.windResCalc+100)/100;
			break;
		}
		int attacke = (int) (((attackWert-this.spr)*multiplier)/elementMulti);
		if (attacke<=0) {
			attacke = 1;
		}
		this.hpCalc = (int) (this.hpCalc - attacke);
		if (this.hpCalc<0) {
			this.hpCalc=0;
			this.setIsDead(true);
		}
		
		if (multiplier>=2) {
			MainFightController.getNumbersToDisplay().add(new NumbersToDisplay("Chain:"+multiplier,this));
		}
		
		MainFightController.getNumbersToDisplay().add(new NumbersToDisplay(attacke,this, AbilityArt.Magie));

	}
	
	
	public void getsHealing(int healingWert, double multiplier) {
		int healing = (int) (healingWert*multiplier);
		
		this.hpCalc = (int) (this.hpCalc + (healing));
		if (this.hpCalc>this.hp) {
			this.hpCalc=this.hp;
		}
		MainFightController.getNumbersToDisplay().add(new NumbersToDisplay(healing,this, AbilityArt.Heilung));
	}
	
	public void restoreHPToPercent(double percent) {
		this.hpCalc = (int) (hp*percent);
		if (this.hpCalc>this.hp) {
			this.hpCalc=this.hp;
		}
	}


	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int pos) {
		this.position = pos;
	}
	
	
	
	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpr() {
		return spr;
	}

	public void setSpr(int spr) {
		this.spr = spr;
	}

	public int getMag() {
		return mag;
	}

	public void setMag(int mag) {
		this.mag = mag;
	}

	public int getThreat() {
		return threat;
	}

	public void setThreat(int threat) {
		this.threat = threat;
	}

	public int getBlitzRes() {
		return blitzRes;
	}

	public void setBlitzRes(int blitzRes) {
		this.blitzRes = blitzRes;
	}

	public int getFeuerRes() {
		return feuerRes;
	}

	public void setFeuerRes(int feuerRes) {
		this.feuerRes = feuerRes;
	}

	public int getWasserRes() {
		return wasserRes;
	}

	public void setWasserRes(int wasserRes) {
		this.wasserRes = wasserRes;
	}

	public int getWindRes() {
		return windRes;
	}

	public void setWindRes(int windRes) {
		this.windRes = windRes;
	}

	public int getEisRes() {
		return eisRes;
	}

	public void setEisRes(int eisRes) {
		this.eisRes = eisRes;
	}

	public int getAtk() {
		return atk;
	}
	
	public void setIsDead(Boolean val) {
		this.isDead = val;
	}
	
	public Boolean getIsDead() {
		return this.isDead;
	}
	
	public int getHpCalc() {
		return hpCalc;
	}
		
	public int getHp() {
		return hp;
	}

	public int getDefCalc() {
		return defCalc;
	}

	public int getSprCalc() {
		return sprCalc;
	}

	public int getMagCalc() {
		return magCalc;
	}

	public int getAtkCalc() {
		return atkCalc;
	}

	public int getBlitzResCalc() {
		return blitzResCalc;
	}

	public int getFeuerResCalc() {
		return feuerResCalc;
	}

	public int getWasserResCalc() {
		return wasserResCalc;
	}

	public int getWindResCalc() {
		return windResCalc;
	}

	public int getEisResCalc() {
		return eisResCalc;
	}
	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMpCalc() {
		return mpCalc;
	}
	
	public String getImageSource() {
		return this.name.toLowerCase();
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public double getProgressHp() {
		return (double) this.hpCalc/(double) this.hp;
	}

	public String getProgressHpString() {
		return this.hpCalc+"/"+this.hp;
	}
	
	public double getProgressMp() {
		return (double) this.mpCalc/(double) this.mp;
	}

	public String getProgressMpString() {
		return this.mpCalc+"/"+this.mp;
	}

	public void setCalcAsDefaults() {
		this.hpCalc = hp;
		this.defCalc = def;
		this.sprCalc = spr;
		this.magCalc = mag;
		this.atkCalc = atk;
		this.mpCalc = mp;
		this.isDead = false;
		this.blitzResCalc = blitzRes;
		this.feuerResCalc = feuerRes;
		this.wasserResCalc = wasserRes;
		this.windResCalc = windRes;
		this.eisResCalc = eisRes;
	}
}
