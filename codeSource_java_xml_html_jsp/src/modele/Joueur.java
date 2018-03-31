package modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Joueur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int j_id;
	
	private String nom;
	private int buts;
	private String role;
	private double note;
	
	@ManyToOne
	private Equipe equipe;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="joueur")
	private List<JouerMatch> jouerMatch=new ArrayList<JouerMatch>();

	public List<JouerMatch> getJouerMatch() {
		return jouerMatch;
	}

	public void setJouerMatch(List<JouerMatch> jouerMatch) {
		this.jouerMatch = jouerMatch;
	}

	public int getJ_id() {
		return j_id;
	}

	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getButs() {
		return buts;
	}

	public void setButs(int buts) {
		this.buts = buts;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	
}
