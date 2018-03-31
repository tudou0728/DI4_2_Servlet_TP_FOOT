package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modele.FootMatch;
import modele.JouerMatch;
import modele.Joueur;

public class JouerMatchDAO 
{
	public List<JouerMatch> getAll(EntityManager em) {
		String sql = "from JouerMatch";
		Query query = em.createQuery(sql);
		List<JouerMatch> jouerMatch = query.getResultList();
		return jouerMatch;
	}
	
	public List<JouerMatch> rechercherJouerM(EntityManager em,String nom) {
		em.getTransaction().begin();
		String sql = "from JouerMatch jouerMatch  where jouerMatch.joueur.nom = :nom";
		Query query = em.createQuery(sql);
		query.setParameter("nom", nom);
		List<JouerMatch> jouerMatch = query.getResultList();			
		return jouerMatch;
	}
	
	public List<JouerMatch> rechercherMatchId(EntityManager em,String id) {
		em.getTransaction().begin();
		String sql = "from JouerMatch jouerMatch  where jouerMatch.footMatch.m_id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("id", id);
		List<JouerMatch> jouerMatch = query.getResultList();			
		return jouerMatch;
	}
	
	public void modifierJouerM(EntityManager em,JouerMatch jouerMatch,String nom,
			Joueur joueur,FootMatch footMatch) {
		
		rechercherJouerM(em, nom);
		jouerMatch.setFootMatch(footMatch);
		jouerMatch.setJoueur(joueur);
		em.persist(footMatch);
		em.persist(joueur);
		em.persist(jouerMatch);
		em.getTransaction().commit();
	}
	
	public void ajouterJM(EntityManager entityManager,Joueur joueur,FootMatch footMatch)
	{
		JouerMatch jouerMatch=new JouerMatch();
		entityManager.getTransaction().begin();
		jouerMatch.setFootMatch(footMatch);
		jouerMatch.setJoueur(joueur);
		entityManager.persist(jouerMatch);
		entityManager.getTransaction().commit();
	}
}
