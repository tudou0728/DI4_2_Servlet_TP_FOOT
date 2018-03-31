package modele;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Calendrier {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int c_id;
	private Date date;
	private String ville;
	private String stade;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="calendrier")
	private FootMatch footMatch;

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getStade() {
		return stade;
	}

	public void setStade(String stade) {
		this.stade = stade;
	}

	public FootMatch getFootMatch() {
		return footMatch;
	}

	public void setFootMatch(FootMatch footMatch) {
		this.footMatch = footMatch;
	}

	
}
