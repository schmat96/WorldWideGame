package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@Column(name="NAME", length=20, nullable=true)
	private String name;
	
	@Column(name="modifier", length=20, nullable=true)
	private int modifier;
	
	@Transient
	Charakter markedCharakter = null;
	
	@Enumerated(EnumType.ORDINAL)
	public AbilityArt getAbilityArt() {
		return art;
	}
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
	
	public void setMarkedCharakter(Charakter choosenCharakter) {
		
		this.markedCharakter = choosenCharakter;
	}
	
	public void execute(DataBeanFight dataBeanFight, Charakter charakter) {
		switch(art) {
		case Angriff:
			dataBeanFight.makeDmgPhy((int) (charakter.getAtkCalc()*modifier));
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
			markedCharakter.getsHealing((int) (charakter.getSprCalc()*modifier), 1);
			break;
		case Raise:
			this.markedCharakter.setIsDead(false);
			this.markedCharakter.setEndTurn(true);
			break;
		default:
			break;
		
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
