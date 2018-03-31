package modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class FootMatch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int m_id;
	
	@Column(nullable=true, columnDefinition="VARCHAR(255) default 'NULL'")
	private String  res1;
	@Column(nullable=true, columnDefinition="VARCHAR(255) default 'NULL'")
	private String  res2;
	
	@ManyToOne
	private Equipe equipe1;
	@ManyToOne
	private Equipe equipe2;
	
	@OneToOne
	private Calendrier calendrier;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="footMatch")
	private List<JouerMatch> jouerMatch=new ArrayList<JouerMatch>();


	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getRes1() {
		return res1;
	}

	public void setRes1(String res1) {
		this.res1 = res1;
	}

	public String getRes2() {
		return res2;
	}

	public void setRes2(String res2) {
		this.res2 = res2;
	}

	public Equipe getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}

	public Equipe getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}

	public Calendrier getCalendrier() {
		return calendrier;
	}

	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}

	public List<JouerMatch> getJouerMatch() {
		return jouerMatch;
	}

	public void setJouerMatch(List<JouerMatch> jouerMatch) {
		this.jouerMatch = jouerMatch;
	}

	
}
