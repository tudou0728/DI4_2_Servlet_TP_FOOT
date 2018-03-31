package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import modele.Equipe;
import modele.FootMatch;
import modele.Joueur;

public class EquipeDAO {
	
	public List<Equipe> getAll(EntityManager em) 
	{
		String sql = " from Equipe ";
		Query query = em.createQuery(sql);
		List<Equipe> equipes = query.getResultList();
		return equipes;
	}
	
	public List<Equipe> rechercherE(EntityManager em, String nom) 
	{
		String sql = "Select equipe from Equipe equipe where equipe.nom=:nom";
		Query query = em.createQuery(sql);
		query.setParameter("nom", nom);
		List<Equipe> equipes=query.getResultList();
		return equipes;
	}
	
	public List<Equipe> rechercherEId(EntityManager em, int id) 
	{
//		em.getTransaction().begin();
		String sql = "Select equipe from Equipe equipe where equipe.e_id=:id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		List<Equipe> equipes=query.getResultList();
		return equipes;
	}

	public void ajouterE(EntityManager em, String nom, List<Joueur> joueurs,int classement,int nbP,int nbV) {
		Equipe equipe = new Equipe();
		em.getTransaction().begin();
		equipe.setNom(nom);
		equipe.setNb_par(nbP);
		equipe.setNb_vic(nbV);
		equipe.setJoueur(joueurs);
		em.persist(equipe);
		for (int i = 0; i < joueurs.size(); i++) {
			em.persist(joueurs.get(i));
		}	
		em.getTransaction().commit();
	}

	public void modifierE(EntityManager em, Equipe equipe, int id,String nom, int nb_par, int nb_vic, int classement,
			List<Joueur> joueur, List<FootMatch> footMatch, List<FootMatch> footMatch2) 
	{
		rechercherEId(em, id);
		em.getTransaction().begin();
		equipe.setNom(nom);
		equipe.setNb_par(nb_par);
		equipe.setNb_vic(nb_vic);
		equipe.setNom(nom);
		equipe.setClassement(classement);
		equipe.setJoueur(joueur);
		equipe.setFootMatch(footMatch);
		equipe.setFootMatch2(footMatch2);
		em.getTransaction().commit();	
	}
	
	public Boolean supprimerE(EntityManager em, int id) throws SQLException,RollbackException 
	{
		if (rechercherEId(em, id) != null && rechercherEId(em, id).size()>0) 
		{
			em.getTransaction().begin();
			em.remove(rechercherEId(em, id).get(0));
			em.getTransaction().commit();
			return true;
		} 
		else {
			return false;
		}
		
	}
	
}
