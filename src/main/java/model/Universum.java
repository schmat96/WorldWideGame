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

import model.unit.Charakter;

@Entity
@Table(name="universum", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"id_universum"})})
public class Universum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_universum", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="NAME", length=20, nullable=true)
	private String name;
	
	@OneToMany(mappedBy = "universum")
    private Set<Charakter> charakters = new HashSet<>();

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
	
	public Set<Charakter> getCharakters() {
		return this.charakters;
	}

}
