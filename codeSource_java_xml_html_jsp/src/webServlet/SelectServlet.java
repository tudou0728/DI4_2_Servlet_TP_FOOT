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
import dao.CalendrierDAO;
import dao.EquipeDAO;
import dao.FootMatchDAO;
import dao.JouerMatchDAO;
import dao.JoueurDAO;
import modele.Equipe;

// la classe servelet pour envoyer les informations d'equipes a la page JSP(<select><select/>)
public class SelectServlet extends HttpServlet
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
		String select=req.getParameter("ajouter");
		connBD.connecter();
		EntityManager entityManager=connBD.getEntityManager();
		List<Equipe> equipes=equipeDAO.getAll(entityManager);
		HttpSession session = req.getSession();
		String authorise = (String)session.getAttribute("user");//获取session中authenticated所对应的值
		if ("authorisierModifierBD".equals(authorise))
		{
			req.setAttribute("equipes", equipes);
			if("equipe".equals(select))
			{
				req.getRequestDispatcher("/ajouterFiles/ajouterE.jsp").forward(req, res);
			}
			else if("joueur".equals(select))
			{
				req.getRequestDispatcher("/ajouterFiles/ajouterJ.jsp").forward(req, res);
			}
			else if("match".equals(select))
			{
				req.getRequestDispatcher("/ajouterFiles/ajouterM.jsp").forward(req, res);
			}
		}
		else
		{
			System.out.println("non authentification.");
			res.sendRedirect("/TPnoteFOOT/nonAUthenModifierBD.html");
		}
		connBD.fermer();
	}
	
	public void destroy() 
	{
		super.destroy();
	}
}
