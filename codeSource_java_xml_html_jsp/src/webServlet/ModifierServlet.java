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
import dao.CalendrierDAO;
import dao.EquipeDAO;
import dao.FootMatchDAO;
import dao.JouerMatchDAO;
import dao.JoueurDAO;
import modele.Calendrier;
import modele.Equipe;
import modele.FootMatch;
import modele.Joueur;

// la classe servlet pour modifier BD
public class ModifierServlet extends HttpServlet
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
		HttpSession httpSession=req.getSession();
		String modifier=req.getParameter("modifier");
		connBD.connecter();
		EntityManager entityManager=connBD.getEntityManager();
		
		HttpSession session = req.getSession();
		String authorise = (String)session.getAttribute("user");
		if ("authorisierModifierBD".equals(authorise))
		{
			if("equipe".equals(modifier))
			{
				modifierEquipe(req,res,entityManager);
			}
			else if("joueur".equals(modifier))
			{
				modifierJoueur(req,res,entityManager);
			}
			else if("match".equals(modifier))
			{
				modifierMatch(req,res,entityManager);
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
	
	//modifier une equipe
	public void modifierEquipe(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String nom=req.getParameter("EquipeNom");
		System.out.println(nom);
		String id=req.getParameter("equipeId");
		String classement=req.getParameter("classement");
		List<Equipe> equipes=equipeDAO.rechercherEId(entityManager, Integer.parseInt(id));
		Equipe equipe=equipes.get(0);
		equipeDAO.modifierE(entityManager, equipe, equipe.getE_id(), nom, equipe.getNb_par(), equipe.getNb_vic(), Integer.parseInt(classement), equipe.getJoueur(), equipe.getFootMatch(), equipe.getFootMatch2());
		try {
			req.setAttribute("equipe", equipe);
			try {
				req.getRequestDispatcher("/reservedUtilisateur/modifierEquipe.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//modifier un joueur
	public void modifierJoueur(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		String id=req.getParameter("joueurId");
		String nom=req.getParameter("joueurNom");
		String buts=req.getParameter("joueurButs");
		String role=req.getParameter("joueurRole");
		String note=req.getParameter("joueurNote");
		String equipeNom=req.getParameter("joueurEquipe");     
		List<Equipe> equipes=equipeDAO.rechercherE(entityManager, equipeNom);
		List<Joueur> joueurs=joueurDAO.rechercherId(entityManager, id);
		if(joueurs!=null && joueurs.size()>0 && equipes!=null && equipes.size()>0)
		{
			Joueur joueur=joueurs.get(0);
			Equipe equipe=equipes.get(0);
			joueurDAO.modifierJ(entityManager, id, nom, Integer.parseInt(buts), role, Double.parseDouble(note), equipe);
			try {
				req.setAttribute("joueur", joueur);
				try {
					req.getRequestDispatcher("/reservedUtilisateur/modifierJoueur.jsp").forward(req, res);
				} catch (ServletException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	//modifier un match
	public void modifierMatch(HttpServletRequest req, HttpServletResponse res,EntityManager entityManager)
	{
		Date now=new Date();
		String id=req.getParameter("matchId");
		String date=req.getParameter("matchDate");
		String ville=req.getParameter("matchVille");
		String stade=req.getParameter("matchStade");
		String equipe1=req.getParameter("matchEquipe1");
		String res1=req.getParameter("matchNote1");
		String equipe2=req.getParameter("matchEquipe2");
		String res2=req.getParameter("matchNote2");
		List<FootMatch> footMatchs=footMatchDAO.rechercherId(entityManager, id);
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			Date dateF=format1.parse(date);
			List<Equipe> equipes1=equipeDAO.rechercherE(entityManager, equipe1);
			List<Equipe> equipes2=equipeDAO.rechercherE(entityManager, equipe2);
			if(footMatchs!=null && footMatchs.size()>0 && equipes1!=null && equipes1.size()>0 && equipes2!=null && equipes2.size()>0)
			{
				FootMatch footMatch=footMatchs.get(0);
				Calendrier calendrier=footMatch.getCalendrier();
				calendrierDAO.modifierC(entityManager, Integer.toString(calendrier.getC_id()), 
						dateF, ville, stade, calendrier.getFootMatch());
				if(dateF.getTime()>now.getTime() && "NULL".equals(footMatchs.get(0).getRes1()))
				{
					footMatchDAO.modifierFM(entityManager, id, 
							"NULL", "NULL", equipes1.get(0), equipes2.get(0), calendrier, footMatch.getJouerMatch());
				}					
				else
				{
					footMatchDAO.modifierFM(entityManager, id, 
						res1, res2, equipes1.get(0), equipes2.get(0), calendrier, footMatch.getJouerMatch());
				}					
				try {
					req.setAttribute("footMatch", footMatch);
					try {
						System.out.println("ok");							
						req.getRequestDispatcher("/reservedUtilisateur/modifierMatch.jsp").forward(req, res);
						} catch (ServletException e) {
							e.printStackTrace();
						}
					} 
				catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					try {
						res.sendRedirect("/TPnoteFOOT/modifierError.html");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		} 
		catch (ParseException e1) 
		{
			try {
				res.sendRedirect("/TPnoteFOOT/modifierError.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
	}
	
	public void destroy() 
	{
		super.destroy();
	}
}
