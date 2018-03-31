package webServlet;

import java.io.IOException;
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
import dao.JoueurDAO;
import modele.Equipe;
import modele.FootMatch;
import modele.Joueur;

// la classe servlet pour affichier tous les informations
public class AffichierTousServlet extends HttpServlet
{
	private ConnBD connBD=new ConnBD();
	private EquipeDAO equipeDAO=new EquipeDAO();
	private JoueurDAO joueurDAO=new JoueurDAO();
	private FootMatchDAO footMatchDAO=new FootMatchDAO();
	
	public void service (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException 
	{
		HttpSession httpSession=req.getSession();
		connBD.connecter();
		EntityManager entityManager=connBD.getEntityManager();
		String select=req.getParameter("affichier");
		if("equipes".equals(select))
		{
			affichierEquipe(req,res,entityManager);
		}
		else if("joueurs".equals(select))
		{
		affichierJoueur(req, res, entityManager);	
		}
		else if("matchs1".equals(select))
		{
			affichierMatchs1(req,res,entityManager);
		}
		else if("matchs2".equals(select))
		{
			affichierMatchs2(req,res,entityManager);
		}
		else {
			System.out.println("fail!.");	
			res.sendRedirect("/TPnoteFOOT/warningError.html");
		}
		connBD.fermer();
	}
	
	//affichier tous les informations d'une Equipe
	public void affichierEquipe(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		List<Equipe> equipes=equipeDAO.getAll(entityManager);
		if(equipes!=null && equipes.size()!=0)
		{
			req.setAttribute("equipes", equipes);	
			try {
				req.getRequestDispatcher("/reservedUtilisateur/tousLesEquipes.jsp").forward(req, res);
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
	//affichier tous les informations d'un joueur
	public void affichierJoueur(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		List<Joueur> joueurs=joueurDAO.getAll(entityManager);
		if(joueurs!=null && joueurs.size()!=0)
		{
			req.setAttribute("joueurs", joueurs);	
			try {
				req.getRequestDispatcher("/reservedUtilisateur/tousLesJoueurs.jsp").forward(req, res);
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
	
	//affichier tous les informations de match qui sont deja finis
	public void affichierMatchs1(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		List<FootMatch> footMatchs1=footMatchDAO.getDeja(entityManager);
		if(footMatchs1!=null && footMatchs1.size()!=0)
		{
			req.setAttribute("footMatchs1", footMatchs1);	
			try {
				req.getRequestDispatcher("/reservedUtilisateur/tousLesMatchs.jsp").forward(req, res);
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
	
	//affichier tous les informations de match a venir
	public void affichierMatchs2(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		List<FootMatch> footMatchs2=footMatchDAO.getPasEnCore(entityManager);
		if(footMatchs2!=null && footMatchs2.size()!=0)
		{
			req.setAttribute("footMatchs2", footMatchs2);	
			try {
				req.getRequestDispatcher("/reservedUtilisateur/matchAVenir.jsp").forward(req, res);
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
	
	public void destroy() 
	{
		super.destroy();
	}
}
