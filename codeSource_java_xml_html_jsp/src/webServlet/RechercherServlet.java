package webServlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controleur.ConnBD;
import dao.EquipeDAO;
import dao.FootMatchDAO;
import dao.JouerMatchDAO;
import dao.JoueurDAO;
import modele.Equipe;
import modele.FootMatch;
import modele.JouerMatch;
import modele.Joueur;

//la classe servlet pour rechercher les informations
public class RechercherServlet extends HttpServlet
{
	private ConnBD connBD=new ConnBD();
	private EquipeDAO equipeDAO=new EquipeDAO();
	private JoueurDAO joueurDAO=new JoueurDAO();
	private JouerMatchDAO joueurMatchDAO=new JouerMatchDAO();
	private FootMatchDAO footMatchDAO=new FootMatchDAO();
	
	public void init() throws ServletException
	{
		super.init();
	}
	
	public void service (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException 
	{
		HttpSession httpSession=req.getSession();
		connBD.connecter();
		EntityManager entityManager=connBD.getEntityManager();
		String select=req.getParameter("select");
		if("equipe".equals(select))
		{
			rechercherE(req,res,entityManager);
		}
		else if("joueur".equals(select))
		{
			rechercherJoueur(req, res, entityManager);
		}
		else if("match".equals(select))
		{
			rechercherMatch(req, res, entityManager);
		}
		else
		{
			res.sendRedirect("/TPnoteFOOT/warningError.html");
			System.out.println("fail!.");
		}
		connBD.fermer();
	}
	
	//rechercher une equipe
	public void rechercherE(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String nom=req.getParameter("EquipeNom");
		List<Equipe> equipes=equipeDAO.rechercherE(entityManager, nom);
		if(equipes!=null && equipes.size()!=0)
		{
			req.setAttribute("equipes", equipes);
			try {
				System.out.println("OK!");
				req.getRequestDispatcher("/reservedUtilisateur/equipeResultat.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else
		{
			try {
				res.sendRedirect("/TPnoteFOOT/reservedUtilisateur/resultatNull.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//rechercher un joueur
	public void rechercherJoueur(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String nom=req.getParameter("joueurNom");
		List<Joueur> joueurs=joueurDAO.rechercherJ(entityManager, nom);
		List<JouerMatch> jouerMatchs=joueurMatchDAO.rechercherJouerM(entityManager, nom);
		if(joueurs!=null && joueurs.size()!=0)
		{
			req.setAttribute("joueurs", joueurs);
			req.setAttribute("jouerMatchs", jouerMatchs);
			try {
				System.out.println("OK!");
				req.getRequestDispatcher("/reservedUtilisateur/joueurResultat.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else
		{
			try {
				res.sendRedirect("/TPnoteFOOT/reservedUtilisateur/resultatNull.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//rechercher un match
	public void rechercherMatch(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		DateFormat format1 = new SimpleDateFormat("dd-MM-yyyy"); 
		String jour=req.getParameter("jour");
		String mois=req.getParameter("mois");
		String annee=req.getParameter("annee");
		String date1=jour+"-"+mois+"-"+annee;
		Date date;
		try {
			date = format1.parse(date1);
			List<FootMatch> footMatchs=footMatchDAO.rechercherE(entityManager, date);
			if(footMatchs!=null && footMatchs.size()!=0)
			{
				req.setAttribute("footMatchs", footMatchs);
				try {
					System.out.println("OK!");
					req.getRequestDispatcher("/reservedUtilisateur/matchResultat.jsp").forward(req, res);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			else
			{
				try {
					res.sendRedirect("/TPnoteFOOT/reservedUtilisateur/resultatNull.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	public void destroy() 
	{
		super.destroy();
	}
}
