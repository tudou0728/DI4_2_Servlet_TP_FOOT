package webServlet;

import java.io.IOException;
import java.sql.SQLException;

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

//la classe servlet pour supprimer les informations
public class SupprimerServlet extends HttpServlet
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
		String modifier=req.getParameter("supprimer");
		connBD.connecter();
		EntityManager entityManager=connBD.getEntityManager();
		
		HttpSession session = req.getSession();
		String authorise = (String)session.getAttribute("user");
		if ("authorisierModifierBD".equals(authorise))
		{
			if("equipe".equals(modifier))
			{
				supprimerEquipe(req, res, entityManager);
			}
			else if("joueur".equals(modifier))
			{
				supprimerJoueur(req, res, entityManager);
			}
			else if("match".equals(modifier))
			{
				supprimerMatch(req, res, entityManager);
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
	
	//supprimer une equipe
	public void supprimerEquipe(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager) 
	{
		String id=req.getParameter("equipeId");
		try {
			try {
				try {
					if(equipeDAO.supprimerE(entityManager, Integer.parseInt(id)))
					{
						req.setAttribute("supprimerResultat", "supprimer equipe: "+id + "  reussir");
					}
					else {
						req.setAttribute("supprimerResultat", "Warning: supprimer equipe: "+id + "  error !!!");
					}
				} catch (NumberFormatException e) {
					res.sendRedirect("/TPnoteFOOT/modifierError.html");
					e.printStackTrace();
				} catch (SQLException e) {
					res.sendRedirect("/TPnoteFOOT/modifierError.html");
					e.printStackTrace();
				}
				req.getRequestDispatcher("/reservedUtilisateur/supprimerResultat.jsp").forward(req, res);		
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	//supprimer un joueur
	public void supprimerJoueur(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String id=req.getParameter("joueurId");
		try {
			try {
				if(joueurDAO.supprimerJ(entityManager, Integer.parseInt(id)))
				{
					req.setAttribute("supprimerResultat", "supprimer joueur "+id+" reussir!");
				}
				else
				{
					req.setAttribute("supprimerResultat", "Warning: supprimer joueur: "+id + "  error !!!");
				}
				req.getRequestDispatcher("/reservedUtilisateur/supprimerResultat.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//supprimer un match
	public void supprimerMatch(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String id=req.getParameter("matchId");
		try {
			try {
				if(footMatchDAO.supprimerFM(entityManager, Integer.parseInt(id)))
				{
					req.setAttribute("supprimerResultat", "supprimer match "+id+" reussir!");
				}
				else
				{
					req.setAttribute("supprimerResultat", "Warning: supprimer match: "+id + "  error !!!");
				}
				req.getRequestDispatcher("/reservedUtilisateur/supprimerResultat.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() 
	{
		super.destroy();
	}
}
