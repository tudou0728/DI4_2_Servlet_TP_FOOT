package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modele.Calendrier;
import modele.FootMatch;

public class CalendrierDAO 
{
	public List<Calendrier> getAll(EntityManager em) 
	{
		String sql = "from Calendrier";
		Query query = em.createQuery(sql);
		List<Calendrier> cals = query.getResultList();
		return cals;
	}
	public List<Calendrier> rechercherId(EntityManager em, String id) 
	{
		String sql = "from Calendrier calendrier where calendrier.c_id=:id";
		Query query = em.createQuery(sql);
		query.setParameter("id", Integer.parseInt(id));
		List<Calendrier> cals = query.getResultList();
		return cals;
	}
	
	public void modifierC(EntityManager em,String id, Date date,String ville,String stade, FootMatch footMatch) {
		Calendrier calendrier=rechercherId(em, id).get(0);
		em.getTransaction().begin();
		calendrier.setDate(date);
		calendrier.setStade(stade);
		calendrier.setVille(ville);
		calendrier.setFootMatch(footMatch);
		em.getTransaction().commit();
	}
	
	public Boolean supprimerC(EntityManager em, String id) 
	{
		if (rechercherId(em, id) != null) {
			em.getTransaction().begin();
			em.remove(rechercherId(em, id).get(0));
			em.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}
	
	public void AjouterC(EntityManager em,Date date,String ville,String stade, FootMatch footMatch) {
		Calendrier calendrier = new Calendrier();
		em.getTransaction().begin();
		calendrier.setDate(date);
		calendrier.setStade(stade);
		calendrier.setVille(ville);
		calendrier.setFootMatch(footMatch);
		em.persist(calendrier);
		em.getTransaction().commit();
	}
}
