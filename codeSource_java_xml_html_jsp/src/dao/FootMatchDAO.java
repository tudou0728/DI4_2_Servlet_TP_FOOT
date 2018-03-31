package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modele.Calendrier;
import modele.Equipe;
import modele.FootMatch;
import modele.JouerMatch;

public class FootMatchDAO 
{
	public List<FootMatch> getDeja(EntityManager em) 
	{
		String sql = " from FootMatch f where f.res1 not LIKE 'NULL'";
		Query query = em.createQuery(sql);
		List<FootMatch> footMatch = query.getResultList();
		return footMatch;
	}
	
	public List<FootMatch> getPasEnCore(EntityManager em) 
	{
		String sql = " from FootMatch f where f.res1='NULL'";
		Query query = em.createQuery(sql);
		List<FootMatch> footMatch = query.getResultList();
		return footMatch;
	}
	
	public List<FootMatch> rechercherE(EntityManager em, Date date) 
	{
		String sql = "from FootMatch footMatch  where footMatch.calendrier.date = :date";
		Query query = em.createQuery(sql);
		query.setParameter("date", date);
		List<FootMatch> footMatch = query.getResultList();			
		return footMatch;
	}
	
	public List<FootMatch> rechercherId(EntityManager em, String id) 
	{
		String sql = "from FootMatch footMatch  where footMatch.m_id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("id", Integer.parseInt(id));
		List<FootMatch> footMatch = query.getResultList();			
		return footMatch;
	}
	
	public void modifierFM(EntityManager em,String id,String res1,String res2,Equipe equipe1,Equipe equipe2,
			Calendrier calendrier,List<JouerMatch> jouerMatch) {
		Date date=new Date();
		FootMatch footMatch=rechercherId(em, id).get(0);
		em.getTransaction().begin();
		footMatch.setRes1(res1);
		footMatch.setRes2(res2);
		footMatch.setEquipe1(equipe1);
		footMatch.setEquipe2(equipe2);
		footMatch.setCalendrier(calendrier);
		em.getTransaction().commit();
	}
	
	public Boolean supprimerFM(EntityManager em, int id) 
	{
		Date date=new Date();
		List<FootMatch> footMatchs=rechercherId(em, String.valueOf(id));
		if (footMatchs != null && footMatchs.size()>0) 
		{		
			FootMatch footMatch=footMatchs.get(0);
			if(footMatch.getCalendrier().getDate().getTime()>date.getTime())
			{
				em.getTransaction().begin();
				em.remove(footMatch.getCalendrier());
				em.remove(footMatch);
				em.getTransaction().commit();
				System.out.println("OK");
				return true;
			}
			else
			{
				System.out.println("FAIL");
				return false;
			}
		} else {
			System.out.println("FAIL");
			return false;
		}
	}
	
	public FootMatch AjouterFM(EntityManager em, String res1, String res2, Equipe equipe1, Equipe equipe2,
			Calendrier calendrier, List<JouerMatch> jouerMatch) 
	{
		FootMatch footMatch = new FootMatch();
		em.getTransaction().begin();
		footMatch.setEquipe1(equipe1);
		footMatch.setEquipe2(equipe2);
		footMatch.setCalendrier(calendrier);
		footMatch.setJouerMatch(jouerMatch);
		footMatch.setRes1(res1);
		footMatch.setRes2(res2);
		em.persist(equipe1);
		em.persist(equipe2);
		em.persist(calendrier);
		for (int i = 0; i < jouerMatch.size(); i++) {
			em.persist(jouerMatch.get(i));
		}
		em.persist(footMatch);
		em.getTransaction().commit();
		return footMatch;
	}
}
