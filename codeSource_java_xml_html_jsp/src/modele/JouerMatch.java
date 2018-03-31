package modele;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class JouerMatch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int jM_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Joueur joueur;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private FootMatch footMatch;

	public int getjM_id() {
		return jM_id;
	}

	public void setjM_id(int jM_id) {
		this.jM_id = jM_id;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public FootMatch getFootMatch() {
		return footMatch;
	}

	public void setFootMatch(FootMatch footMatch) {
		this.footMatch = footMatch;
	}
}
