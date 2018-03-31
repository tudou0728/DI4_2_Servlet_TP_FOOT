package webServlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controleur.ConnBD;
import dao.UtilisateurDAO;
import modele.Utilisateur;

//la classe servlet pour login
public class LogInServlet extends HttpServlet
{
	private ConnBD connBD=new ConnBD();
	private UtilisateurDAO utilisateurDAO=new UtilisateurDAO();
	
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
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		RequestDispatcher dispatcher;
		
		List<Utilisateur> resultList=utilisateurDAO.selectUtilisateur(entityManager, username, password);			
		if(resultList!=null && resultList.size()==1)
		{
			Utilisateur utilisateur=resultList.get(0);
			if(utilisateur.isAutorise())
			{
				httpSession.setAttribute("user", "authorisierModifierBD");
			}
			else
			{
				httpSession.setAttribute("user", "login");
			}			
			System.out.println("message: log in reussir!");
			res.sendRedirect("/TPnoteFOOT/reservedUtilisateur/homePage.jsp");
		}
		else
		{
			httpSession.setAttribute("user", "nonlogin");
			System.out.println("message: pas de bon nom ou mdp, essayer l'autre fois!");
			res.sendRedirect(" /TPnoteFOOT/loginFail.html");
		}
		connBD.fermer();
	}
	
	public void destroy() 
	{
		super.destroy();
	}
}
