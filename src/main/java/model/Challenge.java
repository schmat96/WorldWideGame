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


@Entity
@Table(name="challenge", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"id_challenge"})})
public class Challenge {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_challenge", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="name", length=20, nullable=true)
	private String name;
	
	@Column(name="schwierigkeit", length=20, nullable=true)
	private int schwierigkeit;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "challenge_turn", 
        joinColumns =  @JoinColumn(name = "challenge_id") , 
        inverseJoinColumns = { @JoinColumn(name = "turn_id") }
    )
    Set<ChallengeTurn> turns = new HashSet<>();
	
	public int getSchwierigkeit() {
		return this.schwierigkeit;
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
	
	public Set<ChallengeTurn> getTurns() {
		return this.turns;
	}
	
	/*
	public Set<Enemy> getCharakters() {
		return this.enemies;
	}
	*/

}