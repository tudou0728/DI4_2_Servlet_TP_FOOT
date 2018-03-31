package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modele.Utilisateur;

public class UtilisateurDAO 
{
	public List<Utilisateur> selectUtilisateur(EntityManager entityManager,String username,String password)
	{
		boolean resultat=false;
		String sql="from Utilisateur utilisateur where utilisateur.login = '" + username + "' and utilisateur.mdp = '" + password + "'"; 
		Query query = entityManager.createQuery(sql);
		List<Utilisateur> resultList = new ArrayList<Utilisateur>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public boolean modifierAuthorise(Utilisateur utilisateur)
	{
		boolean resultat=false;
		if(utilisateur.isAutorise())
		{
			resultat=true;
		}
		return resultat;
	}
}
