package modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Equipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int e_id;
	
	private String nom;
	private int nb_par;
	private int nb_vic;
	private int classement;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="equipe")
	private List<Joueur> joueur=new ArrayList<Joueur>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="equipe1")
	private List<FootMatch> footMatch=new ArrayList<FootMatch>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="equipe2")
	private List<FootMatch> footMatch2=new ArrayList<FootMatch>();

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNb_par() {
		return nb_par;
	}

	public void setNb_par(int nb_par) {
		this.nb_par = nb_par;
	}

	public int getNb_vic() {
		return nb_vic;
	}

	public void setNb_vic(int nb_vic) {
		this.nb_vic = nb_vic;
	}

	public int getClassement() {
		return classement;
	}

	public void setClassement(int classement) {
		this.classement = classement;
	}

	public List<Joueur> getJoueur() {
		return joueur;
	}

	public void setJoueur(List<Joueur> joueur) {
		this.joueur = joueur;
	}

	public List<FootMatch> getFootMatch() {
		return footMatch;
	}

	public void setFootMatch(List<FootMatch> footMatch) {
		this.footMatch = footMatch;
	}

	public List<FootMatch> getFootMatch2() {
		return footMatch2;
	}

	public void setFootMatch2(List<FootMatch> footMatch2) {
		this.footMatch2 = footMatch2;
	}	
}
