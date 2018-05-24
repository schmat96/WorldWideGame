package model;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import model.unit.Enemy;


@Entity
@Table(name="turn", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"id_turn"})})
public class ChallengeTurn {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_turn", nullable=false, unique=true, length=11)
	private int id_turn;
	
	@ManyToMany(mappedBy = "turns")
    private Set<Challenge> challenges = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "turn_enemy", 
        joinColumns =  @JoinColumn(name = "turn_id") , 
        inverseJoinColumns = { @JoinColumn(name = "enemy_id") }
    )
	private Set<Enemy> enemies;
	
	public Set<Challenge> getCharakters() {
		return this.challenges;
	}
	
	public Set<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public int getIdTurn() {
		return this.id_turn;
	}

}
