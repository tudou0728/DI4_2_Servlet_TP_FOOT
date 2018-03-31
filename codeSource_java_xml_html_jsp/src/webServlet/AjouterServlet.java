package webServlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controleur.ConnBD;
import dao.CalendrierDAO;
import dao.EquipeDAO;
import dao.FootMatchDAO;
import dao.JouerMatchDAO;
import dao.JoueurDAO;
import modele.Calendrier;
import modele.Equipe;
import modele.FootMatch;
import modele.JouerMatch;
import modele.Joueur;

//la classe servlet pour ajouter des informations
public class AjouterServlet extends HttpServlet
{
	private ConnBD connBD=new ConnBD();
	private EquipeDAO equipeDAO=new EquipeDAO();
	private JoueurDAO joueurDAO=new JoueurDAO();
	private FootMatchDAO footMatchDAO=new FootMatchDAO();
	private CalendrierDAO calendrierDAO=new CalendrierDAO();
	private JouerMatchDAO jouerMatchDAO=new JouerMatchDAO();
	
	public void init() throws ServletException
	{
		super.init();
	}
	
	public void service (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException 
	{
		String ajouter=req.getParameter("ajouter");
		connBD.connecter();
		EntityManager entityManager=connBD.getEntityManager();
		
		HttpSession session = req.getSession();
		String authorise = (String)session.getAttribute("user");
		if ("authorisierModifierBD".equals(authorise))
		{
			if("equipe".equals(ajouter))
			{
				ajouterEquipe(req, res, entityManager);
			}
			else if("joueur".equals(ajouter))
			{
				ajouterJoueur(req, res, entityManager);
			}
			else if("match".equals(ajouter))
			{
				ajouterMatch(req, res, entityManager);
			}
			else
			{
				System.out.println("fail!.");
			}
		}
		else
		{
			System.out.println("non authentification.");
			res.sendRedirect("/TPnoteFOOT/nonAUthenModifierBD.html");
		}
		connBD.fermer();
	}
	
	//ajouter une Equipe
	public void ajouterEquipe(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String nom=req.getParameter("nom");
		String Classement=req.getParameter("classement");
		String nbP=req.getParameter("nbP");
		String nbV=req.getParameter("nbV");
			try {
				equipeDAO.ajouterE(entityManager, nom, new ArrayList<Joueur>(), Integer.parseInt(Classement), Integer.parseInt(nbP), Integer.parseInt(nbV));
				try {
					req.getRequestDispatcher("/ajouterFiles/ajouterResultat.jsp").forward(req, res);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	//ajouter un joueur
	public void ajouterJoueur(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String nom=req.getParameter("nom");
		String buts=req.getParameter("buts");
		String role=req.getParameter("role");
		String note=req.getParameter("note");
		String equipe=req.getParameter("equipe");
		Equipe equipe2=equipeDAO.rechercherE(entityManager, equipe).get(0);
		try {
			try {
				joueurDAO.ajouterJ(entityManager, nom, role, Double.parseDouble(note), Integer.parseInt(buts), equipe2);
				req.getRequestDispatcher("/ajouterFiles/ajouterResultat.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//ajouter un match
	public void ajouterMatch(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String equipe1=req.getParameter("equipe1");
		String equipe2=req.getParameter("equipe2");
		String date=req.getParameter("date");
		String ville=req.getParameter("ville");
		String stade=req.getParameter("stade");
		if(!equipe1.equals(equipe2))
		{
			try {
				try {
					Equipe equipe3=equipeDAO.rechercherE(entityManager, equipe1).get(0);
					Equipe equipe4=equipeDAO.rechercherE(entityManager, equipe2).get(0);
					Calendrier calendrier=new Calendrier();
					calendrier.setVille(ville);
					calendrier.setStade(stade);
					DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
					Date date1;
					try {
						date1 = dateFormat.parse(date);
						calendrier.setDate(date1);
						FootMatch footMatch=footMatchDAO.AjouterFM(entityManager, "NULL", "NULL", equipe3, equipe4, calendrier, new ArrayList<JouerMatch>());
						for(int i=0;i<equipe3.getJoueur().size();i++)
						{
							jouerMatchDAO.ajouterJM(entityManager, equipe3.getJoueur().get(i), footMatch);
						}
						for(int j=0;j<equipe4.getJoueur().size();j++)
						{
							jouerMatchDAO.ajouterJM(entityManager, equipe4.getJoueur().get(j), footMatch);
						}
						req.getRequestDispatcher("/ajouterFiles/ajouterResultat.jsp").forward(req, res);
					} catch (ParseException e) {
						e.printStackTrace();
					}	
				} catch (ServletException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				res.sendRedirect("/TPnoteFOOT/ajouterFiles/ajouterError.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void destroy() 
	{
		super.destroy();
	}
}
