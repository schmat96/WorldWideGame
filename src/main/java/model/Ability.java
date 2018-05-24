package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import model.unit.Charakter;

@Entity
@Table(name="ability", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Ability {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	
	@ManyToMany(mappedBy = "abilities")
    private Set<Charakter> charakters = new HashSet<>();
	
	private AbilityArt art;
	
	private Elemente element;
	
	@Column(name="NAME", length=20, nullable=true)
	private String name;
	
	@Column(name="modifier", length=20, nullable=true)
	private int modifier;
	
	@Column(name="mana", length=20, nullable=true)
	private int mana;
	
	@ManyToOne
    @JoinColumn(name="attackPattern_id", nullable=false)
	private AttackPattern attackPattern;
	
	@Enumerated(EnumType.ORDINAL)
	public AbilityArt getAbilityArt() {
		return art;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public Elemente getElement() {
		return element;
	}
	
	public int getMana() {
		return this.mana;
	}
	
	@Transient
	private Charakter markedCharakter = null;
	
	
	public void setArt(AbilityArt art) {
		this.art = art;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getModifier() {
		return modifier;
	}
	
	public void setAttackPattern(AttackPattern attackPattern) {
		this.attackPattern = attackPattern;
	}
	
	public AttackPattern getAttackPattern() {
		return this.attackPattern;
	}
	
	public void setMarkedCharakter(Charakter choosenCharakter) {
		
		this.markedCharakter = choosenCharakter;
	}
	
	public void execute(DataBeanFight dataBeanFight, Charakter charakter) {
		switch(art) {
		case Angriff:
			String patter = attackPattern.getAttackPattern();
			AttackPatternThread apThread =  new AttackPatternThread(patter, dataBeanFight, this, charakter);
			apThread.start();
			apThread.setRunning(true);
			break;
		case BuffEinzeln:
			//Buff buff = new Buff(stat, this.getModifier(), 1);
			//charakter.addBuff(buff);
			break;
		case BuffGegner:
			//Buff buff3 = new Buff(stat, this.getModifier(), 1);
			//charakter.addBuff(buff3);
			break;
		case BuffGruppe:
			break;
		case BuffSelf:
			break;
		case Defense:
			Buff buff1 = new Buff(Stats.def, 10.0, 1);
			charakter.addBuff(buff1);
			break;
		case Heilung:
			markedCharakter.getsHealing((int) (charakter.getSprCalc()*modifier), 1);
			break;
		case Dispel:
			dataBeanFight.dispel();
			break;
		case Raise:
			this.markedCharakter.setIsDead(false);
			this.markedCharakter.restoreHPToPercent(0.1);
			this.markedCharakter.setEndTurn(true);
			break;
		case Magie:
			String patter2 = attackPattern.getAttackPattern();
			AttackPatternThread apThread2 = new AttackPatternThread(patter2, dataBeanFight, this, charakter);
			apThread2.start();
			apThread2.setRunning(true);
			break;
		default:
			break;
		
		}
		
		charakter.lostmp(this.getMana());
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
