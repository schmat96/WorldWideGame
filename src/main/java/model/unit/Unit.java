package model.unit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import model.Ability;
import model.Buff;

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
	
	protected void outline() {
		System.out.println(name);
		System.out.println("------------------Base Stats----------------------");
		System.out.println(hp+"|"+def+"|"+spr+"|"+mag+"|"+atk+"|"+blitzRes+"|"+feuerRes+"|"+wasserRes+"|"+wasserRes+"|"+windRes+"|"+eisRes);
		System.out.println("------------------Calculated Stats----------------------");
		System.out.println(hpCalc+"|"+defCalc+"|"+sprCalc+"|"+magCalc+"|"+atkCalc+"|"+blitzResCalc+"|"+feuerResCalc+"|"+wasserResCalc+"|"+wasserResCalc+"|"+windResCalc+"|"+eisResCalc);
		System.out.println("");
	}
	
	protected void calculateBuffs() {
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
				switch (buff.getStat()) {
				case atk:
					atkCalc = (int) (atk+(atk*buff.getModifier()));
					break;
				case blitzRes:
					blitzResCalc = (int) (blitzRes+(blitzRes*buff.getModifier()));
					break;
				case def:
					defCalc = (int) (def+(def*buff.getModifier()));
					break;
				case eisRes:
					eisResCalc = (int) (eisRes+(eisRes*buff.getModifier()));
					break;
				case feuerRes:
					feuerRes = (int) (feuerRes+(feuerRes*buff.getModifier()));
					break;
				case hp:
					hpCalc = (int) (hp+(hp*buff.getModifier()));
					break;
				case mag:
					magCalc = (int) (mag+(mag*buff.getModifier()));
					break;
				case spr:
					sprCalc = (int) (spr+(spr*buff.getModifier()));
					break;
				case wasserRes:
					wasserResCalc = (int) (wasserRes+(wasserRes*buff.getModifier()));
					break;
				case windRes:
					windResCalc = (int) (windRes+(windRes*buff.getModifier()));
					break;
				default:
					break;

				}
			}
			
		}
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
	
	public int losehpPhy(int attackWert, double multiplier) {
		int attacke = (attackWert-this.def);
		if (attacke<=0) {
			attacke = 1;
		}
		this.hpCalc = (int) (this.hpCalc - ((attacke)*multiplier));
		if (this.hpCalc<0) {
			this.hpCalc=0;
		}
		return attacke;
	}
	
	public void getsHealing(int healingWert, double multiplier) {
		int healing = (int) (healingWert*multiplier);
		
		this.hpCalc = (int) (this.hpCalc + (healing));
		if (this.hpCalc>this.hp) {
			this.hpCalc=this.hp;
		}
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

	public void setCalcAsDefaults() {
		this.hpCalc = hp;
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


	
	
}
