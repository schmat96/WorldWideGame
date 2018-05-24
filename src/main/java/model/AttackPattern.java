package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="attackpattern", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"id_attackPattern"})})
public class AttackPattern {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_attackPattern", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="pattern", length=400, nullable=true)
	private String attackPattern;
	
	@OneToMany(mappedBy = "attackPattern")
    private Set<Ability> abilities = new HashSet<>();

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttackPattern() {
		return attackPattern;
	}
	public void setAttackPattern(String attackPattern) {
		this.attackPattern = attackPattern;
	}
	
	public Set<Ability> getAbilities() {
		return this.abilities;
	}
}
