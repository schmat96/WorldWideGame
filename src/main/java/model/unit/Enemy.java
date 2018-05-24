package model.unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import model.Ability;
import model.AbilityArt;
import model.AttackPattern;
import model.Buff;
import model.ChallengeTurn;
import model.DataBeanFight;
import model.Elemente;
import model.Stats;

@Entity
@Table(name="enemy", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Enemy extends Unit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true, length=11)
	private int id;
	
	@ManyToMany(mappedBy = "enemies")
    private Set<ChallengeTurn> challengeTurns = new HashSet<>();
	
	public String getImageSource() {
		return this.name.toLowerCase();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Transient
	private ArrayList<Ability> set;


	
	public void hisTurn(ArrayList<Charakter> choosenCharakters, DataBeanFight dataBeanFight) {
		
		if (set == null) {
			initializeAbilitySet();
		}
		
		
		Random rng = new Random();
		
		ArrayList<Charakter> alive = new ArrayList<Charakter>();
		Iterator<Charakter> iter = choosenCharakters.iterator();
		
		while (iter.hasNext()) {
			Charakter charakter = iter.next();
			if (charakter.getIsDead()==false) { alive.add(charakter); }
			
		}
		
		switch (this.getName()) {
			case "Gilgamesh":
				alive.get(rng.nextInt(alive.size())).losehpPhy(rng.nextInt(this.atkCalc));
				alive.get(rng.nextInt(alive.size())).losehpPhy(rng.nextInt(this.atkCalc));
				alive.get(rng.nextInt(alive.size())).losehpPhy(rng.nextInt(this.atkCalc));
				Buff buff = new Buff(Stats.def, 100.0, 999);
				this.addBuff(buff);
				break;
			case "Susano":
				choosenCharakters.get(rng.nextInt(choosenCharakters.size())).setIsDead(true);
				if (!this.getIsDead()) {
					this.getsHealing(100, 1);
				}
				break;
			case "Zeus":
				Iterator<Charakter> iterNew = alive.iterator();
				Charakter mostBlitzRes = null;
				while (iterNew.hasNext()) {
					Charakter charakter = iterNew.next();
					if (mostBlitzRes==null) { mostBlitzRes=charakter; } else {
						if (charakter.getBlitzResCalc()>mostBlitzRes.getBlitzResCalc()) { mostBlitzRes=charakter;}
					}
				}
				if (mostBlitzRes!=null) {
					mostBlitzRes.losehpMag(this.magCalc, Elemente.blitz);
				}
				
				break;
			default:
				alive.get(rng.nextInt(alive.size())).losehpPhy(rng.nextInt(this.atkCalc));
				break;
		}
		
		
	}
	
	private void initializeAbilitySet() {
		
		set = new ArrayList<Ability>();
		
		AttackPattern ap = new AttackPattern();
		ap.setAttackPattern("100=0.5;500=0.5");
		
		AttackPattern ap2 = new AttackPattern();
		ap.setAttackPattern("200=0.25;700=0.25;300=0.25;1000=0.25");
		
		Ability ab = new Ability();
		ab.setAttackPattern(ap);
		ab.setArt(AbilityArt.Angriff);
		set.add(ab);
		
		Ability ab3 = new Ability();
		ab3.setAttackPattern(ap);
		ab3.setArt(AbilityArt.Heilung);
		set.add(ab3);
		
		Ability ab2 = new Ability();
		ab2.setAttackPattern(ap2);
		ab2.setArt(AbilityArt.Angriff);
		set.add(ab2);
	}

	

}
