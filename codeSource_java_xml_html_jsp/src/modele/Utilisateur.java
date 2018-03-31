package modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilisateur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int u_id;
	@Column(nullable=false)
	private String login;
	@Column(nullable=false)
	private String mdp;
	@Column(nullable=false)
	private boolean autorise;//si ==1,il peut modifier la BD, si non, il ne peut pas.

	public boolean isAutorise() {
		return autorise;
	}

	public void setAutorise(boolean autorise) {
		this.autorise = autorise;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
