package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modele.Equipe;
import modele.Joueur;

public class JoueurDAO 
{
	public List<Joueur> getAll(EntityManager em) {
		String sql = "from Joueur";
		Query query = em.createQuery(sql);
		List<Joueur> joueurs = query.getResultList();
		return joueurs;
	}
	
	public List<Joueur> rechercherId(EntityManager em, String id) 
	{
		int id1=Integer.parseInt(id);
		String sql = "from Joueur joueur where joueur.j_id=:id ";
		Query query = em.createQuery(sql);
		query.setParameter("id", id1);
		List<Joueur> joueurs = query.getResultList();
		return joueurs;
	}
	
	public List<Joueur> rechercherJ(EntityManager em, String nom) 
	{
		String sql = "from Joueur joueur where joueur.nom=:nom ";
		Query query = em.createQuery(sql);
		query.setParameter("nom", nom);
		List<Joueur> joueurs = query.getResultList();
		return joueurs;
	}
	
	public void modifierJ(EntityManager em,String id,String nom, int buts,String role,
			double note,Equipe equipe) {
		List<Joueur> joueurs=rechercherId(em, id);
		Joueur joueur = joueurs.get(0);
		joueur.setRole(role);
		joueur.setNom(nom);
		joueur.setButs(buts);
		joueur.setNote(note);
		joueur.setEquipe(equipe);
		em.getTransaction().commit();	
	}
	

	public void ajouterJ(EntityManager em, String nom, String role,double note,int buts,Equipe equipe) {
		Joueur joueur = new Joueur();
		em.getTransaction().begin();
		joueur.setNom(nom);
		joueur.setButs(buts);
		joueur.setNote(note);
		joueur.setRole(role);
		joueur.setEquipe(equipe);
		em.persist(joueur);
		em.getTransaction().commit();
	}
	
	public Boolean supprimerJ(EntityManager em, int id) 
	{
		if (em.find(Joueur.class, id) != null) 
		{
			em.getTransaction().begin();
			em.remove(em.find(Joueur.class, id));
			em.getTransaction().commit();
			return true;				
		} else {
			return false;
		}
	}
}
