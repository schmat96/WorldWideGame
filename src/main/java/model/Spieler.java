package model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;




@Entity
@Table(name="spieler", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Spieler {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="NAME", length=20, nullable=true)
	private String name;
	
	@Column(name="PASSWORT", length=20, nullable=true)
	private String passwort;
	
	@Column(name="EXPERIENCE", length=20, nullable=true)
	private int exp;
	
	@Column(name="GELD", length=20, nullable=true)
	private int geld;
	
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "besitzt", 
        joinColumns =  @JoinColumn(name = "spieler_id") , 
        inverseJoinColumns = { @JoinColumn(name = "charakter_id") }
    )
    Set<Charakter> charakters = new HashSet<>();
	
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
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getGeld() {
		return geld;
	}
	public void setGeld(int geld) {
		this.geld = geld;
	}
	public Set<Charakter> getCharakters() {
		return charakters;
	}
	public void getCharakters(Set<Charakter> besitzt) {
		this.charakters = besitzt;
	}
	
}
