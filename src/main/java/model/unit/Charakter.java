package model.unit;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import model.Ability;
import model.Spieler;
import model.Universum;

@Entity
@Table(name="charakter", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Charakter extends Unit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true, length=11)
	private int id;
	

	
	@ManyToMany(mappedBy = "charakters")
    private Set<Spieler> spieler = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "char_ability", 
        joinColumns =  @JoinColumn(name = "charakter_id") , 
        inverseJoinColumns = { @JoinColumn(name = "ability_id") }
    )
    Set<Ability> abilities = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="universum_id", nullable=false)
    private Universum universum;
		
	@Transient
	private Ability choosenAbility;
	
	@Transient
	private Boolean endTurn;

	

	public Ability getChoosenAbility() {
		return choosenAbility;
	}
	
	public void setChoosenAbility(Ability ability) {
		this.choosenAbility = ability;
	}

	public void setEndTurn(Boolean value) {
		this.endTurn = value;
	}

	public Boolean getEndTurn() {
		return this.endTurn;
	}
	
	public int getId() {
		return id;
	}
	public Set<Ability> getAbilities() {
		return abilities;
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
	public Universum getUniversum() {
		return universum;
	}

}
